package com.stt.iotest;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.stt.iotest.model.C2571001ContentOfflineNotice;
import com.stt.iotest.model.Email;

@SpringBootApplication
@EnableJms
public class IotestApplication {


	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(IotestApplication.class, args);
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		
		System.out.println("sending an email message.");
		//发送一个消息到指定的序列“mailbox”
		jmsTemplate.convertAndSend("mailbox","mail test");
		
		jmsTemplate.convertAndSend("C2571001ContentOfflineNoticeQueue","my test");
	}
}
