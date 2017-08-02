package com.product_type.model;

import java.util.List;

import com.ord.model.OrdVO;

public class Product_typeService {
    private Product_typeDAO_Interface dao;

    public Product_typeService() {
        dao = new Product_typeDAO();
    }

    public void addProduct_type(String type_name) {
        Product_typeVO product_typeVO = new Product_typeVO();
        product_typeVO.setType_name(type_name);
        dao.add(product_typeVO);
    }

    public void addProduct_type(Product_typeVO product_typeVO) {
        dao.add(product_typeVO);
    }

    public void deleteProduct_type(String protype_no) {
        dao.delete(protype_no);
    }

    public void updateProduct_type(String type_name) {
        Product_typeVO product_typeVO = new Product_typeVO();
        product_typeVO.setType_name(type_name);
        dao.update(product_typeVO);
    }

    public void updateProduct_type(Product_typeVO product_typeVO) {
        dao.update(product_typeVO);
    }

    public Product_typeVO getOneOrd(String protype_no) {
        return dao.getOneByPK(protype_no);
    }

    public List<Product_typeVO> getAll() {
        return dao.getAll();
    }
}
