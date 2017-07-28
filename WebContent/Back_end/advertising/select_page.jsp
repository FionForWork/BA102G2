<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	<jsp:useBean id="advertisingSvc" scope="page" class="com.advertising.model.AdvertisingService" />				
				<li><a href='addAdvertising.jsp'>Add</a> a new AD</li>
				
				
				
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