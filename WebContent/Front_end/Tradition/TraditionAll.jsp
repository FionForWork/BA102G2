<%@ page import="java.util.*"%>
<%@ page import="com.tradition.model.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	int rowsPerPage = 10; //每頁的筆數    
	int rowNumber = 0; //總筆數
	int pageNumber = 0; //總頁數      
	int whichPage = 1; //第幾頁
	int pageIndexArray[] = null;
	int pageIndex = 0;
%>



<%
	List<TraditionVO> traditionlist = (List<TraditionVO>) session.getAttribute("traditionlist");
%>

<%
	Tradition_Service traSvc = new Tradition_Service();
	List<TraditionVO> list = traSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<%
	if (traditionlist != null) {
		rowNumber = traditionlist.size();
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






<%@ include file="page/tradition_header.file"%>
<div class="col-md-10">
	<div id="dashboard-content">
		<div class="container-fluid">
			<br>
			<div class="btn-group btn-group-justified">
				<div class="btn-group">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Tradition/Tradition.do">
						<input type="submit" class="btn btn-info" value="ALL"> <input
							type="hidden" name="action" value="get_All">
					</FORM>
				</div>
				<div class="btn-group">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Tradition/Tradition.do">
						<input type="submit" class="btn btn-info" value="訂婚流程"> <input
							type="hidden" name="tra_type_no" value="10"> <input
							type="hidden" name="action" value="get_OneAll">
					</FORM>
				</div>
				<div class="btn-group">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Tradition/Tradition.do">
						<input type="submit" class="btn btn-info" value="結婚流程"> <input
							type="hidden" name="tra_type_no" value="20"> <input
							type="hidden" name="action" value="get_OneAll">
					</FORM>
				</div>
				<div class="btn-group">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Tradition/Tradition.do">
						<input type="submit" class="btn btn-info" value="禁忌、歸寧"> <input
							type="hidden" name="tra_type_no" value="30"> <input
							type="hidden" name="action" value="get_OneAll">
					</FORM>
				</div>
				<div class="btn-group">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Tradition/Tradition.do">
						<input type="submit" class="btn btn-info" value="六禮、十二禮">
						<input type="hidden" name="tra_type_no" value="40"> <input
							type="hidden" name="action" value="get_OneAll">
					</FORM>
				</div>

			</div>
		</div>

		<!-- ****************************************************************************************************************** -->
		<div class="container-fluid">
			<br>
			<c:choose>

				<c:when test="${traditionlist!=null}">
					<c:forEach var="TraditionVO" items="${traditionlist}" 
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<div class="panel-group" id="accordion">
							<div class="panel " style="background-color: #f5f5f5;">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"  
										href="#${TraditionVO.tra_no }">${TraditionVO.tra_order}.${TraditionVO.title}</a>

								</h4>
							</div>

							<div id="${TraditionVO.tra_no }" class="panel-collapse collapse ">
								<div class="panel-body">
									<pre style="border:0px">${TraditionVO.article}</pre>
								</div>
							</div>

						</div>
					</c:forEach>
					<tr>
						<%
							if (rowsPerPage < rowNumber) {
						%>
						<%
							if (pageIndex >= rowsPerPage) {
						%>
						<td><A href="<%=request.getRequestURI()%>?whichPage=1">至第一頁</A>&nbsp;</td>
						<td><A
							href="<%=request.getRequestURI()%>?whichPage=<%=whichPage - 1%>">上一頁
						</A>&nbsp;</td>
						<%
							}
						%>

						<%
							if (pageIndex < pageIndexArray[pageNumber - 1]) {
						%>
						<td><A
							href="<%=request.getRequestURI()%>?whichPage=<%=whichPage + 1%>">下一頁
						</A>&nbsp;</td>
						<td><A
							href="<%=request.getRequestURI()%>?whichPage=<%=pageNumber%>">至最後一頁</A>&nbsp;</td>
						<%
							}
						%>
						<%
							}
						%>
					</tr>


				</c:when>




				

				<c:when test="${list!=null}">
					<c:forEach var="TraditionVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<div class="panel-group" id="accordion">
							<div class="panel " style="background-color: #f5f5f5;">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#${TraditionVO.tra_no }">${TraditionVO.tra_no }.${TraditionVO.title}</a>

								</h4>
							</div>

							<div id="${TraditionVO.tra_no }" class="panel-collapse collapse ">
								<div class="panel-body">
									<pre style="border:0px">${TraditionVO.article}</pre>
								</div>
							</div>

						</div>
					</c:forEach>
					<tr>
						<%
							if (rowsPerPage < rowNumber) {
						%>
						<%
							if (pageIndex >= rowsPerPage) {
						%>
						<td><A href="<%=request.getRequestURI()%>?whichPage=1">至第一頁</A>&nbsp;</td>
						<td><A
							href="<%=request.getRequestURI()%>?whichPage=<%=whichPage - 1%>">上一頁
						</A>&nbsp;</td>
						<%
							}
						%>

						<%
							if (pageIndex < pageIndexArray[pageNumber - 1]) {
						%>
						<td><A
							href="<%=request.getRequestURI()%>?whichPage=<%=whichPage + 1%>">下一頁
						</A>&nbsp;</td>
						<td><A
							href="<%=request.getRequestURI()%>?whichPage=<%=pageNumber%>">至最後一頁</A>&nbsp;</td>
						<%
							}
						%>
						<%
							}
						%>
					</tr>





				</c:when>




				<c:otherwise></c:otherwise>
			</c:choose>



		</div>


	</div>

	<%@ include file="page/tradition_footer.file"%>