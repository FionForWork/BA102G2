package com.temp.model;

import java.sql.Timestamp;
import java.util.List;

public class TempService {

	private TempDAO_Interface dao;
	
	public TempService(){
		dao = new TempDAO();
	}
	
	public TempVO addTemp(String com_no,String mem_no,String name,Timestamp create_date,Integer available,String status){
		TempVO temp = new TempVO();
		temp.setCom_no(com_no);
		temp.setMem_no(mem_no);
		temp.setName(name);
		temp.setCreate_date(create_date);
		temp.setAvailable(available);
		temp.setStatus(status);
		String temp_no = dao.createTemp(temp);
		temp.setTemp_no(temp_no);
		return temp;
	}
	
	public TempVO updateTemp(String temp_no,String com_no,String mem_no,String name,Timestamp create_date,Integer available,String status){
		TempVO temp = new TempVO();
		temp.setTemp_no(temp_no);
		temp.setCom_no(com_no);
		temp.setMem_no(mem_no);
		temp.setName(name);
		temp.setCreate_date(create_date);
		temp.setAvailable(available);
		temp.setStatus(status);
		dao.updateTemp(temp);
		return temp;
	}
	
	public void deleteTemp(String temp_no){
		dao.deleteTemp(temp_no);
	}
	
	public TempVO getOneTemp(String temp_no){
		return dao.findTempByPK(temp_no);
	}
	
	public List<TempVO> getAllByMemNo(String mem_no){
		return dao.findTempsByMemNo(mem_no);
	}
	
	public List<TempVO> getAllByComNo(String com_no){
		return dao.findTempsByComNo(com_no);
	}
	
	public List<TempVO> getAll(){
		return dao.findAll();
	} 

}
