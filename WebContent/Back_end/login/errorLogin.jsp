<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/Back_end/login/loginbackHeader.file"%>
    </script>
<title>登入錯誤</title>
<br><br><br><br><br><br>
<br><br><br>
<center> <h1>帳號錯誤密碼!</h1><a href="<%=request.getContextPath()%>/Back_end/login/login.jsp">返回</a></center>

<br><br><br>
<br><br><br><br><br><br>

<br><br><br><br>
<form name="logout" ACTION="<%= request.getContextPath() %>/adm/adm.do" method=post >
                    <input type="hidden"  name="action" value="logout"/>
                          </form>

<%@ include file="/Back_end/pages/backFooter.file"%>