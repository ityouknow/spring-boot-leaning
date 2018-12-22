package com.neo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping("/content")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String content() {
        return "content";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
}