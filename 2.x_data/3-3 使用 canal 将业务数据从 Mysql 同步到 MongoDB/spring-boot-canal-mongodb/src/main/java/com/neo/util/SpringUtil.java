package com.neo.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * spring工具类，根据名称或者类型获取bean
 */
public class SpringUtil implements ApplicationContextAware {
    private static Logger logger = LoggerFactory.getLogger(SpringUtil.class);

    private static ApplicationContext applicationContext = null;
    //库名和数据处理Bean映射Map
    private static Map<String, Object> instanceMap = new HashMap<String, Object>();
    //路劲和数据处理Method映射Map
    private static Map<String, Method> handlerMap = new HashMap<String, Method>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
