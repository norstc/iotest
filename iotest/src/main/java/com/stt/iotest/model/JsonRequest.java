package com.stt.iotest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="json_request")
public class JsonRequest  extends NamedEntity{
	@Column(name="json_request_url")
	private String jsonRequestUrl;
	
	@Column(name = "json_request_data")
	private String jsonRequestData;
	
	@Column(name = "json_response")
	private String jsonResponse;

	
	
	public String getJsonRequestUrl() {
		return jsonRequestUrl;
	}

	public void setJsonRequestUrl(String jsonRequestUrl) {
		this.jsonRequestUrl = jsonRequestUrl;
	}

	public String getJsonRequestData() {
		return jsonRequestData;
	}

	public void setJsonRequestData(String jsonRequestData) {
		this.jsonRequestData = jsonRequestData;
	}

	public String getJsonResponse() {
		return jsonResponse;
	}

	public void setJsonResponse(String jsonResponse) {
		this.jsonResponse = jsonResponse;
	}
	
	

}
