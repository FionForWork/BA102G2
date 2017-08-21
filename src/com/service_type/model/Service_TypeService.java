package com.service_type.model;

import java.util.List;

public class Service_TypeService {
	
	private Service_TypeDAO_Interface dao;
	
	public Service_TypeService(){
		dao = new Service_TypeDAO();
	}
	
	public Service_TypeVO addServiceType(String stype_no, String name){
		
		Service_TypeVO service_typeVO = new Service_TypeVO();
		
		service_typeVO.setStype_no(stype_no);
		service_typeVO.setName(name);
		dao.insert(service_typeVO);
		
		return service_typeVO;
	}
	
	public Service_TypeVO updateServiceType(String stype_no, String name){
		
		Service_TypeVO service_typeVO = new Service_TypeVO();
		
		service_typeVO.setStype_no(stype_no);
		service_typeVO.setName(name);
		dao.update(service_typeVO);
		
		return service_typeVO;
	}
	
	public void deleteServiceType(String stype_no){
		dao.delete(stype_no);
	}
	
	public Service_TypeVO getOne(String stype_no){
		return dao.findByPK(stype_no);
	}
	
	public List<Service_TypeVO> getAllServiceType(){
		return dao.getAll();
	}
	
	
	
}
