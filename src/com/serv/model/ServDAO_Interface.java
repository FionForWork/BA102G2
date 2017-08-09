package com.serv.model;

import java.util.List;


public interface ServDAO_Interface {

	
	
	public void insert(ServVO servVO);

    public void update(ServVO servVO);
    public void delete(String serv_no);
    public ServVO findByPrimaryKey(String serv_no);
    public List<ServVO> getAll();
    public List<String> findByStype_no(String stype_no);
    public List<ServVO> getCom(String com_no);
	
}