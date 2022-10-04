package com.moxuanran.learning.redis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wutao
 * @date 2022/9/29 16:42
 */
@Component
public class TimeTaskManager implements ApplicationListener<ApplicationReadyEvent>, DisposableBean {
    private static final Logger logger = LoggerFactory.getLogger(TimeTaskManager.class);
    private volatile boolean running = false;
    private final StringRedisTemplate redisTemplate;
    private final List<TimedTask> tasks;

    public TimeTaskManager(StringRedisTemplate redisTemplate, List<TimedTask> tasks) {
        this.redisTemplate = redisTemplate;
        this.tasks = tasks;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        synchronized (TimeTaskManager.class) {
            if (running) {
                return;
            }
            running = true;
        }
        // 将注解的任务加入到任务集合
        registerAnnotationTask(event.getApplicationContext());
        tasks.forEach(TimedTask::start);
    }

    @Override
    public void destroy() throws Exception {
        tasks.forEach(TimedTask::destroy);
    }

    /**
     * 找到所有带RedisTask注解的方法, 并注册到任务集合
     *
     * @param applicationContext ..
     */
    private void registerAnnotationTask(ApplicationContext applicationContext) {
        Collection<Object> beans = applicationContext.getBeansOfType(Object.class).values();
        for (Object bean : beans) {
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                RedisTask task = AnnotationUtils.findAnnotation(method, RedisTask.class);
                if (task != null) {
                    int parameterCount = method.getParameterCount();
                    if (parameterCount > 0) {
                        throw new IllegalStateException("RedisTask method parameter count: "
                                + parameterCount + ", except: 0");
                    }
                    TimedTask timedTask = new AnnoTimedTask(task, method, bean, this.redisTemplate);
                    tasks.add(timedTask);
                    logger.info("registerAnnotationTask > taskName: {}", timedTask.getTaskName());
                }
            }
        }
    }

    /**
     * 注解的任务封装类
     */
    private static class AnnoTimedTask extends AbstractTimedTask {

        private final RedisTask redisTask;
        private final Method method;
        private final Object bean;

        public AnnoTimedTask(RedisTask redisTask, Method method, Object bean, StringRedisTemplate redisTemplate) {
            super(redisTemplate);
            this.redisTask = redisTask;
            this.method = method;
            this.bean = bean;
        }

        @Override
        public String getTaskName() {
            String taskName = redisTask.value();
            if (StringUtils.isEmpty(taskName)) {
                return bean.getClass().getName() + ":" + method.getName();
            }
            return taskName;
        }

        @Override
        public long getInterval() {
            return redisTask.interval();
        }

        @Override
        public long getInitialDelay() {
            return redisTask.initialDelay();
        }

        @Override
        public TimeUnit getTimeUnit() {
            return redisTask.timeUnit();
        }

        @Override
        public void runOnce() {
            try {
                method.invoke(bean);
            } catch (Exception e) {
                logger.error("Run Redis TimedTask failed", e);
            }
        }
    }


}
