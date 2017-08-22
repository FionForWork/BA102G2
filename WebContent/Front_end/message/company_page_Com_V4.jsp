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
	
	
	ComService comSvc2 = new ComService();
	ComVO comVO2 = comSvc2.getOneCom("2003");
	pageContext.setAttribute("comVO", comVO2);
	
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

						<div class="col-md-8 chat-header-name">${comVO.name}</div>

<!-- 						<div id="statusOutput"></div> -->
					</div>
					<div class="panel-body">
					<ul id="textArea"></ul>
					</div>
					<div class="panel-footer">							
							<input id="message" class="col-md-9 text-field" type="text" autofocus="autofocus" onkeydown="if (event.keyCode == 13) sendMessage('${memVO != null? memVO.mem_no : comVO.com_no}',	
																																							  '${memVO != null? memVO.name : comVO.name}');" />	
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
	
	
﻿<script type="text/javascript">

var MyPoint = "/MessageServlet/";
console.log(MyPoint);
var host = window.location.host;
console.log(host);
var path = window.location.pathname;
console.log(path);
var webCtx = path.substring(0, path.indexOf('/', 1));
console.log(webCtx);
var endPointURL = "ws://" + window.location.host + webCtx
+ MyPoint;
console.log(endPointURL);
var statusOutput = document.getElementById("statusOutput");
var webSocket;
var memNo;	
			

 				function connect(no) {
 					// 建立 websocket 物件
 					console.log('connect(no):'+no);
 					webSocket = new WebSocket(endPointURL);

 					webSocket.onopen = function(event) {
						updateStatus("成功連線");
 						document.getElementById('sendMessage').disabled = false;				
 						
 					};

 					webSocket.onmessage = function(event) {
 						$("#chatbox").show();
						$("#close_chat").click(function(){
							$("#chatbox").toggle();
						});
 						var jsonObj = JSON.parse(event.data);
 						var messagesArea = document.getElementById("messagesArea");
 						var who = jsonObj.who;
 						var comNo = jsonObj.comNo;
 						var userName = jsonObj.userName;
 						var message = jsonObj.message + "\r\n";
 						memNo = jsonObj.memNo;
 						
 						var me = {};
 						var you = {};
 						if(no==comNo) {
 							me.img = "";

 							you.img = "https://a11.t26.net/taringa/avatares/9/1/2/F/7/8/Demon_King1/48x48_5C5.jpg";
 						}
 						
 						if(no==memNo) {
 							me.img = "";

 							you.img = "<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${comVO.com_no}";
 						}
 						
 						if (who==no){
 					        control = '<li style="width:100%;">' +
//  					                            '<div class="msg_logo_r">' +
//  					                                '<img class="img-circle" style="width:45px;height:45px;" src="'+ me.img +'" />' +
//  					                            '</div>' +
 					                        '<div class="msg-r">' +
 					                            '<div class="msg_text text-r">' +
 					                                '<p>' + message + '</p>' +
 					                            '</div>' +
 					                        '</div>' +    
 					                    '</li>'+'<br>';                    
 					    }else{
 					        control = '<li style="width:100%;">' +
 					                            '<div class="msg_logo_l">' +
 					                                '<img class="img-circle" style="width:45px;height:45px;" src="'+ you.img +'" />' +
 					                            '</div>' +
 					                        '<div class="msg-l">' +
 					                       		'<div class="msg_text text-l">' +
			                               			 '<p>' + message + '</p>' +
			                            		'</div>' +
 					                        '</div>' +    
 					                  '</li>'+'<br>';
 					    }   
 					
 				        		$("#textArea").append(control);
 				        
	
 						
 						 						
 					};
 					webSocket.onclose = function(event) {
 						updateStatus("已離線");
 					};
 				}

 				function sendMessage(no, userName) {
					console.log('NO:'+no);
					console.log(userName);
 					var inputMessage = document.getElementById("message");
 					var message = inputMessage.value.trim();
 					var date = new Date();
 					var nowdate = date.getFullYear() + "-" + (date.getMonth()+1) + "-"
 								+ date.getDate() + " " + date.getHours()+":"+
 								+ date.getMinutes() + ":" + date.getSeconds();
 					inputMessage.focus();
					
 					if (message === "") {
 						alert("訊息請勿空白!");
 						inputMessage.focus();
 					} else {
 						if(no.indexOf("1")==0){
 							var jsonObj = {
 								"who" : no,
 								"comNo" : "<%=request.getParameter("com_no")%>",
 	 							"memNo" : no,
 	 							"userName" : userName,
 	 							"message" : message,
 	 							"time" : nowdate
 	 						};
 						webSocket.send(JSON.stringify(jsonObj));
						inputMessage.value = "";
 						inputMessage.focus();
 						}else {
 							var jsonObj = {
 								"who" : no,
 								"comNo" : no,
 	 	 						"memNo" : memNo,
 	 	 						"userName" : userName,
 	 	 						"message" : message,
 	 	 						"time" : nowdate
 	 	 					};
 	 						webSocket.send(JSON.stringify(jsonObj));
 							inputMessage.value = "";
 	 						inputMessage.focus();	
 						}
 					}
 				}
 				function disconnect() {
 					webSocket.close();
 				}

 				function updateStatus(newStatus) {
 					statusOutput.innerHTML = newStatus;
 				}
 						
</script>

</body>
</html>