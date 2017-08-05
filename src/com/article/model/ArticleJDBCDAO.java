package com.article.model;

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

public class ArticleJDBCDAO implements ArticleDAO_interfacce{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "home";
	String passwd = "123";
	
	private static final String INSERT_STMT = 
			"INSERT INTO article (art_no,poster_no,art_type_no,title,content,art_date) VALUES ('5'||lpad(art_no_sq.NEXTVAL,3,'0'), ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM article order by art_no";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM article where art_no = ?";
		private static final String DELETE = 
			"DELETE FROM article where ART_NO = ?";
		private static final String UPDATE = 
			"UPDATE article set poster_no=?, art_type_no=?, title=?, content=?, art_date=? where art_no=?";
	
	
	
	@Override
	public void insert(ArticleVO artVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, artVO.getPoster_no());
			pstmt.setInt(2, artVO.getArt_type_no());
			pstmt.setString(3,artVO.getTitle());
			pstmt.setString(4, artVO.getContent());
			pstmt.setDate(5, artVO.getArt_date());
			
			pstmt.executeUpdate();

			
		} catch (SQLException | ClassNotFoundException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
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
	public void update(ArticleVO artVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, artVO.getPoster_no());
			pstmt.setInt(2, artVO.getArt_type_no());
			pstmt.setString(3, artVO.getTitle());
			pstmt.setString(4,artVO.getContent());
			pstmt.setDate(5, artVO.getArt_date());
			pstmt.setInt(6, artVO.getArt_no());
			
			
			pstmt.executeUpdate();

			
		} catch (SQLException | ClassNotFoundException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
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
	public void delete(Integer article_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, article_no);

			pstmt.executeUpdate();

			
		} catch (SQLException | ClassNotFoundException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
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
	public ArticleVO findByPrimaryKey(Integer art_no) {
		
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, art_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				articleVO = new ArticleVO();
				articleVO.setArt_no(rs.getInt("art_no"));
				articleVO.setPoster_no(rs.getInt("poster_no"));
				articleVO.setArt_type_no(rs.getInt("art_type_no"));
				articleVO.setTitle(rs.getString("title"));
				articleVO.setContent(rs.getString("content"));
				articleVO.setArt_date(rs.getDate("art_date"));
			}

			
		} catch (SQLException | ClassNotFoundException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
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
		return articleVO;
	}
	@Override
	public List<ArticleVO> getAll() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArt_no(rs.getInt("art_no"));
				articleVO.setPoster_no(rs.getInt("poster_no"));
				articleVO.setArt_type_no(rs.getInt("art_type_no"));
				articleVO.setTitle(rs.getString("title"));
				articleVO.setContent(rs.getString("content"));
				articleVO.setArt_date(rs.getDate("art_date"));
			
				list.add(articleVO); 
			}

		
		} catch (SQLException | ClassNotFoundException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
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
	
	public static void main(String[] args) {

		ArticleJDBCDAO dao = new ArticleJDBCDAO();
		
		java.util.Date date = new java.util.Date();
		
		//新增
//		ArticleVO articleVO1 = new ArticleVO();
//		
//		articleVO1.setPoster_no(123);
//		articleVO1.setArt_type_no(10);
//		articleVO1.setTitle("標題");
//		articleVO1.setContent("整個社會一直唱衰台灣整天日本好 韓國好 中國好 東協好為何敢放空台股那麼少人十幾年一直抱怨不景氣 百貨公司一家家開 三不五時跑去日本旅遊或其他國家旅遊 出國花錢不手軟 小費給得很用力 回到台灣小氣又巴拉 只會省錢不消費");
//		articleVO1.setArt_date(new Date(date.getTime()));
//		
//		dao.insert(articleVO1);

//		// 修改
//		ArticleVO articleVO2 = new ArticleVO();
//		articleVO2.setArt_no(5002);
//		articleVO2.setPoster_no(123);
//		articleVO2.setArt_type_no(20);
//		articleVO2.setTitle("標題3");
//		articleVO2.setContent("內文");
//		articleVO2.setArt_date(new Date(date.getTime()));
//		dao.update(articleVO2);
//
//		// 刪除
//		dao.delete(2);
//
//		// 查詢
//		ArticleVO articleVO3 = dao.findByPrimaryKey(4);
//		System.out.print(articleVO3.getArt_no() + ",");
//		System.out.print(articleVO3.getPoster_no()+ ",");
//		System.out.print(articleVO3.getArt_type_no()+ ",");
//		System.out.print(articleVO3.getTitle()+ ",");
//		System.out.print(articleVO3.getContent() + ",");
//		System.out.print(articleVO3.getArt_date()+ ",");
//		
//		System.out.println("---------------------");
//
//		// 查詢
//		List<ArticleVO> list = dao.getAll();
//		for (ArticleVO articleVO4 : list) {
//			System.out.print(articleVO4.getArt_no() + ",");
//			System.out.print(articleVO4.getPoster_no()+ ",");
//			System.out.print(articleVO4.getArt_type_no()+ ",");
//			System.out.print(articleVO4.getTitle()+ ",");
//			System.out.print(articleVO4.getContent() + ",");
//			System.out.print(articleVO4.getArt_date()+ ",");
//			
//			System.out.println();
//		}

	
}
}

