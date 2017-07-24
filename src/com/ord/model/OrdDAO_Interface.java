package com.ord.model;

import java.util.List;

public interface OrdDAO_Interface {
	void add(OrdVO ordVO);

	void delete(String ord_no);

	void update(OrdVO ordVO);

	OrdVO getOneByPK(String ord_no);

	List<OrdVO> getAll();

}
