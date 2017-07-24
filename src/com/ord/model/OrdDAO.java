package com.ord.model;

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

public class OrdDAO implements OrdDAO_Interface {
    private static final String INSERT = "insert into ORD (ORD_NO, SELLER_NO, CUST_NO, ADDRESS,ORD_DATE,TOTAL,SCORE,STATUS)"
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_STMT_BY_NO = "DELETE FROM MEMBER WHERE MEM_NO = ?";
    private static final String UPDATE = "update ORD SET ADDRESS = ?, TOTAL = ?, SCORE = ?, STATUS = ? where ORD_NO = ?";
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
    public void add(OrdVO ordVO) {
        try {
            connection = JDBCinit();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, ordVO.getOrd_no());
            preparedStatement.setString(2, ordVO.getSeller_no());
            preparedStatement.setString(3, ordVO.getCust_no());
            preparedStatement.setString(4, ordVO.getAddress());
            preparedStatement.setTimestamp(5, ordVO.getOrd_date());
            preparedStatement.setInt(6, ordVO.getTotal());
            preparedStatement.setInt(7, ordVO.getScore());
            preparedStatement.setString(8, ordVO.getStatus());
            preparedStatement.execute();
            connection.commit();
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
    }

    @Override
    public void delete(String ord_no) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(OrdVO ordVO) {
        try {
            connection = JDBCinit();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, ordVO.getAddress());
            preparedStatement.setInt(2, ordVO.getTotal());
            preparedStatement.setInt(3, ordVO.getScore());
            preparedStatement.setString(4, ordVO.getStatus());
            preparedStatement.setString(5, ordVO.getOrd_no());
            preparedStatement.execute();
            connection.commit();
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
    }

    @Override
    public OrdVO getOneByPK(String ord_no) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OrdVO> getAll() {
        try {
            connection = JDBCinit();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_ORDER_BY_ASC);
            List<OrdVO> list = new ArrayList<>();
            while (resultSet.next()) {
                OrdVO ordVO = new OrdVO();
                ordVO.setOrd_no(resultSet.getString(1));
                ordVO.setSeller_no(resultSet.getString(2));
                ordVO.setCust_no(resultSet.getString(3));
                ordVO.setAddress(resultSet.getString(4));
                ordVO.setOrd_date(resultSet.getTimestamp(5));
                ordVO.setTotal(resultSet.getInt(6));
                ordVO.setScore(resultSet.getInt(7));
                ordVO.setStatus(resultSet.getString(8));
                list.add(ordVO);
            }
            return list;
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
        return null;
    }
}
