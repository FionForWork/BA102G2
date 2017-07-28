package com.adm.model;

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



public class AdmJDBCDAO implements AdmDAO_Interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "yes";
	String passwd = "123";
	
	private static final String INSERT_STMT = 
			"INSERT INTO adminstrator (adm_no,id,pwd,name,job,status) VALUES (ltrim(TO_CHAR(admid_sq.NEXTVAL,'0009')), ?, ?, ?, ?, ?)";
	
	private static final String GET_ALL_STMT = 
			"SELECT adm_no,id,pwd,name,job,status FROM adminstrator order by adm_no";
		private static final String GET_ONE_STMT = 
			"SELECT adm_no,id,pwd,name,job,status FROM adminstrator where adm_no = ?";
		private static final String DELETE = 
			"DELETE FROM adminstrator where adm_no = ?";
		private static final String UPDATE = 
			"UPDATE adminstrator set id= ?, pwd= ?, name= ?, job= ?, status= ? where adm_no = ?";

	
	
	
	
	@Override
	public void insert(AdmVO admVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, admVO.getId());
			pstmt.setString(2, admVO.getPwd());
			pstmt.setString(3, admVO.getName());
			pstmt.setString(4, admVO.getJob());
			pstmt.setString(5, admVO.getStatus());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void update(AdmVO admVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, admVO.getId());
			pstmt.setString(2, admVO.getPwd());
			pstmt.setString(3, admVO.getName());
			pstmt.setString(4, admVO.getJob());
			pstmt.setString(5, admVO.getStatus());
			pstmt.setString(6, admVO.getAdm_no());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(String adm_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, adm_no);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public AdmVO findByPrimaryKey(String adm_no) {
		AdmVO admVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, adm_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				admVO = new AdmVO();
				admVO.setAdm_no(rs.getString("adm_no"));
				admVO.setId(rs.getString("id"));
				admVO.setPwd(rs.getNString("pwd"));
				admVO.setName(rs.getString("name"));
				admVO.setJob(rs.getString("job"));
				admVO.setStatus(rs.getString("status"));

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
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
		
		return admVO;
	}

	@Override
	public List<AdmVO> getAll() {
		// TODO Auto-generated method stub
		List<AdmVO> list = new ArrayList<AdmVO>();
		AdmVO admVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				admVO = new AdmVO();
				admVO.setAdm_no(rs.getString("adm_no"));
				admVO.setId(rs.getString("id"));
				admVO.setPwd(rs.getNString("pwd"));
				admVO.setName(rs.getString("name"));
				admVO.setJob(rs.getString("job"));
				admVO.setStatus(rs.getString("status"));
				list.add(admVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
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
		
	AdmJDBCDAO dao =new AdmJDBCDAO();
	AdmVO admVO01 =new AdmVO();
	
	admVO01.setId("ssss@sss.com");
	admVO01.setPwd("ssssss");
	admVO01.setName("肥淤淤");
	admVO01.setJob("員工");
	admVO01.setStatus("正常");
	admVO01.setAdm_no("0002");
	dao.update(admVO01);
	
	
	}
}
