package com.place.model;

import java.util.List;

public class PlaceService {
	private PlaceDAO_Interface dao;

	public PlaceService() {
		dao = new PlaceDAO();
	}

	public List<PlaceVO> getAll() {
		return dao.getAll();
	}

	public PlaceVO getOnePlace(String pla_no) {
		return dao.findByPrimaryKey(pla_no);
	}

	public void deletePlace(String pla_no) {
		dao.delete(pla_no);
	}
}
