<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adm.model.*"%>

<%
	AdmVO admVO = (AdmVO) request.getAttribute("admVO"); 

%>
<%@ include file="/Back_end/adm/page/backHeader.file"%>
<title>管理員資料修改 - updateadminput.jsp</title>
<div id="content">


<br>
	
<input type="button" class="btn btn-info" value="回首頁" onclick="location.href='<%=request.getContextPath()%>/Back_end/adm/listAllAdm.jsp'" >  <br><br>
	<h3>管理員資料修改 :</h3>
		


<%-- 錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/adm/adm.do" name="form1">



<table class="table table-striped" >
	<tr>
		<td>管理員編號:<font color=red><b>*</b></font></td>
		<td>${admVO.adm_no}</td>
	</tr>
	<tr>
		<td>管理員帳號:</td>
		<td>${admVO.id}</td>
	</tr>
	<tr>
		<td>管理員姓名:</td>
		<td>${admVO.name}</td>
	</tr>
	<tr>
		<td>管理員職位:</td>
		<td><select name="job" style="width: 163px;">
			　<option  value="員工">員工</option>
			　<option value="經理">經理</option>
			 <option value="工讀生">工讀生</option>
			 <option  value="組長">組長</option>
		</select></td>
	</tr>
	<tr>
		<td>管理員狀態:</td>
		<td><select name="status" style="width: 163px;">
			<c:if test="${admVO.status==\"停權\"}">
			<option value="正常">正常</option>
			<option value="停權" SELECTED>停權</option>
			</c:if>
			<c:if test="${admVO.status==\"正常\"}">
			<option value="正常" SELECTED>正常</option>
			<option value="停權" >停權</option>
			</c:if>
		</select></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="id" value="${admVO.id}">
<input type="hidden" name="name" value="${admVO.name}">
<input type="hidden" name="pwd" value="${admVO.pwd}">
<input type="hidden" name="action" value="update">
<input type="hidden" name="adm_no" value="${admVO.adm_no}">
<input type="submit" class="btn btn-info" value="送出修改"></FORM>
</div>
<%@ include file="/Back_end/pages/backFooter.file"%>
