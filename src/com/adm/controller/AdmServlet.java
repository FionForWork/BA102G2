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
import javax.servlet.http.HttpSession;

import com.adm.model.AdmService;
import com.adm.model.AdmVO;
import com.aut.model.AutService;
import com.aut.model.AutVO;
import com.email.MailService;
import com.mem.model.MemService;
import com.mem.model.MemVO;



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
		
		
		
		
		if ("updatePwd".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
		
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				AdmVO admVO = new AdmVO();
				String	adm_no = req.getParameter("adm_no").trim();
				String old = req.getParameter("oldpwd").trim();  //s管理員輸入的舊密碼
				String news = req.getParameter("pwd").trim(); // 管理員輸入的新的密碼
				
				int	var2 = Integer.valueOf(news)*36+77;//新密碼轉乘INT 加密
				String pwd=	Integer.toString(var2);//加密過新密碼轉回字串
				
				AdmService admSvc =new AdmService();
				AdmVO a =admSvc.oldPwd(adm_no);//資料庫拉出來資料的舊密碼
				int	var =Integer.valueOf(old)*36+77; //s管理員輸入的加密
				String oldpwd=	Integer.toString(var);//轉成字串
				
				 if (oldpwd ==null|| (oldpwd.trim()).length() == 0 ||pwd == null || (pwd.trim()).length() == 0 ) {
						errorMsgs.add("新舊密碼請勿空白或是少於5碼");
					}
				 if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/Back_end/login/updatePwd.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				 
				 if(!oldpwd.equals(a.getPwd())){//對比輸入的舊密碼
					 errorMsgs.add("舊密碼錯誤更改失敗");
					 String url = "/Back_end/login/updatePwd.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); 
						successView.forward(req, res);	
				}else{
					admVO =admSvc.updatePwd(adm_no, pwd);//對的話就存入管理員加密新密碼
				}
				
				
	

			/***************************3.新增完成,準備轉交(Send the Success view)***********/
		
				String url = "/Back_end/adm/listOneAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);	
				/***************************其他可能的錯誤處理**********************************/

		}
		
		
		
		
		
		
		if ("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.invalidate();
			//整個連線拔掉
			res.sendRedirect(req.getContextPath()+"/Back_end/login/login.jsp");
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
			AutService autSvc = new AutService();
			 AdmService admSvc = new AdmService();
			 List<AdmVO> list = admSvc.loginid();
			 
			
			
			 for(int i=0;i<list.size();i++){
				 if (list.get(i).getId().equals(id)) {
					 
					AdmVO pp= admSvc.getOneAdmById(id);
					String notRealPwd =pp.getPwd();
					int var=(Integer.valueOf(notRealPwd)-77)/36;
					String RealPwd =Integer.toString(var);
					System.out.println("notRealPwd : "+notRealPwd);
					System.out.println("RealPwd : "+RealPwd);
					System.out.println();
					if (RealPwd.equals(pwd)) {
							HttpSession session = req.getSession();
							 session.removeAttribute("id");
							 session.removeAttribute("admVO");

								
							AdmVO admVO = admSvc.getOneAdmById(id);
						      String status=admVO.getStatus();
						      if(status.equals("停權")){
									res.sendRedirect(req.getContextPath()+"/Back_end/login/statusNotGood.jsp");
									return;
								}
						   
						      session.setAttribute("id", id);
						      session.setAttribute("admVO", admVO);
						      Map<String,List> oneAll =autSvc.getOneAll(admVO.getAdm_no());
								 
						      System.out.println("oneAll---"+oneAll);
								 System.out.println("oneAll.size()---"+oneAll.size());
								 System.out.println( oneAll.values().iterator().next().contains("01"));
										for(int a = 0;a<oneAll.values().iterator().next().size();a++){
											 System.out.println(oneAll.values().iterator().next().get(a));
											 System.out.println(oneAll.values().iterator().next());
											 session.setAttribute("aut", oneAll.values().iterator().next());
										}
									
								
						      System.out.println("sssssssssss");
						      try {
						    	  String admlocation = (String) session.getAttribute("admlocation");
						          if (admlocation != null) {
						            session.removeAttribute("admlocation");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						            res.sendRedirect(admlocation);            
						            return;
						          }
						      }catch(Exception ignored){}
						      
						      res.sendRedirect(req.getContextPath()+"/Back_end/mem/listAllMem.jsp");
						      return;
						 }

					 
				      
				 }

			 }
			 res.sendRedirect(req.getContextPath()+"Back_end/adm/errerlogin.jsp");
		      return;
			
		}
		
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
				
				String realpwd = passRandom+"";
				
				 int admPwd=admPwd(passRandom);
				
				String pwd = admPwd+"";
				
				
				String name =req.getParameter("name").trim();
				if (name == null || (name.trim()).length() == 0 ) {
					 errorMsgs.put("name","姓名請勿空白");
					}
				String job =req.getParameter("job").trim();
				String status =req.getParameter("status").trim();
				
				AdmVO admVO = new AdmVO();
				
				admVO.setId(id);
				admVO.setPwd(pwd.trim());
				admVO.setName(name);
				admVO.setJob(job);
				admVO.setStatus(status);
				

				// Send the use back to the form, if there were errors
				
				
				/***************************2.開始新增資料***************************************/
				AdmService admSvc = new AdmService();
				admVO = admSvc.addAdm(id, pwd.trim(), name, job, status);
				 String to = id;
				 String subject = "管理員密碼";
				 
			     String messageText = "你好! \n"+realpwd.trim()+" \n這是你的密碼,請妥善保管 \n";
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
				String url = "/Back_end/adm/listAllAdm.jsp";
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
