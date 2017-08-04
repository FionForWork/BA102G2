<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.mem.model.*"%>

<%

Object memVO = session.getAttribute("memVO");     
%>
<%@ include file="page/member_header.file"%>
<style type="text/css">
			#big{
				font-size:19px;
				font-weight:600;
			}
			h1{font-weight:600;}
		</style>
<title>會員資料</title>
<div class="container" id="big">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-sm-push-2">
		<h1 ><img src="<%= request.getContextPath() %>/Front_end/mem/img/ring_64.png">個人資料</h1>
			<div class="form-group">
				<span  >大頭貼 :</span><br>
				<img  src="<%=request.getContextPath()%>/ShowPictureServletDAO?mem_no=${memVO.mem_no}" />

				<input type="button" class="btn btn-info " id="addpic" value="修改頭貼">
			 	
				
			</div>
		
		<div class="form-group">
				<span>會員編號 :*</span>
				${memVO.mem_no}
		</div>

		<div class="form-group">
				<span>帳號 :</span>
				${memVO.id}
		</div>
		<div class="form-group">
				<span>姓名 :</span>
				${memVO.name}
		</div>
		<div class="form-group">
				<span>性別 :</span>
				${memVO.sex}
		</div>
		<div class="form-group">
				<span>帳號 :</span>
				${memVO.name}
		</div>
		<div class="form-group">
				<span>生日 :</span>
				${memVO.bday}
		</div>
		<div class="form-group">
				<span>電話 :</span>
				${memVO.phone}
		</div>
		<div class="form-group">
				<span>電子郵件 :</span>
				${memVO.email}
		</div>
		
<div class="form-group">
				<span>銀行帳戶 :</span>
				${memVO.account}
		</div>
		
		<div class="form-group">
				<span>被檢舉次數 :</span>
				${memVO.report}
		</div>
		<div class="form-group">
				<span>狀態 :</span>
				${memVO.status}
		</div>
		

		 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do">
			 <input type="submit" class="btn btn-info " value="修改">
			 <input type="hidden" name="mem_no"value="${memVO.mem_no}" >
			 <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
	
		
		</div>
	</div>
</div>
<%@ include file="/Front_end/mem/page/addpic.file"%>

<%@ include file="page/register_footer.file"%>