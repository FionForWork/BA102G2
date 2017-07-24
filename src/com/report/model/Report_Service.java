package com.report.model;

import java.sql.Date;
import java.util.List;

import com.problem.model.ProblemVO;

public class Report_Service {
private Report_interface dao;
public Report_Service(){
	dao=new ReportJNDIDAO();
}

public ReportVO addReport(Integer reproter_no,Integer reproted_no,String title,
		String content,Date rep_date,Integer status){
	
	java.util.Date date=new java.util.Date();
	ReportVO reportVO=new ReportVO();
	
	reportVO.setReproter_no(reproter_no);
	reportVO.setReproted_no(reproted_no);
	reportVO.setTitle(title);
	reportVO.setContent(content);
	reportVO.setRep_date(new Date(date.getTime()));
	reportVO.setStatus(status);
	dao.insert(reportVO);
	return reportVO;

}
public ReportVO updateReport(Integer rep_no,Integer reproter_no,Integer reproted_no,String title,
		String content,Date rep_date,Integer status){
	
	java.util.Date date=new java.util.Date();
	ReportVO reportVO=new ReportVO();
	
	reportVO.setReproter_no(reproter_no);
	reportVO.setReproted_no(reproted_no);
	reportVO.setTitle(title);
	reportVO.setContent(content);
	reportVO.setRep_date(new Date(date.getTime()));
	reportVO.setStatus(status);
	reportVO.setRep_no(rep_no);
	dao.update(reportVO);
	return reportVO;
}
public List<ReportVO> getAll() {
	return dao.getAll();
}

public ReportVO getOneDept(Integer rep_no) {
	return dao.findByPrimaryKey(rep_no);
}

public void deleteDept(Integer rep_no) {
	dao.delete(rep_no);
}

}
