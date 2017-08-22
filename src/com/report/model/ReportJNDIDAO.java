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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdk.nashorn.internal.ir.RuntimeNode.Request;



public class ReportJNDIDAO implements Report_interface {
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			
			pstmt.setInt(1, reportVO.getRep_ob_no());
			pstmt.setInt(2, reportVO.getReporter_no());
			pstmt.setInt(3, reportVO.getReported_no());
			pstmt.setInt(4, reportVO.getRep_type_no());
			pstmt.setString(5, reportVO.getContent());
			pstmt.setDate(6, reportVO.getRep_date());
			pstmt.setInt(7, reportVO.getStatus());
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
	public void update(ReportVO reportVO) {
		Connection con=null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			
			pstmt.setInt(1, reportVO.getStatus());
			pstmt.setInt(2, reportVO.getRep_no());
			
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
	public void delete(Integer rep_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, rep_no);

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
	public ReportVO findByPrimaryKey(Integer rep_no) {
		ReportVO reportVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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
