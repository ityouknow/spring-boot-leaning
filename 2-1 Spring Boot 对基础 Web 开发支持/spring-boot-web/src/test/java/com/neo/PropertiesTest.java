package com.neo;

import com.neo.comm.NeoProperties;
import com.neo.comm.OtherProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesTest {
    @Value("${neo.title}")
    private String title;
    @Resource
    private NeoProperties properties;
    @Resource
    private OtherProperties otherProperties;

    @Test
    public void testSingle() {
        System.out.println("title:"+properties.getTitle());
        Assert.assertEquals(title,"纯洁的微笑");
    }

    @Test
    public void testMore() throws Exception {
        System.out.println("title:"+properties.getTitle());
        System.out.println("description:"+properties.getDescription());
    }

    @Test
    public void testOther() throws Exception {
        System.out.println("title:"+otherProperties.getTitle());
        System.out.println("blog:"+otherProperties.getBlog());
    }

}
