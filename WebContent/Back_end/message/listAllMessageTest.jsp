<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.message.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import= "org.json.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>message</title>

<%
	MessageService messageSvc = new MessageService();
	List<MessageVO> messageList = messageSvc.getAll();
	pageContext.setAttribute("messageList", messageList);
	
%>

</head>
<body>
		<c:if test="${not empty errorMsgs}">
			<font color='red'>請修正以下錯誤:
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>


	<%for(MessageVO messageVO : messageList){%>

		<div class="col-xs-12 col-sm-3">
			<ul>
				<li class="list-unstyled">
					<span>訊息編號:<%=messageVO.getMsg_no()%></span><br>
					<span>廠商編號:<%=messageVO.getCom_no()%></span><br> 
					<span>會員編號:<%=messageVO.getMem_no()%></span><br>
					<span>內容:
					<% 
					JSONObject j = new JSONObject(messageVO.getContent());%>
					userName:<%=j.get("userName")%><br>
					message:<%=j.get("message")%><br>
					time:<%=j.get("time")%><br>
					</span><br>
					
					<form method="post"
						action="<%=request.getContextPath()%>/message/message.do">
						<input type="submit" value="刪除"> <input type="hidden"
							name="msg_no" value="${messageVO.msg_no}"> <input type="hidden" name="action"
							value="delete">
					</form></li>
			</ul>
		</div>

	<% }%>



</body>
</html>