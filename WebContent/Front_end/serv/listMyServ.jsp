
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.serv.model.*"%>

<%@ include file="/Front_end/com/page/share_header_v2.file"%>
<div class="container">
	<div class="row">
		<!--sidebar sidebar sidebar sidebar sidebar sidebar -->
		<div class="col-md-offset-1 col-md-2"><br><br>
			<ul class="list-group">
				<a href="<%=request.getContextPath()%>/Front_end/com/updatecompany.jsp" class="list-group-item menua">編輯廠商資料</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/com/updatePwd.jsp" class="list-group-item menua">修改密碼</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/Advertising/Advertising.jsp" class="list-group-item menua">廣告管理</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/reservation/comReservation.jsp" class="list-group-item menua ">預約紀錄查詢</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/quote/listMyQuote.jsp" class="list-group-item menua">報價紀錄查詢</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/Temp/ComPage_ListAllTemps.jsp" class="list-group-item menua">作品挑選管理</a><br>
                <a href="<%= request.getContextPath() %>/Front_end/calendar/calendar.jsp" class="list-group-item menua">行事曆</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/Works/ListAllWorks.jsp" class="list-group-item menua">作品管理</a><br>
			</ul>


			<a href="<%=request.getContextPath()%>/Front_end/com/listOneCom.jsp" class="btn btn-block btn-default">查看廠商資料</a>
		</div>
<title>我的服務</title>

<jsp:useBean id="selectByCom2" scope="request" type="java.util.Set" />
<div class="container" id="big">
	<div class="row">
		<div class="col-xs-12 col-sm-9">
	
	<h1 ><img src="<%= request.getContextPath() %>/Front_end/mem/img/ring_64.png">我的服務</h1>



<table class="table table-striped">
	<tr>
		<th>服務編號</th>
		<th>服務型態</th>
		<th>訂金</th>
		<th>價錢</th>
		<th>服務標題</th>
		<th width="25%">服務介紹</th>
		<th>被購買次數</th>
		<th>服務評價</th>
		<th>狀態</th>
		<th>修改</th>
		<th>修改狀態</th>
	</tr>
	

	<c:forEach var="servVO" items="${selectByCom2}" >
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/serv/serv.do">
		
		<tr align='center' valign='middle'>

			<td>${servVO.serv_no}</td>
			<td>${servVO.stype_no}</td>
			<td>${servVO.deposit}</td>
			<td>${servVO.price}</td>
			<td>${servVO.title}</td>
			<td>${servVO.content}</td>
			<td>${servVO.times}</td>
			<td>${servVO.score}</td>
			<td>${servVO.status}</td>
			<td><select style="width:150px;" name="status">
				<option value="正常">正常
				<option value="下架">下架
				</select>
		</td>
			<td>
			
			  
			    <input type="hidden" name="locs" value="/<%= request.getServletPath() %>">
			    <input type="hidden" name="serv_no" value="${servVO.serv_no}">
			    <input type="hidden" name="com_no" value="${servVO.com_no}">
			    <input type="submit" class="btn btn-info " value="修改狀態">
			    <input type="hidden" name="action" value="updateStatus2">
			 
			</td>
		</tr></FORM>
	</c:forEach>
</table>  
</div></div></div>

<%@ include file="/Front_end/mem/page/register_footer.file"%>


