<%@ page import="java.util.*"%>
<%@ page import="com.problem.model.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
   <%
   Problem_Service proSvc=new Problem_Service();
   List<ProblemVO> list=proSvc.getAll();
   pageContext.setAttribute("list", list);
   %>
 
<%@ include file="page/problem_header.file"%>
<c:forEach var="ProblemVO" items="${list}" >
  <tbody>
                                <tr>
                                    <td class="text-center">${ProblemVO.prob_no }</td>
                                    <td class="text-center">${ProblemVO.type }</td>
                                    <td>${ProblemVO.content }</td>
                                    <td>${ProblemVO.reply }</td>
                                    
<!-- ===================================================================================================== -->
									
                                    <td><button type="button" class="btn btn-info " data-toggle="modal" data-target="#myModal">修改</button>
                                    
                                    </td>

  <div class="modal fade" id="myModal" role="dialog">
   <div class="modal-dialog">
                                        
     <div class="modal-content">
   <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal">&times;</button>
     <h4 class="modal-title">修改Q&A</h4>
      </div>

<div class="modal-body">
                                              
<div class="container-fluid">
   
 <Form>
  
   
  
     <div class="form-group">
      <label for="sel1">Q&A類別</label>
      <select class="form-control" id="sel1">
        <option>10</option>
        <option>20</option>
        <option>30</option>
        <option>40</option>
      </select>
    </div>


    <div class="form-group">
      <label for="inputdefault">Q&A問題</label>
     <input class="form-control" id="inputdefault" type="text">
    </div>
    <div class="form-group">
      <label for="inputlg">Q&A回答</label>
      <textarea class="form-control" rows="5" id="comment"></textarea>
    </div>
    
   
   
    
  </Form>
</div>
</div>

   <div class="modal-footer">
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/problem/problem.do">                                          
   <button type="button" class="btn btn-default" data-dismiss="modal">存檔</button>
	<input type="hidden" name="empno" value="${problemVO.prob_no}">
	<input type="hidden" name="action"	value="getOne_For_Update"></FORM>
	
  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    </div>
  </div>
                                          
 </div>
 </div>
 <!-- ====================================================================================================== -->

                                    <td><button type="submit" class="btn btn-default btn-danger btn-primary">移除 </button>
                                    </td>
                                </tr>
                                </tbody>
 </c:forEach>
                         




<%@ include file="page/problem_footer.file"%>