<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.temp.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ include file="page/temp_com_header.file"%>

<style type="text/css">
input[type=date], input[type=number],input[type=file] {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=text], select{
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=submit] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=submit]:hover {
	background-color: #45a049;
}

.formStyle {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
.img-group{
	width:80%;
	margin:auto;
}

img .preview{
	width:100%;
	height:auto;
	margin-bottom:20px;
}

.padding .img-container{
	padding: 0 1%;
}
.errorMsgs {
	color:red;
	
}
</style>

<script>
function preview_images() {
    var total_file=document.getElementById("upload").files.length;

    for(var i = 0; i < total_file; i++){

     $('#showPanel').append("<div class='col-sm-4 img-container'><img class='img-responsive preview' src='"+
     	URL.createObjectURL(event.target.files[i])+"'><span class='glyphicon glyphicon-remove'></span></div>");
    }
}
$("document").ready(function(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();
	if(dd<10){dd='0'+dd} 
	if(mm<10){mm='0'+mm} 
	today = yyyy+'-'+mm+'-'+dd;
	$('#datePicker').attr('value', today).attr('max',today); 
	$("#datePicker").datepicker({dateFormat: 'yy-mm-dd',maxDate: "+0D"});
		 
	
});


</script>

<% 
	Map<String,String> errorMsgs = (Map)request.getAttribute("errorMsgs");
	TempVO temp = (TempVO)request.getAttribute("temp");
	session.setAttribute("com_no","2001");
%>
	
<jsp:useBean id="reservationSvc" scope="page" class="com.reservation.model.ReservationService"/>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService"/>
	

<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
<div class="container">
    <div class="col-md-offset-1">
        <ul class="breadcrumb">
            <li><a href="#">首頁</a></li>
            <li><a href="#">廠商專區</a></li>
            <li><a href="<%=request.getContextPath()%>/Front_end/Temp/ComPage_ListAllTemps.jsp">作品挑選管理</a></li>
            <li class="active">建立挑選作品</li>        
        </ul>
    </div>
</div>
<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
   
    <div class="container">
        <div class="row">
<!--sidebar sidebar sidebar sidebar sidebar sidebar -->
            <div class="col-md-offset-1 col-md-2">
                 <ul class="list-group">
                    <a href="<%=request.getContextPath()%>/Front_end/com/updatecompany.jsp" class="list-group-item menua">編輯廠商資料</a><br>
                    <a href="<%=request.getContextPath()%>/Front_end/com/updatePwd.jsp" class="list-group-item menua">修改密碼</a><br>
                    <a href="<%=request.getContextPath()%>/Front_end/reservation/comReservation.jsp" class="list-group-item menua">預約紀錄查詢</a><br>
                    <a href="<%=request.getContextPath()%>/Front_end/quote/listMyQuote.jsp" class="list-group-item menua">報價紀錄查詢</a><br>
                    <a href="<%=request.getContextPath()%>/Front_end/Temp/ComPage_ListAllTemps.jsp" class="list-group-item menua active">作品挑選管理</a><br>
                    <a href="<%= request.getContextPath() %>/Front_end/calendar/calendar.jsp" class="list-group-item menua">行事曆</a><br>
                    <a href="<%=request.getContextPath()%>/Front_end/Works/ListAllWorks.jsp" class="list-group-item menua">作品管理</a><br>
                </ul>


                <a href="#" class="btn btn-block btn-default">查看廠商資料</a>
            </div>
<!--sidebar sidebar sidebar sidebar sidebar sidebar -->

<!--這裡開始===========================================================================-->

            <div class="col-md-8 col-offset-1">

<div class='formStyle'>
		<form action="<%=request.getContextPath()%>/temp/temp.do" method="post"
				enctype="multipart/form-data">
			<h3>建立待挑選成品</h3>
			<br>
			<label for="name">待挑選作品名稱 <span class='errorMsgs'> ${errorMsgs.get("name")}</span></label> 
			<input type="text" id="name" name="name" value="<%= (temp==null)? "" : temp.getName()%>"> 
			<label for="datepicker1">拍攝日期 <span class='errorMsgs'> ${errorMsgs.get("create_date")}</span></label> 
			<input type="text" id="datePicker" name="create_date" value="<%= (temp==null)? "" : temp.getCreate_date()%>"/> 
				
			<label for="available">可挑選數量 <span class='errorMsgs'> ${errorMsgs.get("available_empty")} ${errorMsgs.get("available_number")}</span></label> 
			<input type="number" id='available' name="available" step="1" value="<%= (temp==null)? "" : temp.getAvailable()%>"> 
				
			<label for="country">客戶名稱</label>
			<select id="mem_no" name="mem_no">
			<c:forEach var='mem_no' items='${reservationSvc.getComResDistinctMemNO(com_no)}'>
				<option value="${mem_no}">${memSvc.getOneMem(mem_no).name}</option>
			</c:forEach>
			
			</select> 
			<br>
			<label for="upload">選擇作品上傳 <span class='errorMsgs'> ${errorMsgs.get("file")}</span></label>
			<input id="inputFile" name="inputFile[]" type="file" multiple class="file-loading"> <br>
			<input type="submit" value="建立">
			<input type ="submit" onclick="history.back()" value="取消"></input>
			<br>		
			
			
			</div>
			<input type='hidden' name='action' value='create_Temp'>
			<input type='hidden' name='com_no' value='${com_no}'>
			
		</form>
	</div>	
	</div>



<%@ include file="page/temp_footer.file"%>