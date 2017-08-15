<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reservation.model.*" %>
<%@ page import="com.mem.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% 
	ReservationService reservationService = new ReservationService();
	String status = request.getParameter("status");
	List<ReservationVO> list = null;
	if(status != null){
		if(status.equals("3")){
			list = reservationService.getMemRes("1001",status,"4");
		}else{
			list = reservationService.getMemRes("1001",status);
		}
	}else{
		list = reservationService.getAllMemRes("1001");
	}
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

<ul class="nav nav-tabs nav-justified">
	<li class="pointer"><a class="menua" onclick="showRes(0)">未繳訂金</a></li>
	<li class="pointer"><a class="menua" onclick="showRes(1)">訂單確認</a></li>
	<li class="pointer"><a class="menua" onclick="showRes(2)">尚未評價</a></li>
	<li class="pointer"><a class="menua" onclick="showRes(3)">服務完成</a></li>
<br>
</ul>
<div id="allRes">
	<c:if test="${list.size() == 0}">
		<h3 class="text-center">目前沒有此狀態訂單資訊!</h3>
	</c:if>
	<c:forEach var="reservationVO" items="${list}">
		<div class="panel panel-default">
			<div class="panel-body" style="margin:15px">
				<div class="row">
						<div class="col-md-6">
							<img src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${reservationVO.com_no}"class="img-circle" style="width:30px;height:30px">
							${comService.getOneCom(reservationVO.com_no).name}
							${dateDF.format(reservationVO.serv_date)}的
							${reservationVO.serv_no.startsWith('7')?"報價預約":"服務預約"}
						</div>
						<div class="col-md-6 text-right">
							訂單狀態 : 
							<i style="color:#f14195;font-weight:500" class="${sortingHat.getResIcon(reservationVO.status)}" aria-hidden="true">
							<c:if test="${reservationVO.status.equals('3')}">
								${reservationVO.score}分!
							</c:if>
							</i>
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
					<button id="${reservationVO.res_no}" class="btn" style="background-color:#ff5722;color:white" onclick="pay(this)" data-toggle="modal" data-target="#myModal">
						刷卡支付訂金 :<i class="fa fa-usd" aria-hidden="true"></i>${nf.format(servService.getOneServ(reservationVO.serv_no).deposit)}
					</button>

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
								<li onclick="clickStars(this)" class='star' title='Poor' data-value='1'>
									<i class='fa fa-star fa-fw'></i>
								</li>
								<li onclick="clickStars(this)" class='star' title='Fair' data-value='2'>
									<i class='fa fa-star fa-fw'></i>
								</li>
								<li onclick="clickStars(this)" class='star' title='Good' data-value='3'>
									<i class='fa fa-star fa-fw'></i>
								</li>
								<li onclick="clickStars(this)" class='star' title='Excellent' data-value='4'>
									<i class='fa fa-star fa-fw'></i>
								</li>
								<li onclick="clickStars(this)" class='star' title='WOW!!!' data-value='5'>
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
</div>
<%@ include file="page/memFooter.file" %>
<form id="payForm" class="form-group" method="post" action="<%= request.getContextPath() %>/reservation/reservation.do">
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">  
<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">進行線上刷卡</h4><hr style="margin:3px">
			</div>
			<div class="modal-body" style="padding-top:0px">
				<div calss="row">
					<img style="width:50%" src="<%=request.getContextPath()%>/Front_end/reservation/img/card.jpg">
				</div>
				<div calss="row col-md-12">
					<label class="col-md-3">請填入信用卡號</label>
					<div  class="col-md-9" id="showResult"></div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
	        			<input class="form-control" id="cardNum1" type="text" maxlength="4" onblur="checkCardNum(this)">
	      			</div>
	      			<div class="col-md-2">
	        			<input class="form-control" id="cardNum2" type="text" maxlength="4" onblur="checkCardNum(this)">
	      			</div>
	      			<div class="col-md-2">
	        			<input class="form-control" id="cardNum3" type="text" maxlength="4" onblur="checkCardNum(this)">
	      			</div>
	      			<div class="col-md-2">
	        			<input class="form-control" id="cardNum4" type="text" maxlength="4" onblur="checkCardNum(this)">
	      			</div>
	      		</div><br>
	      		<div class="row">
		      		<label class="col-md-3">信用卡末3碼</label>
		      		<label class="col-md-3">到期月分</label>
		      		<label class="col-md-3">到期年份</label>
	      		</div>
	      		<div class="row">
		      		<div class="col-xs-3">
		        			<input class="form-control" id="threeNum" type="text" maxlength="3" onblur="checkNum(this)">
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
				<div class="col-md-6" id="showPanel"></div>
				<div class="col-md-6">
					<input type="hidden" name="action" value="pay">
					<input type="hidden" id="res_no" name="res_no" value="">
					<input type="hidden" name="RedirectURL" value="<%=request.getRequestURI()%>">
					<input type="button"  class="btn btn-danger" value="確認刷卡" onclick="checkForm()">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
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
<script>
function checkCardNum(y) {
	$.ajax({
		url : "<%= request.getContextPath() %>/reservation/reservation.do",
		data : {
			action : "checkNum",
			type : "cardNum",
			cardNum : $(y).val()
		},
		type : 'POST',
		dataType: "JSON",
		error : function(xhr) {
			alert('Ajax request 發生錯誤');
		},
		success : function(result) {
			if(result.r == "卡號正確"){
				var checkResult = $("<div style='color:green'>").text(result.r);
				$('#showResult').html(checkResult);
			}else{
				var checkResult = $("<div style='color:red'>").text(result.r);
				$('#showResult').html(checkResult);
			}
		}
	});
}

