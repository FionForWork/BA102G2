<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.json.JSONObject"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Title Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

<script src="https://code.jquery.com/jquery.js"></script>
<script src="js/jquery.mousewheel.min.js"></script>
<script src="js/fabric.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>


<style type="text/css">
#canvas-bg {
	float: left;
	text-align: left;
	height: 70px;
}

#canvas-wrapper {
	position: relative;
	width: 650px;
	margin: auto;
}

canvas {
	border: dashed 1px #808080;
}

#canvas-background {
	display: block;
	width: 100%;
	margin-top: 5px;
}

#output_canvas {
	background-color: white;
	background-image: linear-gradient(45deg, #eee 25%, transparent 25%, transparent 75%, #eee
		75%, #eee),
		linear-gradient(45deg, #eee 25%, transparent 25%, transparent 75%, #eee
		75%, #eee);
	background-size: 30px 30px;
	background-position: 0 0, 15px 15px
}
</style>
</head>
<%
	String originalCont_no = (String) request.getAttribute("originalCont_no");
	//String originalCont_no = "0128";
	System.out.println("jsp original==" + originalCont_no);
	String cropCont_no = (String) request.getAttribute("cropCont_no");
	//String cropCont_no = "0129";
	System.out.println("jsp crop==" + cropCont_no);
%>


<body>

	<div class="container">
		<div class="row">
			<button class="btn btn-default" id='drawBtn'>畫邊框</button>
			<button class="btn btn-default" id='clearBtn'>清除</button>


			<form id="image-form"
				action='<%=request.getContextPath()%>/album/preview.do'
				method="post" enctype="multipart/form-data">

				<input type='hidden' id='xPoints' name='xPoints' value=''> 
				<input type='hidden' id='yPoints' name='yPoints' value=''> 
				<input type='hidden' name='action' value='cropImage'>
				<input
					type="file" name="imageRemove" id='imgfile'
					onchange="preview_images()" style="display: none">
				<a type='button' class="btn btn-app btn-default" id='btnLoad'
					onclick='load_image();'><i class="fa fa-image"></i>Add image</a>
			</form>
			<button class="btn btn-info" id='submitBtn'>裁切</button>
		</div>

		<div class="row">
			<div class="col-xs-6 col-sm-6">
				<div id="canvas-wrapper">
					<canvas id="canvas" width="550" height="640"></canvas>
				</div>


			</div>
			<div class="col-xs-6 col-sm-6">
				<div class=''>

					<canvas id="output_canvas" width="550" height="640"></canvas>
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
		var imageCanvas;
		

	function preview_images() {
	    
// 		imageCanvas = document.createElement("canvas");
// 		imageContext = imageCanvas.getContext('2d');
// 		image = new Image();
// 		image.onload = function() {
			
// 			imageCanvas.width = image.width / 2;
// 			imageCanvas.height= image.height / 2;
// 			imageCanvas.setAttribute("position","absolute");
// 			imageCanvas.setAttribute("id","imageCanvas");
// 			imageContext.scale(0.5,0.5);
// 	        imageContext.drawImage(image,0,0,image.width,image.height);
// 	        context.drawImage(imageCanvas,0,0,image.width,image.height);
// 	    };
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
	           // var previewImage = document.getElementById("imagePreview");
	            console.log(image);
	    		image.addEventListener("mousewheel",wheelImage,false);
	    		console.log(image);
	         }
	     }
	}
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
	
	
			<%if (cropCont_no != null) {%>
			var img = new Image();
			var output = document.getElementById("output_canvas").getContext("2d");
			img.onload = function() {
				output.drawImage(img, 0, 0);
			};
			img.src ="<%=request.getContextPath()%>/ShowPictureServletDAO?cont_no=<%=cropCont_no%>";
		<%}%>
		
		<%if (originalCont_no != null) {%>
			var image = new Image();
			image.onload = function() {
		        context.drawImage(image,0,0,image.width,image.height);
		     };
		     
		    console.log(context);
		    image.setAttribute("src","<%=request.getContextPath()%>/ShowPictureServletDAO?cont_no=<%=originalCont_no%>");
		<%}%>
		
		
		
		
		
		
		
		
		function  getMousePos(canvas, evt) {
          var rect = canvas.getBoundingClientRect(), // abs. size of element
              scaleX = canvas.width / rect.width,    // relationship bitmap vs. element for X
              scaleY = canvas.height / rect.height;  // relationship bitmap vs. element for Y

          return {
            x: (evt.pageX - rect.left) * scaleX,   // scale mouse coordinates after they have
            y: (evt.pageY - rect.top) * scaleY     // been adjusted to be relative to element
          }
        }
		
		function load_image(){
			 
			   var input, file, fr, img;
			   input = document.getElementById('imgfile');
			   input.click();
			  
		}
		
		
		$(document).ready(function(e) {
			
			var stillDown = false;
			var canvas = $("#canvas");
// 			var context = canvas.getContext("2d");
			
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
		
			$("#clearBtn").on("click", function() {
				console.log("clear!");
				context.clearRect(0, 0, canvas.width, canvas.height);
				console.log(canvas);
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
		
		//window.addEventListener("load",init,false);
	</script>


</body>
</html>