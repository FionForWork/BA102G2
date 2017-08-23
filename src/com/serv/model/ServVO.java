package com.serv.model;

public class ServVO {

	private String serv_no;
	private String stype_no;
	private String com_no;
	private Integer deposit;
	private Integer price;
	private String title;
	private String content;
	private Integer times;
	private Double score;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getCom_no() {
		return com_no;
	}
	public void setCom_no(String com_no) {
		this.com_no = com_no;
	}
	public Integer getDeposit() {
		return deposit;
	}
	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
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
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	
}
