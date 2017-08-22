<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.mem.model.*"%>

<%

Object memVO = session.getAttribute("memVO");     
%>
<%@ include file="/Front_end/com/page/share_header_v2.file"%>
 

    <div class="container">
        <div class="row">

            <div class="col-md-offset-1 col-md-2 col-xs-0">
             <br><br><br> 
                <ul class="list-group">
                   <ul class="list-group">
                   <a href="<%=request.getContextPath()%>/Front_end/mem/updatemember.jsp" class="list-group-item menua">編輯個人資料</a>
					<br>
					<a href="<%=request.getContextPath()%>/Front_end/mem/updatePwd.jsp" class="list-group-item menua">密碼修改</a>
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
			
				<div class="form-group" style="width:200px;height:200px;">		 
				   
				<img width="100%" height="auto"  src="<%=request.getContextPath()%>/ShowPictureServletDAO?mem_no=${memVO.mem_no}" />

				
			 	
				
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


<%@ include file="page/register_footer.file"%>
