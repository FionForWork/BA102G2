<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.temp.model.*" %>

<jsp:useBean id="tempContSvc" scope="page"
	class="com.tempcont.model.TempContService"></jsp:useBean>
<%-- <jsp:useBean id="tempSvc" scope="page" --%>
<%-- 	class="com.temp.model.TempService"></jsp:useBean> --%>

<%
	//String temp_no = (String) request.getAttribute("temp_no");
	 	String temp_no = "0017";
	 	session.setAttribute("temp_no",temp_no);
	TempService tempSvc = new TempService();
	TempVO temp = tempSvc.getOneTemp(temp_no);
	request.setAttribute("temp", temp);
%>

<%@ include file="page/photo_header.file"%>

<div class="col-xs-12 col-sm-9 ">	

	<div class="jumbotron">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div class="text-center">
					<h2>${temp.name}</h2>
					<br>
					<h4>建立日期 : ${temp.create_date.toString().substring(0,10)}</h4>
					<h4>可挑選數量 : ${temp.available}</h4>
					<h4>目前狀態 : ${temp.status}</h4>
				</div>
			</div>
		</div>
	</div>
	
	<c:forEach var="tempContVO" items="${tempContSvc.getAllByTempNo(temp_no)}" varStatus="s">
		<c:if test="${(s.count % 4) == 1}">
			<div class="row">
		</c:if>
		
		<div class="col-md-3 col-sm-3 col-xs-6">
			<div class="image">
				<a
					href="<%=request.getContextPath()%>/ShowPictureServletDAO?tcont_no=${tempContVO.tcont_no }"
					data-caption="Image caption" target="_blank"> 
					<img class="img-responsive img-thumbnail"
					src="<%=request.getContextPath()%>/ShowPictureServletDAO?tcont_no=${tempContVO.tcont_no }" />
				</a>
			</div>
		</div>
		<c:if test="${(s.count % 4) == 0}">
</div>
</c:if>
</c:forEach>
<br>
</div>

<%@ include file="page/album_footer.file"%>