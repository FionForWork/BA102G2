package com.product_type.model;

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

public class Product_typeDAO implements Product_typeDAO_Interface {
    private static final String INSERT                = "insert into PRODUCT_TYPE (PROTYPE_NO, TYPE_NAME)" + "values(PROTYPE_NO_SEQ.NEXTVAL, ?)";
    private static final String DELETE_BY_NO          = "delete from PRODUCT_TYPE where PROTYPE_NO = ?";
    private static final String UPDATE                = "update PRODUCT_TYPE set TYPE_NAME = ? where PROTYPE_NO = ?";
    private static final String FIND_BY_PK            = "select * from PRODUCT_TYPE where PROTYPE_NO = ?";
    private static final String GET_ALL_ORDER_BY_ASC  = "select * from PRODUCT_TYPE order by PROTYPE_NO asc";
    private static final String GET_ALL_ORDER_BY_DESC = "select * from PRODUCT_TYPE order by PROTYPE_NO desc";

    private Connection        connection;
    private PreparedStatement preparedStatement;
    private ResultSet         resultSet;

    private Connection JNDIinit() throws NamingException, SQLException {
        Context context = new javax.naming.InitialContext();
        DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/BA102G2DB");
        if (dataSource != null) {
            return dataSource.getConnection();
        }
        else {
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
    public void add(Product_typeVO product_typeVO) {
        try {
            connection = JNDIinit();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, product_typeVO.getType_name());
            preparedStatement.execute();
            connection.commit();
        }
        catch (NamingException e) {
            e.printStackTrace();
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
    public void delete(String protype_no) {
        try {
            connection = JNDIinit();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_BY_NO);
            preparedStatement.setString(1, protype_no);
            preparedStatement.execute();
            connection.commit();
        }
        catch (NamingException e) {
            e.printStackTrace();
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
    public void update(Product_typeVO product_typeVO) {
        try {
            connection = JNDIinit();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, product_typeVO.getType_name());
            preparedStatement.setString(2, product_typeVO.getProtype_no());
            preparedStatement.execute();
            connection.commit();
        }
        catch (NamingException e) {
            e.printStackTrace();
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
    public Product_typeVO getOneByPK(String protype_no) {
        try {
            connection = JNDIinit();
            preparedStatement = connection.prepareStatement(FIND_BY_PK);
            preparedStatement.setString(1, protype_no);
            resultSet = preparedStatement.executeQuery();
            Product_typeVO product_typeVO = new Product_typeVO();
            while (resultSet.next()) {
                product_typeVO.setProtype_no(resultSet.getString(1));
                product_typeVO.setType_name(resultSet.getString(2));
            }
            return product_typeVO;
        }
        catch (NamingException e) {
            e.printStackTrace();
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
    public List<Product_typeVO> getAll() {
        try {
            connection = JNDIinit();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_ORDER_BY_ASC);
            List<Product_typeVO> list = new ArrayList<>();
            while (resultSet.next()) {
                Product_typeVO product_typeVO = new Product_typeVO();
                product_typeVO.setProtype_no(resultSet.getString(1));
                product_typeVO.setType_name(resultSet.getString(2));
                list.add(product_typeVO);
            }
            return list;
        }
        catch (NamingException e) {
            e.printStackTrace();
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
}
