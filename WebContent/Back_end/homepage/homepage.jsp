<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.advertising.model.*"%>
<%@page import="com.product.model.*"%>
<%@page import="com.product_type.model.*"%>
<%@ page import="java.util.*"%>
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
<script src="<%=request.getContextPath()%>/Back_end/res/js/jquery-3.1.1.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
<![endif]-->
<!-- Css files -->
<!-- Icons -->
<link href="<%=request.getContextPath()%>/Back_end/homepage/assets/css/icons.css"
	rel="stylesheet" />
<!-- jQueryUI -->
<link href="<%=request.getContextPath()%>/Back_end/homepage/assets/css/sprflat-theme/jquery.ui.all.css"
	rel="stylesheet" />
<!-- Bootstrap stylesheets (included template modifications) -->
<link href="<%=request.getContextPath()%>/Back_end/homepage/assets/css/bootstrap.css"
	rel="stylesheet" />
<!-- Plugins stylesheets (all plugin custom css) -->
<link href="<%=request.getContextPath()%>/Back_end/homepage/assets/css/plugins.css"
	rel="stylesheet" />
<!-- Main stylesheets (template main css file) -->
<link href="<%=request.getContextPath()%>/Back_end/homepage/assets/css/main.css"
	rel="stylesheet" />
<!-- Custom stylesheets ( Put your own changes here ) -->
<link href="<%=request.getContextPath()%>/Back_end/homepage/assets/css/custom.css"
	rel="stylesheet" />
 <link rel="Short Icon" href="<%=request.getContextPath()%>/Front_end/Resource/img/ring_64.ico">
