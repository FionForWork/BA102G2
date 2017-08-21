<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/Back_end/login/loginbackHeader.file"%>
    </script>
<title>修改密碼</title>
<div id="content">
<br><br><br><br><br><br>
<br><br><br>
<center> <h1>密碼修改</h1></center>


<c:if test="${not empty errorMsgs}">
	<font color='red'>
	請修正錯誤:<br>
	</font>
</c:if>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/adm/adm.do" name="form9" onSubmit="return check();" >
		<div class="form-group">
		<span >員工編號: ${admVO.adm_no}<br><br><br></span>
           <span >舊密碼:<font color='red'>${errorMsgs.get(0)}</font></span>
           <input type="password" class="form-control"  name="oldpwd" value="">
    	</div>
    	<div class="form-group">
           <span >新密碼:</span>
           <input type="password" class="form-control" required title="只能輸入5~12個英數字" pattern="[0-9]{5,12}$"  name="pwd" value="">
    	</div>
    	<div class="form-group">
           <span >確認新密碼:</span>
           <input type="password" class="form-control" required title="只能輸入5~12個英數字" pattern="[0-9]{5,12}$"  value="">
    	</div>
    	<input type="hidden" name="adm_no" value="${admVO.adm_no}">
		<input type="hidden" name="action" value="updatePwd">
		<input type="submit" class="btn btn-info " value="送出新增">
</FORM>
   	


<script language="javascript"> 
	function check() 
	{ 
	if ((document.form9.elements[1].value)!=(document.form9.elements[2].value)){ 
	alert("確認新密碼不一致"); 
	return false; 
	} 
	return true; 
	} 
</script>



<br><br><br>
<br><br><br><br><br><br>

<br><br><br><br>

</div>   
<%@ include file="/Back_end/pages/backFooter.file" %>