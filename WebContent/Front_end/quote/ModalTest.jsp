<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.quote.model.*" %>
<%@ page import="java.util.*" %>
<% 
	
	List<QuoteVO> list = new QuoteService().getAllQuote("0001");
	pageContext.setAttribute("list", list);
	String quo_no = request.getParameter("quo_no");
	// Modal load()用
	if(quo_no != null){
		QuoteVO VO = new QuoteService().getOneQuote(quo_no);
		pageContext.setAttribute("VO", VO);
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <h2>Modal Example</h2>
  <!-- Trigger the modal with a button -->
<c:forEach var="quoteVO" items="${list}">
  <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" onclick="showModal(this)">${quoteVO.quo_no}</button>
</c:forEach>


  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body">
<!-- 動態改變這裡 -->
          <p id="showModal">${VO.content}</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

  </div>
  

<script>
function showModal(quo_no){
// 傳入那個btn所代表的quo_no，再傳給自己當參數load
	var no = $(quo_no).html();
	$("#showModal").load("ModalTest.jsp?quo_no="+no+" #showModal");

}
</script>
</body>
</html>