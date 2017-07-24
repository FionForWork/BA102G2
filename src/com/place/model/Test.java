package com.place.model;

import java.io.*;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		PlaceJDBCDAO dao = new PlaceJDBCDAO();

		// 新增
		PlaceVO placeVO1 = new PlaceVO();
		try {
			placeVO1.setName("淡水莊園");

			InputStream in1 = new FileInputStream(new File("items/171.jpg"));
			byte[] img = new byte[in1.available()];
			in1.read(img);
			in1.close();
			placeVO1.setImg(img);

			placeVO1.setLat(25.219024);
			placeVO1.setLng(121.43994399999997);
			placeVO1.setAddr("新北市淡水區興仁里前洲子20號");

		} catch (IOException e) {
			System.out.println(e);
		}
		dao.insert(placeVO1);
		System.out.println("新增成功");

		// 修改

		PlaceVO placeVO2 = new PlaceVO();
		try {
			placeVO2.setName("淡水莊園");

			InputStream in1 = new FileInputStream(new File("items/171.jpg"));
			byte[] img = new byte[in1.available()];
			in1.read(img);
			in1.close();
			placeVO1.setImg(img);

			placeVO2.setLat(25.219024);
			placeVO2.setLng(121.43994399999997);
			placeVO2.setAddr("苗栗縣西湖鄉高埔村筧窩7號");
			placeVO2.setPla_no("0023");

		} catch (IOException e) {
			System.out.println(e);
		}
		dao.update(placeVO2);
		System.out.println("修改成功");

		// 刪除

		// dao.delete("0023");
		// System.out.println("刪除成功");

		// 查詢一筆
		PlaceVO placeVO3 = dao.findByPrimaryKey("0024");
		System.out.println(placeVO3.getPla_no() + ",");
		System.out.println(placeVO3.getName() + ",");
		System.out.println(placeVO3.getImg() + ",");
		System.out.println(placeVO3.getLat() + ",");
		System.out.println(placeVO3.getLng() + ",");
		System.out.println(placeVO3.getAddr() + ",");

		// 查詢ALL
		List<PlaceVO> list = dao.getAll();
		for (PlaceVO place : list) {
			System.out.println(place.getPla_no() + ",");
			System.out.println(place.getName() + ",");
			System.out.println(place.getImg() + ",");
			System.out.println(place.getLat() + ",");
			System.out.println(place.getLng() + ",");
			System.out.println(place.getAddr() + ",");
		}
	}
}
