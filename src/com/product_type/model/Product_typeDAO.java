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

public class Product_typeDAO implements Product_typeDAO_Interface{
    private static final String INSERT = "insert into PRODUCT_TYPE (PROTYPE_NO, TYPE_NAME)"
            + "VALUES(PROTYPE_NO_SEQ.NEXTVAL, ?)";
    private static final String DELETE_STMT_BY_NO = "DELETE FROM MEMBER WHERE MEM_NO = ?";
    private static final String UPDATE = "UPDATE MEMBER SET PWD = ? ,NAME=?,PHONE=?ACCOUNT=? PICTURE=? WHERE MEM_NO=?";
    private static final String FIND_BY_PK = "SELECT * FROM MEMBER WHERE MEM_NO = ?";
    private static final String GET_ALL_ORDER_BY_ASC = "SELECT * FROM PRODUCT_TYPE ORDER BY PROTYPE_NO ASC";
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
    public void add(Product_typeVO product_typeVO) {
        try {
            connection=JDBCinit();
            connection.setAutoCommit(false);
            preparedStatement=connection.prepareStatement(INSERT);
            preparedStatement.setString(1, product_typeVO.getType_name());
            preparedStatement.execute();
            connection.commit();
        } catch (Exception e) {
//            try {
//                connection.rollback();
//            } catch (SQLException e1) {
                e.printStackTrace();
//            }
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
    public void delete(String protype_no) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(Product_typeVO product_typeVO) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Product_typeVO getOneByPK(String protype_no) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Product_typeVO> getAll() {
        try {
            connection=JDBCinit();
            Statement statement=connection.createStatement();
            resultSet=statement.executeQuery(GET_ALL_ORDER_BY_ASC);
            List<Product_typeVO>list=new ArrayList<>();
            while(resultSet.next()){
                Product_typeVO product_typeVO=new Product_typeVO();
                product_typeVO.setProtype_no(resultSet.getString(1));
                product_typeVO.setType_name(resultSet.getString(2));
                list.add(product_typeVO);
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
