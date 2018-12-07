package com.neo.model;

import java.text.DateFormat;
import java.util.Date;

import com.neo.repository.UserRepository;
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
	public void testRepository() throws Exception {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);        
		String formattedDate = dateFormat.format(date);
		
		userRepository.save(new User("aa@126.com","neo",  "123456","neo", formattedDate));
		userRepository.save(new User("bb@126.com","pure",  "bb123456","小张", formattedDate));
		userRepository.save(new User("cc@126.com","smile",  "cc123456","小王" ,formattedDate));

	}

}