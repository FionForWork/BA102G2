package com.serv.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.serv.model.ServService;
import com.serv.model.ServVO;


public class ServServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String stype_no = req.getParameter("stype_no").trim();
				String com_no = req.getParameter("com_no").trim();
				Integer deposit = new Integer(req.getParameter("deposit").trim());
				Integer price = new Integer(req.getParameter("price").trim());
				String title = req.getParameter("title").trim();
				String content = req.getParameter("content").trim();
				
				ServVO servVO = new ServVO();
				servVO.setStype_no(stype_no);
				servVO.setCom_no(com_no);
				servVO.setDeposit(deposit);
				servVO.setPrice(price);
				servVO.setTitle(title);
				servVO.setContent(content);
			
			
				/***************************2.開始新增資料***************************************/
				ServService servSvc = new ServService();
				servVO = servSvc.addServ(stype_no,com_no,deposit,price,title,content);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/Front_end/serv/listAllServ.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/serv/addServ.jsp");
				failureView.forward(req, res);
			}
		
			
		}
		
		
		
		
		
		
		
		
		
		
		
	}
}
