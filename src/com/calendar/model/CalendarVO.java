package com.calendar.model;

import java.sql.Timestamp;

public class CalendarVO implements java.io.Serializable {
	
	private String cal_no;
	private String com_no;
	private String content;
	private Timestamp cal_date;
	private String status;
	
	public CalendarVO(){
		
	}
	
	
	public String getCal_no() {
		return cal_no;
	}
	public void setCal_no(String cal_no) {
		this.cal_no = cal_no;
	}
	public String getCom_no() {
		return com_no;
	}
	public void setCom_no(String com_no) {
		this.com_no = com_no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCal_date() {
		return cal_date;
	}

	public void setCal_date(Timestamp cal_date) {
		this.cal_date = cal_date;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	
}
