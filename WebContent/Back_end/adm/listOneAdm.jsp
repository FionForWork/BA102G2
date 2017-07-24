<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.adm.model.*"%>
<%
AdmVO admVO = (AdmVO) request.getAttribute("admVO"); 
%>
<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>管理員資料 - ListOneAmd.jsp</h3>
				<a href="listAllAdm.jsp">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
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

</body>
</html>
