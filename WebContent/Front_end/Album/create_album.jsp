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
<% session.setAttribute("mem_no","1001"); %>
	<form action="<%= request.getContextPath() %>/album/album.do" method="post" enctype="multipart/form-data">
		<table border='2px'>
			<tr>
				<td>相簿名稱:</td>
				<td> <input type='text' value=' ' name='name'> </td>
			</tr>
			<tr>
				<td>選擇相片</td>
				<td><input type="file" name="uploadPic" class="form-control" id="upload" onchange="preview_images()" multiple></td>
			</tr>
			<tr>
			<td>
			<input type='hidden' name='mem_no' value='1001'>
			<input type='hidden' name='action' value='create_Album'>
			<input type='submit' value='submit' name='submit'>
			</td>
			
			</tr>
		</table>
	</form>
	<jsp:useBean id="albSvc" scope="page" class="com.album.model.AlbumService"></jsp:useBean>
	<table>
		<c:forEach var="albVO" items="${albSvc.all }" >
			<tr>
				<td>${albVO.name }</td>
				<td><img src='<%=request.getContextPath()%>/ShowPictureServletDAO?alb_no=${albVO.alb_no }' width="200"><td>
				<c:out value="${albVO.alb_no }"></c:out>
			</tr>
		
		</c:forEach>	
	</table>
</body>
</html>