<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/Back_end/login/loginbackHeader.file"%>


    </script>
<title>登入錯誤</title>
<br><br><br><br><br><br>

<br><br><br>
<center> <h1>您的帳號錯誤請先登出後登入對的帳號!</h1>
<form name="logouts" ACTION="<%= request.getContextPath() %>/adm/adm.do" method=post >
                    <input type="hidden"  name="action" value="logout"/>
                     <input type="submit" class="btn btn-info" value="登出">     </form>
<br><br><br>
<br><br><br><br><br><br>

<br><br><br><br>

<%@ include file="/Back_end/pages/backFooter.file"%>