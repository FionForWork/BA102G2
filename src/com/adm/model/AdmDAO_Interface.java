package com.adm.model;

import java.util.List;


public interface AdmDAO_Interface {
	public void insert(AdmVO admVO);
	public void update(AdmVO admVO);
	public void delete(String adm_no);
	public AdmVO findByPrimaryKey(String adm_no);
	public List<AdmVO> getAll();
}
