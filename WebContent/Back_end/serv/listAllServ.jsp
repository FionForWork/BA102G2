<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.serv.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
    ServService servSvc = new ServService();
    List<ServVO> list = servSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<%@ include file="/Back_end/adm/page/backHeader.file"%>

<title>所有服務.jsp</title>

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
<br>
<div id="content">
<table class="table table-striped">
	<tr>
		<th>服務編號</th>
		<th>服務型態</th>
		<th>訂金</th>
		<th>價錢</th>
		<th>服務標題</th>
		<th>服務介紹</th>
		<th>被購買次數</th>
		<th>服務評價</th>
		<th>修改狀態</th>
		<th>修改狀態</th>
	</tr>
	
	<%@ include file="page1.file" %> 
	
	<c:forEach var="servVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
		
			<td>${servVO.serv_no}</td>
			<td>${servVO.stype_no}</td>
			<td>${servVO.deposit}</td>
			<td>${servVO.price}</td>
			<td>${servVO.title}</td>
			<td>${servVO.content}</td>
			<td>${servVO.times}</td>
			<td>${servVO.score}</td>
			<td>${servVO.status}</td>
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/serv/serv.do">
			    <input type="submit" value="修改狀態">
			    <input type="hidden" name="serv_no" value="${servVO.serv_no}">
			    <input type="hidden" name="action" value="getOne_For_Update"></FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</div>
</body>
</html>
