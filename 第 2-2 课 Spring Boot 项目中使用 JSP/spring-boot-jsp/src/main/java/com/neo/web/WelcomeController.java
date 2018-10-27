package com.neo.web;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
public class WelcomeController {

	@GetMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", "hello world");
		return "welcome";
	}

	@GetMapping("/user")
	public String user(Map<String, Object> model, HttpServletRequest request) {
		model.put("username", "neo");
		model.put("salary", 666);
		request.getSession().setAttribute("count",6);
		return "user";
	}

}