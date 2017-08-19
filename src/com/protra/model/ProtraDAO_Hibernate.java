package com.protra.model;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.infinispan.factories.annotations.Start;

import com.mysql.jdbc.EscapeTokenizer;
import com.sun.xml.internal.bind.v2.model.annotation.Quick;

import hibernate.util.HibernateUtil;
import oracle.net.aso.q;

public class ProtraDAO_Hibernate implements ProTraDAO_Interface {

    @Override
    public void insert(ProtraVO protraVO) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(protraVO);
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void deleteByComposite(String pro_no, String mem_no) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query=session.createQuery("delete form ProtraVO where PRO_NO = :pro_no and MEM_NO = :mem_no");
            query.setParameter("pro_no", pro_no);
            query.setParameter("mem_no", mem_no);
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
    
    @Override
    public void deleteByFK(String pro_no) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query=session.createQuery("delete form ProtraVO where PRO_NO = :pro_no");
            query.setParameter("pro_no", pro_no);
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void deleteByFK(String pro_no, Connection connection) {
        // TODO Auto-generated method stub
    }

    @Override
    public void update(ProtraVO protraVO) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(protraVO);
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public ProtraVO getOneByPK(String protra_no) {
        ProtraVO protraVO=null;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            protraVO=(ProtraVO)session.get(ProtraVO.class, protra_no);
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return protraVO;
    }

    @Override
    public List<ProtraVO> getAllByMem(String mem_no) {
        List<ProtraVO> list=null;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query=session.createQuery("form ProtraVO where MEM_NO = :mem_no");
            query.setParameter("mem_no", mem_no);
            list=query.list();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return list;
    }

    @Override
    public int getAllCount(String mem_no) {
        int allCount=0;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query=session.createQuery("select count(*) from ProtraVO where MEM_NO = :mem_no");
            query.setParameter("mem_no", mem_no);
            allCount=Integer.valueOf(String.valueOf(query.list().get(0)));
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return allCount;
    }

    @Override
    public List<ProtraVO> getPage(int start, int itemsCount, String mem_no) {
        List<ProtraVO> list=null;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query=session.createQuery("form ProtraVO where MEM_NO = :mem_no");
            query.setParameter("mem_no", mem_no);
            query.setFirstResult(start);
            int end=start+itemsCount;
            query.setMaxResults(end);
            list=query.list();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return list;
    }

}
