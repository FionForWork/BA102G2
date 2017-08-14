<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.temp.model.*"%>

<jsp:useBean id="tempContSvc" scope="page"
	class="com.tempcont.model.TempContService"></jsp:useBean>
<%-- <jsp:useBean id="tempSvc" scope="page" --%>
<%-- 	class="com.temp.model.TempService"></jsp:useBean> --%>

<%
	String temp_no = request.getParameter("temp_no");
	//String temp_no = "0017";
	TempService tempSvc = new TempService();
	TempVO temp = tempSvc.getOneTemp(temp_no);
	request.setAttribute("temp", temp);
	
%>

<%@ include file="page/temp_com_header.file"%>
<script type="text/javascript">
$("document").ready(function(){
	$("#datePicker").datepicker({dateFormat: 'yy-mm-dd',maxDate: "+0D"});
});
</script>

<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
<div class="container">
    <div class="col-md-offset-1">
        <ul class="breadcrumb">
            <li><a href="#">首頁</a></li>
            <li><a href="#">廠商專區</a></li>
            <li><a href="#">作品挑選管理</a></li>
            <li class="active">修改挑選作品 ${temp.name}</li>        
        </ul>
    </div>
</div>
<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
   
    <div class="container">
        <div class="row">
<!--sidebar sidebar sidebar sidebar sidebar sidebar -->
            <div class="col-md-offset-1 col-md-2">
                 <ul class="list-group">
                    <a href="#" class="list-group-item menua">編輯廠商資料</a><br>
                    <a href="#" class="list-group-item menua">修改密碼</a><br>
                    <a href="#" class="list-group-item menua">預約紀錄查詢</a><br>
                    <a href="#" class="list-group-item menua">報價紀錄查詢</a><br>
                    <a href="#" class="list-group-item menua active">作品挑選管理</a><br>
                    <a href="#" class="list-group-item menua">行事曆</a><br>
                    <a href="#" class="list-group-item menua">作品管理</a><br>
                </ul>


                <a href="#" class="btn btn-block btn-default">查看廠商資料</a>
            </div>
<!--sidebar sidebar sidebar sidebar sidebar sidebar -->

