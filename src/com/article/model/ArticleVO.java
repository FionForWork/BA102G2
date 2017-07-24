package com.article.model;

import java.io.Serializable;
import java.sql.Date;

public class ArticleVO implements Serializable{
	private Integer art_no;
	private Integer poster_no;
	private Integer art_type_no;
	private String title;
	private String content;
	private Date art_date;
	
	
	public Integer getArt_no() {
		return art_no;
	}
	public void setArt_no(Integer art_no) {
		this.art_no = art_no;
	}
	public Integer getPoster_no() {
		return poster_no;
	}
	public void setPoster_no(Integer poster_no) {
		this.poster_no = poster_no;
	}
	public Integer getArt_type_no() {
		return art_type_no;
	}
	public void setArt_type_no(Integer art_type_no) {
		this.art_type_no = art_type_no;
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
	public Date getArt_date() {
		return art_date;
	}
	public void setArt_date(Date art_date) {
		this.art_date = art_date;
	}
}
