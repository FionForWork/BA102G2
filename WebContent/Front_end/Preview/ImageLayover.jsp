<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.placeview.model.*"%>
<%@ page import="java.util.*"%>
<%
	//String mem_no = (String) session.getAttribute("mem_no");
	session.setAttribute("mem_no", "1001");
	String cropCont_no = (String) request.getAttribute("cropCont_no");
	//String cropCont_no = "0166";
	String place_no = "1";
	pageContext.setAttribute("place_no", place_no);
	PlaceViewService placeViewSvc = new PlaceViewService();
	List<String> placeviewNoList = placeViewSvc.getAllByFk(place_no);
	pageContext.setAttribute("placeviewNoList", placeviewNoList);
%>

<%@ include file="page/preview_header.file"%>
<div class="container">
	<div class="col-md-offset-1">
		<ul class="breadcrumb">
			<li><a href="#">首頁</a></li>
			<li><a href="#">會員專區</a></li>
			<li class="active">實景預覽</li>
		</ul>
	</div>
</div>
<div class="container">
	<div class="row">
		<!--sidebar sidebar sidebar sidebar sidebar sidebar -->
		<div class="col-md-offset-1 col-md-2 col-xs-0">
			<br>
			<br>
			<br>
			<ul class="list-group">
				<a href="<%=request.getContextPath()%>/Front_end/mem/updatemember.jsp" class="list-group-item menua">編輯個人資料</a>
				<br>
				<a href="<%=request.getContextPath()%>/Front_end/mem/updatePwd.jsp" class="list-group-item menua">密碼修改</a>
				<br>
				<a href="<%=request.getContextPath()%>/Front_end/reservation/memReservation.jsp" class="list-group-item menua">預約紀錄查詢</a>
				<br>
				<a href="<%=request.getContextPath()%>/Front_end/RFQ/listMyRFQ.jsp" class="list-group-item menua">報價紀錄查詢</a>
				<br>
				<a href="<%=request.getContextPath()%>/Front_end/Temp/MemPage_ListAllTemps.jsp" class="list-group-item menua">作品挑選管理</a>
				<br>
				<a href="<%=request.getContextPath()%>/Front_end/Album/ListAllAlbums.jsp" class="list-group-item menua active">我的相簿</a>
				<br>
				<a href="<%=request.getContextPath()%>/Front_end/ComTra/ListAllComTra.jsp" class="list-group-item menua">我的最愛</a>
				<br>
				<a href="<%=request.getContextPath()%>/Front_end/Preview/ImageCropper.jsp" class="list-group-item menua">實景預覽</a>
				<br>
				<a href="<%=request.getContextPath()%>/Front_end/mall/index.jsp" class="list-group-item menua">商城專區</a>
				<br>
			</ul>


			<a href="<%=request.getContextPath()%>/Front_end/mem/listOneMem.jsp"
				class="btn btn-block btn-default">查看個人資料</a>
		</div>
		<div class="col-md-8 col-offset-1">

				<div class='row'>

					<form action='<%=request.getContextPath()%>/album/preview.do'
						method="post" enctype="multipart/form-data" id='overlayImageForm'>

						<input type='hidden' id='placeview_no' name='placeview_no' value=''> 
						<input type='hidden' name='cropCont_no' value='<%=cropCont_no%>'>
						<input type='hidden' name='mem_no' value='${mem_no}'> 
						<input type='hidden' id='xPoint' name='xPoint' value=''>
						<input type='hidden' id='yPoint' name='yPoint' value=''>
						<input type='hidden' name='cropWidth' value=''> 
						<input type='hidden' name='cropHeight' value=''> 
						<input type='hidden' name='action' value='overlayImage'>
						<input type="file" name="imgfile" id='imgfile'
							onchange="preview_images()" style="display: none">
							 
						
					</form>
					<a type='button' class="btn btn-app btn-default" id='btnLoad'
							onclick='load_image();'><i class="fa fa-image"></i> 選擇背景照片</a>
					<button class='btn btn-default' id='clearBtn'><i class="fa fa-times"></i> 清除背景照</button>
					<button onclick="history.back()" class='btn btn-default'>回上一頁</button>
					<input type='submit' value='確定' id='submitBtn' class='btn btn-info'>
				</div>
				<div class='row'>
					<div class='col-xs-12 col-sm-12'>
						<div id="placeViewCarousel" class="carousel"
							data-ride="carousel" style="height: 180px">
							<!-- Wrapper for slides -->
							<div class="carousel-inner" style="height: 180px">
								<c:forEach var='placeview_no' items='${placeviewNoList}'
									varStatus="s">

									<c:if test="${s.count == 1}">
										<div class="item active">
									</c:if>
									<c:if test="${s.count != 1 && s.count % 4 == 1}">
										<div class="item">
									</c:if>

									<div class="col-md-3 col-sm-3 col-xs-6">
										<div class='image-container'>
											<img class="img-responsive img-thumbnail placeView"
												id='${placeview_no}'
												src="<%=request.getContextPath()%>/image/ShowImage?view_no=${placeview_no}">
										</div>

									</div>
									<c:if test="${s.count % 4 == 0 || s.count == placeviewNoList.size()}">
							</div>
							</c:if>
							</c:forEach>


							<!-- Left and right controls -->
							<a class="left carousel-control" href="#placeViewCarousel"
								data-slide="prev" style="height: 180px"> <i class="fa fa-angle-left"
								style="font-size: 48px;"></i>
							</a> <a class="right carousel-control" href="#placeViewCarousel"
								data-slide="next" style="height: 180px"> <i class="fa fa-angle-right"
								style="font-size: 48px;"></i>
							</a>
						</div>
					</div>
				</div>
			<div class='row'>

				<div class='col-sm-12 col-xs-12'>
					<div id="dropZone">
						<div id='dragCrop' style="display: inline-block">
							<img id='resizable'
								src='<%=request.getContextPath()%>/ShowPictureServletDAO?cont_no=<%=cropCont_no%>'>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script>
	
	var xPoint,yPoint;
	var image;
	var imageWidth,imageHeight;
	var hasBackgroundImage = false;
	
	function load_image(){
		 
		   var input;
		   input = document.getElementById('imgfile');
		   input.click();
		  
	}
	function preview_images() {
		
	     var total_file=document.getElementById("imgfile").files.length;
	     image = new Image();
	     for(var i = 0; i < total_file; i++){
	    	 if(event.target.files[i].type.match('image.*')){
	    		 $('#dropZone').css("background","url("+URL.createObjectURL(event.target.files[i])+")")
	    		 				.css("background-repeat","no-repeat");
	    $("input[name=placeview_no]").val(""); 
	    	 }
	     }
	     hasBackgroundImage = true;
	}
	
	function doFirst(){
		placeView = document.getElementsByClassName('placeView');
		for(var i = 0; i < placeView.length; i++){
			placeView[i].addEventListener('dragstart',startDrag,false);
			placeView[i].addEventListener('dragend',endDrag,false);	
		}
		
		
		dropZone = document.getElementById('dropZone');
//		rightbox.addEventListener('dragenter',function(e){e.preventDefault();},false);
		dropZone.addEventListener('dragover',function(e){e.preventDefault();},false);
		dropZone.addEventListener('drop',dropped,false);
	}
	function startDrag(e){
		
		var data = e.target.getAttribute("id");
		e.dataTransfer.setData('data',data);
	}
	function endDrag(){
		
	}
	function dropped(e){
		e.preventDefault();
		var placeview_no = e.dataTransfer.getData('data');
		dropZone.style.backgroundImage = "url('<%=request.getContextPath()%>/image/ShowImage?view_no="+ placeview_no + "')";
		dropZone.style.backgroundRepeat = "no-repeat";
		document.getElementById("placeview_no").setAttribute("value",placeview_no);
		hasBackgroundImage = true;
	}
	window.addEventListener('load',doFirst,false);
	
		$(function() {
			
			 var origin = {x: 0, y: 0},
		        start = {x: 0, y: 0},
		        movecontinue = false;
			 
			var $dropZone = $("#dropZone");

			
			// cropCont拖曳與縮放功能
			$("#dragCrop").on("click", function() {
				
				$("#dragCrop").css("border","1px solid lightgray").draggable({
					containment : "#dropZone",
					scroll : false,
					drag : function() {
						var $this = $(this);
						var thisPos = $this.position();
						console.log("thisPos : " + thisPos);

						xPoint = thisPos.left;
						yPoint = thisPos.top;

						console.log("xPoint : " + xPoint);
						console.log("yPoint : " + yPoint);

					}
				});
				$("#resizable").resizable({
					containment : "#dropZone",
					resize : function(event, ui) {
						$("input[name=cropWidth]").val(ui.size.width);
						$("input[name=cropHeight]").val(ui.size.height);
					}
				});

			});

			$("#submitBtn").on("click", function() {
				if(hasBackgroundImage == false){return;}
				var JSONxPoint = JSON.stringify(xPoint);
				var JSONyPoint = JSON.stringify(yPoint);
				$("#xPoint").val(JSONxPoint);
				$("#yPoint").val(JSONyPoint);
				$("#overlayImageForm").submit();
			});

			$("#clearBtn").on("click", function() {
				$dropZone.css("background", "none");
				hasBackgroundImage = false;
			});

			// 移動背景
			// 		    function move (e){
			// 		        var moveby = {
			// 		            x: origin.x - e.clientX, 
			// 		            y: origin.y - e.clientY
			// 		        };

			// 		        if (movecontinue === true) {
			// 		            start.x = start.x - moveby.x;
			// 		            start.y = start.y - moveby.y;

			// 		            $(this).css('background-position', start.x + 'px ' + start.y + 'px');
			// 		        }

			// 		        origin.x = e.clientX;
			// 		        origin.y = e.clientY;

			// 		        e.stopPropagation();
			// 		        return false;
			// 		    }

			// 		    function handle (e){
			// 		        movecontinue = false;
			// 		        $dropZone.unbind('mousemove', move);

			// 		        if (e.type == 'mousedown') {
			// 		            origin.x = e.clientX;
			// 		            origin.y = e.clientY;
			// 		            movecontinue = true;
			// 		            $dropZone.bind('mousemove', move);
			// 		        } else {
			// 		            $(document.body).focus();
			// 		        }

			// 		        e.stopPropagation();
			// 		        return false;
			// 		    }

			// 		    function reset (){
			// 		        start = {x: 0, y: 0};
			// 		        $(this).css('backgroundPosition', '0 0');
			// 		    }
			// 		    if ($("#dropZone").css('background-image') != 'none') {

			// 			 	$dropZone.bind('mousedown mouseup mouseleave', handle);
			// 			    $dropZone.bind('dblclick', reset);
			// 			}

		});
	</script>
	<%@ include file="page/preview_footer.file"%>