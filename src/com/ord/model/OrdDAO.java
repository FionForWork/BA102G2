package com.ord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product.model.ProductVO;

public class OrdDAO implements OrdDAO_Interface {
    private static final String INSERT                               = "insert into ORD (ORD_NO, SELLER_NO, CUST_NO, ADDRESS,ORD_DATE,TOTAL,SCORE,STATUS)" + "values(ORD_NO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_BY_ORDNO                      = "delete from ORD where ORD_NO = ?";
    private static final String UPDATE                               = "update ORD set ADDRESS = ? ,TOTAL = ? ,SCORE = ?,STATUS = ? where ORD_NO = ?";
    private static final String GET_BY_ORDNO                         = "select * from ORD where ORD_NO = ?";
    private static final String GET_BY_CUSTNO_AND_SELLERNO           = "select * from ORD where CUST_NO = ? and SELLER_NO = ?";
    private static final String GET_ALL_ORDER_BY_ORDNO_ASC           = "select * from ORD order by ORD_NO asc";
    private static final String GET_ALL_BY_CUST_ORDER_BY_DATE_DESC   = "select * from ORD where CUST_NO = ? and STATUS = ? order by ORD_DATE desc";
    private static final String GET_ALL_BY_SELLER_ORDER_BY_DATE_DESC = "select * from ORD where SELLER_NO = ? and STATUS = ? order by ORD_DATE desc";

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
            return null;
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
    public void add(OrdVO ordVO) {
        try {
            connection = JNDIinit();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, ordVO.getSeller_no());
            preparedStatement.setString(2, ordVO.getCust_no());
            preparedStatement.setString(3, ordVO.getAddress());
            preparedStatement.setTimestamp(4, ordVO.getOrd_date());
            preparedStatement.setInt(5, ordVO.getTotal());
            preparedStatement.setInt(6, ordVO.getScore());
            preparedStatement.setString(7, ordVO.getStatus());
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
    public void delete(String ord_no) {
        try {
            connection = JNDIinit();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_BY_ORDNO);
            preparedStatement.setString(1, ord_no);
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
    public void update(OrdVO ordVO) {
        try {
            connection = JNDIinit();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, ordVO.getAddress());
            preparedStatement.setInt(2, ordVO.getTotal());
            preparedStatement.setInt(3, ordVO.getScore());
            preparedStatement.setString(4, ordVO.getStatus());
            preparedStatement.setString(5, ordVO.getOrd_no());
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
    public OrdVO getOneByPK(String ord_no) {
        try {
            connection = JNDIinit();
            preparedStatement = connection.prepareStatement(GET_BY_ORDNO);
            preparedStatement.setString(1, ord_no);
            resultSet = preparedStatement.executeQuery();
            OrdVO ordVO = new OrdVO();
            while (resultSet.next()) {
                ordVO.setOrd_no(resultSet.getString(1));
                ordVO.setSeller_no(resultSet.getString(2));
                ordVO.setCust_no(resultSet.getString(3));
                ordVO.setAddress(resultSet.getString(4));
                ordVO.setOrd_date(resultSet.getTimestamp(5));
                ordVO.setTotal(resultSet.getInt(6));
                ordVO.setScore(resultSet.getInt(7));
                ordVO.setStatus(resultSet.getString(8));
            }
            return ordVO;
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
    public List<OrdVO> getAll() {
        try {
            connection = JNDIinit();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_ORDER_BY_ORDNO_ASC);
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
    public OrdVO getOne(String cust_no, String seller_no) {
        try {
            connection = JNDIinit();
            preparedStatement = connection.prepareStatement(GET_BY_CUSTNO_AND_SELLERNO, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, cust_no);
            preparedStatement.setString(2, seller_no);
            resultSet = preparedStatement.executeQuery();
            OrdVO ordVO = new OrdVO();
            resultSet.afterLast();
            resultSet.previous();
            ordVO.setOrd_no(resultSet.getString(1));
            ordVO.setSeller_no(resultSet.getString(2));
            ordVO.setCust_no(resultSet.getString(3));
            ordVO.setAddress(resultSet.getString(4));
            ordVO.setOrd_date(resultSet.getTimestamp(5));
            ordVO.setTotal(resultSet.getInt(6));
            ordVO.setScore(resultSet.getInt(7));
            ordVO.setStatus(resultSet.getString(8));
            return ordVO;
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
    public List<OrdVO> getAllByCust(String cust_no, String status) {
        try {
            connection = JNDIinit();
            preparedStatement = connection.prepareStatement(GET_ALL_BY_CUST_ORDER_BY_DATE_DESC);
            preparedStatement.setString(1, cust_no);
            preparedStatement.setString(2, status);
            resultSet = preparedStatement.executeQuery();
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
    public List<OrdVO> getAllBySeller(String seller_no, String status) {
        try {
            connection = JNDIinit();
            preparedStatement = connection.prepareStatement(GET_ALL_BY_SELLER_ORDER_BY_DATE_DESC);
            preparedStatement.setString(1, seller_no);
            preparedStatement.setString(2, status);
            resultSet = preparedStatement.executeQuery();
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
    public List<OrdVO> getAllByRoleAndOrder(String role, String role_no, String status, String orderType) {
        String GET_ALL_BY_ROLE_ORDER_BY = null;
        if ("0".equals(role)) {
            GET_ALL_BY_ROLE_ORDER_BY = "select * from ORD where CUST_NO = ? and STATUS = ? order by " + orderType;
        }
        else {
            GET_ALL_BY_ROLE_ORDER_BY = "select * from ORD where SELLER_NO = ? and STATUS = ? order by " + orderType;
        }
        try {
            connection = JNDIinit();
            preparedStatement = connection.prepareStatement(GET_ALL_BY_ROLE_ORDER_BY);
            preparedStatement.setString(1, role_no);
            preparedStatement.setString(2, status);
            resultSet = preparedStatement.executeQuery();
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
    public int getAllOrderCount(String role, String role_no, String status) {
        String GET_ALL_ROW_COUNT_BY_ROLE;
        if("0".equals(role)){
            GET_ALL_ROW_COUNT_BY_ROLE="select count(rownum) from ORD where CUST_NO = ? and STATUS = ?";
            
        }
        else{
            GET_ALL_ROW_COUNT_BY_ROLE="select count(rownum) from ORD where CUST_NO = ? and STATUS = ?";
        }
        try {
            connection = JNDIinit();
            preparedStatement = connection.prepareStatement(GET_ALL_ROW_COUNT_BY_ROLE);
            preparedStatement.setString(1, role_no);
            preparedStatement.setString(2, status);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
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
        return 0;
    }

    @Override
    public List<OrdVO> getAllOrderByRole(String role, String role_no, String status) {
        String GET_ALL_ROW_BY_ROLE;
        if("0".equals(role)){
            GET_ALL_ROW_BY_ROLE="select * from ORD where CUST_NO = ? and STATUS = ?";
            
        }
        else{
            GET_ALL_ROW_BY_ROLE="select * from ORD where SELLER_NO = ? and STATUS = ?";
        }
        try {
            connection = JNDIinit();
            preparedStatement = connection.prepareStatement(GET_ALL_ROW_BY_ROLE);
            preparedStatement.setString(1, role_no);
            preparedStatement.setString(2, status);
            resultSet = preparedStatement.executeQuery();
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
