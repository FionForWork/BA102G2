<%@page import="com.com.model.ComService"%>
<%@page import="com.com.model.ComVO"%>
<%@page import="com.mem.model.MemService"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.forum_comment.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="com_Svc1" scope="page" class="com.com.model.ComService" />
<jsp:useBean id="mem_Svc1" scope="page" class="com.mem.model.MemService" />


<%
	ComVO comVO = (ComVO) session.getAttribute("comVO");
%>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
%>

<%
	Integer art_no = new Integer(request.getParameter("art_no"));
	Article_Service artSvc = new Article_Service();
	ArticleVO article = artSvc.getOneArt(art_no);
	session.setAttribute("article", article);
%>
<%
	ComService com_Svc = new ComService();
	MemService mem_Svc = new MemService();
	// 	mem_Svc.getOneMem(String.valueOf(article.getPoster_no())).getName();
	// 	com_Svc.getOneCom(String.valueOf(article.getPoster_no())).getName();
	// 	System.out.println(mem_Svc.getOneMem(String.valueOf(article.getPoster_no())).getName());	
	String name = ((mem_Svc.getOneMem(String.valueOf(article.getPoster_no()))) != null)
			? mem_Svc.getOneMem(String.valueOf(article.getPoster_no())).getName()
			: com_Svc.getOneCom(String.valueOf(article.getPoster_no())).getName();
	pageContext.setAttribute("name", name);
%>

<%
	Forum_Comment_Service forum_Comment_Svc = new Forum_Comment_Service();
	List<Forum_CommentVO> list = forum_Comment_Svc.getOne_art_no(art_no);
	pageContext.setAttribute("list", list);
%>
<%
	int rowsPerPage = 5; // 每頁的筆數
	int rowsNumber = 0; // 總筆數
	int pageNumber = 0; // 總頁數
	int whichPage = 1; // 當前頁數
	int pageIndexArray[] = null;
	int pageIndex = 0; // 開始資料筆數
%>
<%
	rowsNumber = list.size();
	if (rowsNumber % rowsPerPage != 0)
		pageNumber = rowsNumber / rowsPerPage + 1;
	else
		pageNumber = rowsNumber / rowsPerPage;

	pageIndexArray = new int[pageNumber];
	for (int i = 1; i <= pageIndexArray.length; i++)
		pageIndexArray[i - 1] = i * rowsPerPage - rowsPerPage;
%>
<%
	try {
		whichPage = Integer.parseInt(request.getParameter("whichPage"));
		pageIndex = pageIndexArray[whichPage - 1];
	} catch (NumberFormatException e) {
		whichPage = 1;
		pageIndex = 0;
	}
%>

<%@ include file="page/discuss_header.file"%>

