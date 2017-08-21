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
<title>Insert title here</title>
</head>
<body>
	<%@ include file="page/before.file"%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	<%System.out.println(request.getAttribute("active")); %>
	
	<%-- 	<%System.out.println(request.getAttribute("active")); %> --%>
<!-- 	<script type="text/javascript"> -->
<%-- 		if('<%=request.getAttribute("active")%>'=='not_audited') { --%>
<%-- 				$('#<%=request.getAttribute("active")%>').addClass("active"); --%>
<%-- 		}else if ('<%=request.getAttribute("active")%>'=='audited') { --%>
<%-- 			$('#<%=request.getAttribute("active")%>').addClass("active"); --%>
<%-- 		}--%>
<!-- 	</script> -->
	

	<div class="tabbable" id="tabs">
		<ul class="nav nav-tabs nav-justified">
			<li class="active"><a href="#all" data-toggle="tab">所有廣告</a></li>
			<li><a href="#not_audited" data-toggle="tab">未審核廣告</a></li>
			<li><a href="#audited" data-toggle="tab">已審核廣告</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="all">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>廠商名稱</th>
<!-- 							<th>廣告標題</th> -->
							<th>刊登日期</th>
							<th>刊登天數</th>
							<th>狀態</th>
						</tr>
					</thead>
					<%@ include file="page/page1.file"%>
					<tbody>
						<c:if test="${param.status==null}">
							<c:forEach var="advertisingVO" items="${advertisingList}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr>
									<c:forEach var="comVO" items="${comList}">
										<c:if test="${advertisingVO.com_no==comVO.com_no}">
											<td>${comVO.name}</td>
										</c:if>
									</c:forEach>

									<fmt:formatDate value="${advertisingVO.startDay}"
										var="startDayFormat" pattern="yyyy-MM-dd" />
									<td>${startDayFormat}</td>
									
									<td>${df.format((advertisingVO.endDay.time-advertisingVO.startDay.time)/(1000*60*60*24))}</td>
									<td><c:choose>
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
										<form method="post"
											action="<%=request.getContextPath()%>/advertising/advertising.do">
											<input type="hidden" name="adv_no" value="${advertisingVO.adv_no}">
											<input type="hidden" name="action" value="getOne_For_Display">	
											<input type="hidden" name="whichPage" value="<%=whichPage%>">
											<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
											<input type="hidden" name="active" value="all">
											<c:choose>
												<c:when test="${advertisingVO.status=='1'}">
													<input type="submit" class="btn btn-info" value="審核廣告" disabled="disabled">		
												</c:when>
												<c:otherwise>
													<input type="submit" class="btn btn-info" value="審核廣告">
												</c:otherwise>
											</c:choose>
										</form>
									</td>
								</tr>
							</c:forEach>
							<%@ include file="page/page2.file"%>
						</c:if>
					</tbody>
				</table>
			</div>

			<div class="tab-pane" id="not_audited">
				<table class="table table-hover">

					<thead>
						<tr>
							<th>廠商名稱</th>
							<th>刊登日期</th>
							<th>刊登天數</th>
							<th>狀態</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="advertisingVO" items="${advertisingList}">
							<c:if test="${advertisingVO.status=='0'}">
								<tr>
									<c:forEach var="comVO" items="${comList}">
										<c:if test="${advertisingVO.com_no==comVO.com_no}">
											<td>${comVO.name}</td>
										</c:if>
									</c:forEach>

									<fmt:formatDate value="${advertisingVO.startDay}"
										var="startDayFormat" pattern="yyyy-MM-dd" />
									<td>${startDayFormat}</td>
									<td>${df.format((advertisingVO.endDay.time-advertisingVO.startDay.time)/(1000*60*60*24))}</td>
									<td><c:choose>
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
										<form method="post"
											action="<%=request.getContextPath()%>/advertising/advertising.do">
											<input type="hidden" name="adv_no" value="${advertisingVO.adv_no}">
											<input type="hidden" name="action" value="getOne_For_Display">
											<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
											<input type="hidden" name="active" value="not_audited">													
											<c:choose>
												<c:when test="${advertisingVO.status=='1'}">
													<input type="submit" class="btn btn-info" value="審核廣告"
														disabled="disabled">
												</c:when>
												<c:otherwise>
													<input type="submit" class="btn btn-info" value="審核廣告">
												</c:otherwise>
											</c:choose>
										</form>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>

				</table>
			</div>

			<div class="tab-pane" id="audited">
				<table class="table table-hover">

					<thead>
						<tr>
							<th>廠商名稱</th>
							<th>刊登日期</th>
							<th>刊登天數</th>
							<th>狀態</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="advertisingVO" items="${advertisingList}">
							<c:if test="${advertisingVO.status!='0'}">
								<tr>
									<c:forEach var="comVO" items="${comList}">
										<c:if test="${advertisingVO.com_no==comVO.com_no}">
											<td>${comVO.name}</td>
										</c:if>
									</c:forEach>

									<fmt:formatDate value="${advertisingVO.startDay}"
										var="startDayFormat" pattern="yyyy-MM-dd" />
									<td>${startDayFormat}</td>
									<fmt:formatDate value="${advertisingVO.endDay}"
										var="endDayFormat" pattern="yyyy-MM-dd" />
									<td>${df.format((advertisingVO.endDay.time-advertisingVO.startDay.time)/(1000*60*60*24))}</td>
									<td><c:choose>
											<c:when test="${advertisingVO.status==0}">
												<span style="color: black">未審核</span>
											</c:when>
											<c:when test="${advertisingVO.status==1}">
												<span style="color: green">通過</span>
											</c:when>
											<c:otherwise>
												<span style="color: red">未通過</span>
											</c:otherwise>
										</c:choose></td>
									<td>
										<form method="post"
											action="<%=request.getContextPath()%>/advertising/advertising.do">
											<input type="hidden" name="adv_no" value="${advertisingVO.adv_no}">
											<input type="hidden" name="action" value="getOne_For_Display">
											<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
											<input type="hidden" name="active" value="audited">
											<c:choose>
												<c:when test="${advertisingVO.status==1}">
													<input type="submit" class="btn btn-info" value="審核廣告" disabled="disabled">
														
												</c:when>
												<c:otherwise>
													<input type="submit" class="btn btn-info" value="審核廣告">
												</c:otherwise>
											</c:choose>
										</form>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>

				</table>
			</div>
		</div>
	</div>






	<%
		if (request.getAttribute("listOneAdContent") != null) {
	%>
	<jsp:include page="listOneAdContent.jsp" />
	<%
		}
	%>
	<div class="col-xs-12 col-sm-1"></div>
	<font color=blue>request.getParameter("whichPage"):</font>
<%=request.getParameter("whichPage")%><br>
<font color=blue>request.getParameter("requestURL"):</font>
<%=request.getParameter("requestURL")%><br>

	<%@ include file="page/after.file"%>
</body>