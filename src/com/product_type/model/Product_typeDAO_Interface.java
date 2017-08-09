package com.product_type.model;

import java.util.List;

public interface Product_typeDAO_Interface {
    void add(Product_typeVO product_typeVO);

    void delete(String protype_no);

    void update(Product_typeVO product_typeVO);

    Product_typeVO getOneByPK(String protype_no);

    List<Product_typeVO> getAll();
}
