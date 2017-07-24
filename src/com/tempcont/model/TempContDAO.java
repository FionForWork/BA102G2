package com.tempcont.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TempContDAO implements TempContDAO_Interface {

	private static final String INSERT_SQL = "insert into tempcont(tcont_no,temp_no,upload_date,img,vdo) "
			+ "values(ltrim(To_char(tempcont_sq.nextval,'0009')),?,?,?,?)";
	private static final String DELETE_SQL = "delete from tempcont where tcont_no = ?";
	private static final String UPDATE_SQL = "update tempcont set temp_no=?,upload_date=?,img=?,vdo=? where tcont_no = ?";
	private static final String FIND_BY_PK = "select * from tempcont where tcont_no = ?";
	private static final String FIND_ALL_BY_TEMP_NO = "select * from tempcont where temp_no = ?";
	private static final String FIND_ALL = "select * from tempcont";

	private static DataSource ds = null;

	static {
		Context ctx;
		try {
			ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String insertTempCont(TempContVO tempcont) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String[] cols = { "tcont_no" };
		String tcont_no = "";
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(INSERT_SQL, cols);
			pstmt.setString(1, tempcont.getTemp_no());
			pstmt.setTimestamp(2, tempcont.getUpload_date());
			pstmt.setBytes(3, tempcont.getImg());
			pstmt.setBytes(4, tempcont.getVdo());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				tcont_no = rs.getString(1);
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
		return tcont_no;
	}

	@Override
	public void deleteTempCont(String tcont_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(DELETE_SQL);
			pstmt.setString(1, tcont_no);
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
	public void updateTempCont(TempContVO tempcont) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, tempcont.getTemp_no());
			pstmt.setTimestamp(2, tempcont.getUpload_date());
			pstmt.setBytes(3, tempcont.getImg());
			pstmt.setBytes(4, tempcont.getVdo());
			pstmt.setString(5, tempcont.getTcont_no());
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
	public TempContVO findTempContByPK(String tcont_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TempContVO tempcont = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, tcont_no);
			rs = pstmt.executeQuery();
			rs.next();
			tempcont = new TempContVO();
			tempcont.setTcont_no(rs.getString(1));
			tempcont.setTemp_no(rs.getString(2));
			tempcont.setUpload_date(rs.getTimestamp(3));
			tempcont.setImg(rs.getBytes(4));
			tempcont.setVdo(rs.getBytes(5));

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
		return tempcont;
	}

	@Override
	public List<TempContVO> findTempContsByTempNo(String temp_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TempContVO tempcont = null;
		List<TempContVO> tempcontList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_ALL_BY_TEMP_NO);
			pstmt.setString(1, temp_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tempcont = new TempContVO();
				tempcont.setTcont_no(rs.getString(1));
				tempcont.setTemp_no(rs.getString(2));
				tempcont.setUpload_date(rs.getTimestamp(3));
				tempcont.setImg(rs.getBytes(4));
				tempcont.setVdo(rs.getBytes(5));
				tempcontList.add(tempcont);
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
		return tempcontList;
	}

	@Override
	public List<TempContVO> findAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		TempContVO tempcont = null;
		List<TempContVO> tempcontList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(FIND_ALL);
			while (rs.next()) {
				tempcont = new TempContVO();
				tempcont.setTcont_no(rs.getString(1));
				tempcont.setTemp_no(rs.getString(2));
				tempcont.setUpload_date(rs.getTimestamp(3));
				tempcont.setImg(rs.getBytes(4));
				tempcont.setVdo(rs.getBytes(5));
				tempcontList.add(tempcont);
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
		return tempcontList;
	}
}
