<%@page import="com.forum_comment.model.Forum_CommentVO"%>
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
<%
Forum_CommentVO forum_CommentVO= (Forum_CommentVO) session.getAttribute("forum_commentVO"); 
// pageContext.setAttribute("forum_CommentVO", forum_CommentVO);
%>



<form METHOD="post"
	ACTION="<%=request.getContextPath()%>/Forum_comment/Forum_commentServlet.do" name="form1">
	<div class="container-fluid">
		<h2>更新留言</h2>
		
		<p></p>

		

		<jsp:useBean id="art_type_Svc" scope="page" class="com.art_type.model.Art_Type_Service" />
			
		

		<div class="form-group">
			<label for="inputlg">更新留言</label>
			<textarea class="form-control" id="comment" name="cont"
				value="">${forum_CommentVO.cont}</textarea>
		</div>


	</div>

	<div class="container" style="display: block;">
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="art_no" value="${forum_commentVO.art_no }"> 
		<input type="hidden" name="fmc_no" value="${forum_commentVO.fmc_no }"> 
		<input type="hidden" name="speaker_no" value="${forum_commentVO.speaker_no}"> 
		
		<input type="submit" class="btn btn-info" data-dismiss="modal" value="更新">
</form>
<input type="submit" class="btn btn-default btn-danger btn-primary"
	value="返回" onclick="history.back()">
</div>



<%@ include file="page/add_footer.file"%>