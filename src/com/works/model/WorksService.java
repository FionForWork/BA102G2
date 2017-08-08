package com.works.model;

import java.sql.Timestamp;
import java.util.List;

public class WorksService {

	private WorksDAO_Interface dao;
	
	public WorksService(){
		dao = new WorksDAO();
	}
	
	public WorksVO addWorks(String com_no,String name, String works_desc,byte[] img, byte[] vdo, Timestamp upload_date ){
		WorksVO works = new WorksVO();
		works.setCom_no(com_no);
		works.setName(name);
		works.setImg(img);
		works.setVdo(vdo);
		works.setUpload_date(upload_date);
		works.setWorks_desc(works_desc);
		String works_no = dao.insertWorks(works);
		works.setWorks_no(works_no);
		return works;
	}
	
	public WorksVO updateWorks(String works_no, String com_no,String name, String works_desc,byte[] img, byte[] vdo, Timestamp upload_date ){
		WorksVO works = new WorksVO();
		works.setWorks_no(works_no);
		works.setCom_no(com_no);
		works.setName(name);
		works.setImg(img);
		works.setVdo(vdo);
		works.setUpload_date(upload_date);
		works.setWorks_desc(works_desc);
		dao.updateWorks(works);
		return works;
	}
	
	public void deleteWorks(String works_no){
		dao.deleteWorks(works_no);
	}
	
	public WorksVO getOneWork(String works_no){
		return dao.findWorksByPK(works_no);
	}
	
	public List<WorksVO> getAllByComNo(String com_no){
		return dao.findWorksByComNo(com_no);
	}
	
	public List<WorksVO> getAll(){
		return dao.findAll();
	}
	public int CountWorksInOneComNo(String com_no){
		return dao.countWorksInOneComNo(com_no);
	}
}
