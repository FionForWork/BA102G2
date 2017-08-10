<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%

MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<title>修改會員</title>
<%@ include file="page/member_header.file"%>
<style type="text/css">
			#big{
				font-size:19px;
				font-weight:600;
			}
			h1{font-weight:600;}
		</style>
		
<div class="col-xs-12 col-sm-7 col-sm-push-1" id="big">

<center><h1><img src="<%= request.getContextPath() %>/Front_end/mem/img/ring_64.png">編輯個人資料</h1></center>
	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/mem/mem.do" name="form1">
		
		<div class="form-group">
				<span>會員編號 :*</span>
				${memVO.mem_no}
		</div>

		<div class="form-group">
				<span>帳號 :</span>
				${memVO.id}
		</div>

		<div class="form-group">
           <span>姓名:</span>
           <input type="text" class="form-control" id="usr" name="name" value="${memVO.name}">
    	</div>
    		<div class="form-group">
				<span>性別 :</span>
			<div><input type="radio" name="sex" size="45"  checked="true" value="<%= (memVO==null)? "女" : memVO.getSex()%>" />女<br></div>
			<div><input type="radio" name="sex" size="45" value="<%= (memVO==null)? "男" : memVO.getSex()%>" />男</div>
	
		</div>
		<div class="form-group">
           <span>生日:</span>
           <input type="date" class="form-control"  name="bday" value="${memVO.bday}">
    	</div>
    	<div class="form-group">
           <span >電話:</span>
           <input type="text" class="form-control" id="usr" name="phone" value="${memVO.phone}">
    	</div>
    	<div class="form-group">
           <span >電子信箱:</span>
           <input type="text" class="form-control" id="usr" name="email" value="${memVO.email}">
    	</div>
    	<div class="form-group">
           <span >帳戶:</span>
           <input type="text" class="form-control" id="usr" name="account" value="${memVO.account}">
    	</div>
    	
		<div class="form-group">
				<span>被檢舉次數 :</span>
				${memVO.report}
		</div>
		<div class="form-group">
				<span>狀態 :</span>
				${memVO.status}
		</div>

<input type="hidden" name="id" value="${memVO.id}">
<input type="hidden" name="report" value="${memVO.report}">
<input type="hidden" name="status" value="${memVO.status}">
<input type="hidden" name="action" value="update">
<input type="hidden" name="mem_no" value="${memVO.mem_no}">
<input type="submit" class="btn btn-info value="送出"></FORM>



</div>

<%@ include file="page/register_footer.file"%>