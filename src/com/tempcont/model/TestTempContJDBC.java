package com.tempcont.model;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class TestTempContJDBC {

	public static void main(String[] args) {
		TempContVO tempcont = null;
		TempContDAO dao = new TempContDAO();
		
		// insert
		
//		BufferedInputStream bis;
//		try {
//			bis = new BufferedInputStream(new FileInputStream("C:\\Users\\cuser\\Downloads\\Liam Payne - Strip That Down.mp4"));
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			int i = 0;
//			byte[] bytes = new byte[1024];
//			while((i = bis.read(bytes)) != -1){
//				baos.write(bytes, 0, i);
//			}
//				tempcont = new TempContVO();
//				tempcont.setTemp_no("0003");
//				tempcont.setVdo(baos.toByteArray());
//				tempcont.setTcont_desc("Stay!");
//				String temp_no = dao.uploadVdo(tempcont);
//				System.out.println(temp_no);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		
		
		//update
//		
//			tempcont = new TempContVO();
//			tempcont.setTemp_no("0001");
//			tempcont.setTcont_no("0001");
//			tempcont.setTcont_desc("太美啦~~");
//			tempcont.setUpload_date(new Timestamp(new Date().getTime()));
//			dao.updateTempCont(tempcont);
//	
		// find by pk
		
//		tempcont = dao.findTempContByPK("0001");
//		System.out.println(tempcont.getTemp_no());
//		System.out.println(tempcont.getTcont_desc());
//		System.out.println(tempcont.getUpload_date());
		
		
		
		// find by memId
//		
//		List<TempContVO> tempList = dao.findTempContsByTempNo("0003");
//		for(int i = 0; i < tempList.size(); i++){
//			tempcont = tempList.get(i);
//			System.out.println("find by memId");
//			System.out.println(tempcont.getTemp_no());
//			System.out.println(tempcont.getTcont_desc());
//			System.out.println(tempcont.getUpload_date());
//		}
		
		// find all
//		List<TempContVO> tempList = dao.findAll();
//		for(int i = 0; i < tempList.size(); i++){
//			tempcont = tempList.get(i);
//			System.out.println("find all");
//			System.out.println(tempcont.getTemp_no());
//			System.out.println(tempcont.getTemp_no());
//			System.out.println(tempcont.getTcont_desc());
//			System.out.println(tempcont.getUpload_date());
//
//		}
//		
//		// delete
//		
		dao.deleteTempCont("0006");
		
		
		
}
}
