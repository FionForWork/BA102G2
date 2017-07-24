package com.rfq.model;

import java.sql.Timestamp;
import java.util.List;

public class RFQService {
	
	private RFQDAO_Interface dao;
	
	public RFQService(){
		dao = new RFQDAO();
	}
	
	public RFQVO addRFQ(String rfq_no, String mem_no, Timestamp rfq_date){
		
		RFQVO rfqVO = new RFQVO();
		
		rfqVO.setRfq_no(rfq_no);
		rfqVO.setMem_no(mem_no);
		rfqVO.setRfq_date(rfq_date);
		dao.insert(rfqVO);
		
		return rfqVO;
	}
	
	public RFQVO updateRFQ(String rfq_no, Timestamp rfq_date){
		
		RFQVO rfqVO = new RFQVO();
		
		rfqVO.setRfq_no(rfq_no);
		rfqVO.setRfq_date(rfq_date);;
		dao.update(rfqVO);
		
		return rfqVO;
	}
	
	public void deleteServiceType(String rfq_no){
		dao.delete(rfq_no);
	}
	
	public RFQVO getOneServiceType(String rfq_no){
		return dao.findByPK(rfq_no);
	}
	
	public List<RFQVO> getAllServiceType(){
		return dao.getAll();
	}
	
}
