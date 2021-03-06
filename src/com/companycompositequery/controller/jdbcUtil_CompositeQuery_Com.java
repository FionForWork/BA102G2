package com.companycompositequery.controller;
import java.util.*;
public class jdbcUtil_CompositeQuery_Com {
	
	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("com_no".equals(columnName) || "name".equals(columnName) || "loc".equals(columnName)) {
			aCondition = columnName + " like '%" + value + "%'";
		} else if ("stype_no".equals(columnName)) {
			aCondition = " com_no in (select com_no from service where stype_no = '"+ value +"')";
		}
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key) && !"requestURL".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("com_no", new String[] { "" });
//		map.put("loc", new String[] { "桃園" });
//		map.put("name", new String[] { "v" });
//		map.put("stype_no", new String[] { "0001" });

		String finalSQL = "select * from company "
				          + jdbcUtil_CompositeQuery_Com.get_WhereCondition(map)
				          + "order by com_no";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
