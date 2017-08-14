package com.ord.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.order_detail.model.Order_detailDAO;
import com.order_detail.model.Order_detailVO;

import hibernate.util.HibernateUtil;

public class OrdDAO implements OrdDAO_Interface{

    @Override
    public void insert(OrdVO ordVO) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(ordVO);
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void insert(OrdVO ordVO, List<Order_detailVO> list) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(ordVO);
            Order_detailDAO order_detailDAO=new Order_detailDAO();
            for (Order_detailVO order_detailVO : list) {
                order_detailDAO.insert(order_detailVO);
            }
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(String ord_no) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            OrdVO ordVO=(OrdVO)session.get(OrdVO.class, ord_no);
            session.delete(ordVO);
            Order_detailDAO order_detailDAO=new Order_detailDAO();
            order_detailDAO.delete(ord_no);
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void update(OrdVO ordVO) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(ordVO);
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public OrdVO getOneByPK(String ord_no) {
        OrdVO ordVO=null;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            ordVO=(OrdVO)session.get(OrdVO.class, ord_no);
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return ordVO;
    }

    @Override
    public List<OrdVO> getAll() {
        return null;
    }

    @Override
    public List<OrdVO> getAllByCust(String cust_no, String status) {
        List<OrdVO> list = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from OrdVO where CUST_NO = :cust_no and Status = :status");
            query.setParameter("cust_no", cust_no);
            query.setParameter("status", status);
            list = query.list();
        } catch (RuntimeException ex) {
            session.getTransaction().rollback();
            throw ex;
        }
        return list;
    }

    @Override
    public List<OrdVO> getAllBySeller(String seller_no, String status) {
        List<OrdVO> list = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from OrdVO where SELLER_NO = :seller_no and Status = :status ");
            query.setParameter("seller_no", seller_no);
            query.setParameter("status", status);
            list = query.list();
        } catch (RuntimeException ex) {
            session.getTransaction().rollback();
            throw ex;
        }
        return list;
    }

    @Override
    public List<OrdVO> getAllByCust(String cust_no, String status, String orderMethod) {
        List<OrdVO> list = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from OrdVO where CUST_NO = :cust_no and Status = :status order by :orderMethod");
            query.setParameter("cust_no", cust_no);
            query.setParameter("status", status);
            query.setParameter("orderMethod", orderMethod);
            list = query.list();
        } catch (RuntimeException ex) {
            session.getTransaction().rollback();
            throw ex;
        }
        return list;
    }

    @Override
    public List<OrdVO> getAllBySeller(String seller_no, String status, String orderMethod) {
        List<OrdVO> list = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from OrdVO where SELLER_NO = :seller_no and Status = :status order by :orderMethod");
            query.setParameter("seller_no", seller_no);
            query.setParameter("status", status);
            query.setParameter("orderMethod", orderMethod);
            list = query.list();
        } catch (RuntimeException ex) {
            session.getTransaction().rollback();
            throw ex;
        }
        return list;
    }

    @Override
    public int getAllRowCountByCust(String cust_no, String status) {
        int rowCount=0;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select count(rownum) from OrdVO where CUST_NO = :cust_no and Status = :status");
            query.setParameter("cust_no", cust_no);
            query.setParameter("status", status);
            List result=query.list();
            rowCount=Integer.valueOf(result.get(0).toString());
        }catch (RuntimeException ex) {
            session.getTransaction().rollback();
            throw ex;
        }
        return rowCount;
    }
    
    @Override
    public int getAllRowCountBySeller(String seller_no, String status) {
        int rowCount=0;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select count(rownum) from OrdVO where SELLER_NO = :seller_no and Status = :status");
            query.setParameter("seller_no", seller_no);
            query.setParameter("status", status);
            List result=query.list();
            rowCount=Integer.valueOf(result.get(0).toString());
        }catch (RuntimeException ex) {
            session.getTransaction().rollback();
            throw ex;
        }
        return rowCount;
    }

    @Override
    public List<OrdVO> getPageByCust(int start, int itemsCount, String cust_no, String status, String orderMethod) {
        List<OrdVO> list = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from OrdVO where CUST_NO = :cust_no and Status = :status order by :orderMethod");
            query.setParameter("cust_no", cust_no);
            query.setParameter("status", status);
            query.setParameter("orderMethod", orderMethod);
            query.setFirstResult(start);
            query.setMaxResults(itemsCount);
            list = query.list();
            session.getTransaction().commit();
        } catch (RuntimeException ex) {
            session.getTransaction().rollback();
            throw ex;
        }
        return list;
    }

    @Override
    public List<OrdVO> getPageBySeller(int start, int itemsCount, String seller_no, String status, String orderMethod) {
        List<OrdVO> list = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from OrdVO where SELLER_NO = :seller_no and Status = :status order by :orderMethod");
            query.setParameter("seller_no", seller_no);
            query.setParameter("status", status);
            query.setParameter("orderMethod", orderMethod);
            query.setFirstResult(start);
            query.setMaxResults(itemsCount);
            list = query.list();
            session.getTransaction().commit();
        } catch (RuntimeException ex) {
            session.getTransaction().rollback();
            throw ex;
        }
        return list;
    }
}
