<%@ page import="java.util.*"%>
<%@ page import="com.problem.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%List<ProblemVO> problemlist = (List<ProblemVO>) session.getAttribute("problemlist"); %>  
   <%
   Problem_Service proSvc=new Problem_Service();
   List<ProblemVO> list=proSvc.getAll();
   pageContext.setAttribute("list", list);
 
   List<ProblemVO> list2=proSvc.getOneAll(10);
   pageContext.setAttribute("list2", list2);
   List<ProblemVO> list3=proSvc.getOneAll(20);
   pageContext.setAttribute("list3", list3);
   List<ProblemVO> list4=proSvc.getOneAll(30);
   pageContext.setAttribute("list4", list4);
   %>
 
<%@ include file="page/problem_header.file"%>
 <div class="col-md-8">
 <ul class="nav nav-tabs " style="padding-left: 8cm" >
    <li class="active"><a data-toggle="tab" href="#home">全部</a></li>
    <li><a data-toggle="tab" href="#menu1">帳號問題</a></li>
    <li><a data-toggle="tab" href="#menu2">商城問題</a></li>
    <li><a data-toggle="tab" href="#menu3">預約服務</a></li>
  </ul> 
  <div class="tab-content">
  <div id="home" class="tab-pane fade in active">
                    <div id="dashboard-content">
                    <c:forEach var="problemVO" items="${list}" varStatus="count">
                        	<div class="panel-group" id="accordion">
							<div class="panel " style="background-color: #f5f5f5;">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#${problemVO.prob_no }">${count.count}.${problemVO.content }</a>

								</h4>
							</div>

							<div id="${problemVO.prob_no }" class="panel-collapse collapse ">
								<div class="panel-body">
									<pre style="border:0px">${problemVO.reply }</pre>
								</div>
							</div>

						</div>
					</c:forEach>
</div>
</div>
 <div id="menu1" class="tab-pane fade ">
                    <div id="dashboard-content">
                    <c:forEach var="problemVO" items="${list2}" varStatus="count">
                        	<div class="panel-group" id="accordion">
							<div class="panel " style="background-color: #f5f5f5;">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#${(problemVO.prob_no)+100 }">${count.count}.${problemVO.content }</a>

								</h4>
							</div>

							<div id="${(problemVO.prob_no)+100 }" class="panel-collapse collapse ">
								<div class="panel-body">
									<pre style="border:0px">${problemVO.reply }</pre>
								</div>
							</div>

						</div>
					</c:forEach>
</div>
</div>
<div id="menu2" class="tab-pane fade">
                    <div id="dashboard-content">
                    <c:forEach var="problemVO" items="${list3}" varStatus="count">
                        	<div class="panel-group" id="accordion">
							<div class="panel " style="background-color: #f5f5f5;">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#${(problemVO.prob_no)+200 }">${count.count}.${problemVO.content }</a>

								</h4>
							</div>

							<div id="${(problemVO.prob_no)+200 }" class="panel-collapse collapse ">
								<div class="panel-body">
									<pre style="border:0px">${problemVO.reply }</pre>
								</div>
							</div>

						</div>
					</c:forEach>
</div>
</div>
<div id="menu3" class="tab-pane fade ">
                    <div id="dashboard-content">
                    <c:forEach var="problemVO" items="${list4}" varStatus="count">
                        	<div class="panel-group" id="accordion">
							<div class="panel " style="background-color: #f5f5f5;">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#${(problemVO.prob_no)+300 }">${count.count}.${problemVO.content }</a>

								</h4>
							</div>

							<div id="${(problemVO.prob_no)+300 }" class="panel-collapse collapse ">
								<div class="panel-body">
									<pre style="border:0px">${problemVO.reply }</pre>
								</div>
							</div>

						</div>
					</c:forEach>
</div>
</div>
 
 </div>
 </div>
 
		
<%@ include file="page/problem_footer.file"%>
