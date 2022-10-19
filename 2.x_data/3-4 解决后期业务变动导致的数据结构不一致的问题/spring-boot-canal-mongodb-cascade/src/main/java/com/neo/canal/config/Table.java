package com.neo.canal.config;

import com.alibaba.otter.canal.protocol.CanalEntry;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface Table {
    String value() default "";

    CanalEntry.EventType[] event() default {};
}
