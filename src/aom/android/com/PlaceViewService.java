package aom.android.com;

import java.sql.Connection;
import java.util.List;

import com.placeview.model.PlaceViewDAO_Interface;
import com.placeview.model.PlaceViewVO;

public class PlaceViewService {
    
    private PlaceViewDAO_Interface dao= new PlaceViewDAO_JDBC();
    
    public void insert(PlaceViewVO placeViewVO){
        dao.insert(placeViewVO);
    }
    public void insert(PlaceViewVO placeViewVO,Connection connection){
        dao.insert(placeViewVO,connection);
    }
    public void delete(String view_no){
        dao.delete(view_no);
    }
    public void deleteByFK(String pla_no){
        dao.deleteByFK(pla_no);
    }
    public void deleteByFK(String pla_no,Connection connection){
        dao.deleteByFK(pla_no,connection);
    }
    public void update(PlaceViewVO placeViewVO){
        dao.update(placeViewVO);
    }
    public PlaceViewVO getOneByPK(String view_no){
        return dao.getOneByPK(view_no);
    }
    public List<String> getAllByFk(String pla_no){
        return dao.getAllByFK(pla_no);
    }
}
