package com.protra.model;

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

public class ProTraDAO implements ProTraDAO_Interface {
    private static final String INSERT                = "insert into PROTRA (PROTRA_NO, PRO_NO,MEM_NO)" + "values(PROTRA_SQ.NEXTVAL, ?,?)";
    private static final String DELETE_BY_COMPOSITE   = "delete from PROTRA where PRO_NO = ? and MEM_NO = ?";
    private static final String DELETE_BY_PRONO       = "delete from PROTRA where PRO_NO = ?";
    private static final String GET_ONE_BY_PK         = "select * from PROTRA where PROTRA_NO = ?";
    private static final String GET_ONE_BY_MEMNO      = "select * from PROTRA where MEM_NO = ?";
    private static final String GET_ALL               = "select * from PROTRA order by PROTRA_NO asc";
    private static final String GET_ROWCOUNT_BY_MEMNO = "select count(rownum) from PROTRA where MEM_NO = ?";
    private static final String GET_PAGE              = "select * from (select rownum bRn, b.*from (select rownum aRn, a.* from PROTRA a where MEM_NO = ? order by PRO_NO asc) b) where bRn between ? and ?";

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
    public void insert(ProtraVO protraVO) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, protraVO.getPro_no());
            preparedStatement.setString(2, protraVO.getMem_no());
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
    public void deleteByComposite(String pro_no, String mem_no) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_BY_COMPOSITE);
            preparedStatement.setString(1, pro_no);
            preparedStatement.setString(2, mem_no);
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
    }

    @Override
    public void deleteByFK(String pro_no) {
        // TODO Auto-generated method stub
    }

    @Override
    public void deleteByFK(String pro_no, Connection connection) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_PRONO);
            preparedStatement.setString(1, pro_no);
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
    }

    @Override
    public void update(ProtraVO protracking_listVO) {
    }

    @Override
    public ProtraVO getOneByPK(String protra_no) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ONE_BY_PK);
            preparedStatement.setString(1, protra_no);
            resultSet = preparedStatement.executeQuery();
            ProtraVO protracking_listVO = new ProtraVO();
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
    public List<ProtraVO> getAllByMem(String mem_no) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ONE_BY_MEMNO);
            preparedStatement.setString(1, mem_no);
            resultSet = preparedStatement.executeQuery();
            List<ProtraVO> list = new ArrayList<ProtraVO>();
            while (resultSet.next()) {
                ProtraVO protracking_listVO = new ProtraVO();
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
    public int getAllCount(String mem_no) {
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ROWCOUNT_BY_MEMNO);
            preparedStatement.setString(1, mem_no);
            resultSet = preparedStatement.executeQuery();
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
    public List<ProtraVO> getPage(int start, int itemsCount, String mem_no) {
        try {
            connection = dataSource.getConnection();
            int end = start + itemsCount-1;
            preparedStatement = connection.prepareStatement(GET_PAGE);
            preparedStatement.setString(1, mem_no);
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, end);
            List<ProtraVO> list = new ArrayList<ProtraVO>();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProtraVO protracking_listVO = new ProtraVO();
                protracking_listVO.setProtra_no(resultSet.getString(3));
                protracking_listVO.setPro_no(resultSet.getString(4));
                protracking_listVO.setMem_no(resultSet.getString(5));
                list.add(protracking_listVO);
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
