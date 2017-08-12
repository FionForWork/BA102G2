package com.place.model;

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

import com.placeview.model.PlaceViewDAO;
import com.placeview.model.PlaceViewService;
import com.placeview.model.PlaceViewVO;

public class PlaceDAO_JDBC implements PlaceDAO_Interface {
    private static final String INSERT        = "insert into PLACE(PLA_NO, NAME, LNG,LAT,ADDR,PLA_DESC)" + "values(PLA_NO_SEQ.NEXTVAL, ?, ?, ? , ?,?)";
    private static final String DELETE        = "delete from PLACE where PLA_NO = ?";
    private static final String UPDATE        = "update PLACE set NAME = ? ,LNG = ?,LAT = ?,ADDR = ? ,PLA_DESC = ? where PLA_NO = ?";
    private static final String FIND_BY_PK    = "select * from PLACE where PLA_NO = ?";
    private static final String GET_ALL_COUNT = "select count(rownum) from PLACE ";
    private static final String GET_PAGE      = "select * from (select rownum bRn, b.*from (select rownum aRn, a.* from PLACE a order by a.PLA_NO) b) where bRn between ? and ?";
    private static final String GET_RANGE     = "select * from place where lat between ? and ? and lng between ? and ?";

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
    public void insert(PlaceVO placeVO, List<PlaceViewVO> viewList) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            String PKName[] = { "PLA_NO" };
            preparedStatement = connection.prepareStatement(INSERT, PKName);
            preparedStatement.setString(1, placeVO.getName());
            preparedStatement.setString(2, placeVO.getLng());
            preparedStatement.setString(3, placeVO.getLat());
            preparedStatement.setString(4, placeVO.getAddr());
            preparedStatement.setString(5, placeVO.getPla_desc());

            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            String pla_no = resultSet.getString(1);
            PlaceViewService placeViewService = new PlaceViewService();
            for (PlaceViewVO view : viewList) {
                view.setPla_no(pla_no);
                placeViewService.insert(view, connection);
            }
            connection.commit();
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
            try {
                cancelConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insert(PlaceVO placeVO) {
    }

    @Override
    public void delete(String pla_no) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setString(1, pla_no);
            PlaceViewDAO placeViewDAO = new PlaceViewDAO();
            preparedStatement.execute();
            placeViewDAO.deleteByFK(pla_no, connection);
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
    public void update(PlaceVO placeVO) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, placeVO.getName());
            preparedStatement.setString(2, placeVO.getLng());
            preparedStatement.setString(3, placeVO.getLat());
            preparedStatement.setString(4, placeVO.getAddr());
            preparedStatement.setString(5, placeVO.getPla_desc());
            preparedStatement.setString(6, placeVO.getPla_no());
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
    public PlaceVO getOneByPK(String pla_no) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_PK);
            preparedStatement.setString(1, pla_no);
            resultSet = preparedStatement.executeQuery();
            PlaceVO placeVO = new PlaceVO();
            while (resultSet.next()) {
                placeVO.setPla_no(resultSet.getString(1));
                placeVO.setName(resultSet.getString(2));
                placeVO.setLng(resultSet.getString(3));
                placeVO.setLat(resultSet.getString(4));
                placeVO.setAddr(resultSet.getString(5));
                placeVO.setPla_desc(resultSet.getString(6));

            }
            return placeVO;
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
    public int getAllCount() {
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_COUNT);
            resultSet.next();
            return resultSet.getInt(1);
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
    public List<PlaceVO> getPage(int start, int itemsCount) {
        try {
            connection = dataSource.getConnection();
            int end = start + itemsCount;
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PAGE);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, end);
            resultSet = preparedStatement.executeQuery();
            List<PlaceVO> list = new ArrayList<>();
            while (resultSet.next()) {
                PlaceVO placeVO = new PlaceVO();
                placeVO.setPla_no(resultSet.getString(3));
                placeVO.setName(resultSet.getString(4));
                placeVO.setLng(resultSet.getString(5));
                placeVO.setLat(resultSet.getString(6));
                placeVO.setAddr(resultSet.getString(7));
                placeVO.setPla_desc(resultSet.getString(8));
                list.add(placeVO);
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
    public List<PlaceVO> getRange(String south, String west, String north, String east) {
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_RANGE);
            preparedStatement.setString(1, south);
            preparedStatement.setString(2, north);
            preparedStatement.setString(3, west);
            preparedStatement.setString(4, east);
            resultSet = preparedStatement.executeQuery();
            List<PlaceVO> list = new ArrayList<>();
            while (resultSet.next()) {
                PlaceVO placeVO = new PlaceVO();
                placeVO.setPla_no(resultSet.getString(1));
                placeVO.setName(resultSet.getString(2));
                placeVO.setLng(resultSet.getString(3));
                placeVO.setLat(resultSet.getString(4));
                placeVO.setAddr(resultSet.getString(5));
                list.add(placeVO);
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
