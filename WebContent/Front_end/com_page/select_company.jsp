<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.works.model.*"%>
<%@ page import="com.com.model.*"%>
<%@ page import="com.serv.model.*"%>
<%@ page import="com.service_type.model.*"%>
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
	
	ServService servSvc1 = new ServService();
	List<String> com_noList = servSvc1.getComnoByStypeno(stype_no);
	pageContext.setAttribute("com_noList", com_noList);
	
	ServService servSvc2 = new ServService();
	List<ServVO> servList = servSvc2.getAllAvg();
	pageContext.setAttribute("servList", servList);
	
	DecimalFormat df = new DecimalFormat("#,##0.0"); 
	pageContext.setAttribute("df", df);
%>
</head>
<body>
	<%@ include file="page/header.file"%>
	<br>
	<br>
	
	<div class="container">
        <div class="col-md-offset-1">
            <ul class="breadcrumb">
                <li><a href="<%=request.getContextPath()%>/Front_end/com_page/all_company.jsp" style="color: black;text-decoration:none;">所有廠商</a></li>
            <jsp:useBean id="service_TypeSvc"  scope="page" class="com.service_type.model.Service_TypeService"/>
                <li>
                <a href="<%=request.getContextPath()%>/Front_end/com_page/select_company.jsp?stype_no=${service_TypeSvc.getOne(param.stype_no).stype_no}" style="color: black;text-decoration:none;">${service_TypeSvc.getOne(param.stype_no).name}</a>
                </li>
            </ul>
        </div>
    </div>

	<div class="container">
		<div class="row">
			<!--圖===========================================================================-->
			<div class="col-xs-12 col-md-12">

							<c:forEach var="com_noList" items="${com_noList}">

								<div class="col-xs-12 col-sm-3">
									<ul class="com_box">
										<li class="list-unstyled"><a
											href="company_page.jsp?com_no=${com_noList}"
											class="thumbnail thumbnail thumbnail-service mod-shadow img-label">
												<img class="works_image img-thumbnail"
												src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${com_noList}">
												<c:forEach var="comVO" items="${comList}">
												<c:if test="${comVO.com_no==com_noList}">
												${comVO.name}
												</c:if>
												</c:forEach>
												
												<c:forEach var="servVO" items="${servList}">
												<c:if test="${com_noList==servVO.com_no}">
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
	<%@ include file="page/footer.file"%>
</body>
</html>