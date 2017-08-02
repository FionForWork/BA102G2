package com.order_detail.model;

import java.util.List;

public interface Order_detailDAO_Interface {

    void add(Order_detailVO order_detailVO);

    void delete(String ord_no, String pro_no);

    void update(Order_detailVO order_detailVO);

    Order_detailVO getOneByPK(String ord_no, String pro_no);

    List<Order_detailVO> getAll();

    List<Order_detailVO> getAllByOrdNo(String ord_no);

}
