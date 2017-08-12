<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<%
		//String mem_no = (String) session.getAttribute("mem_no");
		//String cont_no = request.getParameter("cont_no");
		String cropCont_no = "0166";
		session.setAttribute("mem_no","1001");
		//String cropCont_no = (String) request.getAttribute("cropCont_no");
	%>
	
<%@ include file="page/preview_header.file"%>

<div class="container">
        <div class="row">
<!--sidebar sidebar sidebar sidebar sidebar sidebar -->
            <div class="col-md-offset-1 col-md-2 col-xs-0">
             <br><br><br> 
                <ul class="list-group">
                    <a href="<%=request.getContextPath()%>/Front_end/mem/updatemember.jsp" class="list-group-item menua">編輯個人資料</a><br>

                    <a href="<%=request.getContextPath()%>/Front_end/mem/updatePwd.jsp" class="list-group-item menua">密碼修改</a><br>

                    <a href="#" class="list-group-item menua">預約紀錄查詢</a><br>
                    <a href="#" class="list-group-item menua">報價紀錄查詢</a><br>
                    <a href="<%=request.getContextPath()%>/Front_end/Temp/MemPage_ListAllTemps.jsp" class="list-group-item menua">作品挑選管理</a><br>

                    <a href="<%=request.getContextPath()%>/Front_end/Album/ListAllAlbums.jsp" class="list-group-item menua">我的相簿</a><br>

                    <a href="#" class="list-group-item menua">我的最愛</a><br>
                    <a href="#" class="list-group-item menua">實景預覽</a><br>
                    <a href="<%=request.getContextPath()%>/Front_end/mall/mallIndexAJAX.jsp" class="list-group-item menua">商城專區</a><br>
                </ul>

																				
                <a href="<%=request.getContextPath()%>/Front_end/mem/listOneMem.jsp"  class="btn btn-block btn-default">查看個人資料</a>
            </div>
           <div class="col-md-8 col-offset-1">
            
	<div class="ui-widget ui-helper-clearfix">
			<div class='row'>
				
				<form action='<%=request.getContextPath()%>/album/preview.do'
				method="post" enctype="multipart/form-data" id='overlayImageForm'>
				<input
					type="file" name="imgfile" id='imgfile'
					onchange="preview_images()" style="display: none">
				<a type='button' class="btn btn-app btn-default" id='btnLoad'
					onclick='load_image();'><i class="fa fa-image"></i>Add image</a>
					<input type='submit' value='確定' id='submitBtn'>
					<input type='hidden' name='backgroundImage' value='' >
					<input type='hidden' name='cropCont_no' value='<%=cropCont_no%>'>
					<input type='hidden' name='mem_no' value='${mem_no}'>
					<input type='hidden' id='xPoint' name='xPoint' value=''>
					<input type='hidden' id='yPoint' name='yPoint' value=''>
					<input type='hidden' name='action' value='overlayImage'>
				</form>
			</div>
			<div class='row'>
				<div class='col-xs-12 col-sm-12'>
					<ul id="gallery" class="gallery ui-helper-reset ui-helper-clearfix">
						<li class="ui-widget-content ui-corner-tr"><img
							src="<%=request.getContextPath() %>/Front_end/Preview/img/1.jpg"></li>
						<li class="ui-widget-content ui-corner-tr"><img
							src="<%=request.getContextPath() %>/Front_end/Preview/img/2.jpg"></li>
						<li class="ui-widget-content ui-corner-tr"><img
							src="<%=request.getContextPath() %>/Front_end/Preview/img/001_002.jpg"></li>
						<li class="ui-widget-content ui-corner-tr"><img
							src="<%=request.getContextPath() %>/Front_end/Preview/img/002_003.jpg"></li>
						<li class="ui-widget-content ui-corner-tr"><img
							src="<%=request.getContextPath() %>/Front_end/Preview/img/004_001.jpg"></li>
						<li class="ui-widget-content ui-corner-tr">
						<img
							src="<%=request.getContextPath() %>/Front_end/Preview/img/004_011.jpg"> 
						</li>
					</ul>
					
				</div>
					
			
			</div>
			<div class='row'>
			<br>
				

				<div class='col-sm-12 col-xs-12'>
					<div id="dropZone" class="ui-widget-content">
						<h4 class="ui-widget-header">Drop Here</h4>
						<img id="cropCont" src='<%=request.getContextPath()%>/ShowPictureServletDAO?cont_no=<%=cropCont_no%>'>
					</div>
				</div>
			</div>
	</div>
</div>

<style>
#gallery {
	float: left;
	width: 100%;
	min-height: 10em;
	border:1px solid gray;
}

.gallery.custom-state-active {
	background: #eee;
}

.gallery li {
 	float: left; 
	width: 100px;
	margin:0.4em 0.4em;
	list-style:none;
}

.gallery li img {
	width: 100%;
	cursor: move;
	height: auto;
}

#dropZone {
	border: 2px solid gray;
	width: auto;
	height: 630px;
	background-color: white;
	background-image: linear-gradient(45deg, #eee 25%, transparent 25%, transparent 75%, #eee
		75%, #eee),
		linear-gradient(45deg, #eee 25%, transparent 25%, transparent 75%, #eee
		75%, #eee);
	background-size: 30px 30px;
	background-position: 0 0, 15px 15px
}

#dropZone h4 {
	line-height: 16px;
/* 	margin: 0 0 0.4em; */
}


