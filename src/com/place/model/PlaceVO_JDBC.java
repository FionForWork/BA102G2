package com.place.model;

import java.io.Serializable;

public class PlaceVO_JDBC implements Serializable {
    private String pla_no;
    private String name;
    private String lng;
    private String lat;
    private String addr;
    private String pla_desc;
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
    public String getPla_desc() {
        return pla_desc;
    }
    public void setPla_desc(String pla_desc) {
        this.pla_desc = pla_desc;
    }
    public PlaceVO_JDBC(String pla_no, String name, String lng, String lat, String addr, String pla_desc) {
        super();
        this.pla_no = pla_no;
        this.name = name;
        this.lng = lng;
        this.lat = lat;
        this.addr = addr;
        this.pla_desc = pla_desc;
    }
    public PlaceVO_JDBC() {
        super();
    }
    
}
