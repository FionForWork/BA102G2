package com.product.model;

import java.util.HashMap;
import java.util.List;

import com.sun.org.apache.regexp.internal.recompile;

public class ProductService {
    private ProductDAO_Interface dao;

    public ProductService() {
        dao = new ProductDAO();
    }

    public void insert(String pro_name, String pro_desc, Integer price, Integer amount, byte[] img, String status, Integer times, Integer score) {
        ProductVO productVO = new ProductVO();
        productVO.setPro_name(pro_name);
        productVO.setPro_desc(pro_desc);
        productVO.setPrice(price);
        productVO.setAmount(amount);
        productVO.setImg(img);
        productVO.setStatus(status);
        productVO.setTimes(times);
        productVO.setScore(score);
        dao.insert(productVO);
    }

    public void insert(ProductVO productVO) {
        dao.insert(productVO);
    }

    public void delete(String pro_no) {
        dao.delete(pro_no);
    }

    public void update(String pro_name, String pro_desc, Integer price, Integer amount, byte[] img, String status, Integer times, Integer score) {
        ProductVO productVO = new ProductVO();
        productVO.setPro_name(pro_name);
        productVO.setPro_desc(pro_desc);
        productVO.setPrice(price);
        productVO.setAmount(amount);
        productVO.setImg(img);
        productVO.setStatus(status);
        productVO.setTimes(times);
        productVO.setScore(score);
        dao.update(productVO);
    }

    public void update(ProductVO productVO) {
        dao.update(productVO);
    }

    public ProductVO getOneByPK(String pro_no) {
        return dao.getOneByPK(pro_no);
    }

    public ProductVO getOneByPKNoImg(String pro_no) {
        return dao.getOneByPKNoImg(pro_no);
    }
    
    public int getAllCount(String protype_no,String status) {
        if(protype_no.equals("0")){
            return dao.getAllCount(status);
        }
        else{
            return dao.getAllCount(protype_no, status);
        }
    }
    
    public List<ProductVO> getPage(int nowPage, int itemsCount,String protype_no,String orderType,String status){
        int start=(nowPage-1)*itemsCount+1;
        if(protype_no.equals("0")){
            return dao.getPage(start, itemsCount, getOrderMethod(orderType), status);
        }
        else{
            return dao.getPage(start, itemsCount, protype_no, getOrderMethod(orderType), status);
        }
    }
    
    public int getAllCountBySeller(String seller_no){
        return dao.getAllCountBySeller(seller_no);
    }
    
    public List<ProductVO> getPageBySeller(int nowPage,int itemsCount,String seller_no) {
        int start=(nowPage-1)*itemsCount+1;
        return dao.getPageBySeller(start,itemsCount,seller_no);
    }
    
    public List<ProductVO> getAllNoImg(String status) {
        return dao.getAllNoImg(status);
    }
    
    private String getOrderMethod(String orderType){
        String orderMethod="";
        switch (orderType) {
            case "0":
                orderMethod = "pro_no asc";
                break;
            case "1":
                orderMethod = "pro_name asc";
                break;
            case "2":
                orderMethod = "pro_date asc";
                break;
            case "3":
                orderMethod = "pro_date desc";
                break;
            case "4":
                orderMethod = "price asc";
                break;
            case "5":
                orderMethod = "price desc";
                break;
            case "6":
                orderMethod = "seller_no asc";
                break;
            default:
                orderMethod = "pro_no asc";
                break;
        }
        return orderMethod;
    }
    
    public List<HashMap<String, Double>> getAllAvgSorce () {
        return dao.getAllAvgSorce();
    }
}
