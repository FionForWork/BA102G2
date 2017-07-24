package com.temp.model;

import java.util.List;

public interface TempDAO_Interface {

	public String createTemp(TempVO temp);
	public void deleteTemp(String temp_no);
	public void updateTemp(TempVO temp);
	public TempVO findTempByPK(String temp_no);
	public List<TempVO> findTempsByMemNo(String mem_no);
	public List<TempVO> findTempsByComNo(String com_no);
	public List<TempVO> findAll();
	
}
