package com.mem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.com.model.ComVO;




public class MemDAO implements MemDAO_Interface{
	
	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String UPDATEPIC = 
			"UPDATE member set picture=? where mem_no = ?";
		private static final String INSERT_STMT = 
			"INSERT INTO member (mem_no,id,pwd,name,sex,bday,phone,email,account,picture,report,status) VALUES ('1'||ltrim(TO_CHAR(MEMID_SQ.NEXTVAL,'009')), ?, ?, ?, ?, ?, ?, ?, ?, ?, 0,'正常')";

		private static final String GET_ALL_STMT = 
			"SELECT mem_no,id,pwd,name,sex,to_char(bday,'yyyy-mm-dd') bday,phone,email,account,picture,report,status FROM member order by mem_no";
		private static final String GET_ONE_STMT = 
			"SELECT mem_no,id,pwd,name,sex,to_char(bday,'yyyy-mm-dd') bday,phone,email,account,picture,report,status  FROM member where mem_no = ?";
		private static final String DELETE = 
			"DELETE FROM member where mem_no = ?";
		private static final String UPDATE = 
			"UPDATE member set id=?,  name=?, sex=?, bday=?, phone=? ,email=?,account=?,picture=? where mem_no = ?";
		
		private static final String LOGINID ="SELECT id FROM member";
		private static final String LOGINPWD ="SELECT pwd FROM member";
		private static final String findById = "SELECT mem_no,id,pwd,name,sex,to_char(bday,'yyyy-mm-dd') bday,phone,email,account,picture,report,status from member where id= ?";
		private static final String OLDPWD = 
				"SELECT pwd from member where mem_no = ?";
		private static final String UPDATEPWD = 
				"UPDATE member set pwd=? where mem_no = ?";
		private static final String GET_Mems_ByReport_STMT = "SELECT mem_no,id,pwd,name,sex,to_char(bday,'yyyy-mm-dd') bday,phone,email,account,picture,report,status FROM member where report >= ? order by report";
		private static final String GET_Mems_ByStatus_STMT = "SELECT mem_no,id,pwd,name,sex,to_char(bday,'yyyy-mm-dd') bday,phone,email,account,picture,report,status FROM member where status = ? order by report";
		
