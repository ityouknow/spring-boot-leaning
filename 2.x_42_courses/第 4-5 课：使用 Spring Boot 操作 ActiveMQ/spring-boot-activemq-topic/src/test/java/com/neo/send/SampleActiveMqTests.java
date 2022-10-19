package com.neo.send;

import com.neo.producer.Producer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleActiveMqTests {

	@Autowired
	private Producer producer;

	@Test
	public void sendSimpleTopicMessage() throws InterruptedException {
		this.producer.sendTopic("Test Topic message");
		Thread.sleep(1000L);
	}
}