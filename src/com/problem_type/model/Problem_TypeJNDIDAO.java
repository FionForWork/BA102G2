package com.problem_type.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class Problem_TypeJNDIDAO implements Problem_Type_interface{
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
			"INSERT INTO PROBLEM_TYPE (PROBLEM_TYPE_NO,TYPE) VALUES ( ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM PROBLEM_TYPE order by PROBLEM_TYPE_NO";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM PROBLEM_TYPE where PROBLEM_TYPE_NO = ?";
		private static final String DELETE = 
			"DELETE FROM PROBLEM_TYPE where PROBLEM_TYPE_NO = ?";
		private static final String UPDATE = 
			"UPDATE PROBLEM_TYPE set  TYPE=? where PROBLEM_TYPE_NO=?";
		
		
		@Override
		public void insert(Problem_TypeVO problem_TypeVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				
				pstmt.setInt(1, problem_TypeVO.getProblem_type_no());
				pstmt.setString(2, problem_TypeVO.getType());
				
				
				pstmt.executeUpdate();

				
			} catch (SQLException se) {
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
		public void update(Problem_TypeVO problem_TypeVO) {
			Connection con=null;
			PreparedStatement pstmt = null;
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				
				
				pstmt.setString(1, problem_TypeVO.getType());
				pstmt.setInt(2, problem_TypeVO.getProblem_type_no());
				
				pstmt.executeUpdate();

				
			} catch (SQLException se) {
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
		public void delete(Integer problem_type_no) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, problem_type_no);

				pstmt.executeUpdate();

				
			} catch (SQLException se) {
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
		public Problem_TypeVO findByPrimaryKey(Integer problem_type_no) {
			Problem_TypeVO problem_TypeVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, problem_type_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					problem_TypeVO = new Problem_TypeVO();
					problem_TypeVO.setProblem_type_no(rs.getInt("problem_type_no"));
					problem_TypeVO.setType(rs.getString("type"));
					
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
			return problem_TypeVO;
		}
		@Override
		public List<Problem_TypeVO> getAll() {
			List<Problem_TypeVO> list = new ArrayList<Problem_TypeVO>();
			Problem_TypeVO problem_TypeVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					problem_TypeVO = new Problem_TypeVO();
					problem_TypeVO.setProblem_type_no(rs.getInt("problem_type_no"));
					problem_TypeVO.setType(rs.getString("type"));
					
				
					list.add(problem_TypeVO); 
				}

				
			} catch (SQLException se) {
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
