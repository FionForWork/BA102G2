<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.serv.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="Short Icon" href="<%=request.getContextPath()%>/Front_end/Resource/img/ring_64.ico">
<% 
	LocalDate localDate = LocalDate.now();
	pageContext.setAttribute("localDate", localDate);
	if(request.getAttribute("list") == null){
		Map<String,String[]> map = new HashMap<String,String[]>();
		map.put("cal_date", new String[] {localDate.toString()});
		ServService servService = new ServService();
		List<ServVO> list = servService.getAll(map);
		pageContext.setAttribute("list", list);
	}
	DateFormat df = new SimpleDateFormat("yyyy年m月d日");
	pageContext.setAttribute("df", df);
	int servNum = 1;
%>
<jsp:useBean id="comService" class="com.com.model.ComService"/>
<jsp:useBean id="sortingHat" class="com.ssy.tools.SortingHat"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%@ include file="page/searchServiceHeader.file" %>
  <script>
  $( function() {
	    $( "#datepicker" ).datepicker({dateFormat: 'yy-mm-dd'});
	  } );
  </script>
<!-- 複合查詢 -->
<div class="container text-center">
<form method="post" action="<%= request.getContextPath() %>/reservation/reservation.do">
<div class="form-group row">
	<div class="col-md-3">
		<div class="input-group">
			<span class="input-group-addon">服務日期</span>
			<input id="datepicker" type="text" class="form-control" name="cal_date" value="${map == null?localDate.toString():map.get('cal_date')[0]}" placeholder="請選擇日期">
		</div>
	</div>
	<div class="col-md-3">
		<div class="input-group">
			<span class="input-group-addon">服務類型</span>
			<select class="form-control" name="stype_no">
				<option value="0000" ${map.get('stype_no')[0].equals('0000')? 'selected' : ''}>所有類型</option>
				<option value="0001" ${map.get('stype_no')[0].equals('0001')? 'selected' : ''}>拍婚紗</option>
				<option value="0002" ${map.get('stype_no')[0].equals('0002')? 'selected' : ''}>婚攝/婚錄</option>
				<option value="0003" ${map.get('stype_no')[0].equals('0003')? 'selected' : ''}>新娘秘書</option>
			</select>
		</div>
	</div>
	<div class="col-md-2">
		<div class="input-group">
			<span class="input-group-addon">最低價</span>
			<input id="msg" type="text" class="form-control" name="bottomPrice" value="${map.get('bottomPrice')[0].equals('')? '' : map.get('bottomPrice')[0]}" placeholder="請輸入最低價格">
		</div>
	</div>
	<div class="col-md-2">
		<div class="input-group">
			<span class="input-group-addon">最高價</span>
			<input id="msg" type="text" class="form-control" name="topPrice" value="${map.get('topPrice')[0].equals('')? '' : map.get('topPrice')[0]}" placeholder="請輸入最高價格">
		</div>
	</div>
	<div class="col-md-2">
		<div class="input-group">
		<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
		<input type="hidden" name="action" value="searchServiceByCompositeQuery">
		<input type="submit" class="btn btn-block btn-danger" value="送出查詢">
		</div>
	</div>
</div>
</form>
</div>
<br>
<div class="container text-center">
<c:if test="${list.size() == 0}">
<h3>很抱歉，沒有符合您搜尋條件的服務!</h3>
</c:if>
<c:forEach var="servVO" items="${list}">
<%	if(servNum == 1 || servNum % 4 == 0){%>
		<div class="row">
<% } %>
<div  class="col-xs-6 col-sm-6 col-md-3 btn-like-wrapper" data-toggle="modal" data-target="#myModal${servVO.serv_no}">
<!-- 	<button class="btn btn-lg sharp btn-like" id="userCollect" data-id="1919" data-type="2"><i id="CollectIcon" class="fa fa-heart-o" aria-hidden="true"></i></button> -->
	<a class="thumbnail thumbnail-service mod-shadow">
	<div class="ratiobox rat_1_115 bg-cover" style="background-image: url(<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${servVO.com_no})">
	</div>
	<div class="caption">
		<h4 class="text-ellipsis"><sapn>${comService.getOneCom(servVO.com_no).name}</sapn></h4>
		<!--在服務主頁才會出現-->
		<p class="text-ellipsis text-muted middle" style="font-size:15px">${sortingHat.getServType(servVO.stype_no)}</p>
		<div class="text-muted">
			<div class="clearfix " style="font-size:14px;text-align:justify">
            ${servVO.content}
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="label-price">
			<span class="small">價格</span>
			<b class="price text-pink" >${servVO.price}</b>
			<span class="hidden-xs">元</span>
		</div>
		<div class="label-price" style="font-size:12px">
			<span class="small">訂金</span>
			<span class="hidden-xs">${servVO.deposit}元</span>
		</div>
	</div>
	</a>
</div><!--包套item-->

<%	
	if(servNum > 0 && servNum % 4 == 0){%>
		</div>
<% }servNum++; %>

<!-- 預約單 -->
<div class="modal fade" id="myModal${servVO.serv_no}" role="dialog">
<form method="post" action="<%= request.getContextPath() %>/reservation/reservation.do">
	<div class="modal-dialog">  
<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">是否確定預約${map == null?localDate.toString():map.get('cal_date')[0]}的以下服務</h4>
			</div>
			<div class="modal-body row">
				<div  class="col-xs-offset-3 col-xs-6 btn-like-wrapper"">
				<!-- 	<button class="btn btn-lg sharp btn-like" id="userCollect" data-id="1919" data-type="2"><i id="CollectIcon" class="fa fa-heart-o" aria-hidden="true"></i></button> -->
					<a class="thumbnail thumbnail-service mod-shadow">
					<div class="ratiobox rat_1_115 bg-cover" style="background-image: url(<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${servVO.com_no})">
					</div>
					<div class="caption">
						<h4 class="text-ellipsis"><sapn>${comService.getOneCom(servVO.com_no).name}</sapn></h4>
						<!--在服務主頁才會出現-->
						<p class="text-ellipsis text-muted middle" style="font-size:15px">${sortingHat.getServType(servVO.stype_no)}</p>
						<div class="text-muted">
							<div class="clearfix " style="font-size:14px;text-align:justify">
				            ${servVO.content}
							</div>
						</div>
					</div>
					<div class="footer">
						<div class="label-price">
							<span class="small">價格</span>
							<b class="price text-pink" >${servVO.price}</b>
							<span class="hidden-xs">元</span>
						</div>
					</div>
					</a>
				</div><!--包套item-->
			</div>
			<div class="modal-footer btn-group">
				<input type="hidden" name="serv_no" value="${servVO.serv_no}">
				<input type="hidden" name="serv_date" value="${map == null?localDate.toString():map.get('cal_date')[0]}">
				<input type="hidden" name="requestURI" value="<%=request.getRequestURI()%>">
				<input type="hidden" name="action" value="resFromSearchService">
				<input type="submit" class="btn btn-info" value="確認預約">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</form>
</div>

</c:forEach>
<% servNum = 1; %>
</div>
</div>
<%@ include file="page/searchServiceFooter.file" %>
</body>
</html>