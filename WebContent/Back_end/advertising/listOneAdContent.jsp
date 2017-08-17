<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.advertising.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	AdvertisingVO advertisingVO = (AdvertisingVO) request.getAttribute("listOneAdContent");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-4">
				<img style="width:50%"
					src="<%=request.getContextPath()%>/ShowPictureServletDAO?adv_no=<%=advertisingVO.getAdv_no()%>">
			</div>
			<div class="col-xs-12 col-sm-8"><%=advertisingVO.getText()%></div>
		</div>
	</div>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-8">
				<form method="post"
					action="<%=request.getContextPath()%>/advertising/advertising.do">
					<input type="hidden" name="adv_no" value="<%=advertisingVO.getAdv_no()%>">
					<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
					<input type="hidden" name="action" value="approved">
					<input type="submit" class="btn btn-info btn-block" value="通過">				
				</form>
				
				<form method="post"
					action="<%=request.getContextPath()%>/advertising/advertising.do">
					<input type="hidden" name="adv_no" value="<%=advertisingVO.getAdv_no()%>">
					<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">					
					<input type="hidden" name="action" value="disapproved">
					<input type="submit" class="btn btn-danger btn-block" value="未通過">				
				</form>
			</div>
		</div>
	</div>
	
	
</body>
</html>