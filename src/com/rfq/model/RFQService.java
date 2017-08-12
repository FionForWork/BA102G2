package com.rfq.model;

import java.sql.Timestamp;
import java.util.List;

import com.rfq_detail.model.RFQ_DetailVO;

public class RFQService {
	
	private RFQDAO_Interface dao;
	
	public RFQService(){
		dao = new RFQDAO();
	}
	
	public RFQVO addRFQ(String mem_no, Timestamp rfq_date, List<RFQ_DetailVO> list){
		
		RFQVO rfqVO = new RFQVO();
		
		rfqVO.setMem_no(mem_no);
		rfqVO.setRfq_date(rfq_date);
		dao.insertWithDetail(rfqVO, list);
		
		return rfqVO;
	}
	
	public RFQVO updateRFQ(String rfq_no, Timestamp rfq_date){
		
		RFQVO rfqVO = new RFQVO();
		
		rfqVO.setRfq_no(rfq_no);
		rfqVO.setRfq_date(rfq_date);;
		dao.update(rfqVO);
		
		return rfqVO;
	}
	
	public void deleteRFQ(String rfq_no){
		dao.delete(rfq_no);
	}
	
	public RFQVO getOneRFQ(String rfq_no){
		return dao.findByPK(rfq_no);
	}
	
	public List<RFQVO> getAllRFQ(){
		return dao.getAll();
	}
	
	public RFQVO getMemFromQuote(String quo_no){
		return dao.findFromQuote(quo_no);
	}
	
}
