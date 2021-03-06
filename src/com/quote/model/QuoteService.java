package com.quote.model;

import java.sql.Timestamp;
import java.util.List;

public class QuoteService {
	
	private QuoteDAO_Interface dao;
	
	public QuoteService(){
		dao = new QuoteDAO();
	}
	
	public QuoteVO addQuote(String com_no, String rfqdetail_no, Integer price, 
							String content, Timestamp quo_date){
		
		QuoteVO quoteVO = new QuoteVO();
		quoteVO.setCom_no(com_no);
		quoteVO.setRfqdetail_no(rfqdetail_no);
		quoteVO.setPrice(price);
		quoteVO.setContent(content);
		quoteVO.setQuo_date(quo_date);
		dao.insert(quoteVO);
		
		return quoteVO;
		
	}
	
	public QuoteVO updateQuote(String quo_no, Integer price, Timestamp quo_date){
		
		QuoteVO quoteVO = new QuoteVO();
		quoteVO.setQuo_no(quo_no);
		quoteVO.setPrice(price);
		quoteVO.setQuo_date(quo_date);
		dao.update(quoteVO);
		
		return quoteVO;
	}
	
	public void deleteQuote(String quo_no){
		dao.delete(quo_no);
	}
	
	public QuoteVO getOneQuote(String quo_no){
		return dao.findByPK(quo_no);
	}
	
	public List<QuoteVO> getAllQuote(String rfqdetail_no){
		return dao.getAll(rfqdetail_no);
	}
	
	public List<QuoteVO> getComQuote(String com_no){
		return dao.getCom(com_no);
	}
	
}
