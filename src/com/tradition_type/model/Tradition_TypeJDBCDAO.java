package com.tradition_type.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.problem_type.model.Problem_TypeJDBCDAO;
import com.problem_type.model.Problem_TypeVO;

public class Tradition_TypeJDBCDAO implements Tradition_Type_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "home";
	String passwd = "123";
	
	private static final String INSERT_STMT = 
			"INSERT INTO TRADITION_TYPE (TRA_TYPE_NO,TYPE) VALUES ( ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM TRADITION_TYPE order by TRA_TYPE_NO";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM TRADITION_TYPE where TRA_TYPE_NO = ?";
		private static final String DELETE = 
			"DELETE FROM TRADITION_TYPE where TRA_TYPE_NO = ?";
		private static final String UPDATE = 
			"UPDATE TRADITION_TYPE set  TYPE=? where TRA_TYPE_NO=?";

	@Override
	public void insert(Tradition_TypeVO tradition_TypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, tradition_TypeVO.getTra_type_no());
			pstmt.setString(2, tradition_TypeVO.getType());
			
			
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
	public void update(Tradition_TypeVO tradition_TypeVO) {
		Connection con=null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			
			pstmt.setString(1, tradition_TypeVO.getType());
			pstmt.setInt(2, tradition_TypeVO.getTra_type_no());
			
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
	public void delete(Integer tra_type_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tra_type_no);

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
	public Tradition_TypeVO findByPrimaryKey(Integer tra_type_no) {
		Tradition_TypeVO tradition_TypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tra_type_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				 tradition_TypeVO = new Tradition_TypeVO();
				 tradition_TypeVO.setTra_type_no(rs.getInt("tra_type_no"));
				 tradition_TypeVO.setType(rs.getString("type"));
				
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
		return tradition_TypeVO;
	}

	@Override
	public List<Tradition_TypeVO> getAll() {
		List<Tradition_TypeVO> list = new ArrayList<Tradition_TypeVO>();
		Tradition_TypeVO tradition_TypeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tradition_TypeVO = new Tradition_TypeVO();
				tradition_TypeVO.setTra_type_no(rs.getInt("tra_type_no"));
				tradition_TypeVO.setType(rs.getString("type"));
				
			
				list.add(tradition_TypeVO); 
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

		Tradition_TypeJDBCDAO dao = new Tradition_TypeJDBCDAO();
		
		
		
		// 新增
//		Tradition_TypeVO tradition_TypeVO1 = new Tradition_TypeVO();
//		tradition_TypeVO1.setTra_type_no(60);
//		tradition_TypeVO1.setType("婚紗");
//		dao.insert(tradition_TypeVO1);

//		// 修改
		
//		Tradition_TypeVO tradition_TypeVO2 = new Tradition_TypeVO();
//		tradition_TypeVO2.setTra_type_no(60);
//		tradition_TypeVO2.setType("?");
//		dao.update(tradition_TypeVO2);
		
//
//		// 刪除
//		dao.delete(60);
//
//		// 查詢
//		Tradition_TypeVO tradition_TypeVO3 = dao.findByPrimaryKey(10);
//		System.out.print(tradition_TypeVO3.getTra_type_no() + ",");
//		System.out.print(tradition_TypeVO3.getType() + ",");
//		
//		System.out.println("---------------------");
//
//		// 查詢
		List<Tradition_TypeVO> list = dao.getAll();
		for (Tradition_TypeVO tradition_Type : list) {
			System.out.print(tradition_Type.getTra_type_no() + ",");
			System.out.print(tradition_Type.getType() + ",");
			
			System.out.println();
		}
	
	
}
	
	


}
