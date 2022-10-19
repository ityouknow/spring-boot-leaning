package com.neo.repository;

import com.neo.model.User;
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
public class RepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() throws Exception {
        for (long i=0;i<100;i++) {
            User user=new User();
            user.setId(i);
            user.setUserName("小明"+i);
            user.setPassWord("fffooo123");
            userRepository.save(user);
        }
    }

    @Test
    public void findUserByUserName(){
      User user= userRepository.findByUserName("小明1");
       System.out.println("user is "+user);
    }

    @Test
    public void updateUser(){
        User user=new User();
        user.setId(2l);
        user.setUserName("天空");
        user.setPassWord("fffxxxx");
        userRepository.save(user);
    }

    @Test
    public void deleteUserById(){
        userRepository.deleteById(2l);
    }

    @Test
    public void testPage(){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(2, 10, sort);
        Page page=userRepository.findAll(pageable);
        System.out.println("users: "+page.getContent().toString());
    }

}
