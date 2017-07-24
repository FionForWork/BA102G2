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
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Order_detailDAO implements Order_detailDAO_Interface{
    private static final String INSERT = "insert into ORDER_DETAIL (ORD_NO, PRO_NO, PRICE, QTY,ITEMTOT,SCORE)"
            + "VALUES(?, ?, ?, ?, ?, ?)";
    private static final String DELETE_STMT_BY_NO = "DELETE FROM MEMBER WHERE MEM_NO = ?";
    private static final String UPDATE = "UPDATE MEMBER SET PWD = ? ,NAME=?,PHONE=?ACCOUNT=? PICTURE=? WHERE MEM_NO=?";
    private static final String FIND_BY_PK = "SELECT * FROM MEMBER WHERE MEM_NO = ?";
    private static final String GET_ALL_ORDER_BY_ASC = "SELECT * FROM MEMBER ORDER BY PICTURENO ASC";
    private static final String GET_ALL_ORDER_BY_DESC = "SELECT * FROM MEMBER ORDER BY PICTURENO DESC";

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private Connection JNDIinit() throws NamingException, SQLException {
        Context context = new javax.naming.InitialContext();
        DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ProjectDB");
        if (dataSource != null) {
            System.out.println("DataSource is not null");
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
    public void add(Order_detailVO order_detailVO) {
        try {
            connection=JDBCinit();
            connection.setAutoCommit(false);
            preparedStatement=connection.prepareStatement(INSERT);
            preparedStatement.setString(1, order_detailVO.getOrd_no());
            preparedStatement.setString(2, order_detailVO.getPro_no());
            preparedStatement.setInt(3, order_detailVO.getPrice());
            preparedStatement.setInt(4, order_detailVO.getQty());
            preparedStatement.setInt(5, order_detailVO.getItemtot());
            preparedStatement.setInt(6, order_detailVO.getScore());
            preparedStatement.execute();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try {
                cancelConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void delete(String ord_no, String pro_no) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(Order_detailVO order_detailVO) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Order_detailVO getOneByPK(String ord_no) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Order_detailVO> getAll() {
        try {
            connection=JDBCinit();
            Statement statement=connection.createStatement();
            resultSet=statement.executeQuery(GET_ALL_ORDER_BY_ASC);
            List<Order_detailVO>list=new ArrayList<>();
            while(resultSet.next()){
                Order_detailVO order_detailVO=new Order_detailVO();
                order_detailVO.setOrd_no(resultSet.getString(1));
                order_detailVO.setPro_no(resultSet.getString(2));
                order_detailVO.setPrice(resultSet.getInt(3));
                order_detailVO.setQty(resultSet.getInt(4));
                order_detailVO.setItemtot(resultSet.getInt(5));
                order_detailVO.setScore(resultSet.getInt(6));
                list.add(order_detailVO);
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
