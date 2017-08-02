package com.tradition_type.model;

import java.util.List;



public interface Tradition_Type_interface {
	 public void insert(Tradition_TypeVO tradition_TypeVO);
     public void update(Tradition_TypeVO tradition_TypeVO);
     public void delete(Integer tra_type_no);
     public Tradition_TypeVO findByPrimaryKey(Integer tra_type_no);
     public List<Tradition_TypeVO> getAll();
}
