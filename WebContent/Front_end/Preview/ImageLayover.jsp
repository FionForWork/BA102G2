<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.placeview.model.*"%>
<%@ page import="com.place.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*" %>
<%
	//String mem_no = (String) session.getAttribute("mem_no");
	//session.setAttribute("mem_no", "1001");
	String place_no = null;
	MemVO memVO = (MemVO)session.getAttribute("memVO");  
	String cropCont_no = (String) request.getAttribute("cropCont_no");
	System.out.println("cropCont_no"+cropCont_no);	
	PlaceService placeSvc = new PlaceService();
	place_no = request.getParameter("pla_no");
	System.out.println("place_no=="+place_no);
	
	if(place_no == null || place_no.trim().length() == 0){
		int placeCount = placeSvc.getAllCount();
		double doubleplace_no = (placeCount * Math.random()+1);
		place_no = String.valueOf((int)Math.floor(doubleplace_no));
	}
	PlaceVO place = placeSvc.getOnePlace(place_no);
	pageContext.setAttribute("place",place);
	PlaceViewService placeViewSvc = new PlaceViewService();
	List<String> placeviewNoList = placeViewSvc.getAllByFk(place_no);
	
	pageContext.setAttribute("place_no", place_no);
	pageContext.setAttribute("placeviewNoList", placeviewNoList);
%>

<%@ include file="page/preview_header.file"%>
<div class="col-md-8 col-offset-1">

	<div class='row'>

		<form action='<%=request.getContextPath()%>/album/preview.do'
			method="post" enctype="multipart/form-data" id='overlayImageForm'>

			<input type='hidden' id='placeview_no' name='placeview_no' value=''>
			<input type='hidden' name='imageWidth' value=''> <input
				type='hidden' name='cropCont_no' value='${cropCont_no}'> <input
				type='hidden' name='mem_no' value='${memVO.mem_no}'>  <input
				type='hidden' id='xPoint' name='xPoint' value=''> <input
				type='hidden' id='yPoint' name='yPoint' value=''> <input
				type='hidden' name='cropWidth' value=''> <input
				type='hidden' name='cropHeight' value=''> <input
				type='hidden' name='action' value='overlayImage'> <input
				type="file" name="imgfile" id='imgfile' onchange="preview_images()"
				style="display: none">


		</form>
		<a type='button' class="btn btn-app btn-default" id='btnLoad'
			onclick='load_image();'><i class="fa fa-image"></i> 選擇背景照片</a>
		<button class='btn btn-default' id='clearBtn'>
			<i class="fa fa-times"></i> 清除背景照
		</button>
		<button
			onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/Preview/ImageCropper.jsp?cropCont_no=<%=cropCont_no%>'"
			class='btn btn-default'>回上一頁</button>
		<input type='submit' value='確定' id='submitBtn' class='btn btn-info'>
	</div>
	<div class='row'>
		<div class='col-xs-12 col-sm-12'>
		<h4><i class="fa fa-map-marker" aria-hidden="true"></i>&nbsp${place.name}</h4>
			<div id="placeViewCarousel" class="carousel" data-ride="carousel"
				style="height: 180px">
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
						<c:if
							test="${s.count % 4 == 0 || s.count == placeviewNoList.size()}">
				</div>
				</c:if>
				</c:forEach>


				<!-- Left and right controls -->
				<a class="left carousel-control" href="#placeViewCarousel"
					data-slide="prev" style="height: 150px"> <i
					class="fa fa-angle-left" style="font-size: 48px;"></i>
				</a> <a class="right carousel-control" href="#placeViewCarousel"
					data-slide="next" style="height: 150px"> <i
					class="fa fa-angle-right" style="font-size: 48px;"></i>
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
	var hasBackgroundImage = false;
	
	function load_image(){
		 
		   var input;
		   input = document.getElementById('imgfile');
		   input.click();
		  
	}
	function preview_images() {
		dropZone = $('#dropZone');
		var dropZoneWidth = dropZone.css("width");
		console.log(dropZoneWidth);
	     var file = event.target.files[0];
	     image = new Image();
	     
		 image.onload = function() {
			 console.log(image.height);
			 console.log("image.width "+image.width);
		    if(image.width > 840){
		    	//dropZone.css("height",image.height);
		    	dropZone.css("background-size",dropZoneWidth);
		    	//dropZone.css("background-size","contain");
		    	var imageWidth = dropZoneWidth.substring(0,dropZoneWidth.length-2);
		    	$("input[name=imageWidth]").val(imageWidth);
		    }
		 };
	    	if(file.type.match('image.*')){
	    		image.setAttribute("src",URL.createObjectURL(file));
	    		dropZone.css("background-image","url("+URL.createObjectURL(file)+")")
	    		 		.css("background-repeat","no-repeat").css("background-size","auto");
	    		$("input[name=placeview_no]").val(""); 
	    		
	    	 }else{
	    		 return;
	    	 }
	   	 console.log(image);
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
		console.log("data "+data);
		e.dataTransfer.setData('data',data);
	}
	function endDrag(){
		
	}
	function dropped(e){
		e.preventDefault();
		var placeview_no = e.dataTransfer.getData('data');
		console.log("placeview_no "+placeview_no);
		dropZone = $('#dropZone');
		var dropZoneWidth = dropZone.css("width");
		console.log(dropZoneWidth);
		image = new Image();
		console.log("image "+image);
		
		image.onload = function() {
			 console.log(image.height);
			 console.log("image.width "+image.width);
		    if(image.width > 840){
		    	//dropZone.css("height",image.height);
		    	dropZone.css("background-size",dropZoneWidth);
		    	//dropZone.css("background-size","contain");
		    	var imageWidth = dropZoneWidth.substring(0,dropZoneWidth.length-2);
		    	$("input[name=imageWidth]").val(imageWidth);
		    }
		 };
		 image.setAttribute("src","<%=request.getContextPath()%>/image/ShowImage?view_no="+placeview_no);
		$("#dropZone").css("background-image","url(<%=request.getContextPath()%>/image/ShowImage?view_no="+placeview_no+")")
					.css("background-repeat","no-repeat").css("background-size", "auto");
		
		$("input[name=placeview_no]").val(placeview_no);
		//document.getElementById("placeview_no").setAttribute("value",placeview_no);
		hasBackgroundImage = true;
	}
	window.addEventListener('load', doFirst, false);

	$(function() {

		var origin = {
			x : 0,
			y : 0
		}, start = {
			x : 0,
			y : 0
		}, movecontinue = false;

		var $dropZone = $("#dropZone");

		// cropCont拖曳與縮放功能
		$("#dragCrop").on("click", function() {

			$("#dragCrop").css("border", "1px solid lightgray").draggable({
				containment : "#dropZone",
				scroll : false,
				drag : function() {
					var $this = $(this);
					var thisPos = $this.position();
					console.log("thisPos : " + thisPos);

					xPoint = thisPos.left -12 ;
					yPoint = thisPos.top-2;

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
			if (hasBackgroundImage == false) {
				return;
			}
			var JSONxPoint = JSON.stringify(xPoint);
			var JSONyPoint = JSON.stringify(yPoint);
			$("#xPoint").val(JSONxPoint);
			$("#yPoint").val(JSONyPoint);
			$("#overlayImageForm").submit();
		});

		$("#clearBtn").on(
				"click",
				function() {
					$dropZone.css("background", "none").css("width", "auto")
							.css("height", "500px");
					hasBackgroundImage = false;
				});

	});
</script>
<%@ include file="page/preview_footer.file"%>