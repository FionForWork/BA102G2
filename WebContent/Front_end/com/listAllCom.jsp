<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.com.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
    ComService comSvc = new ComService();
    List<ComVO> list = comSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>廠商資料 - listAllCom.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>廠商資料 - listAllCom.jsp</h3>
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
		<th>廠商編號</th>
		<th>廠商帳號</th>
		<th>廠商密碼</th>
		<th>廠商名稱</th>
		<th>廠商地址</th>
		<th>LON</th>
		<th>LAT</th>
		<th>介紹</th>
		<th>電話</th>
		<th>銀行帳戶</th>
		<th>LOGO</th>
		<th>狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="comVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${comVO.com_no}</td>
			<td>${comVO.id}</td>
			<td>${comVO.pwd}</td>
			<td>${comVO.name}</td>
			<td>${comVO.loc}</td>
			<td>${comVO.lon}</td>
			<td>${comVO.lat}</td>
			<td>${comVO.com_desc}</td>
			<td>${comVO.phone}</td>
			<td>${comVO.account}</td>
			<td><img src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${comVO.com_no}"/></td>
			<td>${comVO.status}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/com/com.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="com_no"value="${comVO.com_no}" >
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/com/com.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="com_no"value="${comVO.com_no}" >
			    <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
