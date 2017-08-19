package com.ord.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import com.order_detail.model.Order_detailVO;

public class OrdVO_Hibernate implements Serializable {
    private String ord_no;
    private String seller_no;
    private String cust_no;
    private String address;
    private Timestamp ord_date;
    private Integer total;
    private Integer score;
    private String status;
    private Set<Order_detailVO> order_detailVOSet=new LinkedHashSet<Order_detailVO>();

    public OrdVO_Hibernate() {
        super();
    }

    public OrdVO_Hibernate(String ord_no, String seller_no, String cust_no, String address, Timestamp ord_date, Integer total, Integer score, String status, Set<Order_detailVO> order_detailVOSet) {
        super();
        this.ord_no = ord_no;
        this.seller_no = seller_no;
        this.cust_no = cust_no;
        this.address = address;
        this.ord_date = ord_date;
        this.total = total;
        this.score = score;
        this.status = status;
        this.order_detailVOSet = order_detailVOSet;
    }

    public String getOrd_no() {
        return ord_no;
    }

    public void setOrd_no(String ord_no) {
        this.ord_no = ord_no;
    }

    public String getSeller_no() {
        return seller_no;
    }

    public void setSeller_no(String seller_no) {
        this.seller_no = seller_no;
    }

    public String getCust_no() {
        return cust_no;
    }

    public void setCust_no(String cust_no) {
        this.cust_no = cust_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getOrd_date() {
        return ord_date;
    }

    public void setOrd_date(Timestamp ord_date) {
        this.ord_date = ord_date;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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
    
    public Set<Order_detailVO> getOrder_detailVOSet() {
        return order_detailVOSet;
    }

    public void setOrder_detailVOSet(Set<Order_detailVO> order_detailVOSet) {
        this.order_detailVOSet = order_detailVOSet;
    }

}
