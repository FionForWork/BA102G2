package com.ord.model;

import java.util.List;

import com.order_detail.model.Order_detailVO;

public interface OrdDAO_Interface {
    void add(OrdVO ordVO);
    
    void insert(OrdVO ordVO,List<Order_detailVO> list);
    
    void delete(String ord_no);

    void update(OrdVO ordVO);

    OrdVO getOneByPK(String ord_no);

    OrdVO getOne(String cust_no, String seller_no);

    List<OrdVO> getAll();

    List<OrdVO> getAllByCust(String cust_no, String status);

    List<OrdVO> getAllBySeller(String seller_no, String status);

    List<OrdVO> getAllByRoleAndOrder(String role, String role_no, String status, String orderType);

    int getAllOrderCount(String role,String role_no,String status);
 
    List<OrdVO> getAllOrderByRole(String role,String role_no,String status);
    
}
