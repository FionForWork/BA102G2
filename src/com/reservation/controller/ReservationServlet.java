package com.reservation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.calendar.model.*;
import com.mem.model.*;
import com.mem.model.MemVO;
import com.quote.model.*;
import com.reservation.model.*;
import com.rfq_detail.model.*;
import com.serv.model.*;
import com.service_type.model.*;

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
		HttpSession session = req.getSession();
		MemVO memVO = (MemVO)session.getAttribute("memVO");
		String action = req.getParameter("action");
		
		if(action.equals("reservationFromQuote")){
			String quo_no = req.getParameter("quo_no");
			QuoteService quoteService = new QuoteService();
			QuoteVO quoteVO = quoteService.getOneQuote(quo_no);
			
			int price = Integer.valueOf(req.getParameter("price"));
			Timestamp serv_date = Timestamp.valueOf(req.getParameter("serv_date"));
			String com_no = req.getParameter("com_no");
			String stype_no = req.getParameter("stype_no");
			String requestURL = req.getParameter("requestURL");
			
			ReservationService reservationService = new ReservationService();
			reservationService.addReservation(memVO.getMem_no(), com_no, new Timestamp(System.currentTimeMillis()), serv_date, quo_no, stype_no, price);
		
			RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
			successView.forward(req, res);
			
		}
		
		if(action.equals("resFromCalendar")){
			String serv_no = req.getParameter("serv_no");
			String serv_date = req.getParameter("serv_date");
			String requestURI = req.getParameter("requestURI");
			
			ServService servService = new ServService();
			ServVO servVO =  servService.getOneServ(serv_no);
			
			ReservationService reservationService = new ReservationService();
			reservationService.addReservation("1001", servVO.getCom_no(), new Timestamp(System.currentTimeMillis())
					, Timestamp.valueOf(serv_date+" 00:00:00"), serv_no, servVO.getStype_no(), servVO.getPrice());
			
			MemService memService = new MemService();
			Service_TypeService stypeService = new Service_TypeService();
			String content = memService.getOneMem("1001").getName() + "-" 
							+ stypeService.getOne(servVO.getStype_no()).getName()+"服務";
			
			CalendarService calendarService = new CalendarService();
			calendarService.addCalendar(servVO.getCom_no(), content, Timestamp.valueOf(serv_date+" 00:00:00"),serv_no);
			
//			forward會再預約一次
//			RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
//			successView.forward(req, res);
			
			res.sendRedirect(requestURI);
		}
		
		if(action.equals("changeCalendar")){
			int month = Integer.valueOf(req.getParameter("month"));
			int year = Integer.valueOf(req.getParameter("year"));
			LocalDate localDate = LocalDate.of(year, month, 1);
			req.setAttribute("localDate", localDate);
			String url = req.getParameter("requestURL");
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if(action.equals("pay")){
			String RedirectURL = req.getParameter("RedirectURL");
			String res_no = req.getParameter("res_no");
			ReservationService resService = new ReservationService();
			resService.updateStatus("1", res_no);
			
			res.sendRedirect(RedirectURL);
		}
		
		if(action.equals("resCompleted")){
			String RedirectURL = req.getParameter("RedirectURL");
			String res_no = req.getParameter("res_no");
			ReservationService resService = new ReservationService();
			resService.updateStatus("2", res_no);
			
			res.sendRedirect(RedirectURL);
		}
		
		if(action.equals("rating")){
			String RedirectURL = req.getParameter("RedirectURL");
			String res_no = req.getParameter("res_no");
			Integer score = new Integer(req.getParameter("score"));
			
			ReservationService resService = new ReservationService();
			resService.updateScore("3", score, res_no);
			res.sendRedirect(RedirectURL);
		}
		
		if(action.equals("searchServiceByCompositeQuery")){
			Map<String, String[]> map = req.getParameterMap();
		}
		
	}

}
