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
// 	ComVO comVO1 = comSvc.getOneCom("2001");
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
<body onload="connect();" onunload="disconnect();">

	<%@ include file="page/before.file"%>


	<!--banner -->
	<c:forEach var="worksVO" items="${worksList}" begin="1" end="1">
		<div class="fade-carousel">
			<div class="home-banner fade-carousel" style="overflow: hidden;">
				<div class="item slides">
					<div class="slide-1 bg-cover lazy"
						style="background-image:url('<%=request.getContextPath()%>/ShowPictureServletDAO?works_no=${worksVO.works_no}');"></div>

				</div>
			</div>
		</div>
		<br>
	</c:forEach>
	<!--banner -->

	<!--店家大頭照-->
	<div class="com_head container">
		<img class="com_logo img-circle center-block"
			src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${comVO.com_no}"
			alt="She Said Yes">
	</div>
	<!--店家大頭照-->

	<div class="text-center">
		<h1>${comVO.name}</h1>
	</div>

	<div class="catalog hidden-xs">
		<ul class="list-inline">
			<li><a href="#info" title="聯絡資訊"> 聯絡資訊 </a></li>
			<li><a href="#works" title="作品"> 作品 </a></li>
			<li><a href="#service" title="方案"> 方案 </a></li>
			<li><a href="#introduction" title="關於我"> 關於我 </a></li>
		</ul>
	</div>

	<!--店家資料-->
	<div class="container" id="info">
		<div class="col-sm-10">
			<div class="hidden-xs">
				<table class="table table-condensed">
					<tbody>
						<tr>
							<th>店休日</th>
							<td>每周休六日</td>
						</tr>

						<tr>
							<th>電話</th>
							<td>${comVO.phone}</td>
						</tr>

						<tr>
							<th>地址</th>
							<td>${comVO.loc}</td>
						</tr>

						<tr>
							<th>信箱</th>
							<td><a href="">${comVO.id}</a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!--////////////////////////////-->
		<!--預約按鈕-->
		<div class="col-sm-2">
			<p class="text-center">
				<a class="btn btn-reservation btn-lg" href=""> 線上預約 </a>
			</p>
			<br><br>
			<p class="text-center" id="addClass">
				<a class="btn btn-reservation btn-lg" href="#" onclick="change(1)">
					立即聯絡我們 </a>
			</p>
			<p class="text-center" style="display: none;" id="dClass">

				<a class="btn btn-reservation btn-lg" href="#" onclick="change(2)">
					立即聯絡我們 </a>
			</p>
		</div>
	</div>
	<!--店家資料-->

	<!--店家相簿-->
	<div class="text-center" id="works">
		<span>
			<h1>廠商作品</h1>
		</span>
	</div>
	<div class="container">
		<div class="row">


			<c:forEach var="worksVO" items="${worksList}" begin="2" end="10">
				<div class="col-xs-12 col-sm-4">
					<ul class="works_box">
						<li class="list-unstyled"><a href="#"
							class="works_a thumbnail thumbnail thumbnail-service mod-shadow img-label">
								<img class="works_image img-thumbnail"
								src="<%=request.getContextPath()%>/ShowPictureServletDAO?works_no=${worksVO.works_no}">
								<div class="overlay">
									<div class="works_text">${worksVO.works_desc}</div>
								</div>
						</a></li>
					</ul>
				</div>
			</c:forEach>


		</div>
	</div>


	<div class="container text-center">
		<div class="row">
			<div class="col-xs-12 col-sm-3"></div>
			<div class="col-xs-12 col-sm-6">
				<a href="#"> <a class="btn btn-default btn-lg" href="#"> 看更多
						<i class="fa fa-angle-double-right" aria-hidden="true"></i>
				</a>
				</a>
			</div>
			<div class="col-xs-12 col-sm-3"></div>
		</div>
	</div>
	<!--店家相簿-->

	<!--店家方案-->
	<div class="text-center" id="service">
		<span>
			<h1>廠商方案</h1>
		</span>
	</div>
	<div class="container">
		<div class="row">

			<c:forEach var="servVO" items="${servList}" begin="1" end="4">
				<div class="col-xs-12 col-sm-3">
					<ul class="service_box">
						<li class="service_title">${servVO.title}</li><br>
						<li class="text">${servVO.content}</li>
						<li class="cost"><span>$NT</span>
						<span style="color: #ef8b87;">${servVO.price}</span></li>
					</ul>
				</div>
			</c:forEach>

		</div>
	</div>


	<div class="container text-center">
		<div class="row">
			<div class="col-xs-12 col-sm-3"></div>
			<div class="col-xs-12 col-sm-6">
				<a href="#"> <a class="btn btn-default btn-lg" href="#"> 看更多
						<i class="fa fa-angle-double-right" aria-hidden="true"></i>
				</a>
				</a>
			</div>
			<div class="col-xs-12 col-sm-3"></div>
		</div>
	</div>
	</div>
	<!--店家方案-->

	<!--店家影片-->
	<!--店家影片-->

	<!--店家自介-->
	<div class="text-center" id="introduction">
		<span>
			<h1>廠商介紹</h1>
		</span>
	</div>
	<div class="com_intro container">
		<div class="row">
			<div class="col-xs-12 col-sm-6">
				<div class="col-xs-12 col-sm-4"></div>
				<div class="col-xs-12 col-sm-8">基本介紹： ${comVO.com_desc}</div>
			</div>
			<div class="col-xs-12 col-sm-6">

				<div class="col-xs-12 col-sm-4"></div>

				<div class="col-xs-12 col-sm-8"></div>
			</div>
		</div>
	</div>

	<!--店家自介-->
	
	<!--聯絡我們-->
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="chat_box panel panel-default" id="chatbox">
					<div class="panel-heading">
						<div id="statusOutput"></div>
						<button id=close class="chat-header-button pull-right" type="button" onclick="change(2);"><i class="fa fa-times"></i></button>
					</div>
					<div class="panel-body">
						<textarea id="messagesArea" class="panel message-area" readonly>							
						</textarea>
					</div>
					<div class="panel-footer">
						<div class="panel input-area">
							<input id="userName" class="text-field" type="text" placeholder="使用者名稱" value="${(memVO==null)?comVO.name:memVO.name}" disabled="true"/> 
							<input id="message" class="text-field" type="text" placeholder="訊息" onkeydown="if (event.keyCode == 13) sendMessage();" />	
							<input type="submit" id="sendMessage" class="button" value="送出" onclick="sendMessage();" />
							<input type="button" id="connect" class="button" value="連線" onclick="connect();" />
							<input type="button" id="disconnect" class="button" value="離線" onclick="disconnect();" />		
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--聯絡我們-->
	
	<%@ include file="page/after.file"%>

