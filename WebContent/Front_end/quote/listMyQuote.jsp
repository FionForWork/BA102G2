<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.quote.model.*" %>
<%
	QuoteService quoteService = new QuoteService();
	List<QuoteVO> list = quoteService.getAllQuote("0006");
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="rfqService" class="com.rfq.model.RFQService"/>
<jsp:useBean id="memService" class="com.mem.model.MemService"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<%@ include file="page/header.file" %>
<c:if test="${not empty errorMsgs}">
	<font color='red'>報價失敗:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<div class="alert alert-danger alert-dismissable fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Danger!</strong> ${message}
			</div>
		</c:forEach>
	</ul>
	</font>
</c:if>
<table class="table table-striped"  >
	<tr>
		<th>顧客</th>
		<th>日期</th>
		<th>金額</th>
		<th>服務內容</th>
		<th>修改</th>
	</tr>
	<c:forEach var="quoteVO" items="${list}">
	<form onsubmit="return false" method="post" action="<%= request.getContextPath() %>/quote/quote.do">
		<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
		<input type="hidden" name="action" value="updateQuote">
		<input type="hidden" name="quo_no" value="${quoteVO.quo_no}">
		<tr>
			<td>${memService.getOneMem(rfqService.getMemFromQuote(quoteVO.quo_no).mem_no).name}</td>
			<td>
				<fmt:formatDate value="${quoteVO.quo_date}" dateStyle="full" timeStyle="short" type="date"/>
				<br>
				<fmt:formatDate value="${quoteVO.quo_date}" timeStyle="short" type="time"/>
			</td>
			
			<td class="edit">
				<p>${quoteVO.price}</p>
				<input type='text' name='price' class='text form-control' value="${quoteVO.price}" style="display:none" >
			</td>
			<td>${quoteVO.content}</td>
			<td><button class="btn btn-danger" onclick="change(this)">修改</button></td>
		</tr>
	</form>
	</c:forEach>
</table>
<%@ include file="page/footer.file" %>

<script type="text/javascript">
function change(obj){
	var btn=$(obj).html();
	if(btn=='修改'){
		$(obj).parent('td').parent('tr').children('td').children('input').show();
		$(obj).parent('td').parent('tr').children('.edit').children('p').hide();
		$(obj).html('保存');
		$(obj).attr("class","btn btn-info");
	}else if( btn=='保存'){
		$(obj).html('修改');
		$('form').attr("onsubmit","");
	}
}
</script>

</body>
</html>