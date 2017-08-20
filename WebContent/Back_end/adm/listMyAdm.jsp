<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.adm.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%
Object admVO = session.getAttribute("admVO");     
%>
<%@ include file="/Back_end/adm/page/backHeader.file"%>

<title>員工資料 - listOneEmp.jsp</title>

<div id="content">

	 
		<td>
		<h3>我的資料</h3>
				<a href="<%=request.getContextPath()%>/Back_end/adm/listAllAdm.jsp
">回首頁</a>
		</td>
	

<table class="table table-striped" >
	<tr>
		<th>管理員編號</th>
		<th>管理員帳號</th>

		<th>管理員姓名</th>
		<th>管理員職位</th>
		<th>管理員狀態</th>

	</tr>
	<tr align='center' valign='middle'>
			<td>${admVO.adm_no}</td>
			<td>${admVO.id}</td>

			<td>${admVO.name}</td>
			<td>${admVO.job}</td>
			<td>${admVO.status}</td>

			
		</tr>
</table>
	<a href="<%=request.getContextPath()%>/Back_end/adm/updatePwd.jsp
">修改密碼</a>
</div>
<%@ include file="/Back_end/pages/backFooter.file"%>