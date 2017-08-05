package com.tradition.model;

public class TraditionVO {
private Integer tra_no;
private Integer tra_type_no;
private Integer tra_order;
private String title;
private String article;
private byte[] img;
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public Integer getTra_no() {
	return tra_no;
}
public void setTra_no(Integer tra_no) {
	this.tra_no = tra_no;
}
public Integer getTra_type_no() {
	return tra_type_no;
}
public void setTra_type_no(Integer tra_type_no) {
	this.tra_type_no = tra_type_no;
}
public Integer getTra_order() {
	return tra_order;
}
public void setTra_order(Integer tra_order) {
	this.tra_order = tra_order;
}
public String getArticle() {
	return article;
}
public void setArticle(String article) {
	this.article = article;
}
public byte[] getImg() {
	return img;
}
public void setImg(byte[] img) {
	this.img = img;
}

}
