<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/Back_end/adm/page/backHeader.file"%>
<div id="sidebar">
        <div class="sidebar-inner">
            <ul id="sideNav" class="nav nav-pills nav-stacked">
               <c:if test="${aut.contains(\"02\")}">
               <li><a href="<%=request.getContextPath()%>/Back_end/mem/select_member.jsp"><i class="en-users"></i>一般會員管理 </a></li>
				</c:if>
				
				<c:if test="${aut.contains(\"03\")}">
				<li><a href="<%= request.getContextPath() %>/Back_end/com/listAllCom.jsp" class='active'><i class="en-users"></i>廠商管理 </a></li>
				</c:if>
				
				<c:if test="${aut.contains(\"01\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/adm/listAllAdm.jsp" ><i class="en-users"> </i>管理者設定 </a></li>
				</c:if>
				
				<c:if test="${aut.contains(\"08\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/advertising/ad.jsp"><i class="im-table2"></i>廣告刊登管理 </a></li>
				</c:if>
				
				<c:if test="${aut.contains(\"07\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/place/placeManagement.jsp"><i class="br-location"></i>景點管理 </a></li>
				</c:if>
				
				<c:if test="${aut.contains(\"04\")}">
				<li><a href="#"><i class="im-newspaper"></i>熱門資訊管理</a></li>
				</c:if>
				
				<c:if test="${aut.contains(\"06\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/mall/productPreview.jsp"><i class="fa-shopping-cart"></i>商城管理</a></li>
				</c:if>
				
				<c:if test="${aut.contains(\"04\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/tradition/Traditionall.jsp"><i class="im-list2"></i>婚禮習俗管理</a></li>
				</c:if>
				
				<c:if test="${aut.contains(\"05\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/problem/Problemall.jsp"><i class="im-question"></i>常見問題管理</a></li>
				</c:if>
				
				<c:if test="${aut.contains(\"09\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/report/Report.jsp"><i class="br-warning"></i>檢舉管理</a></li>
				</c:if>
            </ul>
        </div>
    </div>
<jsp:useBean id="selectByCom" scope="request" type="java.util.Set" />

<title>服務資料</title>
<br><br>
<div id="content">
<a href='<%=request.getContextPath()%>/Back_end/serv/select_Serv.jsp'>返回搜尋</a>
<table class="table table-striped">
	<tr>
		<th>服務編號</th>
		<th>服務型態</th>
		<th>廠商編號</th>
		<th>訂金</th>
		<th>價錢</th>
		<th>服務標題</th>
		<th>服務介紹</th>
		<th>被購買次數</th>
		<th>服務評價</th>
		<th>修改狀態</th>
		<th>修改狀態</th>
	</tr>
	<c:forEach var="servVO" items="${selectByCom}" >
	<tr>
		
		
			<td>${servVO.serv_no}</td>
			<td>${servVO.stype_no}</td>
			<td>${servVO.com_no}</td>
			<td>${servVO.deposit}</td>
			<td>${servVO.price}</td>
			<td>${servVO.title}</td>
			<td>${servVO.content}</td>
			<td>${servVO.times}</td>
			<td>${servVO.score}</td>
			<td>${servVO.status}</td>
		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/serv/serv.do">
			    <input type="submit" class="btn btn-info" value="下架">
			    <input type="hidden" name="servVO" value="${servVO.serv_no}">
			    <input type="hidden" name="action"  value="chStatus"></FORM>
			</td>
	</tr>
</c:forEach>
</table>
</div>
<%@ include file="/Back_end/pages/backFooter.file"%>