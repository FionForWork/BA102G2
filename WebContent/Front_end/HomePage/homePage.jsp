<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ＨomePage</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="css/bootstrap.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="js/homepage.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href='https://fonts.googleapis.com/css?family=Work Sans' rel='stylesheet'>
    <link href='https://fonts.googleapis.com/css?family=Sacramento' rel='stylesheet'>
    <link href="https://fonts.googleapis.com/earlyaccess/notosanstc.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Hind" rel="stylesheet">
</head>
<style>
    /*body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif;}*/
    
    body,
    h1,
    h2,
    h3,
    h4,
    h5,
    h6 {
        font-family: "Sacramento", Arial, serif;
        font-style: bold;
        color: #F14195;
    }
    
    body,
    html {
/*        font-family: "Work Sans", Arial, sans-serif;*/
        font-family: 'Hind', sans-serif;
        background-color: #f5f5f5;
        height: 100%;
        color: #454B4F;
        font-size: 14px;
        /*    color: #F14195;*/
        line-height: 1.8;
        vertical-align: middle;
    }
    
    .w3-top {
        font-family: 'Hind', sans-serif;
        font-size: 15px;
        font-weight: 700;
        color: #454b4f;
    }
    
    .clearfix{
        font-family: 'Hind', sans-serif;
        font-size: 15px;
        font-weight: 700;
    }
    
    .clearfix h4{
        font-family: 'Hind', sans-serif;
    }
    
    #img1 {
        z-index: -1
    }
    
    #s1 {
        z-index: 1;
        color: #ffffff;
        background-color: #F596AA;
    }
    /*
    #myNavbar{
        color: #000000;
        background-color: #ffffff;
    }
*/
    
    .w3-button:hover {
        color: #454B4F!important;
        background-color: #ffecec!important;
    }
    /* Create a Parallax Effect */
    
    .bgimg-1,
    .bgimg-2,
    .bgimg-3 {
        background-attachment: fixed;
        background-position: center;
        background-repeat: no-repeat;
        background-size: cover;
    }
    /* First image (Logo. Full height) */
    
    .bgimg-1 {
        background-image: url("img/sayyes2.jpg");
        min-height: 100%;
    }
    /* Second image (Portfolio) */
    
    .bgimg-2 {
        background-image: url("img/isaidYes.jpg");
        min-height: 400px;
    }
    /* Third image (Contact) */
    
    .bgimg-3 {
        background-image: url("img/flower.jpg");
        min-height: 400px;
    }
    
    .w3-wide {
        letter-spacing: 10px;
    }
    
    .w3-hover-opacity {
        cursor: pointer;
    }
    /* Turn off parallax scrolling for tablets and phones */
    
    @media only screen and (max-device-width: 1024px) {
        .bgimg-1,
        .bgimg-2,
        .bgimg-3 {
            background-attachment: scroll;
        }
    }
    /*show2*/
    
    .wrapper {
        width: 100%;
        position: relative;
        background-color: #fff;
    }
    
    .wrapper:after {
        padding-top: 60%;
        display: block;
        content: '';
    }
    
    .wrapper .column {
        position: absolute;
        left: 0;
        top: 0;
        width: 20%;
        float: left;
        padding: 6px;
        box-sizing: border-box;
    }
    
    .wrapper .column .inner {
        width: 100%;
        position: relative;
    }
    
    .wrapper .column .inner:after {
        padding-top: 100%;
        /* ratio 1:1 */
        display: block;
        content: '';
    }
    /*
.wrapper .column .inner:before {
  content: ' ';
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  -webkit-transition: 300ms ease-in-out;
  transition: 300ms ease-in-out;
  background-color: rgba(0, 0, 0, 0.4);
}
*/
    
    .wrapper .column .inner:hover:before {
        background-color: rgba(0, 0, 0, 0);
    }
    
    .wrapper .column:nth-child(1) {
        width: 40%;
    }
    
    .wrapper .column:nth-child(2) {
        left: 40%;
    }
    
    .wrapper .column:nth-child(3) {
        left: 40%;
        top: 50%;
        -webkit-transform: translateY(-50%);
        transform: translateY(-50%);
    }
    
    .wrapper .column:nth-child(4) {
        width: 40%;
        left: 60%;
        top: 0;
    }
    
    .wrapper .column:nth-child(4) .inner:after {
        padding-top: 48.7%;
    }
    /*
.wrapper .column:nth-child(8) {
  left: auto;
  right: 0;
  top: 50%;
  -webkit-transform: translateY(-50%);
          transform: translateY(-50%);
}
*/
    
    .wrapper .column:nth-child(5) {
        width: 40%;
        top: auto;
        left: 0;
        bottom: 0;
    }
    
    .wrapper .column:nth-child(5) .inner:after {
        padding-top: 48.7%;
    }
    
    .wrapper .column:nth-child(6) {
        left: 40%;
        top: auto;
        bottom: 0;
        width: 20%;
    }
    
    .wrapper .column:nth-child(7) {
        left: 60%;
        top: auto;
        bottom: 0;
        width: 40%;
    }
    /* BEAUTY */
    
    body,
    html {
        background-color: #fff;
        padding: 0;
    }
    
    .wrapper .column .inner {
        background-size: cover;
        background-position: center;
    }
    
    .wrapper .column:nth-child(1) .inner {
        background-image: url('img/wedding5.jpg');
    }
    
    .wrapper .column:nth-child(2) .inner {
        background-image: url('img/wedding3.jpg');
    }
    
    .wrapper .column:nth-child(3) .inner {
        background-image: url('img/sweet.jpg');
    }
    
    .wrapper .column:nth-child(4) .inner {
        background-image: url('img/wedding7.jpg');
    }
    
    .wrapper .column:nth-child(5) .inner {
        background-image: url('img/wee.jpg');
    }
    
    .wrapper .column:nth-child(6) .inner {
        background-image: url('img/style.jpg');
    }
    
    .wrapper .column:nth-child(7) .inner {
        background-image: url('img/forum.jpg');
    }
    
    .servicetopleft{
        font-family: 'Hind', sans-serif;
        font-size: 18px;
        font-weight: 700;
        background-color: rgba(69, 75, 79, 1);
        color: aliceblue;
        padding: 5px;
    }
    
    #footer {
        background-color: #ffecec;
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
            <a href="#about" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-th"></i> 熱門作品</a>
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
    <div class="container" style="width:90%">
        <div class="row">
            <div class="col-xs-12 col-md-4">
                <!--婚攝item-->
                <a href="#" class="thumbnail">

                    <div class="caption">
                        <div class="media" style="margin-bottom:0px;">
                            <div class="media-left">
                                <img class="img-circle media-object" src="img/LOGO.png" style="height:35px;width:35">
                            </div>
                            <div class="media-body media-middle">
                                <b>J-Love婚禮攝影團隊</b> &nbsp;
                            </div>
                        </div>
                    </div>
                    <div class="rat_4_3 ratiobox bg-cover bg-picture img-label" style="background-image: url('img/ask2.jpg');background-position:50% 50%;">
                    </div>
                    <div class="caption clearfix">

                        <span class="pull-left small" style="margin-top: 4px;margin-left: 3px;"><i class="fa fa-ellipsis-h" aria-hidden="true"></i> 成員共19人</span>
                        <div class="text-right">
                            <span class="small">評價</span>
                            <span class="" style="border-radius: 30px;"><b class="a-rating text-warning" style="font-size: 1.3em;"><span>5</span>
                            </b>
                            <i class="fa fa-star text-warning" aria-hidden="true"></i></span>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-xs-12 col-md-4">
                <!--婚攝item-->
                <a href="#" class="thumbnail">

                    <div class="caption">
                        <div class="media" style="margin-bottom:0px;">
                            <div class="media-left">
                                <img class="img-circle media-object" src="img/LOGO.png" style="height:35px;width:35">
                            </div>
                            <div class="media-body media-middle">
                                <b>H-Love婚禮攝影團隊</b> &nbsp;
                            </div>
                        </div>
                    </div>
                    <div class="rat_4_3 ratiobox bg-cover bg-picture img-label" style="background-image: url('img/wedding2.jpg');background-position:50% 50%;">
                    </div>
                    <div class="caption clearfix">

                        <span class="pull-left small" style="margin-top: 4px;margin-left: 3px;"><i class="fa fa-ellipsis-h" aria-hidden="true"></i> 成員共19人</span>
                        <div class="text-right">
                            <span class="small">評價</span>
                            <span class="" style="border-radius: 30px;"><b class="a-rating text-warning" style="font-size: 1.3em;"><span>5</span>
                            </b>
                            <i class="fa fa-star text-warning" aria-hidden="true"></i></span>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-xs-12 col-md-4">
                <!--婚攝item-->
                <a href="#" class="thumbnail">

                    <div class="caption">
                        <div class="media" style="margin-bottom:0px;">
                            <div class="media-left">
                                <img class="img-circle media-object" src="img/LOGO.png" style="height:35px;width:35">
                            </div>
                            <div class="media-body media-middle">
                                <b>A-Love婚禮攝影團隊</b> &nbsp;
                            </div>
                        </div>
                    </div>
                    <div class="rat_4_3 ratiobox bg-cover bg-picture img-label" style="background-image: url('img/wedding3.jpg');background-position:50% 50%;">
                    </div>
                    <div class="caption clearfix">

                        <span class="pull-left small" style="margin-top: 4px;margin-left: 3px;"><i class="fa fa-ellipsis-h" aria-hidden="true"></i> 成員共19人</span>
                        <div class="text-right">
                            <span class="small">評價</span>
                            <span class="" style="border-radius: 30px;"><b class="a-rating text-warning" style="font-size: 1.3em;"><span>5</span>
                            </b>
                            <i class="fa fa-star text-warning" aria-hidden="true"></i></span>
                        </div>
                    </div>
                </a>
            </div>
        </div>
        <br>
                <div class="row">
            <div class="col-xs-12 col-md-4">
                <!--婚攝item-->
                <a href="#" class="thumbnail">

                    <div class="caption">
                        <div class="media" style="margin-bottom:0px;">
                            <div class="media-left">
                                <img class="img-circle media-object" src="img/LOGO.png" style="height:35px;width:35">
                            </div>
                            <div class="media-body media-middle">
                                <b>J-Love婚禮攝影團隊</b> &nbsp;
                            </div>
                        </div>
                    </div>
                    <div class="rat_4_3 ratiobox bg-cover bg-picture img-label" style="background-image: url('img/photography.jpg');background-position:50% 50%;">
                    </div>
                    <div class="caption clearfix">

                        <span class="pull-left small" style="margin-top: 4px;margin-left: 3px;"><i class="fa fa-ellipsis-h" aria-hidden="true"></i> 成員共19人</span>
                        <div class="text-right">
                            <span class="small">評價</span>
                            <span class="" style="border-radius: 30px;"><b class="a-rating text-warning" style="font-size: 1.3em;"><span>5</span>
                            </b>
                            <i class="fa fa-star text-warning" aria-hidden="true"></i></span>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-xs-12 col-md-4">
                <!--婚攝item-->
                <a href="#" class="thumbnail">

                    <div class="caption">
                        <div class="media" style="margin-bottom:0px;">
                            <div class="media-left">
                                <img class="img-circle media-object" src="img/LOGO.png" style="height:35px;width:35">
                            </div>
                            <div class="media-body media-middle">
                                <b>H-Love婚禮攝影團隊</b> &nbsp;
                            </div>
                        </div>
                    </div>
                    <div class="rat_4_3 ratiobox bg-cover bg-picture img-label" style="background-image: url('img/ask.jpg');background-position:50% 50%;">
                    </div>
                    <div class="caption clearfix">

                        <span class="pull-left small" style="margin-top: 4px;margin-left: 3px;"><i class="fa fa-ellipsis-h" aria-hidden="true"></i> 成員共19人</span>
                        <div class="text-right">
                            <span class="small">評價</span>
                            <span class="" style="border-radius: 30px;"><b class="a-rating text-warning" style="font-size: 1.3em;"><span>5</span>
                            </b>
                            <i class="fa fa-star text-warning" aria-hidden="true"></i></span>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-xs-12 col-md-4">
                <!--婚攝item-->
                <a href="#" class="thumbnail">

                    <div class="caption">
                        <div class="media" style="margin-bottom:0px;">
                            <div class="media-left">
                                <img class="img-circle media-object" src="img/LOGO.png" style="height:35px;width:35">
                            </div>
                            <div class="media-body media-middle">
                                <b>A-Love婚禮攝影團隊</b> &nbsp;
                            </div>
                        </div>
                    </div>
                    <div class="rat_4_3 ratiobox bg-cover bg-picture img-label" style="background-image: url('img/banner3.jpg');background-position:50% 50%;">
                    </div>
                    <div class="caption clearfix">

                        <span class="pull-left small" style="margin-top: 4px;margin-left: 3px;"><i class="fa fa-ellipsis-h" aria-hidden="true"></i> 成員共19人</span>
                        <div class="text-right">
                            <span class="small">評價</span>
                            <span class="" style="border-radius: 30px;"><b class="a-rating text-warning" style="font-size: 1.3em;"><span>5</span>
                            </b>
                            <i class="fa fa-star text-warning" aria-hidden="true"></i></span>
                        </div>
                    </div>
                </a>
            </div>
        </div>
        <br>
                <div class="row">
            <div class="col-xs-12 col-md-4">
                <!--婚攝item-->
                <a href="#" class="thumbnail">

                    <div class="caption">
                        <div class="media" style="margin-bottom:0px;">
                            <div class="media-left">
                                <img class="img-circle media-object" src="img/LOGO.png" style="height:35px;width:35">
                            </div>
                            <div class="media-body media-middle">
                                <b>J-Love婚禮攝影團隊</b> &nbsp;
                            </div>
                        </div>
                    </div>
                    <div class="rat_4_3 ratiobox bg-cover bg-picture img-label" style="background-image: url('img/banner4.jpg');background-position:50% 50%;">
                    </div>
                    <div class="caption clearfix">

                        <span class="pull-left small" style="margin-top: 4px;margin-left: 3px;"><i class="fa fa-ellipsis-h" aria-hidden="true"></i> 成員共19人</span>
                        <div class="text-right">
                            <span class="small">評價</span>
                            <span class="" style="border-radius: 30px;"><b class="a-rating text-warning" style="font-size: 1.3em;"><span>5</span>
                            </b>
                            <i class="fa fa-star text-warning" aria-hidden="true"></i></span>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-xs-12 col-md-4">
                <!--婚攝item-->
                <a href="#" class="thumbnail">

                    <div class="caption">
                        <div class="media" style="margin-bottom:0px;">
                            <div class="media-left">
                                <img class="img-circle media-object" src="img/LOGO.png" style="height:35px;width:35">
                            </div>
                            <div class="media-body media-middle">
                                <b>H-Love婚禮攝影團隊</b> &nbsp;
                            </div>
                        </div>
                    </div>
                    <div class="rat_4_3 ratiobox bg-cover bg-picture img-label" style="background-image: url('img/wedding_2.jpg');background-position:50% 50%;">
                    </div>
                    <div class="caption clearfix">

                        <span class="pull-left small" style="margin-top: 4px;margin-left: 3px;"><i class="fa fa-ellipsis-h" aria-hidden="true"></i> 成員共19人</span>
                        <div class="text-right">
                            <span class="small">評價</span>
                            <span class="" style="border-radius: 30px;"><b class="a-rating text-warning" style="font-size: 1.3em;"><span>5</span>
                            </b>
                            <i class="fa fa-star text-warning" aria-hidden="true"></i></span>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-xs-12 col-md-4">
                <!--婚攝item-->
                <a href="#" class="thumbnail">

                    <div class="caption">
                        <div class="media" style="margin-bottom:0px;">
                            <div class="media-left">
                                <img class="img-circle media-object" src="img/LOGO.png" style="height:35px;width:35">
                            </div>
                            <div class="media-body media-middle">
                                <b>A-Love婚禮攝影團隊</b> &nbsp;
                            </div>
                        </div>
                    </div>
                    <div class="rat_4_3 ratiobox bg-cover bg-picture img-label" style="background-image: url('img/banner2.jpg');background-position:50% 50%;">
                    </div>
                    <div class="caption clearfix">

                        <span class="pull-left small" style="margin-top: 4px;margin-left: 3px;"><i class="fa fa-ellipsis-h" aria-hidden="true"></i> 成員共19人</span>
                        <div class="text-right">
                            <span class="small">評價</span>
                            <span class="" style="border-radius: 30px;"><b class="a-rating text-warning" style="font-size: 1.3em;"><span>5</span>
                            </b>
                            <i class="fa fa-star text-warning" aria-hidden="true"></i></span>
                        </div>
                    </div>
                </a>
            </div>
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
                <form action="/action_page.php" target="_blank">
                    <div class="w3-row-padding" style="margin:0 -16px 8px -16px">
                        <div class="w3-half">
                            <input class="w3-input w3-border" type="text" placeholder="Name" required name="Name">
                        </div>
                        <div class="w3-half">
                            <input class="w3-input w3-border" type="text" placeholder="Email" required name="Email">
                        </div>
                    </div>
                    <input class="w3-input w3-border" type="text" placeholder="Message" required name="Message">
                    <button class="w3-button w3-black w3-right w3-section" type="submit">
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
                                <li itemprop="department"><a href="#" itemprop="url">拍婚紗</a></li>
                                <li itemprop="department"><a href="#" itemprop="url">婚攝婚錄</a></li>
                                <li itemprop="department"><a href="#" itemprop="url">新秘</a></li>
                            </ul>
                        </div>

                        <div class="col-xs-4 text-center">
                            <h4><b>婚禮周邊</b></h4>
                            <ul class="list-unstyled">
                                <li itemprop="department"><a href="#" itemprop="url">實景預覽</a></li>
                                <li itemprop="department"><a href="#" itemprop="url">線上商城</a></li>
                            </ul>
                        </div>

                        <div class="col-xs-4 text-center">
                            <h4><b>新娘專屬</b></h4>
                            <ul class="list-unstyled">
                                <li class=""><a href="#" itemprop="url">討論版</a></li>
                                <li class=""><a href="#" target="_blank" itemprop="url">會員專區</a></li>
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
            <i class="fa fa-linkedin w3-hover-opacity"></i>
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
