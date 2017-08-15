package com.calendar.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.calendar.model.*;

/**
 * Servlet implementation class CalendarServlet
 */
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		System.out.println("servletAction : " + action);
		PrintWriter out = res.getWriter();
		
		// 更改行程時間
		if(action.equals("updateDate")){
			
			String cal_no = req.getParameter("cal_no");
			String cal_date = req.getParameter("cal_date");
			String requestURL = req.getParameter("requestURL");
			Timestamp t = Timestamp.valueOf(cal_date+" 00:00:00");
			
			String[] date = cal_date.split("-");
			LocalDate localDate = LocalDate.of(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2]));
			req.setAttribute("localDate", localDate);
			
			CalendarService calendarService = new CalendarService();
			calendarService.updateDate(cal_no, t);
			
			String url = requestURL;
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		// 更改年份、月份
		if(action.equals("changeCalendar")){
			int month = Integer.valueOf(req.getParameter("month"));
			int year = Integer.valueOf(req.getParameter("year"));
			LocalDate localDate = LocalDate.of(year, month, 1);
			req.setAttribute("localDate", localDate);
			String url = "/Front_end/calendar/calendar.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		
//		新增行程
		if(action.equals("addSchedule")){
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String cal_date = req.getParameter("cal_date");
			String content = req.getParameter("content");
			String requestURL = req.getParameter("requestURL");
			
			Timestamp t = null;
			try{
				t.valueOf(cal_date+" 00:00:00");
			}catch(IllegalArgumentException e){
				errorMsgs.add("請輸入正確的日期格式!");
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);
				return;
			}
			
			
			CalendarService calendarService = new CalendarService();
			calendarService.addCalendar("2001", content, Timestamp.valueOf(cal_date+" 00:00:00"),"0");

//			JSONObject j = new JSONObject();
//			try {
//				j.put("result", "success");
//				out.print(j);
//				return;
//			}catch (JSONException e) {
//				e.printStackTrace();
//			}		
			
			String[] date = cal_date.split("-");
			LocalDate localDate = LocalDate.of(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2]));
			req.setAttribute("localDate", localDate);
			RequestDispatcher successView = req.getRequestDispatcher(requestURL);
			successView.forward(req, res);
		}
		
//		刪除行程
		if(action.equals("deleteSchedule")){
			String cal_no = req.getParameter("cal_no");
			String requestURL = req.getParameter("requestURL");
			String[] date = req.getParameter("date").split("-");
			
			CalendarService calendarService = new CalendarService();
			calendarService.deleteCalendar(cal_no);
			
			LocalDate localDate = LocalDate.of(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2]));
			req.setAttribute("localDate", localDate);
			
			RequestDispatcher successView = req.getRequestDispatcher(requestURL);
			successView.forward(req, res);
			
		}
		
		
		if(action.equals("addScheduleAjax")){
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String cal_date = req.getParameter("cal_date");
			String content = req.getParameter("content");
			System.out.println(content);
//			Timestamp t = null;
//			try{
//				t.valueOf(cal_date+" 00:00:00");
//			}catch(IllegalArgumentException e){
//				errorMsgs.add("請輸入正確的日期格式!");
//				
//			}
			
			CalendarService calendarService = new CalendarService();
			calendarService.addCalendar("2001", content, Timestamp.valueOf(cal_date+" 00:00:00"),"0");
			
//			String[] date = cal_date.split("-");
//			LocalDate localDate = LocalDate.of(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2]));
//			req.setAttribute("localDate", localDate);
//			RequestDispatcher successView = req.getRequestDispatcher(requestURL);
//			successView.forward(req, res);
			
			JSONObject j = new JSONObject();
			try {
				j.put("result", "success");
				out.print(j);
			}catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
	}

}
