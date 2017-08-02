package com.tradition_type.model;

import java.io.Serializable;

public class Tradition_TypeVO implements Serializable{

	private Integer tra_type_no;
	private String type;
	
	
	public Integer getTra_type_no() {
		return tra_type_no;
	}
	public void setTra_type_no(Integer tra_type_no) {
		this.tra_type_no = tra_type_no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
