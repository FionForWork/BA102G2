<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.works.model.*"%>
<%@ page import="com.com.model.*"%>
<%@ page import="com.serv.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.message.model.*"%>
<%@ page import="org.json.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body onload="connect('${memVO != null? memVO.mem_no : comVO.com_no}');"
	onunload="disconnect();">


	<!--即時訊息-->
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="chat_box panel panel" id="chatbox">
					<div class="panel-heading">
						<a id=close_chat class="chat-header-button pull-right">x</a>

						<%-- 						<div class="col-md-3"><img class="chat-header-logo img-circle" src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${comVO.com_no}"></div> --%>

						<h4 id="chat-header-name">${memVO != null? comVO.name :''}</h4>

					</div>
					<div class="panel-body">
						<ul id="textArea"></ul>
					</div>
					<div class="panel-footer">
						<input id="message" class="col-md-9 text-field" type="text"
							autofocus="autofocus" placeholder="請輪入訊息"
							onkeydown="if (event.keyCode == 13) sendMessage('${memVO != null? memVO.mem_no : comVO.com_no}',	
																																							  						'${memVO != null? memVO.name : comVO.name}');" />
						<a id="sendMessage" class="col-md-3 sendMessage_btn"
							onclick="sendMessage('${memVO != null? memVO.mem_no : comVO.com_no}',
												 '${memVO != null? memVO.name : comVO.name}')">送出</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--即時訊息-->


	<!--即時訊息2-->
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="chat_box2 panel panel" id="chatbox2">
					<div class="panel-heading">
						<a id=close_chat2 class="chat-header-button pull-right">x</a>

						<%-- 						<div class="col-md-3"><img class="chat-header-logo img-circle" src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${comVO.com_no}"></div> --%>

						<h4 id="chat-header-name2">${memVO != null? comVO.name :''}</h4>

					</div>
					<div class="panel-body">
						<ul id="textArea2"></ul>
					</div>
					<div class="panel-footer">
						<input id="message2" class="col-md-9 text-field" type="text"
							autofocus="autofocus" placeholder="請輪入訊息"
							onkeydown="if (event.keyCode == 13) sendMessage2('${memVO != null? '1001' : comVO.com_no}',	
																																							  						'${memVO != null? '1001' : comVO.name}');" />
						<a id="sendMessage" class="col-md-3 sendMessage_btn"
							onclick="sendMessage2('${memVO != null? memVO.mem_no : comVO.com_no}',
												 '${memVO != null?  memVO.name : comVO.name}')">送出</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--即時訊息-->


	﻿
	<script type="text/javascript">

