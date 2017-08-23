<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/Back_end/adm/page/backHeader.file"%>
<jsp:useBean id="selectByStype" scope="request" type="java.util.Set" />

<title>服務資料</title>
<br><br>
<div id="content">

<table class="table table-striped">
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
		<th>修改狀態</th>
		<th>修改狀態</th>
	</tr>
	<c:forEach var="servVO" items="${selectByStype}" >
	<tr>
		
		
			<td>${servVO.serv_no}</td>
			<td>${servVO.stype_no}</td>
			<td>${servVO.com_no}</td>
			<td>${servVO.deposit}</td>
			<td>${servVO.price}</td>
			<td>${servVO.title}</td>
			<td>${servVO.content}</td>
			<td>${servVO.times}</td>
			<td>${servVO.score}</td>
			<td>${servVO.status}</td>
		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/serv/serv.do">
			    <input type="submit" class="btn btn-info" value="下架">
			    <input type="hidden" name="servVO" value="${servVO.serv_no}">
			    <input type="hidden" name="action"  value="chStatus"></FORM>
			</td>
	</tr>
</c:forEach>
</table>
</div>
<%@ include file="/Back_end/pages/backFooter.file"%>