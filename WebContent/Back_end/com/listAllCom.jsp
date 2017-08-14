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
<%@ include file="page/backEnd_Header.file" %>
	<!-- Start content ===================================================================================================== -->
<div >

<div id="content">
	<div class="content-wrapper">
		<br><br>
	</div>
	<div class="outlet">
<!-- 內文 -->
		<%if (request.getAttribute("listComDeatil")!=null){%>
			<jsp:include page="listComDetail.jsp" />
		<%} %>
		<%@ include file="page/page1.file" %>
			<table class="table table-striped">
				<tr>
					<th>照片</th>
					<th>廠商編號</th>
					<th>廠商帳號</th>
					<th>廠商名稱</th>
					<th>電話</th>
					<th>狀態</th>
					<th>查看全部</th>
					<th>修改</th>
					<th>刪除</th>
				</tr>
				<c:forEach var="comVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr align='center' valign='middle'>
						<td ><img src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${comVO.com_no}" height="100" width="120"/></td>
						<td>${comVO.com_no}</td>
						<td>${comVO.id}</td>
						<td>${comVO.name}</td>
						<td>${comVO.phone}</td>
						<td>${comVO.status}</td>
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/com/backendcom.do">
						    <input type="submit" value="查看全部">
						    <input type="hidden" name="com_no" value="${comVO.com_no}" >
						    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>" >
						    <input type="hidden" name="action"	value="getOne_For_Display"></FORM>
						</td>
						<td></td><td></td>
					</tr>
				</c:forEach>
			</table>
		<%@ include file="page/page2.file" %>
<input type="button" value="顯示員工資料"	onclick="getCompany();">
<div id="showPanel"></div>
<!-- 內文 -->		
		</div>
	</div>
</div>
	
	<div class="clearfix"></div>
</body>
<script>
function getCompany(){	
	
	var xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function (){
		if( xhr.readyState == 4){
			if( xhr.status == 200){
				showCom(xhr.responseText); //jsonStr
			}else{
				alert( xhr.status );
			}
		}
	};//function 
	  
	var data_info = "action=ajax&com_no=2001";
	var url = "<%=request.getContextPath()%>/com/backendcom.do";
	xhr.open( "post" , url, true);
	//送出請求
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.send( data_info );
	
}

function showCom(jsonStr){

	  var com = JSON.parse(jsonStr);

	  //準備要輸出的網頁結構
	  var str = com.d +"<br>"+ com.a+"<br>"+com.b+"<br>"+com.c+"<br>"+com.com_no; 

	  //放入頁面
	  document.getElementById("showPanel").innerHTML = str;
	}
</script>
</html>
