package com.com.model;

import java.util.*;


public interface ComDAO_Interface {
	public void insert(ComVO comVO);
    public void update(ComVO comVO);
    public void delete(String com_no);
    public ComVO findByPrimaryKey(String com_no);
    public List<ComVO> getAll();
    public List<ComVO> getAll(Map<String, String[]> map);
}
