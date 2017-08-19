<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.mem.model.*"%>

<%

MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<%@ include file="/Back_end/adm/page/backHeader.file"%>

<br><br><br>
<div id="content">


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" >
		
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
		<th>修改狀態</th>
		
	</tr>
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
		<td>
				<select style="width:150px;" name="status">
				<option value="正常">正常
				<option value="停權">停權
				</select>
		</td>
		<td>

			     <input type="submit" value="修改狀態">

			     <input type="hidden" name="mem_no"value="${memVO.mem_no}" >
			     <input type="hidden" name="action"	value="updateStatus">
			</td>
		
	</tr></table></FORM></div>
<%@ include file="/Back_end/pages/backFooter.file"%>