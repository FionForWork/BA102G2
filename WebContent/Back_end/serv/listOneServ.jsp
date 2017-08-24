<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.serv.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%

ServVO servVO = (ServVO) request.getAttribute("servVO");
%>
<%@ include file="/Back_end/adm/page/backHeader.file"%>

<br><br><br>
<div id="content">


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/serv/serv.do" >
		
<table class="table table-striped">
	<tr>

		<th>服務編號</th>
		<th>服務型態</th>
		<th>廠商編號</th>
		<th>訂金</th>
		<th>價錢</th>
		<th>服務標題</th>
		<th>服務介紹</th>
		<th>使用次數</th>
		<th>分數</th>
		<th>狀態</th>
		<th>修改狀態</th>
		
	</tr>
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
		<td>
				<select style="width:150px;" name="status">
				<option value="正常">正常
				<option value="停權">停權
				</select>
		</td>
		<td>

			     <input type="submit" class="btn btn-info" value="修改狀態">

			     <input type="hidden" name="serv_no"value="${servVO.serv_no}" >
			     <input type="hidden" name="action"	value="updateStatus">
			</td>
		
	</tr></table></FORM></div>
<%@ include file="/Back_end/pages/backFooter.file"%>