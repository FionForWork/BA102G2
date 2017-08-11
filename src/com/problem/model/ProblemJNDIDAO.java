package com.problem.model;

import java.sql.Connection;
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



public class ProblemJNDIDAO implements Problem_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO PROBLEM (prob_no,problem_type_no,content,reply) VALUES (lpad(PROB_NO_sq.NEXTVAL,4,'0'), ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM PROBLEM order by prob_no";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM PROBLEM where prob_no = ?";
		private static final String DELETE = 
			"DELETE FROM PROBLEM where prob_no = ?";
		private static final String UPDATE = 
			"UPDATE PROBLEM set problem_type_no=?,content=?,reply=? where prob_no=?";
		private static final String GET_ONE_ALL = "SELECT * FROM PROBLEM where problem_type_no = ?";
			
		
		
		@Override
		public void insert(ProblemVO problemVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				
				
				pstmt.setInt(1, problemVO.getProblem_type_no());
				
				pstmt.setString(2, problemVO.getContent());
				pstmt.setString(3, problemVO.getReply());
				
				pstmt.executeUpdate();

				
			} catch (SQLException  se) {
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				
				
				pstmt.setInt(1, problemVO.getProblem_type_no());
				
				pstmt.setString(2, problemVO.getContent());
				pstmt.setString(3, problemVO.getReply());
				pstmt.setInt(4, problemVO.getProb_no());
				
				pstmt.executeUpdate();

				
			} catch (SQLException  se) {
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, prob_no);

				pstmt.executeUpdate();

				
			} catch (SQLException  se) {
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, prob_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					problemVO = new ProblemVO();
					problemVO.setProb_no(rs.getInt("prob_no"));
					problemVO.setProblem_type_no(rs.getInt("problem_type_no"));
					
					problemVO.setContent(rs.getString("content"));
					problemVO.setReply(rs.getString("reply"));
					
				}

				
			} catch (SQLException  se) {
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
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					problemVO = new ProblemVO();
					problemVO.setProb_no(rs.getInt("prob_no"));
					problemVO.setProblem_type_no(rs.getInt("problem_type_no"));
					
					problemVO.setContent(rs.getString("content"));
					problemVO.setReply(rs.getString("reply"));
					
				
					list.add(problemVO); 
				}

				
			} catch (SQLException  se) {
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
		@Override
		public List<ProblemVO> getOneAll(Integer problem_type_no) {
			List<ProblemVO> list = new ArrayList<ProblemVO>();
			ProblemVO problemVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_ALL);
				pstmt.setInt(1, problem_type_no);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					problemVO = new ProblemVO();
					problemVO.setProb_no(rs.getInt("prob_no"));
					problemVO.setProblem_type_no(rs.getInt("problem_type_no"));
					
					problemVO.setContent(rs.getString("content"));
					problemVO.setReply(rs.getString("reply"));
					
				
					list.add(problemVO); 
				}

				
			} catch (SQLException  se) {
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
		
		
}
