package com.report.model;

import java.sql.Date;
import java.util.List;

import com.problem.model.ProblemVO;

public class Report_Service {
private Report_interface dao;
public Report_Service(){
	dao=new ReportJNDIDAO();
}

public ReportVO addReport(Integer rep_ob_no,Integer reporter_no,Integer reported_no,Integer rep_type_no,
		String content,Date rep_date,Integer status){
	
	java.util.Date date=new java.util.Date();
	ReportVO reportVO=new ReportVO();
	
	reportVO.setRep_ob_no(rep_ob_no);
	reportVO.setReporter_no(reporter_no);
	reportVO.setReported_no(reported_no);
	reportVO.setRep_type_no(rep_type_no);
	reportVO.setContent(content);
	reportVO.setRep_date(new Date(date.getTime()));
	reportVO.setStatus(status);
	dao.insert(reportVO);
	return reportVO;

}
public ReportVO updateReport(Integer rep_no,Integer status){
	
	
	ReportVO reportVO=new ReportVO();
	reportVO.setStatus(status);
	reportVO.setRep_no(rep_no);
	dao.update(reportVO);
	return reportVO;
}
public List<ReportVO> getAll() {
	return dao.getAll();
}

public ReportVO getOneReport(Integer rep_no) {
	return dao.findByPrimaryKey(rep_no);
}

public void deleteReport(Integer rep_no) {
	dao.delete(rep_no);
}
public List<ReportVO> getOneStatus() {
	return dao.getOneStatus();
}

}
