package com.com.model;

import java.util.List;

import com.com.model.ComDAO;
import com.com.model.ComDAO_Interface;

public class ComService {
	private ComDAO_Interface dao;

	public ComService() {
		dao = new ComDAO();

	}
	public ComVO addCom(String id,String pwd,String name,String loc,String lon,
			String lat,String com_desc,String phone,String account,byte[] logo,String status)
	{
		ComVO comVO =new ComVO();
		comVO.setId(id);
		comVO.setPwd(pwd);
		comVO.setName(name);
		comVO.setLoc(loc);
		comVO.setLon(lon);
		comVO.setLat(lat);
		comVO.setCom_desc(com_desc);
		comVO.setPhone(phone);
		comVO.setAccount(account);
		comVO.setLogo(logo);
		comVO.setStatus(status);
		dao.insert(comVO);
		return comVO;
	}
	//預留給 Struts 2 用的
	public void addCom(ComVO comVO){
		dao.insert(comVO);
		
	}
	
	public ComVO updateCom(String com_no,String id,String pwd,String name,String loc,String lon,
			String lat,String com_desc,String phone,String account,byte[] logo,String status)
	{
		ComVO comVO=new ComVO();
		comVO.setId(id);
		comVO.setPwd(pwd);
		comVO.setName(name);
		comVO.setLoc(loc);
		comVO.setLon(lon);
		comVO.setLat(lat);
		comVO.setCom_desc(com_desc);
		comVO.setPhone(phone);
		comVO.setAccount(account);
		comVO.setLogo(logo);
		comVO.setStatus(status);
		
		dao.update(comVO);
		
		return dao.findByPrimaryKey(com_no);
	}
	//預留給 Struts 2 用的
	public void updateCom(ComVO comVO){
		dao.update(comVO);
		
	}
	
	
	public void deleteCom(String Com_no){
		dao.delete(Com_no);
		
	}
	
	
	public  ComVO getOneCom(String com_no){
		return dao.findByPrimaryKey(com_no);
	}
	
	public List<ComVO> getAll(){
		return dao.getAll();
	}	
}