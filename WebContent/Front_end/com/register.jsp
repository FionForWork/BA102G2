<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.com.model.*"%>
<%@ page import="java.util.*"%>
<%
ComVO comVO = (ComVO) request.getAttribute("comVO");

Map<String,String> errorMsgs = (HashMap) request.getAttribute("errorMsgs");
%>

<%@ include file="/Front_end/mem/page/register_header.file"%>
<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
        async defer>
    </script>

 



<title>廠商註冊</title>

	
				
<div class="col-xs-12 col-sm-7">

	<center><h1><img src="<%= request.getContextPath() %>/Front_end/mem/img/ring_64.png">廠商註冊</h1></center>
	<h3>請輸入資料</h3>
		<br><center><input  type="button" class="btn btn-info " value="一鍵輸入" id="fast"></center><br>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
		
		</font>
	</c:if>
	
	<div class="mation">
	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/com/com.do" name="form1" enctype="multipart/form-data" onSubmit="return check();">


	<div class="form-group">
				<span>廠商帳號 :請填正確電子郵件驗證信及找回密碼需用到<br><font color='red'>${errorMsgs.get("e")}${errorMsgs.get("id") }</font></span>
				<input type="email" placeholder="請填電子郵件" id="id" name="id" class="form-control"
			value="" onkeypress="if (window.event.keyCode==13) return false;"/>
	</div>
	<div class="form-group">
          <label >密碼:</label>
          <input type="password" name="pwd" required title="只能輸入5~20個英數字" pattern="[A-Z0-9a-z]{5,20}$" class="form-control" id="pwd" value="" onkeypress="if (window.event.keyCode==13) return false;">
    </div>
	<div class="form-group">
          <label >確認密碼:</label>
          <input type="password"  required title="只能輸入5~20個英數字" pattern="[A-Z0-9a-z]{5,20}$" class="form-control" id="pwd2" value="" onkeypress="if (window.event.keyCode==13) return false;">
    </div>
	<div class="form-group">
           <span>廠商名稱:<font color='red'>${errorMsgs.get("name")}</font></span>    
           <input type="text" class="form-control" id="name" name="name" value=""onkeypress="if (window.event.keyCode==13) return false;">
     </div>
	<br>
	<div class="form-group">
		<span>廠商地址:<font color='red'>${errorMsgs.get("loc")}</font></span>
		<input type="TEXT" name="loc" id="loc" class="form-control" value="" onkeypress="if (window.event.keyCode==13) return false;"/>
	</div>
	
	<div class="form-group">
		<span>廠商電話:</span>
		<input type="TEXT" name="phone" class="form-control" required title="只能輸入數字,如為市話請加上區碼" pattern="^[0-9]*$"
			value="" id="phone"onkeypress="if (window.event.keyCode==13) return false;" />
	</div>
	
	<div class="form-group">
		<span>廠商帳戶:<font color='red'>${errorMsgs.get("account")}</font></span>
		<input type="TEXT" name="account"  class="form-control"
			value="" id="account" onkeypress="if (window.event.keyCode==13) return false;"/>
	</div>
	
	
	<div >
		<span>LOGO:<font color='red'>${errorMsgs.get("logo")}</font><br></span>
	
			<label class="control-label">選擇圖片</label>
			<input id="input-1" type="file" name="logo" class="file">
	</div>
	<div class="form-group">
		<span>廠商介紹 :<font color='red'>${errorMsgs.get("com_desc")}</font></span><br>
		
		<textarea name="com_desc" id="com_desc"  class="form-control" rows=8 onkeypress="if (window.event.keyCode==13) return false;" ></textarea>
		
	</div>
	
<center><br><div id="recaptcha_box">請驗證</div>
		<input type="hidden" name=com_no" value="${comVO.com_no}">
		<input type="hidden" name="action" value="insert">
		<input type="submit" class="btn btn-info " id="submit" style="display:none" value="送出新增"></center>
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
	
	
	$("#input-1").fileinput({
	        maxFileCount: 1,
	        allowedFileTypes: ["image"],
	        language: 'zh-TW', //设置语言
	        dropZoneEnabled: false,//是否显示拖拽区域
	        showUpload: false,
	        theme: "fa",
	        
	    }); 
</script>
<script>
	$(document).ready(function(){  
		$("#fast").click(function() {
			$("#name").attr("value",'美美麗麗');
			$("#pwd").attr("value",'123123');
			$("#pwd2").attr("value",'123123');

			$("#id").attr("value",'lf2lf2111@gmail.com');

			$("#com_desc").val('我們賣最好的東西');
			$("#phone").attr("value",'0922333444');
			$("#account").attr("value",'822-223121-2124455');
			$("#loc").attr("value",'桃園市中壢區中大路300號');
			
		});
	});
	</script> 