var MyPoint = "/MessageServlet/${memVO.mem_no}${comVO.com_no}/${memVO==null?comVO.com_no:memVO.mem_no}";
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
var idname = [];
var toname;	

 				function connect(no) {
 					// 建立 websocket 物件
 					console.log('connect(no):'+no);
 					webSocket = new WebSocket(endPointURL);

 					webSocket.onopen = function(event) {

 						document.getElementById('sendMessage').disabled = false;				
 						
 					};

 					webSocket.onmessage = function(event) {
						
 						var jsonObj = JSON.parse(event.data);
 						var messagesArea = document.getElementById("messagesArea");
 						var who = jsonObj.who;
 						var userName = jsonObj.userName;
 						toname=jsonObj.toname;
 						var message = jsonObj.message + "\r\n";
 						console.log(message);
 						
 						
	 					if(${comVO.com_no}!= jsonObj.name) {
	 						memNo = jsonObj.name;
	 						
	 						console.log(memNo+"  "+idname[0]+"  "+idname[1]+"1");
	 						
	 						
	 						 console.log("else");
	 						 idname.push(jsonObj.name);
	 						
	 					
	 						console.log(memNo+"name[1]  "+idname[0]+" name[2] "+idname[1]+"3");
	 				
	 					}
	 					
 						
 							
							$("#chatbox").show();
							$("#close_chat").click(function(){
								$("#chatbox").toggle();
							});	
 						
 						 if(no.indexOf("1")!=0&&idname[1]!=null){
 							$("#chatbox2").show();
 							$("#close_chat2").click(function(){
 								$("#chatbox2").toggle();
 							});
 							
 						}
						
 						
 						
 						var me = {};
 						var you = {};
 						if(no.indexOf("1")==0) {
 							me.img = "";
 							
 							you.img = "<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${comVO.com_no}";
 						}
 						
 						if(no.indexOf("1")!=0) {
 							me.img = "";

 							you.img = "<%=request.getContextPath()%>/ShowPictureServletDAO?mem_no="+memNo;
//  							you.img = "https://www.wallstreetotc.com/wp-content/uploads/2014/10/facebook-anonymous-app.jpg";
 						}
 						
 						
 						
 						if(no.indexOf("1")==0)
 							{
 							
 							if (who==no){
 	 					        control = '<li style="width:100%;">' +
// 	  					                            '<div class="msg_logo_r">' +
// 	  					                                '<img class="img-circle" style="width:45px;height:45px;" src="'+ me.img +'" />' +
// 	  					                            '</div>' +
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
 	 					      		$("#chat-header-name").html(userName);      
 	 					    }   
 	 					
 	 				        		$("#textArea").append(control);
 	 				        					                   			
 							
 							
 							}
 						
 						
 							
 						
 								
							
 								if (no.indexOf("1")!=0){
 									
 									
 									
 									
 									if(memNo==idname[0]&&toname==${comVO.com_no})
 										{
 										
 										if (who==no){
 			 	 					        control = '<li style="width:100%;">' +
// 			 	  					                            '<div class="msg_logo_r">' +
// 			 	  					                                '<img class="img-circle" style="width:45px;height:45px;" src="'+ me.img +'" />' +
// 			 	  					                            '</div>' +
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
 			 	 					      		$("#chat-header-name").html(userName);      
 			 	 					    } 
 										
 										console.log("#textArea");
 									   $("#textArea").append(control);
 											
 			 	 				        		
 			 	 				        					                   			
 										
 										
 
 										
 										}else if(memNo==idname[1]&&toname==${comVO.com_no}){
 											
 											
 											if (who==no){
 				 	 					        control = '<li style="width:100%;">' +
// 				 	  					                            '<div class="msg_logo_r">' +
// 				 	  					                                '<img class="img-circle" style="width:45px;height:45px;" src="'+ me.img +'" />' +
// 				 	  					                            '</div>' +
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
 				 	 					      		$("#chat-header-name2").html(userName);      
 				 	 					    } 
 											
 											console.log("#textArea2");
 											
 												  $("#textArea2").append(control);
 														
 										}else if(no==${comVO.com_no})
 											{
 											 console.log(toname); 
 											 
 											             if(toname==idname[0])
 											            	 {
 											            	console.log(toname+"2"); 
 											            	 
 											            	if (who==no){
 					 			 	 					        control = '<li style="width:100%;">' +
// 					 			 	  					                            '<div class="msg_logo_r">' +
// 					 			 	  					                                '<img class="img-circle" style="width:45px;height:45px;" src="'+ me.img +'" />' +
// 					 			 	  					                            '</div>' +
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
 					 			 	 					      		$("#chat-header-name").html(userName);      
 					 			 	 					    } 
 					 										
 					 										console.log("#textArea");
 					 									   $("#textArea").append(control);
 											            	 
 											            	 
 											            	 
 											            	 
 											            	 }else{
 											            		 
 											            		
 											            		if (who==no){
 											            			
 											            			console.log(toname+"5"); 
 					 				 	 					        control = '<li style="width:100%;">' +
// 					 				 	  					                            '<div class="msg_logo_r">' +
// 					 				 	  					                                '<img class="img-circle" style="width:45px;height:45px;" src="'+ me.img +'" />' +
// 					 				 	  					                            '</div>' +
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
 					 				 	 					      		$("#chat-header-name2").html(userName);      
 					 				 	 					    } 
 					 											
 					 											console.log("#textArea2");
 											            		 
 					 										   $("#textArea2").append(control);
 											            		 
 											            		 
 											            	 }
 											
 											
 											          
 											
 											
 											}
 									
 									
 									
 									
 									
 								
 								
 								
 								
 								

 								
 							}
 						
 	
 					
 					};
 					
 					
 					webSocket.onclose = function(event) {
 						console.log("已離線");
 					};
 				}

 				function sendMessage(no, userName) {
 					console.log('memNo:'+memNo);
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
 								"action" : "message",
 								"who" : no,
 	 							"name" : no,
 								"toname" : "${param.com_no}",
 	 							"userName" : userName,
 	 							"message" : message,
 	 							"time" : nowdate
 	 						};
 						webSocket.send(JSON.stringify(jsonObj));
						inputMessage.value = "";
 						inputMessage.focus();
 						}else {
 							var jsonObj = {
 								"action" : "message",
 								"who" : no,
 								"name" : no,
 	 	 						"toname" : idname[0],
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
 				
 				function sendMessage2(no, userName) {
 					console.log('memNo:'+memNo);
					console.log('NO:'+no);
					console.log(userName);
 					var inputMessage = document.getElementById("message2");
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
 								"action" : "message",
 								"who" : no,
 	 							"name" : no,
 								"toname" : "${param.com_no}",
 	 							"userName" : userName,
 	 							"message" : message,
 	 							"time" : nowdate
 	 						};
 						webSocket.send(JSON.stringify(jsonObj));
						inputMessage.value = "";
 						inputMessage.focus();
 						}else {
 							var jsonObj = {
 								"action" : "message",
 								"who" : no,
 								"name" : no,
 	 	 						"toname" : idname[1],
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

 						
</script>

</body>
</html>