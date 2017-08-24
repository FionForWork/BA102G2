<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.advertising.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_type.model.*"%>
<%@ page import="com.report.model.*" %>
<%@ page import="com.report_type.model.Report_type_Service" %>
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
<form name="logout" ACTION="<%= request.getContextPath() %>/adm/adm.do" method=post >
                    <input type="hidden"  name="action" value="logout"/>
                          </form>
</head>
<%
	// Advertising
	AdvertisingService advertisingSvc = new AdvertisingService();
	List<AdvertisingVO> advertisingList = advertisingSvc.getAllUnverified();
	
	// Product
	ProductService productService = new ProductService();
	List<ProductVO> unpreviewProductList = productService.getAllNoImg("0");
	Product_typeService product_typeService = new Product_typeService();
    List<Product_typeVO> typeList = product_typeService.getAll();
    
    // Report
    Report_Service reportSvc = new Report_Service();
    List<ReportVO> reportList = reportSvc.getOneStatus();
    
    pageContext.setAttribute("advertisingList", advertisingList);
	pageContext.setAttribute("unpreviewProductList", unpreviewProductList);
	pageContext.setAttribute("typeList", typeList);
	pageContext.setAttribute("reportList", reportList);
	
%>
<jsp:useBean id="comSvc" scope="page" class="com.com.model.ComService"/>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService"/>
<jsp:useBean id="report_type_Svc" scope="page"
	class="com.report_type.model.Report_type_Service" />
<jsp:useBean id="product_Svc" scope="page" class="com.product.model.ProductService" />
<jsp:useBean id="art_Svc" scope="page" class="com.article.model.Article_Service" />
<jsp:useBean id="forum_Svc" scope="page" class="com.forum_comment.model.Forum_Comment_Service" />

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

                        <li class="dropdown"><a href="#" data-toggle="dropdown"><i class="st-user" style='font-size:15px'></i>&nbsp${admVO.name}</a>
                            <ul class="dropdown-menu right" role="menu">
                                <li><a href="<%=request.getContextPath()%>/Back_end/adm/listMyAdm.jsp">
                                        <i class="st-user"></i>個人資料
                                    </a></li>
                                <li><a href="javascript:document.logout.submit();">
                                        <i class="im-exit"></i>
                                        登出
                                    </a></li>
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

        <div class="sidebar-inner">
  
            <ul id="sideNav" class="nav nav-pills nav-stacked">
				 <c:if test="${aut.contains(\"02\")}">
                <li><a href="<%=request.getContextPath()%>/Back_end/mem/select_member.jsp"><i class="en-users"></i>一般會員管理 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"03\")}">
				<li><a href="<%=request.getContextPath() %>/Back_end/com/listAllCom.jsp"><i class="en-users"></i>廠商管理 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"01\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/adm/listAllAdm.jsp"><i class="en-users"> </i>管理者設定 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"08\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/advertising/ad.jsp"><i class="im-table2"></i>廣告刊登管理 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"07\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/place/placeManagement.jsp"><i class="br-location"></i>景點管理 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"06\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/mall/productPreview.jsp"><i class="fa-shopping-cart"></i>商城管理</a></li>
				</c:if>
				<c:if test="${aut.contains(\"04\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/tradition/Traditionall.jsp"><i class="im-list2"></i>婚禮習俗管理</a></li>
				</c:if>
				<c:if test="${aut.contains(\"05\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/problem/Problemall.jsp"><i class="im-question"></i>常見問題管理</a></li>
				</c:if>
				<c:if test="${aut.contains(\"09\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/report/Report.jsp"><i class="br-warning"></i>檢舉管理</a></li>
				</c:if>
            </ul>

        </div>

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
                   
                   <div class='container'>
                    <!-- Page start here ( usual with .row ) -->
                    <div class="row">
                        <!-- Start .row -->
                        
                        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                            <div class="carousel-tile carousel vertical slide" onclick="javascript:location.href='<%=request.getContextPath()%>/Back_end/report/Report.jsp'">
                                <div class="carousel-inner">
                                    <div class="item active">
                                        <div class="tile red">
                                            <div class="tile-icon">
                                                <i class="br-warning s64"></i>
                                            </div>
                                            <div class="tile-content">
                                                <div class="number">${reportList.size()}</div>
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
                                                <div class="number">${unpreviewProductList.size()}</div>
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
                    </div>
                    <div class='container'>
                    <div class="row">
                        <!-- Start .row -->
                        <div class="col-xs-12 col-sm-12 text-center">
                            <!-- Start col-lg-6 -->
                            <div class="panel panel-danger toggle">
                                <!-- Start .panel -->
                                <div class="panel-heading">
                                    <h4 class="panel-title"><i class="im-warning"></i> 待處理檢舉 </h4>
                                    <span style='float:right;margin-top:4px'><button class='btn btn-default' onclick="javascript:location.href='<%=request.getContextPath()%>/Back_end/report/Report.jsp'">more</button></span>
                                </div>
                                <div class="panel-body">
                                    <table class="table table-hover text-left">
                                        
                                        <thead>
                                            <tr>
                                                <th>檢舉編號</th>
												<th>檢舉類型</th>
												<th>檢舉標題</th>
												<th>檢舉日期</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <%System.out.println(reportList); %>
                                            <c:forEach var="reportVO" items="${reportList}" varStatus="s"
												>
												<%System.out.println("YYYYYY"); %>
                                            <tr>
                                                <td>${reportVO.rep_no}</td>
                                                <td>${report_type_Svc.getOneRep_Type(reportVO.rep_type_no).type}</td>
                                                <c:choose>
											<c:when test="${art_Svc.getOneArt(reportVO.rep_ob_no)!=null }">
												<td>${art_Svc.getOneArt(reportVO.rep_ob_no).title }</td>
											</c:when>
											<c:when test="${forum_Svc.getOneForum_Comment(reportVO.rep_ob_no)!=null }">
												<td>討論版留言</td>
											</c:when>
											<c:when test="${product_Svc.getOneByPK(reportVO.rep_ob_no)!=null }">
												<td>${product_Svc.getOneByPK(reportVO.rep_ob_no).pro_name }</td>
											</c:when>
											<c:otherwise>
												<td>${com_Svc.getOneCom(reportVO.rep_ob_no).name }</td>
											</c:otherwise>
										</c:choose>
                                                <td>${(reportVO.rep_date).toString().substring(0,10)}</td>
                                                <%System.out.println("XXXXX"); %>
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
                    </div>
                    <div class='container'>
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
                                    <table class="table table-hover text-left">
                                        
                                        <thead>
                                       
                                            <tr>
                                           	 	<th>申請廠商編號</th>
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
                                            	<td>${advertisingVO.com_no}</td>
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
                    </div>
                    <div class='container'>
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
                                    <table class="table table-hover text-left">
                                        <thead>
                                            <tr>
                                                <th>申請賣家編號</th>
                                                <th>申請賣家姓名</th>
                                                <th>商品名稱</th>
                                                <th>商品類型</th>
                                                <th>商品價格</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="productVO" items="${unpreviewProductList}" varStatus="s"
												begin="1" end="3">
                                            <tr>
                                                <td>${productVO.seller_no}</td>
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
                    </div>
                    <!-- Page End here -->
                </div>
                <!-- End .outlet -->
            </div>
            <!-- End .content-wrapper -->


	<!-- End content =================================================================================================== -->
	<div class="clearfix"></div>
	
</body>
</html>
