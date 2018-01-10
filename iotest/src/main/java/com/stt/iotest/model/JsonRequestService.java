package com.stt.iotest.model;

import java.util.List;

public interface JsonRequestService {
	
	List<JsonRequest> listAll();
	JsonRequest getById(Integer id);
	JsonRequest saveOrUpdate(JsonRequest jsonRequest);
	void delete(Integer id);

}
