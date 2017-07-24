package com.eparam.model;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EparamJNDIDAO implements EparamDAO_Interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ProjectDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "insert into eparam (eparam_no, name, eparam_desc, value, eparam_date) values (ltrim(to_char(eparam_no_seq.nextval,'0009')), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "select eparam_no, name, eparam_desc, value, eparam_date from eparam order by eparam_no";
	private static final String GET_ONE_STMT = "select eparam_no, name, eparam_desc, value, eparam_date from eparam where eparam_no = ?";
	private static final String DELETE = "delete from eparam where eparam_no = ?";
	private static final String UPDATE = "update eparam set name=?, eparam_desc=?, value=?, eparam_date=? where eparam_no = ?";

	@Override
	public void insert(EparamVO eparamVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String[] cols = { "eparam_no" };
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, eparamVO.getName());
			pstmt.setString(2, eparamVO.getEparam_desc());
			pstmt.setDouble(3, eparamVO.getValue());
			pstmt.setTimestamp(4, eparamVO.getEparam_date());
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
	public void update(EparamVO eparamVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, eparamVO.getName());
			pstmt.setString(2, eparamVO.getEparam_desc());
			pstmt.setDouble(3, eparamVO.getValue());
			pstmt.setTimestamp(4, eparamVO.getEparam_date());
			pstmt.setString(5, eparamVO.getEparam_no());
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
	public void delete(String eparam_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, eparam_no);
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
	public EparamVO findByPrimaryKey(String eparam_no) {
		EparamVO eparamVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, eparam_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				eparamVO = new EparamVO();
				eparamVO.setEparam_no(rs.getString("eparam_no"));
				eparamVO.setName(rs.getString("name"));
				eparamVO.setEparam_desc(rs.getString("eparam_desc"));
				eparamVO.setValue(rs.getDouble("value"));
				eparamVO.setEparam_date(rs.getTimestamp("eparam_date"));
			}

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
		return eparamVO;
	}

	@Override
	public List<EparamVO> getAll() {
		List<EparamVO> list = new ArrayList<EparamVO>();
		EparamVO eparamVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				eparamVO = new EparamVO();
				eparamVO.setEparam_no(rs.getString("eparam_no"));
				eparamVO.setName(rs.getString("name"));
				eparamVO.setEparam_desc(rs.getString("eparam_desc"));

				eparamVO.setValue(rs.getDouble("value"));
				eparamVO.setEparam_date(rs.getTimestamp("eparam_date"));
				list.add(eparamVO);
			}
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
		return list;
	}
}
