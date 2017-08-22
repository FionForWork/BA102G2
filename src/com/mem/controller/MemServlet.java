package com.mem.controller;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.email.MailService;

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
							.getRequestDispatcher("/Front_end/mem/addPic.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/

				MemService memSvc = new MemService();
				memVO = memSvc.updatePic(mem_no,picture);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/

				String url = "/Front_end/mem/addPic.jsp";
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
		
		
		
		if ("updateStatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

				String mem_no = req.getParameter("mem_no").trim();
				
				String status = req.getParameter("status").trim();
				
				MemVO memVO = new MemVO();
				memVO.setMem_no(mem_no);
				memVO.setStatus(status);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/mem/listOneMem.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/

				MemService memSvc = new MemService();
				memVO = memSvc.updateStatus(mem_no, status);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/

				String url = "/Back_end/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listAllEmp.jsp
				successView.forward(req, res);	
				/***************************其他可能的錯誤處理**********************************/

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/mem/listOneMem.jsp");
				failureView.forward(req, res);
				
				
			}
			
		}
		
		
		if ("selectByReport".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/
			Integer report = new Integer(req.getParameter("report"));

			/*************************** 2.開始查詢資料 ****************************************/
			MemService memSvc = new MemService();
			Set<MemVO> set = memSvc.getMemsByReport(report);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("selectByReport", set);    // 資料庫取出的set物件,存入request
			
			String url = null;
			url = "/Back_end/mem/selectByReport.jsp";   
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("selectByStatus".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/
			String status = req.getParameter("status");

			/*************************** 2.開始查詢資料 ****************************************/
			MemService memSvc = new MemService();
			Set<MemVO> set = memSvc.getMemsByStatus(status);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("selectByStatus", set);    // 資料庫取出的set物件,存入request
			
			String url = null;
			url = "/Back_end/mem/selectByStatus.jsp";   
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("change".equals(action)){
			int passRandom = (int)(Math.random()*99999+1);  
			MemVO memVO = new MemVO();
			String id = req.getParameter("id").trim();
			MemService memSvc = new MemService();
			 memVO =memSvc.getOneMemById(id);
			 memSvc.updatePwd(memVO.getMem_no(),"B"+passRandom);
			

			 String to = id;
			 String subject = "忘記密碼";
				
		     String messageText = "你好! \n"+"B"+passRandom+" \n這是你的新密碼,請妥善保管,登入後建議馬上更改密碼 \n";
		      
		    
		       
		      MailService mailService = new MailService();
		      mailService.sendMail(to, subject, messageText);
		
		      res.sendRedirect(req.getContextPath()+"/Front_end/login/forgetPwdOk.jsp");
			    return;
		}
		
		if("forgetPwd".equals(action)){
			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			MemVO memVO = new MemVO();
			String id = req.getParameter("id").trim();
			
			 MemService memSvc = new MemService();
			 List<MemVO> list = memSvc.loginid();
			
			 for(int i=0;i<list.size();i++){
					
				 if (list.get(i).getId().equals(id)) {
					 memVO =memSvc.getOneMemById(id);
					 
					
					 String to = id;
					 String subject = "忘記密碼";
						
				     String messageText = "你好!請點選網址會發送一組新密碼給您!"+"http://localhost:8081/BA102G2/mem/mem.do?action=change&&id="+id;
				      
				    
				       
				      MailService mailService = new MailService();
				      mailService.sendMail(to, subject, messageText);
				      res.sendRedirect(req.getContextPath()+"/Front_end/login/forgetPwdOk.jsp");
					    return;
				 }
			 
			 
			 }
			 		
			 			errorMsgs.put("forgetPwdMem","請輸入正確帳號");
			 		
					
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/Front_end/login/forgetPwd.jsp");
						failureView.forward(req, res);
						return;
					}
			
		}
		
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
				 
				 if (oldpwd == null || (oldpwd.trim()).length() == 0 ||pwd == null || (pwd.trim()).length() == 0 ) {
						errorMsgs.add("新舊密碼請勿空白");
					}
				 if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/Front_end/mem/updatePwd.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				 
				 if(!oldpwd.equals(a.getPwd())){
					 errorMsgs.add("舊密碼錯誤更改失敗");
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
			//整個連線拔掉
			res.sendRedirect(req.getContextPath()+"/Front_end/login/homepage.jsp");
		    return;
		}
		
		
		
		if ("login".equals(action)) {
			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		    // 【取得使用者 帳號(account) 密碼(password)】
			 String id = req.getParameter("id");//使用者輸入
			 String pwd = req.getParameter("pwd");
			  // 【檢查該帳號 , 密碼是否有效】
			 String memslocation = req.getParameter("comslocation");
			 MemService memSvc = new MemService();
			 List<MemVO> list = memSvc.loginid();
			 List<MemVO> list1 = memSvc.loginpwd();
	
			 for(int i=0;i<list.size();i++){
				 if (list.get(i).getId().equals(id)) {
					 
					
					 for(int j=0;j<list1.size();j++){
						
						 if (pwd.equals(list1.get(j).getPwd())) {
							HttpSession session = req.getSession();
							 session.removeAttribute("id");
							 session.removeAttribute("memVO");

								
						      MemVO memVO = memSvc.getOneMemById(id);
						      String status=memVO.getStatus();
						      if(status.equals("停權")){
									res.sendRedirect(req.getContextPath()+"/Front_end/login/statusNotGood.jsp");
									return;
								}
						      session.setAttribute("id", id);
						      session.setAttribute("memVO", memVO);
						      session.setAttribute("role","mem");
						      
						      try {
						    	  String memlocation = (String) session.getAttribute("memlocation");
						          if (memlocation != null) {
						            session.removeAttribute("memlocation");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						            res.sendRedirect(memlocation);            
						            return;
						          }else if(memslocation != null){
						        	  System.out.println("我是沒經過過濾器的"+memslocation);
							        	 res.sendRedirect(memslocation);            
								         return;
						          }
						      }catch(Exception ignored){}
						      
						      res.sendRedirect(req.getContextPath()+"/Front_end/mem/listOneMem.jsp");
						      return;
						 }

					 }
				      
				 }

			 }
			 res.sendRedirect(req.getContextPath()+"/Front_end/login/errorLogin.jsp");
		      return;
			
		}
		
		//前端
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
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
			
		}
		//後端
		if ("bgetOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
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
			
		}
		
		//前
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
		

		if ("getOneDisplay".equals(action)) {

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
							.getRequestDispatcher("/Front_end/mem/listOneMem.jsp");
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
						.getRequestDispatcher("/Front_end/mem/listOneMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		//後
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
			
			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String id = req.getParameter("id").trim();
				String pwd = req.getParameter("pwd").trim();

				String name = req.getParameter("name").trim();
				if (name == null || (name.trim()).length() == 0) {
					errorMsgs.put("name","姓名請勿空白");
				}
				
				String sex = req.getParameter("sex").trim();
				
				java.sql.Date bday = null;
				try {
					bday = java.sql.Date.valueOf(req.getParameter("bday").trim());
				} catch (IllegalArgumentException e) {
					bday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("bday","請輸入生日");
				}
				
				String phone = req.getParameter("phone").trim();				
				if((phone.trim()).length()<6||(phone.trim()).length()>15){
					errorMsgs.put("phone","請輸入正確電話號碼");
					
				}
				
				String email = req.getParameter("email").trim();
				if (email == null || (email.trim()).length() == 0) {
					errorMsgs.put("email","如未有其他電子信箱請與帳號相同,請勿空白");
				}
				
				String account = req.getParameter("account").trim();
				if (account == null || (account.trim()).length() == 0) {
					errorMsgs.put("account","銀行帳戶請勿空白");
				}
				
				Part part = req.getPart("picture");
				InputStream in = part.getInputStream();
				byte[] picture = new byte[in.available()];
				in.read(picture);
				
				if(part.getSize()==0){
					errorMsgs.put("picture","請選擇圖片");
					}

				


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
				HttpSession session = req.getSession();
			     memVO = memSvc.getOneMemById(id);
			    
			      session.setAttribute("id", id);
			      session.setAttribute("memVO", memVO);
			
			
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				
				String url = "/Front_end/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			
			
		}
	
		
	
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_no = req.getParameter("mem_no").trim();
				String id = req.getParameter("id").trim();
							
				String name = req.getParameter("name").trim();	
				if (name == null || (name.trim()).length() == 0) {
					errorMsgs.put("name","姓名請勿空白");
				}
				String sex = req.getParameter("sex").trim();
				
				java.sql.Date bday = null;
				try {
					bday = java.sql.Date.valueOf(req.getParameter("bday").trim());
				} catch (IllegalArgumentException e) {
					bday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("bday","請輸入生日");
				}
				
				String phone = req.getParameter("phone").trim();
				if((phone.trim()).length()<6||(phone.trim()).length()>15){
					errorMsgs.put("phone","請輸入正確電話號碼");
					
				}
				String email = req.getParameter("email").trim();
				if (email == null || (email.trim()).length() == 0) {
					errorMsgs.put("email","如未有其他電子信箱請與帳號相同,請勿空白");
				}
				String account = req.getParameter("account").trim();
				if (account == null || (account.trim()).length() == 0) {
					errorMsgs.put("account","銀行帳戶請勿空白");
				}
			
				
				
				Integer report = new Integer(req.getParameter("report").trim());
				String status =req.getParameter("status").trim();
				
				Part part = req.getPart("picture");
				InputStream in = part.getInputStream();
				byte[] picture = new byte[in.available()];
				in.read(picture); 
				
				if(part.getSize()==0){
					errorMsgs.put("picture","請選擇圖片");
					}
				MemVO memVO = new MemVO();
				memVO.setMem_no(mem_no);
				memVO.setId(id);
			
				memVO.setName(name);
				memVO.setSex(sex);
				memVO.setBday(bday);
				memVO.setPhone(phone);
				memVO.setEmail(email);
				memVO.setAccount(account);
				memVO.setPicture(picture);
				memVO.setReport(report);
				memVO.setStatus(status);
					
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO",memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Front_end/mem/updatemember.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(mem_no, id, name, sex,bday,phone,email,account,picture,report,status);
				 HttpSession session = req.getSession();
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				 session.setAttribute("memVO",memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/Front_end/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			
		}
		
		
		
		
		
		
	}
	
	
	
			
			
			
}
