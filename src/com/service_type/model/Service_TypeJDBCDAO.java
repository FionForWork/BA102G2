package com.service_type.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Service_TypeJDBCDAO implements Service_TypeDAO_Interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "group2";
	String passwd = "group2";
	
	private static final String INSERT = 
			"INSERT INTO SERVICE_TYPE VALUES (LTRIM(TO_CHAR(SERVICETYPE_SQ.NEXTVAL,'0009')), ?)";
	private static final String UPDATE = 
			"UPDATE SERVICE_TYPE SET NAME=? where STYPE_NO = ?";
	private static final String DELETE = 
			"DELETE FROM SERVICE_TYPE where STYPE_NO = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM SERVICE_TYPE where STYPE_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM SERVICE_TYPE order by STYPE_NO ";
	
	@Override
	public void insert(Service_TypeVO service_typeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1,service_typeVO.getName());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(Service_TypeVO service_typeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, service_typeVO.getName());
			pstmt.setString(2, service_typeVO.getStype_no());


			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public void delete(String stype_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, stype_no);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public Service_TypeVO findByPK(String stype_no) {
		Service_TypeVO service_typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, stype_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				service_typeVO = new Service_TypeVO();
				service_typeVO.setStype_no(rs.getString("stype_no"));
				service_typeVO.setName(rs.getString("name"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return service_typeVO;
	}

	@Override
	public List<Service_TypeVO> getAll() {
		List<Service_TypeVO> list = new ArrayList<Service_TypeVO>();
		Service_TypeVO service_typeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				service_typeVO = new Service_TypeVO();
				service_typeVO.setStype_no(rs.getString("stype_no"));
				service_typeVO.setName(rs.getString("name"));
				list.add(service_typeVO);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public static void main(String args[]){
		
		Service_TypeJDBCDAO dao = new Service_TypeJDBCDAO();
		
//		Service_TypeVO service_typeVO = new Service_TypeVO();
//		service_typeVO.setName("婚紗");
//		dao.insert(service_typeVO);
		
		
//		Service_TypeJDBCDAO dao = new Service_TypeJDBCDAO();
//		Service_TypeVO service_typeVO = new Service_TypeVO();
//		service_typeVO.setStype_no("0005");
//		service_typeVO.setName("新秘");
//		dao.update(service_typeVO);
		
//		dao.delete("0006");
		
//		List<Service_TypeVO> list = dao.getAll();
//		for(Service_TypeVO service_typeVO : list){
//			System.out.print(service_typeVO.getStype_no());
//			System.out.println(service_typeVO.getName());
//		}
		
		Service_TypeVO service_typeVO = dao.findByPK("0009");
		System.out.print(service_typeVO.getStype_no());
		System.out.println(service_typeVO.getName());
		

	}
	
}


