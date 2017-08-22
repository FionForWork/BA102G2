package com.protra.model;


import java.io.Serializable;

public class ProtraVO implements Serializable {
    private String protra_no;
    private String pro_no;
    private String mem_no;

    public ProtraVO() {
        super();
    }

    public ProtraVO(String protra_no, String pro_no, String mem_no) {
        super();
        this.protra_no = protra_no;
        this.pro_no = pro_no;
        this.mem_no = mem_no;
    }

    public String getProtra_no() {
        return protra_no;
    }

    public void setProtra_no(String protra_no) {
        this.protra_no = protra_no;
    }

    public String getPro_no() {
        return pro_no;
    }

    public void setPro_no(String pro_no) {
        this.pro_no = pro_no;
    }

    public String getMem_no() {
        return mem_no;
    }

    public void setMem_no(String mem_no) {
        this.mem_no = mem_no;
    }

}
