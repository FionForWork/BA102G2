package com.aut.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adm.model.AdmService;
import com.aut.model.AutService;
import com.aut.model.AutVO;


public class AutServlet extends HttpServlet{
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
				String adm_no = req.getParameter("adm_no").trim();
				String id = req.getParameter("id").trim();
				
				
				AutVO autVO = new AutVO();
				autVO.setAdm_no(adm_no);
				autVO.setId(id);
				
				

				// Send the use back to the form, if there were errors
				
				
				/***************************2.開始新增資料***************************************/
				AutService autSvc = new AutService();
				autVO = autSvc.addAut(adm_no,id);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/Back_end/aut/listAllAut.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/aut/addAut.jsp");
				failureView.forward(req, res);
			}
		
			
		}
		
		if ("delete".equals(action)) { // 來自listAllAdm.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String adm_no = req.getParameter("adm_no");
				String id = req.getParameter("id");
				
				/***************************2.開始刪除資料***************************************/
				AutService autSvc = new AutService();
				autSvc.deleteAut(adm_no,id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/Back_end/aut/listAllAut.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/aut/listAllAut.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
