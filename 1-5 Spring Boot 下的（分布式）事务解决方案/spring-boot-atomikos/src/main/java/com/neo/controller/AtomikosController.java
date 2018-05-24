package com.neo.controller;

import com.neo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@RestController
public class AtomikosController {
    @Resource
    private UserService userService;

    @RequestMapping("/")
    public String save() {
        userService.save();
        return "Save success !";
    }

    @RequestMapping("/delete")
    @Transactional
    public String delete() {
        userService.delete();
        return "delete all !";
    }
}