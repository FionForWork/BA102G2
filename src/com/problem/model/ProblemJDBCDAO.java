package com.problem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



public class ProblemJDBCDAO implements Problem_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "home";
	String passwd = "123";
	
	private static final String INSERT_STMT = 
			"INSERT INTO PROBLEM (prob_no,type,title,content,reply) VALUES (PROB_NO_sq.NEXTVAL, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM PROBLEM order by prob_no";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM PROBLEM where prob_no = ?";
		private static final String DELETE = 
			"DELETE FROM PROBLEM where prob_no = ?";
		private static final String UPDATE = 
			"UPDATE PROBLEM set type=?, title=?,content=?,reply=? where prob_no=?";
		
		
		@Override
		public void insert(ProblemVO problemVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				
				
				pstmt.setInt(1, problemVO.getType());
				pstmt.setString(2, problemVO.getTitle());
				pstmt.setString(3, problemVO.getContent());
				pstmt.setString(4, problemVO.getReply());
				
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
		public void update(ProblemVO problemVO) {
			Connection con=null;
			PreparedStatement pstmt = null;
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				
				pstmt.setInt(1, problemVO.getType());
				pstmt.setString(2, problemVO.getTitle());
				pstmt.setString(3, problemVO.getContent());
				pstmt.setString(4, problemVO.getReply());
				pstmt.setInt(5, problemVO.getProb_no());
				
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
		public void delete(Integer prob_no) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, prob_no);

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
		public ProblemVO findByPrimaryKey(Integer prob_no) {
			ProblemVO problemVO =null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, prob_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					problemVO = new ProblemVO();
					problemVO.setProb_no(rs.getInt("prob_no"));
					problemVO.setType(rs.getInt("type"));
					problemVO.setTitle(rs.getString("title"));
					problemVO.setContent(rs.getString("content"));
					problemVO.setReply(rs.getString("reply"));
					
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
			return problemVO;
		}
		@Override
		public List<ProblemVO> getAll() {
			List<ProblemVO> list = new ArrayList<ProblemVO>();
			ProblemVO problemVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					problemVO = new ProblemVO();
					problemVO.setProb_no(rs.getInt("prob_no"));
					problemVO.setType(rs.getInt("type"));
					problemVO.setTitle(rs.getString("title"));
					problemVO.setContent(rs.getString("content"));
					problemVO.setReply(rs.getString("reply"));
					
				
					list.add(problemVO); 
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

			ProblemJDBCDAO dao = new ProblemJDBCDAO();
			
			
			 Timestamp t = new Timestamp(System.currentTimeMillis()); 
			// 新增
//			 ProblemVO problemVO1 = new ProblemVO();
//			
//			 problemVO1.setType(10);
//			 problemVO1.setTitle("問題標題");
//			 problemVO1.setContent("問題內容");
//			 problemVO1.setReply("整個社會一直唱衰台灣整天日本好 韓國好 中國好 東協好為何敢放空台股那麼少人十幾年一直抱怨不景氣 百貨公司一家家開 三不五時跑去日本旅遊或其他國家旅遊 出國花錢不手軟 小費給得很用力 回到台灣小氣又巴拉 只會省錢不消費");
//			
//			
//			
//			dao.insert(problemVO1);

//			// 修改
			ProblemVO problemVO2 = new ProblemVO();
			
			 problemVO2.setType(20);
			 problemVO2.setTitle("問題標題");
			 problemVO2.setContent("問題內容");
			 problemVO2.setReply("內容");
			 problemVO2.setProb_no(2);
			dao.update(problemVO2);
	//
//			// 刪除
//			dao.delete();
	//
//			// 查詢
//			 ProblemVO problemVO3 = dao.findByPrimaryKey(2);
//			System.out.print(problemVO3.getProb_no() + ",");
//			System.out.print(problemVO3.getType() + ",");
//			System.out.print(problemVO3.getTitle() + ",");
//			System.out.print(problemVO3.getContent() + ",");
//			System.out.print(problemVO3.getReply()+ ",");
//		
//			System.out.println("---------------------");
	//
//			// 查詢
			List<ProblemVO> list = dao.getAll();
			for (ProblemVO problem4 : list) {
			System.out.print(problem4.getProb_no() + ",");
			System.out.print(problem4.getType() + ",");
			System.out.print(problem4.getTitle() + ",");
			System.out.print(problem4.getContent() + ",");
			System.out.print(problem4.getReply()+ ",");
			System.out.println();
			}
		
		
	}
}
