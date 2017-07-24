package com.ord.model;

import java.sql.Timestamp;
import java.util.List;

public class OrdService {
    private OrdDAO_Interface dao;

    public OrdService() {
        dao = new OrdDAO();
    }

    public void addOrd(String seller_no, String cust_no, String address, Timestamp ord_date, Integer total,
            Integer score, String status) {
        OrdVO ordVO = new OrdVO();
        ordVO.setSeller_no(seller_no);
        ordVO.setCust_no(cust_no);
        ordVO.setAddress(address);
        ordVO.setOrd_date(ord_date);
        ordVO.setTotal(total);
        ordVO.setScore(score);
        ordVO.setStatus(status);
        dao.add(ordVO);
    }

    public void addOrd(OrdVO ordVO) {
        dao.add(ordVO);
    }

    public void deleteOrd(String ord_no) {
        dao.delete(ord_no);
    }

    public void updateOrd(String seller_no, String cust_no, String address, Timestamp ord_date, Integer total,
            Integer score, String status) {
        OrdVO ordVO = new OrdVO();
        ordVO.setSeller_no(seller_no);
        ordVO.setCust_no(cust_no);
        ordVO.setAddress(address);
        ordVO.setOrd_date(ord_date);
        ordVO.setTotal(total);
        ordVO.setScore(score);
        ordVO.setStatus(status);
        dao.update(ordVO);
    }

    public void updateOrd(OrdVO ordVO) {
        dao.update(ordVO);
    }

    public OrdVO getOneOrd(String ord_no) {
        return dao.getOneByPK(ord_no);
    }

    public List<OrdVO> getAll() {
        return dao.getAll();
    }

    }
