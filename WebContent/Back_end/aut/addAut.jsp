<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.aut.model.*"%>
<%
AutVO autVO = (AutVO) request.getAttribute("autVO");
%>
<%@ include file="/Back_end/pages/backHeader.file"%>
<br><br><br>
<div id="content">
<h3>新增員工權限:</h3>
<a href="listAllAut.jsp">回首頁</a>
<%--錯誤處理 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/aut/aut.do" name="form1">
<table class="table table-striped">
	<jsp:useBean id="admSvc" scope="page" class="com.adm.model.AdmService" />
	<tr>
		<td>管理員:<font color=red><b>*</b></font></td>
		<td><select size="1" name="adm_no">
			<c:forEach var="admVO" items="${admSvc.all}">
				<option value="${admVO.adm_no}" ${(admVO.adm_no==autVO.adm_no)? 'selected':'' } >${admVO.adm_no}${admVO.name} 職位:${admVO.job}
			</c:forEach>
		</select></td>
	</tr>
	
	
	
	
	<jsp:useBean id="funSvc" scope="page" class="com.fun.model.FunService" />
	<tr>
		<td>權限:<font color=red><b>*</b></font></td>
		<td>
			<select size="1" name="id">
				<c:forEach var="funVO" items="${funSvc.all}">
					<option value="${funVO.fun_no}" ${(AutVO.id==funVO.fun_no)? 'selected':'' } >${funVO.fun_no}${funVO.name}
				</c:forEach>
			</select>
		</td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
<%@ include file="/Back_end/pages/backFooter.file"%>
