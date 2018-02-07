package com.stt.iotest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="jms_request")
public class JmsRequest extends NamedEntity{
	@Column(name="jms_request_url",length=2048)
	private String jmsRequestUrl;
	
	@Column(name="jms_user",length=1024)
	private String jmsUser;
	
	@Column(name="jms_password",length=1024)
	private String jmsPassword;
	
	@Column(name="jms_request_data", length=2048)
	private String jmsRequestData;
	
	@Column(name="jms_response")
	private String jmsResponse;

	public String getJmsRequestUrl() {
		return jmsRequestUrl;
	}

	public void setJmsRequestUrl(String jmsRequestUrl) {
		this.jmsRequestUrl = jmsRequestUrl;
	}

	public String getJmsUser() {
		return jmsUser;
	}

	public void setJmsUser(String jmsUser) {
		this.jmsUser = jmsUser;
	}

	public String getJmsPassword() {
		return jmsPassword;
	}

	public void setJmsPassword(String jmsPassword) {
		this.jmsPassword = jmsPassword;
	}

	public String getJmsRequestData() {
		return jmsRequestData;
	}

	public void setJmsRequestData(String jmsRequestData) {
		this.jmsRequestData = jmsRequestData;
	}

	public String getJmsResponse() {
		return jmsResponse;
	}

	public void setJmsResponse(String jmsResponse) {
		this.jmsResponse = jmsResponse;
	}
	
	
	
	

}
