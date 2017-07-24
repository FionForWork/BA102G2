package com.res_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Res_DetailJDBCDAO implements Res_DetailDAO_Interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "group2";
	String passwd = "group2";
	
	private static final String INSERT = 
			"INSERT INTO RES_DETAIL VALUES (LTRIM(TO_CHAR(RDNO_SQ.NEXTVAL,'0009')), ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STATUS = 
			"UPDATE RES_DETAIL SET STATUS=? where RD_NO = ?";
	private static final String DELETE = 
			"DELETE FROM RES_DETAIL where RD_NO = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM RES_DETAIL where RD_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM RES_DETAIL order by RD_NO ";
	
	@Override
	public void insert(Res_DetailVO res_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1,res_detailVO.getRes_no());
			pstmt.setString(2, res_detailVO.getServ_no());
			pstmt.setString(3, res_detailVO.getStype_no());
			pstmt.setInt(4, res_detailVO.getPrice());
			pstmt.setString(5, res_detailVO.getStatus());
			pstmt.setInt(6, res_detailVO.getScore());
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
	public void updateStatus(Res_DetailVO res_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setString(1, res_detailVO.getStatus());
			pstmt.setString(2, res_detailVO.getRd_no());

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
	public void delete(String rd_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rd_no);

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
	public Res_DetailVO findByPK(String rd_no) {
		Res_DetailVO res_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rd_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				res_detailVO = new Res_DetailVO();
				res_detailVO.setRd_no(rs.getString("rd_no"));
				res_detailVO.setRes_no(rs.getString("res_no"));
				res_detailVO.setStype_no(rs.getString("stype_no"));
				res_detailVO.setPrice(rs.getInt("price"));
				res_detailVO.setStatus(rs.getString("status"));
				res_detailVO.setScore(rs.getInt("score"));
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
		return res_detailVO;
	}

	@Override
	public List<Res_DetailVO> getAll() {
		List<Res_DetailVO> list = new ArrayList<Res_DetailVO>();
		Res_DetailVO res_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				res_detailVO = new Res_DetailVO();
				res_detailVO.setRd_no(rs.getString("rd_no"));
				res_detailVO.setRes_no(rs.getString("res_no"));
				res_detailVO.setStype_no(rs.getString("stype_no"));
				res_detailVO.setPrice(rs.getInt("price"));
				res_detailVO.setStatus(rs.getString("status"));
				res_detailVO.setScore(rs.getInt("score"));
				list.add(res_detailVO);
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
		
		Res_DetailJDBCDAO dao = new Res_DetailJDBCDAO();
			
		Res_DetailVO res_detailVO = new Res_DetailVO();
		res_detailVO.setRes_no("0001");
		res_detailVO.setServ_no("0002");
		res_detailVO.setStype_no("0001");
		res_detailVO.setPrice(18000);
		res_detailVO.setStatus("0");
		res_detailVO.setScore(5);
		dao.insert(res_detailVO);
		
//		Res_DetailVO res_detailVO = new Res_DetailVO();
//		res_detailVO.setStatus("1");
//		res_detailVO.setRd_no("0004");
//		dao.updateStatus(res_detailVO);
			
//		dao.delete("0003");
			
//		Res_DetailVO res_detailVO = dao.findByPK("0005");
//		System.out.println(res_detailVO.getRd_no());
//		System.out.println(res_detailVO.getRes_no());
//		System.out.println(res_detailVO.getServ_no());
//		System.out.println(res_detailVO.getStype_no());
//		System.out.println(res_detailVO.getPrice());
//		System.out.println(res_detailVO.getStatus());
//		System.out.println(res_detailVO.getScore());
			
//			List<Res_DetailVO> list = dao.getAll();
//			for(Res_DetailVO res_detailVO : list){
//				System.out.println(res_detailVO.getRd_no());
//				System.out.println(res_detailVO.getRes_no());
//				System.out.println(res_detailVO.getServ_no());
//				System.out.println(res_detailVO.getStype_no());
//				System.out.println(res_detailVO.getPrice());
//				System.out.println(res_detailVO.getStatus());
//				System.out.println(res_detailVO.getScore());
//			}

		
		}

}
