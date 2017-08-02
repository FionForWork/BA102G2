package com.place.model;

import java.util.List;

public interface PlaceDAO_Interface {
    public void add(PlaceVO placeVO);

    public void delete(String pla_no);

    public void update(PlaceVO placeVO);

    public PlaceVO getOneByPK(String pla_no);

    public List<PlaceVO> getAll();

    public List<PlaceVO> getSome(int page,int count);
    
    public List<PlaceVO> getSome(String south,String west,String north,String east);
}
