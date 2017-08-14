<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<jsp:useBean id="tempSvc" scope="page"
	class="com.temp.model.TempService" />
<jsp:useBean id="comSvc" scope="page" class="com.com.model.ComService" />
<%
	//String mem_no = (String)session.getAttribute("mem_no");  
	String mem_no = "1001";
	session.setAttribute("mem_no", mem_no);
%>


<%@ include file="page/temp_mem_header.file"%>

<style type="text/css">
.table-hover>tbody>tr:hover {
	cursor: pointer;
	background-color: rgb(247, 209, 211);
}
</style>



<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
<div class="container">
    <div class="col-md-offset-1">
        <ul class="breadcrumb">
            <li><a href="#">首頁</a></li>
            <li><a href="#">會員專區</a></li>
            <li class="active">作品挑選管理</li>        
        </ul>
    </div>
</div>
<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
   
    <div class="container">
        <div class="row">
<!--sidebar sidebar sidebar sidebar sidebar sidebar -->
            <div class="col-md-offset-1 col-md-2">
                 <ul class="list-group">
                   <a href="<%=request.getContextPath()%>/Front_end/mem/updatemember.jsp" class="list-group-item menua">編輯個人資料</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/mem/updatePwd.jsp" class="list-group-item menua">密碼修改</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/reservation/memReservation.jsp" class="list-group-item menua">預約紀錄查詢</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/RFQ/listMyRFQ.jsp" class="list-group-item menua">報價紀錄查詢</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/Temp/MemPage_ListAllTemps.jsp" class="list-group-item menua active">作品挑選管理</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/Album/ListAllAlbums.jsp" class="list-group-item menua">我的相簿</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/ComTra/ListAllComTra.jsp" class="list-group-item menua">我的最愛</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/Preview/ImageCropper.jsp" class="list-group-item menua">實景預覽</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/mall/index.jsp" class="list-group-item menua">商城專區</a>
					<br>
                </ul>
                <a href="#" class="btn btn-block btn-default">查看個人資料</a>
            </div>
<!--sidebar sidebar sidebar sidebar sidebar sidebar -->

<!--這裡開始===========================================================================-->

            <div class="col-md-8 col-offset-1">
            

            	<!-- The lightbox Modal (img)-->
			<div id="lightboxImgModal" class="modal">
				<span class="closeImg">&times;</span> <img
					class="lightbox-modal-content" id="lightboxImg">
			</div>
			<!-- The lightbox Modal (img)-->

			<!-- The lightbox Modal (vdo)-->
			<div id="lightboxVdoModal" class="modal">
				<span class="closeVdo">&times;</span>
				<video controls class="lightbox-modal-content" id="lightboxVdo" >
					<source type="video/mp4">
					您的瀏覽器不支援此撥放程式
				</video>

			</div>
			<!-- The lightbox Modal (vdo)-->
            
            
            

            <ul class="nav nav-tabs nav-justified" role="tablist">
				<li class="active"><a data-toggle="tab" href="#unselect">未挑選</a></li>
				<li><a data-toggle="tab" href="#selected">已挑選</a></li>

			</ul>

			<br>


			<div class="tab-content" style="border:0">
				<div id="unselect" class="tab-pane fade in active">
            
            
            
			<table class="table table-hover table-responsive tempList">
				<thead>
					<tr>
						
						<th>待挑選作品</th>
						<th>廠商名稱</th>
						<th>可挑選數量</th>
						<th>拍攝時間</th>
						<th>狀態</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tempVO" items="${tempSvc.getAllByMemNo(mem_no)}"
						varStatus="s">
						<c:if test='${tempVO.status.equals("未挑選")}'>
						<tr onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/Temp/MemPage_ListAllTempConts.jsp?temp_no=${tempVO.temp_no}'">
							
							<td>${tempVO.name }</td>
							<td>${comSvc.getOneCom(tempVO.com_no).name}</td>
							<td>${tempVO.available}</td>
							<td>${tempVO.create_date.toString().substring(0,10)}</td>
							<td>${tempVO.status}</td>
						</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			</div>
			
			<div id="selected" class="tab-pane fade">
			<table class="table table-hover table-responsive tempList">
				<thead>
					<tr>
						<th>待挑選作品</th>
						<th>廠商名稱</th>
						<th>可挑選數量</th>
						<th>拍攝時間</th>
						<th>狀態</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tempVO" items="${tempSvc.getAllByMemNo(mem_no)}"
						varStatus="s">
						<c:if test='${tempVO.status.equals("已挑選")}'>
						<tr onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/Temp/MemPage_ListAllTempConts.jsp?temp_no=${tempVO.temp_no}'">
							
							<td>${tempVO.name }</td>
							<td>${comSvc.getOneCom(tempVO.com_no).name}</td>
							<td>${tempVO.available}</td>
							<td>${tempVO.create_date.toString().substring(0,10)}</td>
							<td>${tempVO.status}</td>
						</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			</div>
			
			
			</div>
		</div>
	</div>
</div>

<%@ include file="page/temp_footer.file"%>
