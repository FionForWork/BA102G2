<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="page/works_header.file"%>

<style type="text/css">
input[type=date], input[type=number], input[type=file] {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}


input[type=submit] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=submit]:hover {
	background-color: #45a049;
}

.formStyle {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}

.img-group {
	width: 80%;
	margin: auto;
}

img .preview {
	width: 100%;
	height: auto;
	margin-bottom: 20px;
}

.padding .img-container {
	padding: 0 1%;
}

.errorMsgs {
	color: red;
}

div.polaroid {
  width: 250px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  text-align: center;
}

div.caption {
  padding: 10px;
}
</style>



<%
	Map<String, String> errorMsgs = (Map) request.getAttribute("errorMsgs");
String com_no = (String) request.getParameter("com_no");
// 	String com_no = "2001";
pageContext.setAttribute("com_no", com_no);
%>




<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
<div class="container">
	<div class="col-md-offset-1">
		<ul class="breadcrumb">
			<li><a href="#">首頁</a></li>
			<li><a href="#">廠商專區</a></li>
			<li><a href="#">作品管理</a></li>
			<li class="active">新增作品</li>
		</ul>
	</div>
</div>
<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->

<div class="container">
	<div class="row">
		<!--sidebar sidebar sidebar sidebar sidebar sidebar -->
		<div class="col-md-offset-1 col-md-2">
			<ul class="list-group">
				<a href="#" class="list-group-item menua">編輯廠商資料</a>
				<br>
				<a href="#" class="list-group-item menua">修改密碼</a>
				<br>
				<a href="#" class="list-group-item menua">預約紀錄查詢</a>
				<br>
				<a href="#" class="list-group-item menua">報價紀錄查詢</a>
				<br>
				<a href="#" class="list-group-item menua active">作品挑選管理</a>
				<br>
				<a href="#" class="list-group-item menua">行事曆</a>
				<br>
				<a href="#" class="list-group-item menua">作品管理</a>
				<br>
			</ul>


			<a href="#" class="btn btn-block btn-default">查看廠商資料</a>
		</div>
		<!--sidebar sidebar sidebar sidebar sidebar sidebar -->

		<!--這裡開始===========================================================================-->

		<div class="col-md-8 col-offset-1">

			<div class='formStyle'>
				<form action="<%=request.getContextPath()%>/works/works.do"
					method="post" enctype="multipart/form-data">
					<h3>上傳作品</h3>
					
						
						
						<label for="upload">選擇作品上傳 </label> 
						<input id="upload" name="kartik-input-711[]" type="file" multiple class="file-loading">
						<br> 
						<input type="submit" value="上傳"> 
						
						<input type="submit" onclick="history.back()" value="取消"></input> <br>
						
						
						
						
						
			</div>
			<input type='hidden' name='action' value='upload_works'>
			<input type='hidden' name='com_no' value=''>

			</form>
			
			<c:forEach var="worksVO" items="${worksSvc.getAllByComNo(com_no)}"
				varStatus="s">
				<c:if test="${(s.count % 4) == 1}">
					<div class="row">
				</c:if>

				<!-- Modal delete works -->
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
									data-dismiss="modal"
									onclick="document.getElementById('delete${s.count}').submit();">刪除</button>

							</div>
						</div>

					</div>
				</div>
				<!--  End Modal Delete works -->

				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="image-container">

						<c:if test="${worksVO.vdo != null}">

							<div class="polaroid">
								<a
									href="<%=request.getContextPath()%>/ShowPictureServletDAO?works_no=${worksVO.works_no}"
									data-caption="Image caption" target="_blank"> <video
										width="400" controls class="img-responsive"
										style="width: 100%">
										<source
											src="<%=request.getContextPath()%>/ShowPictureServletDAO?works_no=${worksVO.works_no}"
											type="video/mp4">
										您的瀏覽器不支援此撥放程式
									</video>
								</a>
								<div class="caption">
									<label for='name'>作品名稱</label>
									<input type='text' id='name' name='name' value='${worksVO.name}' placeholder='未命名的作品'>
									<label for='upload_date'>上傳日期 </label>
									<input type='date' id='upload_date' name='upload_date' value='${worksVO.upload_date}'>
									<label for='works_desc'>作品敘述</label>
									<textarea name='works_desc' rows='5' cols='30' placeholder='請輸入作品敘述...'>${worksVO.works_desc}</textarea>
								</div>
							</div>


						</c:if>
						<c:if test="${worksVO.img != null}">
							<div class="polaroid">
							<a
								href="<%=request.getContextPath()%>/ShowPictureServletDAO?works_no=${worksVO.works_no}"
								data-caption="Image caption" target="_blank"> 
								<img
								class="img-responsive" style="width: 100%"
								src="<%=request.getContextPath()%>/ShowPictureServletDAO?works_no=${worksVO.works_no}" />
							</a>
								<div class="caption">
								<label for='name'>作品名稱</label>
									<input type='text' id='name' name='name' value='${worksVO.name}' placeholder='未命名的作品'>
									<label for='upload_date'>上傳日期 </label>
									<input type='date' id='upload_date' name='upload_date' value='${worksVO.upload_date}'>
									<label for='works_desc'>作品敘述</label>
									<textarea name='works_desc' rows='5' cols='30' placeholder='請輸入作品敘述...'>${worksVO.works_desc}</textarea>
									
								</div>
							</div>
						</c:if>
						<div class="overlap dropdown">
							<button class="btn btn-default btn-xs" type="submit"
								class='dropbtn'>
								<i class="fa fa-cog" aria-hidden="true"></i>
							</button>

							<div class='dropdownContent' id='dropdownContent${s.count}'>
								<form id="delete${s.count}"
									action="<%=request.getContextPath()%>/works/works.do"
									method="post">
									<input type='hidden' name='works_no' value='${worksVO.works_no}'>
									<input type='hidden' name='action' value='delete_Works'>
									<input type='hidden' name='com_no' value='<%=com_no%>'>
									<a href='#' data-toggle='modal'
										data-target='#deleteModal${s.count}'>刪除相片</a>
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
	</div>



	<%@ include file="page/works_footer.file"%>