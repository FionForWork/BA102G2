package com.report_type.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Report_typeJDBC  implements Report_type_intereface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "home";
	String passwd = "123";
	
	private static final String INSERT_STMT = 
			"INSERT INTO REPORT_TYPE (REP_TYPE_NO,TYPE) VALUES ( ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM REPORT_TYPE order by REP_TYPE_NO";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM REPORT_TYPE where REP_TYPE_NO = ?";
		private static final String DELETE = 
			"DELETE FROM REPORT_TYPE where REP_TYPE_NO = ?";
		private static final String UPDATE = 
			"UPDATE REPORT_TYPE set  TYPE=? where REP_TYPE_NO=?";

	@Override
	public void insert(Report_typeVO report_typeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, report_typeVO.getRep_type_no());
			pstmt.setString(2, report_typeVO.getType());
			
			
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
	public void update(Report_typeVO report_typeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, report_typeVO.getType());
			pstmt.setInt(2, report_typeVO.getRep_type_no());
			
			
			
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
	public void delete(Integer report_type_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, report_type_no);

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
	public Report_typeVO findByPrimaryKey(Integer rep_type_no) {
		Report_typeVO report_typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, rep_type_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				report_typeVO=new Report_typeVO();
				report_typeVO.setRep_type_no(rs.getInt("rep_type_no"));
				report_typeVO.setType(rs.getString("type"));
				
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
		return report_typeVO;
	}

	@Override
	public List<Report_typeVO> getAll() {
		List<Report_typeVO> list = new ArrayList<Report_typeVO>();
		Report_typeVO report_typeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				report_typeVO = new Report_typeVO();
				report_typeVO.setRep_type_no(rs.getInt("rep_type_no"));
				report_typeVO.setType(rs.getString("type"));
				
			
				list.add(report_typeVO); 
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

		Report_typeJDBC dao = new Report_typeJDBC();
		
		
		
		// 新增
//		Report_typeVO report_typeVO1 = new Report_typeVO();
//		report_typeVO1.setRep_type_no(50);
//		report_typeVO1.setType("婚紗");
//		dao.insert(report_typeVO1);

//		// 修改
		
//		Report_typeVO report_typeVO2 = new Report_typeVO();
//		report_typeVO2.setType("婚");
//		report_typeVO2.setRep_type_no(50);
//	
//		dao.update(report_typeVO2);
		
//
//		// 刪除
//		dao.delete(50);
//
//		// 查詢
//		Report_typeVO report_typeVO3 = dao.findByPrimaryKey(10);
//		System.out.print(report_typeVO3.getRep_type_no() + ",");
//		System.out.print(report_typeVO3.getType() + ",");
//		
//		System.out.println("---------------------");
//
//		// 查詢
//		List<Report_typeVO> list = dao.getAll();
//		for (Report_typeVO rep_type : list) {
//			System.out.print(rep_type.getRep_type_no() + ",");
//			System.out.print(rep_type.getType() + ",");
//			
//			System.out.println();
//		}
//	
	
}
}


