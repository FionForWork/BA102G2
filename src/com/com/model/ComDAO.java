package com.com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.companycompositequery.controller.jdbcUtil_CompositeQuery_Com;



public class ComDAO implements ComDAO_Interface {

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
			"INSERT INTO company (com_no,id,pwd,name,loc,lon,lat,com_desc,phone,account,logo,status) VALUES ('2'||ltrim(TO_CHAR(comid_sq.NEXTVAL,'009')), ?, ?, ?, ?, ?, ?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT com_no,id,pwd,name,loc,lon,lat,com_desc,phone,account,logo,status FROM company order by com_no";
		private static final String GET_ONE_STMT = 
			"SELECT com_no,id,pwd,name,loc,lon,lat,com_desc,phone,account,logo,status FROM company where com_no = ?";
		private static final String DELETE = 
			"DELETE FROM company where com_no = ?";
		private static final String UPDATE = 
			"UPDATE company set id=?,pwd=?,name=?,loc=?,lon=?,lat=?,com_desc=?,phone=?,account=?,logo=?,status=? where com_no = ?";
	
	
	
	@Override
	public void insert(ComVO comVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, comVO.getId());
			pstmt.setString(2, comVO.getPwd());
			pstmt.setString(3, comVO.getName());
			pstmt.setString(4, comVO.getLoc());
			pstmt.setString(5, comVO.getLon());
			pstmt.setString(6, comVO.getLat());
			pstmt.setString(7, comVO.getCom_desc());
			pstmt.setString(8, comVO.getPhone());
			pstmt.setString(9, comVO.getAccount());
			pstmt.setBytes(10, comVO.getLogo());
			pstmt.setString(11, comVO.getStatus());
			
			pstmt.executeUpdate();
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
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
	public void update(ComVO comVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, comVO.getId());
			pstmt.setString(2, comVO.getPwd());
			pstmt.setString(3, comVO.getName());
			pstmt.setString(4, comVO.getLoc());
			pstmt.setString(5, comVO.getLon());
			pstmt.setString(6, comVO.getLat());
			pstmt.setString(7, comVO.getCom_desc());
			pstmt.setString(8, comVO.getPhone());
			pstmt.setString(9, comVO.getAccount());
			pstmt.setBytes(10, comVO.getLogo());
			pstmt.setString(11, comVO.getStatus());
			
			pstmt.executeUpdate();
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
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
	public void delete(String com_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, com_no);

			pstmt.executeUpdate();

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
	public ComVO findByPrimaryKey(String com_no) {
		ComVO comVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, com_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 銋迂� Domain objects
				comVO = new ComVO();
				comVO.setCom_no(rs.getString("com_no"));
				comVO.setId(rs.getString("id"));
				comVO.setPwd(rs.getString("pwd"));
				comVO.setName(rs.getString("name"));
				comVO.setLoc(rs.getString("loc"));
				comVO.setLon(rs.getString("lon"));
				comVO.setLat(rs.getString("lat"));
				comVO.setCom_desc(rs.getString("com_desc"));
				comVO.setPhone(rs.getString("phone"));
				comVO.setAccount(rs.getString("account"));
				comVO.setLogo(rs.getBytes("logo"));
				comVO.setStatus(rs.getString("status"));
			
				
			}

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
		return comVO;
	}

	@Override
	public List<ComVO> getAll() {
		List<ComVO> list = new ArrayList<ComVO>();
		ComVO comVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				comVO = new ComVO();

				comVO.setCom_no(rs.getString("com_no"));
				comVO.setId(rs.getString("id"));
				comVO.setPwd(rs.getString("pwd"));
				comVO.setName(rs.getString("name"));
				comVO.setLoc(rs.getString("loc"));
				comVO.setLon(rs.getString("lon"));
				comVO.setLat(rs.getString("lat"));
				comVO.setCom_desc(rs.getString("com_desc"));
				comVO.setPhone(rs.getString("phone"));
				comVO.setAccount(rs.getString("account"));
				comVO.setLogo(rs.getBytes("logo"));
				comVO.setStatus(rs.getString("status"));
				list.add(comVO); // Store the row in the list
			}

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

	@Override
	public List<ComVO> getAll(Map<String, String[]> map) {
		List<ComVO> list = new ArrayList<ComVO>();
		ComVO comVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from company "
		          + jdbcUtil_CompositeQuery_Com.get_WhereCondition(map)
		          + "order by com_no";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				comVO = new ComVO();
				comVO.setCom_no(rs.getString("com_no"));
				comVO.setId(rs.getString("id"));
				comVO.setPwd(rs.getString("pwd"));
				comVO.setName(rs.getString("name"));
				comVO.setLoc(rs.getString("loc"));
				comVO.setLon(rs.getString("lon"));
				comVO.setLat(rs.getString("lat"));
				comVO.setCom_desc(rs.getString("com_desc"));
				comVO.setPhone(rs.getString("phone"));
				comVO.setAccount(rs.getString("account"));
				comVO.setLogo(rs.getBytes("logo"));
				comVO.setStatus(rs.getString("status"));
				list.add(comVO);
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
		return list;
	}
}
