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

	@Rule
    public OutputCapture outputCapture = new OutputCapture();

	@Autowired
	private Producer producer;

	@Test
	public void sendSimpleQueueMessage() throws InterruptedException {
		this.producer.sendQueue("Test queue message");
		Thread.sleep(1000L);
		assertThat(this.outputCapture.toString().contains("Test queue")).isTrue();
	}

	@Test
	public void send100QueueMessage() throws InterruptedException {
		for (int i=0;i<100;i++){
			this.producer.sendQueue("Test queue message"+i);
		}
		Thread.sleep(1000L);
	}

}