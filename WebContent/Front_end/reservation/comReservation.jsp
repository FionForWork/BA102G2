<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reservation.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	ReservationService resService = new ReservationService();
	List<ReservationVO> list = resService.getComRes("2001");
	pageContext.setAttribute("list", list);
	DateFormat df = new SimpleDateFormat("yyyy年M月d日 ahh時");
	pageContext.setAttribute("df", df);
	NumberFormat nf = NumberFormat.getInstance();
	pageContext.setAttribute("nf", nf);
	DecimalFormat def = new DecimalFormat("$#,##0"); 
	pageContext.setAttribute("def", def);
%>
<<jsp:useBean id="sortingHat" class="com.ssy.tools.SortingHat"/>
<jsp:useBean id="memService" class="com.mem.model.MemService"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
<%@ include file="page/comHeader.file" %>
<table class="table table-striped">
	<thead>
		<tr>
			<th>日期</th>
			<th>顧客</th>
			<th>服務內容</th>
			<th>金額</th>
			<th>預約狀態</th>
			<th>查看更多</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="reservationVO" items="${list}">
		<tr>
			<td>${df.format(reservationVO.serv_date)}</td>
			<td>${memService.getOneMem(reservationVO.mem_no).name}</td>
			<td>${sortingHat.getServType(reservationVO.stype_no)}</td>
			<td>${def.format(reservationVO.price)}</td>
			<td>${sortingHat.getResStatus(reservationVO.status)}</td>
			<td>
				<form>
					<input type="hidden" name="res_no" value="${reservationVO.res_no}">
					<input type="submit" class="btn btn-info" value="查看更多">
				</form>				
			</td>
		</tr>
		</c:forEach>
		
</tbody>
</table>
<%@ include file="page/comFooter.file" %>
</body>
</html>