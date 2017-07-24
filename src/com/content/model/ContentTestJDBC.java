package com.content.model;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ContentTestJDBC {

	public static void main(String[] args) {
		ContentVO content = null;
		ContentDAO dao = new ContentDAO();
		
		// insert
		
//		try {
//			content = new ContentVO();
//			BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C:\\Users\\cuser\\Desktop\\BA102第二組\\img\\tdm.png"));
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			int i = 0;
//			byte[] bytes = new byte[1024];
//			while((i = bis.read(bytes)) != -1){
//				baos.write(bytes, 0, i);
//			}
//			content.setAlb_no("0001");
//			content.setImg(baos.toByteArray());
//			content.setCont_desc("Lovely kids!");
//			System.out.println("cccccccccccccccccccccccc");
//			dao.uploadImg(content);
//			baos.close();
//			bis.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		//update
		
//			content = new ContentVO();	
//			content.setCont_no("0001");
//			content.setCont_desc("我要訂婚啦!!!!!");
//			dao.updateContent(content);
	
	
		// find by pk
		
//		content = dao.findContentByPK("0002");
//		System.out.println(content.getAlb_no());
//		System.out.println(content.getCont_desc());
//		System.out.println(content.getUpload_date());
//		try {
//			FileOutputStream fos = new FileOutputStream("C:\\Users\\mac\\Desktop\\test");
//			fos.write(content.getImg());
//			fos.close();
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		// find by memId
		
//		List<ContentVO> albumList = dao.findContentsByAlbNo("0001");
//		for(int i = 0; i < albumList.size(); i++){
//			content = albumList.get(i);
//			System.out.println("find by albId");
//			System.out.println(content.getCont_no());
//			System.out.println(content.getAlb_no());
//			System.out.println(content.getCont_desc());
//			System.out.println(content.getUpload_date());
//			
//		}
//		
		
		// find all
//		List<ContentVO> albumList = dao.findAll();
//		for(int i = 0; i < albumList.size(); i++){
//			content = albumList.get(i);
//			System.out.println("find all");
//			System.out.println(content.getAlb_no());
//			System.out.println(content.getCont_no());
//			System.out.println(content.getCont_desc());
//			System.out.println(content.getUpload_date());
//
//		}
		
		// delete
		
		dao.deleteContent("0003");
		
	}

}
