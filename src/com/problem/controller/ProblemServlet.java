package com.problem.controller;

import java.io.*;
import java.util.*;

import javax.mail.Session;
import javax.servlet.*;
import javax.servlet.http.*;

import com.problem.model.ProblemVO;
import com.problem.model.Problem_Service;
import com.problem_type.model.Problem_TypeVO;
import com.tradition.model.TraditionVO;

public class ProblemServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		HttpSession session=req.getSession();
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer prob_no = new Integer(req.getParameter("prob_no"));
				
				/***************************2.開始查詢資料****************************************/
				Problem_Service problemSvc = new Problem_Service();
				ProblemVO problemVO = problemSvc.getOneProblem(prob_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("problemVO", problemVO);         
				String url = "/Back_end/problem/update_problem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/problem/Problemall.jsp");
				failureView.forward(req, res);
			}
		}
//		*****************************************************************************************************************
		
		if ("update".equals(action)) { 
					
					List<String> errorMsgs = new LinkedList<String>();
					
					req.setAttribute("errorMsgs", errorMsgs);
				
					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						Integer prob_no = new Integer(req.getParameter("prob_no").trim());
						Integer problem_type_no = new Integer(req.getParameter("problem_type_no").trim());
						
						String content = req.getParameter("content").trim();
						String reply = req.getParameter("reply").trim();	
						
		
						ProblemVO problemVO = new ProblemVO();
						problemVO.setProb_no(prob_no);
						problemVO.setProblem_type_no(problem_type_no);
						problemVO.setContent(content);
						problemVO.setReply(reply);
						
		
						
						
						/***************************2.開始修改資料*****************************************/
						Problem_Service problemSvc = new Problem_Service();
						problemVO = problemSvc.updataProblem(prob_no, problem_type_no, content, reply);
						
						/***************************3.修改完成,準備轉交(Send the Success view)*************/
						req.setAttribute("problemVO", problemVO); // 資料庫update成功後,正確的的empVO物件,存入req
						String url = "/Back_end/problem/Problemall.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
						successView.forward(req, res);
		
						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("修改資料失敗:"+e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/Back_end/problem/update_problem_input.jsp");
						failureView.forward(req, res);
					}
				}
//		**************************************************************************************************************
				if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
							
							List<String> errorMsgs = new LinkedList<String>();
							// Store this set in the request scope, in case we need to
							// send the ErrorPage view.
							req.setAttribute("errorMsgs", errorMsgs);
				
							try {
								/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
								
								Integer problem_type_no = new Integer(req.getParameter("problem_type_no").trim());
								
								String content = req.getParameter("content").trim();
								String reply = req.getParameter("reply").trim();	
				
								ProblemVO problemVO = new ProblemVO();
								
								problemVO.setProblem_type_no(problem_type_no);
								problemVO.setContent(content);
								problemVO.setReply(reply);
				
								
								
								
								/***************************2.開始新增資料***************************************/
								Problem_Service empSvc = new Problem_Service();
								problemVO = empSvc.addProblem(problem_type_no, content, reply);
								
								/***************************3.新增完成,準備轉交(Send the Success view)***********/
								String url = "/Back_end/problem/Problemall.jsp";
								RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
								successView.forward(req, res);				
								
								/***************************其他可能的錯誤處理**********************************/
							} catch (Exception e) {
								errorMsgs.add(e.getMessage());
								RequestDispatcher failureView = req
										.getRequestDispatcher("/Back_end/problem/add_problem.jsp");
								failureView.forward(req, res);
							}
						}
				if ("delete".equals(action)) { 

					List<String> errorMsgs = new LinkedList<String>();
					
					req.setAttribute("errorMsgs", errorMsgs);
			
					try {
						/***************************1.接收請求參數***************************************/
						Integer prob_no = new Integer(req.getParameter("prob_no"));
						
						/***************************2.開始刪除資料***************************************/
						Problem_Service problemSvc = new Problem_Service();
						problemSvc.deleteproblem(prob_no);
						
						/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
						String url = "/Back_end/problem/Problemall.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
						successView.forward(req, res);
						
						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add("刪除資料失敗:"+e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/Back_end/problem/Problemall.jsp");
						failureView.forward(req, res);
					}
				}
//				*********************************************************************
				if ("get_OneAll".equals(action)) { 

					List<String> errorMsgs = new LinkedList<String>();
					
					req.setAttribute("errorMsgs", errorMsgs);
					
					try {
						/***************************1.接收請求參數****************************************/
						Integer problem_type_no = new Integer(req.getParameter("problem_type_no"));
						
						/***************************2.開始查詢資料****************************************/
						Problem_Service problemSvc = new Problem_Service();
						List<ProblemVO> problemlist = (List<ProblemVO>) problemSvc.getOneAll(problem_type_no);
										
						/***************************3.查詢完成,準備轉交(Send the Success view)************/
						session.setAttribute("problemlist", problemlist);         
						String url = "/Front_end/Problem/ProblemAll.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);

						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/Front_end/Problem/xxProblem.jsp");
						failureView.forward(req, res);
					}
				}
//				*****************************************************************************
				if ("get_All".equals(action)) { 

					List<String> errorMsgs = new LinkedList<String>();
					
					req.setAttribute("errorMsgs", errorMsgs);
					
					try {
						/***************************1.接收請求參數****************************************/
						
						
						/***************************2.開始查詢資料****************************************/
						Problem_Service problemSvc = new Problem_Service();
						List<ProblemVO> problemlist = (List<ProblemVO>) problemSvc.getAll();
										
						/***************************3.查詢完成,準備轉交(Send the Success view)************/
						session.setAttribute("problemlist", problemlist);         
						String url = "/Front_end/Problem/ProblemAll.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);

						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/Front_end/Problem/xxProblem.jsp");
						failureView.forward(req, res);
					}
				}
				
	}
}
