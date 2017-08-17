<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="worksSvc" scope="page"
	class="com.works.model.WorksService"></jsp:useBean>

<%
	//String com_no = (String) session.getAttribute("com_no");
	String com_no = "2003";
	pageContext.setAttribute("com_no", com_no);
%>

<%@ include file="page/works_header.file"%>

<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
<div class="container">
	<div class="col-md-offset-1">
		<ul class="breadcrumb">
			<li><a href="#">首頁</a></li>
			<li><a href="#">廠商專區</a></li>
			<li class="active">作品管理</li>

		</ul>
	</div>
</div>
<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->

<div class="container">
	<div class="row">
		<!--sidebar sidebar sidebar sidebar sidebar sidebar -->
		<div class="col-md-offset-1 col-md-2">
			<ul class="list-group">
				<a href="<%=request.getContextPath()%>/Front_end/com/updatecompany.jsp" class="list-group-item menua">編輯廠商資料</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/com/updatePwd.jsp" class="list-group-item menua">修改密碼</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/reservation/comReservation.jsp" class="list-group-item menua">預約紀錄查詢</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/quote/listMyQuote.jsp" class="list-group-item menua">報價紀錄查詢</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/Temp/ComPage_ListAllTemps.jsp" class="list-group-item menua">作品挑選管理</a><br>
                <a href="<%= request.getContextPath() %>/Front_end/calendar/calendar.jsp" class="list-group-item menua">行事曆</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/Works/ListAllWorks.jsp" class="list-group-item menua active">作品管理</a><br>
			</ul>


			<a href="#" class="btn btn-block btn-default">查看廠商資料</a>
		</div>
		<!--sidebar sidebar sidebar sidebar sidebar sidebar -->

		<!--這裡開始===========================================================================-->

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
				<button class="btn btn-info text-right" onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/Works/UpdateWorks.jsp?com_no=${com_no}'" >編輯作品</button>
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
			<c:forEach var="worksVO" items="${worksSvc.getAllByComNo(com_no)}"
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
									<p>${worksVO.works_desc}</p>
									
								</div>
							</div>
						</c:if>
						<c:if test="${worksVO.img != null}">
							<div class="polaroid">
								 <img
									class="img-responsive aa" onclick='openLightBox(this)' style="width: 100%"
									src="<%=request.getContextPath()%>/ShowPictureServletDAO?works_no=${worksVO.works_no}" />
								<div class="caption">
									<h4>${worksVO.name == null ? '未命名的作品' : worksVO.name}</h4>
									<p>${worksVO.upload_date.toString().substring(0,10)}</p>	
									<p>${worksVO.works_desc}</p>
								</div>
							</div>
						</c:if>
						<div class="overlap dropdown">
							<button class="btn btn-default btn-xs" type="submit"
								class='dropbtn'>
								<i class="fa fa-cog" aria-hidden="true"></i>
							</button>

							<div class='dropdownContent' id='dropdownContent${s.count}'>
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
	        com_no: <%=com_no%>,
	        action: "upload_Works",
	    }
	});
	$("#inputFile").on("fileuploaded", function (event, data, previewId, index) {  
        top.location.href="<%=request.getContextPath()%>/Front_end/Works/UpdateWorks.jsp?com_no=<%=com_no%>";
	});
});

function doAjax(action,works_no){
	$.ajax({
		url:'<%=request.getContextPath()%>/works/works.do',
		type:'POST',
		data:{
			com_no :'<%=com_no%>',
			works_no : works_no,
			action : action
		},
		success:function success(){
			$("#changeContent").load("<%=request.getContextPath()%>/Front_end/Works/ListAllWorks.jsp #changeContent",{
				com_no :'<%=com_no%>'
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

</script>
<%@ include file="page/works_footer.file"%>