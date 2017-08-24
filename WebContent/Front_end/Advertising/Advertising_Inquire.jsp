<%@page import="com.advertising.model.AdvertisingVO"%>
<%@page import="com.advertising.model.AdvertisingService"%>
<%@page import="com.advertising.controller.AdvertisingServlet"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="page/advertising_header.file"%>
<%
	String adv_no = request.getParameter("adv_no");
	AdvertisingService advSvc = new AdvertisingService();
	AdvertisingVO advertisingVO = advSvc.getOneAdvertising(adv_no);
	session.setAttribute("advertisingVO", advertisingVO);
%>


<div class="col-md-8 col-offset-1">
	<div class="container-fluid">
		<div class="modal-body">
			<label for="inputdefault">標題:</label>
			<p>${advertisingVO.title}</p>
		</div>
		<div class="modal-body">
			<label for="inputdefault">內容:</label>
			<p>${advertisingVO.text }</p>
		</div>
		<div class="modal-body">
			<label for="inputdefault">圖片:</label>
			<p>
				<img
					src="<%=request.getContextPath()%>/ShowPictureServletDAO?adv_no=${advertisingVO.adv_no}" class="pull-left xxx"  >

			</p>

		</div>

	</div>
	<br>
	<div style="float: right; bottom: 0;">
		<input type="submit" class="btn  btn-danger " value="返回"
			onclick="history.back()">
	</div>

</div>


<style type="text/css">
img
{
max-width: 500px
}
</style>
















<%@ include file="page/advertising_footer.file"%>