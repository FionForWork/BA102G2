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

<%@ include file="/Back_end/adm/page/backHeader.file"%>
<title>員工資料</title>
<br><br><br>
<div id="content">
<input type="button" class="btn btn-info" value="新增會員" onclick="location.href='<%=request.getContextPath()%>/Back_end/adm/addAdm.jsp'" >  <br><br>
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
		<th>管理員姓名</th>
		<th>管理員職位</th>
		<th>管理員狀態</th>
		<th>修改</th>

	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="admVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${admVO.adm_no}</td>
			<td>${admVO.id}</td>

			<td>${admVO.name}</td>
			<td>${admVO.job}</td>
			<td>${admVO.status}</td>

			<td>
			<c:if test="${admVO.job!=\"老闆\"}">
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adm/adm.do">
			     <input type="submit" class="btn btn-info" value="修改">
			     <input type="hidden" name="adm_no"value="${admVO.adm_no}" >
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</c:if>
			</td>
			
		</tr>
	</c:forEach>
</table>
	<%@ include file="page2.file" %>
<%@ include file="/Back_end/pages/backFooter.file"%>

