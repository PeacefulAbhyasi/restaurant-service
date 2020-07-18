package com.interview.restaurant.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class ApplicationContextConfig {

    private static ApplicationContextConfig _INSTANCE = new ApplicationContextConfig();

    private ApplicationContext applicationContext;

    public static ApplicationContextConfig getInstance() {
        return _INSTANCE;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public <T> T getBean(Class<T> clazz) {
        return _INSTANCE.applicationContext.getBean(clazz);
    }

    public <T> T getBean(String bean, Class<T> clazz) {
        return (T) _INSTANCE.applicationContext.getBean(bean, clazz);
    }
}
