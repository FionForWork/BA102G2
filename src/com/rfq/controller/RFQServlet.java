package com.rfq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemVO;
import com.quote.model.*;
import com.rfq.model.RFQService;
import com.rfq.model.RFQVO;
import com.rfq_detail.model.RFQ_DetailService;
import com.rfq_detail.model.RFQ_DetailVO;

public class RFQServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		
		HttpSession session = req.getSession();
		
//	新人詢價
		if(action.equals("add")){
			
			MemVO memVO = (MemVO)session.getAttribute("memVO");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String[] type = req.getParameterValues("type");
			String[] stype_no = req.getParameterValues("stype_no");
			String[] ser_date = req.getParameterValues("ser_date");
			String[] location = req.getParameterValues("location");
			String[] content = req.getParameterValues("content");
			
			List<RFQ_DetailVO> list = new ArrayList<RFQ_DetailVO>();
			
			for(int i = 0; i < type.length ; i++){
				int formNum = Integer.valueOf(type[i]);
				Timestamp t = Timestamp.valueOf(ser_date[formNum]+" 00:00:00");
				RFQ_DetailVO rfq_DetailVO = new RFQ_DetailVO();
				rfq_DetailVO.setStype_no(stype_no[formNum]);
				rfq_DetailVO.setLocation(location[formNum]);
				rfq_DetailVO.setSer_date(t);
				rfq_DetailVO.setContent(content[formNum].trim().replace("\n", "<br>"));
				rfq_DetailVO.setStatus("1");
				list.add(rfq_DetailVO);
			}
			
			RFQService rfqService = new RFQService();
			rfqService.addRFQ(memVO.getMem_no(), new Timestamp(System.currentTimeMillis()), list);
			
//			String url = "/Front_end/RFQ/listAllRFQ.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
			res.sendRedirect(req.getContextPath()+"/Front_end/RFQ/listAllRFQ.jsp");
		}
		
		if(action.equals("updateRFQStatus")){
			String status = req.getParameter("status");
			String rfqdetail_no = req.getParameter("rfqdetail_no");
				
			RFQ_DetailService rfq_DetailService = new RFQ_DetailService();
			rfq_DetailService.updateRFQDetailStatus(status, rfqdetail_no);
			
			res.sendRedirect(req.getContextPath()+"/Front_end/RFQ/listMyRFQ.jsp");
		
		}
		
	}
		
}
