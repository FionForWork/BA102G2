<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<%
		//String mem_no = (String) session.getAttribute("mem_no");
		session.setAttribute("mem_no","1001");
		//String cropCont_no = (String) request.getAttribute("cropCont_no");
		String cropCont_no = "0166";
		String place_no = "1";
		pageContext.setAttribute("place_no",place_no);
	%>
	
<%@ include file="page/preview_header.file"%>
<jsp:useBean id="placeViewSvc" scope='page' class='com.placeview.model.PlaceViewService'/>

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

                    <a href="<%=request.getContextPath()%>/Front_end/ComTra/ListAllComTra.jsp" class="list-group-item menua">我的最愛</a><br>
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
					
					<input type='hidden' name='placeview_no' value='' >
					<input type='hidden' name='cropCont_no' value='<%=cropCont_no%>'>
					<input type='hidden' name='mem_no' value='${mem_no}'>
					<input type='hidden' id='xPoint' name='xPoint' value=''>
					<input type='hidden' id='yPoint' name='yPoint' value=''>
					<input type='hidden' name='cropWidth' value=''>
					<input type='hidden' name='cropHeight' value=''>
					 
					<input type='hidden' name='action' value='overlayImage'>
					<input
					type="file" name="imgfile" id='imgfile'
					onchange="preview_images()" style="display: none">
				<a type='button' class="btn btn-app btn-default" id='btnLoad'
					onclick='load_image();'><i class="fa fa-image"></i>選擇背景照片</a>
				</form>
				<button class='btn btn-default' id='clearBtn'>清除背景照</button>
				<input type='submit' value='確定' id='submitBtn' class='btn btn-default'>
			</div>
			<div class='row'>
				<div class='col-xs-12 col-sm-12'>
					<ul id="gallery" class="gallery">
					<c:forEach var='placeview_no' items='${placeViewSvc.getAllByFk(place_no)}'>
						<li class="ui-widget-content ui-corner-tr">
						<img class="img-responsive img-thumbnail" id='${placeview_no}' src="<%=request.getContextPath()%>/image/ShowImage?view_no=${placeview_no}"></li>
					</c:forEach>
					</ul>
					
				</div>
					
			
			</div>
			<div class='row'>
			
				<div class='col-sm-12 col-xs-12'>
				<h4>Drop Here!</h4>
					<div id="dropZone" class="ui-widget-content">
						<div id='draggable' style="display:inline-block">
							<img id='resizable' src='<%=request.getContextPath()%>/ShowPictureServletDAO?cont_no=<%=cropCont_no%>'>
						
						</div>
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
	padding-left:2px;
}

.custom-state-active {
	background: #eee;
}

.gallery li {
 	float: left; 
	width: 150px;
	margin:0.2em 0.2em;
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
	background-image:none;
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
		
	     var total_file=document.getElementById("imgfile").files.length;
	     image = new Image();
	     for(var i = 0; i < total_file; i++){
	    	 if(event.target.files[i].type.match('image.*')){
	    		 $('#dropZone').css("background","url("+URL.createObjectURL(event.target.files[i])+")")
	    		 				.css("background-repeat","no-repeat");
	    $("input[name=placeview_no]").val(""); 
	    	 }
	     }
	}
	
	
		$(function() {
			
			 var origin = {x: 0, y: 0},
		        start = {x: 0, y: 0},
		        movecontinue = false;
			 
			var $gallery = $("#gallery"), $dropZone = $("#dropZone");

			$("li", $gallery).draggable({
// 				cancel : "a.ui-icon", // clicking an icon won't initiate dragging
				revert : "invalid", // when not dropped, the item will revert back to its initial position
				containment : "document",
				helper : "clone",
				cursor : "move"
				
			});

			$dropZone.droppable({
				accept : "#gallery > li",
				
				drop : function(event, ui) {
					dragPlaceViewImage(ui.draggable);
				}
			});

			
			function dragPlaceViewImage($item) {
				$item.fadeIn(function() {
					var placeview_no = $item.find("img").attr("id");
					$dropZone.css("background","url(<%=request.getContextPath()%>/image/ShowImage?view_no="+placeview_no+")")
	 				.css("background-repeat","no-repeat");
					$("input[name=placeview_no]").val(placeview_no);
				});
			}

			// cropCont拖曳與縮放功能
			$("#draggable").on("click",function(){
				
				$("#draggable").draggable({ 
					containment: "#dropZone", 
					scroll: false,
					drag: function(){
						 var $this = $(this);
				         var thisPos = $this.position();
				         console.log("thisPos : "+thisPos);

				         xPoint = thisPos.left - 12;
				         yPoint = thisPos.top - 41;

				         console.log("xPoint : "+xPoint);
				         console.log("yPoint : "+yPoint);
				         
					}
				});
				$("#resizable").resizable({
					containment: "#dropZone",
					aspectRatio: 4 / 3,
					resize: function( event, ui ) {
						$("input[name=cropWidth]").val(ui.size.width);
						$("input[name=cropHeight]").val(ui.size.height);
					  }
				});
				
			});
			
			$("#submitBtn").on("click",function(){
				var JSONxPoint = JSON.stringify(xPoint);
				var JSONyPoint = JSON.stringify(yPoint);
				$("#xPoint").val(JSONxPoint);
				$("#yPoint").val(JSONyPoint);
				$("#overlayImageForm").submit();
			});
			
			$("#clearBtn").on("click",function(){
				$dropZone.css("background","none");
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