package com.place.model;

import java.util.List;

import com.placeview.model.PlaceViewVO;

public interface PlaceDAO_Interface {
    public void add(PlaceVO placeVO);

    public void add(PlaceVO placeVO,List<PlaceViewVO> viewList);

    public void delete(String pla_no);

    public void update(PlaceVO placeVO);

    public PlaceVO getOneByPK(String pla_no);

    public List<PlaceVO> getAll();
    
    public int getAllCount();

    public List<PlaceVO> getSome(int page,int count);
    
    public List<PlaceVO> getSome(String south,String west,String north,String east);
}
