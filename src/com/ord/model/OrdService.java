package com.ord.model;

import static java.util.Comparator.comparing;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.tribes.util.StringManager;

import com.order_detail.model.Order_detailVO;
import com.product.model.ProductVO;
import com.sun.org.apache.bcel.internal.generic.DALOAD;

public class OrdService {
    private OrdDAO_Interface dao;

    public OrdService() {
        dao = new OrdDAO();
    }

    public void insert(String seller_no, String cust_no, String address, Timestamp ord_date, Integer total, Integer score, String status) {
        OrdVO ordVO = new OrdVO();
        ordVO.setSeller_no(seller_no);
        ordVO.setCust_no(cust_no);
        ordVO.setAddress(address);
        ordVO.setOrd_date(ord_date);
        ordVO.setTotal(total);
        ordVO.setScore(score);
        ordVO.setStatus(status);
        dao.insert(ordVO);
    }
    
    public void insert(OrdVO ordVO) {
        dao.insert(ordVO);
    }

    public void insert(OrdVO ordVO, List<Order_detailVO> list) {
        dao.insert(ordVO, list);
    }
    public void deleteByFK(String ord_no) {
        dao.delete(ord_no);
    }

    public void update(String seller_no, String cust_no, String address, Timestamp ord_date, Integer total, Integer score, String status) {
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

    public void update(OrdVO ordVO) {
        dao.update(ordVO);
    }

    public OrdVO getOneByPK(String ord_no) {
        return dao.getOneByPK(ord_no);
    }

    public List<OrdVO> getAll() {
        return dao.getAll();
    }

    public List<OrdVO> getAllByRole(String role, String role_no, String status ) {
        if ("0".equals(role)) {
            return dao.getAllByCust(role_no, status);
        }
        else {
            return dao.getAllBySeller(role_no, status);
        }
    }

    public List<OrdVO> getAllByRole(String role, String role_no, String status, String orderType) {
        String orderMethod="";
        if ("0".equals(orderType)) {
            if ("0".equals(role)) {
                orderMethod="SELLER_NO asc";
            }
            else if ("1".equals(role)) {
                orderMethod="SELLER_NO desc";
            }
        }
        else if ("1".equals(orderType)) {
            if ("0".equals(role)) {
                orderMethod="CUST_NO asc";
            }
            else if ("1".equals(role)) {
                orderMethod="CUST_NO desc";
            }
        }
        else if ("2".equals(orderType)) {
            orderMethod="ADDRESS";
        }
        else if ("3".equals(orderType)) {
            orderMethod="ORD_DATE asc";
        }
        else if ("4".equals(orderType)) {
            orderMethod="ORD_DATE desc";
        }
        else if ("5".equals(orderType)) {
            orderMethod="TOTAL asc";
        }
        else if ("6".equals(orderType)) {
            orderMethod="TOTAL desc";
        }
        if(role.equals("0")){
            return dao.getAllByCust(role_no, status, orderMethod);
        }
        else{
            return dao.getAllBySeller(role_no, status, orderMethod);
        }
    }

    public int getAllRowCountByRoleAndStatus(String role, String role_no, String status) {
        if(role.equals("0")){
            return dao.getAllRowCountByCust(role_no, status);
        }
        else{
            return dao.getAllRowCountBySeller(role_no, status);
        }
    }

    public List<OrdVO> getAllByRole(int nowPage, int itemsCount,String role, String role_no, String status, String orderType) {
        List<OrdVO>originList=null;
        if(role.equals("0")){
            originList=dao.getAllByCust(role_no, status);
        }
        else if(role.equals("1")){
            originList=dao.getAllBySeller(role_no, status);
        }
        Comparator<OrdVO> orderMethod = comparing(OrdVO::getOrd_no);
        if ("0".equals(role)) {
            if ("1".equals(orderType)) {
                orderMethod = comparing(OrdVO::getSeller_no);
            }
            else if ("2".equals(orderType)) {
                orderMethod = comparing(OrdVO::getSeller_no).reversed();
            }
        }
        else if ("1".equals(role)) {
            if ("1".equals(orderType)) {
                orderMethod = comparing(OrdVO::getCust_no);
            }
            else if ("2".equals(orderType)) {
                orderMethod = comparing(OrdVO::getCust_no).reversed();
            }
        }
        if ("3".equals(orderType)) {
            orderMethod = comparing(OrdVO::getAddress);
        }
        else if ("4".equals(orderType)) {
            orderMethod = comparing(OrdVO::getOrd_date);
        }
        else if ("5".equals(orderType)) {
            orderMethod = comparing(OrdVO::getOrd_date).reversed();
        }
        else if ("6".equals(orderType)) {
            orderMethod = comparing(OrdVO::getTotal);
        }
        else if ("7".equals(orderType)) {
            orderMethod = comparing(OrdVO::getTotal).reversed();
        }
        originList=originList.stream().sorted(orderMethod).collect(Collectors.toList());
        int start = (nowPage - 1) * itemsCount;
        int end = (nowPage * itemsCount > originList.size()) ? originList.size() : nowPage * itemsCount;
        List<OrdVO> pageList=new ArrayList<OrdVO>();
        for(int i=start;i<end;i++){
            pageList.add(originList.get(i));
        }
        return pageList;
    }
    
    public int getAllRowCountByRole(String role, String role_no,String status){
        if(role.equals("0")){
            return dao.getAllRowCountByCust(role_no, status);
        }
        else{
            return dao.getAllRowCountBySeller(role_no, status);
        }
    }
    
    public List<OrdVO> getPageByRole(int nowPage, int itemsCount,String role, String role_no, String status, String orderType){
        int allcount=getAllRowCountByRole(role,role_no,status);
        int start = (nowPage - 1) * itemsCount;
        List<OrdVO>pageList=null;
        String orderMethod="";
        if("0".equals(orderType)){
            orderMethod="ORD_NO asc";
        }
        else if ("1".equals(orderType)) {
            if ("0".equals(role)) {
                orderMethod="SELLER_NO asc";
            }
            else if ("1".equals(role)) {
                orderMethod="SELLER_NO desc";
            }
        }
        else if ("2".equals(orderType)) {
            if ("0".equals(role)) {
                orderMethod="CUST_NO asc";
            }
            else if ("1".equals(role)) {
                orderMethod="CUST_NO desc";
            }
        }
        else if ("3".equals(orderType)) {
            orderMethod="ADDRESS";
        }
        else if ("4".equals(orderType)) {
            orderMethod="ORD_DATE asc";
        }
        else if ("5".equals(orderType)) {
            orderMethod="ORD_DATE desc";
        }
        else if ("6".equals(orderType)) {
            orderMethod="TOTAL asc";
        }
        else if ("7".equals(orderType)) {
            orderMethod="TOTAL desc";
        }
        if(role.equals("0")){
            pageList=dao.getPageByCust(start, itemsCount, role_no, status, orderMethod);
        }
        else{
            pageList=dao.getPageBySeller(start, itemsCount, role_no, status, orderMethod);
        }
        return pageList;
    }
    
}
