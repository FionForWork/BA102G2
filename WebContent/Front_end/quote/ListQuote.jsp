<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rfq_detail.model.*" %>
<%@ page import="com.quote.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
	DateFormat df = new SimpleDateFormat("YYYY年M月d日 ah點");
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
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function fsubmit(obj) {
	    obj.submit();
	}
</script> 
<%@ include file="page/headerWithoutSidebar.file" %>
<div class="container">
<div class="col-md-10 col-md-offset-1">
	<br>
	
<!--排序 -->
	<ul class="nav nav-tabs nav-justified">
		<li><a href="<%= request.getContextPath() %>/quote/quote.do?action=listQuote&sort=dateDesc&rfqMem_no=${rfqMem_no}" class="menua">日期 : 近到遠</a></li>
		<li><a href="<%= request.getContextPath() %>/quote/quote.do?action=listQuote&sort=dateOrder&rfqMem_no=${rfqMem_no}" class="menua">日期 : 遠到近</a></li>
		<li><a href="<%= request.getContextPath() %>/quote/quote.do?action=listQuote&sort=priceOrder&rfqMem_no=${rfqMem_no}" class="menua">金額 : 低到高</a></li>
		<li><a href="<%= request.getContextPath() %>/quote/quote.do?action=listQuote&sort=priceDesc&rfqMem_no=${rfqMem_no}" class="menua">金額 : 高到低</a></li>
	</ul>
	<br>
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
					<c:if test="${rfqService.getOneRFQ(rfqDetailVO.rfq_no).mem_no.equals(memVO.mem_no) 
					&& rfqDetailVO.status.equals('1') && calService.checkForRes(quoteVO.com_no,checkdf.format(rfqDetailVO.ser_date)) == null}">
					<div class="col-md-2">
					<br>
<!--馬上預約-->
						<button type="button" class="btn btn-danger btn-block" data-toggle="modal" data-target="#myModal${quoteVO.quo_no}">馬上預約</button>
						  <!-- Modal -->
						  <form method="post" action="<%= request.getContextPath() %>/reservation/reservation.do">
						<div class="modal fade" id="myModal${quoteVO.quo_no}" role="dialog">
							<div class="modal-dialog">
						<!-- Modal content-->
								<div class="modal-content col-md-offset-1 col-md-10">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title" >您選擇預約 ${comService.getOneCom(quoteVO.com_no).name} 的服務內容為...</h4>
									</div>
									<hr>
									<div class="modal-body">
									<h4 >服務日期 : ${df.format(rfqDetailVO.ser_date)}</h4><br>
									<h4 >服務內容 : </h4>
									${rfqDetailVO.content}<br>
									<h3 style="float:right;color:#f14195">金額 : ${quoteVO.price}</h4><br>
									</div>
									<hr>
									<div class="modal-footer">
										<input type="hidden" name="action" value="reservationFromQuote">
										<input type="hidden" name="price" value="${quoteVO.price}">
										<input type="hidden" name="quo_no" value="${quoteVO.quo_no}">
										<input type="hidden" name="serv_date" value="${rfqDetailVO.ser_date}">
										<input type="hidden" name="com_no" value="${quoteVO.com_no}">
										<input type="hidden" name="stype_no" value="${rfqDetailVO.stype_no}">
										<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
										<input type="submit" class="btn btn-danger btn-block" value="馬上預約">
										<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>
					</form>
					</div>
					</c:if>
				</div>
			<hr>
			</c:forEach>
			</div>
			</div>
		</div>
	</div>
<%@ include file="page/footerWithoutSidebar.file" %>
</body>
</html>