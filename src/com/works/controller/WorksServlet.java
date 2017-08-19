package com.works.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONException;
import org.json.JSONObject;

import com.works.model.WorksService;
import com.works.model.WorksVO;

@MultipartConfig(fileSizeThreshold = 10 * 1024 * 1024, maxFileSize = 5 * 10 * 1021 * 1024, maxRequestSize = 5 * 5 * 10
		* 1024 * 1024)
public class WorksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		WorksService worksSvc = new WorksService();
		System.out.println("0000000000000");

		/********** 刪除作品 **********/

		if ("delete_Works".equals(action)) {
			String works_no = request.getParameter("works_no");
			String requestURL = request.getParameter("requestURL");
			String com_no = request.getParameter("com_no");
			System.out.println("works_no=========" + works_no);
			worksSvc.deleteWorks(works_no);
//			String location = requestURL + "?com_no=" + com_no;
//			request.getRequestDispatcher(location).forward(request, response);
			return;
		}

		/********** 修改作品 **********/

		if ("update_Works".equals(action)) {
			System.out.println("update");
			String works_no = request.getParameter("works_no");
			System.out.println("works_no============"+works_no);
			String com_no = request.getParameter("com_no");
			System.out.println("com_no============"+com_no);
			int totalOfWorks = worksSvc.CountWorksInOneComNo(com_no);
			WorksVO works = worksSvc.getOneWork(works_no);
			String name = request.getParameter("name");
			System.out.println("name============"+name);
			
			// ===== 檢查日期是否為空值 ===== //
			Timestamp upload_date = null;
			try {
				upload_date = covertToTimestamp(request.getParameter("upload_date"));
			} catch (NullPointerException e) {
				upload_date = works.getUpload_date();
			}
			String works_desc = request.getParameter("works_desc");
			worksSvc.updateWorks(works_no, com_no, name, works_desc,works.getImg(),works.getVdo(), upload_date);
			return;
		}

		/********** 新增作品 **********/
		if ("upload_Works".equals(action)) {
			String com_no = request.getParameter("com_no");
			ServletContext context = request.getServletContext();
			Collection<Part> parts = request.getParts();

			for (Part part : parts) {
				if (getFileNameFromPart(part) != null && part.getContentType() != null) {
					String filename = getFileNameFromPart(part);
					byte[] file = toByteArray(part.getInputStream());

					if (isImgFile(context.getMimeType(filename))) {
						System.out.println("image upload");
						worksSvc.addWorks(com_no, null, null, file, null, new Timestamp(System.currentTimeMillis()));
					} else {
						System.out.println("video upload");
						worksSvc.addWorks(com_no, null, null, null, file, new Timestamp(System.currentTimeMillis()));
					}
				}
			}
			System.out.println("123456");
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("result", "ok");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			response.getWriter().write(jsonObject.toString());

			// request.setAttribute("com_no", com_no);
			// String url = "/Front_end/Works/CreateWorks.jsp";
			// request.getRequestDispatcher(url).forward(request, response);
			// return;
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
	private Timestamp covertToTimestamp(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date original_date = null;
		try {
			original_date = formatter.parse(date + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(date);
		Timestamp create_date = new Timestamp(original_date.getTime());
		return create_date;
	}

}
