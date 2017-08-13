package wang.test;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.java.swing.plaf.windows.resources.windows;

public class ContactUs extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		Map<String,String> map = new LinkedHashMap<String,String>();

		String name = new String(req.getParameter("name").trim());
		if (name == null || name.trim().length() == 0) {
			errorMsgs.put("name","姓名請勿空白");
		}else{
			map.put("name",name);			
		}
		
		String email = new String(req.getParameter("email").trim());
		if (email == null || email.trim().length() == 0) {
			errorMsgs.put("email","信箱請勿空白");
		}else{
			map.put("email",email);			
		}
		
		String messagesArea = new String(req.getParameter("messagesArea"));
		if (messagesArea == null || messagesArea.trim().length() == 0) { 
			errorMsgs.put("messagesArea","內容請勿空白");
		}else{
			map.put("messagesArea",messagesArea);			
		}
		
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("map", map);
			String requestURL = req.getParameter("requestURL");
			req.getRequestDispatcher(requestURL).forward(req, res);
			return;
			
		}
		
		String to = "shesaidyesteam@gmail.com";
	      
	    String subject = "聯絡我們";
	      
		String messageText = "姓名: " + name + "\r\n" + " 信箱: " + email + "\r\n" +" 內容: "+ messagesArea; 
		
		MailService mailService = new MailService();
	    mailService.sendMail(to, subject, messageText);
	    

	    String requestURL = req.getParameter("requestURL");
		req.getRequestDispatcher(requestURL).forward(req, res);
	}

}
