<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
	
	<jsp:useBean id="contSvc" scope="page" class="com.content.model.ContentService"></jsp:useBean>
	<% String alb_no = (String)request.getAttribute("alb_no"); %>
	<form action="<%= request.getContextPath() %>/album/album.do" method="post"></form>
	<table>
		<c:forEach var="contVO" items="${contSvc.getAllByAlbNo(alb_no) }" >
		
			<tr>
				<td><img src='/BA102G2/ShowPictureServletDAO?cont_no=${contVO.cont_no }' width="200"><td>
				<td><c:out value="${contVO.cont_no }"></c:out></td>
				<input type="hidden" name="cont_no" value="${contVO.cont_no }">
			</tr>
			
		</c:forEach>
		<tr>
			<td>
			<input type="submit" value="刪除相簿" >
			</td>
		</tr>	
	</table>
</body>
</html>