package com.advertising.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.advertising.model.*;

public class AdvertisingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action=" + action);
		if ("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String adv_no = new String(req.getParameter("adv_no"));
				System.out.println("ADV_NO:" + adv_no);
				/*************************** 2.開始查詢資料 ****************************************/
				AdvertisingService advertisingSvc = new AdvertisingService();
				AdvertisingVO advertisingVO = advertisingSvc.getOneAdvertising(adv_no);
				System.out.print("CONTROLLER getOne_For_Update:advertisingVO is null?");
				System.out.println(advertisingVO == null);
				/****************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("advertisingVO", advertisingVO); // 資料庫取出的物件,存入req
				String url = "/Back_end/advertising/updateAdvertising.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/advertising/listAllAdvertising.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("333333333333333333333333333333333333");
			try {
				/******************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/

				String adv_no = new String(req.getParameter("adv_no").trim());

				java.sql.Timestamp startDay = null;
				try {
					startDay = java.sql.Timestamp.valueOf(req.getParameter("startday").trim());
				} catch (IllegalArgumentException e) {
					startDay = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入廣告開始日期!");
				}
				java.sql.Timestamp endDay = null;
				try {
					endDay = java.sql.Timestamp.valueOf(req.getParameter("endday").trim());
				} catch (IllegalArgumentException e) {
					endDay = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入廣告結束日期!");
				}
				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("價錢請填數字.");
				}
				String text = new String(req.getParameter("text"));

				String status = new String(req.getParameter("status"));

				AdvertisingVO advertisingVO = new AdvertisingVO();
				advertisingVO.setStartDay(startDay);
				advertisingVO.setEndDay(endDay);
				advertisingVO.setPrice(price);
				advertisingVO.setText(text);
				advertisingVO.setStatus(status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("advertisingVO", advertisingVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/advertising/updateAdvertising.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				AdvertisingService advertisingSvc = new AdvertisingService();
				AdvertisingVO oldAdvertisingVO = advertisingSvc.getOneAdvertising(adv_no);
				advertisingVO = advertisingSvc.updateAdvertising(adv_no, oldAdvertisingVO.getCom_no(), startDay, endDay,
						price, text, oldAdvertisingVO.getImg(), oldAdvertisingVO.getVdo(), status);
				System.out.print("CONTROLLER update:");
				System.out.println(advertisingVO == null);
				/*****************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("advertisingVO", advertisingVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/Back_end/advertising/listOneAdvertising.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/advertising/updateAdvertising.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String adv_no = new String(req.getParameter("adv_no"));
				System.out.println("ADV_NO=" + adv_no);
				/*************************** 2.開始刪除資料 ***************************************/
				AdvertisingService advertisingSvc = new AdvertisingService();
				advertisingSvc.deleteAdvertising(adv_no);
				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/Back_end/advertising/listAllAdvertising.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/advertising/listAllAdvertising.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
