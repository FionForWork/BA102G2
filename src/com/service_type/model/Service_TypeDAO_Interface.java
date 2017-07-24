package com.service_type.model;

import java.util.List;

public interface Service_TypeDAO_Interface {
	
	void insert(Service_TypeVO service_typeVO);
	void update(Service_TypeVO service_typeVO);
	void delete(String stype_no);
	Service_TypeVO findByPK(String stype_no);
	List<Service_TypeVO> getAll();
}
