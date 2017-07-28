<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.works.model.*"%>
<%@ page import="com.com.model.*"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
// 	WorksService worksSvc = new WorksService();
// 	List<WorksVO> worksList = worksSvc.getAll();
// 	session.setAttribute("worksList", worksList);
	
	ComService comSvc = new ComService();
	List<ComVO> comList = comSvc.getAll();
	session.setAttribute("comList", comList);
	List<String> comno = new ArrayList<String>();
%>
</head>
<body>
	<%@ include file="before.file"%>


	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-md-2">
				<ul class="list-group">
					<li class="list-group-item"><a href="#" class="menua">婚秘</a></li>
					<li class="list-group-item"><a href="#" class="menua">婚紗</a></li>
					<li class="list-group-item"><a href="#" class="menua">婚攝</a></li>
				</ul>
			</div>
			<!--圖===========================================================================-->
			<div class="col-xs-12 col-md-10">
			
				<c:forEach var="comVO" items="${comList}">
				<div class="col-xs-12 col-sm-3">
						<ul class="album_box">
							<li class="list-unstyled"><a href="company_page.jsp?com_no=${comVO.com_no}"
								class="thumbnail thumbnail thumbnail-service mod-shadow img-label">
									<img class="album_image img-thumbnail" src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${comVO.com_no}">
									<h3>${comVO.name}</h3>
							</a></li>
						</ul>
					</div>			
				</c:forEach>	

			</div>
			<!--圖===========================================================================-->
		</div>
	</div>




	<%@ include file="after.file"%>
</body>
</html>