package com.tempcont.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.temp.model.TempService;
import com.temp.model.TempVO;
import com.tempcont.model.TempContService;
import com.tempcont.model.TempContVO;
@MultipartConfig(fileSizeThreshold = 10 * 1024 * 1024, maxFileSize = 10 * 10 * 1021 * 1024, maxRequestSize = 10 * 10 * 10
* 1024 * 1024)
public class TempContServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		TempService tempSvc = new TempService();
		TempContService tcontSvc = new TempContService();
		
		/*********   刪除成品內容       *********/
		if("delete_TempCont".equals(action)){
			String tcont_no = request.getParameter("tcont_no");
			tcontSvc.deleteTempCont(tcont_no);
			System.out.println("tcont_no====="+tcont_no);
			String temp_no = request.getParameter("temp_no");
			request.setAttribute("temp_no", temp_no);
			String url = "/Front_end/Temp/ComPage_ListAllTempConts.jsp";
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
		
		/*********   新增成品內容       *********/
		if("insert_TempCont".equals(action)){
			
			String temp_no = request.getParameter("temp_no");
			ServletContext context = request.getServletContext();
			Collection<Part> parts = request.getParts();
			try{
				for (Part part : parts) {
					if (getFileNameFromPart(part) != null && part.getContentType() != null) {
						String filename = getFileNameFromPart(part);
						byte[] file = toByteArray(part.getInputStream());
						
						if (isImgFile(context.getMimeType(filename))) {
							System.out.println("image upload");
							tcontSvc.addTempCont(temp_no, new Timestamp(System.currentTimeMillis()), file, null);
						} else {
							System.out.println("video upload");
							tcontSvc.addTempCont(temp_no, new Timestamp(System.currentTimeMillis()), null, file);
						}
					}
				}
				request.setAttribute("temp_no", temp_no);
				String url = "/Front_end/Temp/ComPage_ListAllTempConts.jsp";
				request.getRequestDispatcher(url).forward(request, response);
				return;
				
			}catch(Exception e){
				request.setAttribute("temp_no", temp_no);
				String url = "/Front_end/Temp/ComPage_ListAllTempConts.jsp";
				request.getRequestDispatcher(url).forward(request, response);
				return;
			}
		}
		
		/*********  成品挑選完成   *********/
		if ("select_TempConts".equals(action)) {
			Map<String, String> errorMsgs = new Hashtable<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String temp_no = request.getParameter("temp_no");
			String[] selectedList = request.getParameterValues("selectedList");
			System.out.println("selectedList's Size ==="+selectedList.length);
			TempVO temp = tempSvc.getOneTemp(temp_no);
			String status = "已挑選";
			
			if(selectedList.length > temp.getAvailable()){
				errorMsgs.put("outOfAvailable", "超過可挑選數量");
			}
			
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("temp_no", temp_no);
				request.setAttribute("selectedList", selectedList);
				RequestDispatcher failureView = request.getRequestDispatcher("/Front_end/Temp/MemPage_ListAllTempConts.jsp");
				failureView.forward(request, response);
				return;
			}
			// ===== 修改Temp ===== //
			tempSvc.updateTemp(temp_no, temp.getCom_no(), temp.getMem_no(), temp.getName(), temp.getCreate_date(), temp.getAvailable(), status);
			
			// ===== 將未被挑選的照片刪除 ===== //
			List<TempContVO> tempconts = tcontSvc.getAllByTempNo(temp_no);
			for(TempContVO tempVO : tempconts){
				String tcont_no = tempVO.getTcont_no(); 
				if(!Arrays.asList(selectedList).contains(tcont_no)){
					tcontSvc.deleteTempCont(tcont_no);
				}
			}
			String url = "/Front_end/Temp/MemPage_ListAllTemps.jsp";
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
		
		
	}
	private String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header: " + header);
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
	private byte[] toByteArray(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int length = 0;
		byte[] bytes = new byte[1024];
		while ((length = is.read(bytes)) != -1) {
			baos.write(bytes, 0, length);
		}
		baos.close();
		return baos.toByteArray();
	}

	private boolean isImgFile(String mimetype) {
		return mimetype != null && mimetype.startsWith("image");
	}


}
