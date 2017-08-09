package com.comtra.model;

import java.sql.Timestamp;
import java.util.List;

public class ComTraService {

	private ComTraDAO_Interface dao;
	
	public ComTraService(){
		dao = new ComTraDAO();
	}
	
	public ComTraVO addComTra(String com_no, String mem_no,Timestamp tracking_date){
		ComTraVO comTra = new ComTraVO();
		comTra.setCom_no(com_no);
		comTra.setMem_no(mem_no);
		comTra.setTracking_date(tracking_date);
		String comtra_no = dao.insertComTra(comTra);
		comTra.setComtra_no(comtra_no);
		return comTra;
	}
	
	public ComTraVO updateComTra(String comtra_no, String com_no, String mem_no,Timestamp tracking_date){
		ComTraVO comTra = new ComTraVO();
		comTra.setComtra_no(comtra_no);
		comTra.setCom_no(com_no);
		comTra.setMem_no(mem_no);
		comTra.setTracking_date(tracking_date);
		dao.updateComTra(comTra);
		return comTra;
	}
	
	public void deleteComTra(String comtra_no){
		dao.deleteComTra(comtra_no);
		
	}
	
	public ComTraVO getOneComTra(String comtra_no){
		return dao.findComTraByPK(comtra_no);
	}
	
	public List<ComTraVO> getComTraByMemNo(String mem_no){
		return dao.findComTraByMemNo(mem_no);
	}
	
	public List<ComTraVO> getAll(){
		return dao.findAll();
	}
	public List<String> getComNoListByMemNo(String mem_no){
		return dao.getComNoListByMemNo(mem_no);
	}
	public ComTraVO getComTraByComNoAndMemNo(String com_no,String mem_no){
		return dao.getComTraByComNoAndMemNo(com_no, mem_no);
	}
}
