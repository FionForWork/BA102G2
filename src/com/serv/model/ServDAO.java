package com.serv.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mem.model.MemVO;
import com.ssy.tools.jdbcUtil_CompositeQuery_Serv;

public class ServDAO implements ServDAO_Interface {

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
			"INSERT INTO service (serv_no,stype_no,com_no,deposit,price,title,content,score,times,status) VALUES (ltrim(TO_CHAR(SERVNO_SQ.NEXTVAL,'0009')), ?, ?, ?, ?, ?, ?,0,0,?)";

		private static final String GET_ALL_STMT = 
			"SELECT serv_no,stype_no,com_no,deposit,price,title,content,score,times,status FROM service order  by score desc";
		private static final String GET_ONE_STMT = 
			"SELECT serv_no,stype_no,com_no,deposit,price,title,content,score,times,status  FROM service where serv_no = ?";
		private static final String DELETE = 
			"DELETE FROM service where serv_no = ?";
		private static final String UPDATE = 
			"UPDATE service set stype_no=?, com_no=?, deposit=?, price=?, title=?, content=?,status=?  where serv_no = ?";
		private static final String UPDATESCORE = 
				"UPDATE service set times=?, score=?  where serv_no = ?";
		private static final String GET_ALL_COMNO_BY_STYPENO = "select com_no from service where stype_no=? group by com_no order by com_no";
		private static final String GET_COM_STMT = 
				"SELECT * FROM SERVICE WHERE COM_NO = ?";
		private static final String GET_search_STMT = 
				"SELECT * FROM service where title like ?";
		private static final String GET_ALL_AVG = "select com_no,avg(times),avg(score),avg(price) from service group by com_no order by com_no";
		private static final String getServByStype = "SELECT serv_no,stype_no,com_no,deposit,price,title,content,score,times,status FROM service where stype_no = ? order by score";
		private static final String getServByCom = "SELECT serv_no,stype_no,com_no,deposit,price,title,content,score,times,status FROM service where com_no = ? order by com_no";
		private static final String getComStype="select distinct stype_no from service where com_no = ?";
		
		@Override
		public Set<ServVO> getServByStype(String stype_no) {
			Set<ServVO> set = new LinkedHashSet<ServVO>();
			ServVO servVO = null;
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
		
				con = ds.getConnection();
				pstmt = con.prepareStatement(getServByStype);
				pstmt.setString(1, stype_no);
				rs = pstmt.executeQuery();
		
				while (rs.next()) {
					servVO = new ServVO();
					
					servVO.setServ_no(rs.getString("serv_no"));
					servVO.setCom_no(rs.getString("com_no"));
					servVO.setContent(rs.getString("content"));
					servVO.setDeposit(rs.getInt("deposit"));
					servVO.setPrice(rs.getInt("price"));
					servVO.setScore(rs.getDouble("score"));
					servVO.setStype_no(rs.getString("stype_no"));
					servVO.setTimes(rs.getInt("times"));
					servVO.setTitle(rs.getString("title"));
					servVO.setStatus(rs.getString("status"));
					
					set.add(servVO); // Store the row in the vector
				}
		
				// Handle any SQL errors
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
			return set;
		}

		@Override
		public Set<ServVO> getServByCom(String com_no) {
			Set<ServVO> set = new LinkedHashSet<ServVO>();
			ServVO servVO = null;
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
		
				con = ds.getConnection();
				pstmt = con.prepareStatement(getServByCom);
				pstmt.setString(1, com_no);
				rs = pstmt.executeQuery();
		
				while (rs.next()) {				
					servVO = new ServVO();
					
					servVO.setServ_no(rs.getString("serv_no"));
					servVO.setCom_no(rs.getString("com_no"));
					servVO.setContent(rs.getString("content"));
					servVO.setDeposit(rs.getInt("deposit"));
					servVO.setPrice(rs.getInt("price"));
					servVO.setScore(rs.getDouble("score"));
					servVO.setStype_no(rs.getString("stype_no"));
					servVO.setTimes(rs.getInt("times"));
					servVO.setTitle(rs.getString("title"));
					servVO.setStatus(rs.getString("status"));
					
					set.add(servVO); // Store the row in the vector
				}
		
				// Handle any SQL errors
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
			return set;
		}
		
		
		
		
		
		@Override
		public List<ServVO> findBysh(String sh) {
			// TODO Auto-generated method stub
			List<ServVO> list = new ArrayList<ServVO>();
			ServVO servVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_search_STMT);
				pstmt.setString(1, sh);
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
					servVO.setStatus(rs.getString("status"));
					list.add(servVO); // Store the row in the list
				} 
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

	@Override
	public void insert(ServVO servVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, servVO.getStype_no());
			pstmt.setString(2, servVO.getCom_no());
			pstmt.setInt(3, servVO.getDeposit());
			pstmt.setInt(4, servVO.getPrice());
			pstmt.setString(5, servVO.getTitle());
			pstmt.setString(6, servVO.getContent());
			pstmt.setString(7,servVO.getStatus());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, servVO.getStype_no());
			pstmt.setString(2, servVO.getCom_no());
			pstmt.setInt(3, servVO.getDeposit());
			pstmt.setInt(4, servVO.getPrice());
			pstmt.setString(5, servVO.getTitle());
			pstmt.setString(6, servVO.getContent());
			pstmt.setString(7, servVO.getServ_no());
			pstmt.setString(8, servVO.getStatus());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, serv_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			con = ds.getConnection();
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
				servVO.setStatus(rs.getString("status"));
			}

		} catch (SQLException se) {

			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<ServVO> getAll() {
		List<ServVO> list = new ArrayList<ServVO>();
		ServVO servVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
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
				servVO.setStatus(rs.getString("status"));
				list.add(servVO); // Store the row in the list
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public List<String> findByStype_no(String stype_no) {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_COMNO_BY_STYPENO);
			pstmt.setString(1, stype_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("com_no"));
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
	public List<ServVO> getCom(String com_no) {
		List<ServVO> list = new ArrayList<ServVO>();
		ServVO servVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COM_STMT);
			pstmt.setString(1, com_no);
			
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
				servVO.setStatus(rs.getString("status"));
				list.add(servVO); // Store the row in the list
			} 
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


	@Override
	public List<ServVO> getAll(Map<String, String[]> map) {
		List<ServVO> list = new ArrayList<ServVO>();
		ServVO servVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from Service "
			          + jdbcUtil_CompositeQuery_Serv.get_WhereCondition(map)
			          + "order by price";
//			System.out.println(finalSQL);
			pstmt = con.prepareStatement(finalSQL);
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
				servVO.setStatus(rs.getString("status"));
				list.add(servVO); // Store the row in the list
			} 
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
	@Override
	public List<ServVO> getAllAvg() {
		List<ServVO> list = new ArrayList<ServVO>();
		ServVO servVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_AVG);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				servVO = new ServVO();
				servVO.setCom_no(rs.getString("com_no"));
				servVO.setTimes(rs.getInt("avg(times)"));
				servVO.setScore(rs.getDouble("avg(score)"));
				servVO.setPrice(rs.getInt("avg(price)"));
				list.add(servVO);
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
	public void updateScore(ServVO servVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESCORE);

			pstmt.setInt(1, servVO.getTimes());
			pstmt.setDouble(2, servVO.getScore());
			pstmt.setString(3, servVO.getServ_no());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<String> getComStype(String com_no) {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getComStype);
			pstmt.setString(1, com_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("stype_no"));
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
}