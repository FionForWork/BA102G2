package com.order_detail.model;

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

import com.product.model.ProductVO;

public class Order_detailDAO_JDBC implements Order_detailDAO_Interface {
    private static final String INSERT               = "insert into ORDER_DETAIL (ORD_NO, PRO_NO, PRICE, QTY,ITEMTOT,SCORE,STATUS)" + "VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_BY_ORD        = "delete from ORDER_DETAIL where ORD_NO = ?";
    private static final String UPDATE               = "update ORDER_DETAIL set PRICE = ?, QTY = ?, ITEMTOT = ?, SCORE = ?, STATUS = ? where ORD_NO = ? and PRO_NO = ?";
    private static final String GET_ONE_BY_COMPOSITE = "select * from ORDER_DETAIL where ORD_NO = ? and PRO_NO = ?";
    private static final String GET_ALL_BY_ORD       = "select * from ORDER_DETAIL where ORD_NO = ?";
    private static final String GET_ALL_BY_PRO       = "select * from ORDER_DETAIL where PRO_NO = ?";

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
    public void insert(Order_detailVO order_detailVO) {
        // TODO Auto-generated method stub
    }

    @Override
    public void insert(Order_detailVO order_detailVO, Connection connection) {
        try {
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, order_detailVO.getOrd_no());
            preparedStatement.setString(2, order_detailVO.getPro_no());
            preparedStatement.setInt(3, order_detailVO.getPrice());
            preparedStatement.setInt(4, order_detailVO.getQty());
            preparedStatement.setInt(5, order_detailVO.getItemtot());
            preparedStatement.setInt(6, order_detailVO.getScore());
            preparedStatement.setString(7, order_detailVO.getStatus());
            preparedStatement.execute();
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

    public void delete(String ord_no, Connection connection) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_ORD);
            preparedStatement.setString(1, ord_no);
            preparedStatement.execute();
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
    public void delete(String ord_no) {
        // TODO Auto-generated method stub
    }

    @Override
    public void update(Order_detailVO order_detailVO) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, order_detailVO.getPrice());
            preparedStatement.setInt(2, order_detailVO.getQty());
            preparedStatement.setInt(3, order_detailVO.getItemtot());
            preparedStatement.setInt(4, order_detailVO.getScore());
            preparedStatement.setString(5, order_detailVO.getStatus());
            preparedStatement.setString(6, order_detailVO.getOrd_no());
            preparedStatement.setString(7, order_detailVO.getPro_no());
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
    public Order_detailVO getOneByComposite(String ord_no, String pro_no) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ONE_BY_COMPOSITE);
            preparedStatement.setString(1, ord_no);
            preparedStatement.setString(2, pro_no);
            resultSet = preparedStatement.executeQuery();
            Order_detailVO order_detailVO = new Order_detailVO();
            while (resultSet.next()) {
                order_detailVO.setOrd_no(resultSet.getString(1));
                order_detailVO.setPro_no(resultSet.getString(2));
                order_detailVO.setPrice(resultSet.getInt(3));
                order_detailVO.setQty(resultSet.getInt(4));
                order_detailVO.setItemtot(resultSet.getInt(5));
                order_detailVO.setScore(resultSet.getInt(6));
                order_detailVO.setStatus(resultSet.getString(7));
            }
            return order_detailVO;
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
    public List<Order_detailVO> getAllByOrd(String ord_no) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL_BY_ORD);
            preparedStatement.setString(1, ord_no);
            resultSet = preparedStatement.executeQuery();
            List<Order_detailVO> list = new ArrayList<Order_detailVO>();
            while (resultSet.next()) {
                Order_detailVO order_detailVO = new Order_detailVO();
                order_detailVO.setOrd_no(resultSet.getString(1));
                order_detailVO.setPro_no(resultSet.getString(2));
                order_detailVO.setPrice(resultSet.getInt(3));
                order_detailVO.setQty(resultSet.getInt(4));
                order_detailVO.setItemtot(resultSet.getInt(5));
                order_detailVO.setScore(resultSet.getInt(6));
                order_detailVO.setStatus(resultSet.getString(7));
                list.add(order_detailVO);
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

    @Override
    public List<Order_detailVO> getAllByPro(String pro_no) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL_BY_PRO);
            preparedStatement.setString(1, pro_no);
            resultSet = preparedStatement.executeQuery();
            List<Order_detailVO> list = new ArrayList<Order_detailVO>();
            while (resultSet.next()) {
                Order_detailVO order_detailVO = new Order_detailVO();
                order_detailVO.setOrd_no(resultSet.getString(1));
                order_detailVO.setPro_no(resultSet.getString(2));
                order_detailVO.setPrice(resultSet.getInt(3));
                order_detailVO.setQty(resultSet.getInt(4));
                order_detailVO.setItemtot(resultSet.getInt(5));
                order_detailVO.setScore(resultSet.getInt(6));
                order_detailVO.setStatus(resultSet.getString(7));
                list.add(order_detailVO);
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
