package com.com.controller;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.com.model.ComService;
import com.com.model.ComVO;
import com.email.MailService;




@WebServlet("/com/ComServlet.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 15 * 1024 * 1024, maxRequestSize = 5 * 15 * 1024 * 1024)
public class ComServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String[] answer;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	 
	
	public String[] latlng(String loc)
	{
	        String xx="",yy="";
	        String sKeyWord = loc;
	        URL url  = null;//p=%s is KeyWord in
	        try {
	            url = new URL(String.format("http://maps.googleapis.com/maps/api/geocode/json?address=%s&sensor=false&language=zh-TW",
	                    URLEncoder.encode(sKeyWord, "UTF-8")));
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        URLConnection connection = null;
	        try {
	            connection = url.openConnection();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        String line;
	        StringBuilder builder = new StringBuilder();
	        BufferedReader reader = null;
	        try {
	            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        try {
	            while ((line = reader.readLine()) != null) {builder.append(line);}
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        JSONObject json = null; //轉換json格式
	        try {
	            json = new JSONObject(builder.toString());
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        JSONArray ja = null;//取得json的Array物件
	        try {
	            ja = json.getJSONArray("results");
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        for (int z = 0; z < ja.length(); z++) {
	            try {
	                xx=String.valueOf(ja.getJSONObject(z).getJSONObject("geometry").getJSONObject("location").getDouble("lat"));
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
	            try {
	                yy=String.valueOf(ja.getJSONObject(z).getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
	        }

	     //回傳String[] 陣列
	        String[] answer=new  String[2];
	        answer[0]=xx;//緯度
	        answer[1]=yy;//經度
	        this.answer=answer;
	        return answer;
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
		
				String url = "/Front_end/com/addPic.jsp";
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
		
		if ("updateStatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		
				String com_no = req.getParameter("com_no").trim();
				String status = req.getParameter("status").trim();
				 
				
				ComVO comVO = new ComVO();
				comVO.setCom_no(com_no);
				comVO.setStatus(status);
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("comVO", comVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/com/listOneCom.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
		
				ComService comSvc = new ComService();
				comVO = comSvc.updateStatus(com_no, status);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
		
				String url = "/Back_end/com/listAllCom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listAllEmp.jsp
				successView.forward(req, res);	
				/***************************其他可能的錯誤處理**********************************/
		
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/com/listOneCom.jsp");
				failureView.forward(req, res);
				
				
			}
			
		}
		
		
		if("change".equals(action)){
			int passRandom = (int)(Math.random()*99999+1);  
			ComVO comVO = new ComVO();
			String id = req.getParameter("id").trim();
			 ComService comSvc = new ComService();
			 comVO =comSvc.getOneComById(id);
			

			 String to = id;
			 String subject = "忘記密碼";
			 comSvc.updatePwd(comVO.getCom_no(),"b"+passRandom);
				
		     String messageText = "你好! \n"+"b"+passRandom+" \n這是你的新密碼,請妥善保管,登入後建議馬上更改密碼 \n";
		      
		    
		       
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
			
			ComVO comVO = new ComVO();
			String id = req.getParameter("id").trim();
			
			 ComService comSvc = new ComService();
			 List<ComVO> list = comSvc.loginid();
			
			 for(int i=0;i<list.size();i++){
					
				 if (list.get(i).getId().equals(id)) {
					 comVO =comSvc.getOneComById(id);
					 
					
					 String to = id;
					 String subject = "忘記密碼";
						
				     String messageText = "你好!請點選網址會發送一組新密碼給您!"+req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getRequestURI()+"?action=change&&id="+id;
				      
				    
				       
				      MailService mailService = new MailService();
				      mailService.sendMail(to, subject, messageText);
				      res.sendRedirect(req.getContextPath()+"/Front_end/login/forgetPwdOk.jsp");
					    return;
				 }
			 
			 
			 }
			 		
			 			errorMsgs.put("forgetPwdCom","請輸入正確帳號");
			 		
					
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
						ComVO comVO = new ComVO();
						String com_no = req.getParameter("com_no").trim();
						String oldpwd = req.getParameter("oldpwd").trim();
						String pwd = req.getParameter("pwd").trim();
						
						 ComService comSvc = new ComService();
						 ComVO a=comSvc.oldPwd(com_no);	
						 
						 if (oldpwd == null || (oldpwd.trim()).length() == 0 ||pwd == null || (pwd.trim()).length() == 0 ) {
								errorMsgs.add("新舊密碼請勿空白");
							}
						 if (!errorMsgs.isEmpty()) {
								RequestDispatcher failureView = req
										.getRequestDispatcher("/Front_end/com/updatePwd.jsp");
								failureView.forward(req, res);
								return;//程式中斷
							}
						 
						 if(!oldpwd.equals(a.getPwd())){
							 errorMsgs.add("舊密碼錯誤更改失敗");
							 String url = "/Front_end/mem/updatePwd.jsp";
								RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listAllEmp.jsp
								successView.forward(req, res);	
						}else{
							comVO =comSvc.updatePwd(com_no, pwd);
						}
						
						
			
	
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
						String url = "/Front_end/com/listOneCom.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listAllEmp.jsp
						successView.forward(req, res);	
						/***************************其他可能的錯誤處理**********************************/
	
				}
		
		
		
		
		if ("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.invalidate();
//			out.println("<HTML><HEAD><TITLE>登出</TITLE></HEAD>");
//		      out.println("<BODY><h1>你的帳號已登出!<BR>");
//		      out.println(" <A HREF="+req.getContextPath()+"/Front_end/login/login.jsp>返回</h1></A>");
//		      out.println("</BODY></HTML>");
			res.sendRedirect(req.getContextPath()+"/Front_end/login/homepage.jsp");
		    return;
		}
	
		if ("login".equals(action)) {
		    // 【取得使用者 帳號(account) 密碼(password)】
			 String id = req.getParameter("id");//使用者輸入
			 String pwd = req.getParameter("pwd");
			 String comslocation = req.getParameter("comslocation");
			  // 【檢查該帳號 , 密碼是否有效】
			 HttpSession session = req.getSession();

			 ComService comSvc = new ComService();
			 List<ComVO> list = comSvc.loginid();
			 List<ComVO> list1 = comSvc.loginpwd();
			
			 for(int i=0;i<list.size();i++){
				 if (list.get(i).getId().equals(id)) {
					
					
					 for(int j=0;j<list1.size();j++){
						
						 if (list1.get(j).getPwd().equals(pwd)) {
							
							 session.removeAttribute("id");
							 session.removeAttribute("comVO");
							 session.removeAttribute("memVO");
						      ComVO comVO = comSvc.getOneComById(id);
						      String status=comVO.getStatus();
						 
						     String ststus=  comVO.getStatus();
						     System.out.println(ststus);
						      if("待驗證".equals(ststus)){
									 
						    	  res.sendRedirect(req.getContextPath()+"/Front_end/com/notConfirmCom.jsp");
							      return;
								 }else if(status.equals("停權")){
									 res.sendRedirect(req.getContextPath()+"/Front_end/login/statusNotGood.jsp");
										return;
								 }
						      		
									 
									 session.setAttribute("id", id);
								     session.setAttribute("comVO", comVO);
								     session.setAttribute("role","com");
								      try {
								    	  String comlocation = (String) session.getAttribute("comlocation");
								    	 	      
								    	  System.out.println("我是過過濾器的~~~~~"+comlocation);
								          if (comlocation!=null) {
								        	
								            session.removeAttribute("comlocation");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
								            res.sendRedirect(comlocation);            
								            return;
								          }else if(comslocation!=null){
								        	  System.out.println("我是沒經過過濾器的"+comslocation);
								        	 res.sendRedirect(comslocation);            
									         return;
								          }
								      }catch(Exception ignored){}
								      
								      res.sendRedirect(req.getContextPath()+"/Front_end/com/listOneCom.jsp");
								      return;
								 
						 }

					 }
				      
				 }

			 }
			  session.setAttribute("login","com");
		 res.sendRedirect(req.getContextPath()+"/Front_end/login/errorLogin.jsp");
		      return;
	 
		}
	
        if ("insert".equals(action)) {   
			
			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String id = req.getParameter("id").trim();
				String pwd = req.getParameter("pwd").trim();
				String name = req.getParameter("name").trim();
				if (name == null || (name.trim()).length() == 0) {
					errorMsgs.put("name","名稱請勿空白");
				}
				String loc = req.getParameter("loc").trim();
				if (loc == null || (loc.trim()).length() == 0) {
					errorMsgs.put("loc","地址請勿空白");
				}
				ComServlet comServlet = new ComServlet();
				 String[] locs=comServlet.latlng(loc);
				 String lat= locs[0].toString();
				 String lon= locs[1].toString();
				 System.out.println(lon+"----"+lat);
				 
				String com_desc = req.getParameter("com_desc").trim();
				if (com_desc == null || (com_desc.trim()).length() == 0) {
					errorMsgs.put("com_desc","介紹請勿空白");
				}
				String phone = req.getParameter("phone").trim();
				
				if((phone.trim()).length()<6||(phone.trim()).length()>15){
					errorMsgs.put("phone","請輸入正確電話號碼");
					
				}
				
				String account = req.getParameter("account").trim();
				if (account == null || (account.trim()).length() == 0) {
					errorMsgs.put("account","銀行帳戶請勿空白");
				}
				
				
				
				Part part = req.getPart("logo");
				InputStream in = part.getInputStream();
				byte[] logo = new byte[in.available()];
				in.read(logo);
				
			

				ComVO comVO = new ComVO();
				if(part.getSize()==0){
					errorMsgs.put("logo","請選擇圖片");
					}
				comVO.setId(id);
				comVO.setPwd(pwd);
				comVO.setName(name);
				comVO.setLoc(loc);
				comVO.setLogo(logo);
				comVO.setAccount(account);
				comVO.setCom_desc(com_desc);
				comVO.setPhone(phone);
				comVO.setLon(lon);
				comVO.setLat(lat);
			
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("comVO", comVO);  
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Front_end/com/register.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				ComService comSvc = new ComService();
				comVO = comSvc.addCom(id, pwd, name, loc,com_desc,phone,account,logo,lon,lat);
				HttpSession session = req.getSession();
				comVO = comSvc.getOneComById(id);
				
//			      session.setAttribute("id", id);
//			      session.setAttribute("comVO", comVO);
			      
			     
			     //管理員可用 int passRandom = (int)(Math.random()*999+1);  
			      String to = id;
			      
			      String subject = "廠商帳號驗證";
			      
			      String ch_name = name;
			      String messageText = "你好!" + ch_name +"歡迎加入she said yes! \n"+
			    		  
			     "請點選網址完成驗證"+req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/Confirm?action=conFirmCom&&com_no="+comVO.getCom_no() + " \n" ; 
			       
			      MailService mailService = new MailService();
			      mailService.sendMail(to, subject, messageText);
			      
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/Front_end/com/notConfirmCom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
				
//			} catch (Exception e) {
//				errorMsgs.put(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/Front_end/com/register.jsp");
//				failureView.forward(req, res);
//			}
		}
		
        
        if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

        	Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
		
				/***************************1.接收請求參數****************************************/
				String com_no =req.getParameter("com_no");
				
				/***************************2.開始查詢資料****************************************/
				ComService comSvc = new ComService();
				ComVO comVO = comSvc.getOneCom(com_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("comVO", comVO);         // 資料庫取出的empVO物件,存入req
				String url = "/Front_end/com/updatecompany.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			
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
			
        	Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String com_no = req.getParameter("com_no").trim();
				String id = req.getParameter("id").trim();
							
				String name = req.getParameter("name").trim();	
				String loc = req.getParameter("loc").trim();
				if (loc == null || (loc.trim()).length() == 0) {
					errorMsgs.put("loc","請輸入正確地址");
				}
				
				String lon = req.getParameter("lon").trim();
				String lat = req.getParameter("lat").trim();
				String com_desc = req.getParameter("com_desc").trim();
				if (com_desc == null || (com_desc.trim()).length() == 0) {
					errorMsgs.put("com_desc","請輸入廠商介紹");
				}
				
				String phone = req.getParameter("phone").trim();
				if((phone.trim()).length()<6||(phone.trim()).length()>15){
					errorMsgs.put("phone","請輸入正確電話號碼");
					
				}
				String account = req.getParameter("account").trim();
				if (account == null || (account.trim()).length() == 0) {
					errorMsgs.put("account","請輸入正確銀行帳戶");
				}
			
		
				Part part = req.getPart("logo");

				InputStream in = part.getInputStream();
				byte[] logo = new byte[in.available()];
				in.read(logo); 

				
		
				String status =req.getParameter("status").trim();
				ComVO comVO = new ComVO();
				
				
			if(part.getSize()==0){
				errorMsgs.put("logo","請選擇大頭貼");
				}
				


			comVO.setCom_no(com_no);
			comVO.setId(id);
			comVO.setName(name);
			comVO.setLoc(loc);
			comVO.setLon(lon);
			comVO.setLat(lat);
			comVO.setPhone(phone);
			comVO.setCom_desc(com_desc);
			comVO.setAccount(account);
			comVO.setLogo(logo);
			comVO.setStatus(status);
					
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("comVO",comVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Front_end/com/updatecompany.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ComService comSvc = new ComService();
				comVO = comSvc.updateCom(com_no,id,name,loc,lon,lat,com_desc,phone,account,logo,status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				HttpSession session = req.getSession();
				session.setAttribute("comVO",comVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/Front_end/com/listOneCom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			
				
		}
        
	}
}
