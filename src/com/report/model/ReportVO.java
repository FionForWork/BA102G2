package com.report.model;

import java.sql.Date;

public class ReportVO {
private Integer rep_no;
private Integer rep_ob_no;
private Integer reporter_no;
private Integer reported_no;
private Integer rep_type_no;
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
public Integer getReporter_no() {
	return reporter_no;
}
public void setReporter_no(Integer reporter_no) {
	this.reporter_no = reporter_no;
}
public Integer getReported_no() {
	return reported_no;
}
public void setReported_no(Integer reported_no) {
	this.reported_no = reported_no;
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
public Integer getRep_ob_no() {
	return rep_ob_no;
}
public void setRep_ob_no(Integer rep_ob_no) {
	this.rep_ob_no = rep_ob_no;
}
public Integer getRep_type_no() {
	return rep_type_no;
}
public void setRep_type_no(Integer rep_type_no) {
	this.rep_type_no = rep_type_no;
}

}