<div class="col-md-8">
	<div>
		<div class="aa" text-align:center>
			<H3>主題：${article.title }</H3>
		</div>
	</div>
	<div class="container">
		<div class="row">

			<div class="col-xs-12 col-sm-11">
				<div class="row">


					<div class="col-xs-12 col-sm-10">

						<div class="text-center" style="float: right">
						<c:if test="${memVO.mem_no!=null||comVO.com_no!=null }">
							<form METHOD="post"
								ACTION="<%=request.getContextPath()%>/Forum_comment/Forum_commentServlet.do"
								name="form1">
								
									<input type="hidden" name="action" value="getReply_message">
									<input type="hidden" name="art_no" value="${article.art_no }">
									<input type="submit" class="btn btn-info" data-dismiss="modal"
										value="回覆文章">
							</form>
							</c:if>
						</div>
						<c:if test="${((memVO.mem_no!=null)&&(article.poster_no!=memVO.mem_no))||((comVO.com_no!=null)&&(article.poster_no!=comVO.com_no)) }">
						<div class="text-center" style="float: right">
						<input type="submit" class="btn btn-danger" data-toggle="modal" onclick="showmodal('article',${article.art_no},${article.poster_no},'/Front_end/Article/Discuss.jsp?art_no='+${article.art_no});" data-target="#myModal" value="檢舉">
						</div>
						</c:if>
						
						 <div class="modal fade" id="myModal" role="dialog">
    						
    					</div>
						<c:choose>
							<c:when test="${(memVO.mem_no)==(article.poster_no)}">
								<div class="text-center" style="float: right">
									<form METHOD="post"
										ACTION="<%=request.getContextPath()%>/Article/Article.do"
										name="form1">
										<input type="hidden" name="action" value="getOne_For_Update">
										<input type="hidden" name="art_no" value="${article.art_no }">
										<input type="submit" class="btn btn-info" data-dismiss="modal"
											value="編輯文章">
									</form>
								</div>
								<div class="text-center" style="float: right">
									<form METHOD="post"
										ACTION="<%=request.getContextPath()%>/Article/Article.do"
										name="form1">
										<input type="hidden" name="action" value="delete"> <input
											type="hidden" name="art_no" value="${article.art_no }">
										<input type="submit" class="btn btn-danger"
											data-dismiss="modal" value="刪除">
									</form>
								</div>
							</c:when>
							<c:when test="${(comVO.com_no)==(article.poster_no)}">
								<div class="text-center" style="float: right">
									<form METHOD="post"
										ACTION="<%=request.getContextPath()%>/Article/Article.do"
										name="form1">
										<input type="hidden" name="action" value="getOne_For_Update">
										<input type="hidden" name="art_no" value="${article.art_no }">
										<input type="submit" class="btn btn-info" data-dismiss="modal"
											value="編輯文章">
									</form>
								</div>
								<div class="text-center" style="float: right">
									<form METHOD="post"
										ACTION="<%=request.getContextPath()%>/Article/Article.do"
										name="form1">
										<input type="hidden" name="action" value="delete"> <input
											type="hidden" name="art_no" value="${article.art_no }">
										<input type="submit" class="btn btn-danger"
											data-dismiss="modal" value="刪除">
									</form>
								</div>
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>




						<div class="panel panel-default">

							<img
								src="<%=request.getContextPath()%>/ShowPictureServletDAO?all=${article.poster_no}"
								height="100px" width="130px" class="pull-left xxx">

							<div class="panel-heading" style="height: 100px; font-size: 20px">
								<div class="name"></div>

								<div class="name">${name }</div>
								<div class="date">${article.art_date }</div>
								<div class="text"></div>

							</div>

							<div class="panel-body">${article.content }</div>
						</div>




						<!--  ********************************************************************* -->
						<c:forEach var="forum_CommentVO" items="${list}"
							begin="<%=pageIndex %>" end="<%=pageIndex+rowsPerPage-1 %>">
							<c:if test="${forum_CommentVO.art_no== article.art_no }">
								
								
						<c:if test="${((memVO.mem_no!=null)&&(article.poster_no!=memVO.mem_no))||((comVO.com_no!=null)&&(article.poster_no!=comVO.com_no)) }">	
						<div class="text-center" style="float: right">
						<input type="submit" class="btn btn-danger" data-toggle="modal" onclick="showmodal('forum',${forum_CommentVO.fmc_no},${forum_CommentVO.speaker_no},'/Front_end/Article/Discuss.jsp?art_no='+${article.art_no});" data-target="#myModal" value="檢舉">
						</div>
						 <div class="modal fade" id="myModal" role="dialog">
    					</div>
    					</c:if>

								<c:choose>
									<c:when test="${(memVO.mem_no)==(forum_CommentVO.speaker_no)}">
										<div class="text-center" style="float: right">
											<form METHOD="post"
												ACTION="<%=request.getContextPath()%>/Forum_comment/Forum_commentServlet.do"
												name="form1">
												<input type="hidden" name="action" value="getOne_For_Update">
												<input type="hidden" name="fmc_no"
													value="${forum_CommentVO.fmc_no }"> <input
													type="submit" class="btn btn-info" data-dismiss="modal"
													value="編輯留言">
											</form>
										</div>
										<div class="text-center" style="float: right">
											<form METHOD="post"
												ACTION="<%=request.getContextPath()%>/Forum_comment/Forum_commentServlet.do"
												name="form1">
												<input type="hidden" name="action" value="delete"> <input
													type="hidden" name="fmc_no"
													value="${forum_CommentVO.fmc_no }"> <input
													type="hidden" name="art_no"
													value="${forum_CommentVO.art_no }"> <input
													type="submit" class="btn btn-danger" data-dismiss="modal"
													value="刪除">
											</form>
										</div>
									</c:when>
									<c:when test="${(comVO.com_no)==(forum_CommentVO.speaker_no)}">
										<div class="text-center" style="float: right">
											<form METHOD="post"
												ACTION="<%=request.getContextPath()%>/Forum_comment/Forum_commentServlet.do"
												name="form1">
												<input type="hidden" name="action" value="getOne_For_Update">
												<input type="hidden" name="fmc_no"
													value="${forum_CommentVO.fmc_no }"> <input
													type="submit" class="btn btn-info" data-dismiss="modal"
													value="編輯文章">
											</form>
										</div>
										<div class="text-center" style="float: right">
											<form METHOD="post"
												ACTION="<%=request.getContextPath()%>/Forum_comment/Forum_commentServlet.do"
												name="form1">
												<input type="hidden" name="action" value="delete"> <input
													type="hidden" name="fmc_no"
													value="${forum_CommentVO.fmc_no }"> <input
													type="hidden" name="art_no"
													value="${forum_CommentVO.art_no }"> <input
													type="submit" class="btn btn-danger" data-dismiss="modal"
													value="刪除">
											</form>
										</div>
									</c:when>
									<c:otherwise></c:otherwise>
								</c:choose>



								<div class="panel panel-default">
									<img
										src="<%=request.getContextPath()%>/ShowPictureServletDAO?all=${forum_CommentVO.speaker_no }"
										height="100px" width="130px" class="pull-left xxx">
									<div class="panel-heading"
										style="height: 100px; font-size: 20px">
										<c:choose>
											<c:when
												test="${com_Svc1.getOneCom(forum_CommentVO.speaker_no)!=null }">
												<div class="name">${com_Svc1.getOneCom(forum_CommentVO.speaker_no).name }</div>
											</c:when>
											<c:otherwise>
												<div class="name">${mem_Svc1.getOneMem(forum_CommentVO.speaker_no).name }</div>
											</c:otherwise>
										</c:choose>

										<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm:ss"
											value="${forum_CommentVO.fmc_date }" />
										<div class="text"></div>

									</div>







									<div class="panel-body">${forum_CommentVO.cont }</div>
								</div>
							</c:if>
						</c:forEach>
						<div align="center">

							<%
								if (rowsPerPage < rowsNumber) {
							%>
							<%
								if (pageIndex >= rowsPerPage) {
							%>
							<td><A
								href="<%=request.getRequestURI()%>?art_no=${article.art_no }&whichPage=1">至第一頁</A>&nbsp;</td>
							<td><A
								href="<%=request.getRequestURI()%>?art_no=${article.art_no }&whichPage=<%=whichPage - 1%>">上一頁
							</A>&nbsp;</td>
							<%
								}
							%>

							<%
								if (pageIndex < pageIndexArray[pageNumber - 1]) {
							%>
							<td><A
								href="<%=request.getRequestURI()%>?art_no=${article.art_no }&whichPage=<%=whichPage + 1%>">下一頁
							</A>&nbsp;</td>
							<td><A
								href="<%=request.getRequestURI()%>?art_no=${article.art_no }&whichPage=<%=pageNumber%>">至最後一頁</A>&nbsp;</td>
							<%
								}
							%>
							<%
								}
							%>
							<td><A href="/BA102G2/Front_end/Article/Article.jsp">返回</A></td>
						</div>
					</div>


				</div>

			</div>

		</div>

	</div>

</div>


<script type="text/javascript">
function showmodal(action,rep_ob_no,reported_no,position){
	if(action=='article'){
		$('#myModal').load("<%=request.getContextPath()%>/Front_end/Report/Report.jsp?",{"rep_ob_no":rep_ob_no,"reported_no":reported_no,"position":position},
		function(){$("#myModal").modal('show');}
		)}
	if(action=='forum'){
		$('#myModal').load("<%=request.getContextPath()%>/Front_end/Report/Report.jsp?",{"rep_ob_no":rep_ob_no,"reported_no":reported_no,"position":position},
		function(){$("#myModal").modal('show');}
		)}
	
	
};



</script>





<%@ include file="page/discuss_footer.file"%>