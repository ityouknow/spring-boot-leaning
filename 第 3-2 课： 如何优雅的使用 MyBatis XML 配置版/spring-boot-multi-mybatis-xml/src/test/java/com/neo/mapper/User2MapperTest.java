package com.neo.mapper;

import com.neo.model.User;
import com.neo.enums.UserSexEnum;
import com.neo.mapper.two.User2Mapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class User2MapperTest {

	@Autowired
	private User2Mapper userMapper;

	@Test
	public void testInsert() throws Exception {
		userMapper.insert(new User("aammx2", "a123456", UserSexEnum.MAN));
		userMapper.insert(new User("bbmmx2", "b123456", UserSexEnum.WOMAN));
		userMapper.insert(new User("ccmmx2", "b123456", UserSexEnum.WOMAN));

//		Assert.assertEquals(3, userMapper.getAll().size());
	}

	@Test
	public void testQuery() throws Exception {
		List<User> users = userMapper.getAll();
		if(users==null || users.size()==0){
			System.out.println("is null");
		}else{
			System.out.println(users.toString());
		}
	}
	
	
	@Test
	public void testUpdate() throws Exception {
		User user = userMapper.getOne(30l);
		System.out.println(user.toString());
		user.setNickName("neo");
		userMapper.update(user);
		Assert.assertTrue(("neo".equals(userMapper.getOne(6l).getNickName())));
	}

}