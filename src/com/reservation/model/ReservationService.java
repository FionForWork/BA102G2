package com.reservation.model;

import java.sql.Timestamp;
import java.util.List;

public class ReservationService {
	
	private ReservationDAO_Interface dao;
	
	public ReservationService(){
		dao = new ReservationDAO();
	}
	
	public ReservationVO addReservation (String res_no, String mem_no, String com_no, Timestamp res_date){
		
		ReservationVO reservationVO = new ReservationVO();
		reservationVO.setRes_no(res_no);
		reservationVO.setMem_no(mem_no);
		reservationVO.setCom_no(com_no);
		reservationVO.setRes_date(res_date);
		dao.update(reservationVO);
		
		return reservationVO;
	}
	
	public void deleteReservation(String res_no){
		
		dao.delete(res_no);
	}
	
	public ReservationVO findOneReservation(String res_no){
		
		ReservationVO vo = dao.findByPK(res_no);
		return vo;
	}
	
	public List<ReservationVO> getAllReservation(){
		return dao.getAll();
	}
}
