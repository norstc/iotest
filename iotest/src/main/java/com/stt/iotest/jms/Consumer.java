package com.stt.iotest.jms;


import org.apache.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.stt.iotest.model.C2571001ContentOfflineNotice;
import com.stt.iotest.model.Email;
import com.stt.iotest.util.DesCrypt;

@Component
public class Consumer {
	private static final Logger log = Logger.getLogger(Consumer.class);
	
	@JmsListener(destination = "mailbox")
	public void receiveQueue(String content) {
		//这里会处理消息队列中收到的消息
		log.info("Mailbox Consumer收到的报文为：" + content);
	}

	@JmsListener(destination = "C2571001ContentOfflineNoticeQueue")
	public void receiveC2571001ContentOfflineNoticeQueue(String content) {
		//处理C2571001ContentOfflineNotice
		log.info("C2571001ContentOfflineNotice: " + content);
		//解密
		String contentDecoded = DesCrypt.getInstance().decrypt(content);
		log.info("after decode: 中文： " + contentDecoded);
		
	}
	
	@JmsListener(destination = "C2571001ContentCloseNoticeQueue")
	public void receiveC2571001ContentCloseNoticeQueue(String content) {
		log.info(" 收到C2571001ContentCloseNoticeQueue: " + content);
		
		//解密
		log.info("after decode: 中文： " + DesCrypt.getInstance().decrypt(content));
	}
}
