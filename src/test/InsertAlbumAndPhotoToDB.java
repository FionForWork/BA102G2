package test;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.album.model.AlbumJDBCDAO;
import com.album.model.AlbumService;
import com.album.model.AlbumVO;
import com.content.model.ContentJDBCDAO;
import com.content.model.ContentService;
import com.content.model.ContentVO;

public class InsertAlbumAndPhotoToDB {

	public static void main(String[] args) throws IOException {

		int numberOfPic = 20;
		List<String> names = new ArrayList<String>();
		names.add("墾丁拍攝");names.add("白沙灣婚紗攝影作品");
		names.add("花蓮攝影挑選"); names.add("愛在夕陽裡");
		
		
		AlbumJDBCDAO albSvc;
		AlbumVO alb = null;
		ContentVO cont = null;
		ContentJDBCDAO contSvc;
		String[] mems = new String[3]; 
		int start = 1;
		
		for(int i = 0; i < 3; i ++){
			mems[i] = String.valueOf(i+1001);
		}
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = null;
		for (int i = 0; i < 30 ; i++) {
			System.out.println("1111111111111");
			albSvc = new AlbumJDBCDAO();
			String alb_no = null;
			
			for (int j = start; j <= numberOfPic; j++) {
				System.out.println("222222222222222222");
				String path = "WebContent/Front_end/Album/img/ab/" + j + ".jpg";
				
				
					fis = new FileInputStream(path);
					bis = new BufferedInputStream(fis);
					baos = new ByteArrayOutputStream();
					byte[] pics = new byte[2048];
					int length = 0;
					while ((length = bis.read(pics)) != -1) {
						baos.write(pics, 0, length);
					}
					if (j == start) {
						System.out.println("33333333333333333");
						alb = new AlbumVO();
						alb.setMem_no(mems[i]);
						alb.setName(names.get(i));
						alb.setCreate_date(new Timestamp(System.currentTimeMillis()));
						alb.setCover(baos.toByteArray());
						alb_no = albSvc.createAlbum(alb);
						System.out.println("alb_no---------------------"+alb_no);
					}
					contSvc = new ContentJDBCDAO();
					cont = new ContentVO();
					System.out.println("========"+alb_no);
					System.out.println("baos"+baos.size());
					cont.setAlb_no(alb_no);
					System.out.println("========111"+alb_no);
					Timestamp upload_date = new Timestamp(System.currentTimeMillis());
					cont.setUpload_date(upload_date);
					System.out.println("========2222"+upload_date);
					cont.setImg(baos.toByteArray());
					System.out.println("========333");
					cont.setVdo(null);
					System.out.println("========444"+cont.toString());
					System.out.println(alb_no);
					contSvc.insertContent(cont);

				
			}
			
			start= start+20;
			System.out.println("start::::"+start);
			numberOfPic = numberOfPic+20;
			fis.close();
			bis.close();
			baos.close();
		}
		
	}
}
