package com.neo.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String  getUser(){
        String user="i am neo!";
        return user;
    }
}
