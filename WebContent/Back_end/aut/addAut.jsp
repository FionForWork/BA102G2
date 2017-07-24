<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.aut.model.*"%>
<%
AutVO autVO = (AutVO) request.getAttribute("autVO");
%>
<html>
<head>
<title></title></head>


<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>AddAut.jsp</h3>
		</td>
		<td>
	    </td>
	</tr>
</table>

<h3>員工資料新增:</h3>
<a href="listAllAut.jsp">回首頁</a>
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

<FORM METHOD="post" ACTION="aut.do" name="form1">
<table border="0">
<tr>
		<td>管理員編號:</td>
		<td><input type="TEXT" name="adm_no" size="45" 
			value="<%= (autVO==null)? "0003" : autVO.getAdm_no()%>" /></td>
	</tr>
	<tr>
		<td>權限編號:</td>
		<td><input type="TEXT" name="id" size="45" 
			value="<%= (autVO==null)? "02" : autVO.getId()%>" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
