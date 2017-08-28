<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*" %>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<%
		//String mem_no = (String) session.getAttribute("mem_no");
		//session.setAttribute("mem_no","1001");
		MemVO memVO = (MemVO)session.getAttribute("memVO");  
		System.out.println("MemVO"+memVO);
		String mergeCont_no = (String)request.getAttribute("mergeCont_no");
		//String mergeCont_no ="0357";
	%>
	
<%@ include file="page/preview_header.file"%>
           <div class="col-md-8 col-offset-1">
            	
		<ul id="wizardStatus">
  <li class="current">1. 裁切去背</li>
  <li class="current">2. 合成照片</li>
  <li class="current">3. 完成啦!!!</li>
  
</ul>
			<div class='row'>
				<div class='col-sm-8 col-xs-8 col-sm-offset-2'>
					<h2>合成的照片已經匯入您的私人相簿囉~</h2>
					
				</div>
			</div>
			<br>
			<div class='row'>
				<div class='col-sm-12 col-xs-12'>
				<div class='text-center'>
					<img class='img-thumbnail' src='<%=request.getContextPath()%>/ShowPictureServletDAO?cont_no=<%=mergeCont_no%>'>
				</div>
					
				</div>
			</div>
			<br>
			<div class='row'>
			<div class='col-sm-8 col-xs-8 col-sm-offset-2'>
				<button class='btn btn-default btn-block' onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/Preview/ImageCropper.jsp'">再玩一次</button>
				<button class='btn btn-default btn-block' onclick="javascript:location.href='<%=request.getContextPath()%>/ShowPictureServletDAO?downloadCont_no=<%=mergeCont_no%>'">下載圖片</button>
			</div>	
			</div>
	</div>
</div>

<style>
.img-thumbnail {
    border: 1px solid #ddd;
    padding: 7px;
}

</style>
<script>
var image = $(".img-thumbnail")[0];
console.log("image.height"+image.height);
console.log("image.width"+image.width);
</script>
<%@ include file="page/preview_footer.file"%>