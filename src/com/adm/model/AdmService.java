package com.adm.model;

import java.util.List;



public class AdmService {

	private AdmDAO_Interface dao;
	
	public AdmService(){
		dao = new AdmDAO();
	}
	public  AdmVO getOneAdmById(String id){
		return dao.findById(id);
	}
	public List<AdmVO> loginid(){
		
		return dao.loginid();
	}
	public AdmVO addAdm(String id,String pwd,String name,String job,String status){
		AdmVO admVO = new AdmVO();
		admVO.setId(id);
		admVO.setPwd(pwd);
		admVO.setName(name);
		admVO.setJob(job);
		admVO.setStatus(status);
		dao.insert(admVO);
		return admVO;
	}
	
	//預留給 Struts 2 用的
	public void addAmd(AdmVO admVO){
		dao.insert(admVO);
		
	}
	
	
	public AdmVO  updateAdm(String adm_no,String id,String pwd,String name,String job,String status)
	{
		AdmVO admVO = new AdmVO();
		
		admVO.setAdm_no(adm_no);
		admVO.setId(id);
		admVO.setPwd(pwd);
		admVO.setName(name);
		admVO.setJob(job);
		admVO.setStatus(status);
		dao.update(admVO);
		
		return admVO;
		
	}
	
//	//預留給 Struts 2 用的
//	public void updateAdm(AdmVO admVO){
//		dao.update(admVO);
//		
//	}
	
	
	public void deleteAdm(String adm_no) {
		dao.delete(adm_no);
	}

	public AdmVO getOneAdm(String adm_no) {
		return dao.findByPrimaryKey(adm_no);
	}

	public List<AdmVO> getAll() {
		return dao.getAll();
	}
	
	
	
	
}
