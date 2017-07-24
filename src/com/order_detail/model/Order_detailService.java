package com.order_detail.model;

import java.util.List;

public class Order_detailService {
    private Order_detailDAO_Interface dao;
    
    public Order_detailService(){
        dao=new Order_detailDAO();
    }
    
    public void addOrder_detail(String ord_no,String pro_no,Integer price,Integer qty,Integer itemtot,Integer score){
        Order_detailVO order_detailVO=new Order_detailVO();
        order_detailVO.setOrd_no(ord_no);
        order_detailVO.setPro_no(pro_no);
        order_detailVO.setPrice(price);
        order_detailVO.setQty(qty);
        order_detailVO.setItemtot(itemtot);
        order_detailVO.setScore(score);
        dao.add(order_detailVO);
    }
    
    public void addOrder_detail(Order_detailVO order_detailVO){
        dao.add(order_detailVO);
    }
    
    public void deleteOrder_detail(String ord_no,String pro_no) {
        dao.delete(ord_no,pro_no);
    }
    
    public void updateOrder_detail(Integer price,Integer qty,Integer itemtot,Integer score){
        Order_detailVO order_detailVO=new Order_detailVO();
        order_detailVO.setPrice(price);
        order_detailVO.setQty(qty);
        order_detailVO.setItemtot(itemtot);
        order_detailVO.setScore(score);
        dao.update(order_detailVO);
    }
    
    public void updateOrder_detail(Order_detailVO order_detailVO){
        dao.update(order_detailVO);
    }
    
    public List<Order_detailVO> getAll() {
        return dao.getAll();
    }
}
