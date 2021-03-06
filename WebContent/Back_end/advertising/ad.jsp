<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.advertising.model.*"%>
<%@ page import="com.com.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="Short Icon" href="<%=request.getContextPath()%>/Front_end/Resource/img/ring_64.ico">
<%
		

	AdvertisingService advertisingSvc = new AdvertisingService();
	List<AdvertisingVO> advertisingList = advertisingSvc.getAll();
	pageContext.setAttribute("advertisingList", advertisingList);
	
	ComService comSvc = new ComService();
	List<ComVO> comList = comSvc.getAll();
	pageContext.setAttribute("comList", comList);
	
	DecimalFormat df = new DecimalFormat("#,##0"); 
	pageContext.setAttribute("df", df);
	
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>廣告刊登管理</title>
</head>
<body>
	<%@ include file="page/header.file"%>
	<div class="text-center well" >
	<h2 style="font-weight:900">廣告資料一覽</h2>
	</div>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	
	

	
	

	<div class="tabbable" id="tabs">
		<ul class="nav nav-tabs nav-justified">
			<li class="active"><a href="#all" data-toggle="tab">所有廣告</a></li>
			<li><a href="#not_audited" data-toggle="tab" onclick="changetable(0);">未審核廣告</a></li>
			<li><a href="#audited" data-toggle="tab" onclick="changetable(1);">已審核廣告</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="all">
				<table id="table" class="table table-hover">
					<thead>
						<tr>
							<th>廠商名稱</th>
							<th>廣告標題</th>
							<th>刊登日期</th>
							<th>刊登天數</th>
							<th>狀態</th>
						</tr>
					</thead>
					<%@ include file="page/page1.file"%>
					<tbody>
							<c:forEach var="advertisingVO" items="${advertisingList}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr>
									<c:forEach var="comVO" items="${comList}">
										<c:if test="${advertisingVO.com_no==comVO.com_no}">
											<td>${comVO.name}</td>
										</c:if>
									</c:forEach>
									<td>${advertisingVO.title}</td>
									<fmt:formatDate value="${advertisingVO.startDay}"
										var="startDayFormat" pattern="yyyy-MM-dd" />
									<td>${startDayFormat}</td>
									
									<td>${df.format((advertisingVO.endDay.time-advertisingVO.startDay.time)/(1000*60*60*24))}</td>
									<td id="5${advertisingVO.adv_no}"><c:choose>
											<c:when test="${advertisingVO.status=='0'}">
												<span style="color: black">未審核</span>
											</c:when>
											<c:when test="${advertisingVO.status=='1'}">
												<span style="color: green">通過</span>
											</c:when>
											<c:otherwise>
												<span style="color: red">未通過</span>
											</c:otherwise>
										</c:choose></td>
									<td>
											<c:choose>
												<c:when test="${advertisingVO.status=='1' || advertisingVO.status=='2'}">
													<button class="btn btn-info" data-toggle="collapse" data-target="#0${advertisingVO.adv_no}" disabled="disabled">審核廣告</button>	
												</c:when>
												<c:otherwise>
													<button class="btn btn-info" data-toggle="collapse" data-target="#0${advertisingVO.adv_no}">審核廣告</button>
												</c:otherwise>
											</c:choose>
									</td>	
								</tr>
								
								<tr>
									<td colspan="6">
										<div class="row collapse" id="0${advertisingVO.adv_no}">
										<div class="col-xs-12 col-sm-4">
										<a data-lightbox="lightbox" href="<%=request.getContextPath()%>/ShowPictureServletDAO?adv_no=${advertisingVO.adv_no}">
											<img style="width:50%" src="<%=request.getContextPath()%>/ShowPictureServletDAO?adv_no=${advertisingVO.adv_no}">
										</a>
										</div>
										<div class="col-xs-12 col-sm-4">${advertisingVO.text}</div>
										<div class="col-xs-12 col-sm-4">
												<form>
												<input type="hidden" name="adv_no" value="${advertisingVO.adv_no}">
												<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
												<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
												<input type="hidden" name="action" value="approved">
												<c:choose>
												<c:when test="${advertisingVO.status=='1'}">
													<input type="button" id="button" class="btn btn-info col-md-3" value="通過" disabled="disabled" >	
												</c:when>
												<c:otherwise>
													<input type="button" id="button" class="btn btn-info col-md-3" value="通過" onclick="allApproved(this);">
												
												</c:otherwise>
												</c:choose>			
												</form>
				
												<form>
												<input type="hidden" name="adv_no" value="${advertisingVO.adv_no}">
												<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
												<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">					
												<input type="hidden" name="action" value="disapproved">
												<c:choose>
												<c:when test="${advertisingVO.status=='1'}">
													<input type="button" class="btn btn-danger col-md-3" id="button" value="未通過" disabled="disabled">	
												</c:when>
												<c:otherwise>
													<input type="button" class="btn btn-danger col-md-3" id="button" value="未通過" onclick="allDisapproved(this);">
												</c:otherwise>
												</c:choose>				
												</form>
											</div>
										</div>
									</td>
								</tr>
										
							</c:forEach>
							<%@ include file="page/page2.file"%>
					</tbody>
				</table>
			
			</div>
				<div class="tab-pane" id="not_audited">
				
				</div>
				
				
				<div class="tab-pane" id="audited">
				
				</div>
		</div>
	</div>
			
			

	
	<%@ include file="page/footer.file"%>
