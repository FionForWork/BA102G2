package com.quote.model;

import java.sql.Timestamp;

public class QuoteVO {
	private String quo_no;
	private String com_no;
	private String rfqdetail_no;
	private Integer price;
	private String content;
	private Timestamp quo_date;
	
	public QuoteVO() {
	}
	
	public String getQuo_no() {
		return quo_no;
	}
	public void setQuo_no(String quo_no) {
		this.quo_no = quo_no;
	}
	public String getCom_no() {
		return com_no;
	}
	public void setCom_no(String com_no) {
		this.com_no = com_no;
	}
	public String getRfqdetail_no() {
		return rfqdetail_no;
	}
	public void setRfqdetail_no(String rfqdetail_no) {
		this.rfqdetail_no = rfqdetail_no;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getQuo_date() {
		return quo_date;
	}
	public void setQuo_date(Timestamp quo_date) {
		this.quo_date = quo_date;
	}
	
}
