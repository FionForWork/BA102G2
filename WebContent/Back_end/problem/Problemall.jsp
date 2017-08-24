<%@page import="com.problem_type.model.Problem_Type_Service"%>
<%@page import="com.problem_type.model.Problem_TypeVO"%>
<%@ page import="java.util.*"%>
<%@ page import="com.problem.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
   <%
   Problem_Service proSvc=new Problem_Service();
   List<ProblemVO> list=proSvc.getAll();
   pageContext.setAttribute("list", list);
   
   
   
   %>
   <jsp:useBean id="pro_type_Svc" scope="page" class="com.problem_type.model.Problem_Type_Service" />
 
<%@ include file="page/problem_header.file"%>
<c:forEach var="ProblemVO" items="${list}" >
  <tbody>
                                <tr>
                                    <td class="text-center">${ProblemVO.prob_no }</td>
                                     <td class="text-center">${pro_type_Svc.getOneProblem_Type(ProblemVO.problem_type_no).type}</td>
                                  
                                    <td>${ProblemVO.content }</td>
                                    <td>${ProblemVO.reply }</td>
                                    
<!-- ===================================================================================================== -->
									 <td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Problem/Problem.do">
                                    <input type="submit" class="btn btn-info " value="修改" >
                                    
                                    <input type="hidden" name="prob_no" value="${ProblemVO.prob_no}">
			     					<input type="hidden" name="action"	value="getOne_For_Update"></FORM></td>

  
 <!-- ====================================================================================================== -->
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Problem/Problem.do">
                                    <td><input type="submit" class="btn btn-default btn-danger btn-primary" value="移除">
                                    	<input type="hidden" name="action" value="delete">
                                    	<input type="hidden" name="prob_no" value="${ProblemVO.prob_no}">
                                    </td></FORM>
                                </tr>
                                </tbody>
 </c:forEach>
                         




<%@ include file="page/problem_footer.file"%>