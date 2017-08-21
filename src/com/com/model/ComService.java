package com.com.model;

import java.util.*;

import com.com.model.ComDAO;
import com.com.model.ComDAO_Interface;
import com.mem.model.MemVO;


public class ComService {
	private ComDAO_Interface dao;

	public ComService() {
		dao = new ComDAO();

	}
	
	public  ComVO oldPwd(String com_no){
		return dao.oldPwd(com_no);
	}
	
	public ComVO confirmCom(String com_no){
		ComVO comVO = new ComVO();
		comVO.setCom_no(com_no);
		
		dao.confirmCom(comVO);
		
		return comVO;
	}
	
	public ComVO updatePwd(String com_no,String pwd){
		ComVO comVO = new ComVO();
		comVO.setCom_no(com_no);
		comVO.setPwd(pwd);
		dao.updatePwd(comVO);
		
		return comVO;
	}
	public ComVO updateStatus(String com_no,String status){
		ComVO comVO = new ComVO();
		comVO.setCom_no(com_no);
		comVO.setStatus(status);
		dao.updateStatus(comVO);
		
		return comVO;
	}
	public ComVO updatePic(String com_no,byte[] logo){
		ComVO comVO = new ComVO();
		comVO.setCom_no(com_no);
		comVO.setLogo(logo);
		dao.updatePic(comVO);
		
		return comVO;
	}
	
public List<ComVO> loginpwd(){
		
		return dao.loginpwd();
	}
	
	public List<ComVO> loginid(){
		
		return dao.loginid();
	}
	
	public ComVO addCom(String id,String pwd,String name,String loc,String com_desc,String phone,String account,byte[] logo,String lon,String lat)
	{
		ComVO comVO =new ComVO();
		comVO.setId(id);
		comVO.setPwd(pwd);
		comVO.setName(name);
		comVO.setLoc(loc);
		comVO.setCom_desc(com_desc);
		comVO.setPhone(phone);
		comVO.setAccount(account);
		comVO.setLogo(logo);
		comVO.setLon(lon);
		comVO.setLat(lat);
		dao.insert(comVO);
		return comVO;
	}
	//預留給 Struts 2 用的
	public void addCom(ComVO comVO){
		dao.insert(comVO);
		
	}
	
	public ComVO updateCom(String com_no,String id,String name,String loc,String lon,
			String lat,String com_desc,String phone,String account,byte[] logo,String status)
	{
		ComVO comVO=new ComVO();
		comVO.setCom_no(com_no);
		comVO.setId(id);
		
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
		
		return comVO;
	}
	//預留給 Struts 2 用的
	public void updateCom(ComVO comVO){
		dao.update(comVO);
		
	}
	
	
	public void deleteCom(String Com_no){
		dao.delete(Com_no);
		
	}
	public  ComVO getOneComById(String id){
		return dao.findById(id);
	}
	
	public  ComVO getOneCom(String com_no){
		return dao.findByPrimaryKey(com_no);
	}
	
	
	public List<ComVO> getAll(){
		return dao.getAll();

	}
	public List<ComVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);

	}	
	public List<ComVO> getLocation(String lon1, String lon2, String lat1, String lat2){
		return dao.findBylocation(lon1, lon2, lat1, lat2);

	}
}