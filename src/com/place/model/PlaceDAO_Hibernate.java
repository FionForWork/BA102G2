package com.place.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.placeview.model.PlaceViewDAO;
import com.placeview.model.PlaceViewVO;
import com.sun.mail.util.QEncoderStream;

import hibernate.util.HibernateUtil;
import oracle.net.aso.q;

public class PlaceDAO_Hibernate implements PlaceDAO_Interface{

    @Override
    public void insert(PlaceVO placeVO) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.getTransaction();
            session.saveOrUpdate(placeVO);
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void insert(PlaceVO placeVO, List<PlaceViewVO> viewList) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.getTransaction();
            session.saveOrUpdate(placeVO);
            PlaceViewDAO placeViewDAO=new PlaceViewDAO();
            for (PlaceViewVO placeViewVO : viewList) {
                placeViewDAO.insert(placeViewVO);
            }
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(String pla_no) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.getTransaction();
            Query query=session.createQuery("from PlaceVO where PLA_NO = :pla_no");
            query.setParameter("pla_no", pla_no);
            PlaceViewDAO placeViewDAO=new PlaceViewDAO();
            placeViewDAO.deleteByFK(pla_no);
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void update(PlaceVO placeVO) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.getTransaction();
            session.saveOrUpdate(placeVO);
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public PlaceVO getOneByPK(String pla_no) {
        PlaceVO placeVO=null;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.getTransaction();
            Query query=session.createQuery("from PlaceVO where PLA_NO = :pla_no");
            query.setParameter("pla_no", pla_no);
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return placeVO;
    }

    @Override
    public int getAllCount() {
        int allCount=0;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.getTransaction();
            Query query=session.createQuery("select count(rownum) from PlaceVO");
            allCount=Integer.valueOf(String.valueOf(query.list().get(0)));
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return allCount;
    }

    @Override
    public List<PlaceVO> getPage(int start, int itemsCount) {
        List<PlaceVO> list=null;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.getTransaction();
            Query query=session.createQuery("from PlaceVO");
            query.setFirstResult(start);
            query.setMaxResults(itemsCount);
            list=query.list();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return list;
    }

    @Override
    public List<PlaceVO> getRange(String south, String west, String north, String east) {
        List<PlaceVO> list=null;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.getTransaction();
            Query query=session.createQuery("from PlaceVO where LAT between ? and ? and LNG between ? and ?");
            query.setParameter(0, south);
            query.setParameter(1, north);
            query.setParameter(2, west);
            query.setParameter(3, east);
            list=query.list();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return list;
    }
    
}
