<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
    <%@ page import="com.mem.model.*"%>
    
<%MemVO memVO = (MemVO) request.getAttribute("memVO");%>

<style type="text/css">
			#big{
				font-size:19px;
				font-weight:600;
			}
			h1{font-weight:600;}
</style>
<%@ include file="page/member_header.file"%>

<div class="col-xs-12 col-sm-7 col-sm-push-1" id="big">
<h1><img src="<%= request.getContextPath() %>/Front_end/mem/img/ring_64.png">�K�X�ק�</h1><br>
<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/mem/mem.do" name="form3" onSubmit="return check();" >
		<div class="form-group">
           <span >�±K�X:</span>
           <input type="passwprd" class="form-control"  name="oldpwd" value="">
    	</div>
    	<div class="form-group">
           <span >�s�K�X:</span>
           <input type="passwprd" class="form-control"  name="pwd" value="">
    	</div>
    	<div class="form-group">
           <span >�T�{�s�K�X:</span>
           <input type="passwprd" class="form-control"   value="">
    	</div>
    	<input type="hidden" name="mem_no" value="${memVO.mem_no}">
		<input type="hidden" name="action" value="updatePwd">
		<input type="submit" class="btn btn-info " value="�e�X�s�W">
</FORM>
</div>    	

<%@ include file="/Front_end/mem/page/register_footer.file"%>
<script language="javascript"> 
	function check() 
	{ 
	if ((document.form3.elements[1].value)!=(document.form3.elements[2].value)){ 
	alert("�T�{�s�K�X���@�P"); 
	return false; 
	} 
	return true; 
	} 
</script>