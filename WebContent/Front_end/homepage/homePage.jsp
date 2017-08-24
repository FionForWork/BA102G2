<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>    
<%@ page import="com.serv.model.*"%>
<%@ page import="com.works.model.*"%>   
<%@ page import="com.com.model.*"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ＨomePage</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="Short Icon" href="<%=request.getContextPath()%>/Front_end/Resource/img/ring_64.ico">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Front_end/homepage/css/w3.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Front_end/homepage/css/fonts.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Front_end/homepage/css/homepage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="<%=request.getContextPath()%>/Front_end/homepage/css/bootstrap.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/Front_end/homepage/js/jquery-3.2.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Front_end/homepage/js/homepage.js"></script>
    <script src="<%=request.getContextPath()%>/Front_end/homepage/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/Front_end/homepage/js/contact_us.js"></script>
<%
	ServService servSvc = new ServService();
	List<ServVO> servList = servSvc.getAllAvg();
	pageContext.setAttribute("servList", servList);

	DecimalFormat df = new DecimalFormat("#,##0.0"); 
	pageContext.setAttribute("df", df);

	Map<String,String> map =(LinkedHashMap) request.getAttribute("map");
%>    
</head>
<style>
	h1,h2,h3,h4,h5,h6 {
        font-family: "Sacramento", Arial, serif;
        font-style: bold;
        color: #F14195;
    }
</style>
<body>

    <!-- Navbar (sit on top) -->
    <div class="w3-top">
        <div class="w3-bar" id="myNavbar">
            <a class="w3-bar-item w3-button w3-hover-black w3-hide-medium w3-hide-large w3-right" href="javascript:void(0);" onclick="toggleFunction()" title="Toggle Navigation Menu">
                <i class="fa fa-bars"></i>
            </a>
            <a href="#home" class="w3-bar-item w3-button"><i class="fa fa-home"></i> HOME</a>
            <a href="#company" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-th"></i> 熱門廠商</a>
            <a href="#portfolio" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-th"></i> 服務</a>
            <a href="<%=request.getContextPath()%>/Front_end/mall/index.jsp" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-gift"></i> 線上商城</a>
            <a href="#contact" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-envelope"></i> CONTACT</a>
            <a href="#" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-red"><i class="fa fa-user"></i> 註冊</a>
            <a href="#" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-red"><i class="fa fa-sign-in"></i> 登入</a>
        </div>

        <!-- Navbar on small screens -->
        <div id="navDemo" class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium">
            <a href="#about" class="w3-bar-item w3-button" onclick="toggleFunction()">ABOUT</a>
            <a href="#portfolio" class="w3-bar-item w3-button" onclick="toggleFunction()">PORTFOLIO</a>
            <a href="#contact" class="w3-bar-item w3-button" onclick="toggleFunction()">CONTACT</a>
            <a href="#" class="w3-bar-item w3-button">SEARCH</a>
        </div>
    </div>

    <!-- First Parallax Image with Logo Text -->
    <div class="bgimg-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
        </div>

    </div>

    <div class="w3-row w3-center w3-dark-grey w3-padding-16">
        <div class="w3-quarter w3-section">
            <span class="w3-xlarge">160+</span><br> Partners
        </div>
        <div class="w3-quarter w3-section">
            <span class="w3-xlarge">55+</span><br> Weddings Done
        </div>
        <div class="w3-quarter w3-section">
            <span class="w3-xlarge">89+</span><br> Happy Clients
        </div>
        <div class="w3-quarter w3-section">
            <span class="w3-xlarge">150+</span><br> Families
        </div>
    </div>

    <!-- Container (Portfolio Section) -->
    <div class="w3-padding-64 container" id="portfolio" style="width:90%">
        <h1 class="w3-center w3-jumbo"><b>Our Services</b></h1>
        <br>
        <!--show2-->
        <div class="wrapper">
            <div class="column w3-hover-opacity">
            	<a href="<%=request.getContextPath()%>/Front_end/com_page/select_company.jsp?stype_no=0001">
                	<div class="inner"><div class="w3-display-topleft servicetopleft">拍婚紗</div></div>
            	</a>
            </div>
            <div class="column w3-hover-opacity">
                <a href="<%=request.getContextPath()%>/Front_end/com_page/select_company.jsp?stype_no=0003">
                	<div class="inner"><div class="w3-display-topleft servicetopleft">新秘</div></div>
           	</a>
            </div>
            <div class="column w3-hover-opacity">
            	<a href="<%=request.getContextPath()%>/Front_end/Preview/ImageCropper.jsp">
                	<div class="inner"><div class="w3-display-bottomleft servicetopleft">實景預覽</div></div>
            	</a>
            </div>
            <div class="column w3-hover-opacity">
                <a href="<%=request.getContextPath()%>/Front_end/com_page/select_company.jsp?stype_no=0002">
                	<div class="inner"><div class="w3-display-topright servicetopleft">婚攝/婚錄</div></div>
            	</a>
            </div>
            <div class="column w3-hover-opacity">
            	<a href="<%=request.getContextPath()%>/Front_end/RFQ/listAllRFQ.jsp">
                	<div class="inner"><div class="w3-display-bottomleft servicetopleft">新人問報價</div></div>
            	</a>
            </div>
            <div class="column w3-hover-opacity">
            	<a href="<%=request.getContextPath()%>/Front_end/Tradition/TraditionAll.jsp">
                	<div class="inner"><div class="w3-display-bottomright servicetopleft">婚禮習俗</div></div>
            	</a>
            </div>
            <div class="column w3-hover-opacity">
            	<a href="<%=request.getContextPath()%>/Front_end/Article/Article.jsp">
                	<div class="inner"><div class="w3-display-topleft servicetopleft">討論版</div></div>
            	</a>
            </div>
        </div>

        <!-- show2-->


    </div>

    <!-- Second Parallax Image with Portfolio Text -->
    <div class="bgimg-2 w3-display-container w3-opacity-min">
        <!--
  <div class="w3-display-middle">
    <span class="w3-xxlarge w3-text-white w3-wide">OUR SERVICES</span>
  </div>
