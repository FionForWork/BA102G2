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
	ReservationService reservationService = new ReservationService();
	List<ReservationVO> list = reservationService.getMemRes("1001");
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
	<c:forEach var="reservationVO" items="${list}">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
						<div class="col-md-6">
							<img src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${reservationVO.com_no}"class="img-circle" style="width:30px;height:30px">
							${comService.getOneCom(reservationVO.com_no).name}
							${dateDF.format(reservationVO.serv_date)}的
							${reservationVO.serv_no.startsWith('7')?"報價預約":"服務預約"}
						</div>
						<div class="col-md-offset-3 col-md-3 text-right">
							訂單狀態 : 
							<i class="${sortingHat.getResIcon(reservationVO.status)}" aria-hidden="true"></i>
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
					<c:if test="${reservationVO.status.equals('0')}">
					<button id="${reservationVO.res_no}" class="btn" style="background-color:#ff5722;color:white" onclick="pay(this)" data-toggle="modal" data-target="#myModal">線上刷卡</button>
					</c:if>
					<c:if test="${reservationVO.status.equals('1')}">
					<button id="${reservationVO.res_no}" class="btn" style="background-color:#ff5722;color:white" onclick="resCompleted(this)">服務完成</button>
					</c:if>
					<c:if test="${reservationVO.status.equals('2')}">
					<div class="col-md-5">
					<section class='rating-widget'>
					  <!-- Rating Stars Box -->
						<div class='rating-stars'>
							<ul id='stars'>
								<li class='star' title='Poor' data-value='1'>
									<i class='fa fa-star fa-fw'></i>
								</li>
								<li class='star' title='Fair' data-value='2'>
									<i class='fa fa-star fa-fw'></i>
								</li>
								<li class='star' title='Good' data-value='3'>
									<i class='fa fa-star fa-fw'></i>
								</li>
								<li class='star' title='Excellent' data-value='4'>
									<i class='fa fa-star fa-fw'></i>
								</li>
								<li class='star' title='WOW!!!' data-value='5'>
									<i class='fa fa-star fa-fw'></i>
								</li>
							</ul>
						</div>
					</section>
					</div>
					<button id="${reservationVO.res_no}" class="btn" style="background-color:#ff5722;color:white" onclick="rating(this)">給予評價</button>
					</c:if>
					<button class="btn" style="background-color:#ff5722;color:white">聊聊</button>
					<i class="fa fa-usd" aria-hidden="true"></i>
						訂單金額 : ${nf.format(reservationVO.price)}
					</h4>
					
			</div>
		</div>
	</c:forEach>
<%@ include file="page/memFooter.file" %>
<form class="form-group" method="post" action="<%= request.getContextPath() %>/reservation/reservation.do">
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">  
<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">進行線上刷卡</h4><hr style="margin:3px">
			</div>
			<div class="modal-body">
				<label >請填入信用卡號</label>
				<div class="row">
					<div class="col-xs-2">
	        			<input class="form-control" type="text" maxlength="4">
	      			</div>
	      			<div class="col-xs-2">
	        			<input class="form-control" type="text" maxlength="4">
	      			</div>
	      			<div class="col-xs-2">
	        			<input class="form-control" type="text" maxlength="4">
	      			</div>
	      			<div class="col-xs-2">
	        			<input class="form-control" type="text" maxlength="4">
	      			</div>
	      		</div><br>
	      		<div class="row">
		      		<label class="col-xs-3">信用卡末3碼</label>
		      		<label class="col-xs-3">到期月分</label>
		      		<label class="col-xs-3">到期年份</label>
	      		</div>
	      		<div class="row">
		      		<div class="col-xs-3">
		        			<input class="form-control" type="text" maxlength="3">
		      		</div>
		      		<div class="col-xs-3">
						<select class="form-control">
						<% for(int i = 0; i < 12; i++){ %>
						<option><%= i+1 %></option>
						<% } %>
						</select>
		      		</div>
		      		<div class="col-xs-3">
						<select class="form-control">
						<% for(int i = 0; i < 8; i++){ %>
						<option><%= i+17 %></option>
						<% } %>
						</select>
		      		</div>
	      		</div>
			</div>
			<div class="modal-footer"><hr>
				<input type="hidden" name="action" value="pay">
				<input type="hidden" id="res_no" name="res_no" value="">
				<input type="hidden" name="RedirectURL" value="<%=request.getRequestURI()%>">
				<input type="submit"  class="btn btn-default" value="確認刷卡">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>    
	</div>
</div>
</form>
<form id="resCompletedForm" method="post" action="<%= request.getContextPath() %>/reservation/reservation.do">
	<input type="hidden" name="action" value="resCompleted">
	<input type="hidden" id="res_no_completed" name="res_no" value="">
	<input type="hidden" name="RedirectURL" value="<%=request.getRequestURI()%>">
</form>
<form id="ratingForm" method="post" action="<%= request.getContextPath() %>/reservation/reservation.do">
	<input type="hidden" name="action" value="rating">
	<input type="text" id="ratingStar" name="score" value="1">
	<input type="hidden" id="res_no_rating" name="res_no" value="">
	<input type="hidden" name="RedirectURL" value="<%=request.getRequestURI()%>">
</form>

</body>
</html>