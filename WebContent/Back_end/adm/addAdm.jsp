<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adm.model.*"%>
<%
AdmVO admVO = (AdmVO) request.getAttribute("admVO");
%>
<html>
<head>
<title></title></head>


<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>AddAdm.jsp</h3>
				<a href="listAllAdm.jsp">回首頁</a>
		</td>
		<td>
	    </td>
	</tr>
</table>

<h3>員工資料新增:</h3>
<%--錯誤處理 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="adm.do" name="form1">
<table border="0">

	<tr>
		<td>管理員編號:</td>
		<td><input type="TEXT" name="id" size="45" 
			value="<%= (admVO==null)? "123@1234.com" : admVO.getId()%>" /></td>
	</tr>
	<tr>
		<td>管理員密碼:</td>
		<td><input type="password" name="pwd" size="45" 
			value="<%= (admVO==null)? "123" : admVO.getPwd()%>" /></td>
	</tr>



	<tr>
		<td>管理員名字:</td>
		<td><input type="TEXT" name="name" size="45"
			value="<%= (admVO==null)? "MANAGER" : admVO.getName()%>" /></td>
	</tr>
	<tr>
		<td>管理員職位:</td>
		<td><input type="TEXT" name="job" size="45" 
			value="<%= (admVO==null)? "員工" : admVO.getJob()%>" /></td>
	</tr>
	
<tr>
		<td>管理員狀態:</td>
		<td><input type="TEXT" name="status" size="45" 
			value="<%= (admVO==null)? "正常" : admVO.getStatus()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
