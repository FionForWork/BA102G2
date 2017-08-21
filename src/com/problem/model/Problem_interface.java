package com.problem.model;

import java.util.List;



public interface Problem_interface {
	public void insert(ProblemVO problemVO);
    public void update(ProblemVO problemVO);
    public void delete(Integer prob_no);
    public ProblemVO findByPrimaryKey(Integer prob_no);
    public List<ProblemVO> getAll();
    public List<ProblemVO> getOneAll(Integer problem_type_no);
}
