package com.rfq.model;

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

public class RFQDAO implements RFQDAO_Interface {
	
	private static DataSource ds = null;
	static{
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = 
			"INSERT INTO RFQ VALUES (LTRIM(TO_CHAR(RFQ_SQ.NEXTVAL,'0009')), ?, ?)";
	private static final String UPDATE = 
			"UPDATE RFQ SET RFQ_DATE=? where RFQ_NO = ?";
	private static final String DELETE = 
			"DELETE FROM RFQ where RFQ_NO = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM RFQ where RFQ_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM RFQ order by RFQ_DATE DESC ";
	private static final String GET_MEM_NO =
			"Select * From RFQ where RFQ_NO = "
			+ "(select RFQ_NO from Rfq_Detail where Rfqdetail_No = "
			+ "(select RFQdetail_no from quote where quo_no = ?))";
	
	@Override
	public void insert(RFQVO rfqVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1,rfqVO.getMem_no());
			pstmt.setTimestamp(2, rfqVO.getRfq_date());
			pstmt.executeUpdate();
			
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setTimestamp(1, rfqVO.getRfq_date());
			pstmt.setString(2, rfqVO.getRfq_no());


			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rfq_no);

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rfq_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rfqVO = new RFQVO();
				rfqVO.setRfq_no(rs.getString("rfq_no"));
				rfqVO.setMem_no(rs.getString("mem_no"));
				rfqVO.setRfq_date(rs.getTimestamp("rfq_date"));
			}
			
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rfqVO = new RFQVO();
				rfqVO.setRfq_no(rs.getString("rfq_no"));
				rfqVO.setMem_no(rs.getString("mem_no"));
				rfqVO.setRfq_date(rs.getTimestamp("rfq_date"));
				list.add(rfqVO);
			}
			
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEM_NO);

			pstmt.setString(1, quo_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rfqVO = new RFQVO();
				rfqVO.setRfq_no(rs.getString("rfq_no"));
				rfqVO.setMem_no(rs.getString("mem_no"));
				rfqVO.setRfq_date(rs.getTimestamp("rfq_date"));
			}
			
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

}
