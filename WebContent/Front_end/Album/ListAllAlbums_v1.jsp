<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<jsp:useBean id="albSvc" scope="page"
	class="com.album.model.AlbumService"></jsp:useBean>
<jsp:useBean id="contSvc" scope="page"
	class="com.content.model.ContentService"></jsp:useBean>

<%
	//String mem_no = (String)session.getAttribute("mem_no");//formal
	String mem_no = "1001";
	session.setAttribute("mem_no", "1001");
	Map<String, String> errorMsgs = (Map) request.getAttribute("errorMsgs");
%>

<%@ include file="page/album_header.file"%>

<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
<div class="container">
	<div class="col-md-offset-1">
		<ul class="breadcrumb">
			<li><a href="#">首頁</a></li>
			<li><a href="#">會員專區</a></li>
			<li class="active">我的相簿</li>
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
			<div class="row text-right">
				<button class="btn btn-default createAlbum" id="createAlb"
					value="submit">新增相簿</button>
			</div>

			<!--start Modal create album-->
			<form action="<%=request.getContextPath()%>/album/album.do"
				method="post" enctype="multipart/form-data">
				<div class="modal fade" id="albumModal" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->

						<div class="modal-content">
							<div class="modal-header" style="padding: 35px 50px;">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4>
									<span class="glyphicon glyphicon-picture"></span> 建立相簿
								</h4>
							</div>

							<div class="modal-body" style="padding: 40px 50px;">

								<div class="input-group">
									<label class="input-group-addon">相簿名稱 </label> <input
										type="text" name="name" class="form-control">
								</div>
								<br> <span class='errorMsgs'>
									${errorMsgs.get("file")}</span>
								<div class="form-group">
									<input type="file" class="form-control" name="uploadPic"
										id="upload" onchange="preview_images()" multiple>
								</div>
								<div id="showPanel"></div>
								<span class="glyphicon glyphicon-off"></span> <input
									type='submit' class="btn btn-info btn-block" value="建立">
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-default pull-left"
									data-dismiss="modal">
									<span class="glyphicon glyphicon-remove"></span> Cancel
								</button>
								<input type='hidden' name='action' value='create_Album'>
							</div>
						</div>

					</div>
				</div>
			</form>
			<!--end Modal create album -->


			<c:forEach var="albVO" items="${albSvc.getAllByMemNo(mem_no)}"
				varStatus="s">
				<c:if test="${(s.count % 4) == 1}">
					<div class="row">
				</c:if>
				<!-- Modal delete alb -->
				<div class="modal fade" id="deleteModal${s.count}" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">刪除相簿</h4>
							</div>
							<div class="modal-body">
								<p>你確定想刪除「 ${albVO.name} 」嗎？在這本相簿中的相片也會被刪除。</p>
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
				<!--  End Modal Delete Alb -->
				<div class="col-xs-12 col-sm-4 col-md-3">
					<div class="panel panel-default">
						<div class="panel-heading">${albVO.name}</div>
						<div class="panel-body">
								<a id="displayAnc" href="#"
									onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/Album/ListAllContents.jsp?alb_no=${albVO.alb_no}'">
									<img
									src='<%=request.getContextPath() %>/ShowPictureServletDAO?alb_no=${albVO.alb_no }'
									class="img-responsive gallery" class="img-responsive"
									style="width: 100%" alt="Image">
								</a>
						</div>
						<div class="panel-footer">
							${contSvc.countContentsInSingleAlbum(albVO.alb_no) } 張相片
							<div class="text-right">
								<form class="form-inline" id="update${s.count}"
									action="<%=request.getContextPath()%>/Front_end/Album/UpdateAlbum.jsp"
									method="post">
									<input type="hidden" name="alb_no" value="${albVO.alb_no }">
									<a href="#"
										onclick="document.getElementById('update${s.count}').submit();">
										<span class='fa fa-pencil' style='font-size: 20px;'></span>
									</a>
								</form>

								<form id="delete${s.count}"
									action="<%=request.getContextPath()%>/album/album.do"
									method="post">
									<input type="hidden" name="alb_no" value="${albVO.alb_no }">
									<input type="hidden" name="action" value="delete_Album">
									<a href="#" id="alb${s.count}" data-toggle="modal"
										data-target="#deleteModal${s.count}"> <span
										class='fa fa-trash' style='font-size: 20px;'></span>
									</a>
								</form>
							</div>
						</div>
					</div>
				</div>
				<c:if test="${(s.count % 4) == 0}">
		</div>
		</c:if>
		</c:forEach>
	</div>
	<%@ include file="page/album_footer.file"%>