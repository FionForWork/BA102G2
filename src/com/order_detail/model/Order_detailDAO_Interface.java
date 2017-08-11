package com.order_detail.model;

import java.sql.Connection;
import java.util.List;

public interface Order_detailDAO_Interface {

    void insert(Order_detailVO order_detailVO);
    
    void insert(Order_detailVO order_detailVO,Connection connection);

    void delete(String ord_no);

    void delete(String ord_no,Connection connection);

    void update(Order_detailVO order_detailVO);

    Order_detailVO getOneByComposite(String ord_no, String pro_no);

    List<Order_detailVO> getAllByOrd(String ord_no);

    List<Order_detailVO> getAllByPro(String pro_no);

}
