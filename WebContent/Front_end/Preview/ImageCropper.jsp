<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.json.JSONObject"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	//String mem_no = (String)session.getAttribute("mem_no");
	session.setAttribute("mem_no","1001");
	String cropCont_no = (String) request.getParameter("cropCont_no");
	//String cropCont_no = "0129";
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
			<br> <br> <br>
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
				<a href="<%=request.getContextPath()%>/Front_end/Album/ListAllAlbums.jsp" class="list-group-item menua">我的相簿</a>
				<br>
				<a href="<%=request.getContextPath()%>/Front_end/ComTra/ListAllComTra.jsp" class="list-group-item menua">我的最愛</a>
				<br>
				<a href="<%=request.getContextPath()%>/Front_end/Preview/ImageCropper.jsp" class="list-group-item menua active">實景預覽</a>
				<br>
				<a href="<%=request.getContextPath()%>/Front_end/mall/index.jsp" class="list-group-item menua">商城專區</a>
				<br>
			</ul>


			<a href="<%=request.getContextPath()%>/Front_end/mem/listOneMem.jsp"
				class="btn btn-block btn-default">查看個人資料</a>
		</div>
		<div class="col-md-8 col-offset-1">
			<div class="row">
				<div class='col-sm-12 col-xs-12'>
					<a type='button' class="btn btn-app btn-default" id='btnLoad'
					onclick='load_image();'><i class="fa fa-image"></i> 選擇照片</a>
					<button class="btn btn-default" id='drawBtn'><i class="fa fa-paint-brush"></i> 畫邊框</button>
					
<!-- 						<input type="text" id="brushWidth1" readonly style="border:0; color:#f6931f; font-weight:bold;"> -->
<!-- 						<input type="range" id="brushWidth" value="5" max='10' min='1'> -->
					<div id="brushWidth" ></div>
					<button class="btn btn-default" id='stopDrawing'> 不畫了</button>
					<button class="btn btn-default" onclick='clearDrawing();'><i class="fa fa-times"></i> 清除</button>
					<button class="btn btn-info" id='submitBtn'>裁切</button>

					<form id="image-form"
						action='<%=request.getContextPath()%>/album/preview.do'
						method="post" enctype="multipart/form-data">

						<input type='hidden' id='xPoints' name='xPoints' value=''>
						<input type='hidden' id='yPoints' name='yPoints' value=''>
						<input type='hidden' name='mem_no' value='${mem_no}'> 
						<input type='hidden' name='action' value='cropImage'> 
						<input
							type="file" name="imageRemove" id='imgfile'
							onchange="preview_images()" style="display: none"> 
					</form>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<div id="canvas-wrapper">
						<canvas id="canvas" width="800" height="500"></canvas>
					</div>
				</div>
			</div>
		</div>
		
<style>
	#brushWidth{
		display:none;
 		width: 100px; 
		margin: 0 8px;
	}

