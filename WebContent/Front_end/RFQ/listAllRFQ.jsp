<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rfq.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.text.*" %>
<%! int index = 0; %>
<%
	RFQService rfqService = new RFQService();
	List<RFQVO> rfqList = rfqService.getAllRFQ();
	DateFormat df = new SimpleDateFormat("YYYY年M月d日");
	MemService memService = new MemService();

	pageContext.setAttribute("memService",memService);
    pageContext.setAttribute("rfqList",rfqList);
	pageContext.setAttribute("df", df);
%>
<jsp:useBean id="sortingHat" class="com.ssy.tools.SortingHat" />
<jsp:useBean id="rfqDetailSvc" class="com.rfq_detail.model.RFQ_DetailService" />
<jsp:useBean id="servTypeService" class="com.service_type.model.Service_TypeService" />
<jsp:useBean id="quoteService" class="com.quote.model.QuoteService" />
<html>
<head>
</head>
<%@ include file="page/share_header_v2.file" %>
<div class="container step-container">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<a href="<%=request.getContextPath()%>/Front_end/RFQ/addRFQ.jsp">
				<img src="<%=request.getContextPath()%>/Front_end/RFQ/img/rfq_step2.png" class="step-img">
			</a>
		</div>
	</div>
</div>
<%	int rowsPerPage = 4; // 每頁的筆數
	int rowsNumber = 0;	// 總筆數
	int pageNumber = 0; // 總頁數
	int whichPage = 1; // 當前頁數
    int pageIndexArray[]=null;
    int pageIndex=0; // 開始資料筆數
 %>
 
<%	rowsNumber = rfqList.size();
	if(rowsNumber % rowsPerPage != 0){
		pageNumber = rowsNumber / rowsPerPage + 1;
	}else{
		pageNumber = rowsNumber / rowsPerPage;
	}
	
	pageIndexArray = new int[pageNumber];
	for(int i = 0; i < pageIndexArray.length; i++){
		pageIndexArray[i] = i * rowsPerPage;
	}
%>

<%
	try{
		whichPage = Integer.parseInt(request.getParameter("whichPage"));
		pageIndex = pageIndexArray[whichPage - 1];
	}catch(NumberFormatException e){
		whichPage = 1;
		pageIndex = 0;
	}
%>
<c:if test="${not empty errorMsgs}">
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<div class="alert alert-danger alert-dismissable fade in col-md-offset-3 col-md-6">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>報價失敗QQ!</strong> ${message}
			</div>
		</c:forEach>
	</ul>
	</font>
</c:if>
<div class="container">
	<div class="container">
	<c:forEach var="rfqVO" items="${rfqList}" begin="<%= pageIndex %>" end="<%= pageIndex + rowsPerPage - 1 %>">
		<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-default">
				<div class="panel-body rfq-row">
					<div class="row">
						<div class="col-md-2">
<%-- <%=request.getContextPath()%>/Front_end/RFQ/img/LOGO.png --%>
							<img src="<%=request.getContextPath()%>/ShowPictureServletDAO?mem_no=${rfqVO.mem_no}" class="mem_img img-circle">
						</div>
						<c:forEach var="rfqDetailVO" items="${rfqDetailSvc.getSomeRFQDetail(rfqVO.rfq_no)}">
						<% index++; %>
						<% if(index != 1){ %>
							<div class="col-md-offset-2 col-md-8">
						<% }else{ %>
						<div class="col-md-8">
						<% } %>					
						<br>
							<h3>${df.format(rfqDetailVO.ser_date)}
							-${rfqDetailVO.location}找
							<span style="color:#f14195">${servTypeService.getOne(rfqDetailVO.stype_no).name}</span>服務</h3>
							${rfqDetailVO.content}<br><br>
							<b style="font-size:16px">${memService.getOneMem(rfqVO.mem_no).name}</b>於${df.format(rfqVO.rfq_date)}詢價<br><br>
							<b style="font-size:16px" id="${rfqDetailVO.rfqdetail_no}">${sortingHat.getQuoteNum(quoteService.getAllQuote(rfqDetailVO.rfqdetail_no).size())}</b>
							<hr>
						</div>
						<div class="col-md-2">
						<hr><br>
						<!-- listQuote -->
							<form  method="post" action="<%= request.getContextPath() %>/quote/quote.do">
							<input type="hidden" name="action" value="listQuote">
							<input type="hidden" name="rfqMem_no" value="${rfqVO.mem_no}">
							<input type="hidden" name="rfqdetail_no" value="${rfqDetailVO.rfqdetail_no}">
							<input type="submit" class="btn btn-block btn-basic" value="查看內容">
							</form>
