<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.json.JSONObject"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.content.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	//String mem_no = (String)session.getAttribute("mem_no");
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	String cropCont_no = (String) request.getParameter("cropCont_no");
	String alb_no = request.getParameter("alb_no");
	ContentService contSvc = new ContentService();
	List<ContentVO> contentList = null;
	if (alb_no != null) {
		contentList = contSvc.getAllByAlbNo(alb_no);
		pageContext.setAttribute("contentList", contentList);
	}

	System.out.println("contentList" + contentList);
%>
<jsp:useBean id="albSvc" scope="page"
	class="com.album.model.AlbumService"></jsp:useBean>

<%@ include file="page/preview_header.file"%>

<!--start Modal select Pic-->
<div class="modal fade" id="selectPicModal" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->

		<div class="modal-content">
			<div class="modal-header" style="padding: 20px 50px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4>
					<span class="glyphicon glyphicon-picture"></span> 您想從哪裡取得照片呢?
				</h4>
			</div>

			<div class="modal-body" style="padding: 40px 50px;">
				<div class='col-xs-6 col-sm-6 col-md-6'>
					<button class='btn btn-danger btn-block' onclick='fromAlbum()'>相簿</button>
				</div>
				<div class='col-xs-6 col-sm-6'>
					<button class='btn btn-danger btn-block' onclick='load_image();'>檔案</button>
				</div>

				<span class="glyphicon glyphicon-off"></span>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<span class="glyphicon glyphicon-remove" id='cancelSelectModal'></span>
					取消
				</button>
			</div>
		</div>

	</div>
</div>
</form>
<!--end Modal select Pic -->


<!--start Modal select Pic from Alb-->
<div class="modal fade" id="selectPicFromAlbModal" role="dialog">
	<div class="modal-dialog modal-lg"
		style='overflow-y: initial !important;'>

		<!-- Modal content-->

		<div class="modal-content">
			<div class="modal-header" style="padding: 20px 50px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4>
					<span class="glyphicon glyphicon-picture"></span> 請選擇相簿與照片
				</h4>
			</div>

			<div class="modal-body"
				style="padding: 40px 50px; height: 500px; overflow-y: auto;">
				<div class="input-group">
					<label class="input-group-addon" for="status">相簿</label> <select
						id="selectAlbum" name="selectAlbum" class="form-control"
						onchange='showContent()'>
						<option value="defalt">請選擇相簿</option>
						<%-- 								<c:forEach var="albVO" items="${albSvc.getAllByMemNo(memVO.mem_no)}" varStatus="s"> --%>
						<c:forEach var="albVO" items="${albSvc.getAllByMemNo(memVO.mem_no)}"
							varStatus="s">
							<option value="${albVO.alb_no}">${albVO.name}</option>
						</c:forEach>
					</select>
				</div>
				<br>
				<div id='changeModal'>
					<c:forEach var="contVO" items="${contentList}" varStatus="s">
						<div class="col-md-3 col-sm-6 col-xs-6"
							style='margin-bottom: 30px;'>
							<label for='${contVO.cont_no}' style='cursor: pointer;'>
								<i class='fa fa-check checkMark' aria-hidden='true'></i> <img
								class="img-responsive" onclick='selectThisPic(this)'
								src="<%=request.getContextPath()%>/ShowPictureServletDAO?cont_no=${contVO.cont_no}" />
							</label> <input type='radio' name='selectPicRadio'
								value='${contVO.cont_no}' id='${contVO.cont_no}'>
						</div>
					</c:forEach>
				</div>

				<span class="glyphicon glyphicon-off"></span>
			</div>
			<div class="modal-footer">
				<button class='btn btn-info' onclick='selectConfirm()'
					data-dismiss="modal">確定選擇</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<span class="glyphicon glyphicon-remove" id='cancelSelectModal'></span>
					取消
				</button>

			</div>
		</div>

	</div>
</div>
</form>
<!--end Modal select Pic from Alb--->



