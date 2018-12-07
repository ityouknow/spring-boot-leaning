package com.neo.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class Producer{

	@Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired
	private Queue queue;

	public void sendQueue(String msg) {
//		System.out.println("send queue msg :"+msg);
		this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
	}
}
