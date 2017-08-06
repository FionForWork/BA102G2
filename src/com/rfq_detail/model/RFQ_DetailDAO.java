package com.rfq_detail.model;

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

public class RFQ_DetailDAO implements RFQ_DetailDAO_Interface {
	
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
			"INSERT INTO RFQ_DETAIL VALUES (LTRIM(TO_CHAR(RFQDETAIL_SQ.NEXTVAL,'0009')), LTRIM(TO_CHAR(RFQ_SQ.CURRVAL,'0009')), ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE RFQ_DETAIL SET STATUS=? WHERE RFQDETAIL_NO = ?";
	private static final String DELETE = 
			"DELETE FROM RFQ_DETAIL where RFQDETAIL_NO = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM RFQ_DETAIL where RFQDETAIL_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM RFQ_DETAIL order by status DESC, SER_DATE DESC ";
	private static final String GET_SOME_STMT = 
			"SELECT * FROM RFQ_DETAIL where rfq_no = ? order by SER_DATE DESC ";
	private static final String GET_MY_STMT = 
			"SELECT * FROM RFQ_DETAIL where rfq_no in (select rfq_no from RFQ where mem_no = ?) order by status desc";
	
	@Override
	public void insert(RFQ_DetailVO rfq_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, rfq_detailVO.getStype_no());
			pstmt.setString(2, rfq_detailVO.getLocation());
			pstmt.setTimestamp(3, rfq_detailVO.getSer_date());
			pstmt.setString(4, rfq_detailVO.getContent());
			pstmt.setString(5, rfq_detailVO.getStatus());
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
	public void update(RFQ_DetailVO rfq_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rfq_detailVO.getStatus());
			pstmt.setString(2, rfq_detailVO.getRfqdetail_no());

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
	public void delete(String rfqdetail_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rfqdetail_no);

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
	public RFQ_DetailVO findByPK(String rfqdetail_no) {
		
		RFQ_DetailVO rfq_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rfqdetail_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rfq_detailVO = new RFQ_DetailVO();
				rfq_detailVO.setRfqdetail_no(rs.getString("rfqdetail_no"));
				rfq_detailVO.setRfq_no(rs.getString("rfq_no"));
				rfq_detailVO.setStype_no(rs.getString("stype_no"));
				rfq_detailVO.setLocation(rs.getString("location"));
				rfq_detailVO.setSer_date(rs.getTimestamp("ser_date"));
				rfq_detailVO.setContent(rs.getString("content"));
				rfq_detailVO.setStatus(rs.getString("status"));
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
		return rfq_detailVO;
	}

	@Override
	public List<RFQ_DetailVO> getAll() {
		List<RFQ_DetailVO> list = new ArrayList<RFQ_DetailVO>();
		RFQ_DetailVO rfq_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rfq_detailVO = new RFQ_DetailVO();
				rfq_detailVO.setRfqdetail_no(rs.getString("rfqdetail_no"));
				rfq_detailVO.setRfq_no(rs.getString("rfq_no"));
				rfq_detailVO.setStype_no(rs.getString("stype_no"));
				rfq_detailVO.setLocation(rs.getString("location"));
				rfq_detailVO.setSer_date(rs.getTimestamp("ser_date"));
				rfq_detailVO.setContent(rs.getString("content"));
				rfq_detailVO.setStatus(rs.getString("status"));
				list.add(rfq_detailVO);
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
	public List<RFQ_DetailVO> getSome(String rfq_no) {
		List<RFQ_DetailVO> list = new ArrayList<RFQ_DetailVO>();
		RFQ_DetailVO rfq_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SOME_STMT);
			pstmt.setString(1, rfq_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rfq_detailVO = new RFQ_DetailVO();
				rfq_detailVO.setRfqdetail_no(rs.getString("rfqdetail_no"));
				rfq_detailVO.setRfq_no(rs.getString("rfq_no"));
				rfq_detailVO.setStype_no(rs.getString("stype_no"));
				rfq_detailVO.setLocation(rs.getString("location"));
				rfq_detailVO.setSer_date(rs.getTimestamp("ser_date"));
				rfq_detailVO.setContent(rs.getString("content"));
				rfq_detailVO.setStatus(rs.getString("status"));
				list.add(rfq_detailVO);
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
	public List<RFQ_DetailVO> getMy(String mem_no) {
		List<RFQ_DetailVO> list = new ArrayList<RFQ_DetailVO>();
		RFQ_DetailVO rfq_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MY_STMT);
			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rfq_detailVO = new RFQ_DetailVO();
				rfq_detailVO.setRfqdetail_no(rs.getString("rfqdetail_no"));
				rfq_detailVO.setRfq_no(rs.getString("rfq_no"));
				rfq_detailVO.setStype_no(rs.getString("stype_no"));
				rfq_detailVO.setLocation(rs.getString("location"));
				rfq_detailVO.setSer_date(rs.getTimestamp("ser_date"));
				rfq_detailVO.setContent(rs.getString("content"));
				rfq_detailVO.setStatus(rs.getString("status"));
				list.add(rfq_detailVO);
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
	
	
}
