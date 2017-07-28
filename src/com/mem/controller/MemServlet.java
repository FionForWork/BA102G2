package com.mem.controller;
import java.io.ByteArrayOutputStream;

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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.adm.model.AdmService;
import com.adm.model.AdmVO;

import com.mem.model.MemService;
import com.mem.model.MemVO;

@WebServlet("/mem/MemServlet.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 15 * 1024 * 1024, maxRequestSize = 5 * 15 * 1024 * 1024)

public class MemServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String mem_no =req.getParameter("mem_no");
				
				/***************************2.開始查詢資料****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memVO", memVO);         // 資料庫取出的empVO物件,存入req
				String url = "/Front_end/mem/updatemember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/mem/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_no = req.getParameter("mem_no");
				
				// Send the use back to the form, if there were errors
				
				
				
				/***************************2.開始查詢資料*****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_no);
				if (memVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Front_end/mem/listAllMem.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
				String url = "/Front_end/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
		if ("bgetOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_no = req.getParameter("mem_no");
				
				// Send the use back to the form, if there were errors
				
				
				
				/***************************2.開始查詢資料*****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_no);
				if (memVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/mem/listAllMem.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	
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
				String sex = req.getParameter("sex").trim();
				
				java.sql.Date bday = null;
				try {
					bday = java.sql.Date.valueOf(req.getParameter("bday").trim());
				} catch (IllegalArgumentException e) {
					bday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入生日");
				}
				
				String phone = req.getParameter("phone").trim();
				String email = req.getParameter("email").trim();
				String account = req.getParameter("account").trim();

				Part part = req.getPart("picture");
				InputStream in = part.getInputStream();
				byte[] picture = new byte[in.available()];
				in.read(picture);
				

				


				MemVO memVO = new MemVO();
				memVO.setId(id);
				memVO.setPwd(pwd);
				memVO.setName(name);
				memVO.setSex(sex);
				memVO.setBday(bday);
				memVO.setPhone(phone);
				memVO.setEmail(email);
				memVO.setAccount(account);
				memVO.setPicture(picture);

				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Front_end/mem/register.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.addMem(id, pwd, name, sex, bday, phone,email,account,picture);
				
				//session.setAttribute("memVO", memVO);
			
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				req.setAttribute("memVO", memVO);
				String url = "/Front_end/mem/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/mem/register.jsp");
				failureView.forward(req, res);
				
				
			}
			
		}
	
		if ("updatePic".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

				String mem_no = req.getParameter("mem_no").trim();
				
				Part part = req.getPart("picture");
				InputStream in = part.getInputStream();
				byte[] picture = new byte[in.available()];
				in.read(picture); 
				
				MemVO memVO = new MemVO();
				memVO.setMem_no(mem_no);
				memVO.setPicture(picture);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Front_end/mem/addMem.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/

				MemService memSvc = new MemService();
				memVO = memSvc.updatePic(mem_no,picture);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/

				String url = "/Front_end/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listAllEmp.jsp
				successView.forward(req, res);	
				/***************************其他可能的錯誤處理**********************************/

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/mem/addMem.jsp");
				failureView.forward(req, res);
				
				
			}
			
		}
	
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_no = req.getParameter("mem_no").trim();
				String id = req.getParameter("id").trim();
							
				String name = req.getParameter("name").trim();	
				String sex = req.getParameter("sex").trim();
				
				java.sql.Date bday = null;
				bday = java.sql.Date.valueOf(req.getParameter("bday").trim());
				
				String phone = req.getParameter("phone").trim();
				String email = req.getParameter("phone").trim();
				
				String account = req.getParameter("account").trim();
				
			
				
				
				Integer report = new Integer(req.getParameter("report").trim());
				String status =req.getParameter("status").trim();
				
				

				MemVO memVO = new MemVO();
				memVO.setMem_no(mem_no);
				memVO.setId(id);
			
				memVO.setName(name);
				memVO.setSex(sex);
				memVO.setBday(bday);
				memVO.setPhone(phone);
				memVO.setEmail(email);
				memVO.setAccount(account);
			
				memVO.setReport(report);
				memVO.setStatus(status);
					
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO",memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Front_end/mem/index.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(mem_no, id, name, sex,bday,phone,email,account,report,status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO",memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/Front_end/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/Front_end/mem/listAllMem.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		
		
		
		
	}
	
	
	
			
			
			
}