-->
    </div>


    <!--ad==========================================================================-->
    <br>
    <div id="company" class="container" style="width:90%">
        <div class="row">
    		<c:forEach var="servVO" items="${servList}" begin="1" end="9">
            <div class="col-xs-12 col-md-4">
                <!--婚攝item-->
                <a href="<%=request.getContextPath()%>/Front_end/com_page/company_page.jsp?com_no=${servVO.com_no}" class="thumbnail">
				
                    <div class="caption">
                        <div class="media" style="margin-bottom:0px;">
                            <jsp:useBean id="comSvc" scope="page" class="com.com.model.ComService"/>
                            <c:set var="comVO" value="${comSvc.getOneCom(servVO.com_no)}"/>
                            <div class="media-left">
                                <img class="media-object" src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${servVO.com_no}" style="height:35px;width:35px;">
                            </div>
                            <div class="media-body media-middle">
                                <b>${comVO.name}</b> &nbsp;
                            </div>
                        </div>
                    </div>
                    
                    <jsp:useBean id="workSvc" scope="page" class="com.works.model.WorksService"/>
                    <c:forEach var="workVO" items="${workSvc.getAllByComNo(servVO.com_no)}" begin="0" end="0">
                    <div class="rat_4_3 ratiobox bg-cover bg-picture img-label" style="background-image: url('<%=request.getContextPath()%>/ShowPictureServletDAO?works_no=${workVO.works_no}');background-position:50% 50%;">
                    </div>  
                    </c:forEach>
                    
                    <div class="caption clearfix">

                        <div class="text-right">
                            <span class="small">評價</span>
                            <span class="" style="border-radius: 30px;"><b class="a-rating text-warning" style="font-size: 1.3em;"><span>${df.format(servVO.score/servVO.times)}</span>
                            </b>
                            <i class="fa fa-star text-warning" aria-hidden="true"></i></span>
                        </div>
                    </div>
                </a>
            </div>
            </c:forEach>
        </div>
    </div>
    <!--ad===========================================================================-->


    <!-- Modal for full size images on click-->
<!--
    <div id="modal01" class="w3-modal w3-black" onclick="this.style.display='none'">
        <span class="w3-button w3-large w3-black w3-display-topright" title="Close Modal Image"><i class="fa fa-remove"></i></span>
        <div class="w3-modal-content w3-animate-zoom w3-center w3-transparent w3-padding-64">
            <img id="img01" class="w3-image">
            <p id="caption" class="w3-opacity w3-large"></p>
        </div>
    </div>
