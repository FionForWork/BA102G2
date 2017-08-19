<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>She Said Yes!</title>
<!-- Mobile specific metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- Force IE9 to render in normal mode -->
<!--[if IE]><meta http-equiv="x-ua-compatible" content="IE=9" /><![endif]-->
<!-- Import google fonts - Heading first/ text second -->
<!-- <link rel='stylesheet' type='text/css' <!--[if lt IE 9]> -->

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!--[endif]-->
<!-- Css files -->
<!-- Icons -->
<link
	href="<%=request.getContextPath()%>/Back_end/homepage/assets/css/icons.css"
	rel="stylesheet" />
<!-- jQueryUI -->
<link
	href="<%=request.getContextPath()%>/Back_end/homepage/assets/css/sprflat-theme/jquery.ui.all.css"
	rel="stylesheet" />
<!-- Bootstrap stylesheets (included template modifications) -->
<link
	href="<%=request.getContextPath()%>/Back_end/homepage/assets/css/bootstrap.css"
	rel="stylesheet" />
<!-- Plugins stylesheets (all plugin custom css) -->
<link
	href="<%=request.getContextPath()%>/Back_end/homepage/assets/css/plugins.css"
	rel="stylesheet" />
<!-- Main stylesheets (template main css file) -->
<link
	href="<%=request.getContextPath()%>/Back_end/homepage/assets/css/main.css"
	rel="stylesheet" />
<!-- Custom stylesheets ( Put your own changes here ) -->
<link
	href="<%=request.getContextPath()%>/Back_end/homepage/assets/css/custom.css"
	rel="stylesheet" />
<!-- Fav and touch icons -->

<link rel="icon" href="<%=request.getContextPath()%>/Back_end/homepage/assets/img/ico/favicon.ico"
	type="image/png">
 <link rel="Short Icon" href="<%=request.getContextPath()%>/Front_end/Resource/img/ring_64.ico">

<!-- Windows8 touch icon ( http://www.buildmypinnedsite.com/ )-->
<meta name="msapplication-TileColor" content="#3399cc" />
</head>
<body>
	<!-- Start #header -->
	<div id="header">
		<div class="container-fluid">
			<div class="navbar">
				<div class="navbar-header">
					<a class="navbar-brand" href="<%=request.getContextPath()%>/Back_end/homepage/homepage.jsp"> <span
						class="text-logo"> She Said Yes!</span>
					</a>
				</div>
				<nav class="top-nav" role="navigation">
					<ul class="nav navbar-nav pull-right">

						<li class="dropdown"><a href="#" data-toggle="dropdown">喬治</a>
							<ul class="dropdown-menu right" role="menu">
								<li><a href="profile.html"><i class="st-user"></i>
										Profile</a></li>
								<li><a href="login.html"><i class="im-exit"></i> Logout</a>
								</li>
							</ul></li>
					</ul>
				</nav>
			</div>
		</div>
		<!-- Start .header-inner -->
	</div>
	<!-- End #header -->
	<!-- Start #sidebar -->
	<div id="sidebar">
		<!-- Start .sidebar-inner -->
		<div class="sidebar-inner">
			<!-- Start #sideNav -->
			<ul id="sideNav" class="nav nav-pills nav-stacked">

				<li><a href="<%=request.getContextPath()%>/Back_end/mem/select_member.jsp"><i class="en-users"></i>一般會員管理 </a></li>
				<li><a href="#" class='active'><i class="en-users"></i>廠商管理 </a></li>
				<li><a href="<%=request.getContextPath()%>/Back_end/adm/listAllAdm.jsp"><i class="en-users"> </i>管理者設定 </a></li>
				<li><a href="<%=request.getContextPath()%>/Back_end/advertising/ad.jsp"><i class="im-table2"></i>廣告刊登管理 </a></li>
				<li><a href="<%=request.getContextPath()%>/Back_end/place/placeManagement.jsp"><i class="br-location"></i>景點管理 </a></li>
				<li><a href="#"><i class="im-newspaper"></i>熱門資訊管理</a></li>
				<li><a href="<%=request.getContextPath()%>/Back_end/mall/productPreview.jsp"><i class="fa-shopping-cart"></i>商城管理</a></li>
				<li><a href="<%=request.getContextPath()%>/Back_end/tradition/Traditionall.jsp"><i class="im-list2"></i>婚禮習俗管理</a></li>
				<li><a href="<%=request.getContextPath()%>/Back_end/problem/Problemall.jsp"><i class="im-question"></i>常見問題管理</a></li>
				<li><a href="#"><i class="br-warning"></i>檢舉管理</a></li>
			</ul>
			<!-- End #sideNav -->
		</div>
		<!-- End .sidebar-inner -->
	</div>
	<!-- End #sidebar -->

<div id="content">
    <div class="content-wrapper">
        <br><br>
    </div>
    <div class="outlet">
    	<!-- Start content ===================================================================================================== -->
       
       
       
       
       
       
       
       
       
       
       
       
        <!-- End content =================================================================================================== -->	
    </div>
</div>

	<div class="clearfix"></div>
</body>
</html>
