<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.serv.model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%! int servNum = 1; %>
<% 
	ServService servService = new ServService();
	List<ServVO> list = servService.getAll();
	pageContext.setAttribute("list", list);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="page/searchServiceHeader.file" %>
  <script>
  $( function() {
	    $( "#datepicker" ).datepicker({dateFormat: 'yy-m-dd'});
	  } );
  </script>
<!-- 複合查詢 -->
<div class="container text-center">
<form method="post">
<div class="form-group row">
	<div class="col-md-3">
		<div class="input-group">
			<span class="input-group-addon">服務日期</span>
			<input id="datepicker" type="text" class="form-control" name="msg" placeholder="請選擇日期">
		</div>
	</div>
	<div class="col-md-3">
		<div class="input-group">
			<span class="input-group-addon">服務類型</span>
			<select class="form-control" id="sel1">
				<option value="0000">所有類型</option>
				<option value="0001">拍婚紗</option>
				<option value="0002">婚攝/婚錄</option>
				<option value="0003">新娘秘書</option>
			</select>
		</div>
	</div>
	<div class="col-md-2">
		<div class="input-group">
			<span class="input-group-addon">最低價</span>
			<input id="msg" type="text" class="form-control" name="msg" placeholder="請輸入最低價格">
		</div>
	</div>
	<div class="col-md-2">
		<div class="input-group">
			<span class="input-group-addon">最高價</span>
			<input id="msg" type="text" class="form-control" name="msg" placeholder="請輸入最高價格">
		</div>
	</div>
	<div class="col-md-2">
		<div class="input-group">
		<button class="btn btn-block btn-danger">送出查詢</button>
		</div>
	</div>
</div>
</form>
</div>
<br>
<div class="container text-center">
<c:forEach var="servVO" items="${list}">
<%	if(servNum == 1 || servNum % 4 == 0){%>
		<div class="row">
<% } %>
<div  class="col-xs-6 col-sm-6 col-md-3 btn-like-wrapper">
<!-- 	<button class="btn btn-lg sharp btn-like" id="userCollect" data-id="1919" data-type="2"><i id="CollectIcon" class="fa fa-heart-o" aria-hidden="true"></i></button> -->
	<a class="thumbnail thumbnail-service mod-shadow">
	<div class="ratiobox rat_1_115 bg-cover" style="background-image: url('https://cdn.weddingday.com.tw/wedding-image/service/300/7583ad032f82dede7b38599afa5e61c15915d768ebef0.jpg')">
	</div>
	<div class="caption">
		<h4 class="text-ellipsis"><sapn>純宴客【4小時】</sapn></h4>
		<!--在服務主頁才會出現-->
		<p class="text-ellipsis text-muted small">CHOC wedding   橋克攝影工作室</p>
		<div class="text-muted">
			<div class="clearfix">
            ${servVO.content}
<!-- 				<span class="btn service_spec disable text-default bg-light-grey sharp btn-xs" >儀式 </span> -->
				
<!-- 				<span class="btn service_spec text-default bg-light-grey sharp btn-xs" >午宴 <b></b></span> -->
				
<!-- 				<span class="btn service_spec text-default bg-light-grey sharp btn-xs" >晚宴 <b></b></span> -->
				
<!-- 				<span class="btn service_spec text-default bg-light-grey sharp btn-xs" >拍攝時數 <b>4</b></span> -->
				
<!-- 				<span class="btn service_spec text-default bg-light-grey sharp btn-xs" >攝影師數 <b>1</b></span> -->
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="label-price">
			<span class="small">價格</span>
			<b class="price text-pink" >${servVO.price}</b>
			<span class="hidden-xs">元</span>
		</div>
	</div>
	</a>
</div><!--包套item-->

<%	
	if(servNum > 0 && servNum % 4 == 0){%>
		</div>
<% }servNum++; %>

</c:forEach>
</div>
</div>




<%@ include file="page/searchServiceFooter.file" %>
</body>
</html>