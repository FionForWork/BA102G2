package com.album.controller;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.album.model.AlbumService;
import com.album.model.AlbumVO;
import com.content.model.ContentService;
import com.content.model.ContentVO;

@MultipartConfig(fileSizeThreshold = 10 * 1024 * 1024, maxFileSize = 5 * 10 * 1021 * 1024, maxRequestSize = 5 * 5 * 10
		* 1024 * 1024)
@WebServlet("/RemoverServlet2")
public class PreviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//request.setCharacterEncoding("utf-8");
		// response.setContentType("image/jpeg");
		// ServletOutputStream out = response.getOutputStream();
		String action = request.getParameter("action");
		AlbumService albSvc = new AlbumService();
		ContentService contSvc = new ContentService();
		ServletContext context = getServletContext();
		
		if("cropImage".equals(action)){
			/********* 建立實景預覽專用相簿與相簿內容 *********/
			
			ContentVO originalCont = null;
			ContentVO cropCont = null;
			AlbumVO alb = new AlbumVO();
			
			HttpSession session = request.getSession();
			// String mem_no = (String) session.getAttribute("mem_no");
			String mem_no = "1001";
			byte[] cover = null;
			Timestamp create_date = new Timestamp(System.currentTimeMillis());
			String alb_no = null;
			String name = "實景預覽";
			
			boolean alreadyCreated = false;
			
			List<AlbumVO> albums = albSvc.getAllByMemNo(mem_no);
			forloop:
			for(AlbumVO album : albums){
				if(name.equals(album.getName())){
					alreadyCreated = true;
					alb_no = album.getAlb_no();
					break forloop;
				}
			}
			/********* 新增相簿 *********/
			if(!alreadyCreated){
				name = "實景預覽";
				alb = albSvc.addAlbum(mem_no, name, cover, create_date);
				alb_no = alb.getAlb_no();
			}

			/********* 新增相簿內容 *********/
			BufferedImage img = null;
			Part part = request.getPart("imageRemove");
			if (getFileNameFromPart(part) != null && part.getContentType() != null) {
				String filename = getFileNameFromPart(part);
				img = javax.imageio.ImageIO.read(part.getInputStream());
				System.out.println("img----" + img);
				byte[] file = toByteArray(part.getInputStream());
				
				if (isImgFile(context.getMimeType(filename))) {
					originalCont = contSvc.addContent(alb_no, new Timestamp(System.currentTimeMillis()), file, null);
				}
			}

			/********* 修改相簿封面 *********/
			cover = originalCont.getImg();
			albSvc.updateAlbum(alb_no, mem_no, name, cover, create_date);
			
			// 取得x座標
			String xPoints = request.getParameter("xPoints");
			// 取得y座標
			String yPoints = request.getParameter("yPoints");
			System.out.println("xPoints= " + xPoints);
			System.out.println("yPoints= " + yPoints);
			// 轉成陣列
			String[] xStrArr = xPoints.substring(1, xPoints.length() - 1).split(",");
			String[] yStrArr = yPoints.substring(1, yPoints.length() - 1).split(",");
			double[] xIntArr = new double[xStrArr.length];
			double[] yIntArr = new double[yStrArr.length];
			
			for (int i = 0; i < xStrArr.length; i++) {
				xIntArr[i] = Double.parseDouble(xStrArr[i]);
				yIntArr[i] = Double.parseDouble(yStrArr[i]);
			}
			
			// 
			Graphics2D g = null;
			BufferedImage image = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
			System.out.println(img.getWidth());
			System.out.println(img.getHeight());
			g = image.createGraphics();

			Path2D path = new Path2D.Double();
			path.moveTo(xIntArr[0], yIntArr[0]);
			for (int i = 1; i < xIntArr.length; i++) {
				path.lineTo(xIntArr[i], yIntArr[i]);
			}
			// 取得裁切過後的邊界
			Rectangle bounds = path.getBounds();
			path.closePath();
			
			// 透明背景
			g.setComposite(AlphaComposite.Clear);
			g.fillRect(0, 0, image.getWidth(), image.getHeight());
			g.setComposite(AlphaComposite.Src);
			
			// 畫入新的BufferedImage
			g.setClip(path);
			g.drawImage(img, 0, 0, null);
			g.dispose();
			
			// 將多餘的圖片裁掉
			BufferedImage subimage = image.getSubimage(bounds.x, bounds.y, bounds.width, bounds.height);
			
			// 裁切過的圖片存進DB
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(subimage, "png", baos);
			InputStream is = new ByteArrayInputStream(baos.toByteArray());
			cropCont = contSvc.addContent(alb_no, new Timestamp(System.currentTimeMillis()),toByteArray(is), null);
			
			
			request.setAttribute("originalCont_no", originalCont.getCont_no());
			request.setAttribute("cropCont_no", cropCont.getCont_no());
			
			String url = "/Front_end/Preview/ImageLayover.jsp";
			request.getRequestDispatcher(url).forward(request, response);


		}
		if("overlayImage".equals(action)){
			
			System.out.println("0000000000000");
			ServletOutputStream out = response.getOutputStream();
			response.setContentType("image/jpeg");
			BufferedImage img = null ;
			ContentVO originalCont = null;
			ContentVO mergeCont = null;
			String alb_no = null;
			String name = "實景預覽";
			String mem_no = request.getParameter("mem_no");
			System.out.println("mem_no=="+mem_no);
			String backgroundImage = request.getParameter("backgroundImage");
			System.out.println("backgroundImage=="+backgroundImage);
			String cropCont_no = request.getParameter("cropCont_no");
			System.out.println("cropCont_no=="+cropCont_no);
			Double doublexPoint = Double.parseDouble(request.getParameter("xPoint"));
			System.out.println("xPoint=="+doublexPoint);
			int xPoint = doublexPoint.intValue();
			Double doubleyPoint = Double.parseDouble(request.getParameter("yPoint"));
			int yPoint = doubleyPoint.intValue();
			System.out.println("yPoint=="+doubleyPoint);
			
			List<AlbumVO> albums = albSvc.getAllByMemNo(mem_no);
			forloop:
			for(AlbumVO album : albums){
				if(name.equals(album.getName())){
					alb_no = album.getAlb_no();
					break forloop;
				}
			}
			
			Part part = request.getPart("imgfile");
			if (getFileNameFromPart(part) != null && part.getContentType() != null) {
				String filename = getFileNameFromPart(part);
				img = javax.imageio.ImageIO.read(part.getInputStream());
				System.out.println("img----" + img);
				byte[] file = toByteArray(part.getInputStream());
				
				if (isImgFile(context.getMimeType(filename))) {
					originalCont = contSvc.addContent(alb_no, new Timestamp(System.currentTimeMillis()), file, null);
				}
			}
			mergeCont = contSvc.getOneContent(cropCont_no);
			InputStream is = new ByteArrayInputStream(mergeCont.getImg());
			BufferedImage cropImage = javax.imageio.ImageIO.read(is);
			Graphics g = img.getGraphics();
			g.drawImage(cropImage, xPoint, yPoint, null);
			g.dispose();
			ImageIO.write(img, "jpeg", out);
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
