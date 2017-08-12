<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Object comVO = session.getAttribute("comVO");     
%>
<%@ include file="page/company_header.file"%>

<style type="text/css">
			#big{
				font-size:19px;
				font-weight:600;
			}
			h1{font-weight:600;}
		</style>
<title>廠商資料</title>
<div class="container" id="big">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-sm-push-2">
		<h1 ><img src="<%= request.getContextPath() %>/Front_end/mem/img/ring_64.png">廠商資料</h1>
			<div class="form-group" style="width:200px;height:200px;">		 
				 
				  
				   <img width="100%" height="auto" src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${comVO.com_no}" />

			</div>
		
		<br>

		<div class="form-group">
				<span>帳號 :</span>
				${comVO.id}
		</div>
		<div class="form-group">
				<span>廠商名稱 :</span>
				${comVO.name}
		</div>

		<div class="form-group">
				<span>廠商地址:</span>
				${comVO.loc}
		</div>
		
		<div class="form-group">
				<span>電話 :</span>
				${comVO.phone}
		</div>
	
		
<div class="form-group">
				<span>銀行帳戶 :</span>
				${comVO.account}
		</div>
		
		
		<div class="form-group">
				<span>狀態 :</span>
				${comVO.status}
		</div>
		
		<div class="form-group">
				<span>廠商介紹:</span>
				<div ></div>
				 <pre style="border:0;font-size:19px;font-weight:900;">${comVO.com_desc}</pre>
		</div>

		 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/com/com.do">
			 <input type="submit" class="btn btn-info " value="修改">
			 <input type="hidden" name="com_no"value="${comVO.com_no}" >
			 <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
	
		
		</div>
	</div>
</div>


<%@ include file="/Front_end/mem/page/register_footer.file"%>