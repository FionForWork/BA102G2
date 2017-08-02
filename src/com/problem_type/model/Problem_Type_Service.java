package com.problem_type.model;

import java.util.List;

import com.art_type.model.Art_TypeVO;

public class Problem_Type_Service {
	private Problem_Type_interface dao;
	public Problem_Type_Service (){
		
		dao= new Problem_TypeJNDIDAO();
	}
	
	
	public Problem_TypeVO addProblem_Type(Integer problem_type_no,String type){
		Problem_TypeVO problem_TypeVO=new Problem_TypeVO();
		problem_TypeVO.setProblem_type_no(problem_type_no);
		problem_TypeVO.setType(type);
		dao.insert(problem_TypeVO);
		return  problem_TypeVO;
		
	}




	public List<Problem_TypeVO> getAll() {
		return dao.getAll();
	}

	public Problem_TypeVO getOneProblem_Type(Integer problem_type_no) {
		return dao.findByPrimaryKey(problem_type_no);
	}

	public void deleteProblem_Type(Integer problem_type_no) {
		dao.delete(problem_type_no);
	}

}
