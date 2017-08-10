<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.works.model.*"%>
<%@ page import="com.com.model.*"%>
<%@ page import="com.serv.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	ComService comSvc = new ComService();
	List<ComVO> comList = comSvc.getAll();
	pageContext.setAttribute("comList", comList);

	String stype_no = request.getParameter("stype_no");
	System.out.println("stype_no=" + stype_no);
	
	ServService servSvc = new ServService();
	List<String> com_noList = servSvc.getComnoByStypeno(stype_no);
	pageContext.setAttribute("com_noList", com_noList);
%>
</head>
<body>
	<%@ include file="page/before.file"%>











	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-md-2"></div>
			<!--圖===========================================================================-->
			<div class="col-xs-12 col-md-10">

							<c:forEach var="com_noList" items="${com_noList}">

								<div class="col-xs-12 col-sm-3">
									<ul class="works_box">
										<li class="list-unstyled"><a
											href="company_page.jsp?com_no=${com_noList}"
											class="thumbnail thumbnail thumbnail-service mod-shadow img-label">
												<img class="works_image img-thumbnail"
												src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${com_noList}">
												<c:forEach var="comVO" items="${comList}">
												<c:if test="${comVO.com_no==com_noList}">
												<h3>${comVO.name}</h3>
												</c:if>
												</c:forEach>
										</a></li>
									</ul>
								</div>
							</c:forEach>

			</div>
			<!--圖===========================================================================-->
		</div>
	</div>

	<%@ include file="page/after.file"%>
</body>
</html>