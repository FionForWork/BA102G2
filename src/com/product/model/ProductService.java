package com.product.model;

import java.util.List;

public class ProductService {
    private ProductDAO_Interface dao;

    public ProductService() {
        dao = new ProductDAO();
    }

    public void addProduct(String pro_name, String pro_desc, Integer price, Integer amount, byte[] img,String status,Integer times,Integer score) {
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
    
    public void updateProduct(String pro_name, String pro_desc, Integer price, Integer amount, byte[] img,
            String status, Integer times, Integer score) {
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
    
    public ProductVO getOneProduct(String pro_no) {
        return dao.getOneByPK(pro_no);
    }

    public List<ProductVO> getAll() {
        return dao.getAll();
    }
    
    
    
    
    
}
