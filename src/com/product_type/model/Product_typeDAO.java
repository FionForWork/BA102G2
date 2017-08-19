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
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product.model.ProductDAO;

public class Product_typeDAO implements Product_typeDAO_Interface {
    private static final String INSERT                = "insert into PRODUCT_TYPE (PROTYPE_NO, TYPE_NAME)" + "values(PROTYPE_NO_SEQ.NEXTVAL, ?)";
    private static final String DELETE_BY_PK          = "delete from PRODUCT_TYPE where PROTYPE_NO = ?";
    private static final String UPDATE                = "update PRODUCT_TYPE set TYPE_NAME = ? where PROTYPE_NO = ?";
    private static final String GET_ONE_BY_PK            = "select * from PRODUCT_TYPE where PROTYPE_NO = ?";
    private static final String GET_ALL  = "select * from PRODUCT_TYPE order by PROTYPE_NO asc";

    private Connection        connection;
    private PreparedStatement preparedStatement;
    private ResultSet         resultSet;
    private static DataSource dataSource = null;
    static {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G2DB");
        } catch (NamingException e) {
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
    public void insert(Product_typeVO product_typeVO) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, product_typeVO.getType_name());
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
    public void delete(String protype_no) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_BY_PK);
            preparedStatement.setString(1, protype_no);
            preparedStatement.execute();
            ProductDAO productDAO=new ProductDAO();
            productDAO.deleteByFK(protype_no,connection);
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
    public void update(Product_typeVO product_typeVO) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, product_typeVO.getType_name());
            preparedStatement.setString(2, product_typeVO.getProtype_no());
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
    public Product_typeVO getOneByPK(String protype_no) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ONE_BY_PK);
            preparedStatement.setString(1, protype_no);
            resultSet = preparedStatement.executeQuery();
            Product_typeVO product_typeVO = new Product_typeVO();
            while (resultSet.next()) {
                product_typeVO.setProtype_no(resultSet.getString(1));
                product_typeVO.setType_name(resultSet.getString(2));
            }
            return product_typeVO;
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
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL);
            List<Product_typeVO> list = new ArrayList<>();
            while (resultSet.next()) {
                Product_typeVO product_typeVO = new Product_typeVO();
                product_typeVO.setProtype_no(resultSet.getString(1));
                product_typeVO.setType_name(resultSet.getString(2));
                list.add(product_typeVO);
            }
            return list;
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
