package com.neo.service.impl;

import com.neo.model.User;
import com.neo.repository.test1.UserTest1Repository;
import com.neo.repository.test2.UserTest2Repository;
import com.neo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import java.text.DateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserTest1Repository userTest1Repository;
	@Resource
	private UserTest2Repository userTest2Repository;

	@Override
	@Transactional
	public void save() {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);
		userTest1Repository.save(new User("aa", "aa123456", "aa@126.com", "aa", formattedDate));
		userTest1Repository.save(new User("bb", "bb123456", "bb@126.com", "bb", formattedDate));
		userTest2Repository.save(new User("cc", "cc123456", "cc@126.com", "cc", formattedDate));
		if(true){
			throw  new RuntimeException("test Runtime Exception");
		}
	}

	@Override
	@Transactional
	public void delete() {
		userTest1Repository.deleteAll();
		userTest2Repository.deleteAll();
	}
}
