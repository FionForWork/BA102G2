<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.calendar.model.*" %>
<%@ page import="com.mem.model.*" %>
<%@ page import="com.com.model.*" %>
<%@ page import="com.serv.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.temporal.TemporalAdjusters" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="Short Icon" href="<%=request.getContextPath()%>/Front_end/Resource/img/ring_64.ico">
<%
	// 廠商資料
	String com_no = request.getParameter("com_no");	

	int dayOfWeek = 0;int week = 1;int flag = 0; int beforeToday = 0;
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
	
	MemService memService = new MemService();
	
	CalendarService calerdarService = new CalendarService();
	List<CalendarVO> list = calerdarService.getMonthCalendar(localDate.getYear(), localDate.getMonthValue(), dayNum, com_no);
	pageContext.setAttribute("month", localDate.getMonthValue());
	pageContext.setAttribute("list", list);
	List<ServVO> servList = new ServService().getCom(com_no);
	pageContext.setAttribute("servList", servList);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<style>
	
	td{
		position:relative;
		width:90px;
		height:110px;
 		overflow: hidden;
		color:black;
	}
	td a {
	    display: block;
 	    margin: -10em;
 	    padding: 10em;
	}
	td a:hover{
		color:#f14195;
	}
	.day {
		position:absolute;
		top:2px;
		left:2px;
		font-size:25px;
		font-weight:500;
	}
	
</style>
<body onload="connect();" onunload="disconnect();">
<%@ include file="page/searchServiceHeader.file" %>
<style>
	.table{
		border: 1px solid #404040;
	}
</style>
<script>
	function addRes(y){
		var date = $(y).attr("id").split("-");
		$('#res-date').html("您想預約" + date[0]+"年"+date[1]+"月"+date[2]+"日的服務是...");
		$('#serv_date').val($(y).attr("id"));
	}
	
	  function change(){
			
			$('#changeCalendar').submit();
		}
	
