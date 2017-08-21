package com.tempcont.model;

import java.sql.Timestamp;
import java.util.List;

public class TempContService {

	private TempContDAO_Interface dao;
	
	public TempContService(){
		dao = new TempContDAO();
	}
	
	public TempContVO addTempCont(String temp_no, Timestamp upload_date, byte[] img, byte[] vdo){
		TempContVO tempCont = new TempContVO();
		tempCont.setTemp_no(temp_no);
		tempCont.setUpload_date(upload_date);
		tempCont.setImg(img);
		tempCont.setVdo(vdo);
		String tcont_no = dao.insertTempCont(tempCont);
		tempCont.setTcont_no(tcont_no);
		return tempCont;
	}
	
	public TempContVO updateTempCont(String tcont_no, String temp_no, Timestamp upload_date , byte[] img, byte[] vdo){
		TempContVO tempCont = new TempContVO();
		tempCont.setTcont_no(tcont_no);
		tempCont.setTemp_no(temp_no);
		tempCont.setUpload_date(upload_date);
		tempCont.setImg(img);
		tempCont.setVdo(vdo);
		dao.updateTempCont(tempCont);
		return tempCont;
	}
	
	public void deleteTempCont(String tcont_no){
		dao.deleteTempCont(tcont_no);
	}
	
	public TempContVO getOneTempCont(String tcont_no){
		return dao.findTempContByPK(tcont_no);
	}
	
	public List<TempContVO> getAllByTempNo(String temp_no){
		return dao.findTempContsByTempNo(temp_no);
	}
	
	public List<TempContVO> getAll(){
		return dao.findAll();
	} 
	public int countTempContsInSingleTemp(String temp_no){
		return dao.countTempContsInSingleTemp(temp_no);
	}
}
