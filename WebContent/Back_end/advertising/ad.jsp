<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.advertising.model.*"%>
<%@ page import="com.com.model.*"%>
<%@ page import="java.util.*"%>
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
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>


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
					<%@ include file="page1.file"%>
					<tbody>
						<c:forEach var="advertisingVO" items="${advertisingList}"
							begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<c:forEach var="comVO" items="${comList}">
									<c:if test="${advertisingVO.com_no==comVO.com_no}">
										<td>${comVO.name}</td>
									</c:if>
								</c:forEach>
								<td>${advertisingVO.startDay}</td>
								<td>${(advertisingVO.endDay.time-advertisingVO.startDay.time)/(1000*60*60*24)}</td>
								<td>${(advertisingVO.status==1)?"已審核":"未審核"}</td>
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
											<c:when test="${advertisingVO.status==1}">
												<input type="submit" class="btn btn-info" value="審核廣告"
													disabled="true">
											</c:when>
											<c:otherwise>
												<input type="submit" class="btn btn-info" value="審核廣告">
											</c:otherwise>
										</c:choose>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<%@ include file="page2.file"%>
				</table>

			</div>
			<div class="col-xs-12 col-sm-1"></div>
		</div>
	</div>
	<%
		if (request.getAttribute("listOneAdContent") != null) {
	%>
	<jsp:include page="listOneAdContent.jsp" />
	<%
		}
	%>
	<font color=blue>request.getServletPath():</font>
	<%=request.getServletPath()%><br>
	<font color=blue>request.getParameter("whichPage"):</font>
	<%=request.getParameter("whichPage")%><br>

</body>
</html>