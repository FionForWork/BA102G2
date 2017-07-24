package com.order_detail.model;

import java.io.Serializable;

public class Order_detailVO implements Serializable{
	private String ord_no;
	private String pro_no;
	private Integer price;
	private Integer qty;
	private Integer itemtot;
	private Integer score;
	public Order_detailVO() {
		super();
	}
	public Order_detailVO(String ord_no, String pro_no, Integer price, Integer qty, Integer itemtot, Integer score) {
		super();
		this.ord_no = ord_no;
		this.pro_no = pro_no;
		this.price = price;
		this.qty = qty;
		this.itemtot = itemtot;
		this.score = score;
	}
	public String getOrd_no() {
		return ord_no;
	}
	public void setOrd_no(String ord_no) {
		this.ord_no = ord_no;
	}
	public String getPro_no() {
		return pro_no;
	}
	public void setPro_no(String pro_no) {
		this.pro_no = pro_no;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getItemtot() {
		return itemtot;
	}
	public void setItemtot(Integer itemtot) {
		this.itemtot = itemtot;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	
}