<!-- 廠商資料寫死 會員無法報價 -->
							<c:if test="${rfqDetailVO.status.equals('1') && comVO != null}">
							<button type="button" class="btn btn-info btn-block" data-toggle="modal" data-target="#myModal${rfqDetailVO.rfqdetail_no}">我要報價</button>
						<!-- Quote -->
							<div class="modal fade" id="myModal${rfqDetailVO.rfqdetail_no}" role="dialog">
								<div class="modal-dialog">
									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h3 class="modal-title text-pink">我有檔期，提交報價!</h4>
										</div>
										<form class="test">
										<div class="modal-body form-group">
											<label>服務說明</label>
											<input type="radio" name="optradio"  onclick="autoAdd(this,1)">
											<input type="radio" name="optradio"  onclick="autoAdd(this,2)">
											<input type="radio" name="optradio"  onclick="autoAdd(this,3)">
											<input type="radio" name="optradio"  onclick="autoAdd(this,4)">
											<textarea rows="8" class="form-control" name="content">
1.聯絡人:
2.服務內容:
3.備註:
											</textarea>
											<br>
											<label >服務金額</label>
											<input type="text" class="form-control" name="price"  placeholder="請輸入報價金額">
										</div>
										<div class="modal-footer">
<!-- 											<input type="submit" class="btn btn-danger" value="提交報價"> -->
											<input type="button" class="btn btn-danger" value="提交報價" onclick="quote(this)">
											<input type="hidden" name="rfqdetail_no" value="${rfqDetailVO.rfqdetail_no}">
											<input type="hidden" name="action" value="quote">
											<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
										</div>
										</form>
									</div>
								</div>
							</div>
							</c:if>
							<c:if test="${rfqDetailVO.status.equals('0') && comVO != null}">
								<button type="button" class="btn btn-danger btn-block" disabled>已關閉</button>
							</c:if>
						</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<% index = 0; %>
	</c:forEach>
	</div>
</div>
<div class="text-center">
	<ul class="pagination">
		<% for(int i = 0; i < pageNumber; i++){ %>
		<li><a href="<%= request.getRequestURI()%>?whichPage=<%=i + 1%>"><%=i + 1%></a></li>
		<% } %>
	</ul>
</div> 
<%@ include file="page/footer.file" %>
</body>
<script type="text/javascript">
	function quote(btn){
		var form = $(btn).parent().parent("form");
		var showResult = $("#"+$(btn).siblings("input[name='rfqdetail_no']").val());
		$.ajax({
			url : "<%= request.getContextPath() %>/quote/quote.do",
			data : $(form).serialize(),
			type : 'POST',
			error : function() {
				alert('Ajax request 發生錯誤');
			},
			success : function(result) {
				
				if(result == "金額請填入數字"){
					$(btn).parent().prev().children("label:last").html("服務"+result).css("color","red");
				}else if(result == "請問您的報價加上備註!"){
					$(btn).parent().prev().children("label:first").html(result).css("color","red");
				}else{
					$(showResult).html("您為這筆詢價新增了報價!").css("color","#f14195");
					$(btn).siblings("button").click();
				}
			}
		});
	}
	
	function autoAdd(btn,x){
		if(x == "1"){
			$(btn).siblings('textarea').html("美人兒 妳好：\n恭喜妳^^\n請先參考我的作品喔!\n目前有檔期為您服務\n價格太低都可以再聯絡討論!\n歡迎預約~");
			$(btn).siblings("input[name='price']").val("68000");
		}else if(x == "2"){
			$(btn).siblings('textarea').html("您好: 我們是專職的JART攝影團隊，還有合作的新秘、樂團與主持\n我們的作品請參考 有任何問題歡迎詢問");
			$(btn).siblings("input[name='price']").val("8000");
		}else if(x == "3"){
			$(btn).siblings('textarea').html("先恭喜你們走入人生另一個階段 目前有檔期可以為您服務\n1.姓名：Bobo\n2.作品：http://promisestudio.pixnet.net/blog\n3.電話：09-***-121");
			$(btn).siblings("input[name='price']").val("50");
		}else{
			$(btn).siblings('textarea').html("您好\n沒有優惠!\n公道價八萬一!");
			$(btn).siblings("input[name='price']").val("81000");
		}
	}
</script>
</html>