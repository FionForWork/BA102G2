package com.product.model;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.protra.model.ProtraDAO;

import hibernate.util.HibernateUtil;

public class ProductDAO_Hibernate implements ProductDAO_Interface {

    @Override
    public void insert(ProductVO productVO) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(productVO);
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(String pro_no) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            ProductVO productVO=(ProductVO)session.get(ProductVO.class, pro_no);
            session.delete(productVO);
            ProtraDAO protraDAO =new ProtraDAO();
            protraDAO.deleteByFK(pro_no);
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
    
    @Override
    public void deleteByFK(String protype_no) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Query findQuery=session.createQuery("select PRO_NO from ProductVO where PROTYPE_NO = :protype_no");
            findQuery.setParameter("protype_no", protype_no);
            List<String> proNolist=findQuery.list();
            Query deleteQuery=session.createQuery("delete from ProductVO where PROTYPE_NO = :protype_no");
            deleteQuery.setParameter("protype_no", protype_no);
            deleteQuery.executeUpdate();
            ProtraDAO protraDAO =new ProtraDAO();
            for (String pro_no : proNolist) {
                protraDAO.deleteByFK(pro_no);
            }
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
    
    @Override
    public void deleteByFK(String protype_no, Connection connection) {
        // TODO Auto-generated method stub
    }

    @Override
    public void update(ProductVO productVO) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(productVO);
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public ProductVO getOneByPK(String pro_no) {
        ProductVO productVO=null;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            productVO=(ProductVO)session.get(ProductVO.class, pro_no);
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return productVO;
    }

    @Override
    public ProductVO getOneByPKNoImg(String pro_no) {
        ProductVO productVO=null;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query=session.createQuery("select new ProductVO (PRO_NO, PRO_NAME, SELLER_NO, PRO_DESC ,PRICE, AMOUNT, PRO_DATE, PROTYPE_NO, STATUS, TIMES, SCORE) from ProductVO where PRO_NO = :pro_no");
            query.setParameter("pro_no", pro_no);
            productVO=(ProductVO)query.list().get(0);
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return productVO;
    }

    @Override
    public List<ProductVO> getAllNoImg(String status) {
        List<ProductVO> list=null;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query=session.createQuery("select new ProductVO(PRO_NO, PRO_NAME, SELLER_NO, PRO_DESC ,PRICE, AMOUNT, PRO_DATE, PROTYPE_NO, STATUS, TIMES, SCORE) from ProductVO where STATUS = :status");
            query.setParameter("status", status);
            list=query.list();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return list;
    }

    @Override
    public int getAllCount(String status) {
        int allCount=0;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query=session.createQuery("select count(*) from ProductVO where Status = :status");
            query.setParameter("status", status);
            allCount=Integer.valueOf(String.valueOf(query.list().get(0)));
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return allCount;
    }

    @Override
    public int getAllCount(String protype_no, String status) {
        int allCount=0;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query=session.createQuery("select count(*) from ProductVO where PROTYPE_NO = :protype_no and Status = :status");
            query.setParameter("protype_no", protype_no);
            query.setParameter("status", status);
            allCount=Integer.valueOf(String.valueOf(query.list().get(0)));
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return allCount;
    }

    @Override
    public List<ProductVO> getPage(int start, int itemsCount, String orderMethod, String status) {
        List<ProductVO> productList=new ArrayList<ProductVO>();
        List<Object[]>list=null;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
//            String GET_PAGE="select new ProductVO (PRO_NO, PRO_NAME, SELLER_NO, PRO_DESC ,PRICE, AMOUNT, PRO_DATE, PROTYPE_NO, STATUS, TIMES, SCORE) "+
//                             "from ProductVO where STATUS = :status order by "+orderMethod;
            String GET_PAGE="select PRO_NO, PRO_NAME, SELLER_NO, PRO_DESC ,PRICE, AMOUNT, PRO_DATE, PROTYPE_NO, STATUS, TIMES, SCORE "+
                             "from Product where STATUS = :status order by "+orderMethod;
            Query query=session.createSQLQuery(GET_PAGE);
            query.setParameter("status", status);
            query.setFirstResult(start-1);
            query.setMaxResults(itemsCount);
            list=query.list();
            for (Object[] objects : list) {
                ProductVO productVO=new ProductVO();
                productVO.setPro_no((String) objects[0]);
                productVO.setPro_name((String) objects[1]);
                productVO.setSeller_no((String) objects[2]);
                productVO.setPro_desc(String.valueOf(objects[3]));
                productVO.setPrice(Integer.valueOf(String.valueOf(objects[4])));
                productVO.setAmount(Integer.valueOf(String.valueOf(objects[5])));
                productVO.setPro_date((Timestamp) objects[6]);
                productVO.setProtype_no((String) objects[7]);
                productVO.setStatus((String) objects[8]);
                productVO.setTimes(Integer.valueOf(String.valueOf(objects[9])));
                productVO.setScore(Integer.valueOf(String.valueOf(objects[10])));
                productList.add(productVO);
            }
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return productList;
    }

    @Override
    public List<ProductVO> getPage(int start, int itemsCount, String protype_no, String orderMethod, String status) {
        List<ProductVO>list=null;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            String GET_PAGE="select new ProductVO (PRO_NO, PRO_NAME, SELLER_NO, PRO_DESC ,PRICE, AMOUNT, PRO_DATE, PROTYPE_NO, STATUS, TIMES, SCORE) "+
                             "from ProductVO where PROTYPE_NO = :protype_no and STATUS = :status order by "+orderMethod;
//            String GET_PAGE="select PRO_NO, PRO_NAME, SELLER_NO, PRO_DESC ,PRICE, AMOUNT, PRO_DATE, PROTYPE_NO, STATUS, TIMES, SCORE "+
//                             "from Product where STATUS = :status order by "+orderMethod;
            System.out.println(GET_PAGE);
            Query query=session.createQuery(GET_PAGE);
            query.setParameter("protype_no", protype_no);
            query.setParameter("status", status);
            query.setFirstResult(start-1);
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
    public int getAllCountBySeller(String seller_no) {
        int allCount=0;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query=session.createQuery("select count(*) from ProductVO where SELLER_NO = :seller_no");
            query.setParameter("seller_no", seller_no);
            allCount=Integer.valueOf(String.valueOf(query.list().get(0)));
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return allCount;
    }

    @Override
    public List<ProductVO> getPageBySeller(int start, int itemsCount, String seller_no) {
        List<ProductVO> list=null;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            String GET_PAGE="select new ProductVO (PRO_NO, PRO_NAME, SELLER_NO, PRO_DESC ,PRICE, AMOUNT, PRO_DATE, PROTYPE_NO, STATUS, TIMES, SCORE )"+
                             "from ProductVO where SELLER_NO = :seller_no";
            Query query=session.createQuery(GET_PAGE);
            query.setParameter("seller_no", seller_no);
            query.setFirstResult(start-1);
            query.setMaxResults(itemsCount);
            list=query.list();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return list;
    }

}
