package com.report.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jdk.nashorn.internal.ir.RuntimeNode.Request;



public class ReportJDBCDAO implements Report_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "home";
	String passwd = "123";
	
	private static final String INSERT_STMT = 
			"INSERT INTO REPORT (rep_no,rep_ob_no,reporter_no,reported_no,rep_type_no,content,rep_date,status) VALUES (REP_NO_sq.NEXTVAL, ?, ?, ?, ?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM REPORT order by rep_no";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM REPORT where rep_no = ?";
		private static final String DELETE = 
			"DELETE FROM REPORT where rep_no = ?";
		private static final String UPDATE = 
				"UPDATE REPORT set status=? where REP_NO = ?";
		
		
	@Override
	public void insert(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, reportVO.getRep_ob_no());
			pstmt.setInt(2, reportVO.getReporter_no());
			pstmt.setInt(3, reportVO.getReported_no());
			pstmt.setInt(4, reportVO.getRep_type_no());
			pstmt.setString(5, reportVO.getContent());
			pstmt.setDate(6, reportVO.getRep_date());
			pstmt.setInt(7, reportVO.getStatus());
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
	public void update(ReportVO reportVO) {
		Connection con=null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			
			pstmt.setInt(1, reportVO.getStatus());
			pstmt.setInt(2, reportVO.getRep_no());
			
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
	public void delete(Integer rep_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, rep_no);

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
	public ReportVO findByPrimaryKey(Integer rep_no) {
		ReportVO reportVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, rep_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				reportVO = new ReportVO();
				reportVO.setRep_no(rs.getInt("rep_no"));
				reportVO.setRep_ob_no(rs.getInt("rep_ob_no"));
				reportVO.setReporter_no(rs.getInt("reporter_no"));
				reportVO.setReported_no(rs.getInt("reported_no"));
				reportVO.setRep_type_no(rs.getInt("rep_type_no"));
				reportVO.setContent(rs.getString("content"));
				reportVO.setRep_date(rs.getDate("rep_date"));
				reportVO.setStatus(rs.getInt("status"));
				
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
		return reportVO;
	}
	@Override
	public List<ReportVO> getAll() {
		List<ReportVO> list = new ArrayList<ReportVO>();
		ReportVO reportVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reportVO = new ReportVO();
				reportVO.setRep_no(rs.getInt("rep_no"));
				reportVO.setRep_ob_no(rs.getInt("rep_ob_no"));
				reportVO.setReporter_no(rs.getInt("reporter_no"));
				reportVO.setReported_no(rs.getInt("reported_no"));
				reportVO.setRep_type_no(rs.getInt("rep_type_no"));
				reportVO.setContent(rs.getString("content"));
				reportVO.setRep_date(rs.getDate("rep_date"));
				reportVO.setStatus(rs.getInt("status"));
			
				list.add(reportVO); 
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

		ReportJDBCDAO dao = new ReportJDBCDAO();
		
		java.util.Date date=new java.util.Date();
		 Timestamp t = new Timestamp(System.currentTimeMillis()); 
		 
		// 新增
//		 ReportVO reportVO1 = new ReportVO();
//		
//		 reportVO1.setRep_ob_no(5001);
//		 reportVO1.setReporter_no(2001);
//		 reportVO1.setReported_no(1003);
//		 reportVO1.setRep_type_no(10);
//		 reportVO1.setContent("檢舉內文");
//		 reportVO1.setRep_date(new Date(date.getTime()));
//		 reportVO1.setStatus(0);
//		
//		dao.insert(reportVO1);

//		// 修改
		 ReportVO reportVO2 = new ReportVO();
		 
		
		 reportVO2.setStatus(2);
		 reportVO2.setRep_no(1);
		
		dao.update(reportVO2);
//
//		// 刪除
//		dao.delete(1);
//
//		// 查詢
//		 ReportVO reportVO3 = dao.findByPrimaryKey(2);
//		System.out.print(reportVO3.getRep_no() + ",");
//		System.out.print(reportVO3.getRep_ob_no() + ",");
//		System.out.print(reportVO3.getReporter_no()+ ",");
//		System.out.print(reportVO3.getReported_no()+ ",");
//		System.out.print(reportVO3.getRep_type_no() + ",");
//		System.out.print(reportVO3.getContent()+ ",");
//		System.out.print(reportVO3.getRep_date()+ ",");
//		System.out.println(reportVO3.getStatus());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<ReportVO> list = dao.getAll();
//		for (ReportVO report4 : list) {
//		System.out.print(report4.getRep_no() + ",");
//		System.out.print(report4.getRep_ob_no() + ",");
//		System.out.print(report4.getReporter_no()+ ",");
//		System.out.print(report4.getReported_no()+ ",");
//		System.out.print(report4.getRep_type_no() + ",");
//		System.out.print(report4.getContent()+ ",");
//		System.out.print(report4.getRep_date()+ ",");
//		System.out.println(report4.getStatus());
//		System.out.println();
//	}

	
}
}
