package com.report.model;

import java.util.List;



public interface Report_interface {
	public void insert(ReportVO reportVO);
    public void update(ReportVO reportVO);
    public void delete(Integer rep_no);
    public ReportVO findByPrimaryKey(Integer rep_no);
    public List<ReportVO> getAll();
}
