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
<%
//    MemService memService=new MemService();
// MemVO memVO=memService.getOneMem("1003");
// session.setAttribute("memVO", memVO);
session.getAttribute("memVO");

%>
<%
// ComService comService=new ComService();
// ComVO comVO=comService.getOneCom("2001");
//  session.setAttribute("comVO", comVO);
session.getAttribute("comVO");

%>

<form METHOD="post"
	ACTION="<%=request.getContextPath()%>/Article/Article.do" name="form1">
	<div class="container-fluid">
		<h2>新增文章</h2>
		<p></p>
	<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

		<div class="form-group">
			<label for="inputdefault">主題</label> <input class="form-control"
				id="inputdefault" type="text" name="title"
				value="<%=(articleVO == null) ? " " : articleVO.getTitle()%>">
		</div>

		<jsp:useBean id="art_type_Svc" scope="page" class="com.art_type.model.Art_Type_Service" />
		<div class="form-group">
			<label for="sel1">類型</label> 
			<select class="form-control" id="sel1" name="art_type_no">
				<c:forEach var="art_typeVO" items="${art_type_Svc.all}">
					<option value="${art_typeVO.art_type_no }">
						${art_typeVO.type }</option>
				</c:forEach>
			</select>
		</div>

		<div class="form-group">
			<label for="inputlg">文章</label>
			<textarea class="form-control" id="comment" name="content"
				value="<%=(articleVO == null) ? " " : articleVO.getContent()%>"></textarea>
		</div>


	</div>

	<div class="container" style="display: block;">
		<input type="hidden" name="action" value="insert"> 
		<input type="hidden" name="poster_no" value="${(comVO.com_no!=null)?comVO.com_no:memVO.mem_no}"> <input
			type="submit" class="btn btn-info" data-dismiss="modal" value="發佈">
</form>
<input type="submit" class="btn  btn-danger "
	value="返回" onclick="history.back()">
</div>



<%@ include file="page/add_footer.file"%>