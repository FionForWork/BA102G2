<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.calendar.model.*" %>
<%@ page import="com.com.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.temporal.TemporalAdjusters" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="Short Icon" href="<%=request.getContextPath()%>/Front_end/Resource/img/ring_64.ico">
<%
	// 廠商資料
	ComVO comVO = (ComVO)session.getAttribute("comVO");	
	
	int dayOfWeek = 0;int week = 1;int flag = 0;
	LocalDate localDate = (LocalDate)request.getAttribute("localDate");
	if(localDate == null){
		localDate = LocalDate.now();
	}
	// 本月最後一天的日期(LocalDate)
	LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
	// 本月共有幾天
	int dayNum = lastDayOfMonth.getDayOfMonth();
	// 本月第一天是星期幾
	int firstDayOfWeek = localDate.withDayOfMonth(1).getDayOfWeek().getValue();
	Timestamp t = new Timestamp(System.currentTimeMillis());
	CalendarService calerdarService = new CalendarService();
	// 廠商行事曆查詢
	List<CalendarVO> list = calerdarService.getMonthCalendar(localDate.getYear(), localDate.getMonthValue(), dayNum, comVO.getCom_no());
	pageContext.setAttribute("month", localDate.getMonthValue());
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body onload="connect();" onunload="disconnect();">
<%@ include file="page/headerWithoutSidebar.file" %>
<script type="text/javascript">
$(document).ready(function(){
	window.scrollTo(0, 525);
});
</script>
<div class="container">
<div class="text-center col-md-offset-1 col-md-10">
<c:if test="${not empty errorMsgs}">
	<c:forEach var="message" items="${errorMsgs}">
		<div class=" alert alert-danger alert-dismissable fade in">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			${message}
		</div>
	</c:forEach>
</c:if>
<table class="table table-bordered ui-widget-head" >
	<thead>
		<tr>
			<th colspan="7" style="background-color:#587BF1" class="text-center">
				<form id="changeCalendar" method="post" action="<%= request.getContextPath() %>/calendar/calendar.do">
					<h3 style="color:white">
					<select name="year" onchange="change()" style="background-color:#587BF1;border:0">
						<% for(int i = 0; i < 5; i++){ %>
						<% if(i+2017 == localDate.getYear()){ %>
						<option value="<%= i+2017 %>" selected><%= i+2017 %></option>
						<% }else{ %>
						<option value="<%= i+2017 %>"><%= i+2017 %></option>
						<% }} %>
					</select>年
					 
					<select name="month" onchange="change()" style="background-color:#587BF1;border:0">
						<% for(int i = 0; i < 12; i++){ %>
						<% if(i+1 == localDate.getMonthValue()){ %>
						<option value="<%= i+1 %>" selected><%= i+1 %></option>
						<% }else{ %>
						<option value="<%= i+1 %>"><%= i+1 %></option>
						<% }} %>
					</select>月
					</h3>
					<input type="hidden" name="action" value="changeCalendar">
   					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				</form>
			</th>
		</tr>
		<tr>
			<th>週一</th>
			<th>週二</th>
			<th>週三</th>
			<th>週四</th>
			<th>週五</th>
			<th>週六</th>
			<th>週日</th>
		</tr>
	</thead>
	<tbody id="box">
		
		<% for(int i = 0; i < dayNum+firstDayOfWeek-1; i++){ %>
			<% if(i%7 == 0){ %>
			<tr>
			<% } %> 
<!-- 先把上個月的日期列出來 -->
			<% if(week == 1){ %>
				<% for(int j = 0; j < firstDayOfWeek-1; j++ ){ %> 
					<td></td>
					<% dayOfWeek++;%>
					<% week =2 ; %>
				<% } %>
				<% i += dayOfWeek-1; %>
<!-- 開始這個月的日期 -->
			<% }else{ %>
				<% dayOfWeek++; %>
				<% pageContext.setAttribute("date", i-firstDayOfWeek+2); %>
<%-- 				<td class="ui-widget-head calendar cal-td" id="<%= localDate.getYear() %>-<%= localDate.getMonthValue() %>-${date}" data-toggle="modal" data-target="#myModal" onclick="add(this)"> --%>

<%-- 				<p class="day"><%= i-firstDayOfWeek+2 %></p> --%>
<!-- 				<br> -->
<!-- 開始比對行事曆日期與當前的日期 -->
				<c:forEach var="calendarVO" items="${list}">
					<c:if test="${calendarVO.cal_date.getDate() == date}">
<!-- 行事曆行程 -->
						<c:if test="${calendarVO.status == '0'}">
						<td class="ui-widget-head busy-td" id="<%= localDate.getYear() %>-<%= localDate.getMonthValue() %>-${date}">
							<p class="day"><%= i-firstDayOfWeek+2 %></p><br>
							<div id="${calendarVO.cal_no}" class="draggable ui-widget-content" style="background-color:#58F4CF;cursor:all-scroll" onmouseenter="show(this)" onmouseleave="hide(this)">
								<button type="button" class="close" display="none" onclick="deleteSchedule(this)" style="display:none">&times;</button>
								<p style="margin-top:3px">${calendarVO.content}</p>
								<% flag = 1; %>
							</div>
						</td>
						</c:if>
<!-- 預約行程 -->
						<c:if test="${calendarVO.status != '0'}">
						<td class="ui-widget-head busy-td" id="<%= localDate.getYear() %>-<%= localDate.getMonthValue() %>-${date}">
							<p class="day"><%= i-firstDayOfWeek+2 %></p><br>
							<div id="${calendarVO.cal_no}" class="res-content" style="background-color:#FFA0C4;">
								<p style="margin-top:3px">
									<a href="<%=request.getContextPath()%>/Front_end/reservation/comReservation.jsp" style="color:black;cursor:pointer">${calendarVO.content}</a>
								</p>
								<% flag = 1; %>
							</div>
						</td>
						</c:if>
					</c:if>
				</c:forEach>
				<%if(flag == 0){ %>
						<td class="ui-widget-head calendar cal-td" id="<%= localDate.getYear() %>-<%= localDate.getMonthValue() %>-${date}" data-toggle="modal" data-target="#calModal" onclick="add(this)">
							<p class="day"><%= i-firstDayOfWeek+2 %></p><br>
						</td>
					<% } %>
				<% flag = 0; %>
<!-- 				</td> -->
			<% } %>
			<% if(dayOfWeek %7 == 0){ %>
			<% dayOfWeek = 0; %>
			</tr>
			<% } %>
		<% } %>
		<% dayOfWeek =0 ; week = 1; %>
	</tbody>
