package com.product.model;

import java.util.List;

public class ProductService {
    private ProductDAO_Interface dao;

    public ProductService() {
        dao = new ProductDAO();
    }

    public void addProduct(String pro_name, String pro_desc, Integer price, Integer amount, byte[] img, String status, Integer times, Integer score) {
        ProductVO productVO = new ProductVO();
        productVO.setPro_name(pro_name);
        productVO.setPro_desc(pro_desc);
        productVO.setPrice(price);
        productVO.setAmount(amount);
        productVO.setImg(img);
        productVO.setStatus(status);
        productVO.setTimes(times);
        productVO.setScore(score);
        dao.add(productVO);
    }

    public void addProduct(ProductVO productVO) {
        dao.add(productVO);
    }

    public void deleteProduct(String pro_no) {
        dao.delete(pro_no);
    }

    public void updateProduct(String pro_name, String pro_desc, Integer price, Integer amount, byte[] img, String status, Integer times, Integer score) {
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

    public void updateProduct(ProductVO productVO) {
        dao.update(productVO);
    }

    public ProductVO getOneByPK(String pro_no) {
        return dao.getOneByPK(pro_no);
    }

    public List<ProductVO> getAll() {
        return dao.getAll();
    }

    public int getAllCount() {
        return dao.getAllCount();
    }

    public int getAllCountUnPreivew() {
        return dao.getAllUnPreviewCount();
    }

    public List<ProductVO> getSome(int page, int count) {
        return dao.getSome(page, count);
    }

    public int getTypeAllCount(String protype_no) {
        if ("0".equals(protype_no)) {
            return getAllCount();
        }
        else {
            return dao.getTypeAllCount(protype_no);
        }
    }

    public List<ProductVO> getSome(int page, int count, String protype_no) {
        if ("0".equals(protype_no)) {
            return getSome(page, count);
        }
        else {
            return dao.getSome(page, count, protype_no);
        }
    }

    public List<ProductVO> getSome(int page, int count, String protype_no, String orderType) {
        String orderMethod = "";
        if ("0".equals(orderType)) {
            orderMethod = "pro_no asc";
        }
        else if ("1".equals(orderType)) {
            orderMethod = "pro_name asc";
        }
        else if ("2".equals(orderType)) {
            orderMethod = "pro_date asc";
        }
        else if ("3".equals(orderType)) {
            orderMethod = "pro_date desc";
        }
        else if ("4".equals(orderType)) {
            orderMethod = "price asc";
        }
        else if ("5".equals(orderType)) {
            orderMethod = "price desc";
        }
        else if ("6".equals(orderType)) {
            orderMethod = "seller_no asc";
        }
        return dao.getSome(page, count, protype_no, orderMethod);
    }

    public List<ProductVO> getAllBySeller(String seller_no) {
        return dao.getAllBySeller(seller_no);
    }
    
    public List<ProductVO>getSomeUnPreview(int page, int count){
        return dao.getSomeUnPreview(page, count);
    }
    
    public List<ProductVO>getAllByType(String protype_no){
        return dao.getAllByType(protype_no);
    }
    
    public List<ProductVO> getAllNoDescAndImg() {
        return dao.getAllNoDescAndImg();
    }
    
    public ProductVO getOneByPKNoImg(String pro_no) {
        return dao.getOneByPKNoImg(pro_no);
    }
}
