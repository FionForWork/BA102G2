<%@ page import="java.util.*"%>
<%@ page import="com.problem.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
ProblemVO problemVO = (ProblemVO) request.getAttribute("problemVO"); 

%>
<%@ include file="page/problem_header_update.file"%>

                  <div class="panel-heading">
                        <h4>修改Q&A</h4>
                  </div>
<div class="container-fluid">
  <h5>&nbsp;&nbsp;</h5>
  
  <Form METHOD="post" ACTION="Problem.do" name="form1">
  <div class="form-group">
      <label for="inputdefault">Q&A項次</label>
      <br>
      <a ><%=problemVO.getProb_no() %></a>
      
    </div>
<jsp:useBean id="problem_type_Svc" scope="page" class="com.problem_type.model.Problem_Type_Service" />

  <div class="form-group">
      <label for="sel1">Q&A類別</label>
      <select class="form-control" id="sel1" name="problem_type_no" >
     
       
        
        <c:forEach var="problem_typeVO" items="${problem_type_Svc.all}">
				<option  value="${problem_typeVO.problem_type_no}" ${(problem_typeVO.problem_type_no==problemVO.problem_type_no)?'selected':'' }>
				${problem_typeVO.type}
			</c:forEach>

        
      </select>
    </div>
  
    <div class="form-group">
      <label for="inputdefault">Q&A問題</label>
      <input class="form-control" id="inputdefault" name="content" type="text" value="<%=problemVO.getContent() %>">
    </div>
    <div class="form-group">
      <label for="inputlg">Q&A回答</label>
     
      <textarea class="form-control" rows="5" id="comment" name="reply"   value=""><%=problemVO.getReply()%></textarea>
    </div>
   
    
 
   
 

  <div class="modal-footer">
    <input type="hidden" name="action" value="update">  
    <input type="hidden" name="prob_no" value="<%=problemVO.getProb_no()%>">                                        
   <input type="submit" class="btn btn-info" data-dismiss="modal" value="存檔">

  <input type="submit" class="btn btn-danger" data-dismiss="modal"  href="/problem/Problemall.jsp" value="返回">
    </div>
    </Form> 
</div>


<%@ include file="page/problem_footer_update.file"%>