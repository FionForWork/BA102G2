<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ page import="com.advertising.model.*"%>
<%
	AdvertisingVO advertisingVO = (AdvertisingVO) request.getAttribute("advertisingVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>list One AD</title>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<ul>
					<li class="list-unstyled">
						<img src="<%=request.getContextPath()%>/ShowPictureServletDAO?adv_no=<%=advertisingVO.getAdv_no()%>"><br>
						<span>廣告編號:<%=advertisingVO.getAdv_no()%></span><br> 
						<span>廠商編號:<%=advertisingVO.getCom_no()%></span><br> 
						<span>廣告內容:<%=advertisingVO.getText()%></span><br>
						<span>廣告開始時間:<%=advertisingVO.getStartDay()%></span><br> 
						<span>廣告結束時間:<%=advertisingVO.getEndDay()%></span><br> 
						<span>廣告狀態:<%=advertisingVO.getStatus()%></span><br>
					</li>
				</ul>
			</div>
		</div>
	</div>




</body>
</html>