<!-- Windows8 touch icon ( http://www.buildmypinnedsite.com/ )-->
<meta name="msapplication-TileColor" content="#3399cc" />
</head>
<%
	// Advertising
	AdvertisingService advertisingSvc = new AdvertisingService();
	List<AdvertisingVO> advertisingList = advertisingSvc.getAllUnverified();
	
	// Product
	ProductService productService = new ProductService();
// 	int unpreviewProductCount = productService.getAllCountUnPreivew();
// 	List<ProductVO> unpreviewProductList = productService.getSomeUnPreview(1, 5);
	Product_typeService product_typeService = new Product_typeService();
    List<Product_typeVO> typeList = product_typeService.getAll();
    
    pageContext.setAttribute("advertisingList", advertisingList);
// 	pageContext.setAttribute("unpreviewProductCount", unpreviewProductCount);
// 	pageContext.setAttribute("unpreviewProductList", unpreviewProductList);
	pageContext.setAttribute("typeList", typeList);
%>
<jsp:useBean id="comSvc" scope="page" class="com.com.model.ComService"/>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService"/>

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
								<li><a href="#"><i class="st-user"></i>
										個人資料</a></li>
								<li><a href="#"><i class="im-exit"></i> 登出</a>
								</li>
							</ul>
							</li>
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
				<li><a href="#"><i class="en-users"></i>廠商管理 </a></li>
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
                   
                   
                    <!-- Page start here ( usual with .row ) -->
                    <div class="row">
                        <!-- Start .row -->
                        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                            <div class="carousel-tile carousel vertical slide">
                                <div class="carousel-inner">
                                    <div class="item active">
                                        <div class="tile red">
                                            <div class="tile-icon">
                                                <i class="br-warning s64"></i>
                                            </div>
                                            <div class="tile-content">
                                                <div class="number">2</div>
                                                <h1>檢舉</h1>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- End Carousel -->
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                            <div class="carousel-tile carousel slide" onclick="javascript:location.href='<%=request.getContextPath()%>/Back_end/advertising/ad.jsp'">
                                <div class="carousel-inner">
                                    <div class="item active">
                                        <div class="tile blue">
                                            <div class="tile-icon">
                                                <i class="im-table2 s64"></i>
                                            </div>
                                            <div class="tile-content">
                                                <div class="number">${advertisingList.size()}</div>
                                                <h2>廣告</h2>
                                            </div>
                                        </div>
                                    </div>
                                    
                                </div>
                            </div>
                            <!-- End Carousel -->
                        </div>
                       
                        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                            <div class="carousel-tile carousel slide" onclick="javascript:location.href='<%=request.getContextPath()%>/Back_end/mall/productPreview.jsp'">
                                <div class="carousel-inner">
                                    <div class="item active">
                                        <div class="tile teal">
                                            <!-- tile start here -->
                                            <div class="tile-icon">
                                                <i class="fa-shopping-cart s64"></i>
                                            </div>
                                            <div class="tile-content">
                                                <div class="number">${unpreviewProductCount}</div>
                                                <h3>商品</h3>
                                            </div>
                                        </div>
                                    </div>
                               
                                </div>
                            </div>
                            <!-- End Carousel -->
                        </div>
                    </div>
                    <!-- End .row -->
                    
                    <div class="row">
                        <!-- Start .row -->
                        <div class="col-xs-12 col-sm-12 text-center">
                            <!-- Start col-lg-6 -->
                            <div class="panel panel-danger toggle">
                                <!-- Start .panel -->
                                <div class="panel-heading">
                                    <h4 class="panel-title"><i class="im-warning"></i> 待處理檢舉 </h4>
                                    <span style='float:right;margin-top:4px'><button class='btn btn-default'>more</button></span>
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
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- End .panel -->
                        </div>
                        <!-- End col-lg-6 -->
                    </div>
                    <!-- End .row -->
                    <div class="row">
                        <!-- Start .row -->
                        <div class="col-xs-12 col-sm-12 text-center">
                            <!-- Start col-lg-6 -->
                            <div class="panel panel-primary toggle ">
                                <!-- Start .panel -->
                                <div class="panel-heading">
                                    <h4 class="panel-title"><i class="im-table2"></i> 待處理廣告申請 </h4>
                                	<span style='float:right;margin-top:4px'><button class='btn btn-default' onclick="javascript:location.href='<%=request.getContextPath()%>/Back_end/advertising/ad.jsp'">more</button></span>
                                	
                                </div>
                                <div class="panel-body">
                                    <table class="table table-hover">
                                        
                                        <thead>
                                       
                                            <tr>
                                                <th>申請廠商</th>
                                                <th>廣告標題</th>
                                                <th>刊登日期</th>
                                                <th>結束日期</th>
                                                <th>價格</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                         <c:forEach var="advertisingVO" items="${advertisingList}" varStatus="s"
												begin="1" end="3">
                                            <tr>
                                                <td>${comSvc.getOneCom(advertisingVO.com_no).name}</td>
                                                <td>${advertisingVO.title}</td>
                                                <td>${advertisingVO.startDay.toString().substring(0,10)}</td>
                                                <td>${advertisingVO.endDay.toString().substring(0,10)}</td>
                                                <td>$ ${advertisingVO.price}</td>
                                            </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- End .panel -->
                        </div>
                        <!-- End col-lg-6 -->
                    </div>
                    <!-- End .row -->
                    <div class="row">
                        <!-- Start .row -->
                        <div class="col-xs-12 col-sm-12 text-center">
                            <!-- Start col-lg-6 -->
                            <div class="panel panel-success toggle ">
                                <!-- Start .panel -->
                                <div class="panel-heading">
                                    <h4 class="panel-title"><i class="fa-shopping-cart"></i> 待處理商品上架申請 </h4>
                                    <span style='float:right;margin-top:4px'><button class='btn btn-default' onclick="javascript:location.href='<%=request.getContextPath()%>/Back_end/mall/productPreview.jsp'">more</button></span>
                                
                                </div>
                                <div class="panel-body">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>申請賣家</th>
                                                <th>商品名稱</th>
                                                <th>商品類型</th>
                                                <th>商品價格</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="productVO" items="${unpreviewProductList}" varStatus="s"
												begin="1" end="3">
                                            <tr>
                                                
                                                <td>${memSvc.getOneMem(productVO.seller_no).name}</td>
                                                <td>${productVO.pro_name}</td>
                                                <td>${typeList.get(productVO.protype_no-1).type_name}</td>
                                                <td>$ ${productVO.price}</td>
                                            </tr>
                                            </c:forEach>
                                            
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
