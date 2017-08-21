package com.reservation.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reservation.model.*;

/**
 * Servlet implementation class DeleteResServlet
 */
@WebServlet("/DeleteResServlet")
public class DeleteResServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Timer timer = new Timer();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
	public void init(){
		Calendar calendar = new GregorianCalendar(2017,7,19,0,0,0);
		Date date = calendar.getTime();
		long len = date.getTime();
		timer.schedule(t, date, 24*60*60*1000);
	}
	
	public void destory(){
		timer.cancel();
	}
	
	public TimerTask t = new TimerTask(){

		@Override
		public void run() {
			ReservationService resService = new ReservationService();
			ReservationVO resVO = null;
			List<String> list = resService.getDeleteRes();
			for(String res_no : list){
//				System.out.println(res_no);
				resService.updateStatus("5", res_no);
			}
		}
		
	};
}
