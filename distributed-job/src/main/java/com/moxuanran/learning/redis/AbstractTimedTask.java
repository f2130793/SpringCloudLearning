package com.moxuanran.learning.redis;

import com.moxuanran.learning.util.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wutao
 * @date 2022/9/29 16:27
 */
public abstract class AbstractTimedTask implements TimedTask {
    private static final Logger logger = LoggerFactory.getLogger(AbstractTimedTask.class);

    protected static final String LOCAL_IP = IPUtils.getLocalHost();

    protected static final String PROCESSOR_ID = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];

    protected final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1,
            (r) -> new Thread(r, "redis-timed-task"));

    protected final StringRedisTemplate redisTemplate;

    protected AbstractTimedTask(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 开始任务
     */
    @Override
    public void start() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                if (tryRequire()) {
                    this.runOnce();
                }
            } catch (Exception e) {
                logger.error("TimedTask error", e);
            }
        }, getInitialDelay(), getInterval(), getTimeUnit());
    }

    /**
     * 任务名称，唯一
     *
     * @return {@link String}
     */
    @Override
    public String getTaskName() {
        return this.getClass().getName();
    }

    /**
     * 让时间单位
     *
     * @return {@link TimeUnit}
     */
    @Override
    public TimeUnit getTimeUnit() {
        return TimeUnit.SECONDS;
    }

    /**
     * 首次执行延迟
     *
     * @return long
     */
    @Override
    public long getInitialDelay() {
        return 0;
    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {
        scheduler.shutdown();
        tryRelease();
    }

    private boolean tryRequire() {
        String key = getKey();
        String instanceId = LOCAL_IP + ":" + PROCESSOR_ID;
        long interval = getTimeUnit().toSeconds(getInterval());
        long ttl = (long) (interval * 1.5D);
        if (interval == ttl) {
            // 至少多占1s
            ttl = interval + 1;
        }
        String script = "local instanceId,ttl=ARGV[1],ARGV[2] ";
        script += "if redis.call('EXISTS', KEYS[1])==1 " +
                "then local curValue = redis.call('GET', KEYS[1]) " +
                "if curValue==instanceId " +
                "then redis.call('EXPIRE', KEYS[1], ttl) " +
                "return true " +
                "else " +
                "return false " +
                "end " +
                "else " +
                "redis.call('SET', KEYS[1], instanceId) " +
                "redis.call('EXPIRE', KEYS[1], ttl) " +
                "return true " +
                "end";
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Boolean.class);
        redisScript.setScriptText(script);
        List<String> keys = new ArrayList<>();
        keys.add(key);
        return Boolean.TRUE.equals(this.redisTemplate.execute(redisScript, keys, instanceId, String.valueOf(ttl)));
    }

    /**
     * 释放执行权
     */
    private void tryRelease() {
        String key = getKey();
        String instanceId = LOCAL_IP + ":" + PROCESSOR_ID;
        String script = "local instanceId=ARGV[1] ";
        script += "if redis.call('EXISTS', KEYS[1])==1 " +
                "then local curValue = redis.call('GET', KEYS[1]) " +
                "if curValue==instanceId " +
                "then redis.call('DEL', KEYS[1]) " +
                "return true " +
                "else return false " +
                "end " +
                "else return false " +
                "end";
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Boolean.class);
        redisScript.setScriptText(script);
        List<String> keys = new ArrayList<>();
        keys.add(key);
        this.redisTemplate.execute(redisScript, keys, instanceId);
    }

    /**
     * 任务对应的redis key
     *
     * @return ..
     */
    protected String getKey() {
        return "TimedTask:" + getTaskName();
    }

}
