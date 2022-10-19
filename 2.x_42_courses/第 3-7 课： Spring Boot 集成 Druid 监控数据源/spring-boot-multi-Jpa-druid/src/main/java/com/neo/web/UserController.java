package com.neo.web;


import com.neo.model.User;
import com.neo.repository.test1.UserTest1Repository;
import com.neo.repository.test2.UserTest2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
	
	@Autowired
	private UserTest1Repository userTest1Repository;
	@Autowired
	private UserTest2Repository userTest2Repository;

	@RequestMapping("/getUsers")
	public List<User> getUsers() {
		List<User> users1=userTest1Repository.findAll();
		List<User> users2=userTest2Repository.findAll();
		users1.addAll(users2);
		return users1;
	}


    
}