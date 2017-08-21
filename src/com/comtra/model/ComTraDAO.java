package com.comtra.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ComTraDAO implements ComTraDAO_Interface {

	
	private static final String INSERT_SQL = "insert into comtra(comtra_no,com_no,mem_no,tracking_date) values(ltrim(To_char(comtra_sq.nextval,'0009')),?,?,?)";
	private static final String DELETE_SQL = "delete from comtra where comtra_no = ?";
	private static final String UPDATE_SQL = "update comtra set com_no=?,mem_no=?,tracking_date=? where comtra_no = ?";
	private static final String FIND_BY_PK = "select * from comtra where comtra_no = ?";
	private static final String FIND_BY_MEM_NO = "select * from comtra where mem_no=? order by tracking_date desc";
	private static final String FIND_ALL = "select * from comtra";
	private static final String FIND_COM_NO_LIST = "select com_no from comtra where mem_no = ?";
	private static final String FIND_BY_COM_NO_AND_MEM_NO = "select * from comtra where com_no = ? and mem_no = ?";

	private static DataSource ds = null;
	
	static{
		Context ctx;
		try {
			ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public String insertComTra(ComTraVO comTra) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String[] cols = { "comtra_no" };
		String comtra_no ="";
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(INSERT_SQL, cols);
			pstmt.setString(1, comTra.getCom_no());
			pstmt.setString(2, comTra.getMem_no());
			pstmt.setTimestamp(3, comTra.getTracking_date());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				comtra_no = rs.getString(1);
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return comtra_no;
	}

	@Override
	public void deleteComTra(String comtra_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(DELETE_SQL);
			pstmt.setString(1, comtra_no);
			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void updateComTra(ComTraVO comTra) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, comTra.getCom_no());
			pstmt.setString(2, comTra.getMem_no());
			pstmt.setTimestamp(3, comTra.getTracking_date());
			pstmt.setString(4, comTra.getComtra_no());
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ComTraVO findComTraByPK(String comtra_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ComTraVO comTra = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, comtra_no);
			rs = pstmt.executeQuery();
			rs.next();
			comTra = new ComTraVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getTimestamp(4));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return comTra;
	}

	@Override
	public List<ComTraVO> findComTraByMemNo(String mem_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ComTraVO comTra = null;
		List<ComTraVO> comTraList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_BY_MEM_NO);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				comTra = new ComTraVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getTimestamp(4));
				comTraList.add(comTra);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return comTraList;
	}

	@Override
	public List<ComTraVO> findAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ComTraVO comTra = null;
		List<ComTraVO> comTraList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(FIND_ALL);
			while (rs.next()) {
				comTra = new ComTraVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getTimestamp(4));
				comTraList.add(comTra);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return comTraList;
	}

	
	@Override
	public List<String> getComNoListByMemNo(String mem_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> comNoList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_COM_NO_LIST);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				comNoList.add(rs.getString(1));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return comNoList;

	}

	@Override
	public ComTraVO getComTraByComNoAndMemNo(String com_no, String mem_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ComTraVO comTra = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_BY_COM_NO_AND_MEM_NO);
			pstmt.setString(1, com_no);
			pstmt.setString(2, mem_no);
			rs = pstmt.executeQuery();
			rs.next();
			comTra = new ComTraVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getTimestamp(4));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return comTra;
	}

	
}
