package com.neo.repository;

import com.neo.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() throws Exception {
        for (long i=0;i<100;i++) {
            UserEntity user=new UserEntity();
            user.setId(i);
            user.setUserName("小明"+i);
            user.setPassWord("fffooo123");
            userRepository.save(user);
        }
    }

    @Test
    public void findUserByUserName(){
       UserEntity user= userRepository.findByUserName("小明");
       System.out.println("user is "+user);
    }

    @Test
    public void updateUser(){
        UserEntity user=new UserEntity();
        user.setId(2l);
        user.setUserName("天空");
        user.setPassWord("fffxxxx");
        userRepository.save(user);
    }

    @Test
    public void deleteUserById(){
        userRepository.delete(2l);
    }

    @Test
    public void testPage(){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(2, 10, sort);
        Page page=userRepository.findAll(pageable);
        System.out.println("users: "+page.getContent().toString());
    }

}
