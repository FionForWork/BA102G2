package com.rfq_detail.model;

import java.util.List;

public interface RFQ_DetailDAO_Interface {
	
	void insert(RFQ_DetailVO rfqVO);
	void update(RFQ_DetailVO rfqVO);
	void delete(String rfqdetail_no);
	RFQ_DetailVO findByPK(String rfqdetail_no);
	List<RFQ_DetailVO> getAll();
}
