package com.forum_comment.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.ArticleVO;
import com.article.model.Article_Service;
import com.forum_comment.model.Forum_CommentVO;
import com.forum_comment.model.Forum_Comment_Service;
import com.problem.model.ProblemVO;
import com.problem.model.Problem_Service;



public class Forum_commentServlet extends HttpServlet{

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
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				Integer art_no = new Integer(req.getParameter("art_no").trim());
				Integer speaker_no = new Integer(req.getParameter("speaker_no").trim());
				
				String cont = req.getParameter("cont").trim();	

				Forum_CommentVO forum_commentVO = new Forum_CommentVO();
				Timestamp t = new Timestamp(System.currentTimeMillis()); 
				
				forum_commentVO.setArt_no(art_no);
				forum_commentVO.setSpeaker_no(speaker_no);
				forum_commentVO.setCont(cont);
				forum_commentVO.setFmc_date(t);
								
				
				
				/***************************2.開始新增資料***************************************/
				Forum_Comment_Service forum_Comment_Svc = new Forum_Comment_Service();
				forum_commentVO = forum_Comment_Svc.addFC(art_no, speaker_no, cont, t);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/Front_end/Article/Discuss.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/Article/Reply.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer art_no = new Integer(req.getParameter("art_no"));
				
				/***************************2.開始查詢資料****************************************/
				Article_Service articleSvc = new Article_Service();
				ArticleVO articleVO = articleSvc.getOneArt(art_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("articleVO", articleVO);         
				String url = "/Front_end/Article/Reply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/XXXArticle/Article.jsp");
				failureView.forward(req, res);
			}
		}
		
	}	
}
