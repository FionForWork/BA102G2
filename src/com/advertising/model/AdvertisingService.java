package com.advertising.model;

import java.util.List;

public class AdvertisingService {
	private AdvertisingDAO_Interface dao;

	public AdvertisingService() {
		dao = new AdvertisingDAO();
	}

	public List<AdvertisingVO> getAll() {
		return dao.getAll();
	}

	public AdvertisingVO getOneDept(String adv_no) {
		return dao.findByPrimaryKey(adv_no);
	}

	public void deleteDept(String adv_no) {
		dao.delete(adv_no);
	}
}
