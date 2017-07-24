package com.service_type.model;

public class Service_TypeVO implements java.io.Serializable {
	private String stype_no;
	private String name;
	
	public Service_TypeVO() {

	}
	
	public String getStype_no() {
		return stype_no;
	}
	public void setStype_no(String stype_no) {
		this.stype_no = stype_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
