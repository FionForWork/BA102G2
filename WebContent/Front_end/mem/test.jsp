<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <title>She Said Yes!</title>
    <meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link href="<%=request.getContextPath()%>/Front_end/Resource/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/Front_end/Resource/css/bootstrap.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/earlyaccess/notosanstc.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Hind" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Sacramento" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Work+Sans:400,300,600,400italic,700' rel='stylesheet' type='text/css'>
    <link href="<%=request.getContextPath()%>/Front_end/Resource/css/bootstrap-multiselect.css" rel="stylesheet">
    <link href="https://cdn.weddingday.com.tw/weddingday-file/v2/css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/Front_end/Resource/css/pnotify.custom.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/Front_end/Resource/css/index.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/Front_end/Album/css/photo.css" rel="stylesheet">
        <link rel="Short Icon" href="<%=request.getContextPath()%>/Front_end/Resource/img/ring_64.ico">
    
    
    <script src="<%=request.getContextPath()%>/Front_end/Resource/js/jquery-3.1.1.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/Front_end/Resource/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/Front_end/Resource/js/top.js" type="text/javascript"></script>
 <script src="<%=request.getContextPath()%>/Front_end/Album/js/photo.js" type="text/javascript"></script>


<link href="<%=request.getContextPath()%>/Front_end/Album/themes/explorer/theme.min.css" media="all" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/Front_end/Album/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/Front_end/Album/js/piexif.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/Front_end/Album/js/purify.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/Front_end/Album/js/fileinput.min.js"></script>
<script src="<%=request.getContextPath()%>/Front_end/Album/themes/explorer/theme.js"></script>
<script src="<%=request.getContextPath()%>/Front_end/Album/themes/fa/theme.js"></script>
<script src="<%=request.getContextPath()%>/Front_end/Album/js/zh-TW.js"></script>




</head>

