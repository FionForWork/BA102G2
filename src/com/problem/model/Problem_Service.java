package com.problem.model;

import java.util.List;

import com.forum_comment.model.Forum_CommentVO;

public class Problem_Service {
	private Problem_interface dao;
	public Problem_Service(){
		dao=new ProblemJNDIDAO();
	}
	public ProblemVO addProblem(Integer problem_type_no,String content,String reply){
		ProblemVO problemVO = new ProblemVO();
		problemVO.setProblem_type_no(problem_type_no);
		
		problemVO.setContent(content);
		problemVO.setReply(reply);
		dao.insert(problemVO);;
		return problemVO;
		
	}
	
	public ProblemVO updataProblem(Integer prob_no,Integer problem_type_no,String content,String reply){
		ProblemVO problemVO = new ProblemVO();
		problemVO.setProb_no(prob_no);
		problemVO.setProblem_type_no(problem_type_no);
		
		problemVO.setContent(content);
		problemVO.setReply(reply);
		dao.update(problemVO);
		return problemVO;
		
	}
	public List<ProblemVO> getAll() {
		return dao.getAll();
	}

	public ProblemVO getOneProblem(Integer prob_no) {
		return dao.findByPrimaryKey(prob_no);
	}

	public void deleteproblem(Integer prob_no) {
		dao.delete(prob_no);
	}

	
}
