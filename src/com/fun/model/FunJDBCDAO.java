
package com.fun.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.serv.model.ServVO;




public class FunJDBCDAO implements FunDAO_Interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "yes";
	String passwd = "123";
	
	private static final String INSERT_STMT = 
			"INSERT INTO admfunction (fun_no,name) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT fun_no,name FROM admfunction order by fun_no";
		private static final String GET_ONE_STMT = 
			"SELECT fun_no,name FROM admfunction where fun_no = ?";
		private static final String DELETE = 
			"DELETE FROM admfunction where fun_no = ?";


	
	
	@Override
	public void insert(FunVO funVO) {
		// TODO Auto-generated method stub
		

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, funVO.getFun_no());
			pstmt.setString(2, funVO.getName());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void delete(String fun_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, fun_no);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public FunVO findByPrimaryKey(String fun_no) {
		// TODO Auto-generated method stub	
		FunVO funVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, fun_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				funVO =new FunVO();
				funVO.setFun_no(rs.getString("fun_no"));
				funVO.setName(rs.getString("name"));
				
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return funVO;
	}

	@Override
	public List<FunVO> getAll() {
		// TODO Auto-generated method stub
		List<FunVO> list = new ArrayList<FunVO>();
		FunVO funVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				funVO =new FunVO();
				funVO.setFun_no(rs.getString("fun_no"));
				funVO.setName(rs.getString("name"));
				list.add(funVO);
				
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		
		FunJDBCDAO  dao =new FunJDBCDAO();
		//新增
		FunVO funVO01 =new FunVO();
		funVO01.setFun_no("00");
		funVO01.setName("測試");
		dao.insert(funVO01);

		// 刪除
		dao.delete("10");
		
		// 查詢一個
		FunVO funVO02 =dao.findByPrimaryKey("01");
		System.out.print(funVO02.getFun_no() + ",");
		System.out.println(funVO02.getName());
		System.out.println("---------------------");
		
		// 查詢全;
		
		List<FunVO> list = dao.getAll();
		for (FunVO afun : list) {
			System.out.print(afun.getFun_no()+ ",");
			System.out.print(afun.getName());

			System.out.println();
		}
		
	}
	
}
