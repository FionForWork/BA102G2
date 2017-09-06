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
<title>所有店家</title>
<head>
<link rel="Short Icon" href="<%=request.getContextPath()%>/Front_end/Resource/img/ring_64.ico">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%

	if(request.getAttribute("listCom_ByCompositeQuery")==null) {
		ComService comSvc = new ComService();
		List<ComVO> listCom_ByCompositeQuery = comSvc.getAll();
		pageContext.setAttribute("listCom_ByCompositeQuery", listCom_ByCompositeQuery);		
	}

	ServService servSvc = new ServService();
	List<ServVO> servList = servSvc.getAllAvg();
	pageContext.setAttribute("servList", servList);
	
	DecimalFormat df = new DecimalFormat("#,##0.0"); 
	pageContext.setAttribute("df", df);
%>
<jsp:useBean id="comSvc" class="com.com.model.ComService"/>
</head>
<body>
	<%@ include file="page/header.file"%>
	<br>
	<br>

<!--複合查詢-->	
<div class="container text-center">
<div class="form-group row">
	<div class="col-md-3"></div>	

<form method="post" action="<%=request.getContextPath()%>/CompanyCompositeQuery.do">
	
	<div class="col-md-2"></div>
	<div class="col-md-3">
		<div class="input-group">
			<span class="input-group-addon">服務類型</span>
			<select class="form-control" name="stype_no">
				<option value="">所有類型</option>
				<option value="0001" ${com_map.get('stype_no')[0].equals('0001')? 'selected' : ''}>拍婚紗</option>
				<option value="0002" ${com_map.get('stype_no')[0].equals('0002')? 'selected' : ''}>婚攝/婚錄</option>
				<option value="0003" ${com_map.get('stype_no')[0].equals('0003')? 'selected' : ''}>新娘秘書</option>
			</select>
		</div>
	</div>
	<div class="col-md-2">
		<div class="input-group">
			<span class="input-group-addon">廠商名稱</span>
			<input type="text" class="form-control" name="name" placeholder="請輸入關鍵字" value="">
		</div>
	</div>
	<div class="col-md-2">
		<div class="input-group">
		<input type="hidden" class="form-control" name="action" value="listcoms_ByCompositeQuery">
		<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
		<button class="btn btn-block btn-danger">送出查詢</button>
		</div>
	</div>
	<br>
	<br>
	<br>
	
	<div class="col-md-5" style="text-align:rignt;"></div>
	<div class="col-md-1" style="text-align:rignt;">選擇排序:</div>
	<div class="col-md-5">
		<div class="input-group">
		<c:choose>
		<c:when test="${com_map.get('sort')=='date'}">
			<a class="btn btn-black btn-xs round" href="<%=request.getContextPath()%>/CompanyCompositeQuery.do?action=listAll&sort=dateDesc" 
			style="margin-bottom:5px; "id="sortCom_no">新 &gt; 舊</a>
		</c:when>
		<c:otherwise>
			<a class="btn btn-black btn-xs round" href="<%=request.getContextPath()%>/CompanyCompositeQuery.do?action=listAll&sort=date" 
			style="margin-bottom:5px; "id="sortCom_noDesc">舊 &gt; 新</a>
		</c:otherwise>
		</c:choose>
		
		<c:choose>
		<c:when test="${com_map.get('sort')=='scoreDesc'}">
			<a class="btn btn-black btn-xs round" href="<%=request.getContextPath()%>/CompanyCompositeQuery.do?action=listAll&sort=score"
			style="margin-bottom:5px;" id="sortScore">人氣排序:高 &gt; 低</a>
		</c:when>
		
		<c:otherwise>
			<a class="btn btn-black btn-xs round" href="<%=request.getContextPath()%>/CompanyCompositeQuery.do?action=listAll&sort=scoreDesc"
			style="margin-bottom:5px;" id="sortScoreDesc">人氣排序:低 &gt; 高</a>
		</c:otherwise>
		</c:choose>
		
		</div>
	</div>
</form>
</div>
</div>
<!--複合查詢-->
	

	<div class="container">
		<div class="row">
			<!--圖=========================================================================== -->
			<div class="col-xs-12 col-md-12">

				<c:forEach var="comVO" items="${listCom_ByCompositeQuery}">

					<div class="col-xs-12 col-sm-3">
						<ul class="com_box">
							<li class="list-unstyled">
							<a href="<%=request.getContextPath()%>/Front_end/com_page/company_page.jsp?com_no=${comVO.com_no}" class="thumbnail thumbnail-service mod-shadow img-label">
								 <img class="com_box_image img-thumbnail" src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${comVO.com_no}">
									<div class="com_box_name">${comSvc.getOneCom(comVO.com_no).name}
										<c:forEach var="servVO" items="${servList}">
											<c:if test="${comVO.com_no==servVO.com_no}">
												<span class="fa fa-star text-warning">${(servVO.times)==0?'0':df.format(servVO.score/servVO.times)}</span>
											</c:if>
										</c:forEach>
									</div>						
							</a></li>
						</ul>
					</div>
				</c:forEach>

			</div>
			<!--圖=========================================================================== -->
		</div>
	</div>
	<br>
	<br>

	<%@ include file="page/footer.file"%>
</body>
</html>