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
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.order_detail.model.Order_detailDAO;
import com.order_detail.model.Order_detailVO;
import com.product.model.ProductVO;

public class OrdDAO_JDBC implements OrdDAO_Interface {
    private static final String INSERT                 = "insert into ORD (ORD_NO, SELLER_NO, CUST_NO, ADDRESS,ORD_DATE,TOTAL,SCORE,STATUS)" + "values(ORD_NO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE                 = "delete from ORD where ORD_NO = ?";
    private static final String UPDATE                 = "update ORD set ADDRESS = ? ,TOTAL = ? ,SCORE = ?,STATUS = ? where ORD_NO = ?";
    private static final String GET_BY_PK              = "select * from ORD where ORD_NO = ?";
    private static final String GET_ALL                = "select * from ORD order by ORD_NO asc";
    private static final String GET_ALL_BY_CUST        = "select * from ORD where CUST_NO = ? and STATUS = ? order by ORD_DATE desc";
    private static final String GET_ALL_BY_SELLER      = "select * from ORD where SELLER_NO = ? and STATUS = ? order by ORD_DATE desc";
    private static final String GET_ROWCOUNT_BY_CUST   = "select count(rownum) from ORD where CUST_NO = ? and STATUS = ?";
    private static final String GET_ROWCOUNT_BY_SELLER = "select count(rownum) from ORD where SELLER_NO = ? and STATUS = ?";

    private Connection        connection;
    private PreparedStatement preparedStatement;
    private ResultSet         resultSet;
    private static DataSource dataSource = null;
    static {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/BA102G2DB");
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
    public void insert(OrdVO ordVO) {
        try {
            connection = dataSource.getConnection();
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
    public void insert(OrdVO ordVO, List<Order_detailVO> list) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            String PKName[] = { "ORD_NO" };
            preparedStatement = connection.prepareStatement(INSERT, PKName);
            preparedStatement.setString(1, ordVO.getSeller_no());
            preparedStatement.setString(2, ordVO.getCust_no());
            preparedStatement.setString(3, ordVO.getAddress());
            preparedStatement.setTimestamp(4, ordVO.getOrd_date());
            preparedStatement.setInt(5, ordVO.getTotal());
            preparedStatement.setInt(6, ordVO.getScore());
            preparedStatement.setString(7, ordVO.getStatus());
            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            String ord_no = resultSet.getString(1);
            Order_detailDAO order_detailDAO = new Order_detailDAO();
            for (Order_detailVO Order_detailVO : list) {
                Order_detailVO.setOrd_no(ord_no);
                order_detailDAO.insert(Order_detailVO, connection);
            }
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
    public void delete(String ord_no) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setString(1, ord_no);
            preparedStatement.execute();
            Order_detailDAO order_detailDAO=new Order_detailDAO();
            order_detailDAO.delete(ord_no, connection);
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
    public void update(OrdVO ordVO) {
        try {
            connection = dataSource.getConnection();
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
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_BY_PK);
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
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL);
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
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL_BY_CUST);
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
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL_BY_SELLER);
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
    public int getAllRowCountByCust(String cust_no, String status) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ROWCOUNT_BY_CUST);
            preparedStatement.setString(1, cust_no);
            preparedStatement.setString(2, status);
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
    public int getAllRowCountBySeller(String seller_no, String status) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ROWCOUNT_BY_SELLER);
            preparedStatement.setString(1, seller_no);
            preparedStatement.setString(2, status);
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
    public List<OrdVO> getAllByCust(String cust_no, String status, String orderMethod) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OrdVO> getAllBySeller(String seller_no, String status, String orderMethod) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OrdVO> getPageByCust(int start, int itemsCount, String cust_no, String status, String orderMethod) {
        String GET_PAGE_BY_CUST = "select * from (select rownum bRn, b.* from (select rownum aRn, a.* from ORD a where CUST_NO = ? and STATUS = ? order by "+orderMethod+") b) where bRn between ? and ?";
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_PAGE_BY_CUST);
            preparedStatement.setString(1, cust_no);
            preparedStatement.setString(2, status);
            preparedStatement.setInt(3, start);
            int end=start+itemsCount;
            preparedStatement.setInt(4, end);
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
    public List<OrdVO> getPageBySeller(int start, int itemsCount, String seller_no, String status, String orderMethod) {
        String GET_PAGE_BY_CUST = "select * from (select rownum bRn, b.* from (select rownum aRn, a.* from ORD a where SELLER_NO = ? and STATUS = ? order by "+orderMethod+") b) where bRn between ? and ?";
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_PAGE_BY_CUST);
            preparedStatement.setString(1, seller_no);
            preparedStatement.setString(2, status);
            preparedStatement.setInt(3, start);
            int end=start+itemsCount;
            preparedStatement.setInt(4, end);
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
