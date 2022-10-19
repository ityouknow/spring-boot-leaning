package com.neo.repository;

import com.neo.model.User;
import com.neo.model.UserDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Resource
    MongoTemplate mongoTemplate;

    @Test
    public void testQueryUserDetail() throws Exception {
        Query query = new Query(Criteria.where("userId").is(1));
        UserDetail userDetail= mongoTemplate.findOne(query,UserDetail.class);
        if(userDetail!=null){
            System.out.println("**************: "+userDetail.toString());
        }else {
            System.out.println("*****userDetail is null *********");
        }
    }



}
