package com.placeview.model;

import java.io.Serializable;

public class PlaceViewVO implements Serializable{
    
    private String view_no;
    private String pal_no;
    private byte[] img;
    
    public PlaceViewVO(String view_no, String pal_no, byte[] img) {
        super();
        this.view_no = view_no;
        this.pal_no = pal_no;
        this.img = img;
    }
    public PlaceViewVO() {
        super();
    }
    public String getView_no() {
        return view_no;
    }
    public void setView_no(String view_no) {
        this.view_no = view_no;
    }
    public String getPal_no() {
        return pal_no;
    }
    public void setPal_no(String pal_no) {
        this.pal_no = pal_no;
    }
    public byte[] getImg() {
        return img;
    }
    public void setImg(byte[] img) {
        this.img = img;
    }
    
    
}
