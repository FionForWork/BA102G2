<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*" %>


<jsp:useBean id="tempSvc" scope="page"
	class="com.temp.model.TempService" />
<jsp:useBean id="comSvc" scope="page" class="com.com.model.ComService" />
<%
	MemVO memVO = (MemVO)session.getAttribute("memVO");  
	System.out.println("MemVO"+memVO);

	//String mem_no = "1001";
	//session.setAttribute("mem_no", mem_no);
%>


<%@ include file="page/temp_mem_header.file"%>

<style type="text/css">
.table-hover>tbody>tr:hover {
	cursor: pointer;
	background-color: rgb(247, 209, 211);
}
</style>



   
    
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
					<c:forEach var="tempVO" items="${tempSvc.getAllByMemNo(memVO.mem_no)}"
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
					<c:forEach var="tempVO" items="${tempSvc.getAllByMemNo(memVO.mem_no)}"
						varStatus="s">
						<c:if test='${tempVO.status.equals("已挑選")}'>
						<tr onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/Temp/MemPage_ListAllTempConts.jsp?temp_no=${tempVO.temp_no}'">
							
							<td>${tempVO.name}</td>
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
