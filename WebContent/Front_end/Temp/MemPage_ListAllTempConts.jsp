<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.temp.model.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="tempContSvc" scope="page"
	class="com.tempcont.model.TempContService"></jsp:useBean>
<%-- <jsp:useBean id="tempSvc" scope="page" --%>
<%-- 	class="com.temp.model.TempService"></jsp:useBean> --%>

<%
	String temp_no = (String) request.getParameter("temp_no");
	//String temp_no = "0017";
	pageContext.setAttribute("temp_no", temp_no);
	Map<String, String> errorMsgs = (Map) request.getAttribute("errorMsgs");
	String[] selectedList = (String[]) request.getAttribute("selectedList");
	TempService tempSvc = new TempService();
	TempVO temp = tempSvc.getOneTemp(temp_no);
	request.setAttribute("temp", temp);
%>

<%@ include file="page/temp_mem_header.file"%>

<!-- The lightbox Modal (img)-->
			<div id="lightboxImgModal" class="modal">
				<span class="closeImg">&times;</span> <img
					class="lightbox-modal-content" id="lightboxImg">
			</div>
			<!-- The lightbox Modal (img)-->


<%///////// 顯示未挑選頁面 ///////// %>
<c:if test='${temp.status.equals("未挑選")}'>
	<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
<div class="container">
    <div class="col-md-offset-1">
        <ul class="breadcrumb">
            <li><a href="#">首頁</a></li>
            <li><a href="#">會員專區</a></li>
            <li><a href="#">作品挑選管理</a></li>
            <li class="active">${temp.name}</li>        
        </ul>
    </div>
</div>
<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
   
    <div class="container">
        <div class="row">
<!--sidebar sidebar sidebar sidebar sidebar sidebar -->
            <div class="col-md-offset-1 col-md-2">
                 <ul class="list-group">
                    <a href="#" class="list-group-item menua">編輯個人資料</a><br>
                    <a href="#" class="list-group-item menua">密碼修改</a><br>
                    <a href="#" class="list-group-item menua">預約紀錄查詢</a><br>
                    <a href="#" class="list-group-item menua">報價紀錄查詢</a><br>
                    <a href="#" class="list-group-item menua active">作品挑選管理</a><br>
                    <a href="#" class="list-group-item menua">我的相簿</a><br>
                    <a href="#" class="list-group-item menua">我的最愛</a><br>
                    <a href="#" class="list-group-item menua">商城專區</a><br>
                </ul>


                <a href="#" class="btn btn-block btn-default">查看個人資料</a>
            </div>
<!--sidebar sidebar sidebar sidebar sidebar sidebar -->

<!--這裡開始===========================================================================-->

            <div class="col-md-8 col-offset-1">

		<!-- Modal select tempconts -->
		<div class="modal fade" id="selectModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">確認挑選</h4>
					</div>
					<div class="modal-body">
						<p>確認後將無法再更改，確定挑選完成嗎?</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal"
							onclick="document.getElementById('selectTempCont').submit();">確認</button>

					</div>
				</div>

			</div>
		</div>
		<!--  End Modal select tempconts -->

		<!-- Start Modal outOfAvailable warning -->
		<div class="modal fade" id="outOfAvailableModal" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>

					</div>
					<div class="modal-body">
						<p>超過可挑選數量</p>
					</div>

				</div>
			</div>
		</div>
		<!-- End Modal outOfAvailable warning -->


		<!-- Start Modal unselected warning -->
		<div class="modal fade" id="unselectedModal" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>

					</div>
					<div class="modal-body">
						<p>您尚未挑選任何照片或影片</p>
					</div>

				</div>
			</div>
		</div>
		<!-- End Modal unselected warning -->
		<div class="jumbotron">
			<div class="row">

				<div class="col-xs-12 col-sm-12">
					<div class="text-center">
						<span class='errorMsgs'>${errorMsgs.outOfAvailable}</span>
						<h2>${temp.name}</h2>
						<h4>建立日期 : ${temp.create_date.toString().substring(0,10)}</h4>
						<h4>目前狀態 : ${temp.status}</h4>
						<h4>
							可挑選數量 : <span id='availableNum'>${temp.available}</span> ; 已挑選數量
							: <span id='numberOfSelected'> 0</span>
						</h4>
						<button class="btn btn-info" style="margin-button: 0;"
							id='selectConfirm'>確認挑選</button>
					</div>
				</div>
			</div>
		</div>
		
		
			
		
		
		
		<form action="<%=request.getContextPath()%>/tempcont/tempcont.do"
			method="post" id='selectTempCont'>
			<c:forEach var="tempContVO"
				items="${tempContSvc.getAllByTempNo(temp_no)}" varStatus="s">
				<c:if test="${(s.count % 4) == 1}">
					<div class="row">
				</c:if>

				<div class="col-md-3 col-sm-3 col-xs-6">

					<div class="image-container">
						<c:if test="${tempContVO.img == null}">
							 <video
									width="400" controls class="img-responsive img-thumbnail">
									<source
										src="<%=request.getContextPath()%>/ShowPictureServletDAO?tcont_no=${tempContVO.tcont_no }"
										type="video/mp4">
									您的瀏覽器不支援此撥放程式
								</video>
							
						</c:if>
						<c:if test="${tempContVO.img != null}">
							 <img
								class="img-responsive img-thumbnail aa"
								src="<%=request.getContextPath()%>/ShowPictureServletDAO?tcont_no=${tempContVO.tcont_no }" />
							

						</c:if>

						<label for='selectedPic${s.count}'>
							<div class='overlay text-center'>
								<i class='fa fa-check checkMark' aria-hidden='true'></i> <span
									class='textMark'>Select</span>
							</div>
						</label> <input type='checkbox' id='selectedPic${s.count}'
							name='selectedList' class='checkbox'
							value='${tempContVO.tcont_no}'
							${Arrays.asList(selectedList).contains(tempContVO.tcont_no)?'checked':''}>
					</div>

				</div>
				<c:if test="${(s.count % 4) == 0}">
	</div>
