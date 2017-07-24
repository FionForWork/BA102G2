package com.rfq_detail.model;

import java.sql.Timestamp;

public class RFQ_DetailVO implements java.io.Serializable {
	
	private String rfqdetail_no;
	private String rfq_no;
	private String stype_no;
	private String location;
	private Timestamp ser_date;
	private String title;
	private String content;
	private String status;
	
	public RFQ_DetailVO() {
	}
	
	public String getRfqdetail_no() {
		return rfqdetail_no;
	}
	public void setRfqdetail_no(String rfqdetail_no) {
		this.rfqdetail_no = rfqdetail_no;
	}
	public String getRfq_no() {
		return rfq_no;
	}
	public void setRfq_no(String rfq_no) {
		this.rfq_no = rfq_no;
	}
	public String getStype_no() {
		return stype_no;
	}
	public void setStype_no(String stype_no) {
		this.stype_no = stype_no;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Timestamp getSer_date() {
		return ser_date;
	}
	public void setSer_date(Timestamp ser_date) {
		this.ser_date = ser_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
