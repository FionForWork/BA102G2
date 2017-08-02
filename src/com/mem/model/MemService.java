package com.mem.model;

import java.sql.Date;
import java.util.List;



public class MemService {
	private MemDAO_Interface dao;
	
	public MemService() {
		dao = new MemDAO();

	}
	
	public MemVO  addMem(String id,String pwd,String name,String sex,Date bday,String phone,
			String email,String account,byte[] img){
		
		MemVO memVO = new MemVO();
		
		memVO.setId(id);
		memVO.setPwd(pwd);
		memVO.setName(name);
		memVO.setSex(sex);
		memVO.setBday(bday);
		memVO.setPhone(phone);
		memVO.setEmail(email);
		memVO.setAccount(account);
		memVO.setImg(img);

		dao.insert(memVO);
		return memVO;
		
	}                                                     
	
	
	public MemVO updateMem(String mem_no,String id,String pwd,String name,String sex,Date bday,String phone,
			String email,String account,byte[] img){
		
		MemVO memVO = new MemVO();
		memVO.setMem_no(mem_no);
		memVO.setId(id);
		memVO.setPwd(pwd);
		memVO.setName(name);
		memVO.setSex(sex);
		memVO.setBday(bday);
		memVO.setPhone(phone);
		memVO.setEmail(email);
		memVO.setAccount(account);
		memVO.setImg(img);

		dao.update(memVO);
		return memVO;
	}
	public void deleteMem(String mem_no) {
		dao.delete(mem_no);
	}

	public MemVO getOneMem(String mem_no) {
		return dao.findByPrimaryKey(mem_no);
	}

	public List<MemVO> getAll() {
		return dao.getAll();
	}
	
	
}
