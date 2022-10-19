package com.neo.mapper;

import java.util.List;

import com.neo.mapper.one.User1Mapper;
import com.neo.mapper.one.User1Mapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.neo.model.User;
import com.neo.enums.UserSexEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class User1MapperTest {

	@Autowired
	private User1Mapper userMapper;

	@Test
	public void testInsert() throws Exception {
		userMapper.insert(new User("aammx", "a123456", UserSexEnum.MAN));
		userMapper.insert(new User("bbmmx", "b123456", UserSexEnum.WOMAN));
		userMapper.insert(new User("ccmmx", "b123456", UserSexEnum.WOMAN));

//		Assert.assertEquals(3, userMapper.getAll().size());
	}

	@Test
	public void testQuery() throws Exception {
		List<User> users = userMapper.getAll();
		if(users==null || users.size()==0){
			System.out.println("is null");
		}else{
			System.out.println(users.size());
		}
	}
	
	
	@Test
	public void testUpdate() throws Exception {
		User user = userMapper.getOne(40L);
		System.out.println(user.toString());
		user.setNickName("smile");
		userMapper.update(user);
		Assert.assertTrue(("smile".equals(userMapper.getOne(40l).getNickName())));
	}

}