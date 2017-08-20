package com.message.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import org.json.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

import com.message.model.*;

@ServerEndpoint("/MessageServlet/{myRoom}/{myName}")
public class MessageServlet extends HttpServlet {
//	private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	private static final Map<String, Set<Session>> sessionMap = Collections.synchronizedMap(new HashMap<String, Set<Session>>());
	private static final Map<String, Session> sessionUsername = Collections.synchronizedMap(new HashMap<String, Session>());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("ACTION=" + action);

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				String msg_no = new String(req.getParameter("msg_no"));
				System.out.println("MSG_NO=" + msg_no);

				MessageService messageSvc = new MessageService();
				messageSvc.deleteMessage(msg_no);

				String url = "/Front_end/message/listAllMessageTest.jsp";
				req.getRequestDispatcher(url).forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/message/listAllMessageTest.jsp");
				failureView.forward(req, res);
			}
		}
	}

	@OnOpen
	public void onOpen(Session userSession, @PathParam("myRoom") String myRoom, @PathParam("myName") String myName) throws IOException {
		
		if(!sessionMap.containsKey(myRoom)) {
			sessionMap.put(myRoom, new HashSet<Session>());
			sessionMap.get(myRoom).add(userSession);
			System.out.println("創立新房間新連線");
		} else {
			sessionMap.get(myRoom).add(userSession);
			System.out.println("房間存在，加入房間");
		}
		sessionUsername.put(myName, userSession);
		
		System.out.println(myName);
		System.out.println(myRoom);
		System.out.println(userSession.getId());
		
		System.out.println(userSession.getId() + ": 已連線");
		// System.out.println(no + ": 已連線");
		// userSession.getBasicRemote().sendText("WebSocket 連線成功");
	}

	@OnMessage
	public void onMessage(Session userSession, String message, @PathParam("myRoom") String myRoom, @PathParam("myName") String myName) throws JSONException {
		JSONObject j = new JSONObject(message);
		
		String name = (String) j.get("name");
		String toname = (String) j.get("toname");
//		String action = (String) j.get("action");
		
		//存訊息
		MessageService messageSvc = new MessageService();
		MessageVO messageVO = new MessageVO();
		if (message.length() != 0) {
			if(name.startsWith("1")){
				messageVO = messageSvc.addMessage(name, toname, message);		
			} else {
				messageVO = messageSvc.addMessage(toname, name, message);
			}
		}
		
		//設定廠商進入房間
		if(myName.indexOf("2")==0)
		{
			
		  myRoom=toname+myName;
		  
		  //會員登入房間
		}else {
			myRoom=myName+toname;
			sessionMap.get(myRoom).add((Session) sessionUsername.get(toname));
		}
		
		for (Session session : sessionMap.get(myRoom)) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
		System.out.println("發送訊息");
		System.out.println("myRoom: " + myRoom+"myName: "+myName+"myRoomsize: "+myRoom.length());	
		System.out.println("Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		// e.printStackTrace();
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason, @PathParam("myRoom") String myRoom) {
		if (sessionMap.get(myRoom).size() == 0) {
			sessionMap.remove(myRoom);
			System.out.println("房間沒人了，關閉房間");
		}
//		allSessions.remove(userSession);
		
		
		System.out
				.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

}
