<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.advertising.model.*"%>
<%@ page import="java.util.*"%>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update AD</title>
<%
	AdvertisingVO advertisingVO = (AdvertisingVO) request.getAttribute("advertisingVO");
	System.out.print("UPDATE:advertisingVO is null?");
	System.out.println(advertisingVO == null);
%>
<script>
	$(function() {
		$("#startday").datepicker({dateFormat: 'yy-mm-dd'});
		$("#endday").datepicker({dateFormat: 'yy-mm-dd'});
	});
</script>

</head>
<body>


	<a href="listAllAdvertising.jsp">回上一頁</a>
	<h3>資料修改:</h3>

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

	<form METHOD="post"
		ACTION="<%=request.getContextPath()%>/advertising/advertising.do">
		<table border="0">

			<tr>
				<td><img
					src="<%=request.getContextPath()%>/ShowPictureServletDAO?adv_no=<%=advertisingVO.getAdv_no()%>"></td>
			</tr>


			<tr>
				<td>廣告編號:</td>
				<td><%=advertisingVO.getAdv_no()%></td>
			</tr>
			<tr>
				<td>廠商編號:</td>
				<td><%=advertisingVO.getCom_no()%></td>
			</tr>
			<tr>
				<td>廣告內容:</td>
				<td><input type="TEXT" name="text" size="45"
					value="<%=advertisingVO.getText()%>" /></td>
			</tr>
			<tr>
				<td>廣告起始日期:</td>
				<td><input type="text" id="startday" name="startday"
					value="<%=advertisingVO.getStartDay()%>"></td>
			</tr>
			<tr>
				<td>廣告結束日期:</td>
				<td><input type="text" id="endday" name="endday"
					value="<%=advertisingVO.getEndDay()%>"></td>
			</tr>
			<tr>
				<td>價格:</td>
				<td><input type="TEXT" name="price" size="45"
					value="<%=advertisingVO.getPrice()%>" /></td>
			</tr>
			<tr>
				<td>狀態:</td>
				<td><select size="1" name="status">
						<option value="0">未審核</option>
						<option value="1">已審核</option>
				</select></td>
			</tr>

		</table>
		<br><input type="hidden" name="action" value="update"> 
			<input type="hidden" name="adv_no" value="<%=advertisingVO.getAdv_no()%>">
			<input type="hidden" name="com_no" value="<%=advertisingVO.getCom_no()%>">
			<input type="submit" value="送出修改">
	</form>
</body>
</html>