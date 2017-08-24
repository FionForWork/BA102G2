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
</head>
<body>
	<%@ include file="page/header.file"%>
	<%@ include file="/Front_end/Advertising/Advertising_Demo.jsp"%>
	<br>
	<br>

<!--複合查詢-->	
	<div class="container text-center">
<div class="form-group row">
	<div class="col-md-3"></div>
	<div class="col-md-2">
		<div class="input-group">
			<a class="btn btn-black btn-xs round" style="margin-bottom:5px;"></i>新 &gt; 舊</a>
			<a class="btn btn-black btn-xs round" style="margin-bottom:5px;"></i>人氣排序</a>
		</div>
	</div>
	
	

<form method="post" action="<%=request.getContextPath()%>/CompanyCompositeQuery.do">
	<div class="col-md-3">
		<div class="input-group">
			<span class="input-group-addon">服務類型</span>
			<select class="form-control" name="stype_no">
				<option  value="">所有類型</option>
				<option value="0001">拍婚紗</option>
				<option value="0002">婚攝/婚錄</option>
				<option value="0003">新娘秘書</option>
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
		<input type="hidden" class="form-control" name="action" value="listEmps_ByCompositeQuery">
		<button class="btn btn-block btn-danger">送出查詢</button>
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
							<a href="company_page.jsp?com_no=${comVO.com_no}" class="thumbnail thumbnail-service mod-shadow img-label">
								 <img class="works_image img-thumbnail" src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${comVO.com_no}">
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
			<!--圖=========================================================================== -->
		</div>
	</div>
	<br>
	<br>

	<%@ include file="page/footer.file"%>
</body>
</html>