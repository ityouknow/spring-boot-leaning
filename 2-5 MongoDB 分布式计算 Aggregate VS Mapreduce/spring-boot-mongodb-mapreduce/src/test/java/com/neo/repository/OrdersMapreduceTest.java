package com.neo.repository;

import com.neo.model.Orders;
import com.neo.result.OrderTotal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersMapreduceTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testMapreduce() {
        System.out.println("**********bengin*************");

        String mapFunction = "function(){ emit(this.cust_id , this.amount ); }";
        String reduceFunction = "function(key,values){ return Array.sum(values);}";

        Criteria criteria = new Criteria().where("status").is("A");
        Query query = new Query(criteria);

        MapReduceOptions options = MapReduceOptions.options();
        options.finalizeFunction(" function(key, reducedVal){\n" +
                                "        reducedVal = reducedVal + \" dollar \";\n" +
                                "         return reducedVal;\n" +
                                "         }");

        MapReduceResults<OrderTotal> mapReduceResults = mongoTemplate.mapReduce(query, "orders", mapFunction, reduceFunction, /*options,*/ OrderTotal.class);

        for (OrderTotal orderTotal:mapReduceResults) {
            System.out.println("orderTotal====="+orderTotal.toString());
        }
        System.out.println("**********end*************");
    }

}
