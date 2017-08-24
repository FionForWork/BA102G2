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
ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
%>




	<form METHOD="post" ACTION="<%=request.getContextPath()%>/Forum_comment/Forum_commentServlet.do" name="form1">
	<div class="container-fluid">
		<h2>回覆文章</h2>
		
		<p></p>

		

		<jsp:useBean id="art_type_Svc" scope="page" class="com.art_type.model.Art_Type_Service" />
			
		
		<div class="form-group">
			<label for="inputlg">主題：</label>
			<span class="item-text"><%=articleVO.getTitle() %></span> 
		</div>
		
		<div class="form-group">
			<label for="inputlg">留言:</label>
			<textarea  rows="20" cols="77" id="comment" name="cont" value="<%= (forum_CommentVO==null)? " " : forum_CommentVO.getCont()%>">
			</textarea>
		</div>


	</div>

	<div class="button_body">
<div class="container" style="display: block;">
	<input type="hidden" name="action" value="insert">
     <input type="hidden" name="art_no" value="<%=articleVO.getArt_no() %>"> 
<%--      <input type="hidden" name="speaker_no" value="${comVO.com_no }">  --%>
     <c:choose>
     <c:when test="${(comVO.com_no)!=null }">
     <input type="hidden" name="speaker_no" value="${comVO.com_no }">   
     </c:when>
     <c:when test="${(memVO.mem_no)!=null}">
     <input type="hidden" name="speaker_no" value="${memVO.mem_no }">   
     </c:when>    
     <c:otherwise></c:otherwise>
     </c:choose>                               
   <input type="submit" class="btn btn-info" data-dismiss="modal" value="新增">

</form>
<input type="submit" class="btn btn-default btn-danger btn-primary" value="返回" onclick="history.back()">
</div>
</div>



<%@ include file="page/add_footer.file"%>