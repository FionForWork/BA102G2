<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.com.model.*"%>
<%@ page import="java.util.*"%>
<%@ include file="/Front_end/mem/page/register_header.file"%>

<title>忘記密碼</title>
<br><br><br><br><br><br>

<center> <h1>請輸入帳號</h1>

稍後會有一封信寄到信箱

</center>
<div class="container">
  <div class="row">
    <div class="col-xs-12 col-sm-3"></div>
    <div class="col-xs-12 col-sm-6">
    <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/com/com.do" name="form1" enctype="multipart/form-data" ">


		<div class="form-group">
					<span>帳號 :</span>
					<input type="email" placeholder="請填電子郵件" name="id" class="form-control" />
		</div>
		

		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增">
	</FORM>
		
	</div>
    <div class="col-xs-12 col-sm-3"></div>
  </div>
</div>


<br><br><br><br><br><br>
<br><br><br><br><br><br>

<br><br><br><br>


<%@ include file="/Front_end/mem/page/register_footer.file"%>