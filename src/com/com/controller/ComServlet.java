package com.com.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.adm.model.AdmService;
import com.com.model.ComService;
import com.com.model.ComVO;
//還沒寫完

@WebServlet("/com/ComServlet.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 15 * 1024 * 1024, maxRequestSize = 5 * 15 * 1024 * 1024)
public class ComServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
	
        if ("insert".equals(action)) {   
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String id = req.getParameter("id").trim();
				String pwd = req.getParameter("pwd").trim();
				String name = req.getParameter("name").trim();
				String loc = req.getParameter("loc").trim();
				String lon = req.getParameter("lon").trim();
				String lat = req.getParameter("lat").trim();
				String com_desc = req.getParameter("com_desc").trim();
				String phone = req.getParameter("phone").trim();
				String account = req.getParameter("account").trim();
				
				Part part = req.getPart("logo");
				InputStream in = part.getInputStream();
				byte[] logo = new byte[in.available()];
				in.read(logo);
				
				String status = req.getParameter("status").trim();
				


				ComVO comVO = new ComVO();
				comVO.setId(id);
				comVO.setPwd(pwd);
				comVO.setName(name);
				comVO.setLoc(loc);
				comVO.setLon(lon);
				comVO.setLat(lat);
				comVO.setLogo(logo);
				comVO.setAccount(account);
				comVO.setCom_desc(com_desc);
				comVO.setPhone(phone);
				comVO.setStatus(status);
				
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("comVO", comVO);  // 新增成功後轉交listAllEmp.jsp
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Front_end/com/addCom.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				ComService comSvc = new ComService();
				comVO = comSvc.addCom(id, pwd, name, loc,lon,lat,com_desc,phone,account,logo,status);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/Front_end/com/listAllCom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/com/addCom.jsp");
				failureView.forward(req, res);
			}
		}
		
        
        
		
	}
}
