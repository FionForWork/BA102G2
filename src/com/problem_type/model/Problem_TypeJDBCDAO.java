package com.problem_type.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Problem_TypeJDBCDAO implements Problem_Type_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "home";
	String passwd = "123";
	
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				
				pstmt.setInt(1, problem_TypeVO.getProblem_type_no());
				pstmt.setString(2, problem_TypeVO.getType());
				
				
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
		public void update(Problem_TypeVO problem_TypeVO) {
			Connection con=null;
			PreparedStatement pstmt = null;
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				
				
				pstmt.setString(1, problem_TypeVO.getType());
				pstmt.setInt(2, problem_TypeVO.getProblem_type_no());
				
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
		public void delete(Integer problem_type_no) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, problem_type_no);

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
		public Problem_TypeVO findByPrimaryKey(Integer problem_type_no) {
			Problem_TypeVO problem_TypeVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, problem_type_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					problem_TypeVO = new Problem_TypeVO();
					problem_TypeVO.setProblem_type_no(rs.getInt("problem_type_no"));
					problem_TypeVO.setType(rs.getString("type"));
					
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					problem_TypeVO = new Problem_TypeVO();
					problem_TypeVO.setProblem_type_no(rs.getInt("problem_type_no"));
					problem_TypeVO.setType(rs.getString("type"));
					
				
					list.add(problem_TypeVO); 
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

			Problem_TypeJDBCDAO dao = new Problem_TypeJDBCDAO();
			
			
			
			// 新增
//			Problem_TypeVO problem_TypeVO1 = new Problem_TypeVO();
//			problem_TypeVO1.setProblem_type_no(60);
//			problem_TypeVO1.setType("婚紗");
//			dao.insert(problem_TypeVO1);

//			// 修改
			
//			Problem_TypeVO problem_TypeVO2 = new Problem_TypeVO();
//			problem_TypeVO2.setProblem_type_no(60);
//			problem_TypeVO2.setType("?");
//			dao.update(problem_TypeVO2);
			
	//
//			// 刪除
//			dao.delete(60);
	//
//			// 查詢
//			Problem_TypeVO problem_TypeVO3 = dao.findByPrimaryKey(10);
//			System.out.print(problem_TypeVO3.getProblem_type_no() + ",");
//			System.out.print(problem_TypeVO3.getType() + ",");
//			
//			System.out.println("---------------------");
	//
//			// 查詢
//			List<Problem_TypeVO> list = dao.getAll();
//			for (Problem_TypeVO problem_Type : list) {
//				System.out.print(problem_Type.getProblem_type_no() + ",");
//				System.out.print(problem_Type.getType() + ",");
//				
//				System.out.println();
//			}
		
		
	}
		
		

}
