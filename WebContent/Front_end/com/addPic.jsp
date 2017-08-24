<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.com.model.*"%>
<html>
<head>

<title>廠商塞圖片</title>
</head>
<body>
<jsp:useBean id="comSvc" scope="page" class="com.com.model.ComService" />
 
<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/com/com.do" name="form1"  enctype="multipart/form-data">

       <b>選擇廠商編號:</b></td>
      <td> <select style="width: 223px;" name="com_no">
         <c:forEach var="comVO" items="${comSvc.all}" > 
          <option value="${comVO.com_no}">${comVO.com_no}${comVO.name}
         </c:forEach>   
       </select></td>
<input type="file" name="logo">
<input type="hidden" name="action" value="updatePic">
<input type="submit" class="btn btn-info" value="送出"></FORM>
</FORM>
</body>
</html>