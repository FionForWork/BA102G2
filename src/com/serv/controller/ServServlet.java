package com.serv.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mem.model.MemService;
import com.mem.model.MemVO;
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
		
		
		if ("selectByStype".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/
			String stype_no = req.getParameter("stype_no");

			/*************************** 2.開始查詢資料 ****************************************/
			ServService servSvc = new ServService();
			Set<ServVO> set = servSvc.getServByStype(stype_no);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("selectByStype", set);    // 資料庫取出的set物件,存入request
			
			String url = null;
			url = "/Back_end/serv/selectByStype.jsp";   
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("selectByCom".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/
			String com_no = req.getParameter("com_no");

			/*************************** 2.開始查詢資料 ****************************************/
			ServService servSvc = new ServService();
			Set<ServVO> set = servSvc.getServByCom(com_no);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("selectByCom", set);    // 資料庫取出的set物件,存入request
			
			String url = null;
			url = "/Back_end/serv/selectByCom.jsp";   
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
		
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String stype_no = req.getParameter("stype_no").trim();
				
				String com_no = req.getParameter("com_no").trim();
				
				Integer deposit = new Integer(req.getParameter("deposit").trim());
				if (deposit == null || deposit < 1) {
					errorMsgs.put("deposit","請大於0");
				}
				Integer price = new Integer(req.getParameter("price").trim());
				if (price == null || price <1) {
					errorMsgs.put("price","請大於0");
				}
				String title = req.getParameter("title").trim();
				if (title == null || (title.trim()).length() == 0) {
					errorMsgs.put("title","標題請勿空白");
				}
				String content = req.getParameter("content").trim();
				if (content == null || (content.trim()).length() == 0) {
					errorMsgs.put("content","內容請勿空白");
				}
				String status = req.getParameter("status").trim();
				ServVO servVO = new ServVO();
				servVO.setStype_no(stype_no);
				servVO.setCom_no(com_no);
				servVO.setDeposit(deposit);
				servVO.setPrice(price);
				servVO.setTitle(title);
				servVO.setContent(content);
				servVO.setStatus(status);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("servVO",servVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Front_end/serv/addServ.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/***************************2.開始新增資料***************************************/
				ServService servSvc = new ServService();
				servVO = servSvc.addServ(stype_no,com_no,deposit,price,title,content,status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/Front_end/serv/listAllServ.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			
		
			
		}
		
		
		
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String serv_no = req.getParameter("serv_no");
				
				
				/***************************2.開始查詢資料*****************************************/
				ServService servSvc = new ServService();
				ServVO servVO = servSvc.getOneServ(serv_no);
//				if (servVO == null) {
//					errorMsgs.add("查無資料");
//				}
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/Back_end/serv/select_Serv.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("servVO", servVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/serv/listOneServ.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/Back_end/serv/select_Serv.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		
		
		
		
	}
}
