<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.com.model.*"%>
<%@ page import="java.util.*"%>
<%@ include file="/Front_end/mem/page/not_login_header.file"%>
<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
        async defer>
    </script>

<title>忘記密碼</title>
<br><br><br><br><br><br>

<center> <h1>請輸入帳號</h1>

稍後會有一封信寄到信箱

</center>
<div class="container">
  <div class="row">
    <div class="col-xs-12 col-sm-3"></div>
    <div class="col-xs-12 col-sm-6">
	  
	

    <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/com/com.do" name="form2" enctype="multipart/form-data" ">

				<h1 ><img src="<%= request.getContextPath() %>/Front_end/mem/img/ring_64.png">我是廠商</h1>  <br>
		<div class="form-group">
					<span>帳號 :<font color='red'>${errorMsgs.get("forgetPwdCom")}</font></span>
					<input type="email" id="email" placeholder="請填電子郵件" name="id" class="form-control" />
		</div>
	
		<input type="hidden" name="action" value="forgetPwd">
		<input type="submit" class="btn btn-info" value="送出新增">  <br><br><br>
	</FORM>
  
    
    <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/mem/mem.do" name="form1" enctype="multipart/form-data" ">

				<h1 ><img src="<%= request.getContextPath() %>/Front_end/mem/img/ring_64.png">我是會員</h1> <br>
		<div class="form-group">
					<span>帳號 :<font color='red'>${errorMsgs.get("forgetPwdMem")}</font></span>
					<input type="email" placeholder="請填電子郵件" name="id" class="form-control" />
		</div>
		

		<input type="hidden" name="action" value="forgetPwd">
		<input type="submit" id="submit" class="btn btn-info" value="確定">
	</FORM>
	
    <div class="col-xs-12 col-sm-3"></div>
  </div>
</div>


<br><br><br><br><br><br>
<br><br><br><br><br><br>

<br><br><br><br>


<%@ include file="/Front_end/mem/page/register_footer.file"%>