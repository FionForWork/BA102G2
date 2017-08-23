<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.quote.model.*" %>
<%@ page import="com.com.model.*" %>
<%@ page import="java.text.*" %>
<link rel="Short Icon" href="<%=request.getContextPath()%>/Front_end/Resource/img/ring_64.ico">
<%
	ComVO comVO = (ComVO)session.getAttribute("comVO");
	QuoteService quoteService = new QuoteService();
	List<QuoteVO> list = quoteService.getComQuote(comVO.getCom_no());
	pageContext.setAttribute("list", list);
	DecimalFormat nf = new DecimalFormat("$#,##0"); 
	pageContext.setAttribute("nf", nf);
	DateFormat df = new SimpleDateFormat("YYYY年M月d日");
	pageContext.setAttribute("df", df);
%>
<jsp:useBean id="sortingHat" class="com.ssy.tools.SortingHat"/>
<jsp:useBean id="rfqService" class="com.rfq.model.RFQService"/>
<jsp:useBean id="memService" class="com.mem.model.MemService"/>
<jsp:useBean id="rfq_detailService" class="com.rfq_detail.model.RFQ_DetailService"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<%@ include file="page/header.file" %>
<c:if test="${not empty errorMsgs}">
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<div class="alert alert-danger alert-dismissable fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>修改失敗!</strong> ${message}
			</div>
		</c:forEach>
	</ul>
</c:if>
<table class="table table-striped"  >
	<tr>
		<th>顧客</th>
		<th>服務內容</th>
<!-- 		<th>日期</th> -->
		<th>金額</th>
		<th>我的回覆</th>
		<th>修改</th>
	</tr>
	<c:forEach var="quoteVO" items="${list}">
	<tr  class="tr1">
		<form id="${quoteVO.quo_no}">
			<td>${memService.getOneMem(rfqService.getMemFromQuote(quoteVO.quo_no).mem_no).name}</td>
			<td>
				<c:set var="rfq_detailVO" value="${rfq_detailService.getOneFromQuote(quoteVO.quo_no)}" />
				<h4>${df.format(rfq_detailVO.ser_date)}-找${sortingHat.getServType(rfq_detailVO.stype_no)}服務</h4>
				${rfq_detailVO.content}
			</td>
			<td class="edit">
				<p class="text-right text-pink">${nf.format(quoteVO.price)}</p>
				<input type='text' name='price' class='text form-control' value="${quoteVO.price}" style="display:none" >
			</td>
			<td>${quoteVO.content}</td>
			<td class="td1">
				<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				<input type="hidden" name="action" value="updateQuote">
				<input type="hidden" name="quo_no" value="${quoteVO.quo_no}">
				<input type="button" class="btn btn-danger" onclick="change(this)" value="修改">
			</td>
		</form>
	</tr>
	</c:forEach>
</table>
<%@ include file="page/footer.file" %>

<script type="text/javascript">
function change(obj){
	var btn=$(obj).val();
	var form = $("#"+$(obj).siblings("input[name='quo_no']").val());
	if(btn=='修改'){
		$(obj).parent('td').parent('tr').children('td').children('input').show();
		$(obj).parent('td').parent('tr').children('.edit').children('p').hide();
		$(obj).val('保存');
		$(obj).attr("class","btn btn-info");
	}else if( btn=='保存'){
		$(obj).val('修改').attr("class","btn btn-danger");
		$(obj).parent('td').prev().prev().children('input').hide();
		$(obj).parent('td').parent('tr').children('.edit').children('p').show();
		$('form').attr("onsubmit","");
		$.ajax({
			url : "<%= request.getContextPath() %>/quote/quote.do",
			data : $(form).serialize(),
			type : 'POST',
			error : function() {
				alert('Ajax request 發生錯誤');
			},
			success : function(result) {
				if(result != ""){
					var oldPrice = $(obj).parent('td').parent('tr').children('.edit').children('p').html();
					$(obj).parent('td').prev().prev().children('input').val(oldPrice);
					alert(result);	
				}else{
					var price = $(obj).parent('td').prev().prev().children('input').val();
					$(obj).parent('td').parent('tr').children('.edit').children('p').html(price);
				}
			}
		});
	}
}
</script>

</body>
</html>