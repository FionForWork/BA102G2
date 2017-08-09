package com.quote.model;

import java.util.List;

public interface QuoteDAO_Interface {
	
	void insert(QuoteVO quoteVO);
	void update(QuoteVO quoteVO);
	void delete(String quo_no);
	QuoteVO findByPK(String quo_no);
	List<QuoteVO> getAll(String rfqdetail_no);
}
