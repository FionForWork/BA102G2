package com.reservation.model;

import java.util.List;

public interface ReservationDAO_Interface {
	void insert(ReservationVO reservationVO);
	void update(ReservationVO reservationVO);
	void delete(String res_no);
	ReservationVO findByPK(String res_no);
	List<ReservationVO> getAll();
}
