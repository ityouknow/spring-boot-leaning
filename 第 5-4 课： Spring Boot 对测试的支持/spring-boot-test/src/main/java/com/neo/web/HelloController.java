package com.neo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping(name="/hello")
    public String getHello() {
        return "hello web";
    }
}
