<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.advertising.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update AD</title>
<%
	AdvertisingVO advertisingVO = (AdvertisingVO) request.getAttribute("advertisingVO");
%>
</head>
<body>
	<a href="select_page.jsp">回首頁</a>
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


	<FORM METHOD="post" ACTION="advertising.do">
		<table border="0">
			<tr>
				<td>廣告編號:<font color=red><b>*</b></font></td>
				<td><%=advertisingVO.getAdv_no()%></td>
			</tr>
			<tr>
				<td>廠商編號:</td>
				<td><%=advertisingVO.getCom_no()%>"</td>
			</tr>
			<tr>
				<td>廣告內容:</td>
				<td><input type="TEXT" name="text" size="45"
					value="<%=advertisingVO.getText()%>" /></td>
			</tr>
			<tr>
				<td>廣告起始日期:</td>
				<td bgcolor="#CCCCFF"><input class="cal-TextBox"
					onFocus="this.blur()" size="9" readonly type="text" name="startDay"
					value="<%=advertisingVO.getStartDay()%>"> <a class="so-BtnLink"
					href="javascript:calClick();return false;"
					onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
					onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
					onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','hiredate','BTN_date');return false;">
						<img align="middle" border="0" name="BTN_date"
						src="images/btn_date_up.gif" width="22" height="17" alt="開始日期">
				</a></td>
			</tr>
			<tr>
				<td>廣告結束日期:</td>
				<td bgcolor="#CCCCFF"><input class="cal-TextBox"
					onFocus="this.blur()" size="9" readonly type="text" name="endDay"
					value="<%=advertisingVO.getEndDay()%>"> <a class="so-BtnLink"
					href="javascript:calClick();return false;"
					onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
					onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
					onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','hiredate','BTN_date');return false;">
						<img align="middle" border="0" name="BTN_date"
						src="images/btn_date_up.gif" width="22" height="17" alt="結束日期">
				</a></td>
			</tr>
			<tr>
				<td>價格:</td>
				<td><input type="TEXT" name="price" size="45"
					value="<%=advertisingVO.getPrice()%>" /></td>
			</tr>
			<tr>
				<td>狀態:</td>
				<td><input type="TEXT" name="status" size="45"
					value="<%=advertisingVO.getStatus()%>" /></td>
			</tr>

			<jsp:useBean id="deptSvc" scope="page"
				class="com.advertising.model.AdvertisingService" />

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="empno" value="<%=advertisingVO.getAdv_no()%>"> <input
			type="submit" value="送出修改">
	</FORM>






</body>
</html>