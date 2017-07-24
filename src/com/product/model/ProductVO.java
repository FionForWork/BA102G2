package com.product.model;

import java.sql.Timestamp;

public class ProductVO {
    private String pro_no;
    private String pro_name;
    private String seller_no;
    private String pro_desc;
    private Integer price;
    private Integer amount;
    private byte[] img;
    private Timestamp pro_date;
    private String protype_no;
    private String status;
    private Integer times;
    private Integer score;

    public ProductVO() {
        super();
    }

    public ProductVO(String pro_no, String pro_name, String seller_no, String pro_desc, Integer price, Integer amount,
            byte[] img, Timestamp pro_date, String protype_no, String status, Integer times, Integer score) {
        super();
        this.pro_no = pro_no;
        this.pro_name = pro_name;
        this.seller_no = seller_no;
        this.pro_desc = pro_desc;
        this.price = price;
        this.amount = amount;
        this.img = img;
        this.pro_date = pro_date;
        this.protype_no = protype_no;
        this.status = status;
        this.times = times;
        this.score = score;
    }

    public String getPro_no() {
        return pro_no;
    }

    public void setPro_no(String pro_no) {
        this.pro_no = pro_no;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getSeller_no() {
        return seller_no;
    }

    public void setSeller_no(String seller_no) {
        this.seller_no = seller_no;
    }

    public String getPro_desc() {
        return pro_desc;
    }

    public void setPro_desc(String pro_desc) {
        this.pro_desc = pro_desc;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Timestamp getPro_date() {
        return pro_date;
    }

    public void setPro_date(Timestamp pro_date) {
        this.pro_date = pro_date;
    }

    public String getProtype_no() {
        return protype_no;
    }

    public void setProtype_no(String protype_no) {
        this.protype_no = protype_no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    
}