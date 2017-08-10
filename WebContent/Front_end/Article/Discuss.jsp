<%@page import="com.com.model.ComVO"%>
<%@page import="com.mem.model.MemService"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.forum_comment.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");  %>
<% ComVO comVO= (ComVO) session.getAttribute("comVO"); %>
<% MemVO memVO= (MemVO) session.getAttribute("memVO"); %>
<%	
// 	String art_no=request.getParameter("art_no");
	Integer art_no = new Integer(request.getParameter("art_no"));
	 
	String mem_no = request.getParameter("mem_no");
// 	Integer artno=Integer.valueOf(art_no).intValue(); 
	
	Article_Service artSvc=new Article_Service();
    ArticleVO article=artSvc.getOneArt(art_no);
   	pageContext.setAttribute("article",article);
   	
   	MemService memSvc=new MemService();
   	MemVO mem=memSvc.getOneMem(mem_no);
   	
   %>
<%
	Forum_Comment_Service forum_Comment_Svc=new Forum_Comment_Service();
	   List<Forum_CommentVO> list=forum_Comment_Svc.getAll();
	   pageContext.setAttribute("list", list);
	   	
%>   
   

<%@ include file="page/discuss_header.file"%>
<div class="col-xs-12 col-sm-10">
                       
                            <div class="text-center" style="float:right" >
                        <form METHOD="post" ACTION="<%=request.getContextPath()%>/Forum_comment/Forum_commentServlet.do" name="form1"> 
                            <input type="hidden" name="action" value="getOne_For_Update">
                             <input type="hidden" name="art_no" value="${article.art_no }">           
  							 <input type="submit" class="btn btn-info" data-dismiss="modal" value="回覆文章">
                             </div>  
                             
                             </form>                   
        
                            <div class="panel panel-default">
                            <img src="<%=request.getContextPath()%>/ShowPictureServletDAO?mem_no="${mem.mem_no }" class="pull-left xxx" style="width:130px">
                              <div class="panel-heading" style="height: 100px;font-size: 20px">
                               <div class="name">${article.art_no }</div>
                               <div class="date">${article.art_date }</div>
                               <div class="text"></div>  
                               
                               </div>
                              
                              <div class="panel-body">
                                ${article.content }
                              </div>
                            </div>
<!--                             ********************************************************************* -->
 <c:forEach var="forum_CommentVO" items="${list}" >
 <c:if test="${forum_CommentVO.art_no== article.art_no }">
                            <div class="panel panel-default">
                            <img src="<%=request.getContextPath()%>/Front_end/Article/img/img1.jpeg" class="pull-left xxx" style="width:100px">
                              <div class="panel-heading" style="height: 100px;font-size: 20px">
                               <div class="name">${forum_CommentVO.speaker_no }</div>
                               
<%--                                <div class="date">${forum_CommentVO.fmc_date }</div> --%>

<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm:ss" value="${forum_CommentVO.fmc_date }"/>
                               <div class="text"></div>  
                               
                               </div>
                              
                              <div class="panel-body">
                                ${forum_CommentVO.cont }
                              </div>
                            </div>
   </c:if>
   </c:forEach>
                            
                        </div>









<%@ include file="page/discuss_footer.file"%>