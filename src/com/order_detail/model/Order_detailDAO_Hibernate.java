package com.order_detail.model;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.sun.org.apache.xml.internal.security.keys.content.RetrievalMethod;

import hibernate.util.HibernateUtil;
import oracle.net.aso.q;

public class Order_detailDAO_Hibernate implements Order_detailDAO_Interface{
    
    @Override
    public void insert(Order_detailVO order_detailVO) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.getTransaction();
            session.saveOrUpdate(order_detailVO);
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void insert(Order_detailVO order_detailVO, Connection connection) {
        // TODO Auto-generated method stub
    }

    @Override
    public void delete(String ord_no) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.getTransaction();
            Query query=session.createQuery("from Order_detailVO where ORD_NO = :ord_no");
            query.setParameter("ord_no", ord_no);
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
    
    @Override
    public void delete(String ord_no, Connection connection) {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void update(Order_detailVO order_detailVO) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(order_detailVO);
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Order_detailVO getOneByComposite(String ord_no, String pro_no) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Order_detailVO order_detailVO=null;
        try {
            session.beginTransaction();
            Query query=session.createQuery("from Order_detailVO where ORD_NO = :ord_no and PRO_NO = :pro_no");
            query.setParameter("ord_no", ord_no);
            query.setParameter("pro_no", pro_no);
            order_detailVO = (Order_detailVO)query.list().get(0);
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return order_detailVO;
    }

    @Override
    public List<Order_detailVO> getAllByOrd(String ord_no) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        List<Order_detailVO> list=null;
        try {
            session.beginTransaction();
            Query query=session.createQuery("from Order_detailVO where ORD_NO = :ord_no ");
            query.setParameter("ord_no", ord_no);
            list=query.list();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return list;
    }

    @Override
    public List<Order_detailVO> getAllByPro(String pro_no) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        List<Order_detailVO> list=null;
        try {
            session.beginTransaction();
            Query query=session.createQuery("from Order_detailVO where PRO_NO = :pro_no");
            query.setParameter("pro_no", pro_no);
            list=query.list();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return list;
    }

}