<body data-spy="scroll" data-target=".wd-navbar" data-offset="50">
    <div class="navbar navbar-wd">
        <div class="container" style="position: relative;">
            <div class="navbar-header" style="margin-bottom:10px;">
                <!--RWD-->
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".wd-menu" aria-expanded="false" style="margin-top: 15px;">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!--RWD-->
                <a class="navbar-brand" href="#">

                    <img alt="Brand" class="pull-left" width="50" height="auto" src="<%=request.getContextPath()%>/Front_end/Resource/img/ring_64.png">
                    <div class="tooltip tt_black right hidden-xs" role="tooltip" style="opacity: 100;top: 20px;left: 55px;z-index:0;">
                        <h3 id="ssy" class="text-default" style="color:#f14195;letter-spacing: .1em;font-size: 2em;margin-top: 6px;"><span><b>
                            She Said Yes!</b></span>
                        </h3>
                    </div>
                </a>
            </div>

            <div class="navbar-collapse collapse wd-menu">
                <ul class="nav nav-mobile-user-menu navbar-nav navbar-right clearfix ">
                    <li>
                        <a class="hidden" href="" role="login">
                            會員專區
                        </a>
                    </li>
                    <li>
                        <a class="hidden" href="" role="login">
                            登出
                        </a>
                    </li>
                    <li>
                        <a class="" href="" role="login">
                            登入
                        </a>
                    </li>
                    <li>
                        <a href="" role="join" title="註冊會員">註冊會員</a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- 從這邊找自己的功能改NavBar選項!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
    </div>
    <nav class="navbar navbar-wd-storelist collapse navbar-collapse">
        <div class="container">
            <ul class="nav navbar-nav" class="collapse navbar-collapse">
                <li class="wd-drop-wrapper">
                    <a itemprop="url" href="#">
                        <h3>
                            拍婚紗
                        </h3>
                    </a>
                    <!--RWD-->
                    <div class="mobile-main-choice visible-xs">
                        <a class="" href="#">婚紗手機1</a>
                    </div>
                    <!--RWD-->
                    <ol class="wd-drop_submenu list-unstyled text-center hidden-xs">
                        <!--下拉選單-->
                        <li>
                            <a href="#"><b>找店家</b></a>
                        </li>
                        <li>
                            <a href="#"><b>看作品</b></a>
                        </li>
                        <li>
                            <a href="#"><b>看方案</b></a>
                        </li>
                        <li>
                            <a href="#"><b>店家地圖</b></a>
                        </li>
                    </ol>
                </li>

                <li class="wd-drop-wrapper">
                    <a href="#">
                        <h3>新秘</h3>
                    </a>
                    <!--RWD-->
                    <div class="mobile-main-choice visible-xs">
                        <a href="#">新秘手機1</a><span> / </span>
                        <a href="#">新秘手機2</a>
                    </div>
                    <!--RWD-->
                    <ol class="wd-drop_submenu list-unstyled text-center hidden-xs">
                        <!--下拉選單-->
                        <li>
                            <a href="#"><b>找店家</b></a>
                        </li>
                        <li>
                            <a href="#"><b>看作品</b></a>
                        </li>
                        <li>
                            <a href="#"><b>看方案</b></a>
                        </li>
                        <li>
                            <a href="#"><b>店家地圖</b></a>
                        </li>
                    </ol>
                </li>
                <li class="wd-drop-wrapper">
                    <a href="#">
                        <h3>婚攝/婚錄</h3>
                    </a>
                    <!--RWD-->
                    <div class="mobile-main-choice visible-xs">
                        <a href="#">婚攝婚錄手機1</a> <span> / </span>
                        <a href="#">婚攝婚錄手機2</a>
                    </div>
                    <!--RWD-->
                    <ol class="wd-drop_submenu list-unstyled text-center hidden-xs">
                        <!--下拉選單-->
                        <li>
                            <a href="#"><b>找店家</b></a>
                        </li>
                        <li>
                            <a href="#"><b>看作品</b></a>
                        </li>
                        <li>
                            <a href="#"><b>看方案</b></a>
                        </li>
                        <li>
                            <a href="#"><b>店家地圖</b></a>
                        </li>
                    </ol>
                </li>
                <li class="wd-drop-wrapper">
                    <a itemprop="url" href="#">
                        <h3>新人問報價</h3>
                    </a>
                    <!--RWD-->
                    <div class="mobile-main-choice visible-xs">
                        <a href="#">手機1</a> <span> / </span>
                        <a href="#">手機2</a>
                    </div>
                    <!--RWD-->
                    <ol class="wd-drop_submenu list-unstyled  hidden-xs">
                        <li>
                            <a href="#"><b><span class="text-pink">新人</span>詢價</b></a>
                        </li>
                        <li>
                            <a href="#"><b>廠商回覆</b></a>
                        </li>
                        <li>
                            <a href="#"><b>歷史報價</b></a>
                        </li>
                    </ol>
                </li>

                <li class="wd-drop-wrapper">
                    <a itemprop="url" href="#">
                        <h3>實景預覽</h3>
                    </a>
                    <!--RWD-->
                    <div class="mobile-main-choice visible-xs">
                        <a href="#">手機1</a><span> / </span>
                        <a href="#">手機2</a>
                    </div>
                    <!--RWD-->
                </li>

                <li class="wd-drop-wrapper">
                    <a itemprop="url" href="#">
                        <h3>婚禮習俗</h3>
                    </a>
                    <!--RWD-->
                    <div class="mobile-main-choice visible-xs">
                        <a href="#">手機1</a><span>
                </div>
                <!--RWD-->
            </li>
            <li class="wd-drop-wrapper">
                <a itemprop="url" href="mallIndex.html" ><h3>線上商城</h3></a>
                <!--RWD-->
                <div class="mobile-main-choice visible-xs">
                    <a href="#">手機1</a><span> / </span>
                        <a href="#">手機2</a>
                    </div>
                    <!--RWD-->
                </li>
                <li class="wd-drop-wrapper">
                    <a itemprop="url" href="#">
                        <h3>討論版</h3>
                    </a>
                    <!--RWD-->
                    <div class="mobile-main-choice visible-xs">
                        <a href="#">手機1</a>
                    </div>
                    <!--RWD-->
                </li>


                <li class="wd-drop-wrapper">
                    <a itemprop="url" href="#" target="_blank">
                        <h3>客服專區</h3>
                    </a>
                    <ol class="wd-drop_submenu list-unstyled text-center hidden-xs">
                        <!--下拉選單-->
                        <li>
                            <a href="#"><b>聯絡我們</b></a>
                        </li>
                    </ol>
                </li>
            </ul>
        </div>
    </nav>
    <!-- 從上面找自己的功能改NavBar選項!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
    <div class="fade-carousel">
        <div class="home-banner fade-carousel" style="overflow: hidden;">
            <div class="item slides">
                <div class="overlay"></div>
                <!-- Overlay 暗化效果 -->
                <div class="slide-1 bg-cover lazy" style="background-image:url('<%=request.getContextPath()%>/Front_end/Resource/img/banner1.jpg');"></div>
                <!--banner改自己的 banner改自己的 banner改自己的 banner改自己的 banner改自己的 banner改自己的 -->
            </div>
        </div>

        <div class="banner_hero text-center">
            <h1 class="" style="font-family: 'Hind', sans-serif;font-size:26px; text-shadow: 0px 1px 10px rgba(0, 0, 0, 1);">
                <b class="hello_word">
            妳需要的婚禮廠商，我們幫妳嚴選
            </b>
            </h1>
            <h2 style="font-family: 'Hind';text-shadow: 0px 1px 10px rgba(0, 0, 0, 1);">80,118位新娘找到了命定廠商</h2>
        </div>

    </div>


    <!--這裡開始-->
    <!--這裡開始-->
    <!--這裡開始-->
    <!--這裡開始-->
    <!--這裡開始-->
    <!--這裡開始-->
    <!--這裡開始-->
    <!--這裡開始-->
    <!--這裡開始-->

    <br>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>

<form action="<%=request.getContextPath()%>/mem/mem.do"
				method="post" enctype="multipart/form-data">
				<div class="modal fade" id="uploadModal" role="dialog">
					<div class="modal-dialog modal-lg">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header" style="padding: 20px 50px;">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4>
									<span class="glyphicon glyphicon-picture"></span> 上傳照片
								</h4>
							</div>
							<div class="modal-body" style="padding: 40px 50px;">
								<div class="form-group">
									<!-- 									<label for="inputFile"> 選擇照片</label> 									 -->
									<input id="inputFile" name="inputFile[]" type="file" multiple
										class="file-loading">

								</div>

								<input type='submit' class="btn btn-info btn-block" value="新增">
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-default pull-left"
									data-dismiss="modal">
									<span class="glyphicon glyphicon-remove"></span> Cancel
								</button>
								<input type='hidden' name='action' value='insert_Content'>
								<input type='hidden' name='alb_no' value=''>
							</div>
						</div>
					</div>
				</div>
			</form>
			
			<div class="jumbotron">
				<div class="row">
					<button type="submit" class="btn btn-info" id="uploadbtn"
						style="float: right; margin-button: 0;">新增相片</button>
					<div class="col-xs-12 col-sm-12">
						<div class="text-center">
							<h2>${albSvc.getOneAlbum(alb_no).name}</h2>
						</div>
					</div>
				</div>
			</div>
</body>
</html>