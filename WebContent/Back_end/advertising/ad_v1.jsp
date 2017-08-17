<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.advertising.model.*"%>
<%@ page import="com.com.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	
	
	<ul class="nav nav-tabs nav-justified"> 
        <li><a href="<%= request.getContextPath() %>/Back_end/advertising/ad_v1.jsp" class="menua">所有廣告</a></li>
        <li><a href="<%= request.getContextPath() %>/Back_end/advertising/ad_v1.jsp?status=0" class="menua">未審核廣告</a></li>
        <li><a href="<%= request.getContextPath() %>/Back_end/advertising/ad_v1.jsp?status=1" class="menua">已審核廣告</a></li>
        <br><br>
    </ul>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-1"></div>
			<div class="col-xs-12 col-sm-10">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>廠商名稱</th>
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
								
								<fmt:formatDate value="${advertisingVO.startDay}" var="startDayFormat" pattern="yyyy-MM-dd"/>
								<td>${startDayFormat}</td>
								<td>${(advertisingVO.endDay.time-advertisingVO.startDay.time)/(1000*60*60*24)}</td>
								<td>
									<c:choose>
									<c:when test="${advertisingVO.status=='0'}">
										<span style="color:black">未審核</span>
									</c:when>
									<c:when test="${advertisingVO.status=='1'}">
										<span style="color:green">通過</span>
									</c:when>
									<c:otherwise>
										<span style="color:red">未通過</span>
									</c:otherwise>
									</c:choose>
								</td>
								<td>
									<form method="post"
										action="<%=request.getContextPath()%>/advertising/advertising.do">
										<input type="hidden" name="adv_no"
											value="${advertisingVO.adv_no}"> <input type="hidden"
											name="action" value="getOne_For_Display"> <input
											type="hidden" name="whichPage" value="<%=whichPage%>">
										<input type="hidden" name="requestURL"
											value="<%=request.getServletPath()%>">
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
						</c:forEach>
					<%@ include file="page/page2.file"%>
						</c:if>
					</tbody>
					
					<tbody>
						<c:if test="${param.status=='0'}">
						<c:forEach var="advertisingVO" items="${advertisingList}">
							<c:if test="${advertisingVO.status==param.status}">
								<tr>
									<c:forEach var="comVO" items="${comList}">
										<c:if test="${advertisingVO.com_no==comVO.com_no}">
											<td>${comVO.name}</td>
										</c:if>
									</c:forEach>
									
									<fmt:formatDate value="${advertisingVO.startDay}" var="startDayFormat" pattern="yyyy-MM-dd"/>
									<td>${startDayFormat}</td>
									<td>${(advertisingVO.endDay.time-advertisingVO.startDay.time)/(1000*60*60*24)}</td>
									<td>
										<c:choose>
										<c:when test="${advertisingVO.status=='0'}">
											<span style="color:black">未審核</span>
										</c:when>
										<c:when test="${advertisingVO.status=='1'}">
											<span style="color:green">通過</span>
										</c:when>
										<c:otherwise>
											<span style="color:red">未通過</span>
										</c:otherwise>
										</c:choose>
									</td>
									<td>
										<form method="post"
											action="<%=request.getContextPath()%>/advertising/advertising.do">
											<input type="hidden" name="adv_no"
												value="${advertisingVO.adv_no}"> <input
												type="hidden" name="action" value="getOne_For_Display">
											<input type="hidden" name="requestURL"
												value="<%=request.getServletPath()%>?status=${advertisingVO.status}"> 
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
						</c:if>
					</tbody>
					
					<tbody>
						<c:if test="${param.status=='1'}">
						<c:forEach var="advertisingVO" items="${advertisingList}">
							<c:if test="${advertisingVO.status>0}">
								<tr>
									<c:forEach var="comVO" items="${comList}">
										<c:if test="${advertisingVO.com_no==comVO.com_no}">
											<td>${comVO.name}</td>
										</c:if>
									</c:forEach>
									
									<fmt:formatDate value="${advertisingVO.startDay}" var="startDayFormat" pattern="yyyy-MM-dd"/>
									<td>${startDayFormat}</td>
									<td>${(advertisingVO.endDay.time-advertisingVO.startDay.time)/(1000*60*60*24)}</td>
									<td>
										<c:choose>
										<c:when test="${advertisingVO.status==0}">
											<span style="color:black">未審核</span>
										</c:when>
										<c:when test="${advertisingVO.status==1}">
											<span style="color:green">通過</span>
										</c:when>
										<c:otherwise>
											<span style="color:red">未通過</span>
										</c:otherwise>
										</c:choose>
									</td>
									<td>
										<form method="post"
											action="<%=request.getContextPath()%>/advertising/advertising.do">
											<input type="hidden" name="adv_no"
												value="${advertisingVO.adv_no}"> <input
												type="hidden" name="action" value="getOne_For_Display">
											<input type="hidden" name="requestURL"
												value="<%=request.getServletPath()%>?status=${advertisingVO.status}"> 
											<c:choose>
												<c:when test="${advertisingVO.status==1}">
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
						</c:if>
					</tbody>
					
				</table>
	<%
		if (request.getAttribute("listOneAdContent") != null) {
	%>
	<jsp:include page="listOneAdContent.jsp" />
	<%
		}
	%>

			</div>
			<div class="col-xs-12 col-sm-1"></div>
		</div>
	</div>
	<font color=blue>request.getServletPath():</font>
	<%=request.getServletPath()%><br>
	<font color=blue>request.getParameter("whichPage"):</font>
	<%=request.getParameter("whichPage")%><br>
	<font color=blue>status:</font><%=request.getParameter("whichPage")%><br>
<%@ include file="page/after.file"%>
</body>
</html>