<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Add AD</title>
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
	<h3>資料新增:</h3>

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
		ACTION="<%=request.getContextPath()%>/advertising/advertising.do" enctype="multipart/form-data">
		<table border="0">

			<jsp:useBean id="comSvc" scope="page"
				class="com.com.model.ComService" />
			<tr>
				<td>廠商編號:</td>
				<td><select size="1" name="com_no">
						<c:forEach var="comVO" items="${comSvc.all}">
							<option value="${comVO.com_no}">${comVO.name}
						</c:forEach>
				</select></td>
			</tr>
			
			<tr>
				<td>圖片或影片</td>
				<td><input type="file" name="file" size="45" /></td>
			</tr>
			
			<tr>
				<td>廣告內容:</td>
				<td><input type="TEXT" name="text" size="45"
					value="<%=(advertisingVO == null) ? "" : advertisingVO.getText()%>" /></td>
			</tr>
			<tr>
				<td>廣告起始日期:</td>
				<td><input type="text" id="startday" name="startday"
					value="<%=(advertisingVO == null) ? "" : advertisingVO.getStartDay()%>"></td>
			</tr>
			<tr>
				<td>廣告結束日期:</td>
				<td><input type="text" id="endday" name="endday"
					value="<%=(advertisingVO == null) ? "" : advertisingVO.getEndDay()%>"></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>價格:</td> -->
<!-- 				<td><input type="TEXT" name="price" size="45" -->
<%-- 					value="<%=(advertisingVO == null) ? "" : advertisingVO.getPrice()%>" /></td> --%>
<!-- 			</tr> -->

		</table>
		<br> <input type="hidden" name="status" value="0"> <input
			type="hidden" name="action" value="insert"> <input
			type="submit" value="新增">
	</form>

</body>
</html>