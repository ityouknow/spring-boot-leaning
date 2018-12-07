package com.neo.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer2 {

	@JmsListener(destination = "neo.queue", containerFactory = "queueListenerFactory")
    public void receiveQueue(String text) {
		System.out.println("Consumer2 msg : "+text);
	}

	@JmsListener(destination = "neo.topic", containerFactory = "topicListenerFactory")
	public void receiveTopic(String text) {
		System.out.println("Consumer2 topic msg : "+text);
	}
}