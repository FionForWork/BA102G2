package com.message.model;

import java.io.*;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		MessageJDBCDAO dao = new MessageJDBCDAO();

		// 新增
		// MessageVO messageVO1 = new MessageVO();
		// try {
		// messageVO1.setMem_no("1003");
		// messageVO1.setCom_no("2003");
		//
		// BufferedReader br = new BufferedReader(new
		// FileReader("items/text.txt"));
		// StringBuilder sb = new StringBuilder();
		// String str;
		// while ((str = br.readLine()) != null) {
		// sb.append(str);
		// sb.append("\n");
		// }
		// br.close();
		//
		// messageVO1.setContent(sb.toString());
		//
		// } catch (IOException e) {
		// System.out.println(e);
		// }
		// dao.insert(messageVO1);
		// System.out.println("新增成功");

		// 修改
		// MessageVO messageVO2 = new MessageVO();
		// try {
		// messageVO2.setMem_no("1003");
		// messageVO2.setCom_no("2003");
		//
		// BufferedReader br = new BufferedReader(new
		// FileReader("items/text.txt"));
		// StringBuilder sb = new StringBuilder();
		// String str;
		// while ((str = br.readLine()) != null) {
		// sb.append(str);
		// sb.append("\n");
		// }
		// br.close();
		//
		// messageVO2.setContent(sb.toString());
		// messageVO2.setMsg_no("0001");
		//
		// } catch (IOException e) {
		// System.out.println(e);
		// }
		// dao.update(messageVO2);
		// System.out.println("修改成功");

		// 刪除
		// dao.delete("0005");
		// System.out.println("刪除成功");

		// 查詢一筆
//		 MessageVO messageVO3 = dao.findByPrimaryKey("0004");
//		 System.out.println(messageVO3.getMsg_no() + ",");
//		 System.out.println(messageVO3.getMem_no() + ",");
//		 System.out.println(messageVO3.getCom_no() + ",");
//		 System.out.println(messageVO3.getContent());

		// 查詢全部
		List<MessageVO> list = dao.getAll();
		for (MessageVO message : list) {
			System.out.println(message.getMsg_no() + ",");
			System.out.println(message.getMem_no() + ",");
			System.out.println(message.getCom_no() + ",");
			System.out.println(message.getContent());
			System.out.println();
		}
		System.out.println("查詢成功");
	}
}
