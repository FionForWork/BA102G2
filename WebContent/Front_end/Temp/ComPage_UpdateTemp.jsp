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
	session.setAttribute("temp_no", temp_no);
	TempService tempSvc = new TempService();
	TempVO temp = tempSvc.getOneTemp(temp_no);
	request.setAttribute("temp", temp);
	
%>

<%@ include file="page/photo_header.file"%>

<div class="col-xs-12 col-sm-9 ">

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
					<p>你確定想刪除嗎？在這本成品中的相片也會被刪除。</p>
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
			<div>
				<form action="<%=request.getContextPath()%>/temp/temp.do" method="post">
					<h2>
						<div class="input-group">
						
						<label for="name" class="input-group-addon">更改名稱 <span class='errorMsgs'> ${errorMsgs.get("name")}</span></label> 
						<input type='text' id="name"
							name='name' value='${temp.name}' class="form-control">
							</div>
					</h2>

					<h4>
						<div class="input-group">
							<label for="create_date" class="input-group-addon">更改日期  <span class='errorMsgs'> ${errorMsgs.get("create_date")}</span></label>
							<input type='date' name='create_date' class="form-control"
								id="create_date"
								value='${temp.create_date.toString().substring(0,10)}'>
						</div>
					</h4>
					<h4>
						<div class="input-group">
							<label class="input-group-addon" for="status">更改狀態</label> <select
								id="status" name="status" class="form-control">
								<option value="已挑選" ${temp.status.equals("已挑選")? 'selected':'' }>已挑選</option>
								<option value="未挑選" ${temp.status.equals("未挑選")? 'selected':'' }>未挑選</option>
							</select>
						</div>
					</h4>
					<h4>
						<div class="input-group">
							<label class="input-group-addon" for="available"> 更改可挑選數量  ${errorMsgs.get("available_empty")} ${errorMsgs.get("available_number")}</label>
							<input type='number' name='available' id="available"
								value='${temp.available}' class="form-control">
						</div>
					</h4>
					<input type='hidden' name='action' value='update_Temp'> 
					<input type='hidden' name='temp_no' value='${temp_no}'> 
					<input type='hidden' name='mem_no' value='${temp.mem_no}'>
					<input
						type='submit' name='submit' value='儲存' class='btn btn-info btn-block'>
				</form>
				<br>
				<form action="<%=request.getContextPath()%>/temp/temp.do" method="post" id='deleteTemp'>
					<input type='hidden' name='action' value='delete_Temp'> 
					<input type='hidden' name='temp_no' value='${temp_no}'> 
					<input type='hidden' name='mem_no' value='${temp.mem_no}'>
					<input data-toggle='modal' data-target='#deleteTempModal' class='btn btn-default btn-block' value="刪除成品" />
				</form>
			</div>
		</div>
	</div>

	<c:forEach var="tempContVO" items="${tempContSvc.getAllByTempNo(temp_no)}" varStatus="s">
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
			<div class="image">

				<a
					href="<%=request.getContextPath()%>/ShowPictureServletDAO?tcont_no=${tempContVO.tcont_no }"
					data-caption="Image caption" target="_blank"> <img
					class="img-responsive img-thumbnail"
					src="<%=request.getContextPath()%>/ShowPictureServletDAO?tcont_no=${tempContVO.tcont_no }" />
				</a>
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
								type='hidden' name='temp_no' value='<%=temp_no%>'> 
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

<%@ include file="page/album_footer.file"%>