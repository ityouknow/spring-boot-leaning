package com.neo.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAssert {

    @Autowired
    private UserService userService;

    @Test
    public void TestAssert(){
        //验证结果是否为空
        Assert.assertNotNull(userService.getUser());
        //验证结果是否相等
        Assert.assertEquals("i am neo!", userService.getUser());
        //验证条件是否成立
        Assert.assertFalse(1+1>3);
        //验证对象是否相等
        Assert.assertSame(userService,userService);
        int status=404;
        //验证结果集，提示
        Assert.assertFalse("错误，正确的返回值为200", status != 200);
        String[] expectedOutput = {"apple", "mango", "grape"};
        String[] methodOutput = {"apple", "mango", "grape1"};
        //验证数组是否相同
        Assert.assertArrayEquals(expectedOutput, methodOutput);
    }

}
