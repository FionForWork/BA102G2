package com.reservation.model;

import java.sql.Timestamp;
import java.util.List;

import com.calendar.model.CalendarVO;

public class ReservationService {
	
	private ReservationDAO_Interface dao;
	
	public ReservationService(){
		dao = new ReservationDAO();
	}
	
	public ReservationVO addReservation (String mem_no, String com_no, Timestamp res_date,
			Timestamp serv_date, String serv_no, String stype_no, Integer price, CalendarVO calendarVO){
		
		ReservationVO reservationVO = new ReservationVO();
		reservationVO.setMem_no(mem_no);
		reservationVO.setCom_no(com_no);
		reservationVO.setRes_date(res_date);
		reservationVO.setServ_date(serv_date);
		reservationVO.setServ_no(serv_no);
		reservationVO.setStype_no(stype_no);
		reservationVO.setPrice(price);
		dao.insert(reservationVO, calendarVO);
		
		return reservationVO;
	}
	
	public void updateStatus(String status,String res_no){
		ReservationVO reservationVO = new ReservationVO();
		reservationVO.setRes_no(res_no);
		reservationVO.setStatus(status);
		
		dao.update(reservationVO);
	}
	
	public void updateScore(String status,Integer score,String res_no){
		ReservationVO reservationVO = new ReservationVO();
		reservationVO.setRes_no(res_no);
		reservationVO.setScore(score);
		reservationVO.setStatus(status);
		
		dao.updateScore(reservationVO);
	}
	
	public void deleteReservation(String res_no){
		
		dao.delete(res_no);
	}
	
	public ReservationVO getOneReservation(String res_no){
		
		ReservationVO vo = dao.findByPK(res_no);
		return vo;
	}
	
	public List<ReservationVO> getAllReservation(){
		return dao.getAll();
	}
	
	public List<ReservationVO> getMemRes(String mem_no){
		return dao.getMemRes(mem_no);
	}
	
	public List<ReservationVO> getComRes(String com_no){
		return dao.getComRes(com_no);
	}
}
