<%@ page import="java.util.*"%>
<%@ page import="com.problem.model.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="page/problem_header_update.file"%>
<%
ProblemVO problemVO = (ProblemVO) request.getAttribute("problemVO"); 


%>
 <div class="panel-heading">
                        <h4>新增Q&A </h4>
                    </div>
<div class="container-fluid">
  <h5>&nbsp;&nbsp;</h5>
  
  <form METHOD="post" ACTION="<%=request.getContextPath()%>/Problem/Problem.do" name="form1">
  
<jsp:useBean id="problem_type_Svc" scope="page" class="com.problem_type.model.Problem_Type_Service" />
  <div class="form-group">
      <label for="sel1">Q&A類別</label>
      <select class="form-control" id="sel1" name="problem_type_no">
        <c:forEach var="problem_typeVO" items="${problem_type_Svc.all}">
				<option  value="${problem_typeVO.problem_type_no}" >
				${problem_typeVO.type}
			</c:forEach>
      </select>
    </div>
  
    <div class="form-group">
      <label for="inputdefault">Q&A問題</label>
      <input class="form-control" id="inputdefault" type="text" name="content"
      value="<%= (problemVO==null)? " " : problemVO.getContent()%>">
    </div>
    
    
    <div class="form-group">
      <label for="inputlg">Q&A回答</label>
      <textarea class="form-control" id="comment"  name="reply"
      value="<%= (problemVO==null)? " " : problemVO.getReply()%>"></textarea>
    </div>
   
    
   
   
 

  <div class="modal-footer">
                                              
   <input type="hidden" name="action" value="insert">
                                        
   <input type="submit" class="btn btn-info" data-dismiss="modal" value="新增">

  <input type="submit" class="btn btn-danger" data-dismiss="modal" href="<%=request.getContextPath()%>/Back_end/problem/Problemall.jsp" value="返回">


    </div>
     </form>
</div>











<%@ include file="page/problem_footer_update.file"%>