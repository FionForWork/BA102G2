<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.works.model.*"%>
<%@ page import="com.com.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	ComService comSvc = new ComService();
	ComVO comVO = comSvc.getOneCom("2001");
	pageContext.setAttribute("comVO", comVO);

	WorksService worksSvc = new WorksService();
	List<WorksVO> worksList = worksSvc.getAllByComNo("2001");
	pageContext.setAttribute("worksList", worksList);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>


	<%@ include file="before.file"%>

	<div class="fade-carousel">
		<div class="home-banner fade-carousel" style="overflow: hidden;">
			<div class="item slides">
				<div></div>
				<div class="slide-1 bg-cover lazy"
					style="background-image: url('img/banner3.jpg');"></div>
				<!--banner圖放這裡-->
			</div>
		</div>
	</div>

	<!--店家大頭照-->
	<div class="com_head container">
		<img class="com_logo img-circle center-block" src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${comVO.com_no}"
			alt="She Said Yes">
	</div>
	<!--店家大頭照-->

	<div class="text-center">
		<h1>${comVO.name}</h1>
	</div>

	<div class="catalog hidden-xs">
		<ul class="list-inline">
			<li><a href="#info" title="聯絡資訊"> 聯絡資訊 </a></li>
			<li><a href="#album" title="作品"> 作品 </a></li>
			<li><a href="#service" title="方案"> 方案 </a></li>
			<li><a href="#about" title="關於我"> 關於我 </a></li>
		</ul>
	</div>

	<!--店家資料-->
	<div class="container" id="info">
		<div class="col-sm-9">
			<div class="hidden-xs">
				<table class="table table-condensed">
					<tbody>
						<tr>
							<th>營業時間</th>
							<td>13:00 ~ 22:00</td>
						</tr>

						<tr>
							<th>店休日</th>
							<td>每個星期三固定公休</td>
						</tr>

						<tr>
							<th>電話</th>
							<td><a href="">${comVO.phone}</a></td>
						</tr>

						<tr>
							<th>地址</th>
							<td>${comVO.loc}</td>
						</tr>

						<tr>
							<th>信箱</th>
							<td><a href="">${comVO.id}</a></td>
						</tr>

						<tr>
							<th>Line ID</th>
							<td>@wth7964p</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!--////////////////////////////-->
		<!--預約按鈕-->
		<div class="col-sm-3">
			<p class="text-center">
				<a class="btn btn-reservation btn-lg" href=""> 線上預約 </a>
			</p>
		</div>
	</div>
	<!--店家資料-->

	<!--店家相簿-->
	<div class="text-center" id="album">
		<span>
			<h1>廠商作品</h1>
		</span>
	</div>
	<div class="container">
		<div class="row">
		
		
		<c:forEach var="worksVO" items="${worksList}" begin="1" end="9">
			<div class="col-xs-12 col-sm-4">
				<ul class="album_box">
					<li class="list-unstyled"><a href="album.html"
						class="thumbnail thumbnail thumbnail-service mod-shadow img-label">
							<img class="album_image img-thumbnail"
							src="<%=request.getContextPath()%>/ShowPictureServletDAO?works_no=${worksVO.works_no}">
					</a></li>
				</ul>
			</div>
		</c:forEach>	
		
		
		</div>
	</div>


	<div class="container text-center">
		<div class="row">
			<div class="col-xs-12 col-sm-3"></div>
			<div class="col-xs-12 col-sm-6">
				<a href="#">
					<div class="panel panel-default">
						<div class="panel-body">查看所有內容</div>
					</div>
				</a>
			</div>
			<div class="col-xs-12 col-sm-3"></div>
		</div>
	</div>
	<!--店家相簿-->

	<!--店家方案-->
	<div class="text-center" id="service">
		<span>
			<h1>廠商方案</h1>
		</span>
	</div>
	<div class="container">
		<div class="row">
		
		<c:forEach var="worksVO" items="${worksList}" begin="9" end="12">
			<div class="col-xs-12 col-sm-3">
				<ul class="service_box">
					<li class="service_title">方案名稱</li>
					<li class="cost"><span>$NT</span> 81000</li>
					<li class="text">cillum dolore eu fugiat nulla pariatur.
						Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
						officia deserunt mollit anim id est laborum.</li>
					<li class="check_service"><a href="#">查看方案</a></li>
					<li class="photo"><a href="#"> <img class="sercive_image"
							src="<%=request.getContextPath()%>/ShowPictureServletDAO?works_no=${worksVO.works_no}">
					</a></li>
				</ul>
			</div>
		</c:forEach>
			
		</div>
	</div>


	<div class="container text-center">
		<div class="row">
			<div class="col-xs-12 col-sm-3"></div>
			<div class="col-xs-12 col-sm-6">
				<a href="#">
					<div class="panel panel-default">
						<div class="panel-body">查看所有內容</div>
					</div>
				</a>
			</div>
			<div class="col-xs-12 col-sm-3"></div>
		</div>
	</div>
	<!--店家方案-->

	<!--店家影片-->
	<!--店家影片-->

	<!--店家自介-->
	<div class="text-center" id="introduction">
		<span>
			<h1>廠商介紹</h1>
		</span>
	</div>
	<div class="com_intro container">
		<div class="row">
			<div class="col-xs-12 col-sm-6">
				<div class="col-xs-12 col-sm-4"></div>
				<div class="col-xs-12 col-sm-8">基本介紹： ${comVO.com_desc}</div>
			</div>
			<div class="col-xs-12 col-sm-6">
				<div class="col-xs-12 col-sm-4">提供服務： 自助婚紗 婚紗包套</div>
				<div class="col-xs-12 col-sm-8"></div>
			</div>
		</div>
	</div>

	<!--店家自介-->


	<%@ include file="after.file"%>


</body>
</html>