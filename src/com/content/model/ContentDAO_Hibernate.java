package com.content.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;

import com.album.model.AlbumVO;

import hibernate.util.HibernateUtil;

public class ContentDAO_Hibernate implements ContentDAO_Interface {

	
	private static final String FIND_BY_ALB_NO = "from ContentVO where alb_no = ? order by upload_date desc";
	private static final String FIND_ALL = "from ContentVO order by upload_date desc";
	private static final String COUNT_SQL = "select count(*) as count from ContentVO where alb_no = ? ";
		
	@Override
	public String insertContent(ContentVO content) {
		String cont_no = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(content);
			session.getTransaction().commit();
			cont_no = content.getCont_no();
			System.out.println("cont_no====="+cont_no);
			
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return cont_no;
	}

	
	@Override
	public void deleteContent(String cont_no) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			ContentVO content = (ContentVO)session.get(ContentVO.class, cont_no);
			session.delete(content);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void updateContent(ContentVO content) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(content);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public ContentVO findContentByPK(String cont_no) {

		ContentVO content = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			content = (ContentVO) session.get(ContentVO.class, cont_no);
			session.getTransaction().commit();
			
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return content;
	}

	@Override
	public List<ContentVO> findContentsByAlbNo(String alb_no) {

		List<ContentVO> contentList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try{
			Query query = session.createQuery(FIND_BY_ALB_NO);
			query.setParameter(0, alb_no);
			contentList = query.list();
			
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return contentList;
	}

	@Override
	public List<ContentVO> findAll() {
		
		List<ContentVO> contentList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(FIND_ALL);
			contentList = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return contentList;
	}


	@Override
	public int countContentsInSingleAlbum(String alb_no) {
		int numberOfCont = 0;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(COUNT_SQL);
			query.setParameter(0,alb_no);
			Long count = (Long) query.list().get(0);
			numberOfCont = count.intValue();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return numberOfCont;

	}
}
