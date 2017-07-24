<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rfq_detail.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    RFQ_DetailService rfqDetailSvc = new RFQ_DetailService();
    List<RFQ_DetailVO> list = rfqDetailSvc.getAllRFQDetail();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>RFQ.jsp</title>
</head>
<body>
<%@ include file="page/header.file" %>
<div class="container">
<div class="col-md-10 col-md-offset-1">
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有詢價單資料</h3>
		</td>
	</tr>
</table>


<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>詢價明細編號</th>
		<th>詢價編號</th>
		<th>服務類型編號</th>
		<th>服務地點</th>
		<th>服務日期</th>
		<th>標題</th>
		<th>服務內容</th>
		<th>狀態</th>
	</tr>
	<c:forEach var="rfqDetailVO" items="${list}">
		<tr align='center' valign='middle'>
			<td>${rfqDetailVO.rfqdetail_no}</td>
			<td>${rfqDetailVO.rfq_no}</td>
			<td>${rfqDetailVO.stype_no}</td>
			<td>${rfqDetailVO.location}</td>
			<td>${rfqDetailVO.ser_date}</td>
			<td>${rfqDetailVO.title}</td>
			<td>${rfqDetailVO.content}</td>
			<td>${rfqDetailVO.status}</td>
		</tr>
	</c:forEach>
</table>
<table class="table">
    <thead>
      <tr>
        <th>詢價明細編號</th>
		<th>詢價編號</th>
		<th>服務類型編號</th>
		<th>服務地點</th>
		<th>服務日期</th>
		<th>標題</th>
		<th>狀態</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="rfqDetailVO" items="${list}">
		<tr align='center' valign='middle'>
			<td>${rfqDetailVO.rfqdetail_no}</td>
			<td>${rfqDetailVO.rfq_no}</td>
			<td>${rfqDetailVO.stype_no}</td>
			<td>${rfqDetailVO.location}</td>
			<td>${rfqDetailVO.ser_date}</td>
			<td>${rfqDetailVO.title}</td>
			<td>${rfqDetailVO.status}</td>
		</tr>
	</c:forEach>
    </tbody>
  </table>
</div>
</div>
	<div class="container">
	<c:forEach var="rfqDetailVO" items="${list}">
        <div>
            <div class="col-md-10 col-md-offset-1">
                <div class="panel panel-default">
                    <div class="panel-body rfq-row">
                        <div class="row">
                            <div class="col-md-2">
                                <img src="img/LOGO.png" class="mem_img img-circle" 
                                style="width: 80px;height: 80px;display: block;
                                margin-left: auto;margin-right: auto;">
                            </div>
                            <div class="col-md-8">
                                <h3>${rfqDetailVO.ser_date}-${rfqDetailVO.title}</h3>
                                ${rfqDetailVO.content}
                                <a href="#">(完整內容)</a>
                            </div>
                            <div class="col-md-2">
                                <button type="button" class="btn btn-block btn-default">查看內容</button>
                                <button type="button" class="btn btn-block btn-default">提交報價</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </c:forEach>
	</div>
<%@ include file="page/footer.file" %>
</body>
</html>