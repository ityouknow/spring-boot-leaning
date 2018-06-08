package com.neo.repository;

import com.neo.model.Orders;
import com.neo.result.OrderTotal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersAggregateTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private  MongoOperations operations;

    @Test
    public void testAggregate() {
        AggregationResults<OrderTotal> results = mongoTemplate.aggregate(newAggregation(Orders.class,
                match(where("status").is("A")),
                group("custId")
                        .sum("amount").as("total")
        ), OrderTotal.class);

       List<OrderTotal> totals= results.getMappedResults();
        System.out.println("**************begin*********");
        for (OrderTotal orderTotal:totals) {
            System.out.println("orderTotal====="+orderTotal.toString());

        }
        System.out.println("**********end*************");
    }

    @Test
    public void testAggregateMore() {
        AggregationResults<OrderTotal> results = mongoTemplate.aggregate(newAggregation(Orders.class,
                match(where("status").is("A")),
                group("custId")
                        .sum("amount").as("total"),
                sort(Direction.DESC, "total")/*,
                project("total")
                        .andExpression("total * [0]", 100).as("sumTotal")*/
        ), OrderTotal.class);

        List<OrderTotal> totals= results.getMappedResults();
        System.out.println("**************begin*********");
        for (OrderTotal orderTotal:totals) {
            System.out.println("orderTotal====="+orderTotal.toString());
        }
        System.out.println("**********end*************");
    }

}
