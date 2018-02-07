package com.stt.iotest.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JmsRequestServiceImpl implements JmsRequestService{

	private JmsRequestRepo jmsRequestRepo;
	private Logger log = LoggerFactory.getLogger(JmsRequestServiceImpl.class);
	
	@Autowired
	public void setJmsRequestRepo(JmsRequestRepo jmsRequestRepo) {
		this.jmsRequestRepo=jmsRequestRepo;
	}
	@Override
	public List<JmsRequest> listAll() {
		List<JmsRequest> jmsLists = new ArrayList<>();
		jmsRequestRepo.findAll().forEach(jmsLists::add);
		return jmsLists;
	}

	@Override
	public JmsRequest getById(Integer id) {
		
		return jmsRequestRepo.findOne(id);
	}

	@Override
	public JmsRequest saveOrUpdate(JmsRequest jmsRequest) {
		
		return jmsRequestRepo.save(jmsRequest);
	}

	@Override
	public void delete(Integer id) {
		jmsRequestRepo.delete(id);
		
	}

	@Override
	public JmsRequest findFirstByName(String name) {
		
		return jmsRequestRepo.findFirstByName(name);
	}

}