<!--這裡開始===========================================================================-->

            <div class="col-md-8 col-offset-1">


		<!-- Modal addContent -->
	<form action="<%=request.getContextPath()%>/tempcont/tempcont.do"
		method="post" enctype="multipart/form-data">
		<div class="modal fade" id="uploadModal" role="dialog">
			<div class="modal-dialog modal-lg">

				<!-- Modal add tempcont-->
				<div class="modal-content">
					<div class="modal-header" style="padding: 35px 50px;">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4>
							<span class="glyphicon glyphicon-picture"></span> 上傳照片或影片
						</h4>
					</div>
					<div class="modal-body" style="padding: 40px 50px;">
						<div class="form-group">
							<label for="inputFile"> 選擇照片或影片</label> 
							<input id="inputFile"
									name="inputFile[]" type="file" multiple class="file-loading"
									value='選擇照片'> <br>
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
	<!-- End Modal addtempcont -->



	<!-- Modal delete Temp -->
	<div class="modal fade" id="deleteTempModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">刪除成品</h4>
				</div>
				<div class="modal-body">
					<p>你確定想刪除「 ${temp.name} 」嗎？在這本成品中的相片也會被刪除。</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>

					<button type="button" class="btn btn-danger" data-dismiss="modal"
						id='deletebtn' onclick="document.getElementById('deleteTemp').submit();">刪除</button>

				</div>
			</div>

		</div>
	</div>
	<!--  End Modal Delete Temp -->
	<div class="jumbotron text-left">
		<div class="row">
			
				<form action="<%=request.getContextPath()%>/temp/temp.do" method="post" id='updateTemp'>
					<h4>
						<div class="input-group">
						<label for="name" class="input-group-addon">成品名稱 <span class='errorMsgs'> ${errorMsgs.get("name")}</span></label> 
						<input type='text' id="name"
							name='name' value='${temp.name}' class="form-control">
							</div>
					</h4>

					<h4>
						<div class="input-group">
							<label for="create_date" class="input-group-addon">建立日期  <span class='errorMsgs'> ${errorMsgs.get("create_date")}</span></label>
							<input type='text' name='create_date' class="form-control"
								id="datePicker" value='${temp.create_date.toString().substring(0,10)}'>
						</div>
					</h4>
					<h4>
						<div class="input-group">
							<label class="input-group-addon" for="status">目前狀態</label> <select
								id="status" name="status" class="form-control">
								<option value="已挑選" ${temp.status.equals("已挑選")? 'selected':'' }>已挑選</option>
								<option value="未挑選" ${temp.status.equals("未挑選")? 'selected':'' }>未挑選</option>
							</select>
						</div>
					</h4>
					<h4>
						<div class="input-group">
							<label class="input-group-addon" for="available"> 可挑選數量  ${errorMsgs.get("available_empty")} ${errorMsgs.get("available_number")}</label>
							<input type='number' name='available' id="available"
								value='${temp.available}' class="form-control">
						</div>
					</h4>
					
					<input type='hidden' name='action' value='update_Temp'> 
					<input type='hidden' name='temp_no' value='${temp.temp_no}'> 
					<input type='hidden' name='mem_no' value='${temp.mem_no}'>
					
				</form>
				<br>
				<form action="<%=request.getContextPath()%>/temp/temp.do" method="post" id='deleteTemp'>
					<input type='hidden' name='action' value='delete_Temp'> 
					<input type='hidden' name='temp_no' value='${temp.temp_no}'> 
					<input type='hidden' name='mem_no' value='${temp.mem_no}'>
					
				</form>
				<div class='btn-group'>
					<input value='儲存' class='btn btn-info' onclick="document.getElementById('updateTemp').submit();">
					<input class="btn btn-info" id="uploadbtn" value='新增相片或影片'>
					<input data-toggle='modal' data-target='#deleteTempModal' class='btn btn-default' value="刪除成品" />
					
				</div>
			
		</div>
	</div>



	<!-- The lightbox Modal (img)-->
			<div id="lightboxImgModal" class="modal">
				<span class="closeImg">&times;</span> <img
					class="lightbox-modal-content" id="lightboxImg">
			</div>
			<!-- The lightbox Modal (img)-->





	<c:forEach var="tempContVO" items="${tempContSvc.getAllByTempNo(temp.temp_no)}" varStatus="s">
		<c:if test="${(s.count % 4) == 1}">
			<div class="row">
		</c:if>
		<!-- Modal delete tempcont -->
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
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						
						<button type="button" class="btn btn-danger" data-dismiss="modal" onclick="document.getElementById('delete${s.count}').submit();">刪除</button>
						
					</div>
				</div>

			</div>
		</div>
		<!--  End Modal Delete Tempont -->
		<div class="col-md-3 col-sm-3 col-xs-6">
			<div class="image-container">

				<c:if test="${tempContVO.img == null}">
				
					<video width="400" controls class="img-responsive img-thumbnail">
					  <source src="<%=request.getContextPath()%>/ShowPictureServletDAO?tcont_no=${tempContVO.tcont_no }" type="video/mp4">
					  您的瀏覽器不支援此撥放程式
					</video>
				
			</c:if>
			<c:if test="${tempContVO.img != null}">
				
				<img
					class="img-responsive img-thumbnail aa"
					src="<%=request.getContextPath()%>/ShowPictureServletDAO?tcont_no=${tempContVO.tcont_no }" />
				
				</c:if>
				<div class="overlap dropdown">
					<button class="btn btn-default btn-xs" type="submit"
						class='dropbtn'>
						<i class="fa fa-cog" aria-hidden="true"></i>
					</button>
					<div class='dropdownContent' id='dropdownContent${s.count}'>

						<form id="delete${s.count}"
							action="<%=request.getContextPath()%>/tempcont/tempcont.do"
							method="post">
							<input type='hidden' name='tcont_no'
								value='${tempContVO.tcont_no}'> 
								<input type='hidden' name='action' value='delete_TempCont'> 
								<input
								type='hidden' name='temp_no' value='${temp.temp_no}'> 
								<a href='#' data-toggle='modal' data-target='#deleteModal${s.count}' >刪除相片</a>
							<!-- <a href='#' data-toggle="modal" data-target="#deleteModal">刪除相片</a> -->
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

<%@ include file="page/temp_footer.file"%>