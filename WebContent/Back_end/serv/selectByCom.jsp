
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<jsp:useBean id="selectByCom" scope="session" type="java.util.Set" />

<title>服務資料</title>
<br><br>
<div id="content">
<div class="text-center well" >
	<h2 style="font-weight:900">查看服務</h3>
</div>
<input type="button" class="btn btn-info" value="返回搜尋" onclick="location.href='<%=request.getContextPath()%>/Back_end/serv/select_Serv.jsp'" ><table class="table table-striped">
<br><br>
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
		<th>狀態</th>
		<th>修改狀態</th>
	</tr>
	
	<%@ include file="page3.file" %> 
	
	<c:forEach var="servVO" items="${selectByCom}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/serv/serv.do">
			<tr>
			<td>${servVO.serv_no}</td>
			<td><c:if test="${servVO.stype_no==\"0001\"}">0001:拍婚紗</c:if>
			<c:if test="${servVO.stype_no==\"0002\"}">0002:婚攝婚錄</c:if>
			<c:if test="${servVO.stype_no==\"0003\"}">0003:新娘秘書</c:if>
			
			</td>
			<td>${servVO.com_no}</td>
			<td>${servVO.deposit}</td>
			<td>${servVO.price}</td>
			<td>${servVO.title}</td>
			<td>${servVO.content}</td>
			<td>${servVO.times}</td>
			<td>${servVO.score}</td>
			<td>${servVO.status}</td>
			<td><select style="width:150px;" name="status">
				<c:if test="${servVO.status==\"下架\"}">
					<option selected = "true" value="正常">正常
					<option value="下架">下架
				</select>
				</c:if>
				<c:if test="${servVO.status==\"正常\"}">
					<option value="正常">正常
					<option selected = "true" value="下架">下架
				</c:if>
		</td>
			<td>
			
			  
			    <input type="hidden" name="locs" value="/<%= request.getServletPath() %>?whichPage=<%=whichPage%>">
			    <input type="hidden" name="serv_no" value="${servVO.serv_no}">
			    <input type="submit" class="btn btn-info " value="修改狀態">
			    <input type="hidden" name="action" value="updateStatus">
			 
			</td>
	</tr></FORM>
</c:forEach>
</table>
<%@ include file="page2.file" %>
</div>

<%@ include file="/Back_end/pages/backFooter.file"%>