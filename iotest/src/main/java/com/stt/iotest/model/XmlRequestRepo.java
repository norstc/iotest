package com.stt.iotest.model;

import org.springframework.data.repository.CrudRepository;

public interface XmlRequestRepo extends CrudRepository<XmlRequest,Integer> {
	XmlRequest findById(Integer id);
}
