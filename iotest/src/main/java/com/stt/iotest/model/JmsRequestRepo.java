package com.stt.iotest.model;

import org.springframework.data.repository.CrudRepository;

public interface JmsRequestRepo extends CrudRepository<JmsRequest, Integer>{
	JmsRequest findById(Integer id);
	JmsRequest findFirstByName(String name);
}
