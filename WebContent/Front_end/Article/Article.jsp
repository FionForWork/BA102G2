<%@page import="com.forum_comment.model.Forum_CommentVO"%>
<%@page import="com.forum_comment.model.Forum_Comment_Service"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%List<ArticleVO>articlelist=(List<ArticleVO>)session.getAttribute("articlelist"); %>

<%
	Article_Service artSvc = new Article_Service();
	List<ArticleVO> list = artSvc.getAll();
	pageContext.setAttribute("list", list);
	

	
%>

<jsp:useBean id="forum_comment_Svc" scope="page"
	class="com.forum_comment.model.Forum_Comment_Service" />

<%@ include file="page/article_header.file"%>
<!-- ********************************************************************************* -->
<div class="col-md-8">
	<!--    ********************************************************************************* -->
	<div class="container-fluid">
		<br>
		<ul class="nav nav-pills" style="padding-left: 8cm ;font-size: 1.5em">
			<li ><a href="<%=request.getContextPath()%>/Article/Article.do?action=All">ALL</a></li>
			<li><a  href="<%=request.getContextPath()%>/Article/Article.do?action=OneAll&art_type_no=10">婚紗</a></li>
			<li><a  href="<%=request.getContextPath()%>/Article/Article.do?action=OneAll&art_type_no=20">婚秘</a></li>
			<li><a  href="<%=request.getContextPath()%>/Article/Article.do?action=OneAll&art_type_no=30">婚攝</a></li>


			<!-- 		**************************************************************************************************** -->




			<div class="text-center" style="float: right">
				<a
					href="<%=request.getContextPath()%>/Front_end/Article/Article_add.jsp">
					<button class="btn btn-info" >發表文章
					</button>
				</a>
			</div>
		</ul>
	</div>

	
	<!-- ********************************************************************************************** -->
	<div>
		<div class="tablelist">
<c:choose>
<c:when test="${articlelist!=null }">
		<div id="" class="tab-pane fade in active">
			<table
				class="table table-hover table-striped table-condensed table-bordered  text-center">
				<thead class="thead text-center">
					<tr style="">
						<td width="30%" class="subject">主題</td>
						<td width="10%" class="authur">作者</td>
						<td width="10%" class="qty">回覆</td>
						<td width="20%" class="time">發佈時間</td>
						<td width="25%" class="latestpost">最新回覆</td>
					</tr>
				</thead>


				


				<c:forEach var="ArticleVO" items="${articlelist}">
					<tr>
						<td><span class="subjects-text">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/Article/Article.do">

									<a
										href="/BA102G2/Front_end/Article/Discuss.jsp?art_no=${ArticleVO.art_no }">${ArticleVO.title }</a>
									

									<input type="hidden" name="action" value="getOnedata">
									<input type="hidden" name="art_no" value="${ArticleVO.art_no}">
								</FORM>
						</span></td>

						<td class="author">
							<p>${ArticleVO.poster_no}</p>
						</td>
						<td class="qty">
							<p>${forum_comment_Svc.getArt_no_All(ArticleVO.art_no).art_no }</p>
						</td>
						<td class="release_time"><p>${ArticleVO.art_date }</p></td>

						

						<td class="reply_time"><p>
								<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm:ss"
									value="${forum_comment_Svc.getOneAll(ArticleVO.art_no).fmc_date}" />
							</p></td>
					</tr>
				</c:forEach>
			</table>
			</div>
			</c:when>
			
			
			
			
			
	<c:otherwise>	
			<div id="" class="tab-pane fade in active">
			<table
				class="table table-hover table-striped table-condensed table-bordered  text-center">
				<thead class="thead text-center">
					<tr style="">
						<td width="30%" class="subject">主題</td>
						<td width="10%" class="authur">作者</td>
						<td width="10%" class="qty">回覆</td>
						<td width="20%" class="time">發佈時間</td>
						<td width="25%" class="latestpost">最新回覆</td>
					</tr>
				</thead>


				


				<c:forEach var="ArticleVO" items="${list}">
					<tr>
						<td><span class="subjects-text">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/Article/Article.do">

									<a
										href="/BA102G2/Front_end/Article/Discuss.jsp?art_no=${ArticleVO.art_no }">${ArticleVO.title }</a>
									

									<input type="hidden" name="action" value="getOnedata">
									<input type="hidden" name="art_no" value="${ArticleVO.art_no}">
								</FORM>
						</span></td>

						<td class="author">
							<p>${ArticleVO.poster_no}</p>
						</td>
						<td class="qty">
							<p>${forum_comment_Svc.getArt_no_All(ArticleVO.art_no).art_no }</p>
						</td>
						<td class="release_time"><p>${ArticleVO.art_date }</p></td>

						

						<td class="reply_time"><p>
								<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm:ss"
									value="${forum_comment_Svc.getOneAll(ArticleVO.art_no).fmc_date}" />
							</p></td>
					</tr>
				</c:forEach>
			</table>
			</div>
			</c:otherwise>
</c:choose>

	












		</div>
	</div>
</div>


<%@ include file="page/article_footer.file"%>