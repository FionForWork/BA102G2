package com.mem.controller;
//還沒寫完
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mem.model.MemService;
import com.mem.model.MemVO;

@WebServlet("/mem/MemServlet.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 15 * 1024 * 1024, maxRequestSize = 5 * 15 * 1024 * 1024)

public class MemServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
	
		if ("insert".equals(action)) { // 來自addEMem.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String id = req.getParameter("id").trim();
				String pwd = req.getParameter("pwd").trim();
				String name = req.getParameter("name").trim();
				String sex = req.getParameter("sex").trim();
				
				java.sql.Date bday = null;
				try {
					bday = java.sql.Date.valueOf(req.getParameter("bday").trim());
				} catch (IllegalArgumentException e) {
					bday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String phone = req.getParameter("phone").trim();
				String email = req.getParameter("email").trim();
				String account = req.getParameter("account").trim();

				Part part = req.getPart("picture");
				InputStream in = part.getInputStream();
				byte[] img = new byte[in.available()];
				in.read(img);
				

				


				MemVO memVO = new MemVO();
				memVO.setId(id);
				memVO.setPwd(pwd);
				memVO.setName(name);
				memVO.setSex(sex);
				memVO.setBday(bday);
				memVO.setPhone(phone);
				memVO.setEmail(email);
				memVO.setAccount(account);
				memVO.setImg(img);

				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/addMem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.addMem(id, pwd, name, sex, bday, phone,email,account,img);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/mem/addMem.jsp");
//				failureView.forward(req, res);
//			}
		}
	
	
	
	}
}
