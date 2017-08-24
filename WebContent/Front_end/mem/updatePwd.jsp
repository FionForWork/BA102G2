<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page import="com.mem.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="java.util.*"%>
<%MemVO memVO = (MemVO) request.getAttribute("memVO");%>

<style type="text/css">
			#big{
				font-size:19px;
				font-weight:600;
			}
			h1{font-weight:600;}
</style>
<%@ include file="/Front_end/com/page/share_header_v2.file"%>
<div class="container">
        <div class="row">

            <div class="col-md-offset-1 col-md-2 col-xs-0"><br><br>
             <br><br><br> 
                <ul class="list-group">
                   <ul class="list-group">
                   <a href="<%=request.getContextPath()%>/Front_end/mem/updatemember.jsp" class="list-group-item menua">編輯個人資料</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/mem/updatePwd.jsp" class="list-group-item menua active">密碼修改</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/reservation/memReservation.jsp" class="list-group-item menua">預約紀錄查詢</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/RFQ/listMyRFQ.jsp" class="list-group-item menua">報價紀錄查詢</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/Temp/MemPage_ListAllTemps.jsp" class="list-group-item menua ">作品挑選管理</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/Album/ListAllAlbums.jsp" class="list-group-item menua">我的相簿</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/ComTra/ListAllComTra.jsp" class="list-group-item menua">我的最愛</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/Preview/ImageCropper.jsp" class="list-group-item menua">實景預覽</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/mall/index.jsp" class="list-group-item menua">商城專區</a>
					<br></ul>

																				
                <a href="<%=request.getContextPath()%>/Front_end/mem/listOneMem.jsp"  class="btn btn-block btn-default ">查看個人資料</a>
            </div>
<div class="col-xs-12 col-sm-7 col-sm-push-1" id="big">
<h1><img src="<%= request.getContextPath() %>/Front_end/mem/img/ring_64.png">密碼修改</h1><br>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>
	請修正錯誤:<br>
	</font>
</c:if>
<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/mem/mem.do" name="form3" onSubmit="return check();" >
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
           <input type="password" class="form-control"  required title="只能輸入5~20個英數字" pattern="[A-Z0-9a-z]{5,20}$" value="">
    	</div>
    	<input type="hidden" name="mem_no" value="${memVO.mem_no}">
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