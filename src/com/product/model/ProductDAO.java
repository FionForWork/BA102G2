package com.product.model;

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

public class ProductDAO implements ProductDAO_Interface {
    private static final String INSERT                    = "insert into PRODUCT (PRO_NO, PRO_NAME, SELLER_NO,PRO_DESC,PRICE,AMOUNT,IMG,PRO_DATE,PROTYPE_NO,STATUS,TIMES,SCORE)" + "values('4'||lpad(PRO_NO_SEQ.NEXTVAL,3,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_BY_NO              = "delete from PRODUCT where PRO_NO = ?";
    private static final String UPDATE                    = "update PRODUCT set PRO_NAME = ? ,PRO_DESC = ?, PRICE= ? ,AMOUNT = ? ,IMG = ?,PROTYPE_NO = ? ,STATUS = ? ,TIMES = ? , SCORE = ? where PRO_NO = ?";
    private static final String FIND_BY_PK                = "select * from PRODUCT where PRO_NO = ?";
    private static final String FIND_BY_PK_NO_IMG         = "select PRO_NO, PRO_NAME, SELLER_NO, PRO_DESC ,PRICE,AMOUNT,PRO_DATE,PROTYPE_NO,STATUS,TIMES,SCORE from PRODUCT where PRO_NO = ?";
    private static final String FIND_BY_SELLER            = "select PRO_NO, PRO_NAME, SELLER_NO, PRO_DESC ,PRICE,AMOUNT,PRO_DATE,PROTYPE_NO,STATUS,TIMES,SCORE from PRODUCT where SELLER_NO = ?";
    private static final String GET_ALL_NO_DESC_AND_IMG   = "select PRO_NO, PRO_NAME, SELLER_NO,PRICE,AMOUNT,PRO_DATE,PROTYPE_NO,TIMES,SCORE from PRODUCT where STATUS = 1";
    private static final String GET_ALL_ORDER_BY_ASC      = "select * from PRODUCT where STATUS = 1 order by PRO_NO asc";
    private static final String GET_ALL__BY_PROTYPE       = "select * from PRODUCT where STATUS = 1 and PROTYPE_NO = ? ";
    private static final String GET_ALL_ROW               = "select count(rownum) from PRODUCT where STATUS = 1";
    private static final String GET_ALL_ROW_UNPREVIEW     = "select count(rownum) from PRODUCT where STATUS = 0";
    private static final String GET_TYPE_ALL_ROW          = "select count(rownum) from PRODUCT where PROTYPE_NO = ? and STATUS = 1";
    private static final String GET_SOME_ROW              = "select PRO_NO, PRO_NAME, SELLER_NO, PRICE, AMOUNT, PRO_DATE, PROTYPE_NO, TIMES, SCORE from (select rownum bRn, b.*from (select rownum aRn, a.* from PRODUCT a where STATUS = 1 order by a.pro_no) b) where bRn between ? and ?";
    private static final String GET_SOME_ROW_OF_UNPREVIEW = "select PRO_NO, PRO_NAME, SELLER_NO, PRO_DESC, PRICE,AMOUNT, PRO_DATE, PROTYPE_NO, STATUS, TIMES,SCORE from (select rownum bRn, b.*from (select rownum aRn, a.* from PRODUCT a where STATUS = 0 order by a.PRO_DATE ) b) where bRn between ? and ?";

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
    public void add(ProductVO productVO) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, productVO.getPro_name());
            preparedStatement.setString(2, productVO.getSeller_no());
            preparedStatement.setString(3, productVO.getPro_desc());
            preparedStatement.setInt(4, productVO.getPrice());
            preparedStatement.setInt(5, productVO.getAmount());
            preparedStatement.setBytes(6, productVO.getImg());
            preparedStatement.setTimestamp(7, productVO.getPro_date());
            preparedStatement.setString(8, productVO.getProtype_no());
            preparedStatement.setString(9, productVO.getStatus());
            preparedStatement.setInt(10, productVO.getTimes());
            preparedStatement.setInt(11, productVO.getScore());
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
    public void delete(String pro_no) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_BY_NO);
            preparedStatement.setString(1, pro_no);
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
    public void update(ProductVO productVO) {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, productVO.getPro_name());
            preparedStatement.setString(2, productVO.getPro_desc());
            preparedStatement.setInt(3, productVO.getPrice());
            preparedStatement.setInt(4, productVO.getAmount());
            preparedStatement.setBytes(5, productVO.getImg());
            preparedStatement.setString(6, productVO.getProtype_no());
            preparedStatement.setString(7, productVO.getStatus());
            preparedStatement.setInt(8, productVO.getTimes());
            preparedStatement.setInt(9, productVO.getScore());
            preparedStatement.setString(10, productVO.getPro_no());
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
    public ProductVO getOneByPK(String pro_no) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_PK);
            preparedStatement.setString(1, pro_no);
            resultSet = preparedStatement.executeQuery();
            ProductVO productVO = new ProductVO();
            while (resultSet.next()) {
                productVO.setPro_no(resultSet.getString(1));
                productVO.setPro_name(resultSet.getString(2));
                productVO.setSeller_no(resultSet.getString(3));
                productVO.setPro_desc(resultSet.getString(4));
                productVO.setPrice(resultSet.getInt(5));
                productVO.setAmount(resultSet.getInt(6));
                productVO.setImg(resultSet.getBytes(7));
                productVO.setPro_date(resultSet.getTimestamp(8));
                productVO.setProtype_no(resultSet.getString(9));
                productVO.setStatus(resultSet.getString(10));
                productVO.setTimes(resultSet.getInt(11));
                productVO.setScore(resultSet.getInt(12));
            }
            return productVO;
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
    public List<ProductVO> getAll() {
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_ORDER_BY_ASC);
            List<ProductVO> list = new ArrayList<>();
            while (resultSet.next()) {
                ProductVO productVO = new ProductVO();
                productVO.setPro_no(resultSet.getString(1));
                productVO.setPro_name(resultSet.getString(2));
                productVO.setSeller_no(resultSet.getString(3));
                productVO.setPro_desc(resultSet.getString(4));
                productVO.setPrice(resultSet.getInt(5));
                productVO.setAmount(resultSet.getInt(6));
                productVO.setImg(resultSet.getBytes(7));
                productVO.setPro_date(resultSet.getTimestamp(8));
                productVO.setProtype_no(resultSet.getString(9));
                productVO.setStatus(resultSet.getString(10));
                productVO.setTimes(resultSet.getInt(11));
                productVO.setScore(resultSet.getInt(12));
                list.add(productVO);
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
    public int getAllCount() {
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_ROW);
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
    public List<ProductVO> getSome(int page, int count) {
        try {
            connection = dataSource.getConnection();
            int start = (page - 1) * count + 1;
            int end = page * count;
            preparedStatement = connection.prepareStatement(GET_SOME_ROW);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, end);
            List<ProductVO> list = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductVO productVO = new ProductVO();
                productVO.setPro_no(resultSet.getString(3));
                productVO.setPro_name(resultSet.getString(4));
                productVO.setSeller_no(resultSet.getString(5));
                productVO.setPrice(resultSet.getInt(6));
                productVO.setAmount(resultSet.getInt(7));
                productVO.setPro_date(resultSet.getTimestamp(8));
                productVO.setProtype_no(resultSet.getString(9));
                productVO.setStatus(resultSet.getString(10));
                productVO.setTimes(resultSet.getInt(11));
                productVO.setScore(resultSet.getInt(12));
                list.add(productVO);
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
    public int getTypeAllCount(String protype_no) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_TYPE_ALL_ROW);
            preparedStatement.setString(1, protype_no);
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
    public List<ProductVO> getSome(int page, int count, String protype_no) {
        try {
            connection = dataSource.getConnection();
            int start = (page - 1) * count + 1;
            int end = page * count;
            preparedStatement = connection.prepareStatement("select PRO_NO, PRO_NAME, SELLER_NO,PRICE,AMOUNT,PRO_DATE,PROTYPE_NO,TIMES,SCORE from (select rownum bRn, b.*from (select rownum aRn, a.* from PRODUCT a where PROTYPE_NO = ? and STATUS = 1 order by a.PRO_NO ) b) where bRn between ? and ?");
            preparedStatement.setString(1, protype_no);
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, end);
            List<ProductVO> list = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductVO productVO = new ProductVO();
                productVO.setPro_no(resultSet.getString(1));
                productVO.setPro_name(resultSet.getString(2));
                productVO.setSeller_no(resultSet.getString(3));
                productVO.setPro_desc(resultSet.getString(4));
                productVO.setPrice(resultSet.getInt(5));
                productVO.setAmount(resultSet.getInt(6));
                productVO.setPro_date(resultSet.getTimestamp(7));
                productVO.setProtype_no(resultSet.getString(8));
                productVO.setStatus(resultSet.getString(9));
                productVO.setTimes(resultSet.getInt(10));
                productVO.setScore(resultSet.getInt(11));
                list.add(productVO);
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
    public List<ProductVO> getSome(int page, int count, String protype_no, String orderMethod) {
        try {
            connection = dataSource.getConnection();
            int start = (page - 1) * count + 1;
            int end = page * count;
            String GET_SOME_ROW_OF_TYPE_ORDER_BY;
            if (protype_no.equals("0")) {
                GET_SOME_ROW_OF_TYPE_ORDER_BY = "select PRO_NO, PRO_NAME, SELLER_NO, PRO_DESC, PRICE,AMOUNT, PRO_DATE, PROTYPE_NO, STATUS, TIMES,SCORE from (select rownum bRn, b.*from (select rownum aRn, a.* from PRODUCT a where STATUS = 1 order by a." + orderMethod + " ) b) where bRn between ? and ?";
                preparedStatement = connection.prepareStatement(GET_SOME_ROW_OF_TYPE_ORDER_BY);
                preparedStatement.setInt(1, start);
                preparedStatement.setInt(2, end);
            }
            else {
                GET_SOME_ROW_OF_TYPE_ORDER_BY = "select PRO_NO, PRO_NAME, SELLER_NO, PRO_DESC, PRICE,AMOUNT, PRO_DATE, PROTYPE_NO, STATUS, TIMES,SCORE from (select rownum bRn, b.*from (select rownum aRn, a.* from PRODUCT a where PROTYPE_NO = ? and STATUS = 1 order by a." + orderMethod + " ) b) where bRn between ? and ?";
                preparedStatement = connection.prepareStatement(GET_SOME_ROW_OF_TYPE_ORDER_BY);
                preparedStatement.setString(1, protype_no);
                preparedStatement.setInt(2, start);
                preparedStatement.setInt(3, end);
            }
            List<ProductVO> list = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductVO productVO = new ProductVO();
                productVO.setPro_no(resultSet.getString(1));
                productVO.setPro_name(resultSet.getString(2));
                productVO.setSeller_no(resultSet.getString(3));
                productVO.setPro_desc(resultSet.getString(4));
                productVO.setPrice(resultSet.getInt(5));
                productVO.setAmount(resultSet.getInt(6));
                productVO.setPro_date(resultSet.getTimestamp(7));
                productVO.setProtype_no(resultSet.getString(8));
                productVO.setStatus(resultSet.getString(9));
                productVO.setTimes(resultSet.getInt(10));
                productVO.setScore(resultSet.getInt(11));
                list.add(productVO);
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
    public List<ProductVO> getAllBySeller(String seller_no) {
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_SELLER);
            preparedStatement.setString(1, seller_no);
            resultSet = preparedStatement.executeQuery();
            List<ProductVO> list = new ArrayList<>();
            while (resultSet.next()) {
                ProductVO productVO = new ProductVO();
                productVO.setPro_no(resultSet.getString(1));
                productVO.setPro_name(resultSet.getString(2));
                productVO.setSeller_no(resultSet.getString(3));
                productVO.setPro_desc(resultSet.getString(4));
                productVO.setPrice(resultSet.getInt(5));
                productVO.setAmount(resultSet.getInt(6));
                productVO.setPro_date(resultSet.getTimestamp(7));
                productVO.setProtype_no(resultSet.getString(8));
                productVO.setStatus(resultSet.getString(9));
                productVO.setTimes(resultSet.getInt(10));
                productVO.setScore(resultSet.getInt(11));
                list.add(productVO);
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
    public List<ProductVO> getSomeUnPreview(int page, int count) {
        try {
            connection = dataSource.getConnection();
            int start = (page - 1) * count + 1;
            int end = page * count;
            preparedStatement = connection.prepareStatement(GET_SOME_ROW_OF_UNPREVIEW);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, end);
            List<ProductVO> list = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductVO productVO = new ProductVO();
                productVO.setPro_no(resultSet.getString(1));
                productVO.setPro_name(resultSet.getString(2));
                productVO.setSeller_no(resultSet.getString(3));
                productVO.setPro_desc(resultSet.getString(4));
                productVO.setPrice(resultSet.getInt(5));
                productVO.setAmount(resultSet.getInt(6));
                productVO.setPro_date(resultSet.getTimestamp(7));
                productVO.setProtype_no(resultSet.getString(8));
                productVO.setStatus(resultSet.getString(9));
                productVO.setTimes(resultSet.getInt(10));
                productVO.setScore(resultSet.getInt(11));
                list.add(productVO);
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
    public int getAllUnPreviewCount() {
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_ROW_UNPREVIEW);
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
    public List<ProductVO> getAllByType(String protype_no) {
        try {
            connection = dataSource.getConnection();
            if ("0".equals(protype_no)) {
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery("select * from PRODUCT where STATUS = 1");
            }
            else {
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL__BY_PROTYPE);
                preparedStatement.setString(1, protype_no);
                resultSet = preparedStatement.executeQuery();
            }

            List<ProductVO> list = new ArrayList<>();
            while (resultSet.next()) {
                ProductVO productVO = new ProductVO();
                productVO.setPro_no(resultSet.getString(1));
                productVO.setPro_name(resultSet.getString(2));
                productVO.setSeller_no(resultSet.getString(3));
                productVO.setPro_desc(resultSet.getString(4));
                productVO.setPrice(resultSet.getInt(5));
                productVO.setAmount(resultSet.getInt(6));
                productVO.setImg(resultSet.getBytes(7));
                productVO.setPro_date(resultSet.getTimestamp(8));
                productVO.setProtype_no(resultSet.getString(9));
                productVO.setStatus(resultSet.getString(10));
                productVO.setTimes(resultSet.getInt(11));
                productVO.setScore(resultSet.getInt(12));
                list.add(productVO);
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
    public List<ProductVO> getAllNoDescAndImg() {
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_NO_DESC_AND_IMG);
            List<ProductVO> list = new ArrayList<>();
            while (resultSet.next()) {
                ProductVO productVO = new ProductVO();
                productVO.setPro_no(resultSet.getString(1));
                productVO.setPro_name(resultSet.getString(2));
                productVO.setSeller_no(resultSet.getString(3));
                productVO.setPrice(resultSet.getInt(4));
                productVO.setAmount(resultSet.getInt(5));
                productVO.setPro_date(resultSet.getTimestamp(6));
                productVO.setProtype_no(resultSet.getString(7));
                productVO.setTimes(resultSet.getInt(8));
                productVO.setScore(resultSet.getInt(9));
                list.add(productVO);
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
    public ProductVO getOneByPKNoImg(String pro_no) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_PK_NO_IMG);
            preparedStatement.setString(1, pro_no);
            resultSet = preparedStatement.executeQuery();
            ProductVO productVO = new ProductVO();
            while (resultSet.next()) {
                productVO.setPro_no(resultSet.getString(1));
                productVO.setPro_name(resultSet.getString(2));
                productVO.setSeller_no(resultSet.getString(3));
                productVO.setPro_desc(resultSet.getString(4));
                productVO.setPrice(resultSet.getInt(5));
                productVO.setAmount(resultSet.getInt(6));
                productVO.setPro_date(resultSet.getTimestamp(7));
                productVO.setProtype_no(resultSet.getString(8));
                productVO.setStatus(resultSet.getString(9));
                productVO.setTimes(resultSet.getInt(10));
                productVO.setScore(resultSet.getInt(11));
            }
            return productVO;
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
