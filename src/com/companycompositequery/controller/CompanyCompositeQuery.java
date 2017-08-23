package com.companycompositequery.controller;

import java.io.*;
import java.util.*;
import com.com.model.*;
import com.quote.model.QuoteVO;
import com.serv.model.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CompanyCompositeQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("listcoms_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> com_map = (Map<String, String[]>)session.getAttribute("com_map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("com_map",map2);
					com_map = (HashMap<String, String[]>)req.getParameterMap();
					System.out.println(com_map.keySet());
				} 
				
				/***************************2.開始複合查詢***************************************/
				ComService comSvc = new ComService();
				List<ComVO> list  = comSvc.getAll(com_map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				
					req.setAttribute("listCom_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
					RequestDispatcher successView = req.getRequestDispatcher("/Front_end/com_page/all_company.jsp");
					successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/com_page/all_company.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listAll".equals(action)) {
			String sort = req.getParameter("sort");
			System.out.println(sort);
			
			ComService comSvc = new ComService();
			List<ComVO> comList  = comSvc.getAll();
			
			if(sort.startsWith("date")){
				Collections.sort(comList, new Comparator<ComVO>(){
					@Override
					public int compare(ComVO comVO1, ComVO comCO2) {
						int index = comVO1.getCom_no().compareTo(comCO2.getCom_no());
						return index;
					}	
				});
				if(sort.equals("dateDesc")){
					Collections.reverse(comList);
				}
			}
			req.setAttribute("listCom_ByCompositeQuery", comList);

			RequestDispatcher successView = req.getRequestDispatcher("/Front_end/com_page/all_company.jsp");
			successView.forward(req, res);

		}
		if ("listAllByScore".equals(action)) {
			String sort = req.getParameter("sort");
			System.out.println(sort);
			
			ServService servSvc = new ServService();
			List<ServVO> servList  = servSvc.getAllAvg();
			
			if(sort.startsWith("scoreDesc")){
				Collections.reverse(servList);
			}
			req.setAttribute("listCom_ByCompositeQuery", servList);
			
			RequestDispatcher successView = req.getRequestDispatcher("/Front_end/com_page/all_company.jsp");
			successView.forward(req, res);
			
		}
	}

}
