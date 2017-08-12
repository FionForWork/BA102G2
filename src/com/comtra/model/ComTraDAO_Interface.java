package com.comtra.model;

import java.util.List;

public interface ComTraDAO_Interface {

	public String insertComTra(ComTraVO comTra);
	public void deleteComTra(String comtra_no);
	public void updateComTra(ComTraVO comTra);
	public ComTraVO findComTraByPK(String comtra_no);
	public List<ComTraVO> findComTraByMemNo(String mem_no);
	public List<ComTraVO> findAll();
	public List<String> getComNoListByMemNo(String mem_no);
	public ComTraVO getComTraByComNoAndMemNo(String com_no,String mem_no);
}
