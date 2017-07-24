package com.product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDAO implements ProductDAO_Interface {
    private static final String INSERT = "insert into PRODUCT (PRO_NO, PRO_NAME, SELLER_NO,PRO_DESC,PRICE,AMOUNT,IMG,PRO_DATE,PROTYPE_NO,STATUS,TIMES,SCORE)"
            + "values('4'||lpad(PRO_NO_SEQ.NEXTVAL,3,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_BY_NO = "delete from PRODUCT where PRO_NO = ?";
    private static final String UPDATE = "update PRODUCT set PRO_NAME = ? ,PRO_DESC = ?, PRICE= ? ,AMOUNT = ? ,IMG = ?,PROTYPE_NO = ? ,STATUS = ? ,TIMES = ? , SCORE = ? where PRO_NO = ?";
    private static final String FIND_BY_PK = "select * from PRODUCT where PRO_NO = ?";
    private static final String FIND_BY_SELLER = "select * from PRODUCT where SELLER_NO = ?";
    private static final String GET_ALL_ORDER_BY_ASC = "select * from PRODUCT order by PRO_NO asc";
    private static final String GET_ALL_ORDER_BY_DESC = "select * from PRODUCT order by PRO_NO asc";
    private static final String GET_ALL_ROW = "select count(rownum) from PRODUCT";
    private static final String GET_SOME_ROW = "select * from (select rownum bRn, b.*from (select rownum aRn, a.* from PRODUCT a order by a.pro_no) b) where bRn between ? and ?";
    
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private Connection JBDIinit() throws NamingException, SQLException {
        Context context = new javax.naming.InitialContext();
        DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ProjectDB");
        if (dataSource != null) {
            return dataSource.getConnection();
        } else {
            System.out.println("DataSource is null");
            return null;
        }
    }

    private Connection JDBCinit() throws ClassNotFoundException, SQLException {
        String DRIVER = "oracle.jdbc.driver.OracleDriver";
        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        String USER = "ProjectDB";
        String PASSWORD = "eric1101105351";
        Class.forName(DRIVER);
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        return con;
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
    public void add(ProductVO productVO) {
        try {
            connection = JDBCinit();
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
        } catch (Exception e) {
        }
    }

    @Override
    public void delete(String pro_no) {
        try {
            connection = JDBCinit();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_BY_NO);
            preparedStatement.setString(1, pro_no);
            preparedStatement.execute();
            connection.commit();
        } catch (Exception e) {
        }
    }

    @Override
    public void update(ProductVO productVO) {
        try {
            connection = JDBCinit();
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
        }  catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                cancelConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ProductVO getOneByPK(String pro_no) {
        try {
            connection = JDBCinit();
            preparedStatement = connection.prepareStatement(FIND_BY_PK);
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
        }  catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                cancelConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<ProductVO> getAll() {
        try {
            connection = JDBCinit();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_ORDER_BY_ASC);
            List<ProductVO> list = new ArrayList<>();
            while (resultSet.next()) {
                ProductVO productVO = new ProductVO();
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
                list.add(productVO);
            }
            return list;
        }  catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                cancelConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int getAllCount() {
        try {
            connection = JDBCinit();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_ROW);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                cancelConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public List<ProductVO> getSome(int index, int count) {
        try {
            connection = JDBCinit();
            int start = (index - 1) * count + 1;
            int end = (index) * count;
            preparedStatement = connection.prepareStatement(GET_SOME_ROW);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, end);
            List<ProductVO> list = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ProductVO productVO = new ProductVO();
                productVO.setPro_no(resultSet.getString(3));
                productVO.setPro_name(resultSet.getString(4));
                productVO.setSeller_no(resultSet.getString(5));
                productVO.setPro_desc(resultSet.getString(6));
                productVO.setPrice(resultSet.getInt(7));
                productVO.setAmount(resultSet.getInt(8));
                productVO.setImg(resultSet.getBytes(9));
                productVO.setPro_date(resultSet.getTimestamp(8));
                productVO.setProtype_no(resultSet.getString(11));
                productVO.setStatus(resultSet.getString(12));
                productVO.setTimes(resultSet.getInt(13));
                productVO.setScore(resultSet.getInt(14));
                list.add(productVO);
            }
            return list;
        }  catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                cancelConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<ProductVO> getAllBySeller(String seller_no) {
        try {
            connection = JDBCinit();
            preparedStatement = connection.prepareStatement(FIND_BY_SELLER);
            preparedStatement.setString(1, seller_no);
            resultSet = preparedStatement.executeQuery();
            List<ProductVO> list=new ArrayList<ProductVO>();
            while (resultSet.next()) {
                ProductVO productVO = new ProductVO();
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
                list.add(productVO);
            }
            return list;
        }  catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                cancelConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
