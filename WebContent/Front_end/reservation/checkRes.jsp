<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String toLocation = (String)request.getAttribute("toLocation");
	System.out.print(toLocation);
	String URL = "/BA102G2/Front_end/reservation/memReservation.jsp";
	response.setHeader("Refresh", "3;URL="+URL);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="<%=request.getContextPath()%>/Front_end/reservation/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath()%>/Front_end/reservation/js/checked.js"></script>
<style>
	p{
		font-size:18px
	}
</style>
<body>
<%@ include file="page/checkedHeader.file" %>
<div class="container text-center">
	<div class="panel panel-default col-md-6 col-md-offset-3">
		<div class="panel-body">
			<h2>您的服務預約完成囉!</h2>
			<canvas height="200"></canvas>
			<a href=""><p>(<span id="divNum">3</span>秒後即將導入預約管理)</p></a>
			<a href="<%=toLocation%>"><p>(或點擊此處繼續預約其他服務)</p></a>
		</div>
	</div>
</div>
<%@ include file="page/footerWithoutSidebar.file" %>
</body>
</html>