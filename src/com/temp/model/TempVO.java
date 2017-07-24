package com.temp.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class TempVO implements Serializable {
	private String temp_no;
	private String com_no;
	private String mem_no;
	private String name;
	private Timestamp create_date;
	private Integer available;
	private String status;
	
	public TempVO(){}

	public TempVO(String temp_no, String com_no, String mem_no, String name,Timestamp create_date,Integer available, String status) {
		super();
		this.temp_no = temp_no;
		this.com_no = com_no;
		this.mem_no = mem_no;
		this.name = name;
		this.create_date = create_date;
		this.available = available;
		this.status = status;
	}

	public String getTemp_no() {
		return temp_no;
	}

	public void setTemp_no(String temp_no) {
		this.temp_no = temp_no;
	}

	public String getCom_no() {
		return com_no;
	}

	public void setCom_no(String com_no) {
		this.com_no = com_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	
	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
