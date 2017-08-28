<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<%
Map<String,String> errorMsgs = (HashMap) request.getAttribute("errorMsgs");
MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<title>修改會員</title>
<%@ include file="/Front_end/com/page/share_header_v2.file"%>

	<link href="<%=request.getContextPath()%>/Front_end/Resource/themes/explorer/theme.min.css" media="all" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath()%>/Front_end/Resource/css/bootstrapfileinput/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
	<script src="<%=request.getContextPath()%>/Front_end/Resource/js/bootstrapfileinput/piexif.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/Front_end/Resource/js/bootstrapfileinput/purify.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/Front_end/Resource/js/bootstrapfileinput/fileinput.min.js"></script>
	<script src="<%=request.getContextPath()%>/Front_end/Resource/themes/explorer/theme.js"></script>
	<script src="<%=request.getContextPath()%>/Front_end/Resource/themes/fa/theme.js"></script>
	<script src="<%=request.getContextPath()%>/Front_end/Resource/js/bootstrapfileinput/zh-TW.js"></script>
	<script src="<%=request.getContextPath()%>/Front_end/Resource/js/jqueryui/jquery-ui.js" type="text/javascript"></script>
	
 
<div class="container">
	<div class="row">
<!--sidebar sidebar sidebar sidebar sidebar sidebar -->
            <div class="col-md-offset-1 col-md-2"><br><br><br><br><br>
                 <ul class="list-group">
                   <a href="<%=request.getContextPath()%>/Front_end/mem/updatemember.jsp" class="list-group-item menua active">編輯個人資料</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/mem/updatePwd.jsp" class="list-group-item menua">密碼修改</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/reservation/memReservation.jsp" class="list-group-item menua ">預約紀錄查詢</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/RFQ/listMyRFQ.jsp" class="list-group-item menua">報價紀錄查詢</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/Temp/MemPage_ListAllTemps.jsp" class="list-group-item menua">作品挑選管理</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/Album/ListAllAlbums.jsp" class="list-group-item menua">我的相簿</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/ComTra/ListAllComTra.jsp" class="list-group-item menua">我的最愛</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/Preview/ImageCropper.jsp" class="list-group-item menua">實景預覽</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/mall/mallArea.jsp" class="list-group-item menua">商城專區</a>
					<br>
                </ul>
                <a href="<%=request.getContextPath()%>/Front_end/mem/listOneMem.jsp" class="btn btn-block btn-default">查看個人資料</a>
            </div>
<link rel="stylesheet" href="<%=request.getContextPath()%>/Front_end/mem/css/dcalendar.picker.css"/>

<link href="<%=request.getContextPath()%>/Front_end/Album/themes/explorer/theme.min.css" media="all" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/Front_end/Album/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/Front_end/Album/js/piexif.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/Front_end/Album/js/purify.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/Front_end/Album/js/fileinput.min.js"></script>
<script src="<%=request.getContextPath()%>/Front_end/Album/themes/explorer/theme.js"></script>
<script src="<%=request.getContextPath()%>/Front_end/Album/themes/fa/theme.js"></script>
<script src="<%=request.getContextPath()%>/Front_end/Album/js/zh-TW.js"></script>

<style type="text/css">
			#big{
				font-size:19px;
				font-weight:600;
			}
			h1{font-weight:600;}
		</style>

<div class="col-xs-12 col-sm-7 col-sm-push-1" id="big">

<center><h1><img src="<%= request.getContextPath() %>/Front_end/mem/img/ring_64.png">編輯個人資料</h1></center>
			

	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/mem/mem.do" name="form1" enctype="multipart/form-data">
		
		

		<div class="form-group">
				<span>帳號 :</span>
				${memVO.id}
		</div>

		<div class="form-group">
           <span>姓名:<font color='red'>${errorMsgs.get("name")}</font></span>
           <input type="text" id="name" class="form-control"  name="name" value="${memVO.name}">
    	</div>
    		<div class="form-group">
				<span>性別 :</span>
					<c:if test="${memVO.sex==\"男\"}" >
				<div><input type="radio" name="sex"   value="女" />女<br></div>
				<div><input type="radio" name="sex" checked="true"  value="男" />男</div>
				</c:if>
				<c:if test="${memVO.sex==\"女\"}" >
				<div><input type="radio" name="sex" checked="true"  value="女" />女<br></div>
				<div><input type="radio" name="sex"  value="男" />男</div>
				</c:if>
			
		</div>
		<div class="form-group">
           <span>生日:<font color='red'>${errorMsgs.get("bday")}</font><br></span>
  			${memVO.bday}
        </div>
    	<div class="form-group">
           <span >電話:<font color='red'>${errorMsgs.get("phone")}</font></span>
           <input type="text" class="form-control"id="phone"  name="phone" value="${memVO.phone}">
    	</div>
    	<div class="form-group">
           <span >電子信箱:<font color='red'>${errorMsgs.get("email")}</font></span>
           <input type="text" class="form-control" id="email" name="email" value="${memVO.email}">
    	</div>
    	<div class="form-group">
           <span >帳戶:<font color='red'>${errorMsgs.get("account")}</font></span>
           <input type="text" class="form-control" id="account" name="account" value="${memVO.account}">
    	</div>
    	
		<div >
		<span>上傳圖片 :<font color='red'>${errorMsgs.get("picture")}</font><br></span>
		
<input id="input-1" type="file" name="picture" class="file" value="${memVO.picture}">
	</div><br>
<input type="hidden" name="bday" value="${memVO.bday}">
<input type="hidden" name="id" value="${memVO.id}">
<input type="hidden" name="report" value="${memVO.report}">
<input type="hidden" name="status" value="${memVO.status}">
<input type="hidden" name="action" value="update">
<input type="hidden" name="mem_no" value="${memVO.mem_no}">
<input type="submit" class="btn btn-info" value="送出">　　
<input type="button" class="btn btn-info" value="取消" onclick="location.href='<%=request.getContextPath()%>/Front_end/mem/listOneMem.jsp'" >
</FORM>
<br>
<input  type="button" class="btn btn-info " value="" id="fast6">


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
<script type="text/javascript" src="<%=request.getContextPath()%>/Front_end/mem/js/dcalendar.picker.js"></script>
<script language="javascript"> 
		$('#mydatepicker2').dcalendarpicker({
			format:'yyyy-mm-dd'
		}); 

	</script>
<%@ include file="page/register_footer.file"%>
<script>
	$(document).ready(function(){  
		$("#fast6").click(function() {
			$("#name").attr("value",'黃慈慈');
			$("#phone").attr("value",'0948468852');
			$("#account").attr("value",'003-583148-8761547');
			$("#email").attr("value",'a23ddg5@gmail.com');
			
		});
	});
	</script> 