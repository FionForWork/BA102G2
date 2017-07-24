package com.works.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class WorksVO implements Serializable{

	private String works_no;
	private String com_no;
	private String name;
	private String works_desc;
	private byte[] img;
	private byte[] vdo;
	private Timestamp upload_date;
	
	public WorksVO() {
		super();
	}

	public WorksVO(String works_no, String com_no, String name, String works_desc, byte[] img, byte[] vdo,
			Timestamp upload_date) {
		super();
		this.works_no = works_no;
		this.com_no = com_no;
		this.name = name;
		this.works_desc = works_desc;
		this.img = img;
		this.vdo = vdo;
		this.upload_date = upload_date;
	}

	public String getWorks_no() {
		return works_no;
	}

	public void setWorks_no(String works_no) {
		this.works_no = works_no;
	}

	public String getCom_no() {
		return com_no;
	}

	public void setCom_no(String com_no) {
		this.com_no = com_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWorks_desc() {
		return works_desc;
	}

	public void setWorks_desc(String works_desc) {
		this.works_desc = works_desc;
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

	public Timestamp getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(Timestamp upload_date) {
		this.upload_date = upload_date;
	}

	
}
