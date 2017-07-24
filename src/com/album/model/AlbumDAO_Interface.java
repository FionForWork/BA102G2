package com.album.model;

import java.util.Date;
import java.util.List;

public interface AlbumDAO_Interface {
	
	public String createAlbum(AlbumVO album);
	public void deleteAlbum(String alb_no);
	public void updateAlbum(AlbumVO album);
	public AlbumVO findAlbumByPK(String alb_no);
	public List<AlbumVO> findAlbumsByMemNo(String mem_no);
	public List<AlbumVO> findAll();
}
