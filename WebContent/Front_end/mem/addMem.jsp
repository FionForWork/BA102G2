<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
MemVO memVO = (MemVO) request.getAttribute("memVO");
%>

<html>
<head>
<title>註冊會員</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>註冊會員 - </h3>

		</td>
		<td>
		   <a href="select_page.jsp">回首頁</a>
	  
	</tr>
</table>

<h3>資料員工:</h3>
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

<FORM METHOD="post" ACTION="mem.do" name="form1">
<table border="0">

	<tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="id" size="45" 
			value="<%= (memVO==null)? "lf21@gmail.com" : memVO.getId()%>" /></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="password" name="pwd" size="45"
			value="<%= (memVO==null)? "asdqqw" : memVO.getPwd()%>" /></td>
	</tr>
	<tr>
		<td>姓名:</td>
		<td><input type="TEXT" name="name" size="45" 
			value="<%= (memVO==null)? "lf21@gmail.com" : memVO.getName()%>" /></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td><input type="radio" name="sex" size="45" value="<%= (memVO==null)? "女" : memVO.getSex()%>" />女<br>
		<input type="radio" name="sex" size="45" value="<%= (memVO==null)? "男" : memVO.getSex()%>" />男</td>
	</tr>
	
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>生日:</td>
		<td bgcolor="#CCCCFF">
		    <input type="date"
			 name="bday" value="<%= (memVO==null)? date_SQL : memVO.getBday()%>">
		</td>
	</tr>
	
	<tr>
		<td>連絡電話:</td>
		<td><input type="TEXT" name="phone" size="45"
			value="<%= (memVO==null)? "0912345678" : memVO.getPhone()%>" /></td>
	</tr>
	<tr>
		<td>電子信箱:</td>
		<td><input type="email" name="email" size="45"
			value="<%= (memVO==null)? "llf2@gmail.com" : memVO.getEmail()%>" /></td>
	</tr>
	<tr>
		<td>銀行帳戶:</td>
		<td><input type="TEXT" name="account" size="45"
			value="<%= (memVO==null)? "100" : memVO.getAccount()%>" /></td>
	</tr>
	<tr>
		<td>上傳大頭貼:</td>
		<td><input type="file" name="picture" size="45"
			value="<%= (memVO==null)? "100" : memVO.getPicture()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