</table>
</div>
<br>
</div>
<!-- 新增Schedule -->
<form  id="addScheduleForm" method="post" action="<%= request.getContextPath() %>/calendar/calendar.do">
	<div id="calModal" class="modal fade" role="dialog" >
		<div class="modal-dialog">
		<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						為您的行事曆新增活動
					</h4>
					<hr>
				</div>
				<div class="modal-body form-group">
					<label>日期 : </label>
					<input type="text" id="date" class="form-control" name="cal_date" value="" readonly>
					<br>
					<label>活動內容 :</label>
					<textarea rows="3" class="form-control" name="content"></textarea>
				</div>
				<hr>
				<div class="modal-footer">
					<input type="hidden" name="action" value="addSchedule">
					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
<!-- 					<input type="submit" class="btn btn-info" value="新增活動"> -->
					<button type="button" class="btn btn-info" onclick="addSchedule()">新增活動</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</form>

<form id="updateDateForm" method="post" action="<%= request.getContextPath() %>/calendar/calendar.do">
	<input type="hidden" name="action" value="updateDate">
   	<input type="hidden" name="cal_no" id="dragid" value="">
  	<input type="hidden" name="cal_date" id="dropid" value="">
  	<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
</form>

<form id="deleteForm" method="post" action="<%= request.getContextPath() %>/calendar/calendar.do">
	<input type="hidden" name="date" value=<%= localDate.toString() %>>
	<input type="hidden" name="action" value="deleteSchedule">
  	<input type="hidden" name="cal_no" id="cal_no" value="">
  	<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
</form>
<!-- For WebSocket -->
<input type="hidden" id="thisDate" value="">
<input type="hidden" id="toDate" value="">
<%@ include file="page/footerWithoutSidebar.file" %>
</body >
<script>
    
    var MyPoint = "/ResServer/SSY/<%= comVO.getCom_no() %>";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    
	var statusOutput = document.getElementById("statusOutput");
	var webSocket;
	
	function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
// 			updateStatus("WebSocket 成功連線");
// 			document.getElementById('sendMessage').disabled = false;
// 			document.getElementById('connect').disabled = true;
// 			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
	        var jsonObj = JSON.parse(event.data);
	        var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
	        
	        var action = jsonObj.action;
	        if(action == "onRes"){
	        	var thisDate = document.getElementById(jsonObj.thisDate);
	        	var name = jsonObj.name+"預約了新服務!";
	        	var a = $('<a href="<%=request.getContextPath()%>/Front_end/reservation/comReservation.jsp" style="color:black;cursor:pointer">').text(name);
				var content = $("<div style='background-color:pink'>").html(a);
	        	$(thisDate).append(content);
	        }
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
	}
	
	function changeSchedule() {
		
		var jsonObj = {"thisDate" : $("#thisDate").val(),
						"toDate" : $("#toDate").val(),
						"action" : "changeSchedule"};
		
		webSocket.send(JSON.stringify(jsonObj));
		
	}

	function onDeleteSchedule() {
		
		var jsonObj = {"thisDate" : $("#thisDate").val(),
						"action" : "deleteSchedule"};
		
		webSocket.send(JSON.stringify(jsonObj));
		
	}
	
	function addSchedule() {

		$('#thisDate').val($('#date').val().replace(/-/g,""));
		
		var jsonObj = {"thisDate" : $("#thisDate").val(),
						"action" : "addSchedule"};
		
		webSocket.send(JSON.stringify(jsonObj));
		$('#addScheduleForm').submit();
	}
	
	function disconnect () {
		webSocket.close();

	}

	
	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
    
</script>
</html>