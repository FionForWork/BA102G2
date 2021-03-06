package com.aut.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adm.model.AdmService;
import com.aut.model.AutService;
import com.aut.model.AutVO;
import com.fun.model.FunService;
import com.fun.model.FunVO;


public class AutServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
	if("test".equals(action)){
		 AutService autSvc = new AutService();
		    List<AutVO> list = autSvc.getAll();
		    System.out.println(list.toString()+"---1");
		    System.out.println(list.get(0).getId()+"---2");
		    System.out.println(list.listIterator().next().getAdm_no()+"---3");
		    System.out.println(list.iterator().next().getId()+"---4");
		    System.out.println(list.toArray().toString()+"---5");
		    
		    
		    FunService funSvc = new FunService();
		    List<FunVO> list2 = funSvc.getAll();
		    System.out.println(list2.iterator().next().getName()+"---88888");
	}
		
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			AutService autSvc = new AutService();
			String adm_no = req.getParameter("adm_no").trim();
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				
				String[] aut=req.getParameterValues("id");
				 adm_no = req.getParameter("adm_no").trim();
				AutVO autVO = new AutVO();
				
				
				 for (int i = 0; i < aut.length; i++) {
					
					autVO.setAdm_no(adm_no);
					autVO.setId(aut[i]);
						
					autVO = autSvc.addAut(adm_no,aut[i]);
				 }

				 if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/Front_end/aut/addAut.jsp");
						failureView.forward(req, res);
						return;
					}
				

				// Send the use back to the form, if there were errors
				
				
				/***************************2.開始新增資料***************************************/
		
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/Back_end/aut/listAllAut.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				   Map<String,List> oneAll =autSvc.getOneAll(adm_no);
				errorMsgs.put("aut","權限重複,該管理員已有"+oneAll.values()+"權限,請勿重複");
				
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
