
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.serv.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%

ServVO servVO = (ServVO) request.getAttribute("servVO");
%>
<%@ include file="/Back_end/adm/page/backHeader.file"%>
<div id="sidebar">

        <div class="sidebar-inner">
  
            <ul id="sideNav" class="nav nav-pills nav-stacked">
				 <c:if test="${aut.contains(\"02\")}">
                <li><a href="<%=request.getContextPath()%>/Back_end/mem/select_member.jsp" ><i class="en-users"></i>一般會員管理 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"03\")}">
				<li><a href="<%=request.getContextPath() %>/Back_end/com/listAllCom.jsp" class='active'><i class="en-users"></i>廠商管理 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"01\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/adm/listAllAdm.jsp" ><i class="en-users "> </i>管理者設定 </a></li>
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

<div id="content">

<input type="button" class="btn btn-info" value="返回搜尋" onclick="location.href='<%=request.getContextPath()%>/Back_end/serv/select_Serv.jsp'" >
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/serv/serv.do" >
		
<table class="table table-striped">
	<tr>

		<th>服務編號</th>
		<th>服務型態</th>
		<th>廠商編號</th>
		<th>訂金</th>
		<th>價錢</th>
		<th>服務標題</th>
		<th>服務介紹</th>
		<th>使用次數</th>
		<th>分數</th>
		<th>狀態</th>
		<th>修改狀態</th>
		<th>修改狀態</th>
		
	</tr>
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
				<select style="width:150px;" name="status">
				<option value="正常">正常
				<option value="下架">下架
				</select>
		</td>
		<td>

			     <input type="submit" class="btn btn-info" value="修改狀態">
			 <input type="hidden" name="locs"value="/<%= request.getServletPath() %>" >
			     <input type="hidden" name="serv_no"value="${servVO.serv_no}" >
			     <input type="hidden" name="action"	value="updateStatus">
			</td>
		
	</tr></table></FORM></div>

<%@ include file="/Back_end/pages/backFooter.file"%>