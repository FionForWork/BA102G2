package com.reservation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

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
		
		// 報價預約
		if(action.equals("reservationFromQuote")){
			String quo_no = req.getParameter("quo_no");
			QuoteService quoteService = new QuoteService();
			QuoteVO quoteVO = quoteService.getOneQuote(quo_no);
			
			int price = Integer.valueOf(req.getParameter("price"));
			Timestamp serv_date = Timestamp.valueOf(req.getParameter("serv_date"));
			String com_no = req.getParameter("com_no");
			String stype_no = req.getParameter("stype_no");
			String requestURL = req.getParameter("requestURL");
			
			RFQ_DetailService rfq_DetailService = new RFQ_DetailService();
			MemService memService = new MemService();
			Service_TypeService stypeService = new Service_TypeService();
			// 會員名稱寫死
			String content = memService.getOneMem("1001").getName() + "-" 
					+ stypeService.getOne(stype_no).getName()+"服務";
			
			CalendarVO calendarVO = new CalendarVO();
			calendarVO.setCom_no(com_no);
			calendarVO.setContent(content);
			calendarVO.setCal_date(serv_date);
			
			RFQ_DetailVO rfq_detailVO = new RFQ_DetailVO();
			rfq_detailVO.setStatus("0");
			rfq_detailVO.setRfqdetail_no(rfq_DetailService.getOneFromQuote(quo_no).getRfqdetail_no());
			
			ReservationService reservationService = new ReservationService();
			reservationService.addReservation(memVO.getMem_no(), com_no, new Timestamp(System.currentTimeMillis()), serv_date, quo_no, stype_no, price, "1", calendarVO, rfq_detailVO);
		
			RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
			successView.forward(req, res);
			
		}
		
		// 服務預約
		if(action.equals("resFromCalendar")){
			String serv_no = req.getParameter("serv_no");
			String serv_date = req.getParameter("serv_date");
			String requestURI = req.getParameter("requestURI");
			
			// Calendar必要資訊
			ServService servService = new ServService();
			ServVO servVO =  servService.getOneServ(serv_no);
			
			MemService memService = new MemService();
			Service_TypeService stypeService = new Service_TypeService();
			// 會員名稱寫死
			String content = memService.getOneMem("1001").getName() + "-" 
					+ stypeService.getOne(servVO.getStype_no()).getName()+"服務";
			
			
			CalendarVO calendarVO = new CalendarVO();
			calendarVO.setCom_no(servVO.getCom_no());
			calendarVO.setContent(content);
			calendarVO.setCal_date(Timestamp.valueOf(serv_date+" 00:00:00"));
			
			// 將訂單和行事曆一起送進Service
			ReservationService reservationService = new ReservationService();
			reservationService.addReservation("1001", servVO.getCom_no(), new Timestamp(System.currentTimeMillis())
					, Timestamp.valueOf(serv_date+" 00:00:00"), serv_no, servVO.getStype_no(), servVO.getPrice(), "0",calendarVO);
			
			
//			forward會再預約一次
//			RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
//			successView.forward(req, res);
			
			res.sendRedirect(requestURI);
		}
		
		// 查詢預約
		if(action.equals("resFromSearchService")){
			String serv_no = req.getParameter("serv_no");
			String serv_date = req.getParameter("serv_date");
//			String requestURI = req.getParameter("requestURI");
			
			ServService servService = new ServService();
			ServVO servVO = servService.getOneServ(serv_no);
			
			MemService memService = new MemService();
			Service_TypeService stypeService = new Service_TypeService();
			
			String content = memService.getOneMem("1001").getName() + "-" 
					+ stypeService.getOne(servVO.getStype_no()).getName()+"服務";
			
			CalendarVO calendarVO = new CalendarVO();
			calendarVO.setCom_no(servVO.getCom_no());
			calendarVO.setContent(content);
			calendarVO.setCal_date(Timestamp.valueOf(serv_date+" 00:00:00"));
			
			ReservationService reservationService = new ReservationService();
			reservationService.addReservation("1001", servVO.getCom_no(), new Timestamp(System.currentTimeMillis())
					, Timestamp.valueOf(serv_date+" 00:00:00"), serv_no, servVO.getStype_no(), servVO.getPrice(), "0", calendarVO);
			
			String requestURI = req.getParameter("requestURI");
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
			
			// 判斷是一般服務或是詢價服務
			ReservationService resService = new ReservationService();
			ReservationVO resVO =  resService.getOneReservation(res_no);
			
			if(resVO.getServ_no().startsWith("7")){
				resService.updateStatus("4", res_no);
			}else{
				resService.updateStatus("2", res_no);
			}
			
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
			
			String requestURL = req.getParameter("requestURL");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String cal_date = req.getParameter("cal_date");
			
			Date date = null;
			Integer bottomPrice = null;
			Integer topPrice = null;
			try{
				date = Date.valueOf(cal_date);

			}catch(IllegalArgumentException e){
				errorMsgs.add("請輸入正確的日期格式!");
			}
			
			if(!req.getParameter("bottomPrice").trim().equals("")){
				try{
					bottomPrice = new Integer(req.getParameter("bottomPrice"));
				}catch (NumberFormatException e) {
					errorMsgs.add("請輸入正確的金額格式!");
				}
			}
			if(!req.getParameter("topPrice").trim().equals("")){
				try{
					topPrice = new Integer(req.getParameter("topPrice"));
				}catch (NumberFormatException e) {
					errorMsgs.add("請輸入正確的金額格式!");
				}
			}
			
			if (!errorMsgs.isEmpty()) {
				List<ServVO> list = new ArrayList<ServVO>();
				req.setAttribute("list", list);
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
				return;
			}
			
			Map<String, String[]> map = req.getParameterMap();

			ServService servService = new ServService();
			List<ServVO> list = servService.getAll(map);
			req.setAttribute("list", list);
			req.setAttribute("map", map);
			
			
			RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
			successView.forward(req, res);
		}
		
		if(action.equals("checkNum")){
			PrintWriter out = res.getWriter();
			String cardNum1 = req.getParameter("cardNum1");
			
			JSONObject result = new JSONObject();
			Integer num = null;
			try{
				num = new Integer(cardNum1);
			}catch(NumberFormatException e){
				try {
					result.put("r", "請輸入正確卡號");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				out.print(result);
				return;
			}
			try {
				result.put("r", "卡號正確");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print(result);
		}
		
	}

}