</script>
<div class="container">
<div class="text-center  col-md-offset-1 col-md-10">
<table class="table table-bordered">
	<thead>
		<tr>
			<th colspan="7" style="background-color:#FB7291" class="text-center">
				<form id="changeCalendar" method="post" action="<%= request.getContextPath() %>/reservation/reservation.do">
					<h3 style="color:white">
					<input type="hidden" name="com_no" value="<%= com_no %>">
					<select name="year" onchange="change()"  style="background-color:#FB7291;border:0">
						<% for(int i = 0; i < 5; i++){ %>
						<% if(i+2017 == localDate.getYear()){ %>
						<option value="<%= i+2017 %>" selected><%= i+2017 %></option>
						<% }else{ %>
						<option value="<%= i+2017 %>"><%= i+2017 %></option>
						<% }} %>
					</select>年
					 
					<select name="month" onchange="change()"  style="background-color:#FB7291;border:0">
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
	<tbody>
		
		<% for(int i = 0; i < dayNum+firstDayOfWeek-1; i++){ %>
			<% if(i%7 == 0){ %>
			<tr>
			<% } %> 
<!-- 先把上個月的日期列出來 -->
			<% if(week == 1){ %>
				<% for(int j = 0; j < firstDayOfWeek-1; j++ ){ %> 
					<td style="background-color:#D9D9D9"></td>
					<% dayOfWeek++;%>
					<% week =2 ; %>
				<% } %>
				<% i += dayOfWeek-1; %>
<!-- 開始這個月的日期 -->
			<% }else{
					dayOfWeek++;
					pageContext.setAttribute("date", i-firstDayOfWeek+2); %>
<!-- 檢查是否為今天以前的日期 -->
				<% if(localDate.withDayOfMonth(i-firstDayOfWeek+2).isBefore(LocalDate.now())){ %>
					<td style="background-color:#D9D9D9;cursor:not-allowed;">
						<p class="day"><%= i-firstDayOfWeek+2 %></p>
						<br>
					</td>
					<% beforeToday = 1;
				} %>
<!-- 是今天以後的日期，判斷是否能預約 -->
				<% if(beforeToday == 0){ 
				 for(CalendarVO calendarVO : list){ 
					if(calendarVO.getCal_date().getDate() == i-firstDayOfWeek+2){ 
						flag = 1; 
						break; 
					} 
				} %>
<!-- 行事曆有行程，無法預約 -->
					<% if(flag == 1){ %>
						<td id="<%= localDate.getYear() %><%=localDate.getMonthValue()%>${date}" style="background-color:#D9D9D9;cursor:not-allowed;">
							<p class="day"><%= i-firstDayOfWeek+2 %></p>
							<br>
							<a id="<%= localDate.getYear() %>-<%=localDate.getMonthValue()%>-${date}" href="" onclick="addRes(this)" data-toggle="modal" data-target="#resModal" class="menua" style="display:none">馬上預約</a>
						</td>
<!-- 行事曆沒行程，可以預約 -->
					<% } else{ %>
					<td id="<%= localDate.getYear() %><%=localDate.getMonthValue()%>${date}">
						<p class="day"><%= i-firstDayOfWeek+2 %></p>
						<br>
						<a id="<%= localDate.getYear() %>-<%=localDate.getMonthValue()%>-${date}" href="" onclick="addRes(this)" data-toggle="modal" data-target="#resModal" class="menua">馬上預約</a>
					</td>
					<% } %>
			<% } %>
<!-- 參數歸零 -->
			<% beforeToday = 0; %>
			<% flag = 0; %>
			<% } %>
<!-- 週日換行 -->
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

<!-- 預約單 -->
<form id="resForm" method="post" action="<%= request.getContextPath() %>/reservation/reservation.do">
<div id="resModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h3 id="res-date" class="modal-title" style="color:#f14195">
					您想預約<%= localDate.getYear() %>年<%=localDate.getMonthValue()%>月${date}日的服務是...
				</h3>
			</div>
			<div class="modal-body">
			<div class="row">
			<c:if test="${servList.size()==0}">
				<div class="text-center"><h3>很抱歉，廠商目前還沒有提供服務唷!</h3></div>
			</c:if>
			<c:forEach var="servVO" items="${servList}">
				<div class="col-xs-4">
				<div style="border-width:1px;border-style:solid;border-radius: 3px;padding:4px">
					<div class="text-center">
						<input style="zoom:150%;" type="radio" name="serv_no" value="${servVO.serv_no}">
						<b style="font-size:17px">${servVO.title}</b><hr>
					</div>
					<div style="height:100px;text-align:justify;">${servVO.content}</div><hr>
					<div class="label-price text-right">
						<span class="small">價格</span>
						<b class="price text-pink" >${servVO.price}</b>
						<span class="hidden-xs">元</span>
					</div>
					<div class="label-price text-right" style="font-size:12px">
						<span class="small">訂金</span>
						<span class="hidden-xs">${servVO.deposit}元</span>
					</div>
				</div>
				</div>
			</c:forEach>
			</div>
			</div><hr>
			<div class="modal-footer">
				
				<input type="hidden" name="action" value="resFromCalendar">
				<input type="hidden" id="serv_date" name="serv_date" value="">
				<input type="hidden" id="aID" value="">
				<input type="hidden" name="requestURI" value="<%=request.getRequestURI()%>">
<!-- 				<input type="submit" class="btn btn-info" value="送出預約"> -->
			<c:if test="${servList.size()!=0}">
				<button type="button" class="btn btn-danger" onclick="onMessage('${memVO.name}')">送出預約</button>
			</c:if>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>
</form>
<!-- 預約單結束 -->						

<%@ include file="page/footerWithoutSidebar.file" %>
</body>

<script>
    
    var MyPoint = "/ResServer/SSY/<%= com_no %>";
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
		};

		webSocket.onmessage = function(event) {
	        var jsonObj = JSON.parse(event.data);
	        var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
	        var action = jsonObj.action;
	        
	        if(action == "changeSchedule"){
	        	var thisDate = document.getElementById(jsonObj.thisDate);
	        	var toDate = document.getElementById(jsonObj.toDate);
	        	$(thisDate).children('a').show();
	        	$(thisDate).attr("style","");
	        	$(toDate).attr("style","background-color:#D9D9D9;cursor:not-allowed;");
	        	$(toDate).children('a').hide();
	        }else if(action == "deleteSchedule"){
	        	var thisDate = document.getElementById(jsonObj.thisDate);
	        	$(thisDate).children('a').show();
	        	$(thisDate).attr("style","");
	        }else if(action == "addSchedule"){
	        	var thisDate = document.getElementById(jsonObj.thisDate);
	        	$(thisDate).attr("style","background-color:#D9D9D9;cursor:not-allowed;");
	        	$(thisDate).children('a').hide();
	        }else if(action == "onRes"){
		        var resDate = document.getElementById(jsonObj.thisDate.replace(/-/g,""));
		        if(resDate != null){
			        $(resDate).children('a').hide();
			        $(resDate).attr("style","background-color:#D9D9D9;cursor:not-allowed;");
	        
		        }
			}
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
	}
	
	
	
	function onMessage(name) {
		
		var serv_date = document.getElementById('serv_date').value;
		var memName = name;
		var jsonObj = {"thisDate" : $('#serv_date').val(),
						"name" : memName,
						"action" : "onRes"};
		webSocket.send(JSON.stringify(jsonObj));
		
		$('#resForm').submit();
	}

	
	function disconnect () {
		webSocket.close();

	}

	
	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
    
	$(document).ready(function() {
		$('[type=radio]:first').attr("checked","checked");
	});
</script>
</html>