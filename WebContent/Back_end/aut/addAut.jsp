<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.aut.model.*"%>
    
<%
AutVO autVO = (AutVO) request.getAttribute("autVO");
%>
<%@ include file="/Back_end/adm/page/backHeader.file"%>
<script src="<%=request.getContextPath()%>/Front_end/Resource/js/jquery-3.1.1.min.js" type="text/javascript"></script>
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
			 <input TYPE="checkbox"　name="id" id="id" VALUE="01">員工資料管理　　　
			 <input TYPE="checkbox" name="id" id="id" VALUE="02">會員資料管理　　　
			 <input TYPE="checkbox" name="id" id="id" VALUE="03">廠商資料管理　　　
			 <input TYPE="checkbox" name="id" id="id" VALUE="04">其他資料管理<BR><BR>
			 <input TYPE="checkbox" name="id" id="id" VALUE="05">客服資料管理　　　
			 <input TYPE="checkbox" name="id" id="id" VALUE="06">商城資料管理　　　
			 <input TYPE="checkbox" name="id" id="id" VALUE="07">景點資料管理　　　
			 <input TYPE="checkbox" name="id" id="id" VALUE="08">廣告資料管理<BR><BR>
			 <input TYPE="checkbox" name="id" id="id"  VALUE="09">檢舉資料管理　　　<BR><BR><BR><BR>
			 <input TYPE="button" class="btn btn-info " VALUE="員工" id="adm">　　　　　　　　
			 <input TYPE="button"class="btn btn-info " VALUE="經理" id="manager">　　　　　　　　
　　　　　　　　
		</td>
	</tr>
<script>
$(document).ready(function(){
	$("#manager").click(function() {
		$("input[name=id][value=02]").attr("checked",true);
		$("input[name=id][value=03]").attr("checked",true);
		$("input[name=id][value=04]").attr("checked",true);
		$("input[name=id][value=05]").attr("checked",true);
		$("input[name=id][value=06]").attr("checked",true);
		$("input[name=id][value=07]").attr("checked",true);
		$("input[name=id][value=08]").attr("checked",true);
		$("input[name=id][value=09]").attr("checked",true);
		});
$("#adm").click(function() {
	$("input[name=id][value=02]").attr("checked",true);
	$("input[name=id][value=03]").attr("checked",true);
	$("input[name=id][value=04]").attr("checked",true);
	$("input[name=id][value=05]").attr("checked",true);
	$("input[name=id][value=06]").attr("checked",true);
	$("input[name=id][value=07]").attr("checked",true);
	$("input[name=id][value=09]").attr("checked",true);
	});
});
</script>	
</table>
<br>
<input type="hidden"  name="action" value="insert">
<input type="submit" class="btn btn-info " value="送出新增"></FORM>
<%@ include file="/Back_end/pages/backFooter.file"%>
