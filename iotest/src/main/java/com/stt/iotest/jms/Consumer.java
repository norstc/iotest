package com.stt.iotest.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.stt.iotest.model.C2571001ContentOfflineNotice;
import com.stt.iotest.model.Email;

@Component
public class Consumer {
	@JmsListener(destination = "mailbox")
	public void receiveQueue(String content) {
		//这里会处理消息队列中收到的消息
		System.out.println("Consumer收到的报文为：" + content);
	}

	@JmsListener(destination = "C2571001ContentOfflineNoticeQueue")
	public void receiveC2571001ContentOfflineNoticeQueue(String content) {
		//处理C2571001ContentOfflineNotice
		
		System.out.println("C2571001ContentOfflineNotice: " + content);
	}
}
