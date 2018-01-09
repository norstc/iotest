package com.stt.iotest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stt.iotest.model.JsonRequest;
import com.stt.iotest.model.XmlRequest;

@Configuration
public class CommonBeanConfig {
	@Bean
	public XmlRequest xmlRequest(){
		XmlRequest xmlRequest = new XmlRequest();
		return xmlRequest;
	}
	
	@Bean
	public JsonRequest jsonRequest(){
		JsonRequest jsonRequest = new JsonRequest();
		return jsonRequest;
	}
}
