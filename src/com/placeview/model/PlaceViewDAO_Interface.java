package com.placeview.model;

import java.util.List;

public interface PlaceViewDAO_Interface {
    public void add(PlaceViewVO placeViewVO);
    public void delete(String view_no);
    public void update(PlaceViewVO placeViewVO);
    public PlaceViewVO findByPk(String view_no);
    public List<String> getAllByFK(String pla_no);
}