</c:if>
</c:forEach>
<input type='hidden' name='action' value='select_TempConts'>
<input type='hidden' name='temp_no' value='${temp_no}'>
</form>
<br>
</div>
</c:if>



<%///////// 顯示已挑選頁面 ///////// %>
<c:if test='${temp.status.equals("已挑選")}'>
	<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
<div class="container">
    <div class="col-md-offset-1">
        <ul class="breadcrumb">
            <li><a href="#">首頁</a></li>
            <li><a href="#">會員專區</a></li>
            <li><a href="#">作品挑選管理</a></li>
            <li class="active">${temp.name}</li>        
        </ul>
    </div>
</div>
<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
   
    <div class="container">
        <div class="row">
<!--sidebar sidebar sidebar sidebar sidebar sidebar -->
            <div class="col-md-offset-1 col-md-2">
                 <ul class="list-group">
                    <a href="#" class="list-group-item menua">編輯個人資料</a><br>
                    <a href="#" class="list-group-item menua">密碼修改</a><br>
                    <a href="#" class="list-group-item menua">預約紀錄查詢</a><br>
                    <a href="#" class="list-group-item menua">報價紀錄查詢</a><br>
                    <a href="#" class="list-group-item menua active">作品挑選管理</a><br>
                    <a href="#" class="list-group-item menua">我的相簿</a><br>
                    <a href="#" class="list-group-item menua">我的最愛</a><br>
                    <a href="#" class="list-group-item menua">商城專區</a><br>
                </ul>


                <a href="#" class="btn btn-block btn-default">查看個人資料</a>
            </div>
<!--sidebar sidebar sidebar sidebar sidebar sidebar -->

<!--這裡開始===========================================================================-->

            <div class="col-md-8 col-offset-1">
		<div class="jumbotron">
			<div class="row">

				<div class="col-xs-12 col-sm-12">
					<div class="text-center">
						<span class='errorMsgs'>${errorMsgs.outOfAvailable}</span>
						<h2>${temp.name}</h2>
						<h4>建立日期 : ${temp.create_date.toString().substring(0,10)}</h4>
						<h4>目前狀態 : ${temp.status}</h4>
					</div>
				</div>
			</div>
		</div>
		<c:forEach var="tempContVO"
			items="${tempContSvc.getAllByTempNo(temp_no)}" varStatus="s">
			<c:if test="${(s.count % 4) == 1}">
				<div class="row">
			</c:if>

			<div class="col-md-3 col-sm-3 col-xs-6">

				<div class="image-container">
					<c:if test="${tempContVO.img == null}">
						<video
								width="400" controls class="img-responsive img-thumbnail">
								<source
									src="<%=request.getContextPath()%>/ShowPictureServletDAO?tcont_no=${tempContVO.tcont_no }"
									type="video/mp4">
								您的瀏覽器不支援此撥放程式
							</video>
					
					</c:if>
					<c:if test="${tempContVO.img != null}">
						 <img
							class="img-responsive img-thumbnail aa"
							src="<%=request.getContextPath()%>/ShowPictureServletDAO?tcont_no=${tempContVO.tcont_no }" />
						
					</c:if>
				</div>
			</div>
			<c:if test="${(s.count % 4) == 0}">
	</div>
</c:if>
</c:forEach>
<br>
</div>
</c:if>


<%@ include file="page/temp_footer.file"%>