		private static final String UPDATESTATUS = 
				"UPDATE member set status=? where mem_no = ?";
		private static final String UPDATESTATUSFORREPORT = 
				"UPDATE member set status='停權' where mem_no = ?";
		@Override
		public void updatePic(MemVO memVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATEPIC);
				pstmt.setBytes(1, memVO.getPicture());
				pstmt.setString(2, memVO.getMem_no());
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
		public void updateStatusForReport(MemVO memVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATESTATUSFORREPORT);
				pstmt.setString(1, memVO.getMem_no());
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
		public void updateStatus(MemVO memVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATESTATUS);
				pstmt.setString(1, memVO.getStatus());
				pstmt.setString(2, memVO.getMem_no());
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
		public void updatePwd(MemVO memVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATEPWD);
				pstmt.setString(1, memVO.getPwd());
				pstmt.setString(2, memVO.getMem_no());
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
		public MemVO oldPwd(String mem_no) {
			MemVO memVO =null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(OLDPWD);

				pstmt.setString(1,mem_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
				
					memVO = new MemVO();
					memVO.setPwd(rs.getString("pwd"));
				
					
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
			
			return memVO;
		}
	
		@Override
		public MemVO findById(String id) {
			MemVO memVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(findById);
				pstmt.setString(1,id);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					memVO =new MemVO();
					memVO.setMem_no(rs.getString("mem_no"));
					memVO.setId(rs.getString("id"));
					memVO.setPwd(rs.getString("pwd"));
					memVO.setName(rs.getString("name"));
					memVO.setSex(rs.getString("sex"));
					memVO.setBday(rs.getDate("bday"));
					memVO.setPhone(rs.getString("phone"));
					memVO.setEmail(rs.getString("email"));
					memVO.setAccount(rs.getString("account"));
					memVO.setPicture(rs.getBytes("picture"));
					memVO.setReport(rs.getInt("report"));
					memVO.setStatus(rs.getString("status"));
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
			
			
			return memVO;
		}
		
		@Override
		public MemVO findByPrimaryKey(String mem_no) {
			// TODO Auto-generated method stub
			

			MemVO memVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try{			
				con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memVO =new MemVO();
				memVO.setMem_no(rs.getString("mem_no"));
				memVO.setId(rs.getString("id"));
				memVO.setPwd(rs.getString("pwd"));
				memVO.setName(rs.getString("name"));
				memVO.setSex(rs.getString("sex"));
				memVO.setBday(rs.getDate("bday"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setEmail(rs.getString("email"));
				memVO.setAccount(rs.getString("account"));
				memVO.setPicture(rs.getBytes("picture"));
				memVO.setReport(rs.getInt("report"));
				memVO.setStatus(rs.getString("status"));
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
			
			return memVO;
		}
		
		@Override
		public List<MemVO> loginpwd() {
			// TODO Auto-generated method stub
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGINPWD);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memVO =new MemVO();
			
				memVO.setPwd(rs.getString("pwd"));
				list.add(memVO); 
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
	public  List<MemVO> loginid() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGINID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memVO =new MemVO();
			
				memVO.setId(rs.getString("id"));
				list.add(memVO); 
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
	public void insert(MemVO memVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, memVO.getId());
			pstmt.setString(2, memVO.getPwd());
			pstmt.setString(3, memVO.getName());
			pstmt.setString(4, memVO.getSex());
			pstmt.setDate(5, memVO.getBday());
			pstmt.setString(6, memVO.getPhone());
			pstmt.setString(7, memVO.getEmail());
			pstmt.setString(8, memVO.getAccount());
			pstmt.setBytes(9, memVO.getPicture());

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
	public void update(MemVO memVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, memVO.getId());

			pstmt.setString(2, memVO.getName());
			pstmt.setString(3, memVO.getSex());
			pstmt.setDate(4, memVO.getBday());
			pstmt.setString(5, memVO.getPhone());
			pstmt.setString(6, memVO.getEmail());
			pstmt.setString(7, memVO.getAccount());
			pstmt.setBytes(8, memVO.getPicture());
			pstmt.setString(9, memVO.getMem_no());
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
	public void delete(String mem_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, mem_no);

			pstmt.executeUpdate();

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
	public List<MemVO> getAll() {
		// TODO Auto-generated method stub
		
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO =new MemVO();
				memVO.setMem_no(rs.getString("mem_no"));
				memVO.setId(rs.getString("id"));
				memVO.setPwd(rs.getString("pwd"));
				memVO.setName(rs.getString("name"));
				memVO.setSex(rs.getString("sex"));
				memVO.setBday(rs.getDate("bday"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setEmail(rs.getString("email"));
				memVO.setAccount(rs.getString("account"));
				memVO.setPicture(rs.getBytes("picture"));
				memVO.setReport(rs.getInt("report"));
				memVO.setStatus(rs.getString("status"));
				list.add(memVO); 
			}
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
	public Set<MemVO> getMemsByReport(Integer report) {
		Set<MemVO> set = new LinkedHashSet<MemVO>();
		MemVO memVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Mems_ByReport_STMT);
			pstmt.setInt(1, report);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				memVO = new MemVO();
				
				memVO.setMem_no(rs.getString("mem_no"));
				memVO.setId(rs.getString("id"));
				memVO.setPwd(rs.getString("pwd"));
				memVO.setName(rs.getString("name"));
				memVO.setSex(rs.getString("sex"));
				memVO.setBday(rs.getDate("bday"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setEmail(rs.getString("email"));
				memVO.setAccount(rs.getString("account"));
				memVO.setPicture(rs.getBytes("picture"));
				memVO.setReport(rs.getInt("report"));
				memVO.setStatus(rs.getString("status"));
				
				set.add(memVO); // Store the row in the vector
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
	public Set<MemVO> getMemsByStatus(String status) {
		Set<MemVO> set = new LinkedHashSet<MemVO>();
		MemVO memVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Mems_ByStatus_STMT);
			pstmt.setString(1, status);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				memVO = new MemVO();
				
				memVO.setMem_no(rs.getString("mem_no"));
				memVO.setId(rs.getString("id"));
				memVO.setPwd(rs.getString("pwd"));
				memVO.setName(rs.getString("name"));
				memVO.setSex(rs.getString("sex"));
				memVO.setBday(rs.getDate("bday"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setEmail(rs.getString("email"));
				memVO.setAccount(rs.getString("account"));
				memVO.setPicture(rs.getBytes("picture"));
				memVO.setReport(rs.getInt("report"));
				memVO.setStatus(rs.getString("status"));
				
				set.add(memVO); // Store the row in the vector
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

	


	

	

	

}
