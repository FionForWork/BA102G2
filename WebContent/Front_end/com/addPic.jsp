<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.com.model.*"%>
<%
ComVO comVO = (ComVO) request.getAttribute("comVO");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

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


 <FORM ACTION="<%= request.getContextPath() %>/com/com.do" method=post name="form1" enctype="multipart/form-data">
        
        <tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="com_no" size="45" 
			value="<%= (comVO==null)? "1001" : comVO.getCom_no()%>" /></td>
	</tr>
        <input type="file" name="logo" value="<%= (comVO==null)? "" : comVO.getLogo()%>">
        <br>
        <input type="hidden" name="action" value="updatePic">
        <input type="submit" value="上傳">
  </FORM>
</body>
</html>