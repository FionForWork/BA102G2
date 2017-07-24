package com.reservation.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReservationJDBCDAO implements ReservationDAO_Interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "group2";
	String passwd = "group2";
	
	private static final String INSERT = 
			"INSERT INTO RESERVATION VALUES (LTRIM(TO_CHAR(RESNO_SQ.NEXTVAL,'0009')), ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE RESERVATION SET NAME=? where RES_NO = ?";
	private static final String DELETE = 
			"DELETE FROM RESERVATION where RES_NO = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM RESERVATION where RES_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM RESERVATION order by RES_NO ";
	
	@Override
	public void insert(ReservationVO reservationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1,reservationVO.getMem_no());
			pstmt.setString(2,reservationVO.getCom_no());
			pstmt.setTimestamp(3,reservationVO.getRes_date());
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
	public void update(ReservationVO reservationVO) {
		
	}

	@Override
	public void delete(String res_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, res_no);

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
	public ReservationVO findByPK(String res_no) {
		ReservationVO reservationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, res_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				reservationVO = new ReservationVO();
				reservationVO.setRes_no(rs.getString("res_no"));
				reservationVO.setMem_no(rs.getString("mem_no"));
				reservationVO.setCom_no(rs.getString("com_no"));
				reservationVO.setRes_date(rs.getTimestamp("res_date"));
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
		return reservationVO;
	}
	

	@Override
	public List<ReservationVO> getAll() {
		List<ReservationVO> list = new ArrayList<ReservationVO>();
		ReservationVO reservationVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				reservationVO = new ReservationVO();
				reservationVO.setRes_no(rs.getString("res_no"));
				reservationVO.setMem_no(rs.getString("mem_no"));
				reservationVO.setCom_no(rs.getString("com_no"));
				reservationVO.setRes_date(rs.getTimestamp("res_date"));
				list.add(reservationVO);
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
		
		ReservationJDBCDAO dao = new ReservationJDBCDAO();
		
//		ReservationVO reservationVO = new ReservationVO();
//		reservationVO.setMem_no("1001");
//		reservationVO.setCom_no("2001");
//		Timestamp t = new Timestamp(System.currentTimeMillis()); 
//		reservationVO.setRes_date(t);
//		dao.insert(reservationVO);
		
//		dao.delete("0001");
		
//		ReservationVO reservationVO = dao.findByPK("0001");
//		System.out.println(reservationVO.getRes_no());
//		System.out.println(reservationVO.getMem_no());
//		System.out.println(reservationVO.getCom_no());
//		System.out.println(reservationVO.getRes_date());
		
//		List<ReservationVO> list = dao.getAll();
//		for(ReservationVO reservationVO : list){
//			System.out.println(reservationVO.getRes_no());
//			System.out.println(reservationVO.getMem_no());
//			System.out.println(reservationVO.getCom_no());
//			System.out.println(reservationVO.getRes_date());
//		}

	
	}
}
