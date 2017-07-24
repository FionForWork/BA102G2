package com.res_detail.model;

import java.util.List;

public class Res_DetailService {
	
	private Res_DetailDAO_Interface dao;
	
	public Res_DetailService(){
		dao = new Res_DetailDAO();
	}
	
	public Res_DetailVO addResDetail(String rd_no, String res_no, String serv_no, String stype_no,Integer price,
									String status, Integer score){
		
		Res_DetailVO vo = new Res_DetailVO();
		vo.setRd_no(rd_no);
		vo.setRes_no(res_no);
		vo.setServ_no(serv_no);
		vo.setStype_no(stype_no);
		vo.setPrice(price);
		vo.setStatus(status);
		vo.setScore(score);
		dao.insert(vo);
		
		return vo;
	}
	
	public Res_DetailVO updateResDetailStatus(String status, String rd_no){
		
		Res_DetailVO vo = new Res_DetailVO();
		vo.setStatus(status);
		vo.setRd_no(rd_no);
		dao.updateStatus(vo);
		
		return vo;
	}
	
	public void deleteResDetailStatus(String rd_no){
		dao.delete(rd_no);
	}
	
	public Res_DetailVO getOneResDetail(String rd_no){
		
		return dao.findByPK(rd_no);
	}
	
	public List<Res_DetailVO> getAllResDetail(){
		return dao.getAll();
	}
}
