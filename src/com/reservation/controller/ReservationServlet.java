package com.reservation.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		
		if(action.equals("reservationFromQuote")){
			String mem_no = req.getParameter("mem_no");
			String quo_no = req.getParameter("quo_no");
			PrintWriter out = res.getWriter();
			out.print("mem_no : " + mem_no);
			out.print("<br>");
			out.println("quo_no : " + quo_no);
		}
	}

}
