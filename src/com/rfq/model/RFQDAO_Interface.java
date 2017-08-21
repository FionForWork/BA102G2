package com.rfq.model;

import java.util.List;

import com.rfq_detail.model.RFQ_DetailVO;

public interface RFQDAO_Interface {
	
	void insertWithDetail(RFQVO rfqVO, List<RFQ_DetailVO> list);
	void update(RFQVO rfqVO);
	void delete(String rfq_no);
	RFQVO findByPK(String rfq_no);
	RFQVO findFromQuote(String quo_no);
	List<RFQVO> getAll();
}
