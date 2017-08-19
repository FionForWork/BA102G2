package com.product_type.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.product.model.ProductDAO;

import hibernate.util.HibernateUtil;

public class Product_typeDAO_Hibernate implements Product_typeDAO_Interface {

    @Override
    public void insert(Product_typeVO product_typeVO) {
        Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(product_typeVO);
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(String protype_no) {
        Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Product_typeVO product_typeVO=(Product_typeVO)session.get(Product_typeVO.class, protype_no);
            session.delete(product_typeVO);
            ProductDAO productDAO=new ProductDAO();
            productDAO.delete(protype_no);
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void update(Product_typeVO product_typeVO) {
        Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(product_typeVO);
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Product_typeVO getOneByPK(String protype_no) {
        Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        Product_typeVO product_typeVO=null;
        try {
            session.beginTransaction();
            product_typeVO=(Product_typeVO)session.get(Product_typeVO.class, protype_no);
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return product_typeVO;
    }

    @Override
    public List<Product_typeVO> getAll() {
        Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        List<Product_typeVO> list=null;
        try {
            session.beginTransaction();
            Query query=session.createQuery("from Product_typeVO");
            list=query.list();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return list;
    }

}
