package com.tradition_type.model;

import java.util.List;

import com.problem_type.model.Problem_TypeVO;

public class Tradition_Type_Service {
private Tradition_Type_interface dao;
public Tradition_Type_Service(){
	dao=new Tradition_TypeJNDIDAO();
}

public Tradition_TypeVO addTradition_Type(Integer tra_type_no,String type){
	Tradition_TypeVO tradition_TypeVO=new Tradition_TypeVO();
	tradition_TypeVO.setTra_type_no(tra_type_no);
	tradition_TypeVO.setType(type);
	dao.insert(tradition_TypeVO);
	return tradition_TypeVO;
	
}




public List<Tradition_TypeVO> getAll() {
	return dao.getAll();
}

public Tradition_TypeVO getOneTradition_Type(Integer tra_type_no) {
	return dao.findByPrimaryKey(tra_type_no);
}

public void deleteTradition_Type(Integer tra_type_no) {
	dao.delete(tra_type_no);
}


}
