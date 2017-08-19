package com.placeview.model;

import java.io.Serializable;

import com.place.model.PlaceVO;

public class PlaceViewVO_Hibernate implements Serializable{
    
    private String view_no;
    private PlaceVO placeVO;
    private byte[] img;
    public PlaceViewVO_Hibernate() {
        super();
    }
    public PlaceViewVO_Hibernate(String view_no, PlaceVO placeVO, byte[] img) {
        super();
        this.view_no = view_no;
        this.placeVO = placeVO;
        this.img = img;
    }
    public String getView_no() {
        return view_no;
    }
    public void setView_no(String view_no) {
        this.view_no = view_no;
    }
    public PlaceVO getPlaceVO() {
        return placeVO;
    }
    public void setPlaceVO(PlaceVO placeVO) {
        this.placeVO = placeVO;
    }
    public byte[] getImg() {
        return img;
    }
    public void setImg(byte[] img) {
        this.img = img;
    }
    
}
