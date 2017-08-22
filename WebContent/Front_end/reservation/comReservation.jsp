<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reservation.model.*" %>
<%@ page import="com.com.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="Short Icon" href="<%=request.getContextPath()%>/Front_end/Resource/img/ring_64.ico">
<%
	ComVO comVO = (ComVO)session.getAttribute("comVO");
	ReservationService resService = new ReservationService();
	String status = request.getParameter("status");
	List<ReservationVO> list = null;
	if(status != null){
		if(status.equals("3")){
			list = resService.getComRes(comVO.getCom_no(),status,"4");
		}else{
			list = resService.getComRes(comVO.getCom_no(),status);
		}
	}else{
		list = resService.getComRes(comVO.getCom_no(),"0");
	}
	pageContext.setAttribute("list", list);
	DateFormat dateDF = new SimpleDateFormat("yyyy年M月d日 ahh時");
	pageContext.setAttribute("dateDF", dateDF);
	NumberFormat nf = NumberFormat.getInstance();
	pageContext.setAttribute("nf", nf);
%>
<jsp:useBean id="sortingHat" class="com.ssy.tools.SortingHat"/>
<jsp:useBean id="memService" class="com.mem.model.MemService"/>
<jsp:useBean id="comService" class="com.com.model.ComService"/>
<jsp:useBean id="rfq_dateilService" class="com.rfq_detail.model.RFQ_DetailService"/>
<jsp:useBean id="quoteService" class="com.quote.model.QuoteService" />
<jsp:useBean id="servService" class="com.serv.model.ServService"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
</head>
<body>
<%@ include file="page/comHeader.file" %>
<ul class="nav nav-tabs nav-justified">
	<li class="pointer active"><a id="0" class="menua" onclick="showRes(this,0)" style="color:#f14195">未繳訂金</a></li>
	<li class="pointer"><a class="menua" onclick="showRes(this,1)">訂單確認</a></li>
	<li class="pointer"><a class="menua" onclick="showRes(this,2)">尚未評價</a></li>
	<li class="pointer"><a class="menua" onclick="showRes(this,3)">服務完成</a></li>
	<li class="pointer"><a class="menua" onclick="showRes(this,5)">訂單取消</a></li>
<br>
</ul>
<div id="allRes">
<c:if test="${list.size() == 0 }">
	<h3 class="text-center">目前沒有此狀態訂單資訊!</h3>
</c:if>
	<c:forEach var="reservationVO" items="${list}">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
						<div class="col-md-6">
							<img src="<%=request.getContextPath()%>/ShowPictureServletDAO?mem_no=${reservationVO.mem_no}"class="img-circle" style="width:30px;height:30px">
							${memService.getOneMem(reservationVO.mem_no).name}
							${dateDF.format(reservationVO.serv_date)}的
							${reservationVO.serv_no.startsWith('7')?"報價預約":"服務預約"}
						</div>
						<div class="col-md-offset-3 col-md-3 text-right">
							訂單狀態 : 
							<i style="color:#f14195" class="${sortingHat.getResIcon(reservationVO.status)}" aria-hidden="true"></i>
							${sortingHat.getResStatus(reservationVO.status)}
							<c:if test="${reservationVO.status.equals('3')}">
								${reservationVO.score}分!
							</c:if>
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
					<c:if test="${reservationVO.status.equals('1')}">
					<button id="${reservationVO.res_no}" class="btn" style="background-color:#ff5722;color:white" onclick="resCompleted(this)">服務完成</button>
					</c:if>
					<button class="btn" style="background-color:#ff5722;color:white">與客戶聊聊</button>
					<i class="fa fa-usd" aria-hidden="true"></i>
						訂單金額 : ${nf.format(reservationVO.price)}
					</h4>
					
			</div>
		</div>
	</c:forEach>
</div>
<form id="resCompletedForm" method="post" action="<%= request.getContextPath() %>/reservation/reservation.do">
	<input type="hidden" name="action" value="resCompleted">
	<input type="hidden" id="res_no_completed" name="res_no" value="">
	<input type="hidden" name="RedirectURL" value="<%=request.getRequestURI()%>">
</form>
<%@ include file="page/comFooter.file" %>
</body>
<script>
	function showRes(x,y){
		changeActive(x);
		$('#allRes').load("comReservation.jsp #allRes",{"status":y});
	}
	
	function changeActive(x){
		$(".pointer a").css("color","#818181");
		$(x).css("color","#f14195");
		$(x).parent().attr("class","active pointer focus");
		$(x).parent().siblings().attr("class","pointer");
	}
	
	function resCompleted(y){
		$('#res_no_completed').val($(y).attr("id"));
		$.ajax({
			url : "<%= request.getContextPath() %>/reservation/reservation.do",
			data : $('#resCompletedForm').serialize(),
			type : 'POST',
			error : function() {
				alert('Ajax request 發生錯誤');
			},
			success : function() {
				$('#allRes').load("comReservation.jsp #allRes",{"status":"1"});
			}
		});
		
	}
</script>
</html>