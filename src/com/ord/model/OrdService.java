package com.ord.model;

import java.sql.Timestamp;
import java.util.List;

public class OrdService {
    private OrdDAO_Interface dao;

    public OrdService() {
        dao = new OrdDAO();
    }

    public void addOrd(String seller_no, String cust_no, String address, Timestamp ord_date, Integer total, Integer score, String status) {
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

    public void updateOrd(String seller_no, String cust_no, String address, Timestamp ord_date, Integer total, Integer score, String status) {
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

    public OrdVO getOneOrdByCustAndSeller(String cust_no, String seller_no) {
        return dao.getOne(cust_no, seller_no);
    }

    public List<OrdVO> getAllByCust(String cust_no, String status) {
        return dao.getAllByCust(cust_no, status);
    }

    public List<OrdVO> getAllBySeller(String seller_no, String status) {
        return dao.getAllBySeller(seller_no, status);
    }

    public List<OrdVO> getAllByRole(String role_no, String status, String role) {
        if ("0".equals(role)) {
            return getAllByCust(role_no, status);
        }
        else {
            return getAllBySeller(role_no, status);
        }
    }

    public List<OrdVO> getAllByRoleAndOrder(String role, String role_no, String status, String orderType) {
        if ("0".equals(role)) {
            if ("0".equals(orderType)) {
                return dao.getAllByRoleAndOrder(role, role_no, status, "SELLER_NO asc");
            }
            else if ("1".equals(orderType)) {
                return dao.getAllByRoleAndOrder(role, role_no, status, "SELLER_NO desc");
            }
        }
        else if ("1".equals(role)) {
            if ("0".equals(orderType)) {
                return dao.getAllByRoleAndOrder(role, role_no, status, "CUST_NO asc");
            }
            else if ("1".equals(orderType)) {
                return dao.getAllByRoleAndOrder(role, role_no, status, "CUST_NO desc");
            }
        }
        if ("2".equals(orderType)) {
            return dao.getAllByRoleAndOrder(role, role_no, status, "ADDRESS");
        }
        if ("3".equals(orderType)) {
            return dao.getAllByRoleAndOrder(role, role_no, status, "ORD_DATE asc");
        }
        if ("4".equals(orderType)) {
            return dao.getAllByRoleAndOrder(role, role_no, status, "ORD_DATE desc");
        }
        if ("5".equals(orderType)) {
            return dao.getAllByRoleAndOrder(role, role_no, status, "TOTAL asc");
        }
        if ("6".equals(orderType)) {
            return dao.getAllByRoleAndOrder(role, role_no, status, "TOTAL desc");
        }
        return null;
    }
    
    public int getAllOrderCount(String role, String role_no, String status){
        return dao.getAllOrderCount(role, role_no, status);
    }
    
    public List<OrdVO> getAllOrderByRole(String role, String role_no, String status) {
        return dao.getAllOrderByRole(role, role_no, status);
    }
}
