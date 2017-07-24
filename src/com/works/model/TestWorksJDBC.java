package com.works.model;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class TestWorksJDBC {
	public static void main(String[] args) {
		WorksVO works = null;
		WorksDAO dao = new WorksDAO();
		
		// insert
		
//		try {
//			works = new WorksVO();
//			BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C:\\Users\\cuser\\Downloads\\Liam Payne - Strip That Down.mp4"));
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			int i = 0;
//			byte[] bytes = new byte[1024];
//			while((i = bis.read(bytes)) != -1){
//				baos.write(bytes, 0, i);
//			}
//			works.setWtype_no("0001");
//			works.setVdo(baos.toByteArray());
//			works.setName("third");
//			works.setCom_no("2001");
//			works.setWorks_desc("Strip That Down");
//			System.out.println("cccccccccccccccccccccccc");
//			dao.uploadWorksVdo(works);
//			baos.close();
//			bis.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		//update
		
//			works = new WorksVO();
//			works.setWorks_no("3002");
//			works.setCom_no("2001");
//			works.setWtype_no("0001");
//			works.setWorks_desc("新娘妝");
//			works.setUpload_date(new Timestamp(new Date().getTime()));
//			dao.updateWorks(works);
	
	
		// find by pk
		
//		works = dao.findWorksByPK("3003");
//		System.out.println(works.getWtype_no());
//		System.out.println(works.getName());
//		System.out.println(works.getUpload_date());
//		try {
//			FileOutputStream fos = new FileOutputStream("C:\\Users\\mac\\Desktop\\test");
//			fos.write(works.getImg());
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
		
//		List<WorksVO> albumList = dao.findWorksByComNo("2001");
//		for(int i = 0; i < albumList.size(); i++){
//			works = albumList.get(i);
//			System.out.println("find by comId");
//			System.out.println(works.getWtype_no());
//			System.out.println(works.getName());
//			System.out.println(works.getUpload_date());
//			
//		}
//		
		
		// find all
//		List<WorksVO> albumList = dao.findAll();
//		for(int i = 0; i < albumList.size(); i++){
//			works = albumList.get(i);
//			System.out.println("find all");
//			System.out.println(works.getWtype_no());
//			System.out.println(works.getName());
//			System.out.println(works.getUpload_date());
//
//		}
		
		// delete
		
		dao.deleteWorks("3002");
		
	}
}
