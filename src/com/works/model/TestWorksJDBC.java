package com.works.model;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;



public class TestWorksJDBC {
	public static void main(String[] args) {
		WorksVO works = null;
		WorksDAO dao = new WorksDAO();
		
		// insert
		
		try {
			works = new WorksVO();
			works.setCom_no("2005");
			works.setName("2005大圖");
			works.setWorks_desc("2005廠商大圖");
			InputStream in = new BufferedInputStream(new FileInputStream("WebContent/Front_end/com_page/img/banner3.jpg"));
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			in.close();
			works.setImg(bytes);
			works.setVdo(null);
			System.out.println("cccccccccccccccccccccccc");
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			works.setUpload_date(ts);
			dao.insertWorks(works);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
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
		
//		dao.deleteWorks("3002");
		
	}
}
