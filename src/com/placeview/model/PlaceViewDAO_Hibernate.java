package com.placeview.model;

import java.sql.Connection;
import java.util.List;

import javax.management.relation.Role;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.ord.model.OrdVO;

import hibernate.util.HibernateUtil;
import oracle.net.aso.q;

public class PlaceViewDAO_Hibernate implements PlaceViewDAO_Interface{

    @Override
    public void insert(PlaceViewVO placeViewVO) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.getTransaction();
            session.saveOrUpdate(placeViewVO);
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void insert(PlaceViewVO placeViewVO, Connection connection) {
        // TODO Auto-generated method stub
    }

    @Override
    public void delete(String view_no) {
     // TODO Auto-generated method stub
    }

    @Override
    public void deleteByFK(String pla_no, Connection connection) {
        // TODO Auto-generated method stub
    }

    @Override
    public void deleteByFK(String pla_no) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.getTransaction();
            Query query=session.createQuery("from PlaceViewVO where PLA_NO = :pla_no");
            query.setParameter("pla_no", pla_no);
        }
        catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public void update(PlaceViewVO placeViewVO) {
        // TODO Auto-generated method stub
    }

    @Override
    public PlaceViewVO getOneByPK(String view_no) {
        PlaceViewVO placeViewVO=null;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            placeViewVO=(PlaceViewVO)session.get(PlaceViewVO.class, view_no);
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return placeViewVO;
    }

    @Override
    public List<String> getAllByFK(String pla_no) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        List<String> list=null;
        try {
            session.beginTransaction();
            Query query=session.createQuery("select VIEW_NO from PlaveViewVO where PLA_NO = :pla_no");
            query.setParameter("pla_no", pla_no);
            list=query.list();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return list;
    }

}
