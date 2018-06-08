package com.neo.repository;

import com.neo.model.Account;
import com.neo.result.AccountData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testSaveAccount() throws Exception {
        Account account1=new Account("Aerg123","Y",14l,new Date());
        Account account2=new Account("A1r23","N",1090l,new Date());
        Account account3=new Account("B2r12","Y",500l,new Date());
        Account account4=new Account("A1d23","N",140l,new Date());
        Account account5=new Account("A1gd23","Y",100l,new Date());
        Account account6=new Account("A12d3","Y",600l,new Date());
        Account account7=new Account("Ae1g23","N",130l,new Date());
        Account account8=new Account("Arg123","Y",14500l,new Date());
        Account account9=new Account("A1r23","Y",100l,new Date());
        Account account10=new Account("A12t3","N",1080l,new Date());

        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);
        accountRepository.save(account4);
        accountRepository.save(account5);
        accountRepository.save(account6);
        accountRepository.save(account7);
        accountRepository.save(account8);
        accountRepository.save(account9);
        accountRepository.save(account10);
    }


}
