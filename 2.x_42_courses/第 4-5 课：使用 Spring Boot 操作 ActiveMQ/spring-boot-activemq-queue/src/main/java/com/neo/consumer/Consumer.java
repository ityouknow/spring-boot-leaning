package com.neo.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@JmsListener(destination = "neo.queue")
    public void receiveQueue(String text) {
		System.out.println("Consumer queue msg : "+text);
	}
}