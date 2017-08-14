package com.adm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adm.model.AdmService;
import com.adm.model.AdmVO;
import com.aut.model.AutService;
import com.aut.model.AutVO;
import com.email.MailService;



public class AdmServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	
	public int admPwd(int passRandom) {
		int random=passRandom*36+77;

		return random;
	}
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		
		
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String id = req.getParameter("id").trim();
				 if (id == null || (id.trim()).length() == 0 ) {
					 errorMsgs.put("id","帳號為電子信箱,請勿空白");
					}
				 
				 int passRandom = (int)(Math.random()*9999+1);
				
				String realpwd = "h"+passRandom;
				
				 int admPwd=admPwd(passRandom);
				
				String pwd = "h"+admPwd;
				
				
				String name =req.getParameter("name").trim();
				if (name == null || (name.trim()).length() == 0 ) {
					 errorMsgs.put("name","姓名請勿空白");
					}
				String job =req.getParameter("job").trim();
				String status =req.getParameter("status").trim();
				
				AdmVO admVO = new AdmVO();
				
				admVO.setId(id);
				admVO.setPwd(pwd);
				admVO.setName(name);
				admVO.setJob(job);
				admVO.setStatus(status);
				

				// Send the use back to the form, if there were errors
				
				
				/***************************2.開始新增資料***************************************/
				AdmService admSvc = new AdmService();
				admVO = admSvc.addAdm(id, pwd, name, job, status);
				 String to = id;
				 String subject = "管理員密碼";
				 
			     String messageText = "你好! \n"+realpwd+" \n這是你的密碼,請妥善保管 \n";
			     MailService mailService = new MailService();
			      mailService.sendMail(to, subject, messageText);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/Back_end/adm/listAllAdm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			
		
			
		}
		
		if ("delete".equals(action)) { // 來自listAllAdm.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String adm_no = new String(req.getParameter("adm_no"));
				
				/***************************2.開始刪除資料***************************************/
				AdmService admSvc = new AdmService();
				admSvc.deleteAdm(adm_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/adm/listAllAdm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/adm/listAllAdm.jsp");
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
				String adm_no = req.getParameter("adm_no").trim();
				String id = req.getParameter("id").trim();
				String pwd = req.getParameter("pwd").trim();				
				String name = req.getParameter("name").trim();	
				String job = req.getParameter("job").trim();	
				String status = req.getParameter("status").trim();	

				

				AdmVO admVO = new AdmVO();
				admVO.setAdm_no(adm_no);
				admVO.setId(id);
				admVO.setPwd(pwd);
				admVO.setName(name);
				admVO.setJob(job);
				admVO.setStatus(status);
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("admVO",admVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/adm/updateadminput.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				AdmService admSvc = new AdmService();
				admVO = admSvc.updateAdm(adm_no, id, pwd, name, job,status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("admVO", admVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/Back_end/adm/listOneAdm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/adm/updateadminput.jsp");
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
				String adm_no = req.getParameter("adm_no").trim();
				
				/***************************2.開始查詢資料****************************************/
				AdmService admSvc = new AdmService();
				AdmVO admVO = admSvc.getOneAdm(adm_no);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("admVO", admVO);         // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/adm/updateadminput.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_adm_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/Back_end/adm/listAllAdm.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
	
	
}
