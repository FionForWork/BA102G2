<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
MemVO memVO = (MemVO) request.getAttribute("memVO");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>




 <FORM ACTION="<%= request.getContextPath() %>/mem/mem.do" method=post name="form1" enctype="multipart/form-data">
        
        <tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="mem_no" size="45" 
			value="<%= (memVO==null)? "1001" : memVO.getMem_no()%>" /></td>
	</tr>
        <input type="file" name="picture" value="<%= (memVO==null)? "" : memVO.getPicture()%>">
        <br>
        <input type="hidden" name="action" value="updatePic">
        <input type="submit" value="上傳">
  </FORM>
</body>
</html>