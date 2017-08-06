package com.quote.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemVO;
import com.quote.model.QuoteService;
import com.quote.model.QuoteVO;
import com.rfq_detail.model.RFQ_DetailService;
import com.rfq_detail.model.RFQ_DetailVO;

public class QuoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// 會員帳號假定
		HttpSession session = req.getSession();
		MemVO memVO = (MemVO)session.getAttribute("memVO");
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		
		if(action.equals("quote")){
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer price = null;
			try{
				price =new Integer(req.getParameter("price"));
			}catch(NumberFormatException e){
				errorMsgs.add("金額請填入數字");
			}
			
			if(!errorMsgs.isEmpty()){
				String url = "/Front_end/RFQ/listAllRFQ.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			}
			
			String rfqdetail_no = req.getParameter("rfqdetail_no");
			String content = req.getParameter("content");
			content = content.replace("\n","<br>");
			
			Timestamp t = new Timestamp(System.currentTimeMillis());
			
			QuoteService quoteService = new QuoteService();
			quoteService.addQuote("2001", rfqdetail_no, price, content, t);
			
			String url = "/Front_end/RFQ/listAllRFQ.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if(action.equals("listQuote")){
			// session效率問題
			
			String sort = req.getParameter("sort");
			// 日期、金額排序
			if(sort != null){
				RFQ_DetailVO rfqDetailVO = (RFQ_DetailVO)session.getAttribute("rfqDetailVO");
				List<QuoteVO> list =  (List<QuoteVO>)session.getAttribute("list");
				
				if(sort.startsWith("date")){
					Collections.sort(list, new Comparator<QuoteVO>(){
						@Override
						public int compare(QuoteVO quoteVO1, QuoteVO quoteVO2) {
							int index = quoteVO1.getQuo_date().compareTo(quoteVO2.getQuo_date());
							return index;
						}	
					});
					if(sort.equals("dateDesc")){
						Collections.reverse(list);
					}
				}else{
					Collections.sort(list, new Comparator<QuoteVO>(){	
						@Override
						public int compare(QuoteVO quoteVO1, QuoteVO quoteVO2) {
							int index = quoteVO1.getPrice().compareTo(quoteVO2.getPrice());
							return index;
						}
					});
				
					if(sort.equals("priceDesc")){
						Collections.reverse(list);
						}
				}
				req.setAttribute("rfqDetailVO", rfqDetailVO);
				req.setAttribute("list", list);
				
				String url = "/Front_end/quote/ListQuote.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			}
			
			//第一次進來
			String rfqdetail_no = req.getParameter("rfqdetail_no");
			
			RFQ_DetailService rfqDetailService = new RFQ_DetailService();
			RFQ_DetailVO rfqDetailVO = rfqDetailService.getOneRFQDetail(rfqdetail_no);
			
			QuoteService quoteService = new QuoteService();
			List<QuoteVO> list = quoteService.getAllQuote(rfqdetail_no);
			
			req.setAttribute("rfqDetailVO", rfqDetailVO);
			req.setAttribute("list", list);
			
			// 把詢價資訊及報價List存到session中，以供後續排序
			session.setAttribute("rfqDetailVO", rfqDetailVO);
			session.setAttribute("list", list);
			
			String url = "/Front_end/quote/ListQuote.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
				
		}
		
		if(action.equals("updateQuote")){
			String quo_no = req.getParameter("quo_no");
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer price = null;
			try {
				price = new Integer(req.getParameter("price").trim());
			} catch (NumberFormatException e) {
				price = 0;
				errorMsgs.add("修改金額請填入數字.");
			}
			
			if(!errorMsgs.isEmpty()){
				RequestDispatcher failureView = req.getRequestDispatcher(req.getParameter("requestURL"));
				failureView.forward(req, res);
				return;
			}
			
			QuoteService quoteService = new QuoteService();
			quoteService.updateQuote(quo_no, price, new Timestamp(System.currentTimeMillis()) );
			
			String url = "/Front_end/quote/listMyQuote.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
	}

}