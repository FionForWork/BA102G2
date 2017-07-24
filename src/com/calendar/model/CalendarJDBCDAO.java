package com.calendar.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CalendarJDBCDAO implements CalendarDAO_Interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "group2";
	String passwd = "group2";
	
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1,calendarVO.getCom_no());
			pstmt.setString(2, calendarVO.getContent());
			pstmt.setTimestamp(3, calendarVO.getStart_time());
			pstmt.setTimestamp(4, calendarVO.getEnd_time());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, calendarVO.getContent());
			pstmt.setTimestamp(2, calendarVO.getStart_time());
			pstmt.setTimestamp(3, calendarVO.getEnd_time());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, cal_no);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	
public static void main(String args[]){
		
		CalendarJDBCDAO dao = new CalendarJDBCDAO();
		
//		CalendarVO calendarVO = new CalendarVO();
//		calendarVO.setCom_no("2001");
//		calendarVO.setContent("拍婚紗拍婚紗拍婚紗拍婚紗拍婚紗拍婚紗拍婚紗拍婚紗拍婚紗拍婚紗拍婚紗拍婚紗拍婚紗");
//		Timestamp t = new Timestamp(System.currentTimeMillis());
//		calendarVO.setStart_time(t);
//		calendarVO.setEnd_time(t);
//		dao.insert(calendarVO);
		
//		dao.delete("0001");
		
//		CalendarVO calendarVO = dao.findByPK("0001");
//		System.out.println(calendarVO.getCal_no());
//		System.out.println(calendarVO.getCom_no());
//		System.out.println(calendarVO.getContent());
//		System.out.println(calendarVO.getStart_time());
//		System.out.println(calendarVO.getEnd_time());
		
		List<CalendarVO> list = dao.getAll();
		for(CalendarVO calendarVO : list){
			System.out.println(calendarVO.getCal_no());
			System.out.println(calendarVO.getCom_no());
			System.out.println(calendarVO.getContent());
			System.out.println(calendarVO.getStart_time());
			System.out.println(calendarVO.getEnd_time());
		}

	
	}

}
