package com.comtra.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ComTraVO implements Serializable {

	private String comtra_no;
	private String com_no;
	private String mem_no;
	private Timestamp tracking_date;
	
	public ComTraVO() {
		super();
	}

	public ComTraVO(String comtra_no, String com_no, String mem_no,Timestamp tracking_date) {
		super();
		this.comtra_no = comtra_no;
		this.com_no = com_no;
		this.mem_no = mem_no;
		this.tracking_date = tracking_date;
	}

	public String getComtra_no() {
		return comtra_no;
	}

	public void setComtra_no(String comtra_no) {
		this.comtra_no = comtra_no;
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

	public Timestamp getTracking_date() {
		return tracking_date;
	}

	public void setTracking_date(Timestamp tracking_date) {
		this.tracking_date = tracking_date;
	}
	
	
}
