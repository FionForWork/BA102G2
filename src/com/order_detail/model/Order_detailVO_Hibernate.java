package com.order_detail.model;

import java.io.Serializable;

import com.ord.model.OrdVO;
import com.product.model.ProductVO;

public class Order_detailVO_Hibernate implements Serializable {
    private OrdVO ordVO;
    private ProductVO productVO; 
    private Integer price;
    private Integer qty;
    private Integer itemtot;
    private Integer score;
    private String status;
    
    public Order_detailVO_Hibernate() {
        super();
    }

    public Order_detailVO_Hibernate(OrdVO ordVO, ProductVO productVO, Integer price, Integer qty, Integer itemtot, Integer score, String status) {
        super();
        this.ordVO = ordVO;
        this.productVO = productVO;
        this.price = price;
        this.qty = qty;
        this.itemtot = itemtot;
        this.score = score;
        this.status = status;
    }

    public OrdVO getOrdVO() {
        return ordVO;
    }

    public void setOrdVO(OrdVO ordVO) {
        this.ordVO = ordVO;
    }

    public ProductVO getProductVO() {
        return productVO;
    }

    public void setProductVO(ProductVO productVO) {
        this.productVO = productVO;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getItemtot() {
        return itemtot;
    }

    public void setItemtot(Integer itemtot) {
        this.itemtot = itemtot;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
