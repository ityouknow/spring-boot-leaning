package com.neo.service.impl;

import com.neo.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServieImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("hello service");
    }
}
