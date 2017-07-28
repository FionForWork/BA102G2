<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.comtra.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="comSvc" scope="page" class="com.com.model.ComService"/>
<%
	//String mem_no = (String)session.getAttribute("mem_no");
	String mem_no = "1001";	
	ComTraService comtraSvc = new ComTraService();
	List<ComTraVO> comtraList = comtraSvc.getComTraByMemNo(mem_no);
	pageContext.setAttribute("comtraList", comtraList);
%>

<%@ include file="page/pagination.file"%>
<%@ include file="page/comtra_header.file"%>

<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
<div class="container">
	<div class="col-md-offset-1">
		<ul class="breadcrumb">
			<li><a href="#">首頁</a></li>
			<li><a href="#">會員專區</a></li>
			<li class="active">我的最愛</li>
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
				<a href="#" class="list-group-item menua">我的相簿</a>
				<br>
				<a href="#" class="list-group-item menua active">我的最愛</a>
				<br>
				<a href="#" class="list-group-item menua">商城專區</a>
				<br>
			</ul>


			<a href="#" class="btn btn-block btn-default">查看個人資料</a>
		</div>
		<!--sidebar sidebar sidebar sidebar sidebar sidebar -->

		<!--這裡開始===========================================================================-->

		<div class="col-md-8 col-offset-1">


			<div class="clearfix">
				<h1 class="text-center" style="font-size: 26px;">
					<i class="fa fa-heart text-pink" aria-hidden="true"></i> 我的最愛 <span
						class="dropdown collect-dropdown">
						<button class="btn btn-outline" id="collects_type" type="button"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							選擇種類 <span class="caret"></span>
						</button>

						<ul id="collects_type_list" class="dropdown-menu"
							aria-labelledby="collects_type" role="tablist">
							<li role="presentation" class><a href="#store"
								aria-controls="store" role="tab" data-toggle="tab">店家收藏</a></li>
							<li role="presentation" class><a href="#service"
								aria-controls="service" role="tab" data-toggle="tab">包套/服務</a></li>
							<li role="presentation" class><a href="#image"
								aria-controls="image" role="tab" data-toggle="tab">相片作品 </a></li>
							<li role="presentation" class><a href="#dress"
								aria-controls="dress" role="tab" data-toggle="tab">婚紗禮服 </a></li>
							<li role="presentation" class><a href="#post"
								aria-controls="post" role="tab" data-toggle="tab">分享文收藏 </a></li>
						</ul>
					</span>
				</h1>
			</div>

			<!--table table table table table table table table table -->
			<h3>店家收藏</h3>
			<table class="table table-hover table-striped" id='comtraList'>

				<thead>
					<tr>
						<th>#</th>
						<th>店家</th>
						<th>收藏日期</th>
						<th>變更狀態</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="comtraVO" items="${comtraList}" varStatus="s"
						begin="<%=pageIndex%>" end="<%=pageIndex + rowsPerPage-1%>">
						<form action='<%=request.getContextPath()%>/comtra/comtra.do' method='post' id='deleteComTra${s.count}'>
							<input type='hidden' name='action' value='delete_ComTra'>
							<input type='hidden' name='comtra_no' value='${comtraVO.comtra_no}'>
						</form>
						<a href="#">
						<tr>
							<td>${s.count}</td>
							<td>${comSvc.getOneCom(comtraVO.com_no).name}</td>
							<td>${comtraVO.tracking_date.toString().substring(0,10)}</td>
							<td><a href='#' onclick='document.getElementById("deleteComTra${s.count}").submit();'><i class='fa fa-heart text-pink'></i>&nbsp;&nbsp;取消收藏</a></td>
						</tr>
						</a>
					</c:forEach>
					
				</tbody>
			</table>
			<!--table table table table table table table table table -->
			<div class="text-center">
				<ul class="pagination pagination-lg">
					<c:choose>
						<c:when test="<%= nowPage < 5 %>">
							
							<c:forEach var="page" begin="1" end="5">
								<li><a href='<%=request.getContextPath()%>/Front_end/ComTra/ListAllComTra.jsp?nowPage=${page}' class="btn btn-info">${page}</a></li>
							</c:forEach>
							<c:if test="<%=pageNumber > 5 %>">
								<li><a class='disabled'>...</a></li>
								<li><a href="<%=request.getContextPath()%>/Front_end/ComTra/ListAllComTra.jsp?nowPage=<%=pageNumber%>"><%= pageNumber %></a></li>
							</c:if>
							
						</c:when>
						<c:when test="<%= nowPage > (pageNumber-4) %>">
							<li><a href="<%=request.getContextPath()%>/Front_end/ComTra/ListAllComTra.jsp?nowPage=1">1</a></li>
							<li><a class='disabled'>...</a></li>
							<c:forEach var="page" begin="<%= pageNumber -4 %>"
								end="<%=pageNumber%>">
								<li><a href="<%=request.getContextPath()%>/Front_end/ComTra/ListAllComTra.jsp?nowPage=${page}" class="btn btn-info">${page}</a></li>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<li><a href="<%=request.getContextPath()%>/Front_end/ComTra/ListAllComTra.jsp?nowPage=1">1</a></li>
							<li><a class='disabled'>...</a></li>
							<c:forEach var="page" begin="<%=nowPage-2 %>"
								end="<%=nowPage+2 %>">
								<li><a href="<%=request.getContextPath()%>/Front_end/ComTra/ListAllComTra.jsp?nowPage=${page}" class="btn btn-info">${page}</a></li>
							</c:forEach>
							<li><a class='disabled'>...</a></li>
							<li><a href="<%=request.getContextPath()%>/Front_end/ComTra/ListAllComTra.jsp?nowPage=<%=pageNumber%>"><%=pageNumber %></a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
		<!--上面放東西===========================================================================-->
	</div>
</div>


<%@ include file="page/comtra_footer.file"%>