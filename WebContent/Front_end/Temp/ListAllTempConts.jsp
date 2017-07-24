<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:useBean id="tempContSvc" scope="page"
	class="com.tempcont.model.TempContService"></jsp:useBean>
<jsp:useBean id="tempSvc" scope="page"
	class="com.temp.model.TempService"></jsp:useBean>

<%
	String temp_no = (String) request.getAttribute("temp_no");
	// 	String temp_no = "0001";
	// 	session.setAttribute("temp_no","0001");
%>

<%@ include file="page/photo_header.file"%>

<div class="col-xs-12 col-sm-9 ">
	<!-- Photo Start Here -->
	<div class="jumbotron text-center">
		<div class="text-right">
			<button type="submit" class="btn btn-default" id="uploadbtn">新增成品</button>
		</div>
		<!-- Modal addContent -->
		<form action="<%=request.getContextPath()%>/tempcont/tempcont.do"
			method="post" enctype="multipart/form-data">
			<div class="modal fade" id="uploadModal" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header" style="padding: 35px 50px;">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4>
								<span class="glyphicon glyphicon-picture"></span> 上傳照片
							</h4>
						</div>
						<div class="modal-body" style="padding: 40px 50px;">
							<div class="form-group">
								<label for="upload"> 選擇照片</label> <input type="file"
									class="form-control" name="uploadPic" id="upload"
									onchange="preview_images()" multiple>
							</div>

							<div id="showPanel"></div>

							<input type='submit' class="btn btn-info btn-block" value="新增">
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-default pull-left"
								data-dismiss="modal">
								<span class="glyphicon glyphicon-remove"></span> Cancel
							</button>
							<input type='hidden' name='action' value='insert_TempCont'>
							<input type='hidden' name='temp_no' value='<%=temp_no%>'>
						</div>
					</div>
				</div>
			</div>
		</form>
		<!-- End Modal addContent -->
		<!-- Modal delete Content -->
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
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						
						<button type="button" class="btn btn-danger" data-dismiss="modal" id='deletebtn'>刪除</button>
						
					</div>
				</div>

			</div>
		</div>
		<!--  End Modal Delete Content -->
		<h2>${tempSvc.getOneTemp(temp_no).name}</h2>
	</div>
	
	<c:forEach var="tempContVO" items="${tempContSvc.getAllByTempNo(temp_no)}" varStatus="s">
		<c:if test="${(s.count % 4) == 1}">
			<div class="row">
		</c:if>

		<div class="col-md-3 col-sm-3 col-xs-6">
			<div class="image">

				<a
					href="<%=request.getContextPath()%>/ShowPictureServletDAO?tcont_no=${tempContVO.tcont_no }"
					data-caption="Image caption" target="_blank"> 
					<img
					class="img-responsive img-thumbnail"
					src="<%=request.getContextPath()%>/ShowPictureServletDAO?tcont_no=${tempContVO.tcont_no }" />
				</a>
				<div class="overlap dropdown">
					<button class="btn btn-default btn-xs" type="submit"
						class='dropbtn'>
						<i class="fa fa-cog" aria-hidden="true"></i>
					</button>
					<div class='dropdownContent' id='dropdownContent${s.count}'>
					
						<form id="delete${s.count}" action="<%=request.getContextPath()%>/tempcont/tempcont.do" method="post">
						<input type='hidden' name='tcont_no' value='${tempContVO.tcont_no}'>
						<input type='hidden' name='action' value='delete_TempCont'>
						<input type='hidden' name='temp_no' value='<%=temp_no%>'>
						<a href='#' onclick="document.getElementById('delete${s.count}').submit();" >刪除相片</a>
<!-- 						<a href='#' data-toggle="modal" data-target="#deleteModal">刪除相片</a> -->
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