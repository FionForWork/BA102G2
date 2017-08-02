package com.protracking_list.model;

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

import com.product.model.ProductVO;

public class Protracking_listDAO implements Protracking_listDAO_Interface {
    private static final String INSERT = "insert into PROTRACKING_LIST (PROTRA_NO, PRO_NO,MEM_NO)" + "values(PROTRA_NO_SEQ.NEXTVAL, ?,?)";
    private static final String DELETE_BY_NO = "delete from PROTRACKING_LIST where PRO_NO = ?";
    private static final String FIND_BY_PK = "select * from PROTRACKING_LIST where PROTRA_NO = ?";
    private static final String FIND_BY_MEM = "select * from PROTRACKING_LIST where MEM_NO = ?";
    private static final String GET_ALL_ORDER_BY_ASC = "select * from PROTRACKING_LIST order by PROTRA_NO asc";
    private static final String GET_ALL_ORDER_BY_DESC = "select * from PROTRACKING_LIST order by PROTRA_NO desc";
    private static final String GET_ALL_ROW_BY_MEM = "select count(rownum) from PROTRACKING_LIST where MEM_NO = ?";
    private static final String GET_SOME_ROW = "select * from (select rownum bRn, b.*from (select rownum aRn, a.* from PROTRACKING_LIST a where MEM_NO = ? order by PRO_NO asc) b) where bRn between ? and ?";

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

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
    public void add(Protracking_listVO protracking_listVO) {
        try {
            connection = JNDIinit();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, protracking_listVO.getPro_no());
            preparedStatement.setString(2, protracking_listVO.getMem_no());
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
    public void delete(String pro_no) {
        try {
            connection = JNDIinit();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_BY_NO);
            preparedStatement.setString(1, pro_no);
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
    }

    @Override
    public void update(Protracking_listVO protracking_listVO) {
    }

    @Override
    public Protracking_listVO getOneByPK(String protra_no) {
        try {
            connection = JNDIinit();
            preparedStatement = connection.prepareStatement(FIND_BY_PK);
            preparedStatement.setString(1, protra_no);
            resultSet = preparedStatement.executeQuery();
            Protracking_listVO protracking_listVO = new Protracking_listVO();
            while (resultSet.next()) {
                protracking_listVO.setProtra_no(resultSet.getString(1));
                protracking_listVO.setPro_no(resultSet.getString(2));
                protracking_listVO.setMem_no(resultSet.getString(3));
            }
            return protracking_listVO;
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
        return null;
    }

    @Override
    public List<Protracking_listVO> getAll() {
        try {
            connection = JNDIinit();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_ORDER_BY_ASC);
            List<Protracking_listVO> list = new ArrayList<>();
            while (resultSet.next()) {
                Protracking_listVO protracking_listVO = new Protracking_listVO();
                protracking_listVO.setPro_no(resultSet.getString(1));
                protracking_listVO.setPro_no(resultSet.getString(2));
                protracking_listVO.setMem_no(resultSet.getString(3));
                list.add(protracking_listVO);
            }
            return list;
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
        return null;
    }

    @Override
    public List<Protracking_listVO> getAllByMem(String mem_no) {
        try {
            connection = JNDIinit();
            preparedStatement = connection.prepareStatement(FIND_BY_MEM);
            preparedStatement.setString(1, mem_no);
            resultSet = preparedStatement.executeQuery();
            List<Protracking_listVO> list = new ArrayList<Protracking_listVO>();
            while (resultSet.next()) {
                Protracking_listVO protracking_listVO = new Protracking_listVO();
                protracking_listVO.setProtra_no(resultSet.getString(1));
                protracking_listVO.setPro_no(resultSet.getString(2));
                protracking_listVO.setMem_no(resultSet.getString(3));
                list.add(protracking_listVO);
            }
            return list;
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
        return null;
    }

    @Override
    public int getRowCount(String mem_no) {
        try {
            connection = JNDIinit();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ROW_BY_MEM);
            preparedStatement.setString(1, mem_no);
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
    public List<Protracking_listVO> getSome(String mem_no, int nowPage, int count) {
        try {
            connection = JNDIinit();
            int start = (nowPage - 1) * count + 1;
            int end = nowPage * count;
            preparedStatement = connection.prepareStatement(GET_SOME_ROW);
            preparedStatement.setString(1, mem_no);
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, end);
            List<Protracking_listVO> list = new ArrayList<Protracking_listVO>();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Protracking_listVO protracking_listVO = new Protracking_listVO();
                protracking_listVO.setProtra_no(resultSet.getString(3));
                protracking_listVO.setPro_no(resultSet.getString(4));
                protracking_listVO.setMem_no(resultSet.getString(5));
                list.add(protracking_listVO);
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
