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
// 	ComService comSvc = new ComService();
// 	ComVO comVO1 = comSvc.getOneCom("2003");
// 	session.setAttribute("comVO", comVO1);
	
	MemService memSvc = new MemService();
	MemVO memVO1 = memSvc.getOneMem("1001");
	session.setAttribute("memVO", memVO1);
	
	WorksService worksSvc = new WorksService();
	List<WorksVO> worksList = worksSvc.getAllByComNo(request.getParameter("com_no"));
	pageContext.setAttribute("worksList", worksList);

	ServService servSvc = new ServService();
	List<ServVO> servList = servSvc.getAll();
	pageContext.setAttribute("servList", servList);
	
	MessageService messageSvc = new MessageService();
	List<String> messageList = messageSvc.getMessageByMem_no("1001");
	pageContext.setAttribute("messageList", messageList);
	
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
			<p class="text-center" id="addClass">
				<a class="btn btn-reservation btn-lg" onclick="change(1)">
					立即聯絡我們 </a>
			</p>
			<p class="text-center" style="display: none;" id="dClass">

				<a class="btn btn-reservation btn-lg" onclick="change(2)">
					立即聯絡我們 </a>
			</p>
		</div>
	</div>
	<!--店家資料-->

	
	<!--聯絡我們-->
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="chat_box panel panel-default" id="chatbox">
					<div class="panel-heading">
						<button id=close class="chat-header-button pull-right" type="button" onclick="change(2);"><i class="fa fa-times"></i></button>
						<div id="statusOutput"></div>
					</div>
					<div class="panel-body">
					<ul id="textArea"></ul>
					</div>
					<div class="panel-footer">
						<div class="panel input-area">
							<input id="userName" class="text-field" type="text" placeholder="使用者名稱" value="${(memVO==null)?comVO.name:memVO.name}" disabled="true"/> 
							<div class="row">
							<input id="message" class="col-md-9 text-field" type="text" placeholder="訊息" onkeydown="if (event.keyCode == 13) sendMessage('${memVO != null? memVO.mem_no : comVO.com_no}',
								 																														  '${memVO != null? memVO.name : comVO.name}');" />	
							<input type="submit" id="sendMessage" class="col-md-3 button" value="送出" 
							onclick="sendMessage('${memVO != null? memVO.mem_no : comVO.com_no}',
												 '${memVO != null? memVO.name : comVO.name}')" />	
							</div>		
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--聯絡我們-->
	
	<%@ include file="page/after.file"%>

<script type="text/javascript">
<%-- <%ComVO comVO = (ComVO) session.getAttribute("comVO"); --%>
// MemVO memVO = (MemVO) session.getAttribute("memVO");
<%-- %> --%>
<%-- var comVO = <%=comVO%>; --%>
<%-- var memVO = <%=memVO%>; --%>



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
 					webSocket = new WebSocket(endPointURL);

 					webSocket.onopen = function(event) {
						updateStatus("成功連線");
 						document.getElementById('sendMessage').disabled = false;				
 						
 					};

 					webSocket.onmessage = function(event) {
 						
 						
 						var messagesArea = document.getElementById("messagesArea");
 						
 	 						document.getElementById("chatbox").style.display = 'block';
 	 						document.getElementById("addClass").style.display = 'none';
 	 						document.getElementById("dClass").style.display = 'block';
 						var jsonObj = JSON.parse(event.data);
 						memNo = jsonObj.memNo;
 						var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";


 						if (memNo == no){
 					        control = '<li style="width:100%">' +
 					                        '<div class="msg-r">' +
 					                            '<div class="text text-r">' +
 					                                '<p>'+ message +'</p>' +
 					                            '</div>' +
 					                        '</div>' +
 					                    '</li>';                    
 					    }else{
 					        control = '<li style="width:100%;">' +
 					                        '<div class="msg-l">' +
 					                            '<div class="text text-l">' +
 					                                '<p>'+message+'</p>' +
 					                            '</div>' +                               
 					                  '</li>';
 					    }   
 					
 				        		$("#textArea").append(control);
 				        
	
 						
 						 						
 					};
 					webSocket.onclose = function(event) {
 						updateStatus("已離線");
 					};
 				}

 				function sendMessage(no, userName) {
					console.log(no);
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