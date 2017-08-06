package com.rfq.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RFQJDBCDAO implements RFQDAO_Interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "group2";
	String passwd = "group2";
	
	private static final String INSERT = 
			"INSERT INTO RFQ VALUES (LTRIM(TO_CHAR(RFQ_SQ.NEXTVAL,'0009')), ?, ?)";
	private static final String UPDATE = 
			"UPDATE RFQ SET RFQ_DATE=? where RFQ_NO = ?";
	private static final String DELETE = 
			"DELETE FROM RFQ where RFQ_NO = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM RFQ where RFQ_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM RFQ order by RFQ_NO ";
	private static final String GET_MEM_NO =
			"Select * From RFQ where RFQ_NO = "
			+ "(select RFQ_NO from Rfq_Detail where Rfqdetail_No = "
			+ "(select RFQdetail_no from quote where quo_no = ?))";
	
	@Override
	public void insert(RFQVO rfqVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1,rfqVO.getMem_no());
			pstmt.setTimestamp(2, rfqVO.getRfq_date());
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
	public void update(RFQVO rfqVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setTimestamp(1, rfqVO.getRfq_date());
			pstmt.setString(2, rfqVO.getRfq_no());


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
	public void delete(String rfq_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rfq_no);

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
	public RFQVO findByPK(String rfq_no) {
		RFQVO rfqVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rfq_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rfqVO = new RFQVO();
				rfqVO.setRfq_no(rs.getString("rfq_no"));
				rfqVO.setMem_no(rs.getString("mem_no"));
				rfqVO.setRfq_date(rs.getTimestamp("rfq_date"));
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
		return rfqVO;
	}

	@Override
	public List<RFQVO> getAll() {
		List<RFQVO> list = new ArrayList<RFQVO>();
		RFQVO rfqVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rfqVO = new RFQVO();
				rfqVO.setRfq_no(rs.getString("rfq_no"));
				rfqVO.setMem_no(rs.getString("mem_no"));
				rfqVO.setRfq_date(rs.getTimestamp("rfq_date"));
				list.add(rfqVO);
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
	
	@Override
	public RFQVO findFromQuote(String quo_no) {
		RFQVO rfqVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MEM_NO);

			pstmt.setString(1, quo_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rfqVO = new RFQVO();
				rfqVO.setRfq_no(rs.getString("rfq_no"));
				rfqVO.setMem_no(rs.getString("mem_no"));
				rfqVO.setRfq_date(rs.getTimestamp("rfq_date"));
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
		return rfqVO;
	}
	
public static void main(String args[]){
		
		RFQJDBCDAO dao = new RFQJDBCDAO();
		
		RFQVO rfqVO = new RFQVO();
		rfqVO.setMem_no("1001");
		Timestamp t = new Timestamp(System.currentTimeMillis());
		rfqVO.setRfq_date(t);
		dao.insert(rfqVO);
		
		
//		RFQVO rfqVO = new RFQVO();
//		rfqVO.setMem_no("0005");
//		Timestamp t = new Timestamp(System.currentTimeMillis());
//		rfqVO.setRfq_date(t);
//		dao.update(rfqVO);
		
//		dao.delete("0002");
		
//		List<RFQVO> list = dao.getAll();
//		for(RFQVO rfqVO : list){
//			System.out.println(rfqVO.getRfq_no());
//			System.out.println(rfqVO.getMem_no());
//			System.out.println(rfqVO.getRfq_date());
//		}
		
//		RFQVO rfqVO = dao.findByPK("0006");
//		System.out.println(rfqVO.getRfq_no());
//		System.out.println(rfqVO.getMem_no());
//		System.out.println(rfqVO.getRfq_date());
		

	}


	
}
