package com.reservation.model;

import java.util.List;

import com.calendar.model.CalendarVO;

public interface ReservationDAO_Interface {
	void insert(ReservationVO reservationVO,CalendarVO calendarVO);
	void update(ReservationVO reservationVO);
	void updateScore(ReservationVO reservationVO);
	void delete(String res_no);
	ReservationVO findByPK(String res_no);
	List<ReservationVO> getAll();
	List<ReservationVO> getMemRes(String mem_no);
	List<ReservationVO> getComRes(String com_no);
}
