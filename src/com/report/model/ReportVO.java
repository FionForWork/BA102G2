package com.report.model;

import java.sql.Date;

public class ReportVO {
private Integer rep_no;
private Integer reproter_no;
private Integer reproted_no;
private String title;
private String content;
private Date rep_date;
private Integer status;


public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
public Integer getRep_no() {
	return rep_no;
}
public void setRep_no(Integer rep_no) {
	this.rep_no = rep_no;
}
public Integer getReproter_no() {
	return reproter_no;
}
public void setReproter_no(Integer reproter_no) {
	this.reproter_no = reproter_no;
}
public Integer getReproted_no() {
	return reproted_no;
}
public void setReproted_no(Integer reproted_no) {
	this.reproted_no = reproted_no;
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
public Date getRep_date() {
	return rep_date;
}
public void setRep_date(Date rep_date) {
	this.rep_date = rep_date;
}
}
