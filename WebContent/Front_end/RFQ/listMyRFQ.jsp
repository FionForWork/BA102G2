<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rfq_detail.model.*" %>
<%@ page import="com.quote.model.*" %>
<%@ page import="com.mem.model.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%
	MemVO memVO = (MemVO)session.getAttribute("memVO"); // 假會員
	RFQ_DetailService rfq_detailService = new RFQ_DetailService();
	List<RFQ_DetailVO> list = rfq_detailService.getMyRFQDetail(memVO.getMem_no());
	DateFormat datedf = new SimpleDateFormat("YYYY年M月d日");
	DateFormat timedf = new SimpleDateFormat("ah點");
	pageContext.setAttribute("datedf", datedf);
	pageContext.setAttribute("timedf", timedf);
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="quoteService" class="com.quote.model.QuoteService"/>
<jsp:useBean id="sortingHat" class="com.ssy.tools.SortingHat"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List My RFQ</title>
</head>
<body>
<%@ include file="page/memHeader.file" %>
	<table class="table table-striped">
		<thead>
		<tr>
			<th>服務日期</th>
			<th>服務內容</th>
			<th>查看報價</th>
			<th>需求狀態</th>
		</tr>
		</thead>
		<c:forEach var="rfq_detailVO" items="${list}">
			<tr>
				<td><p>${datedf.format(rfq_detailVO.ser_date)}</p></td>
				<td>
					${rfq_detailVO.location}${sortingHat.getServType(rfq_detailVO.stype_no)}服務<br>
					${rfq_detailVO.content}
				</td>
				<td>
					<a href="<%= request.getContextPath() %>/quote/quote.do?action=listQuote&rfqdetail_no=${rfq_detailVO.rfqdetail_no}&rfqMem_no=${memVO.mem_no}">
						${sortingHat.getQuoteNum(quoteService.getAllQuote(rfq_detailVO.rfqdetail_no).size())}
					</a>
				</td>
				<td>
					<div class="dropdown">
					<c:if test="${rfq_detailVO.status == '1'}">
						<button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown">
						<p>${sortingHat.getRFQStatus(rfq_detailVO.status)}</p>
						<span class="caret"></span></button>
					</c:if>
					<c:if test="${rfq_detailVO.status == '0'}">
						<button class="btn btn-danger dropdown-toggle disabled" type="button" data-toggle="dropdown">
						<p>${sortingHat.getRFQStatus(rfq_detailVO.status)}</p>
						<span class="caret"></span></button>
					</c:if>
						<ul class="dropdown-menu">
							<li><a href="<%= request.getContextPath() %>/rfq/rfq.do?action=updateRFQStatus&status=0&rfqdetail_no=${rfq_detailVO.rfqdetail_no}">關閉</a></li>
						</ul>
					</div> 
				</td>
			</tr>
		</c:forEach>
	</table>
<%@ include file="page/memFooter.file" %>
</body>
</html>