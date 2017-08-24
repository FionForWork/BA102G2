package com.report_type.model;

import java.util.List;



public interface Report_type_intereface {
	
	 public void insert(Report_typeVO report_typeVO);
     public void update(Report_typeVO report_typeVO);
     public void delete(Integer rep_type_no);
     public Report_typeVO findByPrimaryKey(Integer rep_type_no);
     public List<Report_typeVO> getAll();

}
