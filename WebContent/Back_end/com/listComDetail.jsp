<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.com.model.*" %>
<%@ page import="java.util.*" %>
<%
	ComService comSvc = new ComService();
	ComVO listComDeatil = comSvc.getOneCom("2001");
	pageContext.setAttribute("listComDeatil", listComDeatil);
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-body">
			<img src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${listComDeatil.com_no}">
			${listComDeatil.com_no}
			${listComDeatil.id}
			${listComDeatil.pwd}
			${listComDeatil.name}
			${listComDeatil.loc}
			${listComDeatil.com_desc}
			${listComDeatil.phone}
			${listComDeatil.account}
			${listComDeatil.status}
		</div>
	</div>
</body>
</html>