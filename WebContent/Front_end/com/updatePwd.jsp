<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
    <%@ page import="com.com.model.*"%>
    
<%ComVO comVO = (ComVO) request.getAttribute("comVO");%>

<style type="text/css">
			#big{
				font-size:19px;
				font-weight:600;
			}
			h1{font-weight:600;}
</style>
<%@ include file="page/company_header.file"%>

<div class="col-xs-12 col-sm-7 col-sm-push-1" id="big">
<h1><img src="<%= request.getContextPath() %>/Front_end/mem/img/ring_64.png">密碼修改</h1><br>
<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/com/com.do" name="form3" onSubmit="return check();" >
		<div class="form-group">
           <span >舊密碼:</span>
           <input type="passwprd" class="form-control"  name="oldpwd" value="">
    	</div>
    	<div class="form-group">
           <span >新密碼:</span>
           <input type="passwprd" class="form-control"  name="pwd" value="">
    	</div>
    	<div class="form-group">
           <span >確認新密碼:</span>
           <input type="passwprd" class="form-control"   value="">
    	</div>
    	<input type="hidden" name="com_no" value="${comVO.com_no}">
		<input type="hidden" name="action" value="updatePwd">
		<input type="submit" class="btn btn-info " value="送出新增">
</FORM>
</div>    	

<%@ include file="/Front_end/mem/page/register_footer.file"%>
<script language="javascript"> 
	function check() 
	{ 
	if ((document.form3.elements[1].value)!=(document.form3.elements[2].value)){ 
	alert("確認新密碼不一致"); 
	return false; 
	} 
	return true; 
	} 
</script>