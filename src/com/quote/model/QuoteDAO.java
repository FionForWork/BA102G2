package com.quote.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class QuoteDAO implements QuoteDAO_Interface {
	
	private static DataSource ds = null;
	static{
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = 
			"INSERT INTO QUOTE VALUES ('7'||LTRIM(TO_CHAR(QUOTE_SQ.NEXTVAL,'009')), ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE QUOTE SET PRICE=?, QUO_DATE=? where QUO_NO = ?";
	private static final String DELETE = 
			"DELETE FROM QUOTE where QUO_NO = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM QUOTE where QUO_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM QUOTE WHERE RFQDETAIL_NO = ? order by QUO_DATE DESC ";
	private static final String GET_COM_STMT = 
			"SELECT * FROM QUOTE WHERE COM_NO = ? order by QUO_DATE DESC ";
	
	@Override
	public void insert(QuoteVO quoteVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, quoteVO.getCom_no());
			pstmt.setString(2, quoteVO.getRfqdetail_no());
			pstmt.setInt(3, quoteVO.getPrice());
			pstmt.setString(4, quoteVO.getContent());
			pstmt.setTimestamp(5, quoteVO.getQuo_date());
			pstmt.executeUpdate();
			
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
	public void update(QuoteVO quoteVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, quoteVO.getPrice());
			pstmt.setTimestamp(2, quoteVO.getQuo_date());
			pstmt.setString(3,quoteVO.getQuo_no());
			pstmt.executeUpdate();
			
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, quo_no);
			pstmt.executeUpdate();
			
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, quo_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				quoteVO = new QuoteVO();
				quoteVO.setQuo_no(rs.getString("quo_no"));
				quoteVO.setCom_no(rs.getString("com_no"));
				quoteVO.setRfqdetail_no(rs.getString("rfqdetail_no"));
				quoteVO.setPrice(rs.getInt("price"));
				quoteVO.setContent(rs.getString("content"));
				quoteVO.setQuo_date(rs.getTimestamp("quo_date"));
			}
			
			
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
	public List<QuoteVO> getAll(String rfqdetail_no) {
		List<QuoteVO> list = new ArrayList<QuoteVO>();
		QuoteVO quoteVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			pstmt.setString(1, rfqdetail_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				quoteVO = new QuoteVO();
				quoteVO.setQuo_no(rs.getString("quo_no"));
				quoteVO.setCom_no(rs.getString("com_no"));
				quoteVO.setRfqdetail_no(rs.getString("rfqdetail_no"));
				quoteVO.setPrice(rs.getInt("price"));
				quoteVO.setContent(rs.getString("content"));
				quoteVO.setQuo_date(rs.getTimestamp("quo_date"));
				list.add(quoteVO);
			}
			
			
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

	@Override
	public List<QuoteVO> getCom(String com_no) {
		List<QuoteVO> list = new ArrayList<QuoteVO>();
		QuoteVO quoteVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COM_STMT);
			
			pstmt.setString(1, com_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				quoteVO = new QuoteVO();
				quoteVO.setQuo_no(rs.getString("quo_no"));
				quoteVO.setCom_no(rs.getString("com_no"));
				quoteVO.setRfqdetail_no(rs.getString("rfqdetail_no"));
				quoteVO.setPrice(rs.getInt("price"));
				quoteVO.setContent(rs.getString("content"));
				quoteVO.setQuo_date(rs.getTimestamp("quo_date"));
				list.add(quoteVO);
			}
			
			
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

}
