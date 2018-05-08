package com.neo.controller;

import com.neo.model.User;
import com.neo.repository.test1.UserTest1Repository;
import com.neo.repository.test2.UserTest2Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.util.Date;

@RestController
public class AtomikosController {
    @Resource
    private UserTest1Repository userTest1Repository;
    @Resource
    private UserTest2Repository userTest2Repository;

    @RequestMapping("/")
    @Transactional
    public String save() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userTest1Repository.save(new User("aa", "aa123456", "aa@126.com", "aa", formattedDate));
/*        if(true){
            throw  new RuntimeException("test Runtime Exception");
        }*/
        userTest1Repository.save(new User("bb", "bb123456", "bb@126.com", "bb", formattedDate));
        userTest2Repository.save(new User("cc", "cc123456", "cc@126.com", "cc", formattedDate));
        return "Save success !";
    }
	
    @RequestMapping("/delete")
    @Transactional
    public String delete() {
        userTest1Repository.deleteAll();
        if(true){
            throw  new RuntimeException("test Runtime Exception");
        }
        userTest2Repository.deleteAll();
        return "delete all";
    }


}