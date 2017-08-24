package com.serv.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mem.model.MemVO;

public class ServService {
	private ServDAO_Interface dao;
	
	public ServService() {
		dao = new ServDAO();
	}
	
	public ServVO addServ(String stype_no,String com_no,Integer deposit,Integer price,String title,String content,String status){
		
		ServVO servVO=new ServVO();
		servVO.setStype_no(stype_no);
		servVO.setCom_no(com_no);
		servVO.setDeposit(deposit);
		servVO.setPrice(price);
		servVO.setTitle(title);
		servVO.setContent(content);
		servVO.setStatus(status);
		dao.insert(servVO);
		return servVO;
	}
	
	public ServVO updateServ(String serv_no,String stype_no,String com_no,Integer deposit,Integer price,String title,String content){
		ServVO servVO=new ServVO();
		
		servVO.setServ_no(serv_no);
		servVO.setStype_no(stype_no);
		servVO.setCom_no(com_no);
		servVO.setDeposit(deposit);
		servVO.setPrice(price);
		servVO.setTitle(title);
		servVO.setContent(content);
		dao.update(servVO);
		return servVO;
	}
	
	public ServVO updateScore(int times, double score, String serv_no){
		ServVO servVO=new ServVO();
		
		servVO.setServ_no(serv_no);
		servVO.setTimes(times);;
		servVO.setServ_no(serv_no);;
		dao.updateScore(servVO);
		return servVO;
	}
	
	public void deleteServ(String serv_no){
		dao.delete(serv_no);
		
	}
	
	public  ServVO getOneServ(String serv_no){
		
		return dao.findByPrimaryKey(serv_no);
	}
	
	public List<ServVO> getAll(){
		return dao.getAll();
		
	}
	
	public List<ServVO> getAll(Map<String, String[]> map){
		return dao.getAll(map);
		
	}
	
	public List<String> getComnoByStypeno(String stype_no){
		return dao.findByStype_no(stype_no);
		
	}
	
	public List<ServVO> getCom(String com_no){
		return dao.getCom(com_no);
		
	}
	
	public List<ServVO> getSearch(String sh){
		return dao.findBysh(sh);	
	}
	public Set<ServVO> getServByStype(String stype_no) {
		return dao.getServByStype(stype_no);
	}
	public Set<ServVO> getServByCom(String com_no) {
		return dao.getServByCom(com_no);
	}
	public List<ServVO> getAllAvg(){
		return dao.getAllAvg();
		
	}
	
	public List<String> getComStype(String com_no){
		return dao.getComStype(com_no);
	}
}