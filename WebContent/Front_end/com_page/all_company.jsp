<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
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

	ServService servSvc = new ServService();
	List<ServVO> servList = servSvc.getAllAvg();
	pageContext.setAttribute("servList", servList);
	
	DecimalFormat df = new DecimalFormat("#,##0.0"); 
	pageContext.setAttribute("df", df);
%>
</head>
<body>
	<%@ include file="page/before.file"%>
	<br>
	<br>

	<div class="container">
		<div class="row">
			<!--圖===========================================================================-->
			<div class="col-xs-12 col-md-12">

				<c:forEach var="comVO" items="${comList}">

					<div class="col-xs-12 col-sm-3">
						<ul class="com_box">
							<li class="list-unstyled"><a
								href="company_page.jsp?com_no=${comVO.com_no}"
								class="thumbnail thumbnail-service mod-shadow img-label"> <img
									class="works_image img-thumbnail"
									src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${comVO.com_no}">
									${comVO.name} 
									<c:forEach var="servVO" items="${servList}">
										<c:if test="${comVO.com_no==servVO.com_no}">
											<span class="fa fa-star text-warning">${df.format(servVO.score/servVO.times)}</span>
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
	<br>
	<br>



	<%@ include file="page/after.file"%>
</body>
</html>