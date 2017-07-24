package com.protra.model;

import java.util.List;

public interface ProTraDAO_Interface {

	public String insertProTra(ProTraVO proTra);
	public void deleteProTra(String protra_no);
	public void updateProTra(ProTraVO proTra);
	public ProTraVO findProTraByPK(String protra_no);
	public List<ProTraVO> findProTraByMemNo(String mem_no);
	public List<ProTraVO> findAll();
}
