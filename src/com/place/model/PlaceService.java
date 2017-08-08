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
        dao.add(placeVO);
    }

    public void addPlace(PlaceVO placeVO) {
        dao.add(placeVO);
    }
    public void addPlace(PlaceVO placeVO,List<PlaceViewVO> list) {
        dao.add(placeVO,list);
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

    public List<PlaceVO> getAll() {
        return dao.getAll();
    }

    public List<PlaceVO>getSome(int page,int itemsCount){
        return dao.getSome(page, itemsCount);
    }

    public List<PlaceVO>getSome(String south,String west,String north,String east){
        return dao.getSome( south, west, north, east);
    }
    
    public int getAllCount() {
        return dao.getAllCount();
    }
}
