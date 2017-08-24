<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rfq_detail.model.*" %>
<%@ page import="com.quote.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="Short Icon" href="<%=request.getContextPath()%>/Front_end/Resource/img/ring_64.ico">
<% 
	DateFormat df = new SimpleDateFormat("YYYY年M月d日");
	DateFormat checkdf = new SimpleDateFormat("YYYY-MM-dd");
	pageContext.setAttribute("df", df);
	pageContext.setAttribute("checkdf", checkdf);
	DateFormat minDF = new SimpleDateFormat("YYYY年M月d日 ah點mm分");
	pageContext.setAttribute("minDF", minDF);
%>
<jsp:useBean id="comService" class="com.com.model.ComService"/>
<jsp:useBean id="rfqService" class="com.rfq.model.RFQService"/>
<jsp:useBean id="calService" class="com.calendar.model.CalendarService"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
	function fsubmit(obj) {
	    obj.submit();
	}
	function show(rfqdetail_no,rfqMem_no,sortBy){
		if(sortBy == "dateOrder" && $('#dateOrder').html() == ""){
			$("#dateOrder").load("<%= request.getContextPath() %>/quote/quote.do?rfqdetail_no="+rfqdetail_no+"&rfqMem_no="+rfqMem_no+"&action=listQuote&sort=dateOrder #dateDesc");
		}else if(sortBy == "priceOrder" && $('#priceOrder').html() == ""){
			$("#priceOrder").load("<%= request.getContextPath() %>/quote/quote.do?rfqdetail_no="+rfqdetail_no+"&rfqMem_no="+rfqMem_no+"&action=listQuote&sort=priceOrder #dateDesc");
		}else if(sortBy == "priceDesc" && $('#priceDesc').html() == ""){
			$("#priceDesc").load("<%= request.getContextPath() %>/quote/quote.do?rfqdetail_no="+rfqdetail_no+"&rfqMem_no="+rfqMem_no+"&action=listQuote&sort=priceDesc #dateDesc");
		}
	}
	function showModal(btn){
		console.log($('#comName').val());
		$( "input[name='quo_no']" ).val($(btn).attr("id"));
		$('#modalComName').html("您選擇預約"+$(btn).prev().val()+"的服務內容為");
		$('#modalPrice').html("金額"+$(btn).siblings('.quotePrice').val());
	}
</script>
<%@ include file="page/headerWithoutSidebar.file" %>
<%-- <%@ include file="listQuoteAjaxIndex.jsp" %> --%>
<div class="container">
<div class="col-md-10 col-md-offset-1">
	<br>

	<ul class="nav nav-tabs nav-justified">
		<li class="active"><a data-toggle="tab" href="#dateDesc" class="menua">日期 : 近到遠</a></li>
		<li><a data-toggle="tab" href="#dateOrder" class="menua" onclick="show('${rfqdetail_no}','${rfqMem_no}','dateOrder')">日期 : 遠到近</a></li>
		<li><a data-toggle="tab" href="#priceOrder" class="menua" onclick="show('${rfqdetail_no}','${rfqMem_no}','priceOrder')">金額 : 低到高</a></li>
		<li><a data-toggle="tab" href="#priceDesc" class="menua" onclick="show('${rfqdetail_no}','${rfqMem_no}','priceDesc')">金額 : 高到低</a></li>
	</ul>

<div class="tab-content">
	<div id="dateDesc" class="tab-pane fade in active">
<!--詢價單-->
	<div class="panel panel-default">
				<div class="panel-heading rfq-row">
					<div class="row">
					<br>
						<div class="col-md-2">
							<img src="<%=request.getContextPath()%>/ShowPictureServletDAO?mem_no=${rfqMem_no}" class="mem_img img-circle">
						</div>
						<div class="col-md-8">
							<h3>${df.format(rfqDetailVO.ser_date)}
							-${rfqDetailVO.location}找${servTypeService.getOne(rfqDetailVO.stype_no).name}服務</h3>
							${rfqDetailVO.content}<br><br>
						</div>
					</div>
				</div>
<!--該詢價單的所有報價-->
			<div class="panel-body">
			<c:if test="${list.size() == 0}">
				<div class="text-center">
					<h3 class="text-align">目前尚未有廠商報價唷!</h3>
				</div>
			</c:if>
			<c:forEach var="quoteVO" items="${list}" >
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-2">
						<img src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${quoteVO.com_no}" class="com_img img-circle">
					</div>
					<div class="col-md-6">
						<h3>${comService.getOneCom(quoteVO.com_no).name}</h3>
						<p>於 ${minDF.format(quoteVO.quo_date)} 報價</p>
						<p>${quoteVO.content}</p>
						<h4><span>價格</span>
							<b class="price text-pink" >${quoteVO.price}</b>
						<span class="hidden-xs">元</span>
					</div>
<!--判斷是否自己的報價才能預約、是否預約過、廠商是否還有空-->
					<c:if test="${rfqService.getOneRFQ(rfqDetailVO.rfq_no).mem_no.equals(memVO.mem_no) && memVO != null
					&& rfqDetailVO.status.equals('1') && calService.checkForRes(quoteVO.com_no,checkdf.format(rfqDetailVO.ser_date)) == null}">
					<div class="col-md-2"><br>
<!--馬上預約-->
						<input type="hidden" class="quotePrice" value="${quoteVO.price}">
						<input type="hidden" class="comName" value="${comService.getOneCom(quoteVO.com_no).name}">
						<button id="${quoteVO.quo_no}" type="button" class="btn btn-danger btn-block" data-toggle="modal" data-target="#resModal" onclick="showModal(this)">馬上預約</button>
					</div>
					</c:if>
				</div>
			<hr>
			</c:forEach>
			</div>
			</div>
	</div>
	<div id="dateOrder" class="tab-pane fade"></div>
	<div id="priceOrder" class="tab-pane fade"></div>
	<div id="priceDesc" class="tab-pane fade"></div>
</div>

<!-- Modal -->
<form method="post" action="<%= request.getContextPath() %>/reservation/reservation.do">
	<div class="modal fade" id="resModal" role="dialog">
		<div class="modal-dialog">
<!-- Modal content-->
			<div class="modal-content col-md-offset-1 col-md-10">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" id="modalComName" >您選擇預約的服務內容為</h4>
				</div><hr>
				<div class="modal-body">
					<h4 >服務日期 : ${df.format(rfqDetailVO.ser_date)}</h4><br>
					<h4 >服務內容 : </h4>
					${rfqDetailVO.content}<br>
					<h3 style="float:right;color:#f14195" id="modalPrice">金額 : ${quoteVO.price}</h4><br>
				</div><hr>
				<div class="modal-footer">
					<input type="hidden" name="action" value="reservationFromQuote">
					<input type="hidden" name="quo_no" value="">
					<input type="hidden" name="rfqdetail_no" value="${rfqDetailVO.rfqdetail_no}">
					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					<input type="submit" class="btn btn-danger btn-block" value="馬上預約">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</form>
		</div>
	</div>
<%@ include file="page/footerWithoutSidebar.file" %>
</body>
<script>

</script>
</html>