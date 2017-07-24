package com.product.model;

import java.util.List;

public interface ProductDAO_Interface {
	void add(ProductVO productVO);

	void delete(String pro_no);

	void update(ProductVO productVO);

	ProductVO getOneByPK(String pro_no);

	List<ProductVO> getAllBySeller(String seller_no);

	List<ProductVO> getAll();
	
	

}
