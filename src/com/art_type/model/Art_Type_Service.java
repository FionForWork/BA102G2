package com.art_type.model;

import java.util.List;



public class Art_Type_Service {
private Art_TypeDAO_interface dao;

public Art_Type_Service(){
	dao=new Art_TypeJNDIDAO();
}

public Art_TypeVO addArt_Type(Integer art_type_no,String type){
	Art_TypeVO art_TypeVO=new Art_TypeVO();
	art_TypeVO.setArt_type_no(art_type_no);
	art_TypeVO.setType(type);
	dao.insert(art_TypeVO);
	return  art_TypeVO;
	
}




public List<Art_TypeVO> getAll() {
	return dao.getAll();
}

public Art_TypeVO getOneDept(Integer art_type_no) {
	return dao.findByPrimaryKey(art_type_no);
}

public void deleteDept(Integer art_type_no) {
	dao.delete(art_type_no);
}
}
