package com.place.model;

import java.util.List;

public interface PlaceDAO_Interface {
	public void insert(PlaceVO placeVO);
    public void update(PlaceVO placeVO);
    public void delete(String pla_no);
    public PlaceVO findByPrimaryKey(String pla_no);
    public List<PlaceVO> getAll();
}
