<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.forum_comment.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="page/reply_header.file"%>

<%
ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
Forum_CommentVO forum_CommentVO = (Forum_CommentVO) request.getAttribute("forum_CommentVO"); 
// MemVO memVO = (MemVO) request.getAttribute("1001");
session.getAttribute("memVo");
session.getAttribute("comVo");
%>
<jsp:useBean id="article_Svc" scope="page" class="com.article.model.Article_Service" />


      <div class="col-md-8">
            <div class="col-md-10">
            <h2 align="center">發表回復</h2>
              <div class="message_body" >
<br>
<div>
<form METHOD="post" ACTION="<%=request.getContextPath()%>/Forum_comment/Forum_commentServlet.do" name="form1">
 
<label class="title">主題: </label>

<span class="item-text"><%=articleVO.getTitle() %></span> 


</div>
<br>
<div  >
留言:<br>
<textarea  rows="20" cols="77" id="comment" name="cont" value="<%= (forum_CommentVO==null)? " " : forum_CommentVO.getCont()%>">
</textarea>
<br>
</div>

<div class="button_body">
<div class="container" style="display: block;">
	<input type="hidden" name="action" value="insert">
     <input type="hidden" name="art_no" value="<%=articleVO.getArt_no() %>"> 
     <input type="hidden" name="speaker_no" value="${comVO.com_no }">                                  
   <input type="submit" class="btn btn-info" data-dismiss="modal" value="新增">

</form>
<input type="submit" class="btn btn-default btn-danger btn-primary" value="返回" onclick="history.back()">
</div>
</div>
            </div>
            <!--上面放東西===========================================================================-->
        </div>
    </div>


<%@ include file="page/reply_footer.file"%>