package com.neo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/layout")
    public String layout() {
        return "layout";
    }


    @RequestMapping("/home")
    public String home() {
        return "home";
    }

}