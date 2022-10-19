package com.neo.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer2 {

	@JmsListener(destination = "neo.queue")
    public void receiveQueue(String text) {
		System.out.println("Consumer2 queue msg : "+text);
	}
}