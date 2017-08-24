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

		if ("listcoms_ByCompositeQuery".equals(action)) {

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

				ComService comSvc = new ComService();
				List<ComVO> list  = comSvc.getAll(com_map);
				
				req.setAttribute("listCom_ByCompositeQuery", list);
				String requestURL = req.getParameter("requestURL");
				if(requestURL.equals("/Front_end/com_page/select_company.jsp")) {
					RequestDispatcher successView = req.getRequestDispatcher("/Front_end/com_page/select_company.jsp");
				}
				RequestDispatcher successView = req.getRequestDispatcher("/Front_end/com_page/all_company.jsp");
				successView.forward(req, res);

		}
		
		if ("listAll".equals(action)) {
			String sort = req.getParameter("sort");
			System.out.println("SORT:"+sort);
			
			
			Map<String,String> com_map = new LinkedHashMap<String,String>();
			com_map.put("sort", sort);
			req.setAttribute("com_map", com_map);
			
			ComService comSvc = new ComService();
			List<ComVO> comList  = null;
			
			ServService servSvc = new ServService();
			List<ServVO> servList  = null;
			
			if(sort.startsWith("date")){
				
				comList  = comSvc.getAll();
				
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
				req.setAttribute("listCom_ByCompositeQuery", comList);
				
			} else {
					
				servList  = servSvc.getAllAvg();
					
				if(sort.equals("scoreDesc")){
					Collections.reverse(servList);
				}
				req.setAttribute("listCom_ByCompositeQuery", servList);
			}

			RequestDispatcher successView = req.getRequestDispatcher("/Front_end/com_page/all_company.jsp");
			successView.forward(req, res);

		}
	}

}