package com.ssy.tools;

import java.util.HashMap;

public class SortingHat {
	
	public String getQuoteNum(int quoteNum){
		
		String quoteStatus;
		
		if(quoteNum == 0){
			quoteStatus = "尚未有廠商報價";
		}else{
			quoteStatus = "已有" + quoteNum + "間廠商報價";
		}
		return quoteStatus ;
	}
	
	public String getRFQStatus(String status){
		String RFQStatus;
		
		if(status.equals("0")){
			RFQStatus = "關閉";
		}else{
			RFQStatus = "開啟";
		}
		return RFQStatus;
	}
	
	public String getServType(String stype_no){
		HashMap<String,String> typeMap = new HashMap<String,String>();
		typeMap.put("0001", "拍婚紗");
		typeMap.put("0002", "婚攝婚錄");
		typeMap.put("0003", "新秘");
		
		return typeMap.get(stype_no);
	}
	
	public String getResStatus(String status){
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap.put("0", "未繳訂金");
		resMap.put("1", "已繳訂金");
		resMap.put("2", "服務已完成");
		
		return resMap.get(status);
	}
	
}
