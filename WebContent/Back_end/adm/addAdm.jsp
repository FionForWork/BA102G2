<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adm.model.*"%>
<%
AdmVO admVO = (AdmVO) request.getAttribute("admVO");
%>
<%@ include file="/Back_end/adm/page/backHeader.file"%>
<br><br><br>
<div id="content">
<h3>員工資料新增:</h3>
<%--錯誤處理 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	</font>
</c:if>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/adm/adm.do" name="form1">
<table class="table table-striped" >

	<tr>
		<td>管理員編號:</td>
		<td><input type="email" name="id" 
			value="<%= (admVO==null)? "123@1234.com" : admVO.getId()%>" /></td>
	</tr>
	



	<tr>
		<td>管理員名字:<font color='red'>${errorMsgs.get("loc")}</font></td>
		<td><input type="TEXT" name="name" 
			value="<%= (admVO==null)? "MANAGER" : admVO.getName()%>" /></td>
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
		<td>
		<select name="status" style="width: 163px;">
			　<option value="停權">停權</option>
			　<option value="正常">正常</option>

		</select>
		
		</td>
	</tr>

</table>
<br>

<input type="hidden" name="action" value="insert">
<input type="submit" class="btn btn-info" value="送出新增"></FORM>
</div>
<%@ include file="/Back_end/pages/backFooter.file"%>