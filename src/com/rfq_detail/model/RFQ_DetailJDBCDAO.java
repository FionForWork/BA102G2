package com.rfq_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RFQ_DetailJDBCDAO implements RFQ_DetailDAO_Interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "group2";
	String passwd = "group2";
	
	private static final String INSERT = 
			"INSERT INTO RFQ_DETAIL VALUES (LTRIM(TO_CHAR(RFQDETAIL_SQ.NEXTVAL,'0009')), ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE RFQ_DETAIL SET STATUS=? WHERE RFQDETAIL_NO = ?";
	private static final String DELETE = 
			"DELETE FROM RFQ_DETAIL where RFQDETAIL_NO = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM RFQ_DETAIL where RFQDETAIL_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM RFQ_DETAIL order by RFQDETAIL_NO ";
	private static final String GET_SOME_STMT = 
			"SELECT * FROM RFQ_DETAIL where status = '1' and rfq_no = ? order by SER_DATE DESC ";
	private static final String GET_MY_STMT = 
			"SELECT * FROM RFQ_DETAIL where rfq_no in (select rfq_no from RFQ where mem_no = ?) order by status";
	private static final String GET_FROM_QUOTE_STMT = 
			"SELECT * FROM RFQ_DETAIL where rfq_no in (select rfq_no from RFQ where mem_no = ?) order by status";
	private static final String GET_ONE_FROM_QUOTE = 
			"SELECT * FROM RFQ_DETAIL where RFQDETAIL_NO = (select RFQDETAIL_NO from quote where Quo_No = ?)";
	
	@Override
	public void insert(RFQ_DetailVO rfq_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1,rfq_detailVO.getRfq_no());
			pstmt.setString(2, rfq_detailVO.getStype_no());
			pstmt.setString(3, rfq_detailVO.getLocation());
			pstmt.setTimestamp(4, rfq_detailVO.getSer_date());
			pstmt.setString(5, rfq_detailVO.getContent());
			pstmt.setString(6, rfq_detailVO.getStatus());
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
	public void update(RFQ_DetailVO rfq_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rfq_detailVO.getStatus());
			pstmt.setString(2, rfq_detailVO.getRfqdetail_no());

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
	public void delete(String rfqdetail_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rfqdetail_no);

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
	public RFQ_DetailVO findByPK(String rfqdetail_no) {
		
		RFQ_DetailVO rfq_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				rfq_detailVO.setTitle(rs.getString("title"));
				rfq_detailVO.setContent(rs.getString("content"));
				rfq_detailVO.setStatus(rs.getString("status"));
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<RFQ_DetailVO> getSome(String rfq_no) {
		List<RFQ_DetailVO> list = new ArrayList<RFQ_DetailVO>();
		RFQ_DetailVO rfq_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<RFQ_DetailVO> getMy(String mem_no) {
		List<RFQ_DetailVO> list = new ArrayList<RFQ_DetailVO>();
		RFQ_DetailVO rfq_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public RFQ_DetailVO getOneFromQuote(String quo_no) {
		RFQ_DetailVO rfq_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_FROM_QUOTE);

			pstmt.setString(1, quo_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rfq_detailVO = new RFQ_DetailVO();
				rfq_detailVO.setRfqdetail_no(rs.getString("rfqdetail_no"));
				rfq_detailVO.setRfq_no(rs.getString("rfq_no"));
				rfq_detailVO.setStype_no(rs.getString("stype_no"));
				rfq_detailVO.setLocation(rs.getString("location"));
				rfq_detailVO.setSer_date(rs.getTimestamp("ser_date"));
				rfq_detailVO.setTitle(rs.getString("title"));
				rfq_detailVO.setContent(rs.getString("content"));
				rfq_detailVO.setStatus(rs.getString("status"));
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
		return rfq_detailVO;
	}

	
	
public static void main(String args[]){
		
	RFQ_DetailJDBCDAO dao = new RFQ_DetailJDBCDAO();
		
//	RFQ_DetailVO rfq_detailVO = new RFQ_DetailVO();
//	rfq_detailVO.setRfq_no("0001");;
//	rfq_detailVO.setStype_no("0001");
//	rfq_detailVO.setLocation("台北市");
//	Timestamp t = new Timestamp(System.currentTimeMillis());
//	rfq_detailVO.setSer_date(t);
//	rfq_detailVO.setTitle("找婚紗");
//	rfq_detailVO.setContent("要兩件婚紗");
//	rfq_detailVO.setStatus("1");
//	dao.insert(rfq_detailVO);
	
//	RFQ_DetailVO rfq_detailVO = new RFQ_DetailVO();
//	rfq_detailVO.setStatus("0");
//	rfq_detailVO.setRfqdetail_no("0005");
//	dao.update(rfq_detailVO);
	
	
		
//	dao.delete("0003");
		
//	RFQ_DetailVO rfq_detailVO = dao.findByPK("0005");
//	System.out.println(rfq_detailVO.getRfqdetail_no());
//	System.out.println(rfq_detailVO.getRfq_no());
//	System.out.println(rfq_detailVO.getStype_no());
//	System.out.println(rfq_detailVO.getLocation());
//	System.out.println(rfq_detailVO.getSer_date());
//	System.out.println(rfq_detailVO.getTitle());
//	System.out.println(rfq_detailVO.getContent());
//	System.out.println(rfq_detailVO.getStatus());
		
		List<RFQ_DetailVO> list = dao.getMy("1001");
		
		for(RFQ_DetailVO rfq_detailVO : list){
			System.out.println(rfq_detailVO.getRfqdetail_no());
			System.out.println(rfq_detailVO.getRfq_no());
			System.out.println(rfq_detailVO.getStype_no());
			System.out.println(rfq_detailVO.getLocation());
			System.out.println(rfq_detailVO.getSer_date());
			System.out.println(rfq_detailVO.getContent());
			System.out.println(rfq_detailVO.getStatus());
		}

	
	}




	
}
