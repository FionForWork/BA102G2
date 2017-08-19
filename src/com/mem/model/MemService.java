package com.mem.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.com.model.ComVO;




public class MemService {
	private MemDAO_Interface dao;
	
	public MemService() {
		dao = new MemDAO();

	}
	
	public  MemVO oldPwd(String mem_no){
		return dao.oldPwd(mem_no);
	}
	
	public MemVO updatePic(String mem_no,byte[] picture){
		MemVO memVO = new MemVO();
		memVO.setMem_no(mem_no);
		memVO.setPicture(picture);
		dao.updatePic(memVO);
		
		return memVO;
	}
	
	public MemVO updatePwd(String mem_no,String pwd){
		MemVO memVO = new MemVO();
		memVO.setMem_no(mem_no);
		memVO.setPwd(pwd);
		dao.updatePwd(memVO);
		
		return memVO;
	}
	
	public MemVO updateStatus(String mem_no,String status){
		MemVO memVO = new MemVO();
		memVO.setMem_no(mem_no);
		memVO.setStatus(status);
		dao.updateStatus(memVO);
		
		return memVO;
	}
	
	public MemVO  addMem(String id,String pwd,String name,String sex,Date bday,String phone,
			String email,String account,byte[] picture){
		
		MemVO memVO = new MemVO();
		
		memVO.setId(id);
		memVO.setPwd(pwd);
		memVO.setName(name);
		memVO.setSex(sex);
		memVO.setBday(bday);
		memVO.setPhone(phone);
		memVO.setEmail(email);
		memVO.setAccount(account);
		memVO.setPicture(picture);

		dao.insert(memVO);
		return memVO;
		
	}                                                     
	
	
	public MemVO updateMem(String mem_no,String id,String name,String sex,Date bday,String phone,
			String email,String account,byte[] picture,Integer report,String status){
		
		MemVO memVO = new MemVO();
		memVO.setMem_no(mem_no);
		memVO.setId(id);
	
		memVO.setName(name);
		memVO.setSex(sex);
		memVO.setBday(bday);
		memVO.setPhone(phone);
		memVO.setEmail(email);
		memVO.setAccount(account);
		memVO.setPicture(picture);
		memVO.setReport(report);
		memVO.setStatus(status);
		dao.update(memVO);
		
		return memVO;
	}
	
	
	
	
	public void deleteMem(String mem_no) {
		dao.delete(mem_no);
	}

	public MemVO getOneMemById(String id) {
		return dao.findById(id);
	}
	
	public MemVO getOneMem(String mem_no) {
		return dao.findByPrimaryKey(mem_no);
	}

public List<MemVO> loginpwd(){
		
		return dao.loginpwd();
	}
	
	public List<MemVO> loginid(){
		
		return dao.loginid();
	}
	
	public List<MemVO> getAll() {
		return dao.getAll();
	}
	public Set<MemVO> getMemsByReport(Integer report) {
		return dao.getMemsByReport(report);
	}
	
}
