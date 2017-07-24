package com.reservation.model;

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

public class ReservationDAO implements ReservationDAO_Interface {
	
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1,reservationVO.getMem_no());
			pstmt.setString(2,reservationVO.getCom_no());
			pstmt.setTimestamp(3,reservationVO.getRes_date());
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
	public void update(ReservationVO reservationVO) {
		
	}

	@Override
	public void delete(String res_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

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

	@Override
	public ReservationVO findByPK(String res_no) {
		ReservationVO reservationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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
