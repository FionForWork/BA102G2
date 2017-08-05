<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:useBean id="contSvc" scope="page"
	class="com.content.model.ContentService"></jsp:useBean>
<jsp:useBean id="albSvc" scope="page"
	class="com.album.model.AlbumService"></jsp:useBean>

<%
	String alb_no = (String) request.getParameter("alb_no");
	//String alb_no = "0001";
	pageContext.setAttribute("alb_no", alb_no);
%>

<%@ include file="page/photo_header.file"%>

<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
<div class="container">
	<div class="col-md-offset-1">
		<ul class="breadcrumb">
			<li><a href="#">首頁</a></li>
			<li><a href="#">會員專區</a></li>
			<li><a href="#">我的相簿</a></li>
			<li class="active">${albSvc.getOneAlbum(alb_no).name}</li>
		</ul>
	</div>
</div>
<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->

<div class="container">
	<div class="row">
		<!--sidebar sidebar sidebar sidebar sidebar sidebar -->
		<div class="col-md-offset-1 col-md-2">
			<ul class="list-group">
				<a href="#" class="list-group-item menua">編輯個人資料</a>
				<br>
				<a href="#" class="list-group-item menua">密碼修改</a>
				<br>
				<a href="#" class="list-group-item menua">預約紀錄查詢</a>
				<br>
				<a href="#" class="list-group-item menua">報價紀錄查詢</a>
				<br>
				<a href="#" class="list-group-item menua">作品挑選管理</a>
				<br>
				<a href="#" class="list-group-item menua active">我的相簿</a>
				<br>
				<a href="#" class="list-group-item menua">我的最愛</a>
				<br>
				<a href="#" class="list-group-item menua">商城專區</a>
				<br>
			</ul>


			<a href="#" class="btn btn-block btn-default">查看個人資料</a>
		</div>
		<!--sidebar sidebar sidebar sidebar sidebar sidebar -->

		<!--這裡開始===========================================================================-->

		<div class="col-md-8 col-offset-1">


			<!-- Photo Start Here -->


			<!-- Modal addContent -->
			<form action="<%=request.getContextPath()%>/content/content.do"
				method="post" enctype="multipart/form-data">
				<div class="modal fade" id="uploadModal" role="dialog">
					<div class="modal-dialog modal-lg">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header" style="padding: 20px 50px;">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4>
									<span class="glyphicon glyphicon-picture"></span> 上傳照片
								</h4>
							</div>
							<div class="modal-body" style="padding: 40px 50px;">
								<div class="form-group">
									<!-- 									<label for="inputFile"> 選擇照片</label> 									 -->
									<input id="inputFile" name="inputFile[]" type="file" multiple
										class="file-loading">

								</div>

								<input type='submit' class="btn btn-info btn-block" value="新增">
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-default pull-left"
									data-dismiss="modal">
									<span class="glyphicon glyphicon-remove"></span> Cancel
								</button>
								<input type='hidden' name='action' value='insert_Content'>
								<input type='hidden' name='alb_no' value='<%=alb_no%>'>
							</div>
						</div>
					</div>
				</div>
			</form>
			<!-- End Modal addContent -->
			<div class="jumbotron">
				<div class="row">
					<button type="submit" class="btn btn-info" id="uploadbtn"
						style="float: right; margin-button: 0;">新增相片</button>
					<div class="col-xs-12 col-sm-12">
						<div class="text-center">
							<h2>${albSvc.getOneAlbum(alb_no).name}</h2>
						</div>
					</div>
				</div>
			</div>




			<!-- The lightbox Modal (img)-->
			<div id="lightboxImgModal" class="modal">
				<span class="closeImg">&times;</span> <img
					class="lightbox-modal-content" id="lightboxImg">
			</div>
			<!-- The lightbox Modal (img)-->





			<c:forEach var="contVO" items="${contSvc.getAllByAlbNo(alb_no)}"
				varStatus="s">
				<c:if test="${(s.count % 4) == 1}">
					<div class="row">
				</c:if>
				<!-- Modal delete Content -->
				<div class="modal fade" id="deleteModal${s.count}" role="dialog">
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
									data-dismiss="modal">取消</button>

								<button type="button" class="btn btn-danger"
									data-dismiss="modal" id='deletebtn'
									onclick="document.getElementById('delete${s.count}').submit();">刪除</button>

							</div>
						</div>

					</div>
				</div>
				<!--  End Modal Delete Content -->
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="image-container">
						<c:if test="${contVO.img == null}">
							<video width="400" controls class="img-responsive img-thumbnail">
								<source
									src="<%=request.getContextPath()%>/ShowPictureServletDAO?cont_no=${contVO.cont_no }"
									type="video/mp4">
								您的瀏覽器不支援此撥放程式
							</video>

						</c:if>
						<c:if test="${contVO.img != null}">

							<img class="img-responsive img-thumbnail original aa"
								src="<%=request.getContextPath()%>/ShowPictureServletDAO?cont_no=${contVO.cont_no }" />



						</c:if>

						<div class="overlap dropdown">
							<button class="btn btn-default btn-xs" type="submit"
								class='dropbtn'>
								<i class="fa fa-cog" aria-hidden="true"></i>
							</button>
							<div class='dropdownContent' id='dropdownContent${s.count}'>
								<form id="update${s.count}"
									action="<%=request.getContextPath()%>/content/content.do"
									method="post">
									<input type='hidden' name='action' value='setCover'> <input
										type='hidden' name='cont_no' value='${contVO.cont_no}'>
									<input type='hidden' name='alb_no' value='<%=alb_no%>'>
									<a href='#' id='setCover'
										onclick="document.getElementById('update${s.count}').submit();">設成封面</a>
								</form>
								<form id="delete${s.count}"
									action="<%=request.getContextPath()%>/content/content.do"
									method="post">
									<input type='hidden' name='cont_no' value='${contVO.cont_no}'>
									<input type='hidden' name='action' value='delete_Content'>
									<input type='hidden' name='alb_no' value='<%=alb_no%>'>
									<a href='#' data-toggle="modal"
										data-target="#deleteModal${s.count}">刪除相片</a>
								</form>
							</div>
						</div>
					</div>
				</div>
				<c:if test="${(s.count % 4) == 0}">
		</div>
		</c:if>
		</c:forEach>
		<br>
	</div>

	<%@ include file="page/album_footer.file"%>