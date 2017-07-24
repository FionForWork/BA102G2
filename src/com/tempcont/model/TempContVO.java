package com.tempcont.model;
import java.io.Serializable;
import java.sql.Timestamp;

public class TempContVO implements Serializable {

	private String tcont_no;
	private String temp_no;
	private Timestamp upload_date;
	private byte[] img;
	private byte[] vdo;
	
	
	public TempContVO(){}

	public TempContVO(String tcont_no, String temp_no, Timestamp upload_date, byte[] img, byte[] vdo) {
		super();
		this.tcont_no = tcont_no;
		this.temp_no = temp_no;
		this.upload_date = upload_date;
		this.img = img;
		this.vdo = vdo;
	}

	public String getTcont_no() {
		return tcont_no;
	}

	public void setTcont_no(String tcont_no) {
		this.tcont_no = tcont_no;
	}

	public String getTemp_no() {
		return temp_no;
	}

	public void setTemp_no(String temp_no) {
		this.temp_no = temp_no;
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
