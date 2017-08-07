
function change(c) {
	if (c == '1') {
		document.getElementById("chatbox").style.display = 'block';
		document.getElementById("addClass").style.display = 'none';
		document.getElementById("dClass").style.display = 'block';
	} else {
		document.getElementById("chatbox").style.display = 'none';
		document.getElementById("addClass").style.display = 'block';
		document.getElementById("dClass").style.display = 'none';
	}
}


//var MyPoint = "/MessageServlet/wang/206";
//var host = window.location.host;
//var path = window.location.pathname;
//var webCtx = path.substring(0, path.indexOf('/', 1));
//var endPointURL = "ws://" + window.location.host + webCtx
//+ MyPoint;
//
//var statusOutput = document.getElementById("statusOutput");
//var webSocket;
//
//function connect() {
//					// 建立 websocket 物件
//					webSocket = new WebSocket(endPointURL);
//
//					webSocket.onopen = function(event) {
//						updateStatus("WebSocket 成功連線");
//						document.getElementById('sendMessage').disabled = false;
//						document.getElementById('connect').disabled = true;
//						document.getElementById('disconnect').disabled = false;
//					};
//
//					webSocket.onmessage = function(event) {
//						var messagesArea = document
//						.getElementById("messagesArea");
//						var jsonObj = JSON.parse(event.data);
//						var message = jsonObj.userName + ": " + jsonObj.message
//						+ "\r\n";
//						messagesArea.value = messagesArea.value + message;
//						messagesArea.scrollTop = messagesArea.scrollHeight;
//					};
//
//					webSocket.onclose = function(event) {
//						updateStatus("WebSocket 已離線");
//					};
//				}
//
//				var inputUserName = document.getElementById("userName");
//				inputUserName.focus();
//
//				function sendMessage() {
//					var userName = inputUserName.value.trim();
//					if (userName === "") {
//						alert("使用者名稱請勿空白!");
//						inputUserName.focus();
//						return;
//					}
//
//					var inputMessage = document.getElementById("message");
//					var message = inputMessage.value.trim();
//
//					if (message === "") {
//						alert("訊息請勿空白!");
//						inputMessage.focus();
//					} else {
//						var jsonObj = {
//							"userName" : userName,
//							"message" : message
//						};
//						webSocket.send(JSON.stringify(jsonObj));
//						inputMessage.value = "";
//						inputMessage.focus();
//					}
//				}
//
//				function disconnect() {
//					webSocket.close();
//					document.getElementById('sendMessage').disabled = true;
//					document.getElementById('connect').disabled = false;
//					document.getElementById('disconnect').disabled = true;
//				}
//
//				function updateStatus(newStatus) {
//					statusOutput.innerHTML = newStatus;
//				}
