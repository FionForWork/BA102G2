package com.aut.model;

import java.util.List;
import java.util.Map;

import com.aut.model.AutDAO_Interface;
import com.com.model.ComVO;

public class AutService {

	private AutDAO_Interface dao;
	
	public AutService(){
		dao = new AutDAO();
		
	}
	public  AutVO getOneAut(String adm_no){
		return dao.findByPrimaryKey(adm_no);
	}
	public AutVO addAut(String adm_no,String id){
		AutVO autVO=new AutVO();
		autVO.setAdm_no(adm_no);
		autVO.setId(id);
		dao.insert(autVO);
		return autVO;
	}
	
	//預留給 Struts 2 用的
	public void addAut(AutVO autVO){
		dao.insert(autVO);
	}
	
	public void deleteAut(String adm_no,String id){
		dao.delete(adm_no,id);
	}
	public List<AutVO> getAll(){
		return dao.getAll();
	}
	public Map<String, List> getOneAll(String adm_no){
		
		return dao.getOneAll(adm_no);
	}
}
