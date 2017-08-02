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
import com.mem.model.MemService;
import com.mem.model.MemVO;

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
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		if ("login".equals(action)) {
		    // 【取得使用者 帳號(account) 密碼(password)】
			 String id = req.getParameter("id");//使用者輸入
			 String pwd = req.getParameter("pwd");
		
			  // 【檢查該帳號 , 密碼是否有效】
			 

			 ComService comSvc = new ComService();
			 List<ComVO> list = comSvc.loginid();
			 List<ComVO> list1 = comSvc.loginpwd();
			
			 for(int i=0;i<list.size();i++){
				
				 if (list.get(i).getId().equals(id)) {
					
					
					 for(int j=0;j<list1.size();j++){
						
						 if (list1.get(j).getPwd().equals(pwd)) {
							 
							 HttpSession session = req.getSession();
						      ComVO comVO = comSvc.getOneComById(id);
						      session.setAttribute("id", id);
						      session.setAttribute("comVO", comVO);
						      
						      try {
						    	  String location = (String) session.getAttribute("location");
						          if (location != null) {
						            session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						            res.sendRedirect(location);            
						            return;
						          }
						      }catch(Exception ignored){}
						      
						      res.sendRedirect(req.getContextPath()+"/Front_end/com/index.jsp");
						 }

					 }
				      
				 }

			 }
			 
			// res.sendRedirect(req.getContextPath()+"/Front_end/login/loginerror.jsp");
			 
			 out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
		      out.println("<BODY>你的帳號 , 密碼無效!<BR>");
		      out.println("請按此重新登入 <A HREF="+req.getContextPath()+"/Front_end/login/login.jsp>重新登入</A>");
		      out.println("</BODY></HTML>");
			 
			 
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
				comVO = comSvc.addCom(id, pwd, name, loc,lon,lat,com_desc,phone,account,logo);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/Front_end/com/index.jsp";
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
		
        
        if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String com_no =req.getParameter("com_no");
				
				/***************************2.開始查詢資料****************************************/
				ComService comSvc = new ComService();
				ComVO comVO = comSvc.getOneCom(com_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("comVO", comVO);         // 資料庫取出的empVO物件,存入req
				String url = "/Front_end/mem/updatemember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/mem/listAllCom.jsp");
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
				ComService comSvc = new ComService();
				ComVO comVO = comSvc.getOneComById(id);
				
				if (comVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Front_end/com/listAllMem.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				HttpSession session = req.getSession();
				session.setAttribute("id", id);
			    session.setAttribute("comVO", comVO); // 資料庫取出的empVO物件,存入req
				String url = "/Front_end/com/listOneCom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/com/listAllCom.jsp");
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
				String com_no = req.getParameter("com_no").trim();
				String id = req.getParameter("id").trim();
							
				String name = req.getParameter("name").trim();	
				String loc = req.getParameter("loc").trim();
				String lon = req.getParameter("lon").trim();
				String lat = req.getParameter("lat").trim();
				String com_desc = req.getParameter("com_desc").trim();
				String phone = req.getParameter("phone").trim();
				String account = req.getParameter("account").trim();
				String status =req.getParameter("status").trim();
				
				

				ComVO comVO = new ComVO();
				comVO.setCom_no(com_no);
				comVO.setId(id);
			
				comVO.setName(name);
				comVO.setLon(lon);
				comVO.setLoc(loc);
				comVO.setLat(lat);
				comVO.setPhone(phone);
				comVO.setCom_desc(com_desc);
				comVO.setAccount(account);
			
				comVO.setStatus(status);
					
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("comVO",comVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Front_end/com/index.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ComService comSvc = new ComService();
				comVO = comSvc.updateCom(com_no, id, name,loc,lon,lat,com_desc,phone,account,status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("comVO",comVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/Front_end/com/listAllcom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/com/listAllCom.jsp");
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
		
				String com_no = req.getParameter("com_no").trim();
				
				Part part = req.getPart("logo");
				InputStream in = part.getInputStream();
				byte[] logo = new byte[in.available()];
				in.read(logo); 
				
				ComVO comVO = new ComVO();
				comVO.setCom_no(com_no);
				comVO.setLogo(logo);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("comVO", comVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Front_end/com/addPic.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
		
				ComService comSvc = new ComService();
				comVO = comSvc.updatePic(com_no,logo);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
		
				String url = "/Front_end/com/listOneCom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listAllEmp.jsp
				successView.forward(req, res);	
				/***************************其他可能的錯誤處理**********************************/
		
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/com/addPic.jsp");
				failureView.forward(req, res);
				
				
			}
			
		}

        
        
	}
}
