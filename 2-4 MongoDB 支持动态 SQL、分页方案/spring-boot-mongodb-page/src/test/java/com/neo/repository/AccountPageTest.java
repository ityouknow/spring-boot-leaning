package com.neo.repository;


import com.neo.model.Account;
import com.neo.param.AccountPageParam;
import com.neo.result.AccountData;
import com.neo.util.DBObjectUtil;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountPageTest {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testFindAccounts() throws Exception {
        Page<Account> pages=accountRepository.findAll(PageRequest.of(1, 3, Sort.by(Sort.Direction.DESC,"accountNo")));
        Page<Account> pagesx=accountRepository.findByAccountStatus("N", PageRequest.of(1, 3));
        for (Account account:pagesx){
            System.out.println("Account =="+ account.toString());
        }
    }


    @Test
    public void testDynamicPage() {
        System.out.println("**********begin*************");
        AccountPageParam param=new AccountPageParam();
        param.setAccountStatus("N");
        Document obj = DBObjectUtil.generateDBObject(param);
        Query query = new BasicQuery(obj).skip(param.getBeginLine()).limit(param.getPageSize());
        List<AccountData> list = mongoTemplate.find(query, AccountData.class);
        if (list == null || list.size() == 0) {
            System.out.println("list size ==0 !");
            return;
        }
        for (AccountData account:list){
            System.out.println("Account =="+ account.toString());
        }
        System.out.println("**********end*************");
    }

}
