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

@ServerEndpoint("/MessageServlet/")
public class MessageServlet extends HttpServlet {
	private static final Set<Session> room = Collections.synchronizedSet(new HashSet<Session>());
	private static final Map<String, Set<Session>> map = Collections.synchronizedMap(new HashMap<String, Set<Session>>());
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
	public void onOpen(Session userSession) throws IOException {
		
		room.add(userSession);
		System.out.println(userSession.getId() + ": 已連線");
		// System.out.println(no + ": 已連線");
		// userSession.getBasicRemote().sendText("WebSocket 連線成功");
	}

	@OnMessage
	public void onMessage(Session userSession, String message) throws JSONException {
		JSONObject j = new JSONObject(message);
		String com_no = (String) j.get("comNo");
		String mem_no = (String) j.get("memNo");

		MessageService messageSvc = new MessageService();
		MessageVO messageVO = new MessageVO();
		if (message.length() != 0) {
			messageVO = messageSvc.addMessage(mem_no, com_no, message);
		}
		for (Session session : room) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
		System.out.println("Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		// e.printStackTrace();
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		room.remove(userSession);
		System.out
				.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

}
