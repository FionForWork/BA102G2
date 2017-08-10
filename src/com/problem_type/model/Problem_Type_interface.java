package com.problem_type.model;

import java.util.List;



public interface Problem_Type_interface {

	 public void insert(Problem_TypeVO problem_TypeVO);
     public void update(Problem_TypeVO problem_TypeVO);
     public void delete(Integer problem_type_no);
     public Problem_TypeVO findByPrimaryKey(Integer problem_type_no);
     public List<Problem_TypeVO> getAll();
}
