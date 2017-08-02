package com.product.model;

import java.util.List;

public interface ProductDAO_Interface {
    void add(ProductVO productVO);

    void delete(String pro_no);

    void update(ProductVO productVO);

    ProductVO getOneByPK(String pro_no);

    List<ProductVO> getAll();

    List<ProductVO> getAllNoImg();
    
    List<ProductVO> getAllByType(String protype_no);

    List<ProductVO> getAllBySeller(String seller_no);

    int getAllCount();

    int getAllUnPreviewCount();

    int getTypeAllCount(String protype_no);

    List<ProductVO> getSome(int page, int count);

    List<ProductVO> getSome(int page, int count, String protype_no);

    List<ProductVO> getSome(int page, int count, String protype_no, String orderType);

    List<ProductVO> getSomeUnPreview(int page, int count);


}
