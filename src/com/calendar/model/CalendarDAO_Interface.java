package com.calendar.model;

import java.util.List;

public interface CalendarDAO_Interface {
	
	void insert(CalendarVO calendarVO);
	void update(CalendarVO calendarVO);
	void delete(String cal_no);
	CalendarVO findByPK(String cal_no);
	List<CalendarVO> getAll();
	
}
