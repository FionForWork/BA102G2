<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.aut.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
    AutService autSvc = new AutService();
    List<AutVO> list = autSvc.getAll();
    pageContext.setAttribute("list",list);
    
%>

<%@ include file="/Back_end/adm/page/backHeader.file"%>
<div id="sidebar">
        <div class="sidebar-inner">
            <ul id="sideNav" class="nav nav-pills nav-stacked">
               <c:if test="${aut.contains(\"02\")}">
               <li><a href="<%=request.getContextPath()%>/Back_end/mem/select_member.jsp"><i class="en-users"></i>一般會員管理 </a></li>
				</c:if>
				
				<c:if test="${aut.contains(\"03\")}">
				<li><a href="<%= request.getContextPath() %>/Back_end/com/listAllCom.jsp" ><i class="en-users"></i>廠商管理 </a></li>
				</c:if>
				
				<c:if test="${aut.contains(\"01\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/adm/listAllAdm.jsp" class='active'><i class="en-users"> </i>管理者設定 </a></li>
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
<br><br><br>
<div id="content">

<a href="addAut.jsp">新增</a>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table class="table table-striped">
	<tr>
		<th>管理員編號</th>
		<th>權限編號</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="autVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
		
			<td>${autVO.adm_no}</td>
			<td>${autVO.id}</td>

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/aut/aut.do">
			    <input type="submit" class="btn btn-info " value="刪除">
			    <input type="hidden" name="adm_no" value="${autVO.adm_no}">
			    <input type="hidden" name="id" value="${autVO.id}">
			    <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</div>
</body>
</html>
