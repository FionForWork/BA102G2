package test;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.advertising.model.AdvertisingService;
import com.advertising.model.AdvertisingVO;
import com.album.model.AlbumService;
import com.album.model.AlbumVO;
import com.com.model.*;
import com.content.model.ContentService;
import com.content.model.ContentVO;
import com.mem.model.*;
import com.tempcont.model.TempContService;
import com.tempcont.model.TempContVO;
import com.works.model.*;

public class ShowPictureServletDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		String file = request.getQueryString();
		String pk = null;
		if(file.startsWith("alb_no")){
			pk = request.getParameter("alb_no");
			System.out.println(pk);
			AlbumService albSvc = new AlbumService();
			AlbumVO alb = albSvc.getOneAlbum(pk);
			byte[] cover = alb.getCover();
			out.write(cover);
			out.close();
			return;
		}
		if(file.startsWith("cont_no")){
			pk = request.getParameter("cont_no");
			byte[] cont =null;
			System.out.println(pk);
			ContentService contSvc = new ContentService();
			ContentVO contVO = contSvc.getOneContent(pk);
			if(contVO.getImg() == null){
				cont = contVO.getVdo();
			}else{
				cont = contVO.getImg();
			}
			out.write(cont);
			out.close(); 
			return;
		}
		if(file.startsWith("tcont_no")){
			pk = request.getParameter("tcont_no");
			byte[] tempcont =null;
			System.out.println(pk);
			TempContService contSvc = new TempContService();
			TempContVO tempcontVO = contSvc.getOneTempCont(pk);
			if(tempcontVO.getImg() == null){
				tempcont = tempcontVO.getVdo();
			}else{
				tempcont = tempcontVO.getImg();
			}
			out.write(tempcont);
			out.close();
			return;
		}
		if(file.startsWith("works_no")){
			pk = request.getParameter("works_no");
			byte[] workcont =null;
			System.out.println(pk);
			WorksService worksSvc = new WorksService();
			WorksVO worksVO = worksSvc.getOneWork(pk);
			if(worksVO.getImg() == null){
				workcont = worksVO.getVdo();
			}else{
				workcont = worksVO.getImg();
			}
			out.write(workcont);
			out.close(); 
			return;
		}
		
		if(file.startsWith("all")){
			pk=request.getParameter("all");
			
			if((pk.substring(0,1)).equals("1")){
				byte[] tempcont =null;
				System.out.println("memPicture"+pk);
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(pk);
				tempcont = memVO.getPicture();
				
//				out.write(tempcont);
				out.close(); 
				return;
				
			}else{
				byte[] logo =null;
				System.out.println("comPicture"+pk);
				ComService comSvc = new ComService();
				ComVO comVO = comSvc.getOneCom(pk);
				
				logo = comVO.getLogo();
				
//				out.write(logo);
				out.close(); 
				return;
			}
			
		}
		

		
		if(file.startsWith("mem_no")){
			pk = request.getParameter("mem_no");
			byte[] picture =null;
//			System.out.println(pk);
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOneMem(pk);
			
			picture = memVO.getPicture();
			
			out.write(picture);
			out.close(); 
			return;
		}
		
		if(file.startsWith("com_no")){
			pk = request.getParameter("com_no");
			byte[] logo =null;
//			System.out.println(pk);
			ComService comSvc = new ComService();
			ComVO comVO = comSvc.getOneCom(pk);
			
			logo = comVO.getLogo();
			
			out.write(logo);
			out.close(); 
			return;
		}
		
		
		if(file.startsWith("adv_no")){
			pk = request.getParameter("adv_no");
			byte[] advcont =null;
			System.out.println(pk);
			AdvertisingService advertisingSvc = new AdvertisingService();
			AdvertisingVO advertisingVO = advertisingSvc.getOneAdvertising(pk);
			advcont = advertisingVO.getImg();
			out.write(advcont);
			out.close();

			return;
		}
		if(file.startsWith("mem_no")){
			pk = request.getParameter("mem_no");
			byte[] memcont =null;
			System.out.println(pk);
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOneMem(pk);
			memcont = memVO.getPicture();
			out.write(memcont);
			out.close(); 
			return;
		}
		if(file.startsWith("downloadCont_no")){
			
			pk = request.getParameter("downloadCont_no");
			response.setContentType("application/octet-stream");
			
			byte[] cont =null;
			System.out.println(pk);
			ContentService contSvc = new ContentService();
			ContentVO contVO = contSvc.getOneContent(pk);
			if(contVO.getImg() == null){
				cont = contVO.getVdo();
				response.setHeader("Content-Disposition", "attachment;filename=\""+pk+".mp4\"");
			}else{
				cont = contVO.getImg();
				response.setHeader("Content-Disposition", "attachment;filename=\""+pk+".jpg\"");
			}
			out.write(cont);
			out.close(); 
			return;
		}
		if(file.startsWith("adv_no")){
			pk = request.getParameter("adv_no");
			byte[] cont =null;
			System.out.println("adv_no"+pk);
			AdvertisingService advSvc=new AdvertisingService();
			AdvertisingVO advertisingVO=advSvc.getOneAdvertising(pk);
			
			cont = advertisingVO.getImg();
			
			out.write(cont);
			out.close(); 
			return;
		}
	}
}
