package com.aut.model;

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



public class AutDAO implements AutDAO_Interface{

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
			"INSERT INTO authority (adm_no,id) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT adm_no,id FROM authority order by adm_no";
		private static final String GET_ONE_STMT = 
			"SELECT adm_no,id FROM authority where adm_no = ?";
		private static final String DELETE = 
			"DELETE FROM authority where adm_no = ? and id= ?";
	
	
	@Override
	public void insert(AutVO autVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
		pstmt = con.prepareStatement(INSERT_STMT);
		pstmt.setString(1, autVO.getAdm_no());
		pstmt.setString(2, autVO.getId());
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
	public void delete(String adm_no,String id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, adm_no);
			pstmt.setString(2, id);
			
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
	public AutVO findByPrimaryKey(String adm_no) {
		// TODO Auto-generated method stub
		AutVO autVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, adm_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				autVO = new AutVO();
				autVO.setAdm_no(rs.getString("adm_no"));
				autVO.setId(rs.getString("id"));
				
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
		
		
		return autVO;
	}

	@Override
	public List<AutVO> getAll() {
		// TODO Auto-generated method stub
		List<AutVO> list =new ArrayList<AutVO>();
		AutVO autVO = null;
				
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				autVO = new AutVO();
				autVO.setAdm_no(rs.getString("adm_no"));
				autVO.setId(rs.getString("id"));
				
				list.add(autVO); 
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


	@Override
	public List<AutVO> getOneAll() {
		List<AutVO> list =new ArrayList<AutVO>();
		AutVO autVO = null;
				
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			rs = pstmt.executeQuery();
			
		
				autVO = new AutVO();
				autVO.setAdm_no(rs.getString("adm_no"));
				while (rs.next()) {
				autVO.setId(rs.getString("id"));
				
				list.add(autVO); 
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
