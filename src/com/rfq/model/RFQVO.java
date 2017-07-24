package com.rfq.model;

import java.sql.*;

public class RFQVO implements java.io.Serializable {
	private String rfq_no;
	private String mem_no;
	private Timestamp rfq_date;
	
	public RFQVO() {
	}

	public RFQVO(String rfq_no, String mem_no, Timestamp rfq_date) {
		this.rfq_no = rfq_no;
		this.mem_no = mem_no;
		this.rfq_date = rfq_date;
	}
	
	public String getRfq_no() {
		return rfq_no;
	}
	public void setRfq_no(String rfq_no) {
		this.rfq_no = rfq_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Timestamp getRfq_date() {
		return rfq_date;
	}
	public void setRfq_date(Timestamp rfq_date) {
		this.rfq_date = rfq_date;
	}
	
	
}
