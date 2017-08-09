<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.calendar.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.temporal.TemporalAdjusters" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int dayOfWeek = 0;int week = 1;int flag = 0;
	LocalDate localDate = (LocalDate)request.getAttribute("localDate");
	// localDate = LocalDate.of(localDate.getYear(),7,1);
	// 本月最後一天的日期(LocalDate)
	LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
	// 本月共有幾天
	int dayNum = lastDayOfMonth.getDayOfMonth();
	// 本月第一天是星期幾
	int firstDayOfWeek = localDate.withDayOfMonth(1).getDayOfWeek().getValue();
	Timestamp t = new Timestamp(System.currentTimeMillis());
	CalendarService calerdarService = new CalendarService();
	List<CalendarVO> list = calerdarService.getMonthCalendar(localDate.getYear(), localDate.getMonthValue(), dayNum, "2001");
	pageContext.setAttribute("month", localDate.getMonthValue());
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>

<body>
<%@ include file="page/headerWithoutSidebar.file" %>

<div class="container">
<div class="text-center col-md-offset-1 col-md-10">
<table class="table table-bordered ui-widget-head" >
	<thead>
		<tr>
			<th colspan="7" style="background-color:#5BFFEB" class="text-center">
				<form id="changeCalendar" method="post" action="<%= request.getContextPath() %>/calendar/calendar.do">
					<h3>
					<select name="year" onchange="change()">
						<% for(int i = 0; i < 5; i++){ %>
						<% if(i+2017 == localDate.getYear()){ %>
						<option value="<%= i+2017 %>" selected><%= i+2017 %></option>
						<% }else{ %>
						<option value="<%= i+2017 %>"><%= i+2017 %></option>
						<% }} %>
					</select>年
					 
					<select name="month" onchange="change()">
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
					<td></td>
					<% dayOfWeek++;%>
					<% week =2 ; %>
				<% } %>
				<% i += dayOfWeek-1; %>
<!-- 開始這個月的日期 -->
			<% }else{ %>
				<% dayOfWeek++; %>
				<% pageContext.setAttribute("date", i-firstDayOfWeek+2); %>
				<td class="ui-widget-head calendar cal-td" id="<%= localDate.getYear() %>-<%= localDate.getMonthValue() %>-${date}" data-toggle="modal" data-target="#myModal" onclick="add(this)">

				<p class="day"><%= i-firstDayOfWeek+2 %></p>
				<br>
				<c:forEach var="calendarVO" items="${list}">
					<c:if test="${calendarVO.cal_date.getDate() == date}">
						<c:if test="${calendarVO.status == '0'}">
							<div id="${calendarVO.cal_no}" class="draggable ui-widget-content" style="background-color:#BDE7FF;cursor:all-scroll" onmouseenter="show(this)" onmouseleave="hide(this)">
								<button type="button" class="close" display="none" onclick="deleteSchedule(this)" style="display:none">&times;</button>
								<p style="margin-top:3px">${calendarVO.content}</p>
								<% flag = 1; %>
							</div>
						</c:if>
						<c:if test="${calendarVO.status != '0'}">
							<div id="${calendarVO.cal_no}" class="res-content" style="background-color:pink;">
								<p style="margin-top:3px"><a style="color:black;">${calendarVO.content}</a></p>
								<% flag = 1; %>
							</div>
						</c:if>
					</c:if>
				</c:forEach>
				</td>
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
<form method="post" action="<%= request.getContextPath() %>/calendar/calendar.do">
	<div id="myModal" class="modal fade" role="dialog">
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
					<input type="text" id="datepicker" class="form-control" name="cal_date" value="">
					<br>
					<label>活動內容 :</label>
					<textarea rows="3" class="form-control" name="content"></textarea>
				</div>
				<hr>
				<div class="modal-footer">
					<input type="hidden" name="action" value="addSchedule">
					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					<input type="submit" class="btn btn-info" value="新增活動">
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

<%@ include file="page/footerWithoutSidebar.file" %>
</body>
</html>