<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.com.model.*"%>
<%
ComVO comVO = (ComVO) request.getAttribute("comVO");
%>

<html>
<head>
<title>addCom.jsp</title></head>

<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3> addCom.jsp</h3>
		</td>
		<td>
	    </td>
	</tr>
</table>

<h3>:</h3>
<%-- --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="Com.do" name="form1">
<table border="0">

	<tr>
		<td>廠商帳號:</td>
		<td><input type="TEXT" name="id" size="45" 
			value="<%= (comVO==null)? "" : comVO.getId()%>" /></td>
	</tr>
	<tr>
		<td>廠商密碼:</td>
		<td><input type="TEXT" name="pwd" size="45" 
			value="<%= (comVO==null)? "" : comVO.getPwd()%>" /></td>
	</tr>
	<tr>
		<td>廠商名稱</td>
		<td><input type="TEXT" name="name" size="45"
			value="<%= (comVO==null)? "MANAGER" : comVO.getName()%>" /></td>
	</tr>
	<tr>
		<td>廠商地址:</td>
		<td><input type="TEXT" name="loc" size="45" 
			value="<%= (comVO==null)? "" : comVO.getLoc()%>" /></td>
	</tr>
	<tr>
		<td>廠商介紹:</td>
		<td><input type="TEXT" name="com_desc" size="45"
			value="<%= (comVO==null)? "jj" : comVO.getCom_desc()%>" /></td>
	</tr>
	<tr>
		<td>廠商電話:</td>
		<td><input type="TEXT" name="phone" size="45"
			value="<%= (comVO==null)? "100" : comVO.getPhone()%>" /></td>
	</tr>
<tr>
		<td>廠商狀態:</td>
		<td><input type="TEXT" name="status" size="45" 
			value="<%= (comVO==null)? "" : comVO.getStatus()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
