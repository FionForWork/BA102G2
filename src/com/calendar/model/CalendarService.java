package com.calendar.model;

import java.sql.Timestamp;
import java.util.List;

public class CalendarService {
	
	private CalendarDAO_Interface dao;
	
	public CalendarService(){
		dao = new CalendarDAO();
	}
	
	public CalendarVO addCalendar(String cal_no, String com_no, String content, Timestamp start_time,Timestamp end_time){
		
		CalendarVO vo = new CalendarVO();
		vo.setCal_no(cal_no);
		vo.setCom_no(com_no);
		vo.setContent(content);
		vo.setStart_time(start_time);
		vo.setEnd_time(end_time);
		dao.insert(vo);
		
		return vo;
	}
	
	public CalendarVO updateCalendar(String cal_no,String content, Timestamp start_time,Timestamp end_time){
		
		CalendarVO vo = new CalendarVO();
		vo.setCal_no(cal_no);
		vo.setContent(content);
		vo.setStart_time(start_time);
		vo.setEnd_time(end_time);
		dao.update(vo);
		
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
}
