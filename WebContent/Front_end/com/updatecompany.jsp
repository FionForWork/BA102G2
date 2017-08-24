<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.com.model.*"%>
<%@ page import="java.util.*"%>
<%
ComVO comVO =(ComVO) request.getAttribute("comVO");
Map<String,String> errorMsgs = (HashMap) request.getAttribute("errorMsgs");
%>

<%@ include file="/Front_end/com/page/share_header_v2.file"%>
<title>修改廠商</title>
<div class="container">
	<div class="row">
	
		<div class="col-md-offset-1 col-md-2"><br><br>
			<ul class="list-group">
				<a href="<%=request.getContextPath()%>/Front_end/com/updatecompany.jsp" class="list-group-item menua active">編輯廠商資料</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/com/updatePwd.jsp" class="list-group-item menua">修改密碼</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/reservation/comReservation.jsp" class="list-group-item menua">預約紀錄查詢</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/quote/listMyQuote.jsp" class="list-group-item menua">報價紀錄查詢</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/Temp/ComPage_ListAllTemps.jsp" class="list-group-item menua ">作品挑選管理</a><br>
                <a href="<%= request.getContextPath() %>/Front_end/calendar/calendar.jsp" class="list-group-item menua">行事曆</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/Works/ListAllWorks.jsp" class="list-group-item menua">作品管理</a><br>
			</ul>


			<a href="<%=request.getContextPath()%>/Front_end/com/listOneCom.jsp" class="btn btn-block btn-default">查看廠商資料</a>
		</div>


<style type="text/css">
			#big{
				font-size:19px;
				font-weight:600;
			}
			h1{font-weight:600;}
		</style>
		
<div class="col-xs-12 col-sm-7 col-sm-push-1" id="big">

<center><h1><img src="<%= request.getContextPath() %>/Front_end/mem/img/ring_64.png">編輯廠商資料</h1></center>
	<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	</font>
</c:if>
	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/com/com.do" name="form1"  enctype="multipart/form-data">


		<div class="form-group">
				<span>廠商帳號 :</span>
				${comVO.id}
		</div>

		<div class="form-group">
           <span>廠商名稱:</span>
         ${comVO.name}
    	</div>
    	<div class="form-group">
           <span >廠商地址:<font color='red'>${errorMsgs.get("loc")}</font></span>
           <input type="text" class="form-control"  name="loc" value="${comVO.loc}">
    	</div>

    	
    	<div class="form-group">
           <span >電話:<font color='red'>${errorMsgs.get("phone")}</font></span>
           <input type="text" class="form-control"  name="phone" value="${comVO.phone}">
    	</div>
    	
    	<div class="form-group">
           <span >帳戶:<font color='red'>${errorMsgs.get("account")}</font></span>
           <input type="text" class="form-control"  name="account" value="${comVO.account}">
    	</div>
    	

		<div>
		<span>LOGO:<font color='red'>${errorMsgs.get("logo")}</font><br></span>
	
			<label class="control-label">選擇圖片</label>
			<input id="input-1" type="file" name="logo"  class="file">
			
	</div>
		
		<div class="form-group">
           <span >廠商介紹:<font color='red'>${errorMsgs.get("com_desc")}</font></span>
           <textarea name=com_desc  class="form-control" rows=8 >${comVO.com_desc}</textarea>
 
    	</div>


<input type="hidden" name="lat" value="${comVO.lat}">
<input type="hidden" name="lon" value="${comVO.lon}">
<input type="hidden" name="id" value="${comVO.id}">
 <input type="hidden" name="name" value="${comVO.name}">
<input type="hidden" name="status" value="${comVO.status}">
<input type="hidden" name="action" value="update">
<input type="hidden" name="com_no" value="${comVO.com_no}">
<input type="submit" class="btn btn-info" value="送出">　　
<input type="button" class="btn btn-info" value="取消" onclick="location.href='<%=request.getContextPath()%>/Front_end/com/listOneCom.jsp'" >
</FORM>



</div>

<script>
$("#input-1").fileinput({
        maxFileCount: 1,
        allowedFileTypes: ["image"],
        language: 'zh-TW', //设置语言
        dropZoneEnabled: false,//是否显示拖拽区域
        showUpload: false,
        theme: "fa",
        
    }); 
</script>

<%@ include file="/Front_end/mem/page/register_footer.file"%>