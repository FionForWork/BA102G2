package com.art_type.model;

import java.util.List;



public interface Art_TypeDAO_interface {
	 public void insert(Art_TypeVO art_TypeVO);
     public void update(Art_TypeVO art_TypeVO);
     public void delete(Integer art_type_no);
     public Art_TypeVO findByPrimaryKey(Integer art_type_no);
     public List<Art_TypeVO> getAll();

}
