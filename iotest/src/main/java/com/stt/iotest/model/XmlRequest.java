package com.stt.iotest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="xml_request")
public class XmlRequest  extends NamedEntity{
	@Column(name = "xml_header")
	@NotEmpty
	private String xmlHeader;
	
	@Column(name = "xml_body")
	@NotEmpty
	private String xmlBody;

	@Column(name = "xml_result")
	private String xmlResult;
	
	
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
