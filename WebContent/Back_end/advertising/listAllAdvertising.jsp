<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@page import="com.advertising.model.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ALL AD</title>

<%
    AdvertisingService advertisingSvc = new AdvertisingService();
    List<AdvertisingVO> advertisingList = advertisingSvc.getAll();
    pageContext.setAttribute("advertisingList",advertisingList);
%>

</head>
<body>
	<%@ include file="before.file"%>


	<div id="content">
		<!-- Start .content-wrapper -->
		<div class="content-wrapper">
			<div class="row">
				<!-- Start .row -->
				<!-- Start .bredcrumb -->
				<ul id="crumb" class="breadcrumb">
				</ul>

				<c:forEach var="advertisingVO" items="${advertisingList}">
				
				<div class="col-xs-12 col-sm-3">
					<ul>
						<li class="list-unstyled"><h3><span>廣告編號:${advertisingVO.adv_no}</span></h3><br> 
						<img src="<%=request.getContextPath()%>/ShowPictureServletDAO?adv_no=${advertisingVO.adv_no}"><br>
							<span>廠商編號:${advertisingVO.com_no}</span><br> 
							<span>廣告文字資料:${advertisingVO.text}</span><br> 
							<span>刊登日期:${advertisingVO.startDay}</span><br>
							<span>結束日期:${advertisingVO.endDay}</span><br> 
							<span>價格:${advertisingVO.price}</span><br> 
							<span>狀態:${advertisingVO.status}</span><br>
							<form method="post" action="<%=request.getContextPath()%>/advertising/advertising.do">
								<input type="submit" value="修改">
								<input type="hidden"
									name="adv_no" value="${AdvertisingVO.adv_no}"> 
								<input type="hidden" name="action" value="getOne_For_Update">
							</form>
						</li>
					</ul>
				</div>

				</c:forEach>








				<!-- End .breadcrumb -->
			</div>
			<!-- End .page-header -->
		</div>
		<!-- End .row -->
	</div>
	<!-- End .content-wrapper -->
	<div class="clearfix"></div>


	<%@ include file="after.file"%>
</body>
</html>