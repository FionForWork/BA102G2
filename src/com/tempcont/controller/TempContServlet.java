package com.tempcont.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.temp.model.TempService;
import com.tempcont.model.TempContService;

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
		System.out.println("000000000000000000");
		
		/*********   刪除成品內容       *********/
		if("delete_TempCont".equals(action)){
			String tcont_no = request.getParameter("tcont_no");
			tcontSvc.deleteTempCont(tcont_no);
			System.out.println("tcont_no====="+tcont_no);
			String temp_no = request.getParameter("temp_no");
			request.setAttribute("temp_no", temp_no);
			String url = "/Front_end/Temp/ListAllTempConts.jsp";
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
		
		/*********   新增成品內容       *********/
		if("insert_TempCont".equals(action)){
			System.out.println("111111111111111111111111");
			String temp_no = request.getParameter("temp_no");
			System.out.println("temp_no========"+temp_no);
			ServletContext context = request.getServletContext();
			Collection<Part> parts = request.getParts();
			System.out.println("partsize::::"+parts.size());
			
			for (Part part : parts) {
				if (getFileNameFromPart(part) != null && part.getContentType() != null) {
					String filename = getFileNameFromPart(part);
					byte[] file = toByteArray(part.getInputStream());
					
					if (isImgFile(context.getMimeType(filename))) {
						tcontSvc.addTempCont(temp_no, new Timestamp(System.currentTimeMillis()), file, null);
					} else {
						tcontSvc.addTempCont(temp_no, new Timestamp(System.currentTimeMillis()), null, file);
					}
				}
			}
			request.setAttribute("temp_no", temp_no);
			String url = "/Front_end/Temp/ListAllTempConts.jsp";
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