</style>
	<script>
	
	var xPoint,yPoint;
	var image;
	var imageWidth,imageHeight;
	function load_image(){
		 
		   var input;
		   input = document.getElementById('imgfile');
		   input.click();
		  
	}
	function preview_images() {
		
		$("img#backgroundImage").remove();
	     var total_file=document.getElementById("imgfile").files.length;
	     image = new Image();
	     for(var i = 0; i < total_file; i++){
	    	 if(event.target.files[i].type.match('image.*')){
	    		 $('#dropZone').css("background","url("+URL.createObjectURL(event.target.files[i])+")")
	    		 				.css("background-repeat","no-repeat");
	    		 
	    		 imageWidth = this.width;
	    		 imageHeight = this.height;
// 	    		 $('#dropZone').append("<img class='img-responsive' id='backgroundImage' src='"+
// 	    		URL.createObjectURL(event.target.files[i])+"'>");
	    		
	    	 }
	      
	     }
	     console.log("real   "+$("#backgroundImage").height());
	}
	
	
		$(function() {
			
			 var origin = {x: 0, y: 0},
		        start = {x: 0, y: 0},
		        movecontinue = false;
			var $gallery = $("#gallery"), $dropZone = $("#dropZone");

			$("li", $gallery).draggable({
// 				cancel : "a.ui-icon", // clicking an icon won't initiate dragging
// 				revert : "invalid", // when not dropped, the item will revert back to its initial position
				containment : "document",
				helper : "clone",
				cursor : "move"
				
			});

			$dropZone.droppable({
				accept : "#gallery > li",
				classes : {
					"ui-droppable-active" : "ui-state-highlight"
				},
				drop : function(event, ui) {
					deleteImage(ui.draggable);
				}
			});

			$gallery.droppable({
				accept : "#dropZone li",
				classes : {
					"ui-droppable-active" : "custom-state-active"
				},
				drop : function(event, ui) {
					recycleImage(ui.draggable);
				}
			});

			function deleteImage($item) {
				$item.fadeOut(function() {
					var $list = $("ul", $dropZone).length ? $("ul", $dropZone)
							: $("<ul class='gallery ui-helper-reset'/>")
									.appendTo($dropZone);

					$item.appendTo($list).fadeIn(function() {
						$item.animate({
							width : "80%"
						}).find("img").attr("id","backgroundImage").animate({
							height : "100%"
							
						});
						
					});
				});
			}

			function recycleImage($item) {
				$item.fadeOut(function() {
					$item.css("width",
							"100px").find("img").css("height", "auto").end()
							.appendTo($gallery).fadeIn();
				});
			}

			// Resolve the icons behavior with event delegation
			$("ul.gallery > li").on("click", function(event) {
				var $item = $(this), $target = $(event.target);

				if ($target.is("a.ui-icon-trash")) {
					deleteImage($item);
				} else if ($target.is("a.ui-icon-refresh")) {
					recycleImage($item);
				}

				return false;
			});
			
			// cropCont拖曳功能
			$("#cropCont").draggable({ 
				containment: "#dropZone", 
				scroll: false,
				drag: function(){
					 var $this = $(this);
			         var thisPos = $this.position();
			         console.log("thisPos : "+thisPos);
			         //var viewPos = $("#backgroundImage").position();

			         xPoint = thisPos.left - start.x;
			         yPoint = thisPos.top - start.y;
			         console.log("xPoint : "+xPoint);
			         console.log("yPoint : "+yPoint);
			         
				}
			});
			$("#submitBtn").on("click",function(){
				var JSONxPoint = JSON.stringify(xPoint);
				var JSONyPoint = JSON.stringify(yPoint);
				$("#xPoint").val(JSONxPoint);
				$("#yPoint").val(JSONyPoint);
				
				$("#overlayImageForm").submit();
			});
			
			
			
			
			
			
			
			
			
			
			
			
		    
		    function move (e){
		        var moveby = {
		            x: origin.x - e.clientX, 
		            y: origin.y - e.clientY
		        };
		        
		        if (movecontinue === true) {
		            start.x = start.x - moveby.x;
		            start.y = start.y - moveby.y;
		            
		            $(this).css('background-position', start.x + 'px ' + start.y + 'px');
		        }
		        
		        origin.x = e.clientX;
		        origin.y = e.clientY;
		        
		        e.stopPropagation();
		        return false;
		    }
		    
		    function handle (e){
		        movecontinue = false;
		        $dropZone.unbind('mousemove', move);
		        
		        if (e.type == 'mousedown') {
		            origin.x = e.clientX;
		            origin.y = e.clientY;
		            movecontinue = true;
		            $dropZone.bind('mousemove', move);
		        } else {
		            $(document.body).focus();
		        }
		        
		        e.stopPropagation();
		        return false;
		    }
		    
		    function reset (){
		        start = {x: 0, y: 0};
		        $(this).css('backgroundPosition', '0 0');
		    }
		    
		    $dropZone.bind('mousedown mouseup mouseleave', handle);
		    $dropZone.bind('dblclick', reset);
		});
	</script>
<%@ include file="page/preview_footer.file"%>