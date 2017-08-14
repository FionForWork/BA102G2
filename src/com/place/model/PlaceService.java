package com.place.model;

import java.util.List;

import com.placeview.model.PlaceViewVO;

public class PlaceService {
    private PlaceDAO_Interface dao;

    public PlaceService() {
        dao = new PlaceDAO();
    }

    public void addPlace(String name, String lng, String lat, String addr,String pla_desc) {
        PlaceVO placeVO = new PlaceVO();
        placeVO.setName(name);
        placeVO.setLng(lng);
        placeVO.setLat(lat);
        placeVO.setAddr(addr);
        placeVO.setPla_desc(pla_desc);
        dao.insert(placeVO);
    }

    public void addPlace(PlaceVO placeVO) {
        dao.insert(placeVO);
    }
    public void addPlace(PlaceVO placeVO,List<PlaceViewVO> list) {
        dao.insert(placeVO,list);
    }

    public void deletePlace(String pla_no) {
        dao.delete(pla_no);
    }

    public void updatePlace(String name, String lng, String lat, String addr,String pla_desc) {
        PlaceVO placeVO = new PlaceVO();
        placeVO.setName(name);
        placeVO.setLng(lng);
        placeVO.setLat(lat);
        placeVO.setAddr(addr);
        placeVO.setPla_desc(pla_desc);
        dao.update(placeVO);
    }

    public void updatePlace(PlaceVO placeVO) {
        dao.update(placeVO);
    }

    public PlaceVO getOnePlace(String pla_no) {
        return dao.getOneByPK(pla_no);
    }

    public int getAllCount() {
        return dao.getAllCount();
    }

    public List<PlaceVO>getPage(int nowPage,int itemsCount){
        int start=(nowPage-1)*itemsCount;
        return dao.getPage(start, itemsCount);
    }

    public List<PlaceVO>getRange(String south,String west,String north,String east){
        return dao.getRange( south, west, north, east);
    }
}
