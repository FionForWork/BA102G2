<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.works.model.*"%>
<%@ page import="com.com.model.*"%>
<%@ page import="com.serv.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.message.model.*"%>
<%@ page import= "org.json.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	ComService comSvc1 = new ComService();
	ComVO comVO1 = comSvc1.getOneCom("2003");
	session.setAttribute("comVO", comVO1);
	
// 	MemService memSvc = new MemService();
// 	MemVO memVO1 = memSvc.getOneMem("1001");
// 	session.setAttribute("memVO", memVO1);
	
	WorksService worksSvc = new WorksService();
	List<WorksVO> worksList = worksSvc.getAllByComNo(request.getParameter("com_no"));
	pageContext.setAttribute("worksList", worksList);

	ServService servSvc = new ServService();
	List<ServVO> servList = servSvc.getAll();
	pageContext.setAttribute("servList", servList);
	
	MessageService messageSvc = new MessageService();
	List<String> messageList = messageSvc.getMessageByMem_no("1001");
	pageContext.setAttribute("messageList", messageList);
	
	ComService comSvc2 = new ComService();
	ComVO comVO2 = comSvc2.getOneCom("2001");
	pageContext.setAttribute("comVO2", comVO2);
	
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body onload="connect('${memVO != null? memVO.mem_no : comVO.com_no}');" onunload="disconnect();">
	
	<%@ include file="page/before.file"%>

	
	

	
		<!--預約按鈕-->
		<div class="col-sm-2">
			<p class="text-center">
				<a class="btn btn-reservation btn-lg" href=""> 線上預約 </a>
			</p>
			<br><br>
			<p class="text-center" id="open_chat">
				<a class="btn btn-reservation btn-lg">立即聯絡我們 </a>
			</p>
		</div>
		
	<!--預約按鈕-->

	
	<!--聯絡我們-->
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="chat_box panel panel" id="chatbox">
					<div class="panel-heading">
						<button id=close_chat class="chat-header-button pull-right" type="button"><i class="fa fa-times"></i></button>
						<jsp:useBean id="comSvc" scope="page" class="com.com.model.ComService" />
						<c:forEach var="comVO" items="${comSvc.all}">
						<c:if test="${param.com_no==comVO.com_no}">
						<div><img class="img-circle" style="width:25%" src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${comVO.com_no}">${comVO.name}</div>
						</c:if>
						</c:forEach>
						<div id="statusOutput"></div>
					</div>
					<div class="panel-body">
					<ul id="textArea"></ul>
					</div>
					<div class="panel-footer">							
							<input id="message" class="col-md-9 text-field" type="text" placeholder="訊息" onkeydown="if (event.keyCode == 13) sendMessage('${memVO != null? memVO.mem_no : comVO.com_no}',																												  '${memVO != null? memVO.name : comVO.name}');" />	
							<input type="submit" id="sendMessage" class="col-md-3 button sendMessage_btn" value="送出" 
							onclick="sendMessage('${memVO != null? memVO.mem_no : comVO.com_no}',
												 '${memVO != null? memVO.name : comVO.name}')" />	
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--聯絡我們-->
	
	<%@ include file="page/after.file"%>
	<%@ include file="page/message_script.file"%>
</body>
</html>