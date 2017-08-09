package com.forum_comment.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.art_type.model.Art_TypeJDBCDAO;
import com.art_type.model.Art_TypeVO;
import com.sun.jmx.snmp.SnmpStringFixed;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class Forum_CommentJDBCDAO implements Forum_Comment_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "home";
	String passwd = "123";

	private static final String INSERT_STMT = "INSERT INTO FORUM_COMMENT (FMC_NO,ART_NO,SPEAKER_NO,CONT,FMC_DATE) VALUES ('6'||lpad(fmc_no_sq.NEXTVAL,3,'0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM FORUM_COMMENT order by FMC_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM FORUM_COMMENT where FMC_NO = ?";
	private static final String DELETE = "DELETE FROM FORUM_COMMENT where FMC_NO = ?";
	private static final String UPDATE = "UPDATE FORUM_COMMENT set  ART_NO=?, SPEAKER_NO=?,CONT=?,FMC_DATE=? where fmc_no=?";
	private static final String GET_ONE_ALL = "select * from ( select * from forum_comment where ART_no = ? order by fmc_no desc ) where  rownum <=1";

	@Override
	public void insert(Forum_CommentVO forum_CommentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forum_CommentVO.getArt_no());
			pstmt.setInt(2, forum_CommentVO.getSpeaker_no());
			pstmt.setString(3, forum_CommentVO.getCont());
			pstmt.setTimestamp(4, forum_CommentVO.getFmc_date());

			pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, forum_CommentVO.getArt_no());
			pstmt.setInt(2, forum_CommentVO.getSpeaker_no());
			pstmt.setString(3, forum_CommentVO.getCont());
			pstmt.setTimestamp(4, forum_CommentVO.getFmc_date());
			pstmt.setInt(5, forum_CommentVO.getFmc_no());

			pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, fmc_no);

			pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (SQLException | ClassNotFoundException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (SQLException | ClassNotFoundException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (SQLException | ClassNotFoundException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return forum_CommentVO;

	}

	public static void main(String[] args) {

		Forum_CommentJDBCDAO dao = new Forum_CommentJDBCDAO();

		Timestamp t = new Timestamp(System.currentTimeMillis());

		// 新增
		// Forum_CommentVO forum_CommentVO1 = new Forum_CommentVO();
		//
		// forum_CommentVO1.setArt_no(5002);
		// forum_CommentVO1.setSpeaker_no(123456);
		// forum_CommentVO1.setCont("整個社會一直唱衰台灣整天日本好 韓國好 中國好
		// 東協好為何敢放空台股那麼少人十幾年一直抱怨不景氣 百貨公司一家家開 三不五時跑去日本旅遊或其他國家旅遊 出國花錢不手軟 小費給得很用力
		// 回到台灣小氣又巴拉 只會省錢不消費");
		// forum_CommentVO1.setFmc_date(t);
		//
		// dao.insert(forum_CommentVO1);

		// // 修改
		// Forum_CommentVO forum_CommentVO2 = new Forum_CommentVO();
		// forum_CommentVO2.setFmc_no(6004);
		// forum_CommentVO2.setArt_no(5002);
		// forum_CommentVO2.setSpeaker_no(123456);
		// forum_CommentVO2.setCont("j06");
		// forum_CommentVO2.setFmc_date(t);
		//
		// dao.update(forum_CommentVO2);

		// // 刪除
		// dao.delete(6002);
		//
		// 查詢
		// Forum_CommentVO forum_CommentVO3 = dao.findByPrimaryKey(5001);
		// System.out.print(forum_CommentVO3.getFmc_no() + ",");
		// System.out.print(forum_CommentVO3.getArt_no() + ",");
		// System.out.print(forum_CommentVO3.getSpeaker_no() + ",");
		// System.out.print(forum_CommentVO3.getCont() + ",");
		// System.out.print(new SimpleDateFormat("yyyy-mm-dd
		// HH:mm:ss").format(forum_CommentVO3.getFmc_date() ) + ",");
		//
		// System.out.println("---------------------");
		//
		// // 查詢
		// List<Forum_CommentVO> list = dao.getAll();
		// for (Forum_CommentVO forum_CommentVO4 : list) {
		// System.out.print(forum_CommentVO4.getFmc_no() + ",");
		// System.out.print(forum_CommentVO4.getArt_no() + ",");
		// System.out.print(forum_CommentVO4.getSpeaker_no() + ",");
		// System.out.print(forum_CommentVO4.getCont() + ",");
		//
		// System.out.print(new SimpleDateFormat("yyyy-mm-dd
		// HH:mm:ss").format(forum_CommentVO4.getFmc_date() ) + ",");
		//
		// System.out.println("---------------------");
		// }

		// 個別查詢
		Forum_CommentVO forum_CommentVO4 = dao.getOneAll(5001);

		System.out.print(forum_CommentVO4.getFmc_no() + ",");
		System.out.print(forum_CommentVO4.getArt_no() + ",");
		System.out.print(forum_CommentVO4.getSpeaker_no() + ",");
		System.out.print(forum_CommentVO4.getCont() + ",");
		System.out.print(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(forum_CommentVO4.getFmc_date()) + ",");

		System.out.println("---------------------");

	}

}
