<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.com.model.*"%>
<%
ComVO comVO = (ComVO) request.getAttribute("comVO");
%>
>
<%@ include file="/Front_end/mem/page/register_header.file"%>
<title>廠商註冊</title>

	
				
<div class="col-xs-12 col-sm-7">

	<center><h1><img src="<%= request.getContextPath() %>/Front_end/mem/img/ring_64.png">廠商註冊</h1></center>
	<h3>請輸入資料</h3>
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
	
	<div class="mation">
	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/com/com.do" name="form1" enctype="multipart/form-data" onSubmit="return check();">


	<div class="form-group">
				<span>廠商帳號 :</span>
				<input type="email" name="id" class="form-control"
			value="<%= (comVO==null)? "lf21@gmail.com" : comVO.getId()%>" />
	</div>
	<div class="form-group">
          <label for="pwd">密碼:</label>
          <input type="password" name="pwd" required title="只能輸入5~20個英數字" pattern="[A-Z0-9a-z]{5,20}$" class="form-control" id="pwd" value="<%= (comVO==null)? "asdqqw" : comVO.getPwd()%>">
    </div>
	<div class="form-group">
          <label for="pwd">確認密碼:</label>
          <input type="password"  required title="只能輸入5~20個英數字" pattern="[A-Z0-9a-z]{5,20}$" class="form-control" id="pwd" value="<%= (comVO==null)? "asdqqw" : comVO.getPwd()%>">
    </div>
	<div class="form-group">
           <span>廠商名稱:</span>
           <input type="text" class="form-control"  name="name" value="<%= (comVO==null)? "美美婚紗" : comVO.getName()%> ">
     </div>
	<br>
	<div class="form-group">
		<span>廠商地址: </span>
		<input type="text" class="form-control"  name="loc" value="<%= (comVO==null)? "(32001)桃園市中壢區中大路300號 " : comVO.getLoc()%> ">
		</div>
	<br>
	<div class="form-group">
			<span>地址經度:</span>
	<input type="text" name="lon" class="form-control"   value="<%= (comVO==null)? "121.213231" : comVO.getLon()%>">
		
	</div>
	
	<div class="form-group">
		<span>地址緯度 :</span>
		<input type="TEXT" class="form-control"name="lat" 
			value="<%= (comVO==null)? "23.123123" : comVO.getLat()%>" />
	</div>
	<div class="form-group">
		<span>廠商介紹 :</span><br>
		<input type="TEXT" name="com_desc"  class="form-control"
			value="<%= (comVO==null)? "好廠商" : comVO.getCom_desc()%>" />
	</div>
	
	<div class="form-group">
		<span>廠商電話:</span>
		<input type="TEXT" name="phone" class="form-control" required title="只能輸入數字,如為市話請加上區碼" pattern="^[0-9]*$"
			value="<%= (comVO==null)? "0936464735" : comVO.getPhone()%>" />
	</div>
	
	<div class="form-group">
		<span>廠商帳戶:</span>
		<input type="TEXT" name="account"  class="form-control"
			value="<%= (comVO==null)? "222-222-222222" : comVO.getAccount()%>" />
	</div>
	
	
	<div class="form-group">
		<span>LOGO:</span>
	
		<input type="file" name="logo" class="form-control
			value="<%= (comVO==null)? "" : comVO.getLogo()%>" />
	</div>
	

		<input type="hidden" name=com_no" value="${comVO.com_no}">
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增">
	</FORM>
	</div>
	</div>




<%@ include file="/Front_end/mem/page/register_footer.file"%>

<script language="javascript"> 
	function check() 
	{ 
	if ((document.form1.elements[1].value)!=(document.form1.elements[2].value)){ 
	alert("確認密碼不一致"); 
	return false; 
	} 
	return true; 
	} 
</script> 