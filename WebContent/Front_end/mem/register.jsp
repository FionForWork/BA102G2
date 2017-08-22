<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>

<%
Map<String,String> errorMsgs = (HashMap) request.getAttribute("errorMsgs");
MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<%@ include file="page/register_header.file"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/Front_end/mem/css/dcalendar.picker.css"/>
<link href="<%=request.getContextPath()%>/Front_end/Album/themes/explorer/theme.min.css" media="all" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/Front_end/Album/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/Front_end/Album/js/piexif.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/Front_end/Album/js/purify.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/Front_end/Album/js/fileinput.min.js"></script>
<script src="<%=request.getContextPath()%>/Front_end/Album/themes/explorer/theme.js"></script>
<script src="<%=request.getContextPath()%>/Front_end/Album/themes/fa/theme.js"></script>
<script src="<%=request.getContextPath()%>/Front_end/Album/js/zh-TW.js"></script>

<title>會員註冊</title>
		
<div class="col-xs-12 col-sm-7">

	<center><h1><img src="<%= request.getContextPath() %>/Front_end/mem/img/ring_64.png">會員註冊</h1></center>
	<h3>請輸入資料</h3>
	<br><center><input  type="button" class="btn btn-info " value="一鍵輸入" id="fast"></center><br>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
		</font>
	</c:if>
	
	<div class="mation">
	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/mem/mem.do" name="form1" enctype="multipart/form-data" onSubmit="return check();">


	<div class="form-group">
				<span>帳號 :請填電子郵件</span>
				<input type="email" name="id" id="id" placeholder="請填電子郵件" class="form-control"
			value="" onkeypress="if (window.event.keyCode==13) return false;"/>
	</div>
	<div class="form-group">
                    <label for="pwd">密碼:</label>
                    <input type="password" name="pwd" required title="只能輸入5~20個英數字" pattern="[A-Z0-9a-z]{5,20}$" class="form-control" id="pwd" value="" onkeypress="if (window.event.keyCode==13) return false;">
    </div>
	<div class="form-group">
                    <label for="pwd">確認密碼:</label>
                    <input type="password"  required title="只能輸入5~20個英數字" pattern="[A-Z0-9a-z]{5,20}$" class="form-control" id="pwd2" value="" onkeypress="if (window.event.keyCode==13) return false;">
    </div>
	<div class="form-group">
           <label >姓名:<font color='red'>${errorMsgs.get("name")}</font></label>
           <input type="text" class="form-control"  name="name" id="name" value="" onkeypress="if (window.event.keyCode==13) return false;">
     </div>
	<br>
	<div>
		<span>性別 :</span>
		<div>	<input type="radio" name="sex"  value="女"size="45"  value=""onkeypress="if (window.event.keyCode==13) return false;" />女<br></div>
		<div>	<input type="radio" name="sex" value="男"size="45" value="" onkeypress="if (window.event.keyCode==13) return false;"/>男</div>
	</div>
	<br>
	
	<div class="form-group">
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<span>生日 :<font color='red'>${errorMsgs.get("bday")}</font><br></span>
		<input id='mydatepicker2' size="100" class="form-control"  name="bday" id="bday"  type='text' value="" onkeypress="if (window.event.keyCode==13) return false;" />
	</div>
	
	<div class="form-group">
		<span>連絡電話 :<font color='red'>${errorMsgs.get("phone")}</font></span>
		<input type="TEXT" class="form-control" name="phone" id="phone" required title="只能輸入數字,如為市話請加上區碼" pattern="^[0-9]*$" 
			value="" onkeypress="if (window.event.keyCode==13) return false;"/>
	</div>
	<div class="form-group">
		<span>電子信箱 :<font color='red'>${errorMsgs.get("email")}</font></span>
		<input type="email" class="form-control" name="email" id="email" size="45"
			value="" onkeypress="if (window.event.keyCode==13) return false;" />
	</div>
	<div class="form-group">
		<span>銀行帳戶 :<font color='red'>${errorMsgs.get("account")}</font></span>
		<input type="TEXT" class="form-control" name="account" id="account" size="45"
			value="" onkeypress="if (window.event.keyCode==13) return false;"/>
	</div>
	
	<div >
		<span>上傳圖片 :<font color='red'>${errorMsgs.get("picture")}</font><br></span>
		<label class="control-label">選擇圖片</label>
<input id="input-1" type="file" name="picture" class="file"><br>
	</div>

	<center><br><div id="recaptcha_box">請驗證</div>
		<input type="hidden" name="mem_no" value="${memVO.mem_no}">
		<input type="hidden" name="action" value="insert">
		<input style="display:none" type="submit" id="submit" class="btn btn-info " value="送出新增"></center>
	</FORM>
	</div>
	</div>
	 


<%@ include file="page/register_footer.file"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/Front_end/mem/js/dcalendar.picker.js"></script>
<script language="javascript"> 
		$('#mydatepicker2').dcalendarpicker({
			format:'yyyy-mm-dd'
		}); 

	</script>
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
			$("#name").attr("value",'黃家家');
			$("#pwd").attr("value",'123123');
			$("#pwd2").attr("value",'123123');
			$("#id").attr("value",'lf2lf2111@gmail.com');
			$("#phone").attr("value",'0922333444');
			$("#account").attr("value",'822-223121-2124455');
			$("#email").attr("value",'lf2lf211@hotmail.com');
			$("input[name=sex][value='女']").attr("checked",true);
		});
	});
	</script>