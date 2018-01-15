package com.stt.iotest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="xml_request")
public class XmlRequest  extends NamedEntity{
	@Column(name="xml_request_url", length=2048)
	private String xmlRequestUrl;
	
	@Column(name = "xml_header", length=2048)
	@NotEmpty
	private String xmlHeader;
	
	@Column(name = "xml_body", length=2048)
	@NotEmpty
	private String xmlBody;
	
	@Lob
	@Column(name = "xml_result")
	private String xmlResult;
	
	
	public String getXmlRequestUrl() {
		return xmlRequestUrl;
	}

	public void setXmlRequestUrl(String xmlRequestUrl) {
		this.xmlRequestUrl = xmlRequestUrl;
	}

	public String getXmlResult() {
		return xmlResult;
	}

	public void setXmlResult(String xmlResult) {
		this.xmlResult = xmlResult;
	}

	public String getXmlHeader() {
		return xmlHeader;
	}

	public void setXmlHeader(String xmlHeader) {
		this.xmlHeader = xmlHeader;
	}

	public String getXmlBody() {
		return xmlBody;
	}

	public void setXmlBody(String xmlBody) {
		this.xmlBody = xmlBody;
	}
	
	
	

}
