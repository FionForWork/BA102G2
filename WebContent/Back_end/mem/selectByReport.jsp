<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/Back_end/adm/page/backHeader.file"%>
<jsp:useBean id="selectByReport" scope="request" type="java.util.Set" />

<title>會員資料</title>
<br><br>
<div id="content">

<table class="table table-striped">
	<tr>
		<th>照片</th>
		<th>會員編號</th>
		<th>會員帳號</th>
		<th>會員姓名</th>
		<th>會員性別</th>
		<th>會員生日</th>
		<th>會員電話</th>
		<th>電子郵件</th>
		<th>銀行帳戶</th>
		<th>被檢舉次數</th>
		<th>狀態</th>
	
	</tr>
	<c:forEach var="memVO" items="${selectByReport}" >
	<tr>
		<td ><img src="<%=request.getContextPath()%>/ShowPictureServletDAO?mem_no=${memVO.mem_no}" width="100" height="120"/></td>
		<td>${memVO.mem_no}</td>
		<td>${memVO.id}</td>
		<td>${memVO.name}</td>
		<td>${memVO.sex}</td>
		<td>${memVO.bday}</td>
		<td>${memVO.phone}</td>
		<td>${memVO.email}</td>
		<td>${memVO.account}</td>
		<td>${memVO.report}</td>
		<td>${memVO.status}</td>
		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do">
			    <input type="submit" value="停權">
			    <input type="hidden" name="memVO" value="${memVO.mem_no}">
			    <input type="hidden" name="action" value="stopStatus"></FORM>
			</td>
	</tr>
</c:forEach>
</table>
</div>
<%@ include file="/Back_end/pages/backFooter.file"%>