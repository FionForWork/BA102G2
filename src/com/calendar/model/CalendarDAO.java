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
			"INSERT INTO CALENDAR VALUES (LTRIM(TO_CHAR(CALENDAR_SQ.NEXTVAL,'0009')), ?, ?, ?, ?)";
	private static final String UPDATE_DATE = 
			"UPDATE CALENDAR SET cal_date=? where CAL_NO = ?";
	private static final String DELETE = 
			"DELETE FROM CALENDAR where CAL_NO = ?";
	private static final String DELETERES = 
			"DELETE FROM CALENDAR where STATUS = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM CALENDAR where CAL_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM CALENDAR order by CAL_NO ";
	private static final String GET_MONTH_STMT = 
			"SELECT * FROM CALENDAR where cal_date >= ? and cal_date <= ? and com_no = ? ";
	private static final String getOneByComNOandDate = 
			"SELECT * FROM CALENDAR where com_no = ? and to_char(cal_date,'YYYY-MM-DD') = ? ";
	
	@Override
	public void insert(CalendarVO calendarVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1,calendarVO.getCom_no());
			pstmt.setString(2, calendarVO.getContent());
			pstmt.setTimestamp(3, calendarVO.getCal_date());
			pstmt.setString(4, calendarVO.getStatus());
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
	public void insertFromRes(CalendarVO calendarVO, Connection con) {
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1,calendarVO.getCom_no());
			pstmt.setString(2, calendarVO.getContent());
			pstmt.setTimestamp(3, calendarVO.getCal_date());
			pstmt.setString(4, calendarVO.getStatus());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			if(con != null){
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateDate(CalendarVO calendarVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_DATE);

			pstmt.setTimestamp(1, calendarVO.getCal_date());
			pstmt.setString(2, calendarVO.getCal_no());

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
				calendarVO.setCal_date(rs.getTimestamp("cal_date"));
				calendarVO.setStatus(rs.getString("status"));
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
				calendarVO.setCal_date(rs.getTimestamp("cal_date"));
				calendarVO.setStatus(rs.getString("status"));
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

	@Override
	public List<CalendarVO> getMonthCal(int year, int month,int dayNum, String com_no) {
		List<CalendarVO> list = new ArrayList<CalendarVO>();
		CalendarVO calendarVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String firstDay = "01-"+month+"月-"+year;
		String lastDay = dayNum+"-"+month+"月-"+year;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MONTH_STMT);
			pstmt.setString(1, firstDay);
			pstmt.setString(2, lastDay);
			pstmt.setString(3, com_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				calendarVO = new CalendarVO();
				calendarVO.setCal_no(rs.getString("cal_no"));
				calendarVO.setCom_no(rs.getString("com_no"));
				calendarVO.setContent(rs.getString("content"));
				calendarVO.setCal_date(rs.getTimestamp("cal_date"));
				calendarVO.setStatus(rs.getString("status"));
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

	@Override
	public CalendarVO findByNoandDate(String com_no, String cal_date) {
		CalendarVO calendarVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(getOneByComNOandDate);

			pstmt.setString(1, com_no);
			pstmt.setString(2, cal_date);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				calendarVO = new CalendarVO();
				calendarVO.setCal_no(rs.getString("cal_no"));
				calendarVO.setCom_no(rs.getString("com_no"));
				calendarVO.setContent(rs.getString("content"));
				calendarVO.setCal_date(rs.getTimestamp("cal_date"));
				calendarVO.setStatus(rs.getString("status"));
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
	public void deleteRes(String res_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETERES);

			pstmt.setString(1, res_no);

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

}
