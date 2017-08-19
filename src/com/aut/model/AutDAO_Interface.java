package com.aut.model;

import java.util.List;

import com.adm.model.AdmVO;

public interface AutDAO_Interface {
	public void insert(AutVO autVO );
	public void delete(String adm_no,String id);
	public AutVO findByPrimaryKey(String adm_no);
	public List<AutVO> getAll();
	public List<AutVO> getOneAll();
}
