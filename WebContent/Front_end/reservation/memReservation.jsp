<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reservation.model.*" %>
<%@ page import="com.mem.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% 
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	ReservationService reservationService = new ReservationService();
	List<ReservationVO> list = reservationService.getMemRes(memVO.getMem_no());
	pageContext.setAttribute("list", list);
	DateFormat dateDF = new SimpleDateFormat("YYYY年M月d日");
	pageContext.setAttribute("dateDF", dateDF);
%>
<jsp:useBean id="quoteService" class="com.quote.model.QuoteService" />
<jsp:useBean id="servService" class="com.serv.model.ServService"/>
<jsp:useBean id="comService" class="com.com.model.ComService"/>
<jsp:useBean id="sortingHat" class="com.ssy.tools.SortingHat"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="page/memHeader.file" %>
	<table class="table">
		<thead>
			<tr>
			 	<th>日期</th>
			 	<th>金額</th>
			 	<th>廠商</th>
			 	<th>服務內容</th>
			 	<th>訂單狀態</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="reservationVO" items="${list}">
			<tr>
				<td>${dateDF.format(reservationVO.serv_date)}</td>
				<td>${reservationVO.price}</td>
				<td>${comService.getOneCom(reservationVO.com_no).name}</td>
				<c:if test="${reservationVO.serv_no.startsWith('7')}">
				<td>${quoteService.getOneQuote(reservationVO.serv_no).content}</td>
				</c:if>
				<c:if test="${reservationVO.serv_no.startsWith('0')}">
				<td>${servService.getOneServ(reservationVO.serv_no).content}</td>
				</c:if>
				<td>${sortingHat.getResStatus(reservationVO.status)}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
<%@ include file="page/memFooter.file" %>
</body>
</html>