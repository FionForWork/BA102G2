<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.comtra.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="comSvc" scope="page" class="com.com.model.ComService" />
<%
//String mem_no = (String)session.getAttribute("mem_no");
		String mem_no = "1003";
		pageContext.setAttribute("mem_no", mem_no);
%>

<%@ include file="page/comtra_header.file"%>

<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
<div class="container">
	<div class="col-md-offset-1">
		<ul class="breadcrumb">
			<li><a href="#">首頁</a></li>
			<li><a href="#">會員專區</a></li>
			<li class="active">我的最愛</li>
		</ul>
	</div>
</div>
<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->

<div class="container">
	<div class="row">
		<!--sidebar sidebar sidebar sidebar sidebar sidebar -->
		<div class="col-md-offset-1 col-md-2">
			<ul class="list-group">
				<a href="#" class="list-group-item menua">編輯個人資料</a>
				<br>
				<a href="#" class="list-group-item menua">密碼修改</a>
				<br>
				<a href="#" class="list-group-item menua">預約紀錄查詢</a>
				<br>
				<a href="#" class="list-group-item menua">報價紀錄查詢</a>
				<br>
				<a href="#" class="list-group-item menua">作品挑選管理</a>
				<br>
				<a href="#" class="list-group-item menua">我的相簿</a>
				<br>
				<a href="#" class="list-group-item menua active">我的最愛</a>
				<br>
				<a href="#" class="list-group-item menua">商城專區</a>
				<br>
			</ul>


			<a href="#" class="btn btn-block btn-default">查看個人資料</a>
		</div>
		<!--sidebar sidebar sidebar sidebar sidebar sidebar -->

		<!--這裡開始===========================================================================-->
		<!--店家大頭照-->
		<div class="com_head container">
			<img class="com_logo img-circle center-block" src="img/logo.jpg"
				alt="She Says Yes">
		</div>
		<!--店家大頭照-->

		<div class="text-center">
			<h1>廠商名稱</h1>
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
			<div class="col-md-8 col-offset-1">



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
								<td><a href="">02-1234567</a></td>
							</tr>

							<tr>
								<th>地址</th>
								<td>XXX路二段XXXXXX號</td>
							</tr>

							<tr>
								<th>信箱</th>
								<td><a href="">XXXXXXXXXX@gmail.com</a></td>
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
				<p class="text-center">
					<a class="btn btn-reservation btn-lg" id='insertComTra'> 加入收藏 </a>
				</p>
				<input type='hidden' name='com_no' value='2020'> 
				<input
					type='hidden' name='mem_no' value='${mem_no}'> 
				<input
					type='hidden' name='path'
					value='<%=request.getContextPath()%>/comtra/comtra.do'>
			</div>
			<div id="snackbar">Some text some message..</div>
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
				<div class="col-xs-12 col-sm-4">
					<ul class="album_box">
						<li class="list-unstyled"><a href="album.html"
							class="thumbnail thumbnail thumbnail-service mod-shadow img-label">
								<img class="album_image img-thumbnail" src="img/wedding1.jpg">
						</a></li>
					</ul>
				</div>
				<div class="col-xs-12 col-sm-4">
					<ul class="album_box">
						<li class="list-unstyled"><a href="#"
							class="thumbnail thumbnail thumbnail-service mod-shadow img-label">
								<img class="album_image img-thumbnail" src="img/wedding1.jpg">
						</a></li>
					</ul>
				</div>
				<div class="col-xs-12 col-sm-4">
					<ul class="album_box">
						<li class="list-unstyled"><a href="#"
							class="thumbnail thumbnail thumbnail-service mod-shadow img-label">
								<img class="album_image img-thumbnail" src="img/wedding1.jpg">
						</a></li>
					</ul>
				</div>
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
				<div class="col-xs-12 col-sm-3">
					<ul class="service_box">
						<li class="service_title">方案名稱</li>
						<li class="cost"><span>$NT</span> 81000</li>
						<li class="text">cillum dolore eu fugiat nulla pariatur.
							Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
							officia deserunt mollit anim id est laborum.</li>
						<li class="check_service"><a href="#">查看方案</a></li>
						<li class="photo"><a href="#"> <img class="sercive_image"
								src="img/wedding6.jpg">
						</a></li>
					</ul>
				</div>
				<div class="col-xs-12 col-sm-3">
					<ul class="service_box">
						<li class="service_title">方案名稱</li>
						<li class="cost"><span>$NT</span> 81000</li>
						<li class="text">cillum dolore eu fugiat nulla pariatur.
							Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
							officia deserunt mollit anim id est laborum.</li>
						<li class="check_service"><a href="#">查看方案</a></li>
						<li class="photo"><a href="#"> <img class="sercive_image"
								src="img/wedding6.jpg">
						</a></li>
					</ul>
				</div>
				<div class="col-xs-12 col-sm-3">
					<ul class="service_box">
						<li class="service_title">方案名稱</li>
						<li class="cost"><span>$NT</span> 81000</li>
						<li class="text">cillum dolore eu fugiat nulla pariatur.
							Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
							officia deserunt mollit anim id est laborum.</li>
						<li class="check_service"><a href="#">查看方案</a></li>
						<li class="photo"><a href="#"> <img class="sercive_image"
								src="img/wedding6.jpg">
						</a></li>
					</ul>
				</div>
				<div class="col-xs-12 col-sm-3">
					<ul class="service_box">
						<li class="service_title">方案名稱</li>
						<li class="cost"><span>$NT</span> 81000</li>
						<li class="text">cillum dolore eu fugiat nulla pariatur.
							Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
							officia deserunt mollit anim id est laborum.</li>
						<li class="check_service"><a href="#">查看方案</a></li>
						<li class="photo"><a href="#"> <img class="sercive_image"
								src="img/wedding6.jpg">
						</a></li>
					</ul>
				</div>
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
				<h1>廠商自介</h1>
			</span>
		</div>
		<div class="com_intro container">
			<div class="row">
				<div class="col-xs-12 col-sm-6">
					<div class="col-xs-12 col-sm-4"></div>
					<div class="col-xs-12 col-sm-8">基本介紹：
						從懵懂到成熟，從憧憬到實現，女孩做了一生中最美、最重要的決定。 我們不想僅僅做一個婚紗攝影工作室，我們希望能透過一個個的畫面，
						說出每個女孩，從最初的悸動到最美的成真，這一路的點滴與感動。</div>
				</div>
				<div class="col-xs-12 col-sm-6">
					<div class="col-xs-12 col-sm-4">提供服務： 自助婚紗 婚紗包套</div>
					<div class="col-xs-12 col-sm-8"></div>
				</div>
			</div>
		</div>

		<!--店家自介-->


		<br> <br> <br> <br> <br> <br>
		<!--menu-->



	</div>
	<!--上面放東西===========================================================================-->
</div>



<%@ include file="page/comtra_footer.file"%>