package com.rfq.model;

import java.util.List;

public interface RFQDAO_Interface {
	
	void insert(RFQVO rfqVO);
	void update(RFQVO rfqVO);
	void delete(String rfq_no);
	RFQVO findByPK(String rfq_no);
	RFQVO findFromQuote(String quo_no);
	List<RFQVO> getAll();
}
