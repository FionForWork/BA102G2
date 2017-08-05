<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.forum_comment.model.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");  %>
<%	
// 	String art_no=request.getParameter("art_no");
	Integer art_no = new Integer(request.getParameter("art_no"));
// 	Integer artno=Integer.valueOf(art_no).intValue(); 
	
	Article_Service artSvc=new Article_Service();
    ArticleVO article=artSvc.getOneArt(art_no);
   	pageContext.setAttribute("article",article);
   	
   %>
<%@ include file="page/discuss_header.file"%>
<div class="col-xs-12 col-sm-10">
                       
                            <div class="text-center" style="float:right" >
                                <a href="reply.html">
        <button class="btn btn-info" style="font-size: 15px"  onclick="location.href='reply.html'">¦^ÂÐ¤å³¹
                                    </button></a>
        </div> 
                            <div class="panel panel-default">
                            <img src="<%=request.getContextPath()%>/Front_end/Article/img/img1.jpeg" class="pull-left xxx" style="width:100px">
                              <div class="panel-heading" style="height: 100px;font-size: 20px">
                               <div class="name">${article.art_no }</div>
                               <div class="date">${article.art_date }</div>
                               <div class="text"></div>  
                               
                               </div>
                              
                              <div class="panel-body">
                                ${article.content }
                              </div>
                            </div>
                        </div>









<%@ include file="page/discuss_footer.file"%>