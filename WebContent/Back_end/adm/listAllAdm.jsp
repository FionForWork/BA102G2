<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adm.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
   
<%
    AdmService admSvc = new AdmService();
    List<AdmVO> list = admSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<%@ include file="/Back_end/pages/backHeader.file"%>
<title>員工資料</title>
<br><br><br>
<div id="content">
<h3>全員工資料:</h3>
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
		<th>管理員帳號</th>
		<th>管理員密碼</th>
		<th>管理員姓名</th>
		<th>管理員職位</th>
		<th>管理員狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="admVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${admVO.adm_no}</td>
			<td>${admVO.id}</td>
			<td>${admVO.pwd}</td>
			<td>${admVO.name}</td>
			<td>${admVO.job}</td>
			<td>${admVO.status}</td>

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adm/adm.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="adm_no"value="${admVO.adm_no}" >
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adm/adm.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="adm_no" value="${admVO.adm_no}">
			    <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="/Back_end/pages/backFooter.file"%>
<%@ include file="page2.file" %>
</div>
</body>
</html>