-->

    <!-- Third Parallax Image with Portfolio Text -->
    <div class="bgimg-3 w3-display-container w3-opacity-min">
        <div class="w3-display-middle">
            <span class="w3-xxlarge w3-text-white w3-wide">CONTACT</span>
        </div>
    </div>

    <!-- Container (Contact Section) -->
    <div class="w3-content w3-container w3-padding-64" id="contact">
        <h1 class="w3-center w3-jumbo"><b>Where We Are</b></h1>
        <p class="w3-center"><em>I'd love your feedback!</em></p>

        <div class="w3-row w3-padding-32 w3-section">
            <div class="w3-col m4 w3-container">
                <!-- Add Google Maps -->
                <div id="googleMap" class="w3-round-large" style="width:100%;height:400px;"></div>
            </div>
            <div class="w3-col m8 w3-panel">
                <div class="w3-large w3-margin-bottom">
                    <i class="fa fa-map-marker fa-fw w3-hover-text-black w3-xlarge w3-margin-right"></i> Taoyuan, Taiwan<br>
                    <i class="fa fa-phone fa-fw w3-hover-text-black w3-xlarge w3-margin-right"></i> Phone: (03) 151515<br>
                    <i class="fa fa-envelope fa-fw w3-hover-text-black w3-xlarge w3-margin-right"></i> Email: mail@gmail.com<br>
                </div>
                <p>Swing by for a cup of <i class="fa fa-coffee"></i>, or leave me a note:</p>
                <form method="post" action="<%=request.getContextPath()%>/ContactUs">
                    <div class="w3-row-padding" style="margin:0 -16px 8px -16px">
                        <div class="w3-half">
                            <input class="w3-input w3-border" type="text" placeholder="Name" required  name="name">
                        </div>
                        <div class="w3-half">
                            <input class="w3-input w3-border" type="email" placeholder="Email" required name="email">
                        </div>
                    </div>
                    <input class="w3-input w3-border" type="text" placeholder="Message" required name="messagesArea"><br>
                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
                    <button class="w3-button w3-black w3-right w3-section"  onClick="validateForm(this.form)">
          			<i class="fa fa-paper-plane"></i> SEND MESSAGE
        			</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Footer -->
    
                <div class="clearfix" style="background-color:#333">
                <div class="wd_footer">
                    <div class="clearfix">
                        <div class="col-xs-4 text-center">
                            <h4><b>婚禮服務</b></h4>
                            <ul class="list-unstyled">
                                <li itemprop="department"><a href="<%=request.getContextPath()%>/Front_end/com_page/select_company.jsp?stype_no=0001" itemprop="url">拍婚紗</a></li>
                                <li itemprop="department"><a href="<%=request.getContextPath()%>/Front_end/com_page/select_company.jsp?stype_no=0002" itemprop="url">婚攝婚錄</a></li>
                                <li itemprop="department"><a href="<%=request.getContextPath()%>/Front_end/com_page/select_company.jsp?stype_no=0003" itemprop="url">新秘</a></li>
                            </ul>
                        </div>

                        <div class="col-xs-4 text-center">
                            <h4><b>婚禮周邊</b></h4>
                            <ul class="list-unstyled">
                                <li itemprop="department"><a href="<%=request.getContextPath()%>/Front_end/Preview/ImageCropper.jsp" itemprop="url">實景預覽</a></li>
                                <li itemprop="department"><a href="<%=request.getContextPath()%>/Front_end/mall/index.jsp" itemprop="url">線上商城</a></li>
                            </ul>
                        </div>

                        <div class="col-xs-4 text-center">
                            <h4><b>新娘專屬</b></h4>
                            <ul class="list-unstyled">
                                <li class=""><a href="<%=request.getContextPath()%>/Front_end/Article/Article.jsp" itemprop="url">討論版</a></li>
                                <li class=""><a href="<%=request.getContextPath()%>/Front_end/mem/listOneMem.jsp" target="_blank" itemprop="url">會員專區</a></li>
                            </ul>
                        </div>

                    </div>
                </div>
            </div>
    
    <footer id="footer" class="w3-center">
        <a href="#home" class="w3-button w3-light-grey"><i class="fa fa-arrow-up w3-margin-right"></i>To the top</a>
        <div class="w3-xlarge w3-section">
            <i class="fa fa-facebook-official w3-hover-opacity"></i>
            <i class="fa fa-instagram w3-hover-opacity"></i>
            <i class="fa fa-snapchat w3-hover-opacity"></i>
            <i class="fa fa-pinterest-p w3-hover-opacity"></i>
            <i class="fa fa-twitter w3-hover-opacity"></i>
        </div>
    </footer>

    <!-- Add Google Maps -->
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDhkPylt6ne313RHlmh0T8umT2ISt_24Uc&callback=myMap"></script>
    <!--
To use this code on your website, get a free API key from Google.
Read more at: https://www.w3schools.com/graphics/google_maps_basic.asp
-->
</body>

</html>
