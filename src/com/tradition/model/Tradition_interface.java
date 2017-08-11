package com.tradition.model;

import java.util.List;



public interface Tradition_interface {
	public void insert(TraditionVO traditionVO);
    public void update(TraditionVO traditionVO);
    public void delete(Integer tra_no);
    public TraditionVO findByPrimaryKey(Integer tra_no);
    public List<TraditionVO> getAll();
   
	public List<TraditionVO> getOneAll(Integer tra_type_no);
	
}
