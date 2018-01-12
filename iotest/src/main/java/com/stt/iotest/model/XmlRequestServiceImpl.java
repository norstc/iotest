package com.stt.iotest.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XmlRequestServiceImpl implements XmlRequestService {
	private XmlRequestRepo xmlRequestRepo;
	private Logger log = LoggerFactory.getLogger(XmlRequestServiceImpl.class);
	
	@Autowired
	public void setXmlRequestRepo(XmlRequestRepo xmlRequestRepo){
		this.xmlRequestRepo = xmlRequestRepo;
	}
	@Override
	public List<XmlRequest> listAll() {
		List<XmlRequest> xmlLists = new ArrayList<>();
		xmlRequestRepo.findAll().forEach(xmlLists::add);
		
		return null;
	}

	@Override
	public XmlRequest getById(Integer id) {
		
		return xmlRequestRepo.findOne(id);
	}

	@Override
	public XmlRequest saveOrUpdate(XmlRequest xmlRequest) {
		
		return xmlRequestRepo.save(xmlRequest);
	}

	@Override
	public void delete(Integer id) {
		xmlRequestRepo.delete(id);
		
	}

}
