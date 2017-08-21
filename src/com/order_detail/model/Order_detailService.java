package com.order_detail.model;

import java.util.List;

import com.product.model.ProductVO;

public class Order_detailService {
    private Order_detailDAO_Interface dao;

    public Order_detailService() {
        dao = new Order_detailDAO();
    }

    public void addOrder_detail(String ord_no, String pro_no, Integer price, Integer qty, Integer itemtot, Integer score) {
        Order_detailVO order_detailVO = new Order_detailVO();
        order_detailVO.setOrd_no(ord_no);
        order_detailVO.setPro_no(pro_no);
        order_detailVO.setPrice(price);
        order_detailVO.setQty(qty);
        order_detailVO.setItemtot(itemtot);
        order_detailVO.setScore(score);
        dao.insert(order_detailVO);
    }

    public void addOrder_detail(Order_detailVO order_detailVO) {
        dao.insert(order_detailVO);
    }

    public void deleteOrder_detail(String ord_no) {
        dao.delete(ord_no);
    }

    public void updateOrder_detail(Integer price, Integer qty, Integer itemtot, Integer score) {
        Order_detailVO order_detailVO = new Order_detailVO();
        order_detailVO.setPrice(price);
        order_detailVO.setQty(qty);
        order_detailVO.setItemtot(itemtot);
        order_detailVO.setScore(score);
        dao.update(order_detailVO);
    }

    public void updateOrder_detail(Order_detailVO order_detailVO) {
        dao.update(order_detailVO);
    }

    public Order_detailVO getOneByComposite(String ord_no, String pro_no) {
        return dao.getOneByComposite(ord_no, pro_no);
    }

    public List<Order_detailVO> getAllByOrd(String ord_no) {
        return dao.getAllByOrd(ord_no);
    }

    public List<Order_detailVO> getAllByPro(String ord_no) {
        return dao.getAllByPro(ord_no);
    }
}
