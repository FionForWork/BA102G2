package com.rfq_detail.model;

import java.sql.Timestamp;
import java.util.List;

public class RFQ_DetailService {
	
	private RFQ_DetailDAO_Interface dao;
	
	public RFQ_DetailService(){
		dao = new RFQ_DetailDAO();
	}
	
	public RFQ_DetailVO addRFQDetail(String rfqdetail_no, String rfq_no, String stype_no, String location,
									Timestamp ser_date, String title, String content, String status){
		
		RFQ_DetailVO rfq_detailVO = new RFQ_DetailVO();
		
		rfq_detailVO.setRfqdetail_no(rfqdetail_no);
		rfq_detailVO.setRfq_no(rfq_no);
		rfq_detailVO.setStype_no(stype_no);
		rfq_detailVO.setLocation(location);
		rfq_detailVO.setSer_date(ser_date);
		rfq_detailVO.setTitle(title);
		rfq_detailVO.setContent(content);
		rfq_detailVO.setStatus(status);
		dao.insert(rfq_detailVO);
		
		return rfq_detailVO;
		
	}
	
	public RFQ_DetailVO updateRFQDetailStatus(String status,String rfqdetail_no){
		
		RFQ_DetailVO rfq_detailVO = new RFQ_DetailVO();
		rfq_detailVO.setStatus(status);
		rfq_detailVO.setRfqdetail_no(rfqdetail_no);
		dao.update(rfq_detailVO);
		
		return rfq_detailVO;
		
	}
	
	public void deleteRFQDetail(String rfqdetail_no){
		
		dao.delete(rfqdetail_no);
	
	}
	
	public RFQ_DetailVO getOneRFQDetail(String rfqdetail_no){
		
		RFQ_DetailVO rfq_detailVO = dao.findByPK(rfqdetail_no);
		return rfq_detailVO;
	}
	
	public List<RFQ_DetailVO> getAllRFQDetail(){
		
		return dao.getAll();
		
	}
	
}
