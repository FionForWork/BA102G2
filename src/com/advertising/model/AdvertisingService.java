package com.advertising.model;

import java.util.List;

public class AdvertisingService {
	private AdvertisingDAO_Interface dao;

	public AdvertisingService() {
		dao = new AdvertisingDAO();
	}

	public AdvertisingVO updateAdvertising(String adv_no, String com_no, java.sql.Timestamp startDay,
			java.sql.Timestamp endDay, Integer price, String text, byte[] img, byte[] vdo, String status) {

		AdvertisingVO advertisingVO = new AdvertisingVO();
		
		advertisingVO.setAdv_no(adv_no);
		advertisingVO.setCom_no(com_no);
		advertisingVO.setStartDay(startDay);
		advertisingVO.setEndDay(endDay);
		advertisingVO.setPrice(price);
		advertisingVO.setText(text);
		advertisingVO.setImg(img);
		advertisingVO.setVdo(vdo);		
		advertisingVO.setStatus(status);
		dao.update(advertisingVO);

		return advertisingVO;
	}
	
	public AdvertisingVO addAdvertising(String com_no, String title , java.sql.Timestamp startDay,
			java.sql.Timestamp endDay, Integer price, String text, byte[] img, byte[] vdo, String status) {

		AdvertisingVO advertisingVO = new AdvertisingVO();
		
		advertisingVO.setCom_no(com_no);
		advertisingVO.setTitle(title);
		advertisingVO.setStartDay(startDay);
		advertisingVO.setEndDay(endDay);
		advertisingVO.setPrice(price);
		advertisingVO.setText(text);
		advertisingVO.setImg(img);
		advertisingVO.setVdo(vdo);		
		advertisingVO.setStatus(status);
		dao.insert(advertisingVO);

		return advertisingVO;
	}

	public List<AdvertisingVO> getAll() {
		return dao.getAll();
	}

	public AdvertisingVO getOneAdvertising(String adv_no) {
		
		return dao.findByPrimaryKey(adv_no);
	}

	public void deleteAdvertising(String adv_no) {
		dao.delete(adv_no);
	}
	public List<AdvertisingVO> getAllUnverified(){
		return dao.getAllUnverified();
	}
	public List<AdvertisingVO> getOneAll(String com_no) {
		return dao.getOneAll(com_no);
	}
	public List<AdvertisingVO> getOneSatus() {
		return dao.getOneSatus();
	}
}
