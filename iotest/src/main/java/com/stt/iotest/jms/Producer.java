package com.stt.iotest.jms;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
	@Autowired
	private JmsMessagingTemplate jmsTemplate;
	// 发送消息
	public void sendMessage(Destination destination, final String message) {
		jmsTemplate.convertAndSend(destination,message);
	}

}
