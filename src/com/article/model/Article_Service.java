package com.article.model;

import java.sql.Date;
import java.util.List;



public class Article_Service {
private ArticleDAO_interfacce dao;
public Article_Service(){
	dao=new ArticleJNDIDAO();
}

public ArticleVO addArt(Integer poster_no,Integer art_type_no,String title,
		String content,Date art_date){
	ArticleVO articleVO=new ArticleVO();
	articleVO.setPoster_no(poster_no);
	articleVO.setArt_type_no(art_type_no);
	articleVO.setTitle(title);
	articleVO.setContent(content);
	articleVO.setArt_date(art_date);
	dao.insert(articleVO);
	return articleVO;
	
	
}
public ArticleVO updateArt(Integer art_no, Integer poster_no,Integer art_type_no,String title,
		String content,Date art_date){
	ArticleVO articleVO=new ArticleVO();
	articleVO.setArt_no(art_no);
	articleVO.setPoster_no(poster_no);
	articleVO.setArt_type_no(art_type_no);
	articleVO.setTitle(title);
	articleVO.setContent(content);
	articleVO.setArt_date(art_date);
	dao.update(articleVO);
	return articleVO;
	
	
}


public List<ArticleVO> getAll() {
	return dao.getAll();
}

public ArticleVO getOneDept(Integer art_no) {
	return dao.findByPrimaryKey(art_no);
}

public void deleteDept(Integer art_no) {
	dao.delete(art_no);
}




}
