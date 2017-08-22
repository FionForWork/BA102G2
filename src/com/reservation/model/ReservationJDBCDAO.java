package com.reservation.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.calendar.model.CalendarVO;
import com.rfq_detail.model.RFQ_DetailVO;

public class ReservationJDBCDAO implements ReservationDAO_Interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "group2";
	String passwd = "group2";
	
	private static final String INSERT = 
			"INSERT INTO RESERVATION VALUES (LTRIM(TO_CHAR(RESERVATION_SQ.NEXTVAL,'0009')), ?, ?, ?, ?, ?, ?, ?, '0', 0)";
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
	private static final String GET_MEM_STMT = 
			"SELECT * FROM RESERVATION WHERE MEM_NO = ?";
	private static final String GET_COM_STMT = 
			"SELECT * FROM RESERVATION WHERE COM_NO = ?";
	private static final String GET_COM_DISTINCT_MEM_NO_STMT = "SELECT DISTINCT MEM_NO FROM RESERVATION WHERE COM_NO = ?";
	private static final String GETDELETERES =
			"select res_no from reservation where sysdate-res_date > 3 and status = '0'";
	
	@Override
	public void insert(ReservationVO reservationVO,CalendarVO calendarVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1,reservationVO.getMem_no());
			pstmt.setString(2,reservationVO.getCom_no());
			pstmt.setTimestamp(3,reservationVO.getRes_date());
			pstmt.setTimestamp(4, reservationVO.getServ_date());
			pstmt.setString(5, reservationVO.getServ_no());
			pstmt.setString(6, reservationVO.getStype_no());
			pstmt.setInt(7, reservationVO.getPrice());
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
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, reservationVO.getStatus());
			pstmt.setString(2, reservationVO.getRes_no());

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
				reservationVO.setServ_date(rs.getTimestamp("serv_date"));
				reservationVO.setServ_no(rs.getString("serv_no"));
				reservationVO.setStype_no(rs.getString("stype_no"));
				reservationVO.setPrice(rs.getInt("price"));
				reservationVO.setStatus(rs.getString("status"));
				reservationVO.setScore(rs.getInt("score"));
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
				reservationVO.setServ_date(rs.getTimestamp("serv_date"));
				reservationVO.setServ_no(rs.getString("serv_no"));
				reservationVO.setStype_no(rs.getString("stype_no"));
				reservationVO.setPrice(rs.getInt("price"));
				reservationVO.setStatus(rs.getString("status"));
				reservationVO.setScore(rs.getInt("score"));
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
	
	@Override
	public List<ReservationVO> getMemRes(String mem_no, String status) {
		List<ReservationVO> list = new ArrayList<ReservationVO>();
		ReservationVO reservationVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MEM_STMT);
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

	@Override
	public List<ReservationVO> getComRes(String com_no, String status) {
		List<ReservationVO> list = new ArrayList<ReservationVO>();
		ReservationVO reservationVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_COM_STMT);
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
	
	@Override
	public void updateScore(ReservationVO reservationVO) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args[]){
		
		ReservationJDBCDAO dao = new ReservationJDBCDAO();
		
//		ReservationVO reservationVO = new ReservationVO();
//		reservationVO.setMem_no("1001");
//		reservationVO.setCom_no("2001");
//		Timestamp t = new Timestamp(System.currentTimeMillis()); 
//		reservationVO.setRes_date(t);
//		reservationVO.setServ_date(t);
//		reservationVO.setServ_no("0001");
//		reservationVO.setStype_no("0001");
//		reservationVO.setPrice(33500);
//		dao.insert(reservationVO);
		
//		ReservationVO reservationVO = new ReservationVO();
//		reservationVO.setRes_no("0001");
//		reservationVO.setStatus("1");
//		dao.update(reservationVO);
		
//		dao.delete("0001");
		
//		ReservationVO reservationVO = dao.findByPK("0002");
//		System.out.println(reservationVO.getRes_no());
//		System.out.println(reservationVO.getMem_no());
//		System.out.println(reservationVO.getCom_no());
//		System.out.println(reservationVO.getRes_date());
//		System.out.println(reservationVO.getServ_date());
//		System.out.println(reservationVO.getServ_no());
//		System.out.println(reservationVO.getStype_no());
//		System.out.println(reservationVO.getPrice());
//		System.out.println(reservationVO.getStatus());
//		System.out.println(reservationVO.getScore());
		
		List<String> list = dao.getDeleteRes();
		for(String res_no : list){
			System.out.println(res_no);
		}

		
	}

	@Override
	public void insert(ReservationVO reservationVO, CalendarVO calendarVO, RFQ_DetailVO rfq_detailVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ReservationVO> getAllMemRes(String mem_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReservationVO> getMemRes(String mem_no, String status, String status2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReservationVO> getComRes(String com_no, String status, String status2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReservationVO> getAllComRes(String com_no) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<String> getComResDistinctMemNO(String com_no) {
		List<String> list = new ArrayList<String>();
		ReservationVO reservationVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_COM_DISTINCT_MEM_NO_STMT);
			pstmt.setString(1, com_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				list.add(rs.getString(1));
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

	@Override
	public List<String> getDeleteRes() {
		List<String> list = new ArrayList<String>();
		ReservationVO reservationVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETDELETERES);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				list.add(rs.getString("res_no"));
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

	@Override
	public List<ReservationVO> getMenMonthCal(int year, int month, int dayNum, String men_no) {
		// TODO Auto-generated method stub
		return null;
	}


}
