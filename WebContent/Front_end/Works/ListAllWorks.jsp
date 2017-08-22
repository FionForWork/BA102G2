<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.com.model.*" %>

<jsp:useBean id="worksSvc" scope="page"
	class="com.works.model.WorksService"></jsp:useBean>

<%
	ComVO comVO = (ComVO)session.getAttribute("comVO");
	System.out.println("comVO"+comVO);	
	//String com_no = (String) session.getAttribute("com_no");
	//String com_no = "2003";
	//pageContext.setAttribute("com_no", com_no);
%>

<%@ include file="page/works_header.file"%>

</script>

		<div class="col-md-8 col-offset-1">
			<!-- Photo Start Here -->

			<!-- Modal addContent -->
			<form action="<%=request.getContextPath()%>/works/works.do"
				method="post" enctype="multipart/form-data">
				<div class="modal fade" id="uploadModal" role="dialog">
					<div class="modal-dialog modal-lg">

						<!-- Modal add works-->
						<div class="modal-content">
							<div class="modal-header" style="padding: 35px 50px;">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4>
									<span class="glyphicon glyphicon-picture"></span> 上傳作品
								</h4>
							</div>
							<div class="modal-body" style="padding: 40px 50px;">
								<div class="form-group">
									<label for="upload"> 選擇照片或影片</label> <input id="inputFile"
										name="inputFile[]" type="file" multiple class="file-loading">
									<br>
								</div>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-default pull-left"
									data-dismiss="modal">
									<span class="glyphicon glyphicon-remove"></span> Cancel
								</button>

							</div>
						</div>
					</div>
				</div>
			</form>
			<!-- End Modal add works -->

			<div class="row" style="position:relative" >
				<div class="col-xs-12 col-sm-12">
				<div class='btn-group' style="right:15px; top:15px;position:absolute;">
				<button type="submit" class="btn btn-info text-right" id="uploadbtn" >新增作品</button>
				<button class="btn btn-info text-right" onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/Works/UpdateWorks.jsp?com_no=${comVO.com_no}'" >編輯作品</button>
				</div>
					<br>	
					<div class="text-center">
						<h2>所有作品</h2>
					</div>
				</div>
			</div>
<br>


			<!-- The lightbox Modal (img)-->
			<div id="lightboxImgModal" class="modal">
				<span class="closeImg" onclick='closeLightBox()'>&times;</span> <img
					class="lightbox-modal-content" id="lightboxImg">
			</div>
			<!-- The lightbox Modal (img)-->
			
				<!-- Modal delete works -->
				<div class="modal fade" id="deleteModal" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">刪除相片</h4>
							</div>
							<div class="modal-body">
								<p>刪除相片後將無法復原，確定刪除嗎?</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal" id='cancelDelete'>取消</button>
								<input type='hidden' name='works_no' value=''>
								<button type="button" class="btn btn-danger"
									data-dismiss="modal" onclick="deleteWorks()">刪除</button>

							</div>
						</div>
					</div>
				</div>
				<!--  End Modal Delete works -->


			<div id='changeContent'>
			<c:forEach var="worksVO" items="${worksSvc.getAllByComNo(comVO.com_no)}"
				varStatus="s">
				<c:if test="${(s.count % 3) == 1}">
					<div class="row">
				</c:if>
				<div class="col-md-4 col-sm-4 col-xs-12">
					<div class="image-container">
						
						<c:if test="${worksVO.img == null}">

							<div class="polaroid">
								 <video
										width="400" controls class="img-responsive"
										style="width: 100%">
										<source
											src="<%=request.getContextPath()%>/ShowPictureServletDAO?works_no=${worksVO.works_no}"
											type="video/mp4">
										您的瀏覽器不支援此撥放程式
									</video>
								
								<div class="caption">
									<h4>${worksVO.name == null ? '未命名的作品' : worksVO.name}</h4>
									<p>${worksVO.upload_date.toString().substring(0,10)}</p>	
									<p>${worksVO.works_desc.length() == 0 ? '為您的作品添加敘述吧...' : worksVO.works_desc}</p>
									
								</div>
							</div>
						</c:if>
						<c:if test="${worksVO.img != null}">
							<div class="polaroid">
								 <img
									class="img-responsive aa" onclick='openLightBox(this)' style="width: 100%"
									src="<%=request.getContextPath()%>/ShowPictureServletDAO?works_no=${worksVO.works_no}" />
								<div class="caption">
								<form name="updateForms" action="<%=request.getContextPath()%>/works/works.do" method="post" class='updateForms'>
									<h4>${worksVO.name == null ? '未命名的作品' : worksVO.name}</h4>
									<p>${worksVO.upload_date.toString().substring(0,10)}</p>	
									<p>${worksVO.works_desc.length() == 0 ? '為您的作品添加敘述吧...' : worksVO.works_desc}</p>
									<p></p>
									<input type='hidden' name='com_no' value='${comVO.com_no}'>
									<input type='hidden' name='works_no' value='${worksVO.works_no}'>
									<input type='hidden' name='action' value='update_Single_Work'>
								</form>
									
								</div>
							</div>
						</c:if>
						<div class="overlap dropdown">
							<button class="btn btn-default btn-xs" type="submit"
								class='dropbtn'>
								<i class="fa fa-cog" aria-hidden="true"></i>
							</button>
							
							<div class='dropdownContent' id='dropdownContent${s.count}'>
								<a onclick="updateSigleWorks(this);" class='updateSingleBtn'>編輯作品</a>
								<a data-toggle='modal' onclick="openModal('${worksVO.works_no}')">刪除作品</a>
							</div>
						</div>

					</div>
				</div>
				<c:if test="${(s.count % 3) == 0}">
		</div>
		</c:if>
		</c:forEach>
		</div>
		<br>
	</div>
	
