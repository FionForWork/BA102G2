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

import com.calendar.model.*;
import com.rfq_detail.model.*;

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
			"INSERT INTO RESERVATION VALUES (LTRIM(TO_CHAR(RESERVATION_SQ.NEXTVAL,'0009')), ?, ?, ?, ?, ?, ?, ?, ?, 0)";
	private static final String UPDATE = 
			"UPDATE RESERVATION SET status=? where RES_NO = ?";
	private static final String UPDATESCORE = 
			"UPDATE RESERVATION SET status=?, score=? where RES_NO = ?";
	private static final String DELETE = 
			"DELETE FROM RESERVATION where RES_NO = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM RESERVATION where RES_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM RESERVATION order by RES_NO ";
	private static final String GET_ALLMEM_STMT = 
			"SELECT * FROM RESERVATION WHERE MEM_NO = ? order by res_date desc";
	private static final String GET_ALLCOM_STMT = 
			"SELECT * FROM RESERVATION WHERE COM_NO = ? order by res_date desc";
	private static final String GET_4MEM_STMT = 
			"SELECT * FROM RESERVATION WHERE MEM_NO = ? and status in ( ? , ? ) order by res_date desc";
	private static final String GET_4COM_STMT = 
			"SELECT * FROM RESERVATION WHERE COM_NO = ? and status in ( ? , ? ) order by res_date desc";
	private static final String GET_MEM_STMT = 
			"SELECT * FROM RESERVATION WHERE MEM_NO = ? and status = ? order by res_date desc";
	private static final String GET_COM_STMT = 
			"SELECT * FROM RESERVATION WHERE COM_NO = ? and status = ? order by res_date desc";
	private static final String GET_COM_MONTH_STMT = 
			"SELECT * FROM RESERVATION WHERE serv_date >= ? and serv_date <= ? and COM_NO = ?;";
	
	@Override
	public void insert(ReservationVO reservationVO,CalendarVO calendarVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			String cols[] = {"RES_NO"};
			
			pstmt = con.prepareStatement(INSERT, cols);
			
			pstmt.setString(1,reservationVO.getMem_no());
			pstmt.setString(2,reservationVO.getCom_no());
			pstmt.setTimestamp(3,reservationVO.getRes_date());
			pstmt.setTimestamp(4, reservationVO.getServ_date());
			pstmt.setString(5, reservationVO.getServ_no());
			pstmt.setString(6, reservationVO.getStype_no());
			pstmt.setInt(7, reservationVO.getPrice());
			pstmt.setString(8, reservationVO.getStatus());
			pstmt.executeUpdate();
			
			String RES_NO = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				RES_NO = rs.getString(1);
			}
			rs.close();
			
			calendarVO.setStatus(RES_NO);
			CalendarDAO calendarDAO = new CalendarDAO();
			calendarDAO.insertFromRes(calendarVO, con);
			
			con.commit();
			con.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
			if(con!= null){
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
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
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	// 未修改完
	@Override
	public void insert(ReservationVO reservationVO,CalendarVO calendarVO, RFQ_DetailVO rfq_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			String cols[] = {"RES_NO"};
			
			pstmt = con.prepareStatement(INSERT, cols);
			
			pstmt.setString(1,reservationVO.getMem_no());
			pstmt.setString(2,reservationVO.getCom_no());
			pstmt.setTimestamp(3,reservationVO.getRes_date());
			pstmt.setTimestamp(4, reservationVO.getServ_date());
			pstmt.setString(5, reservationVO.getServ_no());
			pstmt.setString(6, reservationVO.getStype_no());
			pstmt.setInt(7, reservationVO.getPrice());
			pstmt.setString(8, reservationVO.getStatus());
			pstmt.executeUpdate();
			
			String RES_NO = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				RES_NO = rs.getString(1);
			}
			rs.close();
			
			// 新增行事曆
			calendarVO.setStatus(RES_NO);
			CalendarDAO calendarDAO = new CalendarDAO();
			calendarDAO.insertFromRes(calendarVO, con);
			
			// 改變詢價狀態
			RFQ_DetailDAO rfq_DetailDAO = new RFQ_DetailDAO();
			rfq_DetailDAO.updateStatusFromRes(rfq_detailVO, con);
			
			con.commit();
			con.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
			if(con!= null){
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
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
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, reservationVO.getStatus());
			pstmt.setString(2, reservationVO.getRes_no());

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
				reservationVO.setServ_date(rs.getTimestamp("serv_date"));
				reservationVO.setServ_no(rs.getString("serv_no"));
				reservationVO.setStype_no(rs.getString("stype_no"));
				reservationVO.setPrice(rs.getInt("price"));
				reservationVO.setStatus(rs.getString("status"));
				reservationVO.setScore(rs.getInt("score"));
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
				reservationVO.setServ_date(rs.getTimestamp("serv_date"));
				reservationVO.setServ_no(rs.getString("serv_no"));
				reservationVO.setStype_no(rs.getString("stype_no"));
				reservationVO.setPrice(rs.getInt("price"));
				reservationVO.setStatus(rs.getString("status"));
				reservationVO.setScore(rs.getInt("score"));
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

	@Override
	public List<ReservationVO> getMemRes(String mem_no, String status) {
		List<ReservationVO> list = new ArrayList<ReservationVO>();
		ReservationVO reservationVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEM_STMT);
			pstmt.setString(1, mem_no);
			pstmt.setString(2, status);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reservationVO = new ReservationVO();
				reservationVO.setRes_no(rs.getString("res_no"));
				reservationVO.setMem_no(rs.getString("mem_no"));
				reservationVO.setCom_no(rs.getString("com_no"));
				reservationVO.setRes_date(rs.getTimestamp("res_date"));
				reservationVO.setServ_date(rs.getTimestamp("serv_date"));
				reservationVO.setServ_no(rs.getString("serv_no"));
				reservationVO.setStype_no(rs.getString("stype_no"));
				reservationVO.setPrice(rs.getInt("price"));
				reservationVO.setStatus(rs.getString("status"));
				reservationVO.setScore(rs.getInt("score"));
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

	@Override
	public List<ReservationVO> getComRes(String com_no, String status) {
		List<ReservationVO> list = new ArrayList<ReservationVO>();
		ReservationVO reservationVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COM_STMT);
			pstmt.setString(1, com_no);
			pstmt.setString(2, status);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reservationVO = new ReservationVO();
				reservationVO.setRes_no(rs.getString("res_no"));
				reservationVO.setMem_no(rs.getString("mem_no"));
				reservationVO.setCom_no(rs.getString("com_no"));
				reservationVO.setRes_date(rs.getTimestamp("res_date"));
				reservationVO.setServ_date(rs.getTimestamp("serv_date"));
				reservationVO.setServ_no(rs.getString("serv_no"));
				reservationVO.setStype_no(rs.getString("stype_no"));
				reservationVO.setPrice(rs.getInt("price"));
				reservationVO.setStatus(rs.getString("status"));
				reservationVO.setScore(rs.getInt("score"));
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

	@Override
	public void updateScore(ReservationVO reservationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESCORE);

			pstmt.setString(1, reservationVO.getStatus());
			pstmt.setInt(2, reservationVO.getScore());
			pstmt.setString(3, reservationVO.getRes_no());

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
	public List<ReservationVO> getAllMemRes(String mem_no) {
		List<ReservationVO> list = new ArrayList<ReservationVO>();
		ReservationVO reservationVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLMEM_STMT);
			pstmt.setString(1, mem_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reservationVO = new ReservationVO();
				reservationVO.setRes_no(rs.getString("res_no"));
				reservationVO.setMem_no(rs.getString("mem_no"));
				reservationVO.setCom_no(rs.getString("com_no"));
				reservationVO.setRes_date(rs.getTimestamp("res_date"));
				reservationVO.setServ_date(rs.getTimestamp("serv_date"));
				reservationVO.setServ_no(rs.getString("serv_no"));
				reservationVO.setStype_no(rs.getString("stype_no"));
				reservationVO.setPrice(rs.getInt("price"));
				reservationVO.setStatus(rs.getString("status"));
				reservationVO.setScore(rs.getInt("score"));
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
	@Override
	public List<ReservationVO> getMemRes(String mem_no, String status, String status2) {
		List<ReservationVO> list = new ArrayList<ReservationVO>();
		ReservationVO reservationVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_4MEM_STMT);
			pstmt.setString(1, mem_no);
			pstmt.setString(2, status);
			pstmt.setString(3, status2);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reservationVO = new ReservationVO();
				reservationVO.setRes_no(rs.getString("res_no"));
				reservationVO.setMem_no(rs.getString("mem_no"));
				reservationVO.setCom_no(rs.getString("com_no"));
				reservationVO.setRes_date(rs.getTimestamp("res_date"));
				reservationVO.setServ_date(rs.getTimestamp("serv_date"));
				reservationVO.setServ_no(rs.getString("serv_no"));
				reservationVO.setStype_no(rs.getString("stype_no"));
				reservationVO.setPrice(rs.getInt("price"));
				reservationVO.setStatus(rs.getString("status"));
				reservationVO.setScore(rs.getInt("score"));
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
	@Override
	public List<ReservationVO> getComRes(String com_no, String status, String status2) {
		List<ReservationVO> list = new ArrayList<ReservationVO>();
		ReservationVO reservationVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_4COM_STMT);
			pstmt.setString(1, com_no);
			pstmt.setString(2, status);
			pstmt.setString(3, status2);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reservationVO = new ReservationVO();
				reservationVO.setRes_no(rs.getString("res_no"));
				reservationVO.setMem_no(rs.getString("mem_no"));
				reservationVO.setCom_no(rs.getString("com_no"));
				reservationVO.setRes_date(rs.getTimestamp("res_date"));
				reservationVO.setServ_date(rs.getTimestamp("serv_date"));
				reservationVO.setServ_no(rs.getString("serv_no"));
				reservationVO.setStype_no(rs.getString("stype_no"));
				reservationVO.setPrice(rs.getInt("price"));
				reservationVO.setStatus(rs.getString("status"));
				reservationVO.setScore(rs.getInt("score"));
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
	@Override
	public List<ReservationVO> getAllComRes(String com_no) {
		List<ReservationVO> list = new ArrayList<ReservationVO>();
		ReservationVO reservationVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLCOM_STMT);
			pstmt.setString(1, com_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reservationVO = new ReservationVO();
				reservationVO.setRes_no(rs.getString("res_no"));
				reservationVO.setMem_no(rs.getString("mem_no"));
				reservationVO.setCom_no(rs.getString("com_no"));
				reservationVO.setRes_date(rs.getTimestamp("res_date"));
				reservationVO.setServ_date(rs.getTimestamp("serv_date"));
				reservationVO.setServ_no(rs.getString("serv_no"));
				reservationVO.setStype_no(rs.getString("stype_no"));
				reservationVO.setPrice(rs.getInt("price"));
				reservationVO.setStatus(rs.getString("status"));
				reservationVO.setScore(rs.getInt("score"));
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