<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<html>
<head>

<title>會員塞圖片</title>
</head>
<body>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
 
<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/mem/mem.do" name="form1"  enctype="multipart/form-data">

       <b>選擇會員編號:</b></td>
      <td> <select style="width: 223px;" name="mem_no">
         <c:forEach var="memVO" items="${memSvc.all}" > 
          <option value="${memVO.mem_no}">${memVO.mem_no}
         </c:forEach>   
       </select></td>
<input type="file" name="picture">
<input type="hidden" name="action" value="updatePic">
<input type="submit" class="btn btn-info" value="送出"></FORM>
</FORM>
</body>
</html>