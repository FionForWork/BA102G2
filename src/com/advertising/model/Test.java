package com.advertising.model;

import java.io.*;
import java.sql.Timestamp;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		AdvertisingJDBCDAO dao = new AdvertisingJDBCDAO();

		// ?���?
//		AdvertisingVO adverstingVO1 = new AdvertisingVO();
//		try {
//			adverstingVO1.setCom_no("2005");
//			adverstingVO1.setTitle("標題");
//			adverstingVO1.setStartDay(Timestamp.valueOf("2017-7-18 20:20:20"));
//			adverstingVO1.setEndDay(Timestamp.valueOf("2017-8-18 20:20:20"));
//			adverstingVO1.setPrice(17000);
//
//			BufferedReader br = new BufferedReader(new FileReader("items/text2.txt"));
//			StringBuilder sb = new StringBuilder();
//			String str;
//			while ((str = br.readLine()) != null) {
//				sb.append(str);
//				sb.append("\n");
//			}
//			br.close();
//			adverstingVO1.setText(sb.toString());
//
//			// FileInputStream fis1 = new FileInputStream(new
//			// File("items/b03.jpg"));
//			// ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
//			// byte[] buffer1 = new byte[8192];
//			// int i;
//			// while ((i = fis1.read(buffer1)) != -1) {
//			// baos1.write(buffer1, 0, i);
//			// }
//			// baos1.close();
//			// fis1.close();
//			// adverstingVO1.setImg(baos1.toByteArray());
//
//			InputStream in1 = new FileInputStream(new File("items/b03.jpg"));
//			byte[] img = new byte[in1.available()];
//			in1.read(img);
//			in1.close();
//			adverstingVO1.setImg(img);
//
//			// FileInputStream fis2 = new FileInputStream(new
//			// File("items/video.mp4"));
//			// ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
//			// byte[] buffer2 = new byte[8192];
//			// int j;
//			// while ((j = fis2.read(buffer2)) != -1) {
//			// baos2.write(buffer2, 0, j);
//			// }
//			// baos2.close();
//			// fis2.close();
//			// adverstingVO1.setVdo(baos2.toByteArray());
//
//			InputStream in2 = new FileInputStream(new File("items/video.mp4"));
//			byte[] vdo = new byte[in2.available()];
//			in2.read(vdo);
//			in2.close();
//			adverstingVO1.setVdo(vdo);
//
//			adverstingVO1.setStatus("0");
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//		dao.insert(adverstingVO1);
//		System.out.println("?��增�?��??");

		// 修改
//		AdvertisingVO adverstingVO2 = new AdvertisingVO();
//		try {
//			adverstingVO2.setCom_no("2001");
//			adverstingVO2.setStartDay(Timestamp.valueOf("2017-9-09 00:00:00"));
//			adverstingVO2.setEndDay(Timestamp.valueOf("2017-9-09 00:00:00"));
//			adverstingVO2.setPrice(2000);
//
//			BufferedReader br = new BufferedReader(new FileReader("items/text.txt"));
//			StringBuilder sb = new StringBuilder();
//			String str;
//			while ((str = br.readLine()) != null) {
//				sb.append(str);
//				sb.append("\n");
//			}
//			br.close();
//			adverstingVO2.setText(sb.toString());
//
//			InputStream in1 = new FileInputStream(new File("items/b03.jpg"));
//			byte[] img = new byte[in1.available()];
//			in1.read(img);
//			in1.close();
//			adverstingVO1.setImg(img);
//
//			InputStream in2 = new FileInputStream(new File("items/video.mp4"));
//			byte[] vdo = new byte[in2.available()];
//			in2.read(vdo);
//			in2.close();
//			adverstingVO1.setVdo(vdo);
//
//			adverstingVO2.setStatus("1");
//			adverstingVO2.setAdv_no("0020");
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//		dao.update(adverstingVO2);
//		System.out.println("修改??��??");

		// ?��?��
//		dao.delete("0020");
//		System.out.println("?��?��??��??");

		// ?��詢�?�?
//		AdvertisingVO adverstingVO3 = dao.findByPrimaryKey("0001");
//		System.out.print(adverstingVO3.getCom_no() + ",");
//		System.out.print(adverstingVO3.getTitle() + ",");
//		System.out.print(adverstingVO3.getStartDay() + ",");
//		System.out.print(adverstingVO3.getEndDay() + ",");
//		System.out.print(adverstingVO3.getPrice() + ",");
//		System.out.print(adverstingVO3.getText());
//		System.out.print(adverstingVO3.getImg() + ",");
//		System.out.print(adverstingVO3.getVdo() + ",");
//		System.out.print(adverstingVO3.getStatus());
//		System.out.println("?��詢�?��??");

		// // ?��詢ALL
//		List<AdvertisingVO> list = dao.getAll();
//		for (AdvertisingVO adversting : list) {
//			System.out.print(adversting.getAdv_no() + ",");
//			System.out.print(adversting.getCom_no() + ",");
//			System.out.print(adversting.getTitle() + ",");
//			System.out.print(adversting.getStartDay() + ",");
//			System.out.print(adversting.getEndDay() + ",");
//			System.out.print(adversting.getPrice() + ",");
//			System.out.print(adversting.getText() + ",");
//			System.out.print(adversting.getImg() + ",");
//			System.out.print(adversting.getVdo() + ",");
//			System.out.print(adversting.getStatus());
//			System.out.println();
//		}
//		List<AdvertisingVO> list = dao.getOneAll("2001");
//		for (AdvertisingVO adversting : list) {
//			System.out.print(adversting.getAdv_no() + ",");
//			System.out.print(adversting.getCom_no() + ",");
//			System.out.print(adversting.getTitle() + ",");
//			System.out.print(adversting.getStartDay() + ",");
//			System.out.print(adversting.getEndDay() + ",");
//			System.out.print(adversting.getPrice() + ",");
//			System.out.print(adversting.getText() + ",");
//			System.out.print(adversting.getImg() + ",");
//			System.out.print(adversting.getVdo() + ",");
//			System.out.print(adversting.getStatus());
//			System.out.println();
//	}
//	
		List<AdvertisingVO> list = dao.getOneSatus();
		for (AdvertisingVO adversting : list) {
			System.out.print(adversting.getAdv_no() + ",");
			System.out.print(adversting.getCom_no() + ",");
			System.out.print(adversting.getTitle() + ",");
			System.out.print(adversting.getStartDay() + ",");
			System.out.print(adversting.getEndDay() + ",");
			System.out.print(adversting.getPrice() + ",");
			System.out.print(adversting.getText() + ",");
			System.out.print(adversting.getImg() + ",");
			System.out.print(adversting.getVdo() + ",");
			System.out.print(adversting.getStatus());
			System.out.println();
	}
	}
}
