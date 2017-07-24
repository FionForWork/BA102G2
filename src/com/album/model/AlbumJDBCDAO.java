package com.album.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.sql.DataSource;

public class AlbumJDBCDAO implements AlbumDAO_Interface {

	private static final String CREATE_SQL = "insert into album(alb_no,mem_no,name,cover,create_date) values(ltrim(To_char(alb_sq.nextval,'0009')),?,?,?,?)";
	private static final String DELETE_SQL = "delete from album where alb_no = ?";
	private static final String UPDATE_SQL = "update album set mem_no=?,name=?,cover=?,create_date=? where alb_no = ?";
	private static final String FIND_BY_PK = "select * from album where alb_no = ?";
	private static final String FIND_BY_MEM_NO = "select * from album where mem_no = ?";
	private static final String FIND_ALL = "select * from album";

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "model";
	private static final String PWD = "model";

	@Override
	public String createAlbum(AlbumVO album) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String[] cols = { "alb_no" };
		String alb_no ="";
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PWD);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(CREATE_SQL, cols);
			pstmt.setString(1, album.getMem_no());
			pstmt.setString(2, album.getName());
			pstmt.setBytes(3, album.getCover());
			pstmt.setTimestamp(4, album.getCreate_date());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				alb_no = rs.getString(1);
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
		return alb_no;	
		
	}

	@Override
	public void deleteAlbum(String alb_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PWD);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(DELETE_SQL);
			pstmt.setString(1, alb_no);
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
	public void updateAlbum(AlbumVO album) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PWD);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, album.getMem_no());
			pstmt.setString(2, album.getName());
			pstmt.setBytes(3, album.getCover());
			pstmt.setTimestamp(4, album.getCreate_date());
			pstmt.setString(5, album.getAlb_no());
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
	public AlbumVO findAlbumByPK(String alb_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AlbumVO album = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PWD);
			pstmt = conn.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, alb_no);
			rs = pstmt.executeQuery();
			rs.next();
			album = new AlbumVO(rs.getString("alb_no"), rs.getString("mem_no"), rs.getString("name"),rs.getBytes("cover"), rs.getTimestamp("create_date"));
			
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
		return album;
	}

	@Override
	public List<AlbumVO> findAlbumsByMemNo(String mem_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AlbumVO album = null;
		List<AlbumVO> albumList = new ArrayList<>();
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PWD);
			pstmt = conn.prepareStatement(FIND_BY_MEM_NO);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				album = new AlbumVO(rs.getString("alb_no"), rs.getString("mem_no"), rs.getString("name"),
						rs.getBytes("cover"), rs.getTimestamp("create_date"));
				albumList.add(album);
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
		return albumList;
	}

	@Override
	public List<AlbumVO> findAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		AlbumVO album = null;
		List<AlbumVO> albumList = new ArrayList<>();
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PWD);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(FIND_ALL);
			while (rs.next()) {
				album = new AlbumVO(rs.getString("alb_no"), rs.getString("mem_no"), rs.getString("name"),
						rs.getBytes("cover"), rs.getTimestamp("create_date"));
				albumList.add(album);
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
		return albumList;
	}
}
