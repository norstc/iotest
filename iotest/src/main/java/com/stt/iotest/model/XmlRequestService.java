package com.stt.iotest.model;

import java.util.List;

public interface XmlRequestService {
	List<XmlRequest> listAll();
	XmlRequest getById(Integer id);
	XmlRequest saveOrUpdate(XmlRequest xmlRequest);
	void delete(Integer id);

}
