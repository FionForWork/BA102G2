<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%

MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<%@ include file="page/register_header.file"%>

<title>會員註冊</title>



				
<div class="col-xs-12 col-sm-7">

	<center><h1><img src="<%= request.getContextPath() %>/Front_end/mem/img/ring_64.png">會員註冊</h1></center>
	<h3>請輸入資料</h3>
	<br>
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
	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/mem/mem.do" name="form1" enctype="multipart/form-data" onSubmit="return check();">


	<div class="form-group">
				<span>帳號 :</span>
				<input type="email" name="id" class="form-control"
			value="<%= (memVO==null)? "lf21@gmail.com" : memVO.getId()%>" />
	</div>
	<div class="form-group">
                    <label for="pwd">密碼:</label>
                    <input type="password" name="pwd" required title="只能輸入5~20個英數字" pattern="[A-Z0-9a-z]{5,20}$" class="form-control" id="pwd" value="<%= (memVO==null)? "asdqqw" : memVO.getPwd()%>">
    </div>
	<div class="form-group">
                    <label for="pwd">確認密碼:</label>
                    <input type="password"  required title="只能輸入5~20個英數字" pattern="[A-Z0-9a-z]{5,20}$" class="form-control" id="pwd" value="<%= (memVO==null)? "asdqqw" : memVO.getPwd()%>">
    </div>
	<div class="form-group">
           <label for="usr">姓名:</label>
           <input type="text" class="form-control" id="usr" name="name" value="<%= (memVO==null)? "肉肉" : memVO.getName()%> ">
     </div>
	<br>
	<div>
		<span>性別 :</span>
		<div>	<input type="radio" name="sex" size="45" checked="true" value="<%= (memVO==null)? "WOMAN" : memVO.getSex()%>" />女<br></div>
		<div>	<input type="radio" name="sex" size="45" value="<%= (memVO==null)? "MALE" : memVO.getSex()%>" />男</div>
	</div>
	<br>
	<div class="form-group">
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<span>生日 :</span>
	<input type="date" name="bday" class="form-control" value="<%= (memVO==null)? date_SQL : memVO.getBday()%>">
		
	</div>
	
	<div class="form-group">
		<span>連絡電話 :</span>
		<input type="TEXT" class="form-control"name="phone" required title="只能輸入數字至少8個以上如為市話請加上區碼" pattern="^[0-9]*$" 
			value="<%= (memVO==null)? "0912345678" : memVO.getPhone()%>" />
	</div>
	<div class="form-group">
		<span>電子信箱 :</span>
		<input type="email" class="form-control" name="email" size="45"
			value="<%= (memVO==null)? "llf2@gmail.com" : memVO.getEmail()%>" />
	</div>
	<div class="form-group">
		<span>銀行帳戶 :</span>
		<input type="TEXT" class="form-control" name="account" size="45"
			value="<%= (memVO==null)? "100" : memVO.getAccount()%>" />
	</div>
	
	<div class="form-group">
		<span>上傳圖片 :</span>
		<input type="file" class="form-control" name="picture" size="45"
			value="<%= (memVO==null)? "" : memVO.getPicture()%>" />
	</div>
	
		<input type="hidden" name="mem_no" value="${memVO.mem_no}">
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增">
	</FORM>
	</div>
	</div>
	 


<%@ include file="page/register_footer.file"%>

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