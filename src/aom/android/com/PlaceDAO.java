package aom.android.com;

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

public class PlaceDAO implements PlaceDAO_Interface {
    private static final String INSERT                = "insert into PLACE(PLA_NO, NAME, LNG,LAT,ADDR,PLA_DESC)" + "values(PLA_NO_SEQ.NEXTVAL, ?, ?, ? , ?, ?)";
    private static final String DELETE_BY_NO          = "delete from PLACE where PLA_NO = ?";
    private static final String UPDATE                = "update PLACE set NAME = ? ,LNG = ?,LAT = ?,ADDR = ?,,PLA_DESC = ? where PLA_NO = ?";
    private static final String FIND_BY_PK            = "select * from PLACE where PLA_NO = ?";
    private static final String GET_ALL_ORDER_BY_ASC  = "select * from PLACE order by PLA_NO asc";
    private static final String GET_ALL_ORDER_BY_DESC = "select * from PLACE order by PLA_NO desc";
    private static final String GET_SOME_ROW          = "select * from (select rownum bRn, b.*from (select rownum aRn, a.* from PLACE a order by a.PLA_NO) b) where bRn between ? and ?";
    private static final String GET_SOME_ROW_BY_FOUR  = "select * from place where lat between ? and ? and lng between ? and ?";

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
    public void add(PlaceVO placeVO) {
        try {
            connection = JNDIinit();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, placeVO.getName());
            preparedStatement.setString(2, placeVO.getLng());
            preparedStatement.setString(3, placeVO.getLat());
            preparedStatement.setString(4, placeVO.getAddr());
            preparedStatement.setString(6, placeVO.getPla_desc());
            preparedStatement.execute();
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
    public void delete(String pla_no) {
        try {
            connection = JNDIinit();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_BY_NO);
            preparedStatement.setString(1, pla_no);
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
    public void update(PlaceVO placeVO) {
        try {
            connection = JNDIinit();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, placeVO.getName());
            preparedStatement.setString(2, placeVO.getLng());
            preparedStatement.setString(3, placeVO.getLat());
            preparedStatement.setString(4, placeVO.getAddr());
            preparedStatement.setString(5, placeVO.getPla_no());
            preparedStatement.setString(6, placeVO.getPla_desc());
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
    public PlaceVO getOneByPK(String pla_no) {
        try {
            connection = JNDIinit();
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
    public List<PlaceVO> getAll() {
        try {
            connection = JNDIinit();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_ORDER_BY_ASC);
            List<PlaceVO> list = new ArrayList<>();
            while (resultSet.next()) {
                PlaceVO placeVO = new PlaceVO();
                placeVO.setPla_no(resultSet.getString(1));
                placeVO.setName(resultSet.getString(2));
                placeVO.setLng(resultSet.getString(3));
                placeVO.setLat(resultSet.getString(4));
                placeVO.setAddr(resultSet.getString(5));
                placeVO.setPla_desc(resultSet.getString(6));
                list.add(placeVO);
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
    public List<PlaceVO> getSome(int page, int count) {
        try {
            connection = JNDIinit();
            int start = (page - 1) * count + 1;
            int end = page * count;
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SOME_ROW);
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
    public List<PlaceVO> getSome(String south, String west, String north, String east) {
        try {
            connection = JNDIinit();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SOME_ROW_BY_FOUR);
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
                placeVO.setPla_desc(resultSet.getString(6));
                list.add(placeVO);
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
