package com.eparam.model;

import java.util.List;

public interface EparamDAO_Interface {
	public void insert(EparamVO eparamVO);
	public void update(EparamVO eparamVO);
	public void delete(String eparam_no);
	public EparamVO findByPrimaryKey(String eparam_no);
	public List<EparamVO> getAll();
}
