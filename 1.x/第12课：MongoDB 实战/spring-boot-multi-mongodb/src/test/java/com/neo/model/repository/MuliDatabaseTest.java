package com.neo.model.repository;

import com.neo.entity.UserEntity;
import com.neo.model.repository.primary.PrimaryRepository;
import com.neo.model.repository.secondary.SecondaryRepository;
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
    private PrimaryRepository primaryRepository;

    @Autowired
    private SecondaryRepository secondaryRepository;

    @Test
    public void TestSave() {

        System.out.println("************************************************************");
        System.out.println("测试开始");
        System.out.println("************************************************************");

        this.primaryRepository.save(new UserEntity("小张", "123456"));

        this.secondaryRepository.save(new UserEntity("小王", "654321"));

        List<UserEntity> primaries = this.primaryRepository.findAll();
        for (UserEntity primary : primaries) {
            System.out.println(primary.toString());
        }

        List<UserEntity> secondaries = this.secondaryRepository.findAll();

        for (UserEntity secondary : secondaries) {
            System.out.println(secondary.toString());
        }

        System.out.println("************************************************************");
        System.out.println("测试完成");
        System.out.println("************************************************************");
    }

}
