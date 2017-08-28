
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.adm.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%
Object admVO = session.getAttribute("admVO");     
%>
<%@ include file="/Back_end/adm/page/backHeader.file"%>
<style type="text/css">
td  {font-size:20px;
	font-weight:900;
	
	}
</style>
  <div id="sidebar">

        <div class="sidebar-inner">
  
            <ul id="sideNav" class="nav nav-pills nav-stacked">
				 <c:if test="${aut.contains(\"02\")}">
                <li><a href="<%=request.getContextPath()%>/Back_end/mem/select_member.jsp"><i class="en-users"></i>一般會員管理 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"03\")}">
				<li><a href="<%=request.getContextPath() %>/Back_end/com/listAllCom.jsp"><i class="en-users"></i>廠商管理 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"01\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/adm/listAllAdm.jsp" class='active'><i class="en-users "> </i>管理者設定 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"08\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/advertising/ad.jsp"><i class="im-table2"></i>廣告刊登管理 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"07\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/place/placeManagement.jsp"><i class="br-location"></i>景點管理 </a></li>
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
<title>管理員個人資料</title>

<div id="content">

	<div class="text-center well" >
	<h2 style="font-weight:900">個人資料</h3>
</div>
<table class="table table-striped"  >

	<tr>
		<td>管理員編號</td>
		<td>${admVO.adm_no}</td>
	</tr>
	<tr>
		<td>管理員帳號</td>
		<td>${admVO.id}</td>
	</tr>
	<tr>
		<td>管理員姓名</td>
		<td>${admVO.name}</td>
	</tr>
	<tr>
		<td>管理員職位</td>
		<td>${admVO.job}</td>
	</tr>
	<tr>
		<td>管理員狀態</td>
		<td>${admVO.status}</td>
	</tr>
</table>

<input type="button" class="btn btn-info" value="修改密碼" onclick="location.href='<%=request.getContextPath()%>/Back_end/adm/updatePwd.jsp'" >
	
</div>

<%@ include file="/Back_end/pages/backFooter.file"%>