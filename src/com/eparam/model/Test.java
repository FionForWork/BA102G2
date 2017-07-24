package com.eparam.model;

import java.io.*;
import java.sql.Timestamp;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		EparamJDBCDAO dao = new EparamJDBCDAO();

		// 新增
		// EparamVO eparamVO1 = new EparamVO();
		// eparamVO1.setName("廣告費調整");
		// eparamVO1.setEparam_desc("特價打折500");
		// eparamVO1.setValue(19500.0);
		// eparamVO1.setEparam_date(Timestamp.valueOf("2017-7-21 18:00:00"));
		// dao.insert(eparamVO1);
		// System.out.println("新增成功");

		// 修改
		// EparamVO eparamVO2 = new EparamVO();
		// eparamVO2.setName("抽成費用調整");
		// eparamVO2.setEparam_desc("特價打折50%");
		// eparamVO2.setValue(0.5);
		// eparamVO2.setEparam_date(Timestamp.valueOf("2017-7-21 19:00:00"));
		// eparamVO2.setEparam_no("0003");
		// dao.update(eparamVO2);
		// System.out.println("修改成功");

		// 刪除
		// dao.delete("0003");
		// System.out.println("刪除成功");

		// 查詢一筆
//		EparamVO eparamVO3 = dao.findByPrimaryKey("0001");
//		System.out.println(eparamVO3.getEparam_no() + ",");
//		System.out.println(eparamVO3.getName() + ",");
//		System.out.println(eparamVO3.getEparam_desc() + ",");
//		System.out.println(eparamVO3.getValue() + ",");
//		System.out.println(eparamVO3.getEparam_date());
//
//		System.out.println("查詢成功");

		// 查詢全部
		List<EparamVO> list = dao.getAll();
		for (EparamVO eparam : list) {
			System.out.println(eparam.getEparam_no() + ",");
			System.out.println(eparam.getName() + ",");
			System.out.println(eparam.getEparam_desc() + ",");
			System.out.println(eparam.getValue() + ",");
			System.out.println(eparam.getEparam_date());
		}
		System.out.println("查詢成功");
	}

}
