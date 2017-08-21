package com.content.model;

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

public class ContentDAO implements ContentDAO_Interface {

	private static final String INSERT_SQL = "insert into content(cont_no,alb_no,upload_date,img,vdo) "
			+ "values(ltrim(To_char(cont_sq.nextval,'0009')),?,?,?,?)";
	private static final String DELETE_SQL = "delete from content where cont_no = ?";
	private static final String UPDATE_SQL = "update content set alb_no=?,upload_date=?,img=?,vdo=? where cont_no = ?";
	private static final String FIND_BY_PK = "select * from content where cont_no = ?";
	private static final String FIND_ALL_BY_ALB_NO = "select cont_no,alb_no,upload_date,img from content where alb_no = ? order by upload_date desc";
	private static final String FIND_ALL = "select * from content";
	private static final String COUNT_SQL = "select count(*) from content where alb_no = ? ";
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
	public String insertContent(ContentVO content) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String[] cols = { "cont_no" };
		String cont_no = "";
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(INSERT_SQL, cols);
			pstmt.setString(1, content.getAlb_no());
			pstmt.setTimestamp(2, content.getUpload_date());
			pstmt.setBytes(3, content.getImg());
			pstmt.setBytes(4, content.getVdo());

			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				cont_no = rs.getString(1);
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
		return cont_no;
	}

	
	@Override
	public void deleteContent(String cont_no) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(DELETE_SQL);
			pstmt.setString(1, cont_no);
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
	public void updateContent(ContentVO content) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, content.getAlb_no());
			pstmt.setTimestamp(2, content.getUpload_date());
			pstmt.setBytes(3, content.getImg());
			pstmt.setBytes(4, content.getVdo());
			pstmt.setString(5, content.getCont_no());
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
	public ContentVO findContentByPK(String cont_no) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ContentVO content = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, cont_no);
			rs = pstmt.executeQuery();
			rs.next();
			content = new ContentVO();
			content.setCont_no(rs.getString(1));
			content.setAlb_no(rs.getString(2));
			content.setUpload_date(rs.getTimestamp(3));
			content.setImg(rs.getBytes(4));
			content.setVdo(rs.getBytes(5));

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
		return content;
	}

	@Override
	public List<ContentVO> findContentsByAlbNo(String alb_no) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ContentVO content = null;
		List<ContentVO> contentList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_ALL_BY_ALB_NO);
			pstmt.setString(1, alb_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				content = new ContentVO();
				content.setCont_no(rs.getString(1));
				content.setAlb_no(rs.getString(2));
				content.setUpload_date(rs.getTimestamp(3));
				content.setImg(rs.getBytes(4));
				contentList.add(content);
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
		return contentList;
	}

	@Override
	public List<ContentVO> findAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ContentVO content = null;
		List<ContentVO> contentList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(FIND_ALL);
			while (rs.next()) {
				content = new ContentVO();
				content.setCont_no(rs.getString(1));
				content.setAlb_no(rs.getString(2));
				content.setUpload_date(rs.getTimestamp(3));
				content.setImg(rs.getBytes(4));
				content.setVdo(rs.getBytes(5));
				contentList.add(content);
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
		return contentList;
	}


	@Override
	public int countContentsInSingleAlbum(String alb_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfCont = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(COUNT_SQL);
			pstmt.setString(1, alb_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfCont = rs.getInt(1);
			}
		} catch (Exception e) {
			
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
		return numberOfCont;

	}
}
