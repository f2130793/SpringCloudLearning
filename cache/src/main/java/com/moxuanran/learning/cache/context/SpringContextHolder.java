package com.moxuanran.learning.cache.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author wutao
 * @date 2020/3/24 14:40
 */
@Component
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext appContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        appContext = null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T)appContext.getBean(name);
    }

    public static <T> T getBean(Class<T> aClass) {
        return appContext.getBean(aClass);
    }

}
