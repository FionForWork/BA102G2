package com.place.model;

import java.io.Serializable;

public class PlaceVO implements Serializable {
    private String pla_no;
    private String name;
    private byte[] img;
    private String lng;
    private String lat;
    private String addr;

    public PlaceVO() {
        super();
    }

    public PlaceVO(String pla_no, String name, byte[] img, String lng, String lat, String addr) {
        super();
        this.pla_no = pla_no;
        this.name = name;
        this.img = img;
        this.lng = lng;
        this.lat = lat;
        this.addr = addr;
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

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

}
