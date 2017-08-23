<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
    MemService memSvc = new MemService();
    List<MemVO> list = memSvc.getAll();
    pageContext.setAttribute("list",list);
    List aut = (List)session.getAttribute("aut");   
%>

<%@ include file="/Back_end/adm/page/backHeader.file"%>
<title>查看會員</title>
<br><br><br>

<div id="content">


			 

<table class="table table-striped">
	<tr>
		<th>照片</th>
		<th>會員編號</th>
		<th>會員帳號</th>
		<th>會員姓名</th>
		<th>被檢舉次數</th>
		<th>狀態</th>
<th>查看全部</th>

	</tr>
	<%@ include file="/Back_end/mem/page1.file" %> 
	<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td ><img src="<%=request.getContextPath()%>/ShowPictureServletDAO?mem_no=${memVO.mem_no}" height="100" width="100"/></td>
			<td>${memVO.mem_no}</td>
			<td>${memVO.id}</td>
			<td>${memVO.name}</td>
			<td>${memVO.report}</td>
			
		
			<td>
				${memVO.status}
			</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do">
			      <input type="submit" class="btn btn-info" value="查看全部">
			     <input type="hidden" name="mem_no"value="${memVO.mem_no}" >
			     <input type="hidden" name="action"	value="bgetOne_For_Display"></FORM>
			</td>
		</tr>
	</c:forEach>
</table></FORM>
<%@ include file="/Back_end/mem/page2.file"%>
</div>

<%@ include file="/Back_end/pages/backFooter.file"%>

