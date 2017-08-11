package com.order_detail.model;

import java.sql.Connection;
import java.util.List;

public interface Order_detailDAO_Interface {

    void insert(Order_detailVO order_detailVO);
    
    void insert(Order_detailVO order_detailVO,Connection connection);

    void delete(String ord_no);

    void update(Order_detailVO order_detailVO);

    Order_detailVO getOneByPK(String ord_no, String pro_no);

    List<Order_detailVO> getAll();

    List<Order_detailVO> getAllByFK(String ord_no);

}
