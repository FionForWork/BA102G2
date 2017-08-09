package com.comtra.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comtra.model.ComTraService;
import com.comtra.model.ComTraVO;

public class ComTraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		ComTraService comtraSvc = new ComTraService();
		
		/********* 刪除我的最愛 (廠商頁面)*********/
		if ("delete_ComTra_FromComPage".equals(action)) {
			String com_no = request.getParameter("com_no");
			String mem_no = request.getParameter("mem_no");
			ComTraVO comtra = comtraSvc.getComTraByComNoAndMemNo(com_no, mem_no);
			String comtra_no = comtra.getComtra_no();
			try {
				
				comtraSvc.deleteComTra(comtra_no);
				System.out.println("success");
				

			} catch (Exception e) {
				System.out.println("error");
			}
		}
		
		
		
		/********* 刪除我的最愛 (會員專區)*********/
		if ("delete_ComTra".equals(action)) {
			String comtra_no = request.getParameter("comtra_no");
			String nowPage = request.getParameter("nowPage");
			String requestURL = request.getParameter("requestURL");
			try {
				
				System.out.println("comtra_no   " + comtra_no);
				comtraSvc.deleteComTra(comtra_no);
				String url = requestURL+"?nowPage=" + nowPage;
				request.getRequestDispatcher(url).forward(request, response);
				System.out.println("success");
				

			} catch (Exception e) {
				System.out.println("error");
				String url = requestURL;
				request.getRequestDispatcher(url).forward(request, response);
			}
		}

		/********* 加入我的最愛 *********/
		if ("insert_ComTra".equals(action)) {
			try {
				String mem_no = request.getParameter("mem_no");
				String requestURL = request.getParameter("requestURL");
				System.out.println("requestURL====="+requestURL);
				String com_no = request.getParameter("com_no");
				System.out.println("mem_no  " + mem_no);
				System.out.println("com_no  " + com_no);
				Timestamp tracking_date = new Timestamp(System.currentTimeMillis());
				
				comtraSvc.addComTra(com_no, mem_no, tracking_date);
				
			} catch (Exception e) {
				
			}
		}
	}

}
