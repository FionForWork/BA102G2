<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.com.model.*"%>
<%@ page import="java.util.*"%>
<%ComVO comVO = (ComVO) request.getAttribute("comVO");%>

<style type="text/css">
			#big{
				font-size:19px;
				font-weight:600;
			}
			h1{font-weight:600;}
</style>
<%@ include file="/Front_end/com/page/share_header_v2.file"%>
<title>修改密碼</title>
<div class="container">
	<div class="row">
	
		<div class="col-md-offset-1 col-md-2">
			<ul class="list-group">
				<a href="<%=request.getContextPath()%>/Front_end/com/updatecompany.jsp" class="list-group-item menua">編輯廠商資料</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/com/updatePwd.jsp" class="list-group-item menua active">修改密碼</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/reservation/comReservation.jsp" class="list-group-item menua">預約紀錄查詢</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/quote/listMyQuote.jsp" class="list-group-item menua">報價紀錄查詢</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/Temp/ComPage_ListAllTemps.jsp" class="list-group-item menua ">作品挑選管理</a><br>
                <a href="<%= request.getContextPath() %>/Front_end/calendar/calendar.jsp" class="list-group-item menua">行事曆</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/Works/ListAllWorks.jsp" class="list-group-item menua">作品管理</a><br>
			</ul>


			<a href="<%=request.getContextPath()%>/Front_end/com/listOneCom.jsp" class="btn btn-block btn-default">查看廠商資料</a>
		</div>
<div class="col-xs-12 col-sm-7 col-sm-push-1" id="big">
<h1><img src="<%= request.getContextPath() %>/Front_end/mem/img/ring_64.png">密碼修改</h1><br>
<c:if test="${not empty errorMsgs}">
	<font color='red'>
	請修正錯誤:<br>
	</font>
</c:if>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/com/com.do" name="form9" onSubmit="return check();" >
		<div class="form-group">
           <span >舊密碼:<font color='red'>${errorMsgs.get(0)}</font></span>
           <input type="password" class="form-control"  name="oldpwd" value="">
    	</div>
    	<div class="form-group">
           <span >新密碼:</span>
           <input type="password" class="form-control" required title="只能輸入5~20個英數字" pattern="[A-Z0-9a-z]{5,20}$"  name="pwd" value="">
    	</div>
    	<div class="form-group">
           <span >確認新密碼:</span>
           <input type="password" class="form-control" required title="只能輸入5~20個英數字" pattern="[A-Z0-9a-z]{5,20}$"  value="">
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
	if ((document.form9.elements[1].value)!=(document.form9.elements[2].value)){ 
	alert("確認新密碼不一致"); 
	return false; 
	} 
	return true; 
	} 
</script>