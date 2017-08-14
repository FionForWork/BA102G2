package com.ord.model;

import java.util.List;

import com.order_detail.model.Order_detailVO;

public interface OrdDAO_Interface {
    void insert(OrdVO ordVO);
    
    void insert(OrdVO ordVO,List<Order_detailVO> list);
    
    void delete(String ord_no);

    void update(OrdVO ordVO);

    OrdVO getOneByPK(String ord_no);

    List<OrdVO> getAll();

    List<OrdVO> getAllByCust(String cust_no, String status);

    List<OrdVO> getAllByCust(String cust_no, String status,String orderMethod);
    
    List<OrdVO> getAllBySeller(String seller_no, String status);
    
    List<OrdVO> getAllBySeller(String seller_no, String status,String orderMethod);
    
    int getAllRowCountByCust(String cust_no,String status);

    int getAllRowCountBySeller(String seller_no,String status);

    List<OrdVO> getPageByCust(int start,int itemsCount,String cust_no, String status,String orderMethod);
    
    List<OrdVO> getPageBySeller(int start,int itemsCount,String seller_no, String status,String orderMethod);
}
