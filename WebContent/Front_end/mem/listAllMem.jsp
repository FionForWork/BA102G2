<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
    MemService memSvc = new MemService();
    List<MemVO> list = memSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>會員資料 - listAllMem.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有會員資料 - ListAllMem.jsp</h3>
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
		<th>會員編號</th>
		<th>會員帳號</th>
		<th>會員密碼</th>
		<th>會員姓名</th>
		<th>會員性別</th>
		<th>會員生日</th>
		<th>會員電話</th>
		<th>電子郵件</th>
		<th>銀行帳戶</th>
		<th>照片</th>
		<th>被檢舉次數</th>
		<th>狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${memVO.mem_no}</td>
			<td>${memVO.id}</td>
			<td>${memVO.pwd}</td>
			<td>${memVO.name}</td>
			<td>${memVO.sex}</td>
			<td>${memVO.bday}</td>
			<td>${memVO.phone}</td>
			<td>${memVO.email}</td>
			<td>${memVO.account}</td>
			<td><img src="<%=request.getContextPath()%>/ShowPictureServletDAO?mem_no=${memVO.mem_no }" /></td>
			<td>${memVO.report}</td>
			<td>${memVO.status}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="mem_no"value="${memVO.mem_no}" >
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="mem_no" value="${memVO.mem_no}">
			    <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
