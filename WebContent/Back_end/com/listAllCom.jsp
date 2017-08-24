<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.com.model.*" %>
<!DOCTYPE html>
<%
    ComService comSvc = new ComService();
    List<ComVO> list = comSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<html>
<%@ include file="page/back_end_index.file" %>
	<!-- Start content ===================================================================================================== -->
<div>
<Style>
	th{
		text-align:center;
	}
</Style>
<div id="content">
	<div class="content-wrapper">
		<br><br>
	</div>
	<div class="outlet">
<!-- 內文 -->
<div class="text-center well" >
	<h2 style="font-weight:900">廠商資料一覽</h3>
</div>
	<a class="btn btn-info" href="<%=request.getContextPath()%>/Back_end/serv/select_Serv.jsp">廠商服務一覽</a>
		<%if (request.getAttribute("listComDeatil")!=null){%>
			<jsp:include page="listComDetail.jsp" />
		<%} %>
		<%@ include file="page/page1.file" %>
			<table class="table table-striped">
				<tr align='center' valign='middle'>
					<th>廠商編號</th>
					<th>廠商名稱</th>
					<th>廠商帳號</th>
					<th>電話</th>
					<th>狀態</th>
					<th>查看全部</th>
				</tr>
				<c:forEach var="comVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr align='center' valign='middle'>
						<td>${comVO.com_no}</td>
						<td>${comVO.name}</td>
						<td>${comVO.id}</td>
						<td>${comVO.phone}</td>
						<td>${comVO.status}</td>
						<td>
							<button id="delbtn" class="btn btn-info" onclick="showComDetail(this,${comVO.com_no})">查看詳細資料</button>
						</td>
					</tr>
					<tr>
						<td colspan="9" id="${comVO.com_no}" style="display:none"></td>
					</tr>
				</c:forEach>
			</table>

<div class="text-center">
	<ul class="pagination">
		<li><a  href="<%=request.getRequestURI()%>?whichPage=<%=whichPage-1%>">上一頁</a></li>
		<% for(int i = 0; i < pageNumber; i++){
			pageContext.setAttribute("i", i+1);%>
		<li><a  ${whichPage==i?"class='btn btn-info active'":"" } href="<%= request.getRequestURI()%>?whichPage=<%=i + 1%>"><%=i + 1%></a></li>
		<% } %>
		<li><a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage+1%>">下一頁</a></li>
	</ul>
</div> 
<%-- 		<%@ include file="page/page2.file" %> --%>
<!-- 內文 -->		
		</div>
	</div>
</div>
<div class="clearfix"></div>
</body>
<script>

function showComDetail(y,com_no){
	if($(y).html() == "查看詳細資料"){
		if($("#"+com_no).html() != ""){
			$("#"+com_no).show();
			$(y).html("隱藏詳細資料");
		}else{
			$("#"+com_no).load("listComDetail.jsp?com_no="+com_no).show();
			$(y).html("隱藏詳細資料");
		}
	}else{
		$(y).html("查看詳細資料");
		$("#"+com_no).hide();
	}
}

</script>
</html>
