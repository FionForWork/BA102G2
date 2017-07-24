package com.place.model;

import java.sql.*;
import java.util.*;

public class PlaceJDBCDAO implements PlaceDAO_Interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "ProjectDB";
	String passwd = "projectdb";

	private static final String INSERT_STMT = "insert into place (pla_no, name, img, lat, lng, addr) values (ltrim(to_char(pla_no_seq.NEXTVAL,'0009')), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "select pla_no,name,img,lat,lng,addr from place order by pla_no";
	private static final String GET_ONE_STMT = "select pla_no,name,img,lat,lng,addr from place where pla_no = ?";
	private static final String DELETE = "delete from place where pla_no = ?";
	private static final String UPDATE = "update place set name=?, img=?, lat=?, lng=?, addr=? where pla_no = ?";

	@Override
	public void insert(PlaceVO placeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String[] cols = { "pla_no" };
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, placeVO.getName());

			Blob blob = con.createBlob();
			blob.setBytes(1, placeVO.getImg());
			pstmt.setBlob(2, blob);

			pstmt.setDouble(3, placeVO.getLat());
			pstmt.setDouble(4, placeVO.getLng());
			pstmt.setString(5, placeVO.getAddr());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(PlaceVO placeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, placeVO.getName());

			Blob blob = con.createBlob();
			blob.setBytes(1, placeVO.getImg());
			pstmt.setBlob(2, blob);

			pstmt.setDouble(3, placeVO.getLat());
			pstmt.setDouble(4, placeVO.getLng());
			pstmt.setString(5, placeVO.getAddr());
			pstmt.setString(6, placeVO.getPla_no());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(String pla_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, pla_no);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public PlaceVO findByPrimaryKey(String pla_no) {
		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, pla_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				placeVO = new PlaceVO();
				placeVO.setPla_no(rs.getString("pla_no"));
				placeVO.setName(rs.getString("name"));
				placeVO.setImg(rs.getBytes("img"));
				placeVO.setLat(rs.getDouble("lat"));
				placeVO.setLng(rs.getDouble("lng"));
				placeVO.setAddr(rs.getString("addr"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return placeVO;
	}

	@Override
	public List<PlaceVO> getAll() {
		List<PlaceVO> list = new ArrayList<PlaceVO>();
		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				placeVO = new PlaceVO();
				placeVO.setPla_no(rs.getString("pla_no"));
				placeVO.setName(rs.getString("name"));
				placeVO.setImg(rs.getBytes("img"));
				placeVO.setLat(rs.getDouble("lat"));
				placeVO.setLng(rs.getDouble("lng"));
				placeVO.setAddr(rs.getString("addr"));
				list.add(placeVO);
			}
		} catch (

		ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
}
