package com.rfq_detail.model;

import java.sql.Connection;
import java.util.List;

public interface RFQ_DetailDAO_Interface {
	
	void insert(RFQ_DetailVO rfqVO, Connection con);
	void update(RFQ_DetailVO rfqVO);
	void updateStatusFromRes(RFQ_DetailVO rfqVO, Connection con);
	void delete(String rfqdetail_no);
	RFQ_DetailVO findByPK(String rfqdetail_no);
	RFQ_DetailVO getOneFromQuote(String quo_no);
	List<RFQ_DetailVO> getAll();
	List<RFQ_DetailVO> getSome(String rfq_no);
	List<RFQ_DetailVO> getMy(String mem_no);
}
