package com.problem.model;

import java.util.List;

import com.forum_comment.model.Forum_CommentVO;

public class Problem_Service {
	private Problem_interface dao;
	public Problem_Service(){
		dao=new ProblemJNDIDAO();
	}
	public ProblemVO addProblem(Integer type,String title,String content,String reply){
		ProblemVO problemVO = new ProblemVO();
		problemVO.setType(type);
		problemVO.setTitle(title);
		problemVO.setContent(content);
		problemVO.setReply(reply);
		dao.insert(problemVO);;
		return problemVO;
		
	}
	
	public ProblemVO updataProblem(Integer prob_no,Integer type,String title,String content,String reply){
		ProblemVO problemVO = new ProblemVO();
		problemVO.setProb_no(prob_no);
		problemVO.setType(type);
		problemVO.setTitle(title);
		problemVO.setContent(content);
		problemVO.setReply(reply);
		dao.update(problemVO);
		return problemVO;
		
	}
	public List<ProblemVO> getAll() {
		return dao.getAll();
	}

	public ProblemVO getOneDept(Integer prob_no) {
		return dao.findByPrimaryKey(prob_no);
	}

	public void deleteDept(Integer prob_no) {
		dao.delete(prob_no);
	}

	
}
