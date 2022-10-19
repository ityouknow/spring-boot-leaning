package com.neo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index() {
        return "你好，世界！";
    }


    @RequestMapping("/sayHi")
    public String sayHi(String name) {
        return name+ ", 你好！";
    }
}