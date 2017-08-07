package com.problem.model;

public class ProblemVO {
private Integer prob_no;
private Integer problem_type_no;

private String content;
private String reply;


public Integer getProb_no() {
	return prob_no;
}
public void setProb_no(Integer prob_no) {
	this.prob_no = prob_no;
}
public Integer getProblem_type_no() {
	return problem_type_no;
}
public void setProblem_type_no(Integer problem_type_no) {
	this.problem_type_no = problem_type_no;
}

public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getReply() {
	return reply;
}
public void setReply(String reply) {
	this.reply = reply;
}
}
