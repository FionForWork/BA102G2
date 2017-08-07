package com.reservation.model;

import java.sql.Timestamp;

public class ReservationVO implements java.io.Serializable {
	private String res_no;
	private String mem_no;
	private String com_no;
	private Timestamp res_date;
	private Timestamp serv_date;
	private String serv_no;
	private String stype_no;
	private Integer price;
	private String status;
	private Integer score;
	public ReservationVO() {
		
	}
	
	public String getRes_no() {
		return res_no;
	}
	public void setRes_no(String res_no) {
		this.res_no = res_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getCom_no() {
		return com_no;
	}
	public void setCom_no(String com_no) {
		this.com_no = com_no;
	}
	public Timestamp getRes_date() {
		return res_date;
	}
	public void setRes_date(Timestamp res_date) {
		this.res_date = res_date;
	}

	public Timestamp getServ_date() {
		return serv_date;
	}

	public void setServ_date(Timestamp serv_date) {
		this.serv_date = serv_date;
	}

	public String getServ_no() {
		return serv_no;
	}

	public void setServ_no(String serv_no) {
		this.serv_no = serv_no;
	}

	public String getStype_no() {
		return stype_no;
	}

	public void setStype_no(String stype_no) {
		this.stype_no = stype_no;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	
}
