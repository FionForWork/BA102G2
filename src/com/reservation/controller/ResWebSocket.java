package com.reservation.controller;

import java.util.Collections;
import java.io.IOException;
import java.util.*;

import javax.websocket.*;
import javax.websocket.server.*;

import org.json.JSONException;
import org.json.JSONObject;

@ServerEndpoint("/ResServer/{myName}/{myRoom}")
public class ResWebSocket {

//private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
private static final Map<String , Set<Session>> sessionMap = Collections.synchronizedMap(new HashMap<String , Set<Session>>());

	@OnOpen
	public void onOpen(@PathParam("myName") String myName, @PathParam("myRoom") String myRoom, Session userSession) throws IOException {
		
		if(sessionMap.get(myRoom) == null){
			sessionMap.put(myRoom, new HashSet<Session>());
			sessionMap.get(myRoom).add(userSession);
			System.out.println("創立新房間新連線");
		}else{
			sessionMap.get(myRoom).add(userSession);
			System.out.println("房間存在，加入房間");
		}
//		allSessions.add(userSession);
		System.out.println(userSession.getId() + ": 已連線");
		System.out.println(myName + ": 已連線");
		System.out.println(myRoom + ": 房號");
//		userSession.getBasicRemote().sendText("WebSocket 連線成功");
	}
	
	
	@OnMessage
	public void onMessage(Session userSession, String message, @PathParam("myRoom") String myRoom) throws JSONException {

		for(Session session : sessionMap.get(myRoom)){
			System.out.println("發送訊息");
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
//		for (Session session : allSessions) {
//			if (session.isOpen())
//				session.getAsyncRemote().sendText(message);
//		}
		System.out.println("Message received: " + message);
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason, @PathParam("myRoom") String myRoom) {
		sessionMap.get(myRoom).remove(userSession);
		if(sessionMap.get(myRoom).size()==0 ){
			sessionMap.remove(myRoom);
			System.out.println("房間沒人了，關閉房間");
		}
//		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

	
}
