package com.art_type.model;

import java.sql.Connection;
import java.sql.Date;
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

import com.article.model.ArticleJDBCDAO;
import com.article.model.ArticleVO;

public class Art_TypeJNDIDAO implements Art_TypeDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String INSERT_STMT = 
			"INSERT INTO ART_TYPE (ART_TYPE_NO,TYPE) VALUES ( ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM ART_TYPE order by ART_TYPE_NO";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM ART_TYPE where ART_TYPE_NO = ?";
		private static final String DELETE = 
			"DELETE FROM ART_TYPE where ART_TYPE_NO = ?";
		private static final String UPDATE = 
			"UPDATE ART_TYPE set  TYPE=? where ART_type_no=?";
	
		
	@Override
	public void insert(Art_TypeVO art_TypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, art_TypeVO.getArt_type_no());
			pstmt.setString(2, art_TypeVO.getType());
			
			
			pstmt.executeUpdate();

			
		} catch (SQLException  se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(Art_TypeVO art_TypeVO) {
		
		Connection con=null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			
			pstmt.setString(1, art_TypeVO.getType());
			pstmt.setInt(2, art_TypeVO.getArt_type_no());
			
			pstmt.executeUpdate();

			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer art_type_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, art_type_no);

			pstmt.executeUpdate();

			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public Art_TypeVO findByPrimaryKey(Integer art_type_no) {
		Art_TypeVO art_TypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, art_type_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				art_TypeVO = new Art_TypeVO();
				art_TypeVO.setArt_type_no(rs.getInt("art_type_no"));
				art_TypeVO.setType(rs.getString("type"));
				
			}

			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return art_TypeVO;
	}

	@Override
	public List<Art_TypeVO> getAll() {
		List<Art_TypeVO> list = new ArrayList<Art_TypeVO>();
		Art_TypeVO art_TypeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				art_TypeVO = new Art_TypeVO();
				art_TypeVO.setArt_type_no(rs.getInt("art_type_no"));
				art_TypeVO.setType(rs.getString("type"));
				
			
				list.add(art_TypeVO); 
			}

			
		} catch (SQLException  se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}
	
}
