<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<%
		//String mem_no = (String) session.getAttribute("mem_no");
		session.setAttribute("mem_no","1001");
		String mergeCont_no = (String)request.getAttribute("mergeCont_no");
		//String mergeCont_no ="0216";
	%>
	
<%@ include file="page/preview_header.file"%>
<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
<div class="container">
	<div class="col-md-offset-1">
		<ul class="breadcrumb">
			<li><a href="#">首頁</a></li>
			<li><a href="#">會員專區</a></li>
			<li class="active">實景預覽</li>
		</ul>
	</div>
</div>
<div class="container">
        <div class="row">
<!--sidebar sidebar sidebar sidebar sidebar sidebar -->
            <div class="col-md-offset-1 col-md-2 col-xs-0">
             <br><br><br> 
                <ul class="list-group">
                    <a href="<%=request.getContextPath()%>/Front_end/mem/updatemember.jsp" class="list-group-item menua">編輯個人資料</a><br>

                    <a href="<%=request.getContextPath()%>/Front_end/mem/updatePwd.jsp" class="list-group-item menua">密碼修改</a><br>

                    <a href="#" class="list-group-item menua">預約紀錄查詢</a><br>
                    <a href="#" class="list-group-item menua">報價紀錄查詢</a><br>
                    <a href="<%=request.getContextPath()%>/Front_end/Temp/MemPage_ListAllTemps.jsp" class="list-group-item menua">作品挑選管理</a><br>

                    <a href="<%=request.getContextPath()%>/Front_end/Album/ListAllAlbums.jsp" class="list-group-item menua">我的相簿</a><br>

                    <a href="<%=request.getContextPath()%>/Front_end/ComTra/ListAllComTra.jsp" class="list-group-item menua">我的最愛</a><br>
                    <a href="#" class="list-group-item menua active">實景預覽</a><br>
                    <a href="<%=request.getContextPath()%>/Front_end/mall/mallIndexAJAX.jsp" class="list-group-item menua">商城專區</a><br>
                </ul>

																				
                <a href="<%=request.getContextPath()%>/Front_end/mem/listOneMem.jsp"  class="btn btn-block btn-default">查看個人資料</a>
            </div>
           <div class="col-md-8 col-offset-1">
            
			<div class='row'>
				<div class='col-sm-12 col-xs-12'>
				<form action='<%=request.getContextPath()%>/album/preview.do'
				method="post" enctype="multipart/form-data" id='overlayImageForm'>
					<input type='submit' value='儲存' id='submitBtn' class='btn btn-info'>
					<input type='hidden' name='action' value='storeImage'>
				</form>
				</div>
			</div>
			<div class='row'>
				<div class='col-sm-12 col-xs-12'>
				<div class='text-center'>
					<img class='img-thumbnail' src='<%=request.getContextPath()%>/ShowPictureServletDAO?cont_no=<%=mergeCont_no%>'>
				</div>
					
				</div>
			</div>
	</div>
</div>


<%@ include file="page/preview_footer.file"%>