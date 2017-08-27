package com.place.model;

import java.util.List;

import com.placeview.model.PlaceViewVO;

public interface PlaceDAO_Interface {
    public void insert(PlaceVO placeVO);

    public void insert(PlaceVO placeVO,List<PlaceViewVO> viewList);

    public void delete(String pla_no);

    public void update(PlaceVO placeVO);

    public PlaceVO getOneByPK(String pla_no);

    public int getAllCount();

    public List<PlaceVO> getPage(int start,int itemsCount);

    public List<PlaceVO> getRange(String south,String west,String north,String east);

    public List<PlaceVO> getRangeNoSet(String south,String west,String north,String east);
}