<script type="text/javascript">
$("document").ready(function(){
	
	$("#inputFile").fileinput({
	    maxFileCount: 50,
	    allowedFileTypes: ["image", "video"],
	    language: 'zh-TW', //设置语言
	    theme: "fa",
	    uploadUrl: "<%=request.getContextPath()%>/works/works.do", // server upload action
	    uploadAsync: true,
	    browseOnZoneClick: true ,
	    uploadExtraData: {
	        com_no: '${comVO.com_no}',
	        action: "insert_Works",
	    }
	});
	$("#inputFile").on("fileuploaded", function (event, data, previewId, index) {  
        top.location.href="<%=request.getContextPath()%>/Front_end/Works/UpdateWorks.jsp?com_no=${comVO.com_no}";
	});
});

function doAjax(action,works_no){
	$.ajax({
		url:'<%=request.getContextPath()%>/works/works.do',
		type:'POST',
		data:{
			com_no :'${comVO.com_no}',
			works_no : works_no,
			action : action
		},
		success:function success(){
			$("#changeContent").load("<%=request.getContextPath()%>/Front_end/Works/ListAllWorks.jsp #changeContent",{
				com_no :'${comVO.com_no}'
			});
		},
		error:function(xhr){
			alert('Ajax request error!');
		}
	});
}
function openModal(works_no){
	$("#deleteModal").modal();
	$('input[name=works_no]').val(works_no);
}
function deleteWorks(){
	$("#cancelDelete").click();
	var works_no = $('input[name=works_no]').val();
	doAjax('delete_Works',works_no);
	
}

var options = {
		success: change
};

function change(){
	console.log("YYYYYY");
	$("#changeContent").load("<%=request.getContextPath()%>/Front_end/Works/ListAllWorks.jsp #changeContent",{
		com_no :'${comVO.com_no}'
	});
}
function submitUpdate(){
	console.log("XXXXXXX");
	$(".updateForms").each(function(){
		$(this).submit(function(){
			$(this).ajaxSubmit(options);
			return false;
		});
	});
}


function updateSigleWorks(works){
	var worksname = $(works).parent().parent().prev().find("h4");
	var worksdate = worksname.next();
	var worksdesc = worksdate.next();
	var worksbtn = worksdesc.next();
	console.log(worksname);
	var name = worksname.text();
	var date = worksdate.text();
	var desc = worksdesc.text();
 	worksname.html("<div class='form-group'><label for='name'>作品名稱</label> <input type='text' id='name' name='name' class='form-control' value='"+name+"' placeholder='未命名的作品'></div>");
 	worksdate.html("<div class='form-group'><label for='datepicker'>上傳日期 </label> <input type='text' id='datepicker' class='form-control' name='upload_date'value='"+date+"'></div>");
 	worksdesc.html("<div class='form-group'><label for='works_desc'>作品敘述</label><textarea name='works_desc' rows='4' cols='20'class='form-control' placeholder='請輸入作品敘述...'>"+desc+"</textarea></div>");
 	worksbtn.html("<button class='btn btn-default' onclick='submitUpdate();'>完成</button>");
 	$("#datepicker").datepicker({dateFormat: 'yy-mm-dd',maxDate: "+0D"});
 	$(".updateSingleBtn").each(function(){
 		$(this).css('pointer-events','none');
 	});
}
</script>
<%@ include file="page/works_footer.file"%>