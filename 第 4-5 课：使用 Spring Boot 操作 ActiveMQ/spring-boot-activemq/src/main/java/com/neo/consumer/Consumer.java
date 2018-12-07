package com.neo.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@JmsListener(destination = "neo.queue", containerFactory = "queueListenerFactory")
    public void receiveQueue(String text) {
		System.out.println("Consumer queue msg : "+text);
	}

	@JmsListener(destination = "neo.topic", containerFactory = "topicListenerFactory")
	public void receiveTopic(String text) {
		System.out.println("Consumer topic msg : "+text);
	}

}