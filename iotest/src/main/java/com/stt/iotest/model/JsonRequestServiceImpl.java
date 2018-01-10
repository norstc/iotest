package com.stt.iotest.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonRequestServiceImpl implements JsonRequestService{
	private JsonRequestRepo jsonRequestRepo;
	private Logger log = LoggerFactory.getLogger(JsonRequestServiceImpl.class);
	
	@Autowired
	public void setJsonRequestRepo(JsonRequestRepo jsonRequestRepo){
		this.jsonRequestRepo = jsonRequestRepo;
	}

	@Override
	public List<JsonRequest> listAll() {
		List<JsonRequest> jsonLists = new ArrayList<>();
		jsonRequestRepo.findAll().forEach(jsonLists::add);
		return jsonLists;
	}

	@Override
	public JsonRequest getById(Integer id) {
		
		return jsonRequestRepo.findOne(id);
	}

	@Override
	public JsonRequest saveOrUpdate(JsonRequest jsonRequest) {
		
		return jsonRequestRepo.save(jsonRequest);
	}

	@Override
	public void delete(Integer id) {
		jsonRequestRepo.delete(id);
		
	}

}
