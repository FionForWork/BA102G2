<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reservation.model.*" %>
<%@ page import="com.mem.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
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
	NumberFormat nf = NumberFormat.getInstance();
	pageContext.setAttribute("nf", nf);
%>
<jsp:useBean id="rfq_dateilService" class="com.rfq_detail.model.RFQ_DetailService"/>
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
	<c:forEach var="reservationVO" items="${list}">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
						<div class="col-md-6">
							與${comService.getOneCom(reservationVO.com_no).name}的
							${reservationVO.serv_no.startsWith('7')?"報價預約":"服務預約"}
							<button class="btn btn-info">聊聊</button>
						</div>
						<div class="col-md-offset-3 col-md-3">
							${sortingHat.getResStatus(reservationVO.status)}
						</div>
				</div><hr>
				<div class="row">
					<c:if test="${reservationVO.serv_no.startsWith('7')}">
						<div class="col-md-6" style="border-right:2px solid #d5d5d5">
							<h4>服務內容</h4>
							${rfq_dateilService.getOneFromQuote(reservationVO.serv_no).content}
						</div>
						<div class="col-md-6">
							<h4>廠商回覆</h4>
							${quoteService.getOneQuote(reservationVO.serv_no).content}
						</div>
					</c:if>
					<c:if test="${reservationVO.serv_no.startsWith('0')}">
						<div class="col-md-12">
							<h4>服務內容</h4>
							${servService.getOneServ(reservationVO.serv_no).content}
						</div>

					</c:if>
					</div><hr>
					<h4 class="text-right">
					<i class="fa fa-usd" aria-hidden="true"></i>
						訂單金額 : ${nf.format(reservationVO.price)}
					</h4>
			</div>
		</div>
	</c:forEach>
<%@ include file="page/memFooter.file" %>
</body>
</html>