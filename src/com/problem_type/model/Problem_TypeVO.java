package com.problem_type.model;

import java.io.Serializable;

public class Problem_TypeVO implements Serializable{

	
	private Integer problem_type_no;
	private String type;
	
	
	public Integer getProblem_type_no() {
		return problem_type_no;
	}
	public void setProblem_type_no(Integer problem_type_no) {
		this.problem_type_no = problem_type_no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
