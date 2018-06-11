package com.neo.repository;

import com.neo.domain.User;
import com.neo.domain.UserDetail;
import com.neo.domain.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoRepositoryTests {

	@Resource
    private UserDetailRepository userDetailRepository;


	@Test
	public void testSave() {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);
		UserDetail ud=new UserDetail();
		ud.setHobby("打球");
		ud.setUserId("2");
		ud.setAddress("北京市");
		userDetailRepository.save(ud);
	}

	@Test
	public void testUserInfo()  {
		List<UserInfo> userInfos=userDetailRepository.findUserInfo("打球");
		for (UserInfo userInfo:userInfos){
			System.out.println("UserName === "+userInfo.getUserName());
			System.out.println("Address === "+userInfo.getAddress());
		}
	}


}