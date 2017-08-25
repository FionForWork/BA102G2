<%@page import="com.com.model.ComVO"%>
<%@page import="com.com.model.ComService"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.mem.model.MemService"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="page/add_header.file"%>
<%ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO"); %>



<form METHOD="post"
	ACTION="<%=request.getContextPath()%>/Article/Article.do" name="form1">
	<div class="container-fluid">
		<h2>更新文章</h2>
		<p></p>

		<div class="form-group">
			<label for="inputdefault">主題</label> <input class="form-control"
				id="inputdefault" type="text" name="title"
				value="<%=(articleVO == null) ? " " : articleVO.getTitle()%>">
		</div>

		<jsp:useBean id="art_type_Svc" scope="page" class="com.art_type.model.Art_Type_Service" />
			
		<div class="form-group">
			<label for="sel1">類型</label> <select class="form-control" id="sel1"
				name="art_type_no">
				<c:forEach var="art_typeVO" items="${art_type_Svc.all}">
				
					<option value="${art_typeVO.art_type_no }" ${(art_typeVO.art_type_no==articleVO.art_type_no)?'selected':''}>
						${art_typeVO.type }</option>
				</c:forEach>
			</select>
		</div>

		<div class="form-group">
			<label for="inputlg">文章</label>
			<textarea class="form-control" id="comment" name="content"
				value=""><%=articleVO.getContent() %></textarea>
		</div>


	</div>

	<div class="container" style="display: block;">
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="poster_no" value="${articleVO.poster_no }"> 
		<input type="hidden" name="art_no" value="${articleVO.art_no }">
		<input type="submit" class="btn btn-info" data-dismiss="modal" value="更新">
</form>
<input type="submit" class="btn btn-default btn-danger btn-primary"
	value="返回" onclick="history.back()">
</div>



<%@ include file="page/add_footer.file"%>