package com.eparam.model;

import java.sql.Timestamp;

public class EparamVO implements java.io.Serializable {
	private String eparam_no;
	private String name;
	private String eparam_desc;
	private Double value;
	private Timestamp eparam_date;

	public EparamVO() {
	}

	public String getEparam_no() {
		return eparam_no;
	}

	public void setEparam_no(String eparam_no) {
		this.eparam_no = eparam_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEparam_desc() {
		return eparam_desc;
	}

	public void setEparam_desc(String eparam_desc) {
		this.eparam_desc = eparam_desc;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Timestamp getEparam_date() {
		return eparam_date;
	}

	public void setEparam_date(Timestamp eparam_date) {
		this.eparam_date = eparam_date;
	}
}
