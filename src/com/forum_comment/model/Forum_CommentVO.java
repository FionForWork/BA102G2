package com.forum_comment.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Forum_CommentVO implements Serializable{
private Integer fmc_no;
private Integer art_no;
private Integer speaker_no;
private String cont;
private Timestamp fmc_date;


public Integer getFmc_no() {
	return fmc_no;
}
public void setFmc_no(Integer fmc_no) {
	this.fmc_no = fmc_no;
}
public Integer getArt_no() {
	return art_no;
}
public void setArt_no(Integer art_no) {
	this.art_no = art_no;
}
public Integer getSpeaker_no() {
	return speaker_no;
}
public void setSpeaker_no(Integer speaker_no) {
	this.speaker_no = speaker_no;
}
public String getCont() {
	return cont;
}
public void setCont(String cont) {
	this.cont = cont;
}
public Timestamp getFmc_date() {
	
	return fmc_date;
}
public void setFmc_date(Timestamp fmc_date) {
	this.fmc_date = fmc_date;
	
}
}
