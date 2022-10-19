package com.neo.simple;

import com.neo.service.HelloService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.rule.OutputCapture;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {
    @Rule
    public OutputCapture outputCapture = new OutputCapture();
    @Resource
    HelloService helloService;

    @Test
    public void  sayHelloTest(){
        helloService.sayHello();
        assertThat(this.outputCapture.toString().contains("hello service")).isTrue();
    }
}
