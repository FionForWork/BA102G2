package com.report_type.model;

import java.util.List;



public class Report_type_Service {
	private Report_type_intereface dao;
	public Report_type_Service(){
		dao=new Report_typeJNDI();
	}
	
	public Report_typeVO addRep_Type(Integer rep_type_no,String type){
		Report_typeVO report_typeVO=new Report_typeVO();
		report_typeVO.setRep_type_no(rep_type_no);
		report_typeVO.setType(type);
		dao.insert(report_typeVO);
		return  report_typeVO;
		
	}
	public List<Report_typeVO> getAll() {
		return dao.getAll();
	}

	public Report_typeVO getOneRep_Type(Integer rep_type_no) {
		return dao.findByPrimaryKey(rep_type_no);
	}

	public void deleteRep_Type(Integer rep_type_no) {
		dao.delete(rep_type_no);
	}

	
	
	
	
}
