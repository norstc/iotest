package com.stt.iotest.model;

import org.springframework.data.repository.CrudRepository;

public interface JsonRequestRepo extends CrudRepository<JsonRequest,Integer>{
	JsonRequest findById(Integer id);

}
