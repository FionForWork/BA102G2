<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.advertising.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ALL AD</title>

<%
	AdvertisingService advertisingSvc = new AdvertisingService();
	List<AdvertisingVO> advertisingList = advertisingSvc.getAll();
	pageContext.setAttribute("advertisingList", advertisingList);
%>

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

				<c:forEach var="advertisingVO" items="${advertisingList}">

					<div class="col-xs-12 col-sm-3">
						<ul>
							<li class="list-unstyled"><h3>
									<span>廣告編號:${advertisingVO.adv_no}</span>
								</h3> <br> <img
								src="<%=request.getContextPath()%>/ShowPictureServletDAO?adv_no=${advertisingVO.adv_no}"><br>
								<span>廠商編號:${advertisingVO.com_no}</span><br> <span>廣告文字資料:${advertisingVO.text}</span><br>
								<span>刊登日期:${advertisingVO.startDay}</span><br> <span>結束日期:${advertisingVO.endDay}</span><br>
								<span>價格:${advertisingVO.price}</span><br> <span>狀態:${(advertisingVO.status==1)?"已審核":"未審核"}</span><br>
								<form method="post"
									action="<%=request.getContextPath()%>/advertising/advertising.do">
									<input type="submit" value="修改"> <input type="hidden"
										name="adv_no" value="${advertisingVO.adv_no}"> <input
										type="hidden" name="action" value="getOne_For_Update">
								</form>
								<form method="post"
									action="<%=request.getContextPath()%>/advertising/advertising.do">
									<input type="submit" value="刪除"> <input type="hidden"
										name="adv_no" value="${advertisingVO.adv_no}"> <input
										type="hidden" name="action" value="delete">
								</form></li>
						</ul>
					</div>

				</c:forEach>
				

</body>
</html>