<script type="text/javascript">
<%
ComVO comVO = (ComVO) session.getAttribute("comVO");
MemVO memVO = (MemVO) session.getAttribute("memVO");
%>


<%-- var com_no = "<%=comVO.getCom_no()%>"; --%>
<%-- var comName = "<%=comVO.getName()%>"; --%>
// console.log(com_no);
// console.log(comName);
var mem_no = "<%=memVO.getMem_no()%>";
var memName = "<%=memVO.getName()%>";
console.log(mem_no);
console.log(memName);
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
	


 				function connect() {
 					// 建立 websocket 物件
 					webSocket = new WebSocket(endPointURL);

 					webSocket.onopen = function(event) {
						updateStatus("成功連線");
 						document.getElementById('sendMessage').disabled = false;
 						document.getElementById('connect').disabled = true;
 						document.getElementById('disconnect').disabled = false;					
 						var messagesArea = document.getElementById("messagesArea");
 						messagesArea.value = messagesArea.value + "\r\n";
 							
 					};

 					webSocket.onmessage = function(event) {
 						document.getElementById("chatbox").style.display = 'block';
 						document.getElementById("addClass").style.display = 'none';
 						document.getElementById("dClass").style.display = 'block';
 						
 						var messagesArea = document.getElementById("messagesArea");
 						var jsonObj = JSON.parse(event.data);
 						var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
 						messagesArea.value = messagesArea.value + message;
 						messagesArea.scrollTop = messagesArea.scrollHeight;
 					};

 					webSocket.onclose = function(event) {
 						updateStatus("已離線");
 					};
 				}

 				function sendMessage() {

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
 						var jsonObj = {
 	 							"comNo" : "<%=request.getParameter("com_no")%>",
 	 							"memNo" : mem_no,
 	 							"userName" : (memName==null)?comName:memName,
 	 							"message" : message,
 	 							"time" : nowdate
 	 					};
 						webSocket.send(JSON.stringify(jsonObj));
						inputMessage.value = "";
 						inputMessage.focus();
 					}
 				}

 				function disconnect() {
 					webSocket.close();
 					document.getElementById('sendMessage').disabled = true;
 					document.getElementById('connect').disabled = false;
 					document.getElementById('disconnect').disabled = true;
 				}

 				function updateStatus(newStatus) {
 					statusOutput.innerHTML = newStatus;
 				}


</script>
</body>
</html>