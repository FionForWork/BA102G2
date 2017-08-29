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
<title>服務類型</title>
<body>
	<%@ include file="page/header.file"%>
	<br>
	<br>
	
<!-- 	<div class="container"> -->
<!--         <div class="col-md-offset-1"> -->
<!--             <ul class="breadcrumb"> -->
<%--                 <li><a href="<%=request.getContextPath()%>/Front_end/com_page/all_company.jsp" style="color: black;text-decoration:none;">所有廠商</a></li> --%>
<%--             <jsp:useBean id="service_TypeSvc"  scope="page" class="com.service_type.model.Service_TypeService"/> --%>
<!--                 <li> -->
<%--                 <a href="<%=request.getContextPath()%>/Front_end/com_page/select_company.jsp?stype_no=${service_TypeSvc.getOne(param.stype_no).stype_no}" style="color: black;text-decoration:none;">${service_TypeSvc.getOne(param.stype_no).name}</a> --%>
<!--                 </li> -->
<!--             </ul> -->
<!--         </div> -->
<!--     </div> -->


<!--複合查詢	 -->
<!-- <div class="container text-center"> -->
<!-- <div class="form-group row"> -->
<!-- 	<div class="col-md-3"></div> -->
	
	

<%-- <form method="post" action="<%=request.getContextPath()%>/CompanyCompositeQuery.do"> --%>
<!-- 	<div class="col-md-2"> -->
<!-- 		<div class="input-group"> -->
<%-- 			<a class="btn btn-black btn-xs round" href="<%=request.getContextPath()%>/CompanyCompositeQuery.do?action=listAll&sort=dateDesc"  --%>
<!-- 			style="margin-bottom:5px;" name="com_no" value="">新 &gt; 舊</a> -->
<%-- 			<a class="btn btn-black btn-xs round" href="<%=request.getContextPath()%>/CompanyCompositeQuery.do?action=listAll&sort=date"  --%>
<!-- 			style="margin-bottom:5px;" name="com_no" value="">舊 &gt; 新</a> -->
<%-- 			<a class="btn btn-black btn-xs round" href="<%=request.getContextPath()%>/CompanyCompositeQuery.do?action=listAllByScore&sort=score" --%>
<!-- 			style="margin-bottom:5px; "name="orderbyscore"></i>人氣排序:高 &gt; 低</a> -->
<%-- 			<a class="btn btn-black btn-xs round" href="<%=request.getContextPath()%>/CompanyCompositeQuery.do?action=listAllByScore&sort=scoreDesc" --%>
<!-- 			style="margin-bottom:5px; "name="orderbyscore"></i>人氣排序:低 &gt; 高</a> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<div class="col-md-3"> -->
<!-- 		<div class="input-group"> -->
<!-- 			<span class="input-group-addon">服務類型</span> -->
<!-- 			<select class="form-control" name="stype_no"> -->
<!-- 				<option value="">所有類型</option> -->
<%-- 				<option value="0001" ${com_map.get('stype_no')[0].equals('0001')? 'selected' : ''}>拍婚紗</option> --%>
<%-- 				<option value="0002" ${com_map.get('stype_no')[0].equals('0002')? 'selected' : ''}>婚攝/婚錄</option> --%>
<%-- 				<option value="0003" ${com_map.get('stype_no')[0].equals('0003')? 'selected' : ''}>新娘秘書</option> --%>
<!-- 			</select> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<div class="col-md-2"> -->
<!-- 		<div class="input-group"> -->
<!-- 			<span class="input-group-addon">廠商名稱</span> -->
<!-- 			<input type="text" class="form-control" name="name" placeholder="請輸入關鍵字" value=""> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<div class="col-md-2"> -->
<!-- 		<div class="input-group"> -->
<%-- 		<input type="hidden" class="form-control" name="stype_no" value="<%=stype_no%>"> --%>
<%-- 		<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<!-- 		<input type="hidden" class="form-control" name="action" value="listcoms_ByCompositeQuery"> -->
<!-- 		<button class="btn btn-block btn-danger">送出查詢</button> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </form> -->
<!-- </div> -->
<!-- </div> -->
<!--複合查詢 -->




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
													<span class="fa fa-star text-warning">${(servVO.times)==0?'0':df.format(servVO.score/servVO.times)}</span>
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