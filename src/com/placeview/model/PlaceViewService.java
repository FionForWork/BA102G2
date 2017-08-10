package com.placeview.model;

import java.util.List;

public class PlaceViewService {
    
    private PlaceViewDAO_Interface dao= new PlaceViewDAO();
    
    public void add(PlaceViewVO placeViewVO){
        dao.add(placeViewVO);
    }
    public void delete(String view_no){
        dao.delete(view_no);
    }
    public void update(PlaceViewVO placeViewVO){
        dao.update(placeViewVO);
    }
    public PlaceViewVO findByPk(String view_no){
        return dao.findByPk(view_no);
    }
    public List<String> getAllByFk(String pla_no){
        return dao.getAllByFK(pla_no);
    }
}
