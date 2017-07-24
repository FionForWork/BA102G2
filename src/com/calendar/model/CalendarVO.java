package com.calendar.model;

import java.sql.Timestamp;

public class CalendarVO implements java.io.Serializable {
	
	private String cal_no;
	private String com_no;
	private String content;
	private Timestamp start_time;
	private Timestamp end_time;
	
	public CalendarVO(){
		
	}
	
	public CalendarVO(String cal_no, String com_no, String content, Timestamp start_time, Timestamp end_time) {
		this.cal_no = cal_no;
		this.com_no = com_no;
		this.content = content;
		this.start_time = start_time;
		this.end_time = end_time;
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
	public Timestamp getStart_time() {
		return start_time;
	}
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	public Timestamp getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	
	
}
