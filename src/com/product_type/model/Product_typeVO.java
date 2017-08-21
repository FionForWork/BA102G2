package com.product_type.model;

import java.io.Serializable;

public class Product_typeVO implements Serializable {
    private String protype_no;
    private String type_name;

    public Product_typeVO() {
        super();
    }

    public Product_typeVO(String protype_no, String type_name) {
        super();
        this.protype_no = protype_no;
        this.type_name = type_name;
    }

    public String getProtype_no() {
        return protype_no;
    }

    public void setProtype_no(String protype_no) {
        this.protype_no = protype_no;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

}
