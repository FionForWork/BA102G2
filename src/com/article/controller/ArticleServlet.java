package com.article.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.ArticleVO;
import com.article.model.Article_Service;
import com.problem.model.ProblemVO;
import com.problem.model.Problem_Service;

public class ArticleServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		if ("getOnedata".equals(action)) { 
			System.out.println(action);

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer art_no = new Integer(req.getParameter("art_no"));
				System.out.println(art_no);
				/***************************2.開始查詢資料****************************************/
				Article_Service articleSvc = new Article_Service();
				ArticleVO articleVO = articleSvc.getOneArt(art_no);
					System.out.println(articleVO);			
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("articleVO", articleVO);         
				
				String url = "/Front_end/Article/Discuss.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/ArticleArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
	}
}
