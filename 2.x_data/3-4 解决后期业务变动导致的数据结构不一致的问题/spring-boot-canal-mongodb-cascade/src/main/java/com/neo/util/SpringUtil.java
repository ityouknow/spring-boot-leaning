package com.neo.util;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.neo.canal.config.Schema;
import com.neo.canal.config.Table;
import org.bson.Document;
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
            //初始化instanceMap数据
            instanceMap();
            //初始化handlerMap数据
            handlerMap();
        }
    }

    private void instanceMap() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Schema.class);
        for (Object bean : beans.values()) {
            Class<?> clazz = bean.getClass();
            Object instance = applicationContext.getBean(clazz);
            Schema schema = clazz.getAnnotation(Schema.class);
            String key = schema.value();
            instanceMap.put(key, instance);
            logger.info("instanceMap [{}:{}]", key, bean == null ? "null" : clazz.getName());
        }
    }

    private void handlerMap() {
        if (instanceMap.size() <= 0)
            return;
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
            if (entry.getValue().getClass().isAnnotationPresent(Schema.class)) {
                Schema schema = entry.getValue().getClass().getAnnotation(Schema.class);
                String schemeName = schema.value();
                Method[] methods = entry.getValue().getClass().getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(Table.class)) {
                        Table table = method.getAnnotation(Table.class);
                        String tName = table.value();
                        CanalEntry.EventType[] events = table.event();
                        //未标明数据事件类型的方法不做映射
                        if (events.length < 1) {
                            continue;
                        }
                        //同一个方法可以映射多张表
                        for (int i = 0; i < events.length; i++) {
                            String path = "/" + schemeName + "/" + tName + "/" + events[i].getNumber();
                            handlerMap.put(path, method);
                            logger.info("handlerMap [{}:{}]", path, method.getName());
                        }
                    } else {
                        continue;
                    }
                }
            } else {
                continue;
            }

        }
    }

    public static void doEvent(String path, Document obj) throws Exception {
        String[] pathArray = path.split("/");
        if (pathArray.length != 4) {
            logger.info("path 格式不正确: {}", path);
            return;
        }
        Method method = handlerMap.get(path);
        Object schema = instanceMap.get(pathArray[1]);
        //查找不到映射Bean和Method不做处理
        if (method == null || schema == null) {
            return;
        }
        try {
            long begin = System.currentTimeMillis();
            logger.info("integrate data: {} , {}", path, obj);
            method.invoke(schema, new Object[]{obj});
            logger.info("integrate data consume: {}ms ", System.currentTimeMillis() - begin);
        } catch (Exception e) {
            logger.error("调用组合逻辑异常", e);
            throw new Exception(e.getCause());
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
