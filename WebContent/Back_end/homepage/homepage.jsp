<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="<%=request.getContextPath()%>/Back_end/homepage/assets/img/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="<%=request.getContextPath()%>/Back_end/homepage/assets/img/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="<%=request.getContextPath()%>/Back_end/homepage/assets/img/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="<%=request.getContextPath()%>/Back_end/homepage/assets/img/ico/apple-touch-icon-57-precomposed.png">
<link rel="icon"
	href="<%=request.getContextPath()%>/Back_end/homepage/assets/img/ico/favicon.ico"
	type="image/png">
<!-- Windows8 touch icon ( http://www.buildmypinnedsite.com/ )-->
<meta name="msapplication-TileColor" content="#3399cc" />
</head>
<body>
	<!-- Start #header -->
	<div id="header">
		<div class="container-fluid">
			<div class="navbar">
				<div class="navbar-header">
					<a class="navbar-brand" href="index.html"> <span
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

				<li><a href="#"><i class="en-users"></i>一般會員管理 </a></li>
				<li><a href="#"><i class="en-users"></i>廠商管理 </a></li>
				<li><a href="#"><i class="en-users"> </i>管理者設定 </a></li>
				<li><a href="#"><i class="im-table2"></i>廣告刊登管理 </a></li>
				<li><a href="#"><i class="br-location"></i>景點管理 </a></li>
				<li><a href="#"><i class="im-newspaper"></i>熱門資訊管理</a></li>
				<li><a href="#"><i class="fa-shopping-cart"></i>商城管理</a></li>
				<li><a href="#"><i class="ec-mail"></i> 聯絡我們管理 </a></li>
				<li><a href="#"><i class="im-question"></i>常見問題管理</a></li>
				<li><a href="#"><i class="br-warning"></i>檢舉管理</a></li>
			</ul>
			<!-- End #sideNav -->
		</div>
		<!-- End .sidebar-inner -->
	</div>
	<!-- End #sidebar -->

	<!-- Start content ===================================================================================================== -->
		
        <!-- Start #content -->
        <div id="content">
            <!-- Start .content-wrapper -->
            <div class="content-wrapper">
               <br>
               <br>
                    <!-- End .page-header -->
                </div>
                <!-- End .row -->
                <div class="outlet">
                    <!-- Start .outlet -->
                    <div class="row">
                        <!-- Start .row -->
                        <div class="col-xs-10 col-sm-10 col-sm-offset-1 text-center">
                            <!-- Start col-lg-6 -->
                            <div class="panel panel-success toggle ">
                                <!-- Start .panel -->
                                <div class="panel-heading">
                                    <h4 class="panel-title"><i class="im-warning"></i> 待處理檢舉事項 </h4>
                                </div>
                                <div class="panel-body">
                                    <table class="table table-hover">
                                        
                                        <thead>
                                            <tr>
                                                <th>項目</th>
                                                <th>標題</th>
                                                <th>檢舉者</th>
                                                <th>檢舉時間</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>資料</td>
                                                <td>資料</td>
                                                <td>資料</td>
                                                <td>資料</td>
                                            </tr>
                                            <tr>
                                                <td>資料</td>
                                                <td>資料</td>
                                                <td>資料</td>
                                                <td>資料</td>
                                            </tr>
                                            <tr>
                                                <td>資料</td>
                                                <td>資料</td>
                                                <td>資料</td>
                                                <td>資料</td>
                                            </tr>
                                             <tr>
                                                <td>資料</td>
                                                <td>資料</td>
                                                <td>資料</td>
                                                <td>資料</td>
                                            </tr>
                                            <tr>
                                                <td>資料</td>
                                                <td>資料</td>
                                                <td>資料</td>
                                                <td>資料</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- End .panel -->
                            
                        </div>
                        <!-- End col-lg-6 -->
                    </div>
                    <!-- End .row -->
                    <!-- Page End here -->
                </div>
                <!-- End .outlet -->
            </div>
            <!-- End .content-wrapper -->


	<!-- End content =================================================================================================== -->
	<div class="clearfix"></div>
	
</body>
</html>
