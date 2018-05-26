package com.neo.repository;

import com.neo.model.mongodb.Address;
import com.neo.model.mysql.Customer;
import com.neo.repository.mongodb.AddressRepository;
import com.neo.repository.mysql.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MuliDatabaseTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void TestSave() {

        System.out.println("************************************************************");
        System.out.println("测试开始");
        System.out.println("************************************************************");

        this.customerRepository.save(new Customer("小张", "123456"));
        this.addressRepository.save(new Address("陕西", "西安"));

        List<Customer> mysqlUsers = this.customerRepository.findAll();
        for (Customer user : mysqlUsers) {
            System.out.println(user.toString());
        }

        List<Address> mongoDBUsers = this.addressRepository.findAll();
        for (Address user : mongoDBUsers) {
            System.out.println(user.toString());
        }

        System.out.println("************************************************************");
        System.out.println("测试完成");
        System.out.println("************************************************************");
    }

}
