package com.art_type.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.article.model.ArticleJDBCDAO;
import com.article.model.ArticleVO;

public class Art_TypeJDBCDAO implements Art_TypeDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "home";
	String passwd = "123";
	
	
	private static final String INSERT_STMT = 
			"INSERT INTO ART_TYPE (ART_TYPE_NO,TYPE) VALUES ( ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM ART_TYPE order by ART_TYPE_NO";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM ART_TYPE where ART_TYPE_NO = ?";
		private static final String DELETE = 
			"DELETE FROM ART_TYPE where ART_TYPE_NO = ?";
		private static final String UPDATE = 
			"UPDATE ART_TYPE set  TYPE=? where art_type_no=?";
	
		
	@Override
	public void insert(Art_TypeVO art_TypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, art_TypeVO.getArt_type_no());
			pstmt.setString(2, art_TypeVO.getType());
			
			
			pstmt.executeUpdate();

			
		} catch (SQLException | ClassNotFoundException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			
			pstmt.setString(1, art_TypeVO.getType());
			pstmt.setInt(2, art_TypeVO.getArt_type_no());
			
			pstmt.executeUpdate();

			
		} catch (SQLException | ClassNotFoundException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, art_type_no);

			pstmt.executeUpdate();

			
		} catch (SQLException | ClassNotFoundException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, art_type_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				art_TypeVO = new Art_TypeVO();
				art_TypeVO.setArt_type_no(rs.getInt("art_type_no"));
				art_TypeVO.setType(rs.getString("type"));
				
			}

			
		} catch (SQLException | ClassNotFoundException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				art_TypeVO = new Art_TypeVO();
				art_TypeVO.setArt_type_no(rs.getInt("art_type_no"));
				art_TypeVO.setType(rs.getString("type"));
				
			
				list.add(art_TypeVO); 
			}

			
		} catch (SQLException | ClassNotFoundException se) {
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
	public static void main(String[] args) {

		Art_TypeJDBCDAO dao = new Art_TypeJDBCDAO();
		
		
		
		// 新增
		Art_TypeVO art_TypeVO1 = new Art_TypeVO();
		art_TypeVO1.setArt_type_no(10);
		art_TypeVO1.setType("婚紗");
		dao.insert(art_TypeVO1);

//		// 修改
		
		Art_TypeVO art_TypeVO2 = new Art_TypeVO();
		art_TypeVO2.setArt_type_no(10);
		art_TypeVO2.setType("婚紗");
		dao.update(art_TypeVO2);
		
//
//		// 刪除
//		dao.delete(123);
//
//		// 查詢
//		Art_TypeVO art_TypeVO3 = dao.findByPrimaryKey(10);
//		System.out.print(art_TypeVO3.getArt_type_no() + ",");
//		System.out.print(art_TypeVO3.getType() + ",");
//		
//		System.out.println("---------------------");
//
//		// 查詢
//		List<Art_TypeVO> list = dao.getAll();
//		for (Art_TypeVO art_type : list) {
//			System.out.print(art_type.getArt_type_no() + ",");
//			System.out.print(art_type.getType() + ",");
//			
//			System.out.println();
//		}
	
	
}
}
