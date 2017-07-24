package com.quote.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class QuoteJDBCDAO implements QuoteDAO_Interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "group2";
	String passwd = "group2";
	
	private static final String INSERT = 
			"INSERT INTO QUOTE VALUES (LTRIM(TO_CHAR(QUOTE_SQ.NEXTVAL,'0009')), ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE QUOTE SET PRICE=?, QUO_DATE=? where QUO_NO = ?";
	private static final String DELETE = 
			"DELETE FROM QUOTE where QUO_NO = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM QUOTE where QUO_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM QUOTE order by QUO_NO ";
	
	@Override
	public void insert(QuoteVO quoteVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, quoteVO.getCom_no());
			pstmt.setString(2, quoteVO.getRfqdetail_no());
			pstmt.setInt(3, quoteVO.getPrice());
			pstmt.setString(4, quoteVO.getTitle());
			pstmt.setString(5, quoteVO.getContent());
			pstmt.setTimestamp(6, quoteVO.getQuo_date());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateStatus(QuoteVO quoteVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, quoteVO.getPrice());
			pstmt.setTimestamp(2, quoteVO.getQuo_date());
			pstmt.setString(3,quoteVO.getQuo_no());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(String quo_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, quo_no);
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public QuoteVO findByPK(String quo_no) {
		QuoteVO quoteVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, quo_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				quoteVO = new QuoteVO();
				quoteVO.setQuo_no(rs.getString("quo_no"));
				quoteVO.setCom_no(rs.getString("com_no"));
				quoteVO.setRfqdetail_no(rs.getString("rfqdetail_no"));
				quoteVO.setPrice(rs.getInt("price"));
				quoteVO.setTitle(rs.getString("title"));
				quoteVO.setContent(rs.getString("content"));
				quoteVO.setQuo_date(rs.getTimestamp("quo_date"));
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return quoteVO;
	}

	@Override
	public List<QuoteVO> getAll() {
		List<QuoteVO> list = new ArrayList<QuoteVO>();
		QuoteVO quoteVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				quoteVO = new QuoteVO();
				quoteVO.setQuo_no(rs.getString("quo_no"));
				quoteVO.setCom_no(rs.getString("com_no"));
				quoteVO.setRfqdetail_no(rs.getString("rfqdetail_no"));
				quoteVO.setPrice(rs.getInt("price"));
				quoteVO.setTitle(rs.getString("title"));
				quoteVO.setContent(rs.getString("content"));
				quoteVO.setQuo_date(rs.getTimestamp("quo_date"));
				list.add(quoteVO);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
public static void main(String args[]){
		
		QuoteJDBCDAO dao = new QuoteJDBCDAO();
		
//		QuoteVO quoteVO = new QuoteVO();
//		quoteVO.setCom_no("2001");
//		quoteVO.setRfqdetail_no("0001");
//		quoteVO.setPrice(81000);
//		quoteVO.setTitle("提供最好的服務");
//		quoteVO.setContent("本公司提供最好的服務");
//		Timestamp t = new Timestamp(System.currentTimeMillis());
//		quoteVO.setQuo_date(t);
//		dao.insert(quoteVO);
		
//		QuoteVO quoteVO = new QuoteVO();
//		quoteVO.setPrice(31000);
//		Timestamp t = new Timestamp(System.currentTimeMillis());
//		quoteVO.setQuo_date(t);
//		quoteVO.setQuo_no("0002");
//		dao.updateStatus(quoteVO);
		
//		dao.delete("0003");
		
//		QuoteVO quoteVO = dao.findByPK("0002");
//		System.out.println(quoteVO.getQuo_no());
//		System.out.println(quoteVO.getCom_no());
//		System.out.println(quoteVO.getRfqdetail_no());
//		System.out.println(quoteVO.getPrice());
//		System.out.println(quoteVO.getTitle());
//		System.out.println(quoteVO.getContent());
//		System.out.println(quoteVO.getQuo_date());
		
		List<QuoteVO> list = dao.getAll();
		for(QuoteVO quoteVO : list){
			System.out.println(quoteVO.getQuo_no());
			System.out.println(quoteVO.getCom_no());
			System.out.println(quoteVO.getRfqdetail_no());
			System.out.println(quoteVO.getPrice());
			System.out.println(quoteVO.getTitle());
			System.out.println(quoteVO.getContent());
			System.out.println(quoteVO.getQuo_date());
		}

	
	}
	
}
