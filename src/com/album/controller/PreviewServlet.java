package com.album.controller;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.album.model.AlbumService;
import com.album.model.AlbumVO;
import com.content.model.ContentService;
import com.content.model.ContentVO;
import com.placeview.model.PlaceViewService;
import com.placeview.model.PlaceViewVO;

@MultipartConfig(fileSizeThreshold = 10 * 1024 * 1024, maxFileSize = 5 * 10 * 1021 * 1024, maxRequestSize = 5 * 5 * 10
		* 1024 * 1024)
public class PreviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		AlbumService albSvc = new AlbumService();
		ContentService contSvc = new ContentService();
		PlaceViewService placeviewSvc = new PlaceViewService();
		
		/***** 圖片裁切  *****/
		if("cropImage".equals(action)){
			
			/********* 建立實景預覽專用相簿與相簿內容 *********/
			ContentVO cropCont = null;
			AlbumVO alb = new AlbumVO();
			
			String mem_no = request.getParameter("mem_no");
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
			/********* 新增實景預覽相簿 *********/
			if(!alreadyCreated){
				name = "實景預覽";
				alb = albSvc.addAlbum(mem_no, name, cover, create_date);
				alb_no = alb.getAlb_no();
			}

			// 取得原照片
			BufferedImage img = null;
			Part part = request.getPart("imageRemove");
			if (getFileNameFromPart(part) != null && part.getContentType() != null) {
				img = javax.imageio.ImageIO.read(part.getInputStream());
				System.out.println("img----" + img);
			}

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
			
			// 畫出根原圖一樣大小的image
			BufferedImage image = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
			System.out.println(img.getWidth());
			System.out.println(img.getHeight());
			// 繪製裁切的路線
			Graphics2D g = null;
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
//			g.setComposite(AlphaComposite.Clear);
//			g.fillRect(0, 0, image.getWidth(), image.getHeight());
//			g.setComposite(AlphaComposite.Src);
			
			// 畫入新的BufferedImage
			g.setClip(path);
			g.drawImage(img, 0, 0, null);
			g.dispose();
			
			// 將多餘的圖片裁掉
			BufferedImage subimage = image.getSubimage(bounds.x, bounds.y, bounds.width, bounds.height);
			
			// 裁切過的圖片存進DB
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(subimage, "png", baos);
			cropCont = contSvc.addContent(alb_no, new Timestamp(System.currentTimeMillis()),baos.toByteArray(), null);
			albSvc.updateAlbum(alb_no, mem_no, name, cropCont.getImg(), create_date);
			request.setAttribute("cropCont_no", cropCont.getCont_no());
			
			String url = "/Front_end/Preview/ImageLayover.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
		
		
		/***** 圖片合成  *****/
		if("overlayImage".equals(action)){
			
			BufferedImage img = null ;
			Graphics g = null;
			ContentVO cropCont = null;
			ContentVO resultCont = null;
			String alb_no = null;
			
			String name = "實景預覽";
			String mem_no = request.getParameter("mem_no");
			String placeview_no = request.getParameter("placeview_no").trim();
			
			String cropWidth = request.getParameter("cropWidth").trim();
			String cropHeight = request.getParameter("cropHeight").trim();
			int intcropWidth= 0;
			int intcropHeight = 0;
			
			String imageWidth = request.getParameter("imageWidth");
			String cropCont_no = request.getParameter("cropCont_no");
			System.out.println("cropCont_no=="+cropCont_no);
			System.out.println("backgroundImage=="+placeview_no);
			
			
			// 取得裁切圖片移動的座標
			int xPoint = 0;
			int yPoint = 0;
			try{
				Double doublexPoint = Double.parseDouble(request.getParameter("xPoint"));
				xPoint = doublexPoint.intValue();
				Double doubleyPoint = Double.parseDouble(request.getParameter("yPoint"));
				yPoint = doubleyPoint.intValue();
				
			}catch(NumberFormatException e){
				xPoint = 0;
				yPoint = 0;
			}
			
			// 取得相簿編號
			List<AlbumVO> albums = albSvc.getAllByMemNo(mem_no);
			forloop:
			for(AlbumVO album : albums){
				if(name.equals(album.getName())){
					alb_no = album.getAlb_no();
					break forloop;
				}
			}
			
			// 判斷使用景點照或是上傳照片
			if(placeview_no.length() != 0){
				
				PlaceViewVO placeview = placeviewSvc.getOneByPK(placeview_no);
				img = javax.imageio.ImageIO.read(new ByteArrayInputStream(placeview.getImg()));
				g = img.getGraphics();
			}else{
				
				Part part = request.getPart("imgfile");
				if (getFileNameFromPart(part) != null && part.getContentType() != null) {
					img = javax.imageio.ImageIO.read(part.getInputStream());
					System.out.println("img----" + img);
					
					if(imageWidth.length() != 0){
						BufferedImage originalimg = img;
						System.out.println("img----" + img);
						
						int resizeWidth = Integer.parseInt(imageWidth);
						int ratio = originalimg.getWidth() / resizeWidth;
						int resizeHeight = originalimg.getHeight() / ratio;
						img = new BufferedImage(resizeWidth,resizeHeight,BufferedImage.TYPE_INT_RGB);
						Graphics tempg = img.getGraphics();
						tempg.drawImage(originalimg, 0,0,resizeWidth, resizeHeight, null);
						
					}
					g = img.getGraphics();
				}
			}
			// 取得裁剪過的圖
			cropCont = contSvc.getOneContent(cropCont_no);
			BufferedImage cropImage = javax.imageio.ImageIO.read(new ByteArrayInputStream(cropCont.getImg()));
			
			
			//判斷是否有調整cropCont大小
			if(cropWidth.length() != 0 && cropHeight.length() != 0){
				Double doublecropWidth = Double.parseDouble(cropWidth);
				intcropWidth = doublecropWidth.intValue();
				
				Double doublecropHeight = Double.parseDouble(cropHeight);
				intcropHeight = doublecropHeight.intValue();
				Image cropImageTmp = cropImage.getScaledInstance(intcropWidth, intcropHeight, Image.SCALE_AREA_AVERAGING);
				g.drawImage(cropImageTmp, xPoint, yPoint, null);
			}else{
				g.drawImage(cropImage, xPoint, yPoint,null);
			}
			g.dispose();
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, "jpeg", baos);
			resultCont = contSvc.addContent(alb_no, new Timestamp(System.currentTimeMillis()),baos.toByteArray(), null);
			albSvc.updateAlbum(alb_no, mem_no, name, resultCont.getImg(), new Timestamp(System.currentTimeMillis()));
			request.setAttribute("mergeCont_no",resultCont.getCont_no());
			String url = "/Front_end/Preview/ShowResult.jsp";
			request.getRequestDispatcher(url).forward(request, response);

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
}
