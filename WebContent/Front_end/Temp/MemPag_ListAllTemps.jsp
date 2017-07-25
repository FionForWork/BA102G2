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


<%@ include file="page/photo_header.file"%>

<style type="text/css">
.table-hover>tbody>tr:hover {
	cursor: pointer;
	background-color: rgb(247, 209, 211);
}
</style>
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-9">
			<table class="table table-hover table-responsive" id="tempList">
				<caption>待挑選成品清單</caption>

				<thead>
					<tr>
						<th>#</th>
						<th>待挑選作品</th>
						<th>廠商名稱</th>
						<th>可挑選數量</th>
						<th>拍攝時間</th>
						<th>狀態</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tempVO" items="${tempSvc.getAllByMemNo(mem_no)}"
						varStatus="s">
						<form action="<%=request.getContextPath()%>/temp/temp.do"
							method="post" id='display${s.count}'>
						<tr onclick="document.getElementById('display${s.count}').submit();">
							<td>${s.count}</td>
							<td>${tempVO.name }</td>
							<td>${comSvc.getOneCom(tempVO.com_no).name}</td>
							<td>${tempVO.available}</td>
							<td>${tempVO.create_date.toString().substring(0,10)}</td>
							<td>${tempVO.status}</td>
							<td><input type='hidden' name='action' value='getOne_For_Display'> 
								<input type='hidden' name='temp_no' value='${tempVO.temp_no}'>							
						</tr>
						</form>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<%@ include file="page/album_footer.file"%>
