package com.album.model;

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

import hibernate.util.HibernateUtil;

public class AlbumDAO_Hibernate implements AlbumDAO_Interface {

	private static final String FIND_BY_MEM_NO = "from AlbumVO where mem_no = :mem_no order by create_date desc";
	private static final String FIND_ALL = "from AlbumVO order by create_date desc";
	@Override
	public String createAlbum(AlbumVO album) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		String alb_no = null;
		try{
			session.beginTransaction();
			session.saveOrUpdate(album);
			session.getTransaction().commit();
			alb_no = album.getAlb_no();
			System.out.println("alb_no====="+alb_no);
			
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return alb_no;
	}

	@Override
	public void deleteAlbum(String alb_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			AlbumVO album = (AlbumVO)session.get(AlbumVO.class, alb_no);
			session.delete(album);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void updateAlbum(AlbumVO album) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(album);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public AlbumVO findAlbumByPK(String alb_no) {
		AlbumVO album = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			album = (AlbumVO) session.get(AlbumVO.class, alb_no);
			session.getTransaction().commit();
			
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return album;
	}

	@Override
	public List<AlbumVO> findAlbumsByMemNo(String mem_no) {
		
		List<AlbumVO> albumList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try{
			Query query = session.createQuery(FIND_BY_MEM_NO);
			query.setParameter("mem_no", mem_no);
			albumList = query.list();
			
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return albumList;
	}

	@Override
	public List<AlbumVO> findAll() {
		
		List<AlbumVO> albumList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(FIND_ALL);
			albumList = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return albumList;
	}
}
