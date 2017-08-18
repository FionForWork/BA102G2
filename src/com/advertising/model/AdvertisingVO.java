package com.advertising.model;

import java.sql.*;

public class AdvertisingVO implements java.io.Serializable {
	private String adv_no;
	private String com_no;
	private String title;
	private Timestamp startDay;
	private Timestamp endDay;
	private Integer price;
	private String text;
	private byte[] img;
	private byte[] vdo;
	private String status;

	public AdvertisingVO() {
	}
	
	public String getAdv_no() {
		return adv_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAdv_no(String adv_no) {
		this.adv_no = adv_no;
	}

	public String getCom_no() {
		return com_no;
	}

	public void setCom_no(String com_no) {
		this.com_no = com_no;
	}

	public Timestamp getStartDay() {
		return startDay;
	}

	public void setStartDay(Timestamp startDay) {
		this.startDay = startDay;
	}

	public Timestamp getEndDay() {
		return endDay;
	}

	public void setEndDay(Timestamp endDay) {
		this.endDay = endDay;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public byte[] getVdo() {
		return vdo;
	}

	public void setVdo(byte[] vdo) {
		this.vdo = vdo;
	}
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
