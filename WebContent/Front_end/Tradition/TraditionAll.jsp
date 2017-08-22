<%@ page import="java.util.*"%>
<%@ page import="com.tradition.model.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	List<TraditionVO> traditionlist = (List<TraditionVO>) session.getAttribute("traditionlist");
%>

<%
	Tradition_Service traSvc = new Tradition_Service();
	List<TraditionVO> list = traSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<%@ include file="page/tradition_header.file"%>
 <div class="col-md-8">

    <ul class="nav nav-tabs " style="padding-left: 8cm" >
    <li class="active"><a data-toggle="tab" href="#home">全部</a></li>
    <li><a data-toggle="tab" href="#menu1">訂婚流程</a></li>
    <li><a data-toggle="tab" href="#menu2">結婚流程</a></li>
    <li><a data-toggle="tab" href="#menu3">禁忌、歸寧</a></li>
    <li><a data-toggle="tab" href="#menu4">六禮、十二禮</a></li>
  </ul> 
  <div class="tab-content">
  <div id="home" class="tab-pane fade in active">
                    <div id="dashboard-content">
                    <c:forEach var="traditionVO" items="${list}" varStatus="count">
                        	<div class="panel-group" id="accordion">
							<div class="panel " style="background-color: #f5f5f5;">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#${traditionVO.tra_no }">${count.count}.${traditionVO.title }</a>

								</h4>
							</div>

							<div id="${traditionVO.tra_no }" class="panel-collapse collapse ">
								<div class="panel-body">
									<pre style="border:0px">${traditionVO.article }</pre>
								</div>
							</div>

						</div>
					</c:forEach>
</div>
</div>
 <div id="menu1" class="tab-pane fade">
                     <div id="dashboard-content">
                    <c:forEach var="traditionVO" items="${list}" >
                    <c:if test="${traditionVO.tra_type_no==10}">
                        	<div class="panel-group" id="accordion">
							<div class="panel " style="background-color: #f5f5f5;">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#${(traditionVO.tra_no)+100 }">${traditionVO.tra_order}.${traditionVO.title }</a>

								</h4>
							</div>

							<div id="${(traditionVO.tra_no)+100 }" class="panel-collapse collapse ">
								<div class="panel-body">
									<pre style="border:0px">${traditionVO.article }</pre>
								</div>
								
							</div>
							</div>
							</c:if>
								</c:forEach>

						</div>
						</div>
<div id="menu2" class="tab-pane fade">
                     <div id="dashboard-content">
                    <c:forEach var="traditionVO" items="${list}" >
                    <c:if test="${traditionVO.tra_type_no==20}">
                        	<div class="panel-group" id="accordion">
							<div class="panel " style="background-color: #f5f5f5;">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#${(traditionVO.tra_no)+200 }">${traditionVO.tra_order}.${traditionVO.title }</a>

								</h4>
							</div>

							<div id="${(traditionVO.tra_no)+200 }" class="panel-collapse collapse ">
								<div class="panel-body">
									<pre style="border:0px">${traditionVO.article }</pre>
								</div>
								
							</div>
							

						</div>
						</c:if>
								</c:forEach>
								</div>
								</div>
<div id="menu3" class="tab-pane fade">
                     <div id="dashboard-content">
                    <c:forEach var="traditionVO" items="${list}" >
                    <c:if test="${traditionVO.tra_type_no==30}">
                        	<div class="panel-group" id="accordion">
							<div class="panel " style="background-color: #f5f5f5;">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#${(traditionVO.tra_no)+300 }">${traditionVO.tra_order}.${traditionVO.title }</a>

								</h4>
							</div>

							<div id="${(traditionVO.tra_no)+300 }" class="panel-collapse collapse ">
								<div class="panel-body">
									<pre style="border:0px">${traditionVO.article }</pre>
								</div>
								
							</div>
							

						</div>
						</c:if>
								</c:forEach>
								</div>
								</div>
<div id="menu4" class="tab-pane fade">
                     <div id="dashboard-content">
                    <c:forEach var="traditionVO" items="${list}">
                    <c:if test="${traditionVO.tra_type_no==40}">
                        	<div class="panel-group" id="accordion">
							<div class="panel " style="background-color: #f5f5f5;">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#${(traditionVO.tra_no)+400 }">${traditionVO.tra_order}.${traditionVO.title }</a>

								</h4>
							</div>

							<div id="${(traditionVO.tra_no)+400 }" class="panel-collapse collapse ">
								<div class="panel-body">
									<pre style="border:0px">${traditionVO.article }</pre>
								</div>
								
							</div>
							

						</div>
						</c:if>
								</c:forEach>
								</div>
								</div>
						
					


</div>


	<%@ include file="page/tradition_footer.file"%>