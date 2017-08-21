package com.product.model;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.sun.javafx.collections.MappingChange.Map;

public interface ProductDAO_Interface {
    
    void insert(ProductVO productVO);

    void delete(String pro_no);

    void deleteByFK(String protype_no);

    void deleteByFK(String protype_no,Connection connection);

    void update(ProductVO productVO);
    
    ProductVO getOneByPK(String pro_no);

    ProductVO getOneByPKNoImg(String pro_no);

    List<ProductVO> getAllNoImg(String status);

    int getAllCount(String status);

    int getAllCount(String protype_no,String status);

    List<ProductVO> getPage(int start, int itemsCount, String orderMethod,String status);

    List<ProductVO> getPage(int start, int itemsCount, String protype_no, String orderMethod,String status);
    
    int getAllCountBySeller(String seller_no);
    
    List<ProductVO> getPageBySeller(int start, int itemsCount,String seller_no);
    
    List<HashMap<String, Double>> getAllAvgSorce();
}
