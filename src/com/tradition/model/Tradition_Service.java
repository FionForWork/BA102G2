package com.tradition.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.report.model.ReportVO;

import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0;

public class Tradition_Service {
	
	private Tradition_interface dao;
	public Tradition_Service(){
		dao=new TraditionJNDIDAO();
	}
	public TraditionVO addTradition(String title,String article,Byte[] img) throws IOException{
		TraditionVO traditionVO=new TraditionVO();
		traditionVO.setTitle(title);
		traditionVO.setArticle(article);
		
		FileInputStream fis = new FileInputStream(new File("items/b03.jpg"));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();
		
		traditionVO.setImg(baos.toByteArray());
		
		dao.insert(traditionVO);
		return traditionVO;
	}
	public TraditionVO updateTradition(String title,String article,Byte[] img) throws IOException{
		TraditionVO traditionVO=new TraditionVO();
		traditionVO.setTitle(title);
		traditionVO.setArticle(article);
		
		FileInputStream fis = new FileInputStream(new File("items/b03.jpg"));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();
		
		traditionVO.setImg(baos.toByteArray());
		traditionVO.setTra_no(3);
		
		dao.update(traditionVO);
		return traditionVO;

	}
	public List<TraditionVO> getAll() {
		return dao.getAll();
	}

	public TraditionVO getOneDept(Integer tra_no) {
		return dao.findByPrimaryKey(tra_no);
	}

	public void deleteDept(Integer tra_no) {
		dao.delete(tra_no);
	}
	
	

}
