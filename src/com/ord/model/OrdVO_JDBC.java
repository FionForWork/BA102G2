package com.ord.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrdVO_JDBC implements Serializable {
    private String ord_no;
    private String seller_no;
    private String cust_no;
    private String address;
    private Timestamp ord_date;
    private Integer total;
    private Integer score;
    private String status;

    public OrdVO_JDBC() {
        super();
    }

    public OrdVO_JDBC(String ord_no, String seller_no, String cust_no, String address, Timestamp ord_date, Integer total, Integer score, String status) {
        super();
        this.ord_no = ord_no;
        this.seller_no = seller_no;
        this.cust_no = cust_no;
        this.address = address;
        this.ord_date = ord_date;
        this.total = total;
        this.score = score;
        this.status = status;
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

}
