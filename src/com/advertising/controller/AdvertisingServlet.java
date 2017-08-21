package com.advertising.controller;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.advertising.model.*;

@MultipartConfig(fileSizeThreshold = 10 * 1024 * 1024, maxFileSize = 5 * 10 * 1021 * 1024, maxRequestSize = 5 * 5 * 10
		* 1024 * 1024)
public class AdvertisingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("ACTION=" + action);
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
				String url = "/Back_end/advertising/updateAdvertisingTest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/advertising/listAllAdvertisingTest.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/******************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String adv_no = new String(req.getParameter("adv_no"));
				
				String title = new String(req.getParameter("title"));

				Timestamp startday = null;
				try {
					startday = Timestamp.valueOf(req.getParameter("startday") + " 00:00:00");
				} catch (IllegalArgumentException e) {
					startday = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入廣告開始日期!");
				}
				Timestamp endday = null;
				try {
					endday = Timestamp.valueOf(req.getParameter("endday") + " 00:00:00");
				} catch (IllegalArgumentException e) {
					endday = new Timestamp(System.currentTimeMillis());
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
				AdvertisingService advertisingSvc = new AdvertisingService();
				AdvertisingVO oldAdvertisingVO = advertisingSvc.getOneAdvertising(adv_no);
				advertisingVO.setAdv_no(oldAdvertisingVO.getAdv_no());
				advertisingVO.setCom_no(oldAdvertisingVO.getCom_no());
				advertisingVO.setTitle(oldAdvertisingVO.getTitle());
				advertisingVO.setStartDay(startday);
				advertisingVO.setEndDay(endday);
				advertisingVO.setPrice(price);
				advertisingVO.setText(text);
				advertisingVO.setStatus(status);
				advertisingVO.setImg(oldAdvertisingVO.getImg());
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("advertisingVO", advertisingVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/advertising/updateAdvertisingTest.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				advertisingVO = advertisingSvc.updateAdvertising(adv_no, oldAdvertisingVO.getCom_no(),oldAdvertisingVO.getTitle(), startday, endday,
						price, text, oldAdvertisingVO.getImg(), oldAdvertisingVO.getVdo(), status);
				System.out.print("CONTROLLER update:");
				System.out.println(advertisingVO == null);
				/*****************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("advertisingVO", advertisingVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/Back_end/advertising/listOneAdvertisingTest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/advertising/updateAdvertisingTest.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String com_no = req.getParameter("com_no").trim();
				
				String title = null;
				try {
					title = new String(req.getParameter("title").trim());
				} catch (NumberFormatException e) {
					title = "";
					errorMsgs.add("請輸入內容");
				}

				String text = null;
				try {
					text = new String(req.getParameter("text").trim());
				} catch (NumberFormatException e) {
					text = "";
					errorMsgs.add("請輸入內容");
				}

				Timestamp startday = null;
				try {
					startday = Timestamp.valueOf(req.getParameter("startday") + " 00:00:00");
				} catch (IllegalArgumentException e) {
					startday = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Timestamp endday = null;
				try {
					endday = Timestamp.valueOf(req.getParameter("endday") + " 00:00:00");
				} catch (IllegalArgumentException e) {
					endday = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer price = null;
				try {
					price = new Integer((int) (endday.getTime() - startday.getTime()) / (100 * 60 * 60 * 24) * 1000);
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("價格請填數字.");
				}

				String status = new String(req.getParameter("status").trim());

				AdvertisingVO advertisingVO = null;
				advertisingVO = new AdvertisingVO();
				advertisingVO.setCom_no(com_no);
				advertisingVO.setText(text);
				advertisingVO.setStartDay(startday);
				advertisingVO.setEndDay(endday);
				advertisingVO.setPrice(price);
				advertisingVO.setStatus(status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("advertisingVO", advertisingVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/advertising/addAdvertisingTest.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				AdvertisingService advertisingSvc = new AdvertisingService();
				ServletContext context = getServletContext();
				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					String filename = getFileNameFromPart(part);
					System.out.println("ContentType=" + part.getContentType());
					byte[] file = null;
					InputStream in = part.getInputStream();
					file = new byte[in.available()];
					in.read(file);
					in.close();
					if (getFileNameFromPart(part) != null && part.getContentType() != null) {
						if (isImgFile(context.getMimeType(filename))) {
							advertisingVO = advertisingSvc.addAdvertising(com_no,title, startday, endday, price, text, file,
									null, status);
						} else {
							advertisingVO = advertisingSvc.addAdvertising(com_no, title , startday, endday, price, text, null,
									file, status);
						}
					}
				}

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/Back_end/advertising/listAllAdvertisingTest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/advertising/addAdvertisingTest.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			String active = req.getParameter("active");
			req.setAttribute("active", active);
			try {
				String adv_no = new String(req.getParameter("adv_no"));
				System.out.println("ADV_NO:" + adv_no);
				
				String whichPage = req.getParameter("whichPage");
				req.setAttribute("whichPage", whichPage);

				AdvertisingService advertisingSvc = new AdvertisingService();
				AdvertisingVO advertisingVO = advertisingSvc.getOneAdvertising(adv_no);

				req.setAttribute("listOneAdContent", advertisingVO);
				String url = requestURL;
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要展示的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			try {
				
				String adv_no = new String(req.getParameter("adv_no"));
				System.out.println("ADV_NO=" + adv_no);

				AdvertisingService advertisingSvc = new AdvertisingService();
				advertisingSvc.deleteAdvertising(adv_no);

				String url = requestURL+"?whichPage="+whichPage;
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("approved".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			System.out.println("requestURL:" + requestURL);
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			try {
				
				String adv_no = new String(req.getParameter("adv_no"));
				System.out.println("ADV_NO:" + adv_no);
				
				AdvertisingVO advertisingVO = new AdvertisingVO();
				AdvertisingService advertisingSvc = new AdvertisingService();
				AdvertisingVO oldAdvertisingVO = advertisingSvc.getOneAdvertising(adv_no);

				advertisingVO = advertisingSvc.updateAdvertising(oldAdvertisingVO.getAdv_no(),
						oldAdvertisingVO.getCom_no(),oldAdvertisingVO.getTitle() , oldAdvertisingVO.getStartDay(), oldAdvertisingVO.getEndDay(),
						oldAdvertisingVO.getPrice(), oldAdvertisingVO.getText(), oldAdvertisingVO.getImg(),
						oldAdvertisingVO.getVdo(), "1");

				req.setAttribute("advertisingVO", advertisingVO);
				
				String url = requestURL+"?whichPage="+whichPage;
				
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if("disapproved".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			System.out.println("requestURL:" + requestURL);
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			try {
				
				String adv_no = new String(req.getParameter("adv_no"));
				System.out.println("ADV_NO:" + adv_no);
				
				AdvertisingVO advertisingVO = new AdvertisingVO();
				AdvertisingService advertisingSvc = new AdvertisingService();
				AdvertisingVO oldAdvertisingVO = advertisingSvc.getOneAdvertising(adv_no);

				advertisingVO = advertisingSvc.updateAdvertising(oldAdvertisingVO.getAdv_no(),
						oldAdvertisingVO.getCom_no(), oldAdvertisingVO.getTitle(), oldAdvertisingVO.getStartDay(), oldAdvertisingVO.getEndDay(),
						oldAdvertisingVO.getPrice(), oldAdvertisingVO.getText(), oldAdvertisingVO.getImg(),
						oldAdvertisingVO.getVdo(), "2");

				req.setAttribute("advertisingVO", advertisingVO);
				
				String url = null;
				url = requestURL+"?whichPage="+whichPage;

				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

	private boolean isImgFile(String mimetype) {
		return mimetype != null && mimetype.startsWith("image");
	}
}

