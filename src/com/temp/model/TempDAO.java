package com.temp.model;

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

public class TempDAO implements TempDAO_Interface{

	private static final String CREATE_SQL = "insert into temp(temp_no,com_no,mem_no,name,create_date,available,status) values(ltrim(To_char(temp_sq.nextval,'0009')),?,?,?,?,?,?)";
	private static final String DELETE_SQL = "delete from temp where temp_no = ?";
	private static final String UPDATE_SQL = "update temp set com_no=?,mem_no=? ,name=?,create_date=?,available=?,status=? where temp_no = ?";
	private static final String FIND_BY_PK = "select * from temp where temp_no = ?";
	private static final String FIND_BY_MEM_NO = "select * from temp where mem_no = ? order by create_date desc";
	private static final String FIND_BY_COM_NO = "select * from temp where com_no = ? order by create_date desc";
	private static final String FIND_ALL = "select * from temp";

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
	public String createTemp(TempVO temp) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String[] cols = { "temp_no" };
		String temp_no ="";
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(CREATE_SQL, cols);
			pstmt.setString(1, temp.getCom_no());
			pstmt.setString(2, temp.getMem_no());
			pstmt.setString(3, temp.getName());
			pstmt.setTimestamp(4, temp.getCreate_date());
			pstmt.setInt(5, temp.getAvailable());
			pstmt.setString(6, temp.getStatus());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				temp_no = rs.getString(1);
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
		return temp_no;
	}

	@Override
	public void deleteTemp(String temp_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(DELETE_SQL);
			pstmt.setString(1, temp_no);
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
	public void updateTemp(TempVO temp) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, temp.getCom_no());
			pstmt.setString(2, temp.getMem_no());
			pstmt.setString(3, temp.getName());
			pstmt.setTimestamp(4, temp.getCreate_date());
			pstmt.setInt(5, temp.getAvailable());
			pstmt.setString(6, temp.getStatus());
			pstmt.setString(7, temp.getTemp_no());
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
	public TempVO findTempByPK(String temp_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TempVO temporary = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, temp_no);
			rs = pstmt.executeQuery();
			rs.next();
			temporary = new TempVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5),rs.getInt(6),rs.getString(7));
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
		return temporary;
	}

	@Override
	public List<TempVO> findTempsByMemNo(String mem_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TempVO temp = null;
		List<TempVO> tempList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_BY_MEM_NO);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				temp = new TempVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5),rs.getInt(6),rs.getString(7));
				tempList.add(temp);
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
		return tempList;
	}

	@Override
	public List<TempVO> findTempsByComNo(String com_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TempVO temp = null;
		List<TempVO> tempList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_BY_COM_NO);
			pstmt.setString(1, com_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				temp = new TempVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5),rs.getInt(6),rs.getString(7));
				tempList.add(temp);
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
		return tempList;
	}

	@Override
	public List<TempVO> findAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		TempVO temp = null;
		List<TempVO> tempList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(FIND_ALL);
			while (rs.next()) {
				temp = new TempVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5),rs.getInt(6),rs.getString(7));
				tempList.add(temp);
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
		return tempList;
	}
}
