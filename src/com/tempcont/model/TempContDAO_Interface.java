package com.tempcont.model;

import java.util.List;

public interface TempContDAO_Interface {

	public String insertTempCont(TempContVO tempcont);
	public void deleteTempCont(String tcont_no);
	public void updateTempCont(TempContVO tempcont);
	public TempContVO findTempContByPK(String tcont_no);
	public List<TempContVO> findTempContsByTempNo(String temp_no);
	public List<TempContVO> findAll();
	public int countTempContsInSingleTemp(String temp_no);
}
