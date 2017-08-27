package com.placeview.model;

import java.io.Serializable;

import com.place.model.PlaceVO;

public class PlaceViewVO implements Serializable{
    
    private PlaceVO placeVO;
    private String view_no;
    private byte[] img;
    
    public PlaceViewVO() {
        super();
    }
    
    public PlaceViewVO(String view_no, PlaceVO placeVO, byte[] img) {
        super();
        this.view_no = view_no;
        this.placeVO = placeVO;
        this.img = img;
    }
    
    public PlaceViewVO(String view_no, PlaceVO placeVO) {
        super();
        this.view_no = view_no;
        this.placeVO = placeVO;
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
