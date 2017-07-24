package com.art_type.model;

import java.io.Serializable;

public class Art_TypeVO implements Serializable {
	private Integer art_type_no;
	private String type;
	
	
	
	public Integer getArt_type_no() {
		return art_type_no;
	}
	public void setArt_type_no(Integer art_type_no) {
		this.art_type_no = art_type_no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
	
}
