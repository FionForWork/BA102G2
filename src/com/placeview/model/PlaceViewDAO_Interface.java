package com.placeview.model;

import java.sql.Connection;
import java.util.List;

public interface PlaceViewDAO_Interface {
    public void insert(PlaceViewVO placeViewVO);
    
    public void insert(PlaceViewVO placeViewVO,Connection connection);
    
    public void delete(String view_no);
    
    public void deleteByFK(String pla_no,Connection connection);
    
    public void deleteByFK(String pla_no);
    
    public void update(PlaceViewVO placeViewVO);
    
    public PlaceViewVO getOneByPK(String view_no);
    
    public List<String> getAllByFK(String pla_no);
    
}