</style>
<script type="text/javascript">
		
		var canvas = document.getElementById("canvas");
		var context = canvas.getContext("2d");
		var xPoints = [];
		var yPoints = [];
		var image;
		var lineWidth = 10;
		var hasImage = false;
		var hasBeenDrawn = false;
		
	function init(){
		
		context.font = "30px Sacramento";
		var middleWidth = canvas.width / 2 - 100;
		var middleHeight = canvas.height / 2 -10 ;
		context.fillText("請先選擇照片...",middleWidth, middleHeight);
	}	
	
		
	// 預覽圖片	
	function load_image(){
			 
		var input;
		input = document.getElementById('imgfile');
		input.click();
	}
	
	function preview_images() {
		clearDrawing();
		var file = event.target.files[0];
		image = new Image();
	    image.onload = function() {
	    	if(image.width > 800 || image.height > 500){
	    		canvas.setAttribute("width",image.width);
	    		canvas.setAttribute("height",image.height);
	    		canvas.addEventListener("mousewheel",wheelImage,false);
	    	}
	    	context.drawImage(image,0,0,image.width,image.height);
	        
	     };
	     console.log(context);
	     
	         if(file.type.match('image.*')){
	            image.setAttribute("src",URL.createObjectURL(file));
	            image.setAttribute("id","imagePreview");
	            console.log(image);
	         }
	     
	     hasImage = true;
	     
	}
	// 滑鼠滾動事件處發圖片縮放效果
	function wheelImage(e){
		var width = parseInt(window.getComputedStyle(this).width);
		var height = parseInt(window.getComputedStyle(this).height);
		var zoom = 10;
		
		if(e.wheelDelta > 0){
			this.style.width = Math.min(1500,width + zoom) + "px";
			//this.style.height = Math.min(1500,height + zoom) + "px";
		}else{
			this.style.width = Math.max(200,width - zoom) + "px";
			//this.style.height = Math.max(200,height - zoom) + "px";
		}
		e.preventDefault();
	}
	
		// 校準滑鼠座標	
		function  getMousePos(canvas, evt) {
          var rect = canvas.getBoundingClientRect(), // abs. size of element
              scaleX = canvas.width / rect.width,    
              scaleY = canvas.height / rect.height;  

          return {
            x: (evt.clientX - rect.left) * scaleX,   // scale mouse coordinates after they have
            y: (evt.clientY - rect.top) * scaleY     // been adjusted to be relative to element
          }
        }
		
		// Clear the Drawing and Image
		function clearDrawing(){
			context.clearRect(0, 0, canvas.width, canvas.height);
			xPoints=[];
			yPoints=[];
			hasImage = false;
		}
		
		$(document).ready(function(e) {
			
			var stillDown = false;
			var canvas = $("#canvas");
			
			function mouseDownHandler(e){
				stillDown = true;
				var pos = getMousePos(document.getElementById("canvas"), e);
				this.X = pos.x ;
			    this.Y = pos.y ;
				console.log("down");
			}
			
			function mouseMoveHandler(e){
				if (!stillDown) {
					return;
				}
				console.log("moving");
				context.beginPath();
				context.moveTo(this.X, this.Y);
				context.lineCap = 'round';
				context.lineWidth = lineWidth;
				context.strokeStyle = "#80ff00";
				var pos = getMousePos(document.getElementById("canvas"), e);
				context.lineTo(pos.x, pos.y);
				context.stroke();
				xPoints.push(pos.x);
				yPoints.push(pos.y);
		         this.X = pos.x ;
		         this.Y = pos.y;
			}
			
			function mouseUpHandler(e){
				stillDown = false;
				context.closePath();
			}
			
			// Start Drawing
			$("#drawBtn").on("click",function(e){
				canvas.off("mousewheel",wheelImage);
				console.log("1111111111");
				$("#brushWidth").css("display","inline-block");
				canvas.css("cursor","crosshair");
				canvas.on("mousedown", mouseDownHandler);
				canvas.on("mousemove", mouseMoveHandler);
				canvas.on("mouseup", mouseUpHandler);
				hasBeenDrawn = true;
			});
			// Stop Drawing
			$("#stopDrawing").on("click",function(e){
				console.log("22222222222");
				$("#brushWidth").css("display","none");
				canvas.css("cursor","default");
				canvas.off("mousedown", mouseDownHandler);
				canvas.off("mousemove", mouseMoveHandler);
				canvas.off("mouseup", mouseUpHandler);
				
			});
			
			// Submit
			$("#submitBtn").on("click", function() {
				console.log("hasBeenDrawn"+hasBeenDrawn);
				console.log("hasImage"+hasImage);
				if(hasBeenDrawn == false || hasImage == false){return;}
				var JSONxPoints = JSON.stringify(xPoints);
				var JSONyPoints = JSON.stringify(yPoints);
				$("#xPoints").val(JSONxPoints);
				$("#yPoints").val(JSONyPoints);
				console.log("XXX");
				$("#image-form").submit();
				
				
			});
			
			// Change Brush Width
			$("#brushWidth").slider({
			      range: "min",
			      value: 10,
			      min: 1,
			      max: 20,
			      slide: function( event, ui ) {
			    	 lineWidth = ui.value;
			      }
			});
			
		});
		
		<%if (cropCont_no != null) {%>
		var img = new Image();
		var output = document.getElementById("canvas").getContext("2d");
		img.onload = function() {
		output.drawImage(img, 0, 0);
		hasImage = true;
		};
		img.src ="<%=request.getContextPath()%>/ShowPictureServletDAO?cont_no=<%=cropCont_no%>";
	<%}%>
	
		window.addEventListener("load",init,false);
	</script>

		<%@ include file="page/preview_footer.file"%>