<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.serv.model.*"%>
<%

ServVO servVO = (ServVO) request.getAttribute("servVO");
%>
<html>
<head>
<title></title></head>


<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>Addserv.jsp</h3>
		</td>
		<td>
	    </td>
	</tr>
</table>

<h3>新增廠商服務:</h3>
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

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/serv/serv.do" name="form1">
<table border="0">
	<tr>
		<td>服務類型:</td>
		<td><input type="TEXT" name="stype_no" size="45" 
			value="<%= (servVO==null)? "0003" : servVO.getStype_no()%>" /></td>
	</tr>

	<tr>
		<td>廠商編號:</td>
		<td>${servVO.com_no}</td>
	</tr>
	
<tr>
		<td>訂金:</td>
		<td><input type="TEXT" name="deposit" size="45" 
			value="<%= (servVO==null)? "0" : servVO.getDeposit()%>" /></td>
	</tr>
	<tr>
		<td>價錢:</td>
		<td><input type="TEXT" name="price" size="45" 
			value="<%= (servVO==null)? "0" : servVO.getPrice()%>" /></td>
	</tr>
	<tr>
		<td>服務標題:</td>
		<td><input type="TEXT" name="title" size="45" 
			value="<%= (servVO==null)? "標題" : servVO.getTitle()%>" /></td>
	</tr>
	<tr>
		<td>服務介紹:</td>
		<td><input type="TEXT" name="content" size="45" 
			value="<%= (servVO==null)? "介紹內容" : servVO.getContent()%>" /></td>
	</tr>
	
	
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
