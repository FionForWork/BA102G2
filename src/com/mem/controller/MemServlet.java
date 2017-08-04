package com.mem.controller;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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

import com.com.model.ComService;
import com.com.model.ComVO;
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
	
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		
		//修改密碼
		if ("updatePwd".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
		
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				MemVO memVO = new MemVO();
				String	mem_no = req.getParameter("mem_no").trim();
				String oldpwd = req.getParameter("oldpwd").trim();
				String pwd = req.getParameter("pwd").trim();
				
				 MemService memSvc = new MemService();
				 MemVO a=memSvc.oldPwd(mem_no);		 
				 if(!oldpwd.equals(a.getPwd())){

					 String url = "/Front_end/mem/updatePwd.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listAllEmp.jsp
						successView.forward(req, res);	
				}else{
					memVO =memSvc.updatePwd(mem_no, pwd);
				}
				
				
	

			/***************************3.新增完成,準備轉交(Send the Success view)***********/
		
				String url = "/Front_end/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listAllEmp.jsp
				successView.forward(req, res);	
				/***************************其他可能的錯誤處理**********************************/

		}
		
		
		
		if ("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.invalidate();
			
			out.println("<HTML><HEAD><TITLE>登出</TITLE></HEAD>");
		      out.println("<BODY><h1>你的帳號已登出!<BR>");
		      out.println(" <A HREF="+req.getContextPath()+"/Front_end/login/login.jsp>返回</h1></A>");
		      out.println("</BODY></HTML>");
		}
		
		
		
		if ("login".equals(action)) {
		    // 【取得使用者 帳號(account) 密碼(password)】
			 String id = req.getParameter("id");//使用者輸入
			 String pwd = req.getParameter("pwd");
			  // 【檢查該帳號 , 密碼是否有效】
			
			 MemService memSvc = new MemService();
			 List<MemVO> list = memSvc.loginid();
			 List<MemVO> list1 = memSvc.loginpwd();
			
		

			 for(int i=0;i<list.size();i++){
				 if (list.get(i).getId().equals(id)) {
					 
					
					 for(int j=0;j<list1.size();j++){
						  
						 if (list1.get(j).getPwd().equals(pwd)) {
							
							 HttpSession session = req.getSession();
						      MemVO memVO = memSvc.getOneMemById(id);
						    
						      session.setAttribute("id", id);
						      session.setAttribute("memVO", memVO);
;
						      try {
						    	  String memlocation = (String) session.getAttribute("memlocation");
						          if (memlocation != null) {
						            session.removeAttribute("memlocation");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						            res.sendRedirect(memlocation);            
						            return;
						          }
						      }catch(Exception ignored){}
						      
						      res.sendRedirect(req.getContextPath()+"/Front_end/mem/index.jsp");
						 }

					 }
				      
				 }

			 }
			
			  
			 out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
		      out.println("<BODY>你的帳號 , 密碼無效!<BR>");
		      out.println("請按此重新登入 <A HREF="+req.getContextPath()+"/Front_end/login/login.jsp>重新登入</A>");
		      out.println("</BODY></HTML>");
			 
			 
		}
		
		
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
		

		if ("getOneDisplay".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String id = req.getParameter("id");
				
				// Send the use back to the form, if there were errors
				
				
				
				/***************************2.開始查詢資料*****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMemById(id);
				
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
				HttpSession session = req.getSession();
				session.setAttribute("id", id);
			    session.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
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
							.getRequestDispatcher("/Front_end/mem/listOneMem.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/

				MemService memSvc = new MemService();
				memVO = memSvc.updatePic(mem_no,picture);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/

				String url = "/Front_end/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listAllEmp.jsp
				successView.forward(req, res);	
				/***************************其他可能的錯誤處理**********************************/

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/mem/listOneMem.jsp");
				failureView.forward(req, res);
				
				
			}
			
		}
	
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_no = req.getParameter("mem_no").trim();
				String id = req.getParameter("id").trim();
							
				String name = req.getParameter("name").trim();	
				String sex = req.getParameter("sex").trim();
				
				java.sql.Date bday = null;
				bday = java.sql.Date.valueOf(req.getParameter("bday").trim());
				
				String phone = req.getParameter("phone").trim();
				String email = req.getParameter("email").trim();
				
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
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
	}
	
	
	
			
			
			
}
