<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.com.model.*"%>
<%@ page import="java.util.*"%>
<%@ include file="/Front_end/mem/page/not_login_header.file"%>
<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
        async defer>
    </script>
<title>登入錯誤</title>
<br><br><br><br><br><br>

<center> <h1>您的帳號錯誤請登入對的帳號!
<c:choose>
  <c:when test="${login==\"mem\"}">
    <script>
    	$(document).ready(function(){
    	$('#mem').modal('show');
    		
    	});
    </script>
   </c:when>
   <c:when test="${login==\"com\"}">
   		 <script>
    		$(document).ready(function(){
    		$('#com').modal('show');
    		
    		});
   	 	</script>
   </c:when>
   </c:choose></h1></center>

<br><br><br><br><br><br>
<br><br><br><br><br><br>

<br><br><br><br>
<%

%>

<%@ include file="/Front_end/mem/page/register_footer.file" %>