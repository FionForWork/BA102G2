package com.report.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.report.model.ReportVO;
import com.report.model.Report_Service;
import com.report_type.model.Report_typeVO;

public class ReportServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		HttpSession session=req.getSession();
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String position = req.getParameter("position").trim();
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				Integer rep_ob_no = new Integer(req.getParameter("rep_ob_no").trim());
				Integer reporter_no = new Integer(req.getParameter("reporter_no").trim());
				Integer reported_no = new Integer(req.getParameter("reported_no").trim());
				Integer rep_type_no = new Integer(req.getParameter("rep_type_no").trim());
				String content = req.getParameter("content").trim();
				Integer status = new Integer(req.getParameter("status").trim());
				
				
				ReportVO reportVO =new ReportVO();
				java.util.Date date = new java.util.Date();
				long date1 = date.getTime();
				Date rep_date = new Date(date1);
				
				reportVO.setRep_ob_no(rep_ob_no);
				reportVO.setReporter_no(reporter_no);
				reportVO.setReported_no(reported_no);
				reportVO.setRep_type_no(rep_type_no);
				reportVO.setContent(content);
				reportVO.setRep_date(rep_date);
				reportVO.setStatus(status);

				/***************************2.開始新增資料***************************************/
				Report_Service rep_Svc=new Report_Service();
				reportVO=rep_Svc.addReport(rep_ob_no, reporter_no, reported_no, rep_type_no, content, rep_date, status);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
			
				String url = position;
				System.out.println(url);
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher(position);
				failureView.forward(req, res);
			}
		}
		
	}	
		
}
