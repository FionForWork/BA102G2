package com.place.model;

public class PlaceVO implements java.io.Serializable {
	private String pla_no;
	private String name;
	private byte[] img;
	private Double lat;
	private Double lng;
	private String addr;

	public PlaceVO() {
	}

	public String getPla_no() {
		return pla_no;
	}

	public void setPla_no(String pla_no) {
		this.pla_no = pla_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
