<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.comtra.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*" %>
<jsp:useBean id="comSvc" scope="page" class="com.com.model.ComService"/>
<%
	//String mem_no = (String)session.getAttribute("mem_no");
	//String mem_no = "1001";	
	MemVO memVO = (MemVO)session.getAttribute("memVO");  
	System.out.println("MemVO"+memVO);
	ComTraService comtraSvc = new ComTraService();
	List<ComTraVO> comtraList = comtraSvc.getComTraByMemNo(memVO.getMem_no());
	pageContext.setAttribute("comtraList", comtraList);
%>

<%@ include file="page/pagination.file"%>
<%@ include file="page/comtra_header.file"%>

<% pageContext.setAttribute("nowPage", nowPage); %>



		<div class="col-md-8 col-offset-1">
			<div class="clearfix">
				<h1 class="text-center" style="font-size: 26px;">
					<i class="fa fa-heart text-pink" aria-hidden="true"></i> 我的最愛 
				</h1>
			</div>
			<br>
			<!--table table table table table table table table table -->
			<div id="snackbar">成功取消收藏...</div>
			<div id='changeContent'>
			
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
							<input type='hidden' name='nowPage' value='${nowPage}'>
							<input type='hidden' name='requestURL' value='<%=request.getServletPath()%>'>
						</form>
						<tr>
							<td>${s.count}</td>
							<td><a href='<%=request.getContextPath()%>/Front_end/com_page/company_page.jsp?com_no=${comtraVO.com_no}'>${comSvc.getOneCom(comtraVO.com_no).name}</a></td>
							<td>${comtraVO.tracking_date.toString().substring(0,10)}</td>
							<td><a onclick="javascript:doAjax('delete_ComTra','${comtraVO.comtra_no}','${nowPage}');"><i class='fa fa-heart text-pink'></i>&nbsp;&nbsp;取消收藏</a></td>
<%-- 							<td><a href='#' onclick='document.getElementById("deleteComTra${s.count}").submit();'><i class='fa fa-heart text-pink'></i>&nbsp;&nbsp;取消收藏</a></td> --%>
						</tr>
						
					</c:forEach>
					
				</tbody>
			</table>
			<!--table table table table table table table table table -->
			<div class="text-center">
				<ul class="pagination pagination-lg">
					<c:choose>
						<c:when test="<%= nowPage < 5 %>">
							<c:if test="<%=pageNumber < 5 %>">
								<c:forEach var="page" begin="1" end="<%=pageNumber%>">
								<li><a href='javascript:changePage(${page});' class="btn btn-info ${nowPage == page?'active':'' }">${page}</a></li>
								</c:forEach>
							</c:if>
							
							<c:if test="<%=pageNumber > 5 %>">
								<c:forEach var="page" begin="1" end="5">
								<li><a href='javascript:changePage(${page});' class="btn btn-info ${nowPage == page?'active':'' }">${page}</a></li>
								</c:forEach>
								<li><a class='disabled'>...</a></li>
								<li><a href="javascript:changePage(<%=pageNumber%>);"><%= pageNumber %></a></li>
							</c:if>
							
						</c:when>
						<c:when test="<%= nowPage > (pageNumber-4) %>">
							<li><a href="javascript:changePage(1)">1</a></li>
							<li><a class='disabled'>...</a></li>
							<c:forEach var="page" begin="<%= pageNumber -4 %>"
								end="<%=pageNumber%>">
								<li><a href="javascript:changePage(${page})" class="btn btn-info ${nowPage == page?'active':'' }">${page}</a></li>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<li><a href="javascript:changePage(1)">1</a></li>
							<li><a class='disabled'>...</a></li>
							<c:forEach var="page" begin="<%=nowPage-2 %>"
								end="<%=nowPage+2 %>">
								<li><a href="javascript:changePage(${page});" class="btn btn-info ${nowPage == page?'active':'' }">${page}</a></li>
							</c:forEach>
							<li><a class='disabled'>...</a></li>
							<li><a href="javascript:changePage(<%=pageNumber%>);"><%=pageNumber %></a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<!--  -->
			</div>
			<!--  -->
		</div>
		<!--上面放東西===========================================================================-->
	</div>
</div>

<script>


function changePage(nowPage){
	$("#changeContent").load("<%=request.getContextPath()%>/Front_end/ComTra/ListAllComTra.jsp #changeContent",{
		"nowPage":nowPage,
	});
}
function doAjax(action,comtra_no,nowPage){
	var scroll = $(window).scrollTop();
	
	$.ajax({
		url:'<%=request.getContextPath()%>/comtra/comtra.do',
		type:'POST',
		data:{
			comtra_no : comtra_no,
			action : action,
		},
		success:function success(){
			$("#snackbar").addClass("show");
			setTimeout('$("#snackbar").removeClass("show")',5000);
			
			$("#changeContent").load("<%=request.getContextPath()%>/Front_end/ComTra/ListAllComTra.jsp #changeContent",{
				"nowPage":nowPage
			});
		},
		error:function(xhr){
			alert('Ajax request error!');
		}
	});
}

</script>
<%@ include file="page/comtra_footer.file"%>