package com.calendar.model;

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

public class CalendarDAO implements CalendarDAO_Interface {
	
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
			"INSERT INTO CALENDAR VALUES (LTRIM(TO_CHAR(CAL_SQ.NEXTVAL,'0009')), ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE CALENDAR SET CONTENT=?, START_TIME=?, END_TIME=? where CAL_NO = ?";
	private static final String DELETE = 
			"DELETE FROM CALENDAR where CAL_NO = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM CALENDAR where CAL_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM CALENDAR order by CAL_NO ";
	
	@Override
	public void insert(CalendarVO calendarVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1,calendarVO.getCom_no());
			pstmt.setString(2, calendarVO.getContent());
			pstmt.setTimestamp(3, calendarVO.getStart_time());
			pstmt.setTimestamp(4, calendarVO.getEnd_time());
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
	public void update(CalendarVO calendarVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, calendarVO.getContent());
			pstmt.setTimestamp(2, calendarVO.getStart_time());
			pstmt.setTimestamp(3, calendarVO.getEnd_time());

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
	public void delete(String cal_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, cal_no);

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
	public CalendarVO findByPK(String cal_no) {
		CalendarVO calendarVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, cal_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				calendarVO = new CalendarVO();
				calendarVO.setCal_no(rs.getString("cal_no"));
				calendarVO.setCom_no(rs.getString("com_no"));
				calendarVO.setContent(rs.getString("content"));
				calendarVO.setStart_time(rs.getTimestamp("start_time"));
				calendarVO.setEnd_time(rs.getTimestamp("end_time"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		return calendarVO;
	}

	@Override
	public List<CalendarVO> getAll() {
		List<CalendarVO> list = new ArrayList<CalendarVO>();
		CalendarVO calendarVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				calendarVO = new CalendarVO();
				calendarVO.setCal_no(rs.getString("cal_no"));
				calendarVO.setCom_no(rs.getString("com_no"));
				calendarVO.setContent(rs.getString("content"));
				calendarVO.setStart_time(rs.getTimestamp("start_time"));
				calendarVO.setEnd_time(rs.getTimestamp("end_time"));
				list.add(calendarVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
