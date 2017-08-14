<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adm.model.*"%>

<%
	AdmVO admVO = (AdmVO) request.getAttribute("admVO"); 

%>
<%@ include file="/Back_end/pages/backHeader.file"%>
<title>管理員資料修改 - updateadminput.jsp</title>
<div id="content">



		<h3>管理員資料修改 - update_adm_input.jsp</h3>
				<a href="listAllAdm.jsp">回首頁</a>

<h3>資料修改:</h3>
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
			　<option value="停權">停權</option>
			　<option value="正常">正常</option>

		</select></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="id" value="${admVO.id}">
<input type="hidden" name="name" value="${admVO.name}">
<input type="hidden" name="pwd" value="${admVO.pwd}">
<input type="hidden" name="action" value="update">
<input type="hidden" name="adm_no" value="${admVO.adm_no}">
<input type="submit" value="送出修改"></FORM>
</div>
<%@ include file="/Back_end/pages/backFooter.file"%>
