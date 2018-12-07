package com.neo.model;

import java.text.DateFormat;
import java.util.Date;

import com.neo.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void test() throws Exception {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);        
		String formattedDate = dateFormat.format(date);
		
		userRepository.save(new User("aa@126.com","neo",  "aa12345d6","小明", formattedDate));
		userRepository.save(new User("bb@126.com","pure",  "bb12345d6","小张", formattedDate));
		userRepository.save(new User("cc@126.com","smile",  "cc12345d6","小王" ,formattedDate));

		Assert.assertEquals(3, userRepository.findAll().size());
		Assert.assertEquals("smile", userRepository.findByUserNameOrEmail("smile", "cc@126.com").getNickname());
		userRepository.delete(userRepository.findByUserName("小张"));
	}

}