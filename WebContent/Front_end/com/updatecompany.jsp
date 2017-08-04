<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.com.model.*"%>
<%

ComVO comVO = (ComVO) request.getAttribute("comVO");
%>
<title>修改廠商</title>
<%@ include file="page/company_header.file"%>
<style type="text/css">
			#big{
				font-size:19px;
				font-weight:600;
			}
			h1{font-weight:600;}
		</style>
		
<div class="col-xs-12 col-sm-7 col-sm-push-1" id="big">

<center><h1><img src="<%= request.getContextPath() %>/Front_end/mem/img/ring_64.png">編輯廠商資料</h1></center>
	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/com/com.do" name="form1">
		
		<div class="form-group">
				<span>廠商編號 :*</span>
				${comVO.com_no}
		</div>

		<div class="form-group">
				<span>廠商帳號 :</span>
				${comVO.id}
		</div>

		<div class="form-group">
           <span>廠商名稱:</span>
         ${comVO.name}
    	</div>
    	<div class="form-group">
           <span >廠商地址:</span>
           <input type="text" class="form-control" id="usr" name="loc" value="${comVO.loc}">
    	</div>
    	<div class="form-group">
           <span >地址經度:</span>
           <input type="text" class="form-control" id="usr" name="lon" value="${comVO.lon}">
    	</div>
    	<div class="form-group">
           <span >地址緯度:</span>
           <input type="text" class="form-control" id="usr" name="lat" value="${comVO.lat}">
    	</div>
    	<div class="form-group">
           <span >廠商介紹:</span>
           <input type="text" class="form-control" id="usr" name="com_desc" value="${comVO.com_desc}">
    	</div>
    	<div class="form-group">
           <span >電話:</span>
           <input type="text" class="form-control" id="usr" name="phone" value="${comVO.phone}">
    	</div>
    	
    	<div class="form-group">
           <span >帳戶:</span>
           <input type="text" class="form-control" id="usr" name="account" value="${comVO.account}">
    	</div>
    	
		<div class="form-group">
				<span>狀態 :</span>
				${comVO.status}
		</div>

<input type="hidden" name="id" value="${comVO.id}">
 <input type="hidden" name="name" value="${comVO.name}">
<input type="hidden" name="status" value="${comVO.status}">
<input type="hidden" name="action" value="update">
<input type="hidden" name="com_no" value="${comVO.com_no}">
<input type="submit" class="btn btn-info value="送出"></FORM>



</div>

<%@ include file="/Front_end/mem/page/register_footer.file"%>