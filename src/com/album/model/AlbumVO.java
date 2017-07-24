package com.album.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AlbumVO implements Serializable {
	private String alb_no;
	private String mem_no;
	private String name;
	private byte[] cover;
	private Timestamp create_date;
	
	public AlbumVO(){}
	
	public AlbumVO(String alb_no, String mem_no, String name, byte[] cover, Timestamp create_date) {
		super();
		this.alb_no = alb_no;
		this.mem_no = mem_no;
		this.name = name;
		this.cover = cover;
		this.create_date = create_date;
	}

	public String getAlb_no() {
		return alb_no;
	}
	public void setAlb_no(String alb_no) {
		this.alb_no = alb_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getCover() {
		return cover;
	}
	public void setCover(byte[] cover) {
		this.cover = cover;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
}
