package com.neo.repository;

import com.neo.model.Orders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testSaveOrders() throws Exception {
        Orders Orders1=new Orders("A123",500,"A");
        Orders Orders2=new Orders("A123",250,"A");
        Orders Orders3=new Orders("B212",200,"A");
        Orders Orders4=new Orders("A123",300,"D");
        mongoTemplate.save(Orders1);
        mongoTemplate.save(Orders2);
        mongoTemplate.save(Orders3);
        mongoTemplate.save(Orders4);
    }

}
