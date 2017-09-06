package com.placeview.model;

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

public class PlaceViewDAO_JDBC implements PlaceViewDAO_Interface {

    private static final String INSERT        = "insert into PLACEVIEW (VIEW_NO, PLA_NO, IMG)" + "VALUES(VIEW_NO_SEQ.NEXTVAL, ?, ?)";
    private static final String DELETE_BY_PK  = "delete from PLACEVIEW where VIEW_NO = ? ";
    private static final String DELETE_BY_FK  = "delete from PLACEVIEW where PLA_NO = ? ";
    private static final String UPDATE        = "update PLACEVIEW set IMG = ? where VIEW_NO = ?";
    private static final String GET_ONE_BY_PK    = "select * from PLACEVIEW where VIEW_NO = ? ";
    private static final String GET_ALL_BY_FK = "select VIEW_NO from PLACEVIEW where PLA_NO =?";

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
    public void insert(PlaceViewVO placeViewVO) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);
//            preparedStatement.setString(1, placeViewVO.getPla_no());
            preparedStatement.setBytes(2, placeViewVO.getImg());
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
    public void insert(PlaceViewVO placeViewVO, Connection connection) {
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);
//            preparedStatement.setString(1, placeViewVO.getPla_no());
            preparedStatement.setBytes(2, placeViewVO.getImg());
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
    public void delete(String view_no) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_BY_PK);
            preparedStatement.setString(1, view_no);
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
    public void deleteByFK(String pla_no,Connection connection) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_FK);
            preparedStatement.setString(1, pla_no);
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
            try {
                cancelConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    

    @Override
    public void deleteByFK(String pla_no) {
        // TODO Auto-generated method stub
    }

    @Override
    public void update(PlaceViewVO placeViewVO) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setBytes(1, placeViewVO.getImg());
            preparedStatement.setString(2, placeViewVO.getView_no());
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
    public PlaceViewVO getOneByPK(String view_no) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ONE_BY_PK);
            preparedStatement.setString(1, view_no);
            resultSet = preparedStatement.executeQuery();
            PlaceViewVO placeViewVO = new PlaceViewVO();
            while (resultSet.next()) {
                placeViewVO.setView_no(resultSet.getString(1));
//                placeViewVO.setPla_no(resultSet.getString(2));
                placeViewVO.setImg(resultSet.getBytes(3));
            }
            return placeViewVO;
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
    public List<String> getAllByFK(String pla_no) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL_BY_FK);
            preparedStatement.setString(1, pla_no);
            resultSet=preparedStatement.executeQuery();
            List<String> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
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
    public String getOneByFK(String pla_no) {
        // TODO Auto-generated method stub
        return null;
    }
}
