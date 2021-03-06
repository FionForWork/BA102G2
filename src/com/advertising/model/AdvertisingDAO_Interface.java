package com.advertising.model;

import java.util.List;

public interface AdvertisingDAO_Interface {
	public void insert(AdvertisingVO advertisingVO);
	public void update(AdvertisingVO advertisingVO);
	public void delete(String adv_no);
	public AdvertisingVO findByPrimaryKey(String adv_no);
	public List<AdvertisingVO> getAll();
	public List<AdvertisingVO> getAllUnverified();
	public List<AdvertisingVO> getOneAll(String com_no);
	public List<AdvertisingVO> getOneSatus();
	public List<AdvertisingVO> getAllByStatus(String status);
	public List<AdvertisingVO> getAllByStatus(String status1,String status2);
	
}
