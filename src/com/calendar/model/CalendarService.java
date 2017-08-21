package com.calendar.model;

import java.sql.Timestamp;
import java.util.List;

public class CalendarService {
	
	private CalendarDAO_Interface dao;
	
	public CalendarService(){
		dao = new CalendarDAO();
	}
	
	public CalendarVO addCalendar(String com_no, String content, Timestamp cal_date, String status){
		
		CalendarVO vo = new CalendarVO();
		vo.setCom_no(com_no);
		vo.setContent(content);
		vo.setCal_date(cal_date);
		vo.setStatus(status);
		dao.insert(vo);
		
		return vo;
	}
	
	public CalendarVO updateDate(String cal_no, Timestamp cal_date){
		
		CalendarVO vo = new CalendarVO();
		vo.setCal_no(cal_no);
		vo.setCal_date(cal_date);
		dao.updateDate(vo);
		
		return vo;
	}
	
	public void deleteCalendar(String cal_no){
		dao.delete(cal_no);
	}
	
	public CalendarVO getOneCalendar(String cal_no){
		return dao.findByPK(cal_no);
	}
	
	public List<CalendarVO> getAllCalendar(){
		return dao.getAll();
	}
	
	public List<CalendarVO> getMonthCalendar(int year, int month, int dayNum, String com_no){
		return dao.getMonthCal(year, month, dayNum, com_no);
	}
	
	public CalendarVO checkForRes(String com_no, String cal_date){
		return dao.findByNoandDate(com_no, cal_date);
	}
}