</body>
<script type="text/javascript">


function allApproved(btn){
	
	var id = $(btn).siblings("input[name='adv_no']").val();

	$.ajax({
		url : "<%=request.getContextPath()%>/advertising/advertising.do",
		data : {	
			action : "approved",
			adv_no : $(btn).siblings("input[name='adv_no']").val(),		
		},
		type : 'POST',
		error : function() {
			alert('Ajax request 發生錯誤');
		},
		success : function() {
			$("#5"+id).children().html("通過").css("color","green");
			$("#5"+id).next().children().click().attr("disabled","disabled");
		}
	});	
}

function allDisapproved(btn){
	
	var id = $(btn).siblings("input[name='adv_no']").val();

	$.ajax({
		url : "<%=request.getContextPath()%>/advertising/advertising.do",
		data : {
			action : "disapproved",
			adv_no : $(btn).siblings("input[name='adv_no']").val(),		
		},
		type : 'POST',
		error : function() {
			alert('Ajax request 發生錯誤');
		},
		success : function() {
			$("#5"+id).children().html("未通過").css("color","red");
			$("#5"+id).next().children().click().attr("disabled","disabled");
		}
	});	
}

function approved(btn){
	
	var id = $(btn).siblings("input[name='adv_no']").val();

	$.ajax({
		url : "<%=request.getContextPath()%>/advertising/advertising.do",
		data : {	
			action : "approved",
			adv_no : $(btn).siblings("input[name='adv_no']").val(),		
		},
		type : 'POST',
		error : function() {
			alert('Ajax request 發生錯誤');
		},
		success : function() {
			$("#"+id).children().html("通過").css("color","green");
			$("#"+id).next().children().click().attr("disabled","disabled");
		}
	});	
}

function disapproved(btn){
	
	var id = $(btn).siblings("input[name='adv_no']").val();

	$.ajax({
		url : "<%=request.getContextPath()%>/advertising/advertising.do",
		data : {
			action : "disapproved",
			adv_no : $(btn).siblings("input[name='adv_no']").val(),		
		},
		type : 'POST',
		error : function() {
			alert('Ajax request 發生錯誤');
		},
		success : function() {
			$("#"+id).children().html("未通過").css("color","red");
			$("#"+id).next().children().click().attr("disabled","disabled");
		}
	});	
}

function changetable(x) {
	if(x=='0') {
		$('#not_audited').load("table.jsp #table",{"status":"0"});		
	} else {
		$('#audited').load("table.jsp #table",{"status":"1"});	
	}
}
</script>
</html>