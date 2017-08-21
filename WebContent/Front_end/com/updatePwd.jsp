<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
    <%@ page import="com.com.model.*"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
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
<h1><img src="<%= request.getContextPath() %>/Front_end/mem/img/ring_64.png">�K�X�ק�</h1><br>
<c:if test="${not empty errorMsgs}">
	<font color='red'>
	�Эץ����~:<br>
	</font>
</c:if>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/com/com.do" name="form9" onSubmit="return check();" >
		<div class="form-group">
           <span >�±K�X:<font color='red'>${errorMsgs.get(0)}</font></span>
           <input type="password" class="form-control"  name="oldpwd" value="">
    	</div>
    	<div class="form-group">
           <span >�s�K�X:</span>
           <input type="password" class="form-control" required title="�u���J5~20�ӭ^�Ʀr" pattern="[A-Z0-9a-z]{5,20}$"  name="pwd" value="">
    	</div>
    	<div class="form-group">
           <span >�T�{�s�K�X:</span>
           <input type="password" class="form-control" required title="�u���J5~20�ӭ^�Ʀr" pattern="[A-Z0-9a-z]{5,20}$"  value="">
    	</div>
    	<input type="hidden" name="com_no" value="${comVO.com_no}">
		<input type="hidden" name="action" value="updatePwd">
		<input type="submit" class="btn btn-info " value="�e�X�s�W">
</FORM>
</div>    	

<%@ include file="/Front_end/mem/page/register_footer.file"%>
<script language="javascript"> 
	function check() 
	{ 
	if ((document.form9.elements[1].value)!=(document.form9.elements[2].value)){ 
	alert("�T�{�s�K�X���@�P"); 
	return false; 
	} 
	return true; 
	} 
</script>