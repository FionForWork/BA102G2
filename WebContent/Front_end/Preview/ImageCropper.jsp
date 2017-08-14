<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.json.JSONObject"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	//String mem_no = (String)session.getAttribute("mem_no");
	session.setAttribute("mem_no","1001");
	String originalCont_no = (String) request.getAttribute("originalCont_no");
	String cropCont_no = (String) request.getAttribute("cropCont_no");
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
				<a
					href="<%=request.getContextPath()%>/Front_end/mem/updatemember.jsp"
					class="list-group-item menua">編輯個人資料</a>
				<br>

				<a href="<%=request.getContextPath()%>/Front_end/mem/updatePwd.jsp"
					class="list-group-item menua">密碼修改</a>
				<br>

				<a href="#" class="list-group-item menua">預約紀錄查詢</a>
				<br>
				<a href="#" class="list-group-item menua">報價紀錄查詢</a>
				<br>
				<a
					href="<%=request.getContextPath()%>/Front_end/Temp/MemPage_ListAllTemps.jsp"
					class="list-group-item menua">作品挑選管理</a>
				<br>

				<a
					href="<%=request.getContextPath()%>/Front_end/Album/ListAllAlbums.jsp"
					class="list-group-item menua">我的相簿</a>
				<br>

				<a href="<%=request.getContextPath()%>/Front_end/ComTra/ListAllComTra.jsp" class="list-group-item menua">我的最愛</a>
				<br>
				<a href="#" class="list-group-item menua">實景預覽</a>
				<br>
				<a
					href="<%=request.getContextPath()%>/Front_end/mall/mallIndexAJAX.jsp"
					class="list-group-item menua">商城專區</a>
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
					<button class="btn btn-default" onclick='clearDrawing()'><i class="fa fa-times"></i> 清除</button>
					<button class="btn btn-info" id='submitBtn'>裁切</button>

					<form id="image-form"
						action='<%=request.getContextPath()%>/album/preview.do'
						method="post" enctype="multipart/form-data">

						<input type='hidden' id='xPoints' name='xPoints' value=''>
						<input type='hidden' id='yPoints' name='yPoints' value=''>
						<input type='hidden' name='mem_no' value='${mem_no}'> 
						<input type='hidden' name='action' value='cropImage'> <input
							type="file" name="imageRemove" id='imgfile'
							onchange="preview_images()" style="display: none"> 
					</form>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<div id="canvas-wrapper">
						<canvas id="canvas" width="790" height="500"></canvas>
					</div>
				</div>
			</div>
		</div>
<script type="text/javascript">
		
		var canvas = document.getElementById("canvas");
		var context = canvas.getContext("2d");
		var xPoints = [];
		var yPoints = [];
		var image;
	
	// 預覽圖片	
	function load_image(){
			 
		var input;
		input = document.getElementById('imgfile');
		input.click();
	}
	
	function preview_images() {
		image = new Image();
	    image.onload = function() {
	        context.drawImage(image,0,0,image.width,image.height);
	     };
	     var total_file=document.getElementById("imgfile").files.length;
	     console.log(context);
	     for(var i = 0; i < total_file; i++){
	         if(event.target.files[i].type.match('image.*')){
	            image.setAttribute("src",URL.createObjectURL(event.target.files[i]));
	            image.setAttribute("id","imagePreview");
	            console.log(image);
	    		image.addEventListener("mousewheel",wheelImage,false);
	    		console.log(image);
	         }
	     }
	}
	// 滑鼠滾動事件處發圖片縮放效果
	function wheelImage(e){
		var width = parseInt(window.getComputedStyle(this).width);
		var height = parseInt(window.getComputedStyle(this).height);
		var zoom = 40;
		
		if(e.wheelDelta > 0){
			this.style.width = Math.min(1500,width + zoom) + "px";
			this.style.height = Math.min(1500,height + zoom) + "px";
		}else{
			this.style.width = Math.max(200,width - zoom) + "px";
			this.style.height = Math.max(200,height - zoom) + "px";
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
		
		function clearDrawing(){
			context.clearRect(0, 0, canvas.width, canvas.height);
		}
		
		
		$(document).ready(function(e) {
			
			var stillDown = false;
			var canvas = $("#canvas");
			
			// Drawing Effect
			$("#drawBtn").on("click",function(e){
				console.log("1111111111");
				canvas.css("cursor","crosshair");
				canvas.on("mousedown", function(e) {
					stillDown = true;
					console.log("down");
                    var pos = getMousePos(document.getElementById("canvas"), e);
					context.beginPath();
                    console.log("click" + pos.x + "," +  pos.y)
					context.moveTo(pos.x, pos.y);

				});
				canvas.on("mousemove", function(e) {
					if (!stillDown) {
						return;
					}
					console.log("moving");
					context.lineWidth = 10;
					context.strokeStyle = "green";
					context.lineCap = 'round';
                    var pos = getMousePos(document.getElementById("canvas"), e);
					context.lineTo(pos.x, pos.y);
					xPoints.push(pos.x);
					yPoints.push(pos.y);
					context.stroke();

				});
				canvas.on("mouseup", function(e) {
					stillDown = false;
					context.closePath();
				});
			});
			
			$("#submitBtn").on("click", function() {
				var JSONxPoints = JSON.stringify(xPoints);
				var JSONyPoints = JSON.stringify(yPoints);
				$("#xPoints").val(JSONxPoints);
				$("#yPoints").val(JSONyPoints);
				console.log("XXX");
				$("#image-form").submit();
				console.log("YYY");
			});
			
		});
		
	</script>

		<%@ include file="page/preview_footer.file"%>