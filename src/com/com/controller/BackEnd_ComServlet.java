package com.com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.com.model.*;

@WebServlet("/BackEnd_ComServlet")
public class BackEnd_ComServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		
//		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		res.setCharacterEncoding("UTF-8"); 

		PrintWriter out = res.getWriter();
		
		String action = req.getParameter("action");
		
		if(action.equals("getOne_For_Display")){
			
			String com_no = req.getParameter("com_no");
			ComService comService = new ComService();
			ComVO comVO = comService.getOneCom(com_no);
			
			req.setAttribute("listComDeatil", comVO);
			
			String requestURL = req.getParameter("requestURL");
			RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
			successView.forward(req, res);
		}
		
		if(action.equals("ajax")){
			
			String com_no = req.getParameter("com_no");
			ComService comService = new ComService();
			ComVO comVO = comService.getOneCom(com_no);
			List<ComVO> comList = comService.getAll();
			
			JSONObject com = new JSONObject();
			try {
				com.put("comList", comList);
			}catch (JSONException e) {
				e.printStackTrace();
			}
			out.print(com);
		}
		if(action.equals("update")){
			String com_no = req.getParameter("com_no");
			ComService comService = new ComService();
			comService.updatePwd(com_no, "123456");
			JSONObject com = new JSONObject();
			try {
				com.put("1", "123");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print(com);
		}
	}


}
