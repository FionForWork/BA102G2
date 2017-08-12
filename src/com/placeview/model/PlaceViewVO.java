package com.placeview.model;

import java.io.Serializable;

public class PlaceViewVO implements Serializable{
    
    private String view_no;
    private String pla_no;
    private byte[] img;
    public String getView_no() {
        return view_no;
    }
    public void setView_no(String view_no) {
        this.view_no = view_no;
    }
    public String getPla_no() {
        return pla_no;
    }
    public void setPla_no(String pla_no) {
        this.pla_no = pla_no;
    }
    public byte[] getImg() {
        return img;
    }
    public void setImg(byte[] img) {
        this.img = img;
    }
    public PlaceViewVO(String view_no, String pla_no, byte[] img) {
        super();
        this.view_no = view_no;
        this.pla_no = pla_no;
        this.img = img;
    }
    public PlaceViewVO() {
        super();
    }
    
   
    
}
