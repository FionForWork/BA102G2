package com.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDAO implements ProductDAO_Interface {
    private static final String INSERT                  = "insert into PRODUCT (PRO_NO, PRO_NAME, SELLER_NO,PRO_DESC,PRICE,AMOUNT,IMG,PRO_DATE,PROTYPE_NO,STATUS,TIMES,SCORE)" + "values('4'||lpad(PRO_NO_SEQ.NEXTVAL,3,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_BY_PK            = "delete from PRODUCT where PRO_NO = ?";
    private static final String DELETE_BY_FK            = "delete from PRODUCT where PROTYPE_NO = ?";
    private static final String UPDATE                  = "update PRODUCT set PRO_NAME = ? ,PRO_DESC = ?, PRICE= ? ,AMOUNT = ? ,IMG = ?,PROTYPE_NO = ? ,STATUS = ? ,TIMES = ? , SCORE = ? where PRO_NO = ?";
    private static final String GET_ONE_BY_PK           = "select * from PRODUCT where PRO_NO = ?";
    private static final String GET_ONE_BY_PK_NO_IMG    = "select PRO_NO, PRO_NAME, SELLER_NO, PRO_DESC ,PRICE,AMOUNT,PRO_DATE,PROTYPE_NO,STATUS,TIMES,SCORE from PRODUCT where PRO_NO = ?";
    private static final String GET_ALL_BY_STATUS       = "select PRO_NO, PRO_NAME, SELLER_NO, PRO_DESC ,PRICE,AMOUNT,PRO_DATE,PROTYPE_NO,STATUS,TIMES,SCORE from PRODUCT where STATUS = ?";
    private static final String GET_ROWCOUNT_BY_STATUS  = "select count(rownum) from PRODUCT where STATUS = ?";
    private static final String GET_ROWCOUNT_BY_PROTYPE = "select count(rownum) from PRODUCT where STATUS = ? and PROTYPE_NO = ?";
    private static final String GET_ROWCOUNT_BY_SELLER  = "select count(rownum) from PRODUCT where SELLER_NO = ?";
    private static final String GET_PAGE_BY_SELLER      = "select PRO_NO, PRO_NAME, SELLER_NO, PRO_DESC ,PRICE, AMOUNT, PRO_DATE, PROTYPE_NO, STATUS, TIMES, SCORE " + "from (select rownum bRn, b.*from (select rownum aRn, a.* from PRODUCT a where SELLER_NO = ? order by a.pro_no) b) where bRn between ? and ?";

    private Connection        connection;
    private PreparedStatement preparedStatement;
    private ResultSet         resultSet;
    private static DataSource dataSource = null;
    static {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G2DB");
        }
        catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void cancelConnection() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public void insert(ProductVO productVO) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, productVO.getPro_name());
            preparedStatement.setString(2, productVO.getSeller_no());
            preparedStatement.setString(3, productVO.getPro_desc());
            preparedStatement.setInt(4, productVO.getPrice());
            preparedStatement.setInt(5, productVO.getAmount());
            preparedStatement.setBytes(6, productVO.getImg());
            preparedStatement.setTimestamp(7, productVO.getPro_date());
            preparedStatement.setString(8, productVO.getProtype_no());
            preparedStatement.setString(9, productVO.getStatus());
            preparedStatement.setInt(10, productVO.getTimes());
            preparedStatement.setInt(11, productVO.getScore());
            preparedStatement.execute();

            connection.commit();
        }
        catch (SQLException e) {
            try {
                connection.rollback();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            try {
                cancelConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String pro_no) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_BY_PK);
            preparedStatement.setString(1, pro_no);
            preparedStatement.execute();
            connection.commit();
        }
        catch (SQLException e) {
            try {
                connection.rollback();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            try {
                cancelConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void deleteByFK(String protype_no) {
        
    }
    
    @Override
    public void deleteByFK(String protype_no, Connection connection) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_FK);
            preparedStatement.setString(1, protype_no);
            preparedStatement.executeQuery();
        }
        catch (Exception e) {
            try {
                connection.rollback();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(ProductVO productVO) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, productVO.getPro_name());
            preparedStatement.setString(2, productVO.getPro_desc());
            preparedStatement.setInt(3, productVO.getPrice());
            preparedStatement.setInt(4, productVO.getAmount());
            preparedStatement.setBytes(5, productVO.getImg());
            preparedStatement.setString(6, productVO.getProtype_no());
            preparedStatement.setString(7, productVO.getStatus());
            preparedStatement.setInt(8, productVO.getTimes());
            preparedStatement.setInt(9, productVO.getScore());
            preparedStatement.setString(10, productVO.getPro_no());
            preparedStatement.execute();
            connection.commit();
        }
        catch (SQLException e) {
            try {
                connection.rollback();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            try {
                cancelConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ProductVO getOneByPK(String pro_no) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ONE_BY_PK);
            preparedStatement.setString(1, pro_no);
            resultSet = preparedStatement.executeQuery();
            ProductVO productVO = new ProductVO();
            while (resultSet.next()) {
                productVO.setPro_no(resultSet.getString(1));
                productVO.setPro_name(resultSet.getString(2));
                productVO.setSeller_no(resultSet.getString(3));
                productVO.setPro_desc(resultSet.getString(4));
                productVO.setPrice(resultSet.getInt(5));
                productVO.setAmount(resultSet.getInt(6));
                productVO.setImg(resultSet.getBytes(7));
                productVO.setPro_date(resultSet.getTimestamp(8));
                productVO.setProtype_no(resultSet.getString(9));
                productVO.setStatus(resultSet.getString(10));
                productVO.setTimes(resultSet.getInt(11));
                productVO.setScore(resultSet.getInt(12));
            }
            return productVO;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                cancelConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public ProductVO getOneByPKNoImg(String pro_no) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ONE_BY_PK_NO_IMG);
            preparedStatement.setString(1, pro_no);
            resultSet = preparedStatement.executeQuery();
            ProductVO productVO = new ProductVO();
            while (resultSet.next()) {
                productVO.setPro_no(resultSet.getString(1));
                productVO.setPro_name(resultSet.getString(2));
                productVO.setSeller_no(resultSet.getString(3));
                productVO.setPro_desc(resultSet.getString(4));
                productVO.setPrice(resultSet.getInt(5));
                productVO.setAmount(resultSet.getInt(6));
                productVO.setPro_date(resultSet.getTimestamp(7));
                productVO.setProtype_no(resultSet.getString(8));
                productVO.setStatus(resultSet.getString(9));
                productVO.setTimes(resultSet.getInt(10));
                productVO.setScore(resultSet.getInt(11));
            }
            return productVO;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                cancelConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<ProductVO> getAllNoImg(String status) {
        List<ProductVO> list = new ArrayList<ProductVO>();
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL_BY_STATUS);
            preparedStatement.setString(1, status);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductVO productVO = new ProductVO();
                productVO.setPro_no(resultSet.getString(1));
                productVO.setPro_name(resultSet.getString(2));
                productVO.setSeller_no(resultSet.getString(3));
                productVO.setPro_desc(resultSet.getString(4));
                productVO.setPrice(resultSet.getInt(5));
                productVO.setAmount(resultSet.getInt(6));
                productVO.setPro_date(resultSet.getTimestamp(7));
                productVO.setProtype_no(resultSet.getString(8));
                productVO.setStatus(resultSet.getString(9));
                productVO.setTimes(resultSet.getInt(10));
                productVO.setScore(resultSet.getInt(11));
                list.add(productVO);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                cancelConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public int getAllCount(String status) {
        int allcount = 0;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ROWCOUNT_BY_STATUS);
            preparedStatement.setString(1, status);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            allcount = resultSet.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                cancelConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allcount;
    }

    @Override
    public int getAllCount(String protype_no, String status) {
        int allcount = 0;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ROWCOUNT_BY_PROTYPE);
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, protype_no);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            allcount = resultSet.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                cancelConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allcount;
    }

    @Override
    public List<ProductVO> getPage(int start, int itemsCount, String orderMethod, String status) {
        String GET_PAGE = "select PRO_NO, PRO_NAME, SELLER_NO, PRO_DESC ,PRICE, AMOUNT, PRO_DATE, PROTYPE_NO, STATUS, TIMES, SCORE " + "from (select rownum bRn, b.*from (select rownum aRn, a.* from PRODUCT a where STATUS = ? order by a." + orderMethod + ") b) where bRn between ? and ?";
        List<ProductVO> list = new ArrayList<ProductVO>();
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_PAGE);
            preparedStatement.setString(1, status);
            int end = start + itemsCount-1;
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, end);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductVO productVO = new ProductVO();
                productVO.setPro_no(resultSet.getString(1));
                productVO.setPro_name(resultSet.getString(2));
                productVO.setSeller_no(resultSet.getString(3));
                productVO.setPro_desc(resultSet.getString(4));
                productVO.setPrice(resultSet.getInt(5));
                productVO.setAmount(resultSet.getInt(6));
                productVO.setPro_date(resultSet.getTimestamp(7));
                productVO.setProtype_no(resultSet.getString(8));
                productVO.setStatus(resultSet.getString(9));
                productVO.setTimes(resultSet.getInt(10));
                productVO.setScore(resultSet.getInt(11));
                list.add(productVO);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                cancelConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<ProductVO> getPage(int start, int itemsCount, String protype_no, String orderMethod, String status) {
        String GET_PAGE = "select PRO_NO, PRO_NAME, SELLER_NO, PRO_DESC ,PRICE, AMOUNT, PRO_DATE, PROTYPE_NO, STATUS, TIMES, SCORE " + "from (select rownum bRn, b.*from (select rownum aRn, a.* from PRODUCT a where PROTYPE_NO = ? and STATUS = ? order by a." + orderMethod + ") b) where bRn between ? and ?";
        List<ProductVO> list = new ArrayList<ProductVO>();
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_PAGE);
            preparedStatement.setString(1, protype_no);
            preparedStatement.setString(2, status);
            int end = start + itemsCount-1;
            preparedStatement.setInt(3, start);
            preparedStatement.setInt(4, end);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductVO productVO = new ProductVO();
                productVO.setPro_no(resultSet.getString(1));
                productVO.setPro_name(resultSet.getString(2));
                productVO.setSeller_no(resultSet.getString(3));
                productVO.setPro_desc(resultSet.getString(4));
                productVO.setPrice(resultSet.getInt(5));
                productVO.setAmount(resultSet.getInt(6));
                productVO.setPro_date(resultSet.getTimestamp(7));
                productVO.setProtype_no(resultSet.getString(8));
                productVO.setStatus(resultSet.getString(9));
                productVO.setTimes(resultSet.getInt(10));
                productVO.setScore(resultSet.getInt(11));
                list.add(productVO);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                cancelConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public int getAllCountBySeller(String seller_no) {
        int allcount = 0;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ROWCOUNT_BY_SELLER);
            preparedStatement.setString(1, seller_no);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            allcount = resultSet.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                cancelConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allcount;
    }

    @Override
    public List<ProductVO> getPageBySeller(int start, int itemsCount, String seller_no) {
        List<ProductVO> list = new ArrayList<ProductVO>();
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_PAGE_BY_SELLER);
            preparedStatement.setString(1, seller_no);
            int end = start + itemsCount-1;
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, end);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductVO productVO = new ProductVO();
                productVO.setPro_no(resultSet.getString(1));
                productVO.setPro_name(resultSet.getString(2));
                productVO.setSeller_no(resultSet.getString(3));
                productVO.setPro_desc(resultSet.getString(4));
                productVO.setPrice(resultSet.getInt(5));
                productVO.setAmount(resultSet.getInt(6));
                productVO.setPro_date(resultSet.getTimestamp(7));
                productVO.setProtype_no(resultSet.getString(8));
                productVO.setStatus(resultSet.getString(9));
                productVO.setTimes(resultSet.getInt(10));
                productVO.setScore(resultSet.getInt(11));
                list.add(productVO);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                cancelConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