<div class="col-md-8 col-offset-1">
	<div class="row">
		<div class='col-sm-12 col-xs-12'>
			<a type='button' class="btn btn-app btn-default" id='btnLoad'
				onclick='openSelectModal();'><i class="fa fa-image"></i> 選擇照片</a>
			<button class="btn btn-default" id='drawBtn'>
				<i class="fa fa-paint-brush"></i> 畫邊框
			</button>

			<div id="brushWidth"></div>
			<button class="btn btn-default" id='stopDrawing'>不畫了</button>
			<button class="btn btn-default" onclick='clearDrawing();'>
				<i class="fa fa-times"></i> 清除
			</button>
			<button class="btn btn-info" id='submitBtn'>裁切</button>

			<form id="image-form"
				action='<%=request.getContextPath()%>/album/preview.do'
				method="post" enctype="multipart/form-data">

				<input type='hidden' id='xPoints' name='xPoints' value=''> <input
					type='hidden' id='yPoints' name='yPoints' value=''> <input
					type='hidden' name='mem_no' value='${memVO.mem_no}'> <input
					type='hidden' name='action' value='cropImage'> <input
					type="file" name="imageRemove" id='imgfile'
					onchange="preview_images()" style="display: none"> <input
					type='hidden' name='cont_no'>
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
#brushWidth {
	display: none;
	width: 100px;
	margin: 0 8px;
}

input[type="radio"] {
	display: none;
}

.checkMark {
	background-color: white;
	color: white;
	display: block;
	border-radius: 50%;
	border: 1px solid grey;
	position: absolute;
	top: -8px;
	left: -7px;
	width: 25px;
	height: 25px;
	text-align: center;
	line-height: 22px;
	transition-duration: 0.4s;
	transform: scale(0);
}

.selectPicRadio {
	border: 1px solid #ddd;
	padding: 5px;
	display: block;
	position: relative;
	cursor: pointer;
}

.selectPicRadio img {
	transition-duration: 0.2s;
	transform-origin: 50% 50%;
	transform: scale(0.9);
	box-shadow: 0 0 5px #333;
	z-index: -1;
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
		var cont_no;
		
	function init(){
		
		context.font = "30px Sacramento";
		var middleWidth = canvas.width / 2 - 100;
		var middleHeight = canvas.height / 2 -10 ;
		context.fillText("請先選擇照片...",middleWidth, middleHeight);
	}	
	
	// 打開選擇圖片來源的modal
	function openSelectModal(){
		$("#selectPicModal").modal();
	}
	// 從相簿選擇照片
	function fromAlbum(){
		$("#cancelSelectModal").click();
		$("#selectPicFromAlbModal").modal();
		
	}
	// 顯示該相簿照片
	function showContent(){
		var alb_no = $("#selectAlbum").val();
		console.log("alb_no"+alb_no);
		$("#changeModal").load("<%=request.getContextPath()%>/Front_end/Preview/ImageCropper.jsp #changeModal",{
			alb_no :alb_no
		});
	}
	
	// 挑選照片
	function selectThisPic(img){
		var selectedLabel = $("input[type=radio]:checked").prev();
		selectedLabel.removeClass("selectPicRadio").attr("");
		selectedLabel.find("i").css("background-color","white").css("transform","scale(0)");
		var label = $(img).parent();
		console.log("label=="+label);
		label.addClass("selectPicRadio");
		$(img).prev().css("background-color","grey").css("transform","scale(1)");
		cont_no = label.next().attr("id");
		
	}
	
	function selectConfirm(){
		
		//var cont_no = $("input[type=radio]:checked").val();
		var src = $("input[type=radio]:checked").prev().find("img").attr("src");
		clearDrawing();
		$("input[name=cont_no]").val(cont_no);
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
	     image.setAttribute("src",src);
	     image.setAttribute("id","imagePreview");
	     console.log(image);
	     hasImage = true;
	     $("#imgfile").val("");
		
	}
	
	// 從檔案選擇照片	
	function load_image(){
			 
		var input;
		input = document.getElementById('imgfile');
		$("#cancelSelectModal").click();
		input.click();
		
	}
	// 畫到canvas裡
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
	     $("input[name=cont_no]").val("");
	     
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
			canvas.removeEventListener("mousewheel",wheelImage,false);
			$("input[name=cont_no]").val("");
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
	window.addEventListener("load", init, false);
</script>

<%@ include file="page/preview_footer.file"%>