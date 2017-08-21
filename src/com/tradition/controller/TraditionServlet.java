package com.tradition.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.problem.model.ProblemVO;
import com.problem.model.Problem_Service;
import com.tradition.model.TraditionVO;
import com.tradition.model.Tradition_Service;



public class TraditionServlet  extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		HttpSession session=req.getSession();
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer tra_no = new Integer(req.getParameter("tra_no"));
				
				/***************************2.開始查詢資料****************************************/
				Tradition_Service traditionSvc = new Tradition_Service();
				TraditionVO traditionVO = traditionSvc.getOneTradition(tra_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("traditionVO", traditionVO);         
				String url = "/Back_end/tradition/update_tradition_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/tradition/Traditionall.jsp");
				failureView.forward(req, res);
			}
		}
//		*****************************************************************************************************************
		
		
		
		if ("update".equals(action)) { 
			System.out.println(action);
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer tra_no = new Integer(req.getParameter("tra_no").trim());
				Integer tra_type_no = new Integer(req.getParameter("tra_type_no").trim());
				Integer tra_order = new Integer(req.getParameter("tra_order").trim());
				String title = req.getParameter("title").trim();
				String article = req.getParameter("article").trim();	
				

				TraditionVO traditionVO = new TraditionVO();
				traditionVO.setTra_no(tra_no);
				traditionVO.setTra_type_no(tra_type_no);
				traditionVO.setTra_order(tra_order);
				traditionVO.setTitle(title);
				traditionVO.setArticle(article);
				

				
				
				/***************************2.開始修改資料*****************************************/
				Tradition_Service traditionSvc = new Tradition_Service();
				traditionVO = traditionSvc.updateTradition(tra_no,tra_type_no, tra_order, title, article );
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("traditionVO", traditionVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/Back_end/tradition/Traditionall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/tradition/update_tradition_input.jsp");
				failureView.forward(req, res);
			}
		}
//		**************************************************************************************************************
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			System.out.println(action);
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				Integer tra_type_no = new Integer(req.getParameter("tra_type_no").trim());
				Integer tra_order = new Integer(req.getParameter("tra_order").trim());
				String title = req.getParameter("title").trim();
				String article = req.getParameter("article").trim();	
				

				TraditionVO traditionVO = new TraditionVO();
				
				traditionVO.setTra_type_no(tra_type_no);
				traditionVO.setTra_order(tra_order);
				traditionVO.setTitle(title);
				traditionVO.setArticle(article);

				
				
				
				/***************************2.開始新增資料***************************************/
				Tradition_Service traditionSvc = new Tradition_Service();
				traditionVO = traditionSvc.addTradition(tra_type_no, tra_order, title, article);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/Back_end/tradition/Traditionall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/tradition/add_tradition.jsp");
				failureView.forward(req, res);
			}
		}
//		**************************************************************************************************************
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer tra_no = new Integer(req.getParameter("tra_no"));
				
				/***************************2.開始刪除資料***************************************/
				Tradition_Service traditionSvc = new Tradition_Service();
				traditionSvc.deleteTradition(tra_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/Back_end/tradition/Traditionall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/tradition/Traditionall.jsp");
				failureView.forward(req, res);
			}
	}
//		******************************************************************************************************************
		if ("get_OneAll".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer tra_type_no = new Integer(req.getParameter("tra_type_no"));
				System.out.println(tra_type_no);
				/***************************2.開始查詢資料****************************************/
				Tradition_Service traditionSvc = new Tradition_Service();
				List<TraditionVO> traditionlist = (List<TraditionVO>) traditionSvc.getOneAll(tra_type_no);
				System.out.println(traditionlist.size());
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				session.setAttribute("traditionlist", traditionlist);         
				String url = "/Front_end/Tradition/TraditionAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/Tradition/xxTradition");
				failureView.forward(req, res);
			}
		}
//		******************************************************************************************************************
		if ("get_All".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				
				/***************************2.開始查詢資料****************************************/
				Tradition_Service traditionSvc = new Tradition_Service();
				List<TraditionVO> traditionlist = (List<TraditionVO>) traditionSvc.getAll();
				
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				session.setAttribute("traditionlist", traditionlist);         
				String url = "/Front_end/Tradition/TraditionAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/Tradition/xxTradition");
				failureView.forward(req, res);
			}
		}

}
}
