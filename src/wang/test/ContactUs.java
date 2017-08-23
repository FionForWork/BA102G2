package wang.test;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactUs extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		Map<String,String> contact_us_map = new LinkedHashMap<String,String>();
		String requestURL = new String(req.getParameter("requestURL"));
		System.out.println(requestURL);
		
		String name = new String(req.getParameter("name").trim());
		if (name == null || name.trim().length() == 0) {
			errorMsgs.put("name","姓名請勿空白");
		}else{
			contact_us_map.put("name",name);			
		}
		
		String email = new String(req.getParameter("email").trim());
		if (email == null || email.trim().length() == 0) {
			errorMsgs.put("email","信箱請勿空白");
		}else{
			contact_us_map.put("email",email);			
		}
		
		String messagesArea = new String(req.getParameter("messagesArea"));
		if (messagesArea == null || messagesArea.trim().length() == 0) { 
			errorMsgs.put("messagesArea","內容請勿空白");
		}else{
			contact_us_map.put("messagesArea",messagesArea);			
		}
		
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("contact_us_map", contact_us_map);
			System.out.println("com_no="+req.getParameter("com_no"));
			if(requestURL != "/Front_end/com_page/company_page.jsp") {
		    	RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);
//		    	res.sendRedirect(requestURL);	    	
		    } else {
		    	RequestDispatcher successView = req.getRequestDispatcher(requestURL+"?com_no=" + req.getParameter("com_no"));
				successView.forward(req, res);
		    }
			return;
			
		}
		
		String to = "shesaidyesteam@gmail.com";
	      
	    String subject = "聯絡我們";
	      
		String messageText = "姓名: " + name + "\r\n" + " 信箱: " + email + "\r\n" +" 內容: "+ messagesArea; 
		
		MailService mailService = new MailService();
	    mailService.sendMail(to, subject, messageText);
	    if(requestURL != "/Front_end/com_page/company_page.jsp") {
	    	RequestDispatcher successView = req.getRequestDispatcher(requestURL);
			successView.forward(req, res);
//	    	res.sendRedirect(requestURL);	    	
	    } else {
	    	RequestDispatcher successView = req.getRequestDispatcher(requestURL+"?com_no=" + req.getParameter("com_no"));
			successView.forward(req, res);
	    }
	}
}

