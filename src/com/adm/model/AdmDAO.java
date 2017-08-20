package com.adm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.com.model.ComVO;
import com.mem.model.MemVO;

public class AdmDAO implements AdmDAO_Interface{

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
			"INSERT INTO adminstrator (adm_no,id,pwd,name,job,status) VALUES (ltrim(TO_CHAR(admid_sq.NEXTVAL,'0009')), ?, ?, ?, ?, ?)";
	private static final String OLDPWD = 
			"SELECT pwd from adminstrator where adm_no = ?";
	private static final String UPDATEPWD = 
			"UPDATE adminstrator set pwd=? where adm_no = ?";
	private static final String GET_ALL_STMT = 
			"SELECT adm_no,id,pwd,name,job,status FROM adminstrator order by adm_no";
		private static final String GET_ONE_STMT = 
			"SELECT adm_no,id,pwd,name,job,status FROM adminstrator where adm_no = ?";
		private static final String DELETE = 
			"DELETE FROM adminstrator where adm_no = ?";
		private static final String UPDATE = 
			"UPDATE adminstrator set id=?, pwd=?, name=?, job=?, status=?  where adm_no = ?";
		private static final String LOGINID ="SELECT id FROM adminstrator";
		private static final String findById = "SELECT adm_no,id,pwd,name,job,status from adminstrator where id= ?";
	
		
		
		@Override
		public void updatePwd(AdmVO admVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATEPWD);
				pstmt.setString(1, admVO.getPwd());
				pstmt.setString(2, admVO.getAdm_no());
				pstmt.executeUpdate();
				
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
		public AdmVO oldPwd(String adm_no) {
			AdmVO admVO =null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(OLDPWD);

				pstmt.setString(1,adm_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
				
					admVO = new AdmVO();
					admVO.setPwd(rs.getString("pwd"));
				
					
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
			
			return admVO;
		}
		
		
		
		
		
		
		@Override
		public List<AdmVO> loginid() {
			List<AdmVO> list = new ArrayList<AdmVO>();
			AdmVO admVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(LOGINID);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					admVO =new AdmVO();
				
					admVO.setId(rs.getString("id"));
					list.add(admVO); 
				}
				
				
				
				
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
			return list;
		}

		@Override
		public AdmVO findById(String id) {
			AdmVO admVO =null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(findById);

				pstmt.setString(1, id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
				
					admVO = new AdmVO();
					
					admVO.setAdm_no(rs.getString("adm_no"));
					admVO.setId(rs.getString("id"));
					admVO.setPwd(rs.getString("pwd"));
					admVO.setName(rs.getString("name"));
					admVO.setStatus(rs.getString("status"));
				
					
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
			
			return admVO;
			
		}
	
	@Override
	public void insert(AdmVO admVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, admVO.getId());
			pstmt.setString(2, admVO.getPwd());
			pstmt.setString(3, admVO.getName());
			pstmt.setString(4, admVO.getJob());
			pstmt.setString(5, admVO.getStatus());
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
	public void update(AdmVO admVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, admVO.getId());
			pstmt.setString(2, admVO.getPwd());
			pstmt.setString(3, admVO.getName());
			pstmt.setString(4, admVO.getJob());
			pstmt.setString(5, admVO.getStatus());
			pstmt.setString(6, admVO.getAdm_no());
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
	public void delete(String adm_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, adm_no);

			pstmt.executeUpdate();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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



	
	
}
