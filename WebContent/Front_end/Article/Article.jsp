<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	Article_Service artSvc = new Article_Service();
	List<ArticleVO> list = artSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<%@ include file="page/article_header.file"%>
<c:forEach var="ArticleVO" items="${list}">
	<tr>
		<td><span class="subjects-text">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/Article/Article.do">

					<a
						href="/BA102G2/Front_end/Article/Discuss.jsp?art_no=${ArticleVO.art_no }">${ArticleVO.title }</a>
					<!--                   <input  type="submit" > -->

					<input type="hidden" name="action" value="getOnedata"> <input
						type="hidden" name="art_no" value="${ArticleVO.art_no}">
				</FORM>
		</span></td>

		<td class="author">
			<p>${ArticleVO.poster_no}</p>
		</td>
		<td class="qty">
			<p>1</p>
		</td>
		<td class="release_time"><p>${ArticleVO.art_date }</p></td>
		<td class="reply_time"><p>2017-07-14 07:56</p>
			</a></td>
	</tr>
</c:forEach>

<%@ include file="page/article_footer.file"%>