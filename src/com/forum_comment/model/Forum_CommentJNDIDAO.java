package com.forum_comment.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.art_type.model.Art_TypeJDBCDAO;
import com.art_type.model.Art_TypeVO;

public class Forum_CommentJNDIDAO implements Forum_Comment_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO FORUM_COMMENT (FMC_NO,ART_NO,SPEAKER_NO,CONT,FMC_DATE) VALUES ('6'||lpad(fmc_no_sq.NEXTVAL,3,'0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM FORUM_COMMENT order by FMC_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM FORUM_COMMENT where FMC_NO = ?";
	private static final String DELETE = "DELETE FROM FORUM_COMMENT where FMC_NO = ?";
	private static final String UPDATE = "UPDATE FORUM_COMMENT set  ART_NO=?, SPEAKER_NO=?,CONT=?,FMC_DATE=? where fmc_no=?";
	private static final String GET_ONE_ALL = "select * from ( select * from forum_comment where ART_no = ? order by fmc_no desc ) where  rownum <=1";
	private static final String GET_art_no_ALL ="SELECT count(art_no) FROM forum_comment where art_no=?";

	@Override
	public void insert(Forum_CommentVO forum_CommentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forum_CommentVO.getArt_no());
			pstmt.setInt(2, forum_CommentVO.getSpeaker_no());
			pstmt.setString(3, forum_CommentVO.getCont());
			pstmt.setTimestamp(4, forum_CommentVO.getFmc_date());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Forum_CommentVO forum_CommentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, forum_CommentVO.getArt_no());
			pstmt.setInt(2, forum_CommentVO.getSpeaker_no());
			pstmt.setString(3, forum_CommentVO.getCont());
			pstmt.setTimestamp(4, forum_CommentVO.getFmc_date());
			pstmt.setInt(5, forum_CommentVO.getFmc_no());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer fmc_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, fmc_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public Forum_CommentVO findByPrimaryKey(Integer fmc_no) {
	
		Forum_CommentVO forum_CommentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, fmc_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				forum_CommentVO = new Forum_CommentVO();
				forum_CommentVO.setFmc_no(rs.getInt("fmc_no"));
				forum_CommentVO.setArt_no(rs.getInt("art_no"));
				forum_CommentVO.setSpeaker_no(rs.getInt("speaker_no"));
				forum_CommentVO.setCont(rs.getString("cont"));
				forum_CommentVO.setFmc_date(rs.getTimestamp("fmc_date"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return forum_CommentVO;
	}

	@Override
	public List<Forum_CommentVO> getAll() {
		List<Forum_CommentVO> list = new ArrayList<Forum_CommentVO>();
		Forum_CommentVO forum_CommentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				forum_CommentVO = new Forum_CommentVO();
				forum_CommentVO.setFmc_no(rs.getInt("fmc_no"));
				forum_CommentVO.setArt_no(rs.getInt("art_no"));
				forum_CommentVO.setSpeaker_no(rs.getInt("speaker_no"));
				forum_CommentVO.setCont(rs.getString("cont"));
				forum_CommentVO.setFmc_date(rs.getTimestamp("fmc_date"));

				list.add(forum_CommentVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

	@Override
	public Forum_CommentVO getOneAll(Integer art_no) {
		Forum_CommentVO forum_CommentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ALL);

			pstmt.setInt(1, art_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				forum_CommentVO = new Forum_CommentVO();
				forum_CommentVO.setFmc_no(rs.getInt("fmc_no"));
				forum_CommentVO.setArt_no(rs.getInt("art_no"));
				forum_CommentVO.setSpeaker_no(rs.getInt("speaker_no"));
				forum_CommentVO.setCont(rs.getString("cont"));
				forum_CommentVO.setFmc_date(rs.getTimestamp("fmc_date"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return forum_CommentVO;
	}
	@Override
	public Forum_CommentVO getArt_no_All(Integer art_no) {
		Forum_CommentVO forum_CommentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_art_no_ALL);

			pstmt.setInt(1, art_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				forum_CommentVO = new Forum_CommentVO();
				
				forum_CommentVO.setArt_no(rs.getInt("count(art_no)"));
				

			}

		} catch (SQLException  se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return forum_CommentVO;
	}

}
