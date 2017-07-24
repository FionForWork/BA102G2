package com.album.model;

import java.sql.Timestamp;
import java.util.List;

public class AlbumService {

	private AlbumDAO_Interface dao;
	
	public AlbumService(){
		dao = new AlbumDAO();
	}
	
	public AlbumVO addAlbum(String mem_no,String name, byte[] cover, Timestamp create_date){
		AlbumVO album = new AlbumVO();
		album.setMem_no(mem_no);
		album.setName(name);
		album.setCover(cover);
		album.setCreate_date(create_date);
		String alb_no = dao.createAlbum(album);
		album.setAlb_no(alb_no);
		return album;
		
	}
	
	public AlbumVO updateAlbum(String alb_no,String mem_no,String name,byte[] cover, Timestamp create_date){
		AlbumVO album = new AlbumVO();
		album.setAlb_no(alb_no);
		album.setMem_no(mem_no);
		album.setName(name);
		album.setCover(cover);
		album.setCreate_date(create_date);
		dao.updateAlbum(album);
		return album;		
	}
	
	public void deleteAlbum(String alb_no){
		dao.deleteAlbum(alb_no);
	}
	
	public AlbumVO getOneAlbum(String alb_no){
		return dao.findAlbumByPK(alb_no);
	}
	
	public List<AlbumVO> getAllByMemNo(String mem_no){
		return dao.findAlbumsByMemNo(mem_no);
	}
	
	public List<AlbumVO> getAll(){
		return dao.findAll();
	}
}
