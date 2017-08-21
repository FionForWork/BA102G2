<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<%
Map<String,String> errorMsgs = (HashMap) request.getAttribute("errorMsgs");
MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<title>修改會員</title>
<%@ include file="page/member_header.file"%>
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
			<%--錯誤處理 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	</font>
</c:if>
	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/mem/mem.do" name="form1" enctype="multipart/form-data">
		
		

		<div class="form-group">
				<span>帳號 :</span>
				${memVO.id}
		</div>

		<div class="form-group">
           <span>姓名:<font color='red'>${errorMsgs.get("name")}</font></span>
           <input type="text" class="form-control"  name="name" value="${memVO.name}">
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
  			 <input id='mydatepicker2' size="85" class="form-control"   name="bday"  type='text' value="" />
        </div>
    	<div class="form-group">
           <span >電話:<font color='red'>${errorMsgs.get("phone")}</font></span>
           <input type="text" class="form-control"  name="phone" value="${memVO.phone}">
    	</div>
    	<div class="form-group">
           <span >電子信箱:<font color='red'>${errorMsgs.get("email")}</font></span>
           <input type="text" class="form-control" name="email" value="${memVO.email}">
    	</div>
    	<div class="form-group">
           <span >帳戶:<font color='red'>${errorMsgs.get("account")}</font></span>
           <input type="text" class="form-control" name="account" value="${memVO.account}">
    	</div>
    	
		<div >
		<span>上傳圖片 :<font color='red'>${errorMsgs.get("picture")}</font><br></span>
		
<input id="input-1" type="file" name="picture" class="file" value="${memVO.picture}">
	</div><br>

<input type="hidden" name="id" value="${memVO.id}">
<input type="hidden" name="report" value="${memVO.report}">
<input type="hidden" name="status" value="${memVO.status}">
<input type="hidden" name="action" value="update">
<input type="hidden" name="mem_no" value="${memVO.mem_no}">
<input type="submit" class="btn btn-info" value="送出"></FORM>



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