function checkNum(y) {
	$.ajax({
		url : "<%= request.getContextPath() %>/reservation/reservation.do",
		data : {
			action : "checkNum",
			type : "threeNum",
			threeNum : $(y).val()
		},
		type : 'POST',
		dataType: "JSON",
		error : function(xhr) {
			alert('Ajax request 發生錯誤');
		},
		success : function(result) {
			if(result.r == "卡號正確"){
				var checkResult = $("<div style='color:green'>").text(result.r);
				$('#showResult').html(checkResult);
			}else{
				var checkResult = $("<div style='color:red'>").text(result.r);
				$('#showResult').html(checkResult);
			}
		}
	});
}

function checkForm() {
	$.ajax({
		url : "<%= request.getContextPath() %>/reservation/reservation.do",
		data : {
			action : "checkForm",
			cardNum1 : $('#cardNum1').val(),
			cardNum2 : $('#cardNum2').val(),
			cardNum3 : $('#cardNum3').val(),
			cardNum4 : $('#cardNum4').val(),
			threeNum : $('#threeNum').val()
		},
		type : 'POST',
		dataType: "JSON",
		error : function(xhr) {
			alert('Ajax request 發生錯誤');
		},
		success : function(result) {
			if(result.r == "卡號正確"){
				$('#payForm').submit();
			}else{
				var checkResult = $("<div style='color:red'>").text(result.r);
				$('#showPanel').html(checkResult);
			}
		}
	});
}

function showRes(y){
	$('#allRes').load("memReservation.jsp #allRes",{"status":y});
}

function clickStars(y){
	var onStar = parseInt($(y).data('value'), 10); // The star currently selected
    var stars = $(y).parent().children('li.star');
    
    for (i = 0; i < stars.length; i++) {
      $(stars[i]).removeClass('selected');
    }
    
    for (i = 0; i < onStar; i++) {
      $(stars[i]).addClass('selected');
    }
    
    // JUST RESPONSE (Not needed)
    var ratingValue = parseInt($('#stars li.selected').last().data('value'), 10);
    $('#ratingStar').val(ratingValue);
}

function overStars(y){
	var onStar = parseInt($(y).data('value'), 10); // The star currently mouse on
    // Now highlight all the stars that's not after the current hovered star
    $(y).parent().children('li.star').each(function(e){
      if (e < onStar) {
        $(y).addClass('hover');
      }
      else {
        $(y).removeClass('hover');
      }
    });
}

</script>
</html>