<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.adm.model.*"%>
<%
AdmVO admVO = (AdmVO) request.getAttribute("admVO"); 
%>
<%@ include file="/Back_end/pages/backHeader.file"%>

<title>員工資料 - listOneEmp.jsp</title>

<div id="content">

	
		<td>
		<h3>管理員資料 - ListOneAmd.jsp</h3>
				<a href="listAllAdm.jsp">回首頁</a>
		</td>
	

<table class="table table-striped" >
	<tr>
		<th>管理員編號</th>
		<th>管理員帳號</th>
		<th>管理員密碼</th>
		<th>管理員姓名</th>
		<th>管理員職位</th>
		<th>管理員狀態</th>

	</tr>
	<tr align='center' valign='middle'>
			<td>${admVO.adm_no}</td>
			<td>${admVO.id}</td>
			<td>${admVO.pwd}</td>
			<td>${admVO.name}</td>
			<td>${admVO.job}</td>
			<td>${admVO.status}</td>

			
		</tr>
</table>
</div>
<%@ include file="/Back_end/pages/backFooter.file"%>