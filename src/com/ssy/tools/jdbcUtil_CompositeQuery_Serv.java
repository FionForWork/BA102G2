package com.ssy.tools;

import java.util.Map;
import java.util.Set;


public class jdbcUtil_CompositeQuery_Serv {
	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("topPrice".equals(columnName)){ // 用於其他
			aCondition = " price <= " + value;
		}else if ("bottomPrice".equals(columnName)){ // 用於varchar
			aCondition = " price >= " + value;
		}else if("stype_no".equals(columnName)){
			aCondition = columnName + " = " + value;
		}else if ("cal_date".equals(columnName)){                          // 用於Oracle的date
			aCondition = "com_no not in (Select com_no from calendar where to_char(cal_date,'YYYY-MM-DD') = '"+value+"')";
		}
			return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key) && !"requestURL".equals(key) && !value.equals("0000")) {
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
}
