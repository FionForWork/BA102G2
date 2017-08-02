package com.mem.model;

import java.util.List;



public interface MemDAO_Interface {
	
	 public void insert(MemVO memVO);
     public void update(MemVO memVO);
     public void delete(String mem_no);
     public MemVO findByPrimaryKey(String mem_no);
     public List<MemVO> getAll();
     public void updatePic(MemVO memVO);
     public List<MemVO> loginid();
     public List<MemVO> loginpwd();
     public MemVO findById(String id);
}
