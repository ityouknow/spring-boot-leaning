package com.neo.repository;

import com.neo.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

	@Resource
    private UserRepository userRepository;

	@Test
	public void testSave() {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);
		
		userRepository.save(new User("aa", "aa123456","aa@126.com", "aa",  formattedDate));
		userRepository.save(new User("bb", "bb123456","bb@126.com", "bb",  formattedDate));
		userRepository.save(new User("cc", "cc123456","cc@126.com", "cc",  formattedDate));

		Assert.assertEquals(3, userRepository.findAll());
		Assert.assertEquals("bb", userRepository.findByUserNameOrEmail("bb", "bb@126.com").getNickName());
//		userRepository.delete(userRepository.findByUserName("aa"));
	}


	@Test
	public void testBaseQuery() {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);
		User user=new User("ff", "ff123456","ff@126.com", "ff",  formattedDate);
//		userRepository.findAll();
//		userRepository.findOne(3l);
//		userRepository.save(user);
//		user.setId(2l);
//		userRepository.delete(user);
//		userRepository.count();
		userRepository.exists(3l);
	}

	@Test
	public void testCustomSql() {
		userRepository.modifyById("neo",3l);
//		userRepository.deleteById(3l);
		userRepository.findByEmail("ff@126.com");
	}


	@Test
	public void testPageQuery()  {
		int page=1,size=2;
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(page, size, sort);
		userRepository.findALL(pageable);
		userRepository.findByNickName("aa", pageable);
	}

}