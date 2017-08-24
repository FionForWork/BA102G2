<%@page import="com.forum_comment.model.Forum_CommentVO"%>
<%@page import="com.forum_comment.model.Forum_Comment_Service"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	List<ArticleVO> articlelist = (List<ArticleVO>) session.getAttribute("articlelist");
%>

<%
	Article_Service artSvc = new Article_Service();
	List<ArticleVO> list = artSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<%
	int rowsPerPage = 5; //每頁的筆數    
	int rowNumber = 0; //總筆數
	int pageNumber = 0; //總頁數      
	int whichPage = 1; //第幾頁
	int pageIndexArray[] = null;
	int pageIndex = 0;
%>
   
<%
	if (articlelist != null) {
		rowNumber = articlelist.size();
		if (rowNumber % rowsPerPage != 0)
			pageNumber = rowNumber / rowsPerPage + 1;
		else
			pageNumber = rowNumber / rowsPerPage;

		pageIndexArray = new int[pageNumber];
		for (int i = 1; i <= pageIndexArray.length; i++)
			pageIndexArray[i - 1] = i * rowsPerPage - rowsPerPage;
	
	} else {
		rowNumber = list.size();
		if (rowNumber % rowsPerPage != 0)
			pageNumber = rowNumber / rowsPerPage + 1;
		else
			pageNumber = rowNumber / rowsPerPage;

		pageIndexArray = new int[pageNumber];
		for (int i = 1; i <= pageIndexArray.length; i++)
			pageIndexArray[i - 1] = i * rowsPerPage - rowsPerPage;

	}


%>


<%
	try {
		whichPage = Integer.parseInt(request.getParameter("whichPage"));
		pageIndex = pageIndexArray[whichPage - 1];
	} catch (NumberFormatException e) { //第一次執行的時候
		whichPage = 1;
		pageIndex = 0;
	} catch (ArrayIndexOutOfBoundsException e) { //總頁數之外的錯誤頁數
		if (pageNumber > 0) {
			whichPage = pageNumber;
			pageIndex = pageIndexArray[pageNumber - 1];
		}
	}

%>



<jsp:useBean id="forum_comment_Svc" scope="page" class="com.forum_comment.model.Forum_Comment_Service" />
<jsp:useBean id="com_Svc" scope="page" class="com.com.model.ComService" />
<jsp:useBean id="mem_Svc" scope="page" class="com.mem.model.MemService" />

<%@ include file="page/article_header.file"%>
<!-- ********************************************************************************* -->
<div class="col-md-8">
	<!--    ********************************************************************************* -->
	<div class="container-fluid">
		<br>
		<ul class="nav nav-pills" style="padding-left: 8cm; font-size: 1.5em">
			<li><a
				href="<%=request.getContextPath()%>/Article/Article.do?action=All">ALL</a></li>
			<li><a
				href="<%=request.getContextPath()%>/Article/Article.do?action=OneAll&art_type_no=10">婚紗</a></li>
			<li><a
				href="<%=request.getContextPath()%>/Article/Article.do?action=OneAll&art_type_no=20">婚秘</a></li>
			<li><a
				href="<%=request.getContextPath()%>/Article/Article.do?action=OneAll&art_type_no=30">婚攝</a></li>


			<!-- 		**************************************************************************************************** -->




			<div class="text-center" style="float: right">
				<a
					href="<%=request.getContextPath()%>/Front_end/Article/Article_add.jsp">
					<button class="btn btn-info">發表文章</button>
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

							<c:forEach var="ArticleVO" items="${articlelist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr>
									<td><span class="subjects-text">
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/Article/Article.do">

												<a href="/BA102G2/Front_end/Article/Discuss.jsp?art_no=${ArticleVO.art_no }">${ArticleVO.title }</a>


												<input type="hidden" name="action" value="getOnedata">
												<input type="hidden" name="art_no"
													value="${ArticleVO.art_no}">
											</FORM>
									</span></td>

									<td class="author"><c:choose>
											<c:when
												test="${com_Svc.getOneCom(ArticleVO.poster_no)!=null}">
												<p>${com_Svc.getOneCom(ArticleVO.poster_no).name }</p>
											</c:when>
											<c:otherwise>
												<p>${mem_Svc.getOneMem(ArticleVO.poster_no).name }</p>
											</c:otherwise>
										</c:choose></td>
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





							<c:forEach var="ArticleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr>
									<td><span class="subjects-text">
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/Article/Article.do">

												<a
													href="/BA102G2/Front_end/Article/Discuss.jsp?art_no=${ArticleVO.art_no }">${ArticleVO.title }</a>


												<input type="hidden" name="action" value="getOnedata">
												<input type="hidden" name="art_no"
													value="${ArticleVO.art_no}">
											</FORM>
									</span></td>

									<td class="author"><c:choose>
											<c:when
												test="${com_Svc.getOneCom(ArticleVO.poster_no)!=null}">
												<p>${com_Svc.getOneCom(ArticleVO.poster_no).name }</p>
											</c:when>
											<c:otherwise>
												<p>${mem_Svc.getOneMem(ArticleVO.poster_no).name }</p>
											</c:otherwise>
										</c:choose></td>
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
<div align="center">

   
 <tr>
    <div align="center">
    <ul class="pagination">
    <%for (int i=1; i<=pageNumber; i++){%>
           <li class="active"><a href="<%=request.getRequestURI()%>?whichPage=<%=i%>"><%=i%></a></li>
        <%}%> 
  	</ul>
  	</div>
 </tr>
					
</div>
						
							

		</div>
	</div>
</div>


<%@ include file="page/article_footer.file"%>