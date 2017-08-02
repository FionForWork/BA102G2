package com.mem.model;

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
	
		private static final String INSERT_STMT = 
			"INSERT INTO member (mem_no,id,pwd,name,sex,bday,phone,email,account,img,report,status) VALUES ('1'||ltrim(TO_CHAR(MEMID_SQ.NEXTVAL,'009')), ?, ?, ?, ?, ?, ?, ?, ?, null, 0,'停權')";

		private static final String GET_ALL_STMT = 
			"SELECT mem_no,id,pwd,name,sex,to_char(bday,'yyyy-mm-dd') bday,phone,email,account,img,report,status FROM member order by mem_no";
		private static final String GET_ONE_STMT = 
			"SELECT mem_no,id,pwd,name,sex,to_char(bday,'yyyy-mm-dd') bday,phone,email,account,img,report,status  FROM member where mem_no = ?";
		private static final String DELETE = 
			"DELETE FROM member where mem_no = ?";
		private static final String UPDATE = 
			"UPDATE member set id=?, pwd=?, name=?, sex=?, bday=?, phone=? ,email=?,account=? where mem_no = ?";
		private static final String UPDATEPIC = 
				"UPDATE member set picture=? where mem_no = ?";
		
	
	
	@Override
	public void insert(MemVO memVO) {

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
			pstmt.setString(2, memVO.getPwd());
			pstmt.setString(3, memVO.getName());
			pstmt.setString(4, memVO.getSex());
			pstmt.setDate(5, memVO.getBday());
			pstmt.setString(6, memVO.getPhone());
			pstmt.setString(7, memVO.getEmail());
			pstmt.setString(8, memVO.getAccount());
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
			memVO.setImg(rs.getBytes("img"));
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
				memVO.setImg(rs.getBytes("img"));
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
	public void updatePic(MemVO memVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setBytes(1, memVO.getImg());
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

	

}
