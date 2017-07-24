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
			"INSERT INTO REPORT (rep_no,reproter_no,reproted_no,title,content,rep_date,status) VALUES (REP_NO_sq.NEXTVAL, ?, ?, ?, ?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM REPORT order by rep_no";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM REPORT where rep_no = ?";
		private static final String DELETE = 
			"DELETE FROM REPORT where rep_no = ?";
		private static final String UPDATE = 
				"UPDATE REPORT set reproter_no=?, reproted_no=?,title=?,content=?,rep_date=?,status=? where REP_NO = ?";
		
		
	@Override
	public void insert(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			
			pstmt.setInt(1, reportVO.getReproter_no());
			pstmt.setInt(2, reportVO.getReproted_no());
			pstmt.setString(3, reportVO.getTitle());
			pstmt.setString(4, reportVO.getContent());
			pstmt.setDate(5, reportVO.getRep_date());
			pstmt.setInt(6, reportVO.getStatus());
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

			
			pstmt.setInt(1, reportVO.getReproter_no());
			pstmt.setInt(2, reportVO.getReproted_no());
			pstmt.setString(3, reportVO.getTitle());
			pstmt.setString(4, reportVO.getContent());
			pstmt.setDate(5, reportVO.getRep_date());
			pstmt.setInt(6, reportVO.getStatus());
			pstmt.setInt(7, reportVO.getRep_no());
			
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
				reportVO.setReproter_no(rs.getInt("reproter_no"));
				reportVO.setReproted_no(rs.getInt("reproted_no"));
				reportVO.setTitle(rs.getString("title"));
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
				reportVO.setReproter_no(rs.getInt("reproter_no"));
				reportVO.setReproted_no(rs.getInt("reproted_no"));
				reportVO.setTitle(rs.getString("title"));
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
//		 reportVO1.setReproter_no(11);
//		 reportVO1.setReproted_no(22);
//		 reportVO1.setTitle("檢舉標題");
//		 reportVO1.setContent("檢舉內文");
//		 reportVO1.setRep_date(new Date(date.getTime()));
//		 reportVO1.setStatus(10);
//		
//		dao.insert(reportVO1);

//		// 修改
		 ReportVO reportVO2 = new ReportVO();
		 reportVO2.setRep_no(15);
		 reportVO2.setReproter_no(1);
		 reportVO2.setReproted_no(2);
		 reportVO2.setTitle("檢舉");
		 reportVO2.setContent("檢舉內文");
		 reportVO2.setRep_date(new Date(date.getTime()));
		 reportVO2.setStatus(10);
		
		dao.update(reportVO2);
//
//		// 刪除
//		dao.delete(1);
//
//		// 查詢
//		 ReportVO reportVO3 = dao.findByPrimaryKey(2);
//		System.out.print(reportVO3.getRep_no() + ",");
//		System.out.print(reportVO3.getReproter_no()+ ",");
//		System.out.print(reportVO3.getReproted_no()+ ",");
//		System.out.print(reportVO3.getTitle()+ ",");
//		System.out.print(reportVO3.getContent()+ ",");
//		System.out.print(reportVO3.getRep_date()+ ",");
//		System.out.println(reportVO3.getStatus());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<ReportVO> list = dao.getAll();
//		for (ReportVO report4 : list) {
//		System.out.print(report4.getRep_no() + ",");
//		System.out.print(report4.getReproter_no()+ ",");
//		System.out.print(report4.getReproted_no()+ ",");
//		System.out.print(report4.getTitle()+ ",");
//		System.out.print(report4.getContent()+ ",");
//		System.out.print(report4.getRep_date()+ ",");
//		System.out.println(report4.getStatus());
//		System.out.println();
//	}

	
}
}
