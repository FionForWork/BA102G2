package com.fun.model;

import java.util.List;


public class FunService {

	private FunDAO_Interface dao;
	
	public FunService(){
		
		dao=new FunDAO();
		
	}
	
	public FunVO addFun(String fun_no,String name){
		FunVO funVO=new FunVO();
		
		funVO.setFun_no(fun_no);
		funVO.setName(name);
		dao.insert(funVO);
		
		return funVO;
	}
	
	public void deleteFun(String fun_no) {
		dao.delete(fun_no);
	}
	
	public FunVO getOneFun(String fun_no) {
		return dao.findByPrimaryKey(fun_no);
	}
	
	public List<FunVO> getAll() {
		return dao.getAll();
	}
	
}
