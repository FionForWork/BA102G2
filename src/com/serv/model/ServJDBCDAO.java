package com.serv.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;




public class ServJDBCDAO implements ServDAO_Interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "yes";
	String passwd = "123";
	
	
	
	private static final String INSERT_STMT = 
			"INSERT INTO service (serv_no,stype_no,com_no,deposit,price,title,content,score,times) VALUES (ltrim(TO_CHAR(SERVNO_SQ.NEXTVAL,'0009')), ?, ?, ?, ?, ?, ?,0,0)";

		private static final String GET_ALL_STMT = 
			"SELECT serv_no,stype_no,com_no,deposit,price,title,content,score,times FROM service order by serv_no";
		private static final String GET_ONE_STMT = 
			"SELECT serv_no,stype_no,com_no,deposit,price,title,content,score,times  FROM service where serv_no = ?";
		private static final String DELETE = 
			"DELETE FROM service where serv_no = ?";
		private static final String UPDATE = 
			"UPDATE service set stype_no=?, com_no=?, deposit=?, price=?, title=?, content=?  where serv_no = ?";
		private static final String GET_COM_STMT = 
				"SELECT * FROM SERVICE WHERE COM_NO = ?";
		
	
	@Override
	public void insert(ServVO servVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
		
			pstmt.setString(1, servVO.getStype_no());
			pstmt.setString(2, servVO.getCom_no());
			pstmt.setInt(3, servVO.getDeposit());
			pstmt.setInt(4, servVO.getPrice());
			pstmt.setString(5, servVO.getTitle());
			pstmt.setString(6, servVO.getContent());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
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
	public void update(ServVO servVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, servVO.getStype_no());
			pstmt.setString(2, servVO.getCom_no());
			pstmt.setInt(3, servVO.getDeposit());
			pstmt.setInt(4, servVO.getPrice());
			pstmt.setString(5, servVO.getTitle());
			pstmt.setString(6, servVO.getContent());
			pstmt.setString(7, servVO.getServ_no());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void delete(String serv_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, serv_no);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public ServVO findByPrimaryKey(String serv_no) {
		// TODO Auto-generated method stub
		
		ServVO servVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, serv_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				servVO = new ServVO();
				servVO.setServ_no(rs.getString("serv_no"));
				servVO.setStype_no(rs.getString("stype_no"));
				servVO.setCom_no(rs.getString("com_no"));
				servVO.setDeposit(rs.getInt("deposit"));
				servVO.setPrice(rs.getInt("price"));
				servVO.setTitle(rs.getString("title"));
				servVO.setContent(rs.getString("content"));
				servVO.setTimes(rs.getInt("times"));
				servVO.setScore(rs.getDouble("score"));
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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
		
		return servVO;
	}
	@Override
	public List<String> findByStype_no(String stype_no) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<ServVO> getCom(String com_no) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<ServVO> findBysh(String sh) {
		return null;
	}

	@Override
	public List<ServVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<ServVO> getAllAvg() {

		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ServVO> getAll() {
		List<ServVO> list = new ArrayList<ServVO>();
		ServVO servVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				servVO = new ServVO();
				servVO.setServ_no(rs.getString("serv_no"));
				servVO.setStype_no(rs.getString("stype_no"));
				servVO.setCom_no(rs.getString("com_no"));
				servVO.setDeposit(rs.getInt("deposit"));
				servVO.setPrice(rs.getInt("price"));
				servVO.setTitle(rs.getString("title"));
				servVO.setContent(rs.getString("content"));
				servVO.setTimes(rs.getInt("times"));
				servVO.setScore(rs.getDouble("score"));
				list.add(servVO); // Store the row in the list
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		
		}catch (SQLException se) {
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
		
		ServJDBCDAO dao =new ServJDBCDAO();
		//新增
		ServVO servVO01 =new ServVO();
		servVO01.setStype_no("0001");
		servVO01.setCom_no("2001");
		servVO01.setDeposit(2000);
		servVO01.setPrice(15000);
		servVO01.setTitle("測試資料");
		servVO01.setContent("測試內榮");
		dao.insert(servVO01);
		
		//修改
		ServVO servVO02=new ServVO();
	
		servVO02.setStype_no("0001");
		servVO02.setCom_no("2001");
		servVO02.setDeposit(2000);
		servVO02.setPrice(15000);
		servVO02.setTitle("測試資料");
		servVO02.setContent("測試內榮");
		dao.insert(servVO02);
		
		// 刪除
		dao.delete("0010");
		
		// 查詢一個
		ServVO servVO03 = dao.findByPrimaryKey("0001");
		
		System.out.print(servVO03.getServ_no() + ",");
		System.out.print(servVO03.getStype_no() + ",");
		System.out.print(servVO03.getCom_no() + ",");
		System.out.print(servVO03.getDeposit() + ",");
		System.out.print(servVO03.getPrice() + ",");
		System.out.print(servVO03.getTitle() + ",");
		System.out.print(servVO03.getContent()+",");
		System.out.print(servVO03.getTimes()+",");
		System.out.println(servVO03.getScore());
		System.out.println("---------------------");
		
		
		// 查詢
		List<ServVO> list = dao.getAll();
		for (ServVO aserv : list) {
			System.out.print(aserv.getServ_no() + ",");
			System.out.print(aserv.getStype_no() + ",");
			System.out.print(aserv.getCom_no() + ",");
			System.out.print(aserv.getDeposit() + ",");
			System.out.print(aserv.getPrice() + ",");
			System.out.print(aserv.getTitle() + ",");
			System.out.print(aserv.getContent()+",");
			System.out.print(aserv.getTimes()+",");
			System.out.print(aserv.getScore());

			System.out.println();
		}
		
		
		
		
	}


	@Override
	public Set<ServVO> getServByStype(String stype_no) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<ServVO> getServByCom(String com_no) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
}





