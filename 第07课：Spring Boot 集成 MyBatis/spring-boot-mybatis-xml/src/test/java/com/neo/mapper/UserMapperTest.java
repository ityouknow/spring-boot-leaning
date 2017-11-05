package com.neo.mapper;

import java.util.List;

import com.neo.param.UserParam;
import com.neo.result.Page;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.neo.entity.UserEntity;
import com.neo.enums.UserSexEnum;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

	@Resource
	private UserMapper userMapper;

	@Test
	public void testInsert()  {
		userMapper.insert(new UserEntity("aa", "a123456", UserSexEnum.MAN));
		userMapper.insert(new UserEntity("bb", "b123456", UserSexEnum.WOMAN));
		userMapper.insert(new UserEntity("cc", "b123456", UserSexEnum.WOMAN));

		Assert.assertEquals(3, userMapper.getAll().size());
	}

	@Test
	public void testQuery() {
		List<UserEntity> users = userMapper.getAll();
		if(users==null || users.size()==0){
			System.out.println("is null");
		}else{
			System.out.println(users.toString());
		}
	}
	
	
	@Test
	public void testUpdate() {
		long id=30l;
		UserEntity user = userMapper.getOne(id);
		if(user!=null){
			System.out.println(user.toString());
			user.setNickName("neo");
			userMapper.update(user);
			Assert.assertTrue(("neo".equals(userMapper.getOne(id).getNickName())));
		}else {
			System.out.println("not find user id="+id);
		}
	}


	@Test
	public void testDelete() {
		int count=userMapper.delete(29l);
		if(count>0){
			System.out.println("delete is sucess");
		}else {
			System.out.println("delete if failed");
		}
	}


	@Test
	public void testPage() {
		UserParam userParam=new UserParam();
		userParam.setUserSex("WOMAN");
		userParam.setCurrentPage(1);
		List<UserEntity> users=userMapper.getList(userParam);
		long count=userMapper.getCount(userParam);
		Page page = new Page(userParam,count,users);
		System.out.println(page);
	}
}