<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.aut.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
    AutService autSvc = new AutService();
    List<AutVO> list = autSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有管理員資料 - listAllAut.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有管理員資料 - ListAllAut.jsp</h3>
		</td>
	</tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>管理員編號</th>
		<th>權限編號</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="autVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${autVO.adm_no}</td>
			<td>${autVO.id}</td>

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/aut/aut.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="adm_no" value="${autVO.adm_no}">
			    <input type="hidden" name="id" value="${autVO.id}">
			    <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
