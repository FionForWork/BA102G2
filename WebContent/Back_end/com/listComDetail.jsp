<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.com.model.*" %>
<%@ page import="com.serv.model.*" %>
<%@ page import="java.util.*" %>
<%
	ComService comSvc = new ComService();
	String com_no = request.getParameter("com_no");
	ComVO listComDeatil = comSvc.getOneCom(com_no);
	pageContext.setAttribute("listComDeatil", listComDeatil);
%>
<jsp:useBean id="servService" class="com.serv.model.ServService"/>
<jsp:useBean id="sortingHat" class="com.ssy.tools.SortingHat" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>She Said Yes!</title>
</head>
<style>
 	p { 
		line-height: 1.7em; 
		text-align: justify; 
		text-justify: ideographic; 
		margin: 0; 
		text-indent: 2em; 
		margin-after: 0.5em; 
		word-break: break-all; 
	}
</style>
<body>
	<div class="panel panel-default">
		<div class="panel-body row">
			<div class="col-md-12 col-lg-2">
				<img src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${listComDeatil.com_no}" style="margin-right:10px">
			</div>
			<div class="col-md-12 col-lg-10">
				<label>廠商密碼 :&nbsp;</label>${listComDeatil.pwd}<br>
				<label>廠商帳戶 :&nbsp;</label>${listComDeatil.account}<br>
				<label>廠商位置 :&nbsp;</label>${listComDeatil.loc}<br>
				<label>廠商描述 :&nbsp;</label>
				<p>${listComDeatil.com_desc}</p>
				<label>廠商服務類型 :&nbsp;</label>
				<c:set var="list" value="${servService.getComStype(listComDeatil.com_no)}" />
				<c:if test="${list.size() == 0}">
					廠商目前尚未新增任何服務內容
				</c:if>
				<c:forEach var="stype" items="${list}">
				${sortingHat.getServType(stype)}&nbsp;
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>