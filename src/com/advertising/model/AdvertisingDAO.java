package com.advertising.model;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class AdvertisingDAO implements AdvertisingDAO_Interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "insert into advertising (adv_no, com_no,title, startday, endday, price, text, img, vdo, status) values (ltrim(to_char(adv_no_seq.nextval,'0009')), ?, ?, ?, ?, ?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = "select adv_no, com_no,title, startday, endday, price, text, img, vdo, status from advertising order by adv_no";
	private static final String GET_ONE_STMT = "select adv_no, com_no,title, startday, endday, price, text, img, vdo, status from advertising where adv_no = ?";
	private static final String DELETE = "delete from advertising where adv_no = ?";
	private static final String UPDATE = "update advertising set com_no=?,title=?, startday=?, endday=?, price=?, text=?, img=?, vdo=?, status=? where adv_no = ?";
	private static final String GET_ONE_ALL = "select adv_no, com_no, title,startday, endday, price, text, img, vdo, status from advertising where com_no = ?";

	@Override
	public void insert(AdvertisingVO advertisingVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String[] cols = { "adv_no" };
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, advertisingVO.getCom_no());
			pstmt.setString(2, advertisingVO.getTitle());
			pstmt.setTimestamp(3, advertisingVO.getStartDay());
			pstmt.setTimestamp(4, advertisingVO.getEndDay());
			pstmt.setInt(5, advertisingVO.getPrice());

			Clob clob = con.createClob();
			String str = advertisingVO.getText();
			clob.setString(1, str);
			pstmt.setClob(6, clob);

			Blob blob1 = con.createBlob();
			blob1.setBytes(1, advertisingVO.getImg());
			pstmt.setBlob(7, blob1);

			Blob blob2 = con.createBlob();
			blob2.setBytes(1, advertisingVO.getVdo());
			pstmt.setBlob(8, blob2);

			pstmt.setString(9, advertisingVO.getStatus());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(AdvertisingVO advertisingVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, advertisingVO.getCom_no());
			pstmt.setString(2, advertisingVO.getTitle());
			pstmt.setTimestamp(3, advertisingVO.getStartDay());
			pstmt.setTimestamp(4, advertisingVO.getEndDay());
			pstmt.setInt(5, advertisingVO.getPrice());

			Clob clob = con.createClob();
			String str = advertisingVO.getText();
			clob.setString(1, str);
			pstmt.setClob(6, clob);

			Blob blob1 = con.createBlob();
			blob1.setBytes(1, advertisingVO.getImg());
			pstmt.setBlob(7, blob1);

			Blob blob2 = con.createBlob();
			blob2.setBytes(1, advertisingVO.getVdo());
			pstmt.setBlob(8, blob2);
			pstmt.setString(9, advertisingVO.getStatus());
			pstmt.setString(10, advertisingVO.getAdv_no());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String adv_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, adv_no);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public AdvertisingVO findByPrimaryKey(String adv_no) {
		AdvertisingVO advertisingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, adv_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				advertisingVO = new AdvertisingVO();
				advertisingVO.setAdv_no(rs.getString("adv_no"));
				advertisingVO.setCom_no(rs.getString("com_no"));
				advertisingVO.setTitle(rs.getString("title"));
				advertisingVO.setStartDay(rs.getTimestamp("startday"));
				advertisingVO.setEndDay(rs.getTimestamp("endday"));
				advertisingVO.setPrice(rs.getInt("price"));

				Clob clob = rs.getClob("text");
				StringBuilder sb = new StringBuilder();
				BufferedReader br = new BufferedReader(clob.getCharacterStream());
				String str;
				while ((str = br.readLine()) != null) {
					sb.append(str);
					sb.append("\n");
				}
				br.close();
				advertisingVO.setText(sb.toString());

				advertisingVO.setImg(rs.getBytes("img"));
				advertisingVO.setVdo(rs.getBytes("vdo"));
				advertisingVO.setStatus(rs.getString("status"));
			}
		} catch (IOException e) {
			System.out.println(e);
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return advertisingVO;
	}

	@Override
	public List<AdvertisingVO> getAll() {
		List<AdvertisingVO> list = new ArrayList<AdvertisingVO>();
		AdvertisingVO advertisingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				advertisingVO = new AdvertisingVO();
				advertisingVO.setAdv_no(rs.getString("adv_no"));
				advertisingVO.setCom_no(rs.getString("com_no"));
				advertisingVO.setTitle(rs.getString("title"));
				advertisingVO.setStartDay(rs.getTimestamp("startday"));
				advertisingVO.setEndDay(rs.getTimestamp("endday"));
				advertisingVO.setPrice(rs.getInt("price"));

				Clob clob = rs.getClob("text");
				StringBuilder sb = new StringBuilder();
				BufferedReader br = new BufferedReader(clob.getCharacterStream());
				String str;
				while ((str = br.readLine()) != null) {
					sb.append(str);
					sb.append("\n");
				}
				br.close();
				advertisingVO.setText(sb.toString());

				advertisingVO.setImg(rs.getBytes("img"));
				advertisingVO.setVdo(rs.getBytes("vdo"));
				advertisingVO.setStatus(rs.getString("status"));
				list.add(advertisingVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (IOException ie) {
			System.out.println(ie);
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
	public List<AdvertisingVO> getOneAll(String com_no) {
		List<AdvertisingVO> list = new ArrayList<AdvertisingVO>();
		AdvertisingVO advertisingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ALL);
			pstmt.setString(1, com_no);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				advertisingVO = new AdvertisingVO();
				advertisingVO.setAdv_no(rs.getString("adv_no"));
				advertisingVO.setCom_no(rs.getString("com_no"));
				advertisingVO.setTitle(rs.getString("title"));
				advertisingVO.setStartDay(rs.getTimestamp("startday"));
				advertisingVO.setEndDay(rs.getTimestamp("endday"));
				advertisingVO.setPrice(rs.getInt("price"));

				Clob clob = rs.getClob("text");
				StringBuilder sb = new StringBuilder();
				BufferedReader br = new BufferedReader(clob.getCharacterStream());
				String str;
				while ((str = br.readLine()) != null) {
					sb.append(str);
					sb.append("\n");
				}
				br.close();
				advertisingVO.setText(sb.toString());
				advertisingVO.setImg(rs.getBytes("img"));
				advertisingVO.setVdo(rs.getBytes("vdo"));
				advertisingVO.setStatus(rs.getString("status"));
				list.add(advertisingVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (IOException ie) {
			System.out.println(ie);
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
	
}