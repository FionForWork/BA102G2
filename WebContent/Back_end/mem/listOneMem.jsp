<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%

MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<style type="text/css">
td  {font-size:20px;
	font-weight:900;
	
	}
</style>
<%@ include file="/Back_end/adm/page/backHeader.file"%>
<div id="sidebar">

        <div class="sidebar-inner">
  
            <ul id="sideNav" class="nav nav-pills nav-stacked">
				 <c:if test="${aut.contains(\"02\")}">
                <li><a href="<%=request.getContextPath()%>/Back_end/mem/select_member.jsp" class='active'><i class="en-users"></i>一般會員管理 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"03\")}">
				<li><a href="<%=request.getContextPath() %>/Back_end/com/listAllCom.jsp"><i class="en-users"></i>廠商管理 </a></li>
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
<br><br><br>
<div id="content">
<div class="text-center well" >
	<h2 style="font-weight:900">查看會員</h3>
</div>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" >
	

<div class="col-xs-12 col-sm-2"></div>
<div class="col-xs-12 col-sm-4" style="height:70%"><img src="<%=request.getContextPath()%>/ShowPictureServletDAO?mem_no=${memVO.mem_no}" width="auto" height="58%" /></div>
<div class="col-xs-12 col-sm-4"><table class="table table-striped"  >

	<tr>
		<td>會員編號</td>
		<td>${memVO.mem_no}</td>
	</tr>
	<tr>
		<td>會員帳號</td>
		<td>${memVO.id}</td>
	</tr>
	<tr>
		<td>會員姓名</td>
		<td>${memVO.name}</td>
	</tr>
	<tr>
		<td>會員性別</td>
		<td>${memVO.sex}</td>
	</tr>
	<tr>
		<td>會員電話</td>
		<td>${memVO.phone}</td>
	</tr>
	<tr>
		<td>電子郵件</td>
		<td>${memVO.email}</td>
	</tr>
	<tr>
		<td>會員生日</td>
		<td>${memVO.bday}</td>
	</tr>
	<tr>
		<td>被檢舉次數</td>
		<td>${memVO.report}</td>
	</tr>
	<tr>
		<td>會員狀態</td>
		<td>${memVO.status}</td>
	</tr>
	<tr>
		<td>修改狀態</td>
		<td><select style="width:150px;" name="status">
				<option value="正常">正常
				<option value="停權">停權
				</select>
		</td>
	</tr>
	<tr>
		<td>修改狀態</td>
		<td><input type="submit" class="btn btn-info" value="修改狀態"></td>
	</tr>

				<input type="hidden" name="locs"value="/<%= request.getServletPath() %>" >
			     <input type="hidden" name="mem_no"value="${memVO.mem_no}" >
			     <input type="hidden" name="action"	value="updateStatus">
	
	
</table></FOEM></div>
<div class="col-xs-12 col-sm-2"></div>


<%@ include file="/Back_end/pages/backFooter.file"%>