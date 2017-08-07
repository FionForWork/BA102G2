package com.message.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

import com.message.model.*;

@ServerEndpoint("/MessageServlet/{mem_no}/{com_no}")
public class MessageServlet extends HttpServlet {
	private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
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

				String url = "/Back_end/message/listAllMessageTest.jsp";
				req.getRequestDispatcher(url).forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/message/listAllMessageTest.jsp");
				failureView.forward(req, res);
			}
		}
	}

	StringBuffer sb = new StringBuffer();
	String mem_no, com_no;

	@OnOpen
	public void onOpen(@PathParam("mem_no") String mem_no, @PathParam("com_no") String com_no, Session userSession)
			throws IOException {
		allSessions.add(userSession);
		// System.out.println(userSession.getId() + ": 已連線");
		this.mem_no = mem_no;
		this.com_no = com_no;
		System.out.println(mem_no + ": 已連線");
		System.out.println(com_no + ": 已連線");
		// userSession.getBasicRemote().sendText("WebSocket 連線成功");
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		sb.append(message);
		for (Session session : allSessions) {
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
		allSessions.remove(userSession);
		MessageService messageSvc = new MessageService();
		MessageVO messageVO = new MessageVO();
		messageVO = messageSvc.addMessage(mem_no, com_no, sb.toString());

		System.out
				.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

}
