package com.content.model;

import java.sql.Timestamp;
import java.util.List;

public class ContentService {

	private ContentDAO_Interface dao;
	
	public ContentService(){
		dao = new ContentDAO();
	}
	
	public ContentVO addContent(String alb_no, Timestamp upload_date, byte[] img, byte[] vdo){
		ContentVO content = new ContentVO();
		content.setAlb_no(alb_no);
		content.setImg(img);
		content.setVdo(vdo);
		content.setUpload_date(upload_date);
		String cont_no = dao.insertContent(content);
		content.setCont_no(cont_no);
		return content;
	}
	
	public ContentVO updateContent(String cont_no, String alb_no, Timestamp upload_date, byte[] img, byte[] vdo){
		ContentVO content = new ContentVO();
		content.setCont_no(cont_no);
		content.setAlb_no(alb_no);
		content.setUpload_date(upload_date);
		content.setImg(img);
		content.setVdo(vdo);
		dao.updateContent(content);
		return content;

	}
	
	public void deleteContent(String cont_no){
		dao.deleteContent(cont_no);
	}
	
	public ContentVO getOneContent(String cont_no){
		return dao.findContentByPK(cont_no);
	}
	
	public List<ContentVO> getAllByAlbNo(String alb_no){
		return dao.findContentsByAlbNo(alb_no);
	}
	
	public List<ContentVO> getAll(){
		return dao.findAll();
	}
	
	public int countContentsInSingleAlbum(String alb_no){
		return dao.countContentsInSingleAlbum(alb_no);
	}
}
