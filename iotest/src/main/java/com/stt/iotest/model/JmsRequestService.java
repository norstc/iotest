package com.stt.iotest.model;

import java.util.List;

public interface JmsRequestService {
	List<JmsRequest> listAll();
	JmsRequest getById(Integer id);
	JmsRequest saveOrUpdate(JmsRequest jmsRequest);
	void delete(Integer id);
	JmsRequest findFirstByName(String name);
}
