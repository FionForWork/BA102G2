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
						<span>�s�i�s��:<%=advertisingVO.getAdv_no()%></span><br> 
						<span>�t�ӽs��:<%=advertisingVO.getCom_no()%></span><br> 
						<span>�s�i���e:<%=advertisingVO.getText()%></span><br>
						<span>�s�i�}�l�ɶ�:<%=advertisingVO.getStartDay()%></span><br> 
						<span>�s�i�����ɶ�:<%=advertisingVO.getEndDay()%></span><br> 
						<span>�s�i���A:<%=advertisingVO.getStatus()%></span><br>
					</li>
				</ul>
			</div>
		</div>
	</div>




</body>
</html>