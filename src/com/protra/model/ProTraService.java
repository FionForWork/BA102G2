package com.protra.model;

import java.util.List;

public class ProTraService {

	private ProTraDAO_Interface dao;
	
	public ProTraService(){
		dao = new ProTraDAO();
	}
	
	public ProTraVO addProTra(String pro_no, String mem_no){
		ProTraVO proTra = new ProTraVO();
		proTra.setPro_no(pro_no);
		proTra.setMem_no(mem_no);
		String protra_no = dao.insertProTra(proTra);
		proTra.setProtra_no(protra_no);
		return proTra;
	}
	
	public ProTraVO updateProTra(String protra_no, String pro_no, String mem_no){
		ProTraVO proTra = new ProTraVO();
		proTra.setProtra_no(protra_no);
		proTra.setPro_no(pro_no);
		proTra.setMem_no(mem_no);
		dao.updateProTra(proTra);
		return proTra;
	}
	
	public void deleteProTra(String protra_no){
		dao.deleteProTra(protra_no);
	}
	
	public ProTraVO getOneProTra(String protra_no){
		return dao.findProTraByPK(protra_no);
	}
	
	public List<ProTraVO> getAllByMemNo(String mem_no){
		return dao.findProTraByMemNo(mem_no);
	}
	
	public List<ProTraVO> getAll(){
		return dao.findAll();
	}
}
