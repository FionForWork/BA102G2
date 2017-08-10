package com.works.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class WorksDAO implements WorksDAO_Interface {

	private static final String INSERT_SQL = "insert into works(works_no,com_no,name,works_desc,img,vdo,upload_date) "
			+ "values(ltrim(To_char(works_sq.nextval,'0009')),?,?,?,?,?,?)";
	private static final String DELETE_SQL = "delete from works where works_no = ?";
	private static final String UPDATE_SQL = "update works set com_no=?, name=?, works_desc=? ,img=?,vdo=?,upload_date=? where works_no = ?";
	private static final String FIND_BY_PK = "select * from works where works_no = ?";
	private static final String FIND_BY_COM_NO = "select works_no,com_no,name,works_desc,img,upload_date from works where com_no = ? order by upload_date desc";
	private static final String FIND_ALL = "select * from works";
	private static final String COUNT_SQL ="select count(*) from works where com_no = ? ";

	private static DataSource ds = null;

	static {
		Context ctx;
		try {
			ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String insertWorks(WorksVO works) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String[] cols = { "works_no" };
		String works_no = "";
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(INSERT_SQL, cols);
			pstmt.setString(1, works.getCom_no());
			pstmt.setString(2, works.getName());
			Clob clob = (Clob) conn.createClob();
			clob.setString(1, works.getWorks_desc());
			pstmt.setClob(3, clob);
			pstmt.setBytes(4, works.getImg());
			pstmt.setBytes(5, works.getVdo());
			pstmt.setTimestamp(6, works.getUpload_date());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				works_no = rs.getString(1);
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return works_no;
	}

	@Override
	public void deleteWorks(String works_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(DELETE_SQL);
			pstmt.setString(1, works_no);
			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void updateWorks(WorksVO works) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, works.getCom_no());
			pstmt.setString(2, works.getName());
			Clob clob = conn.createClob();
			clob.setString(1, works.getWorks_desc());
			pstmt.setClob(3, clob);
			pstmt.setBytes(4, works.getImg());
			pstmt.setBytes(5, works.getVdo());
			pstmt.setTimestamp(6, works.getUpload_date());
			pstmt.setString(7, works.getWorks_no());
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public WorksVO findWorksByPK(String works_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WorksVO works = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, works_no);
			rs = pstmt.executeQuery();
			rs.next();
			works = new WorksVO();
			works.setWorks_no(rs.getString("WORKS_NO"));
			works.setCom_no(rs.getString("COM_NO"));
			works.setName(rs.getString("NAME"));
			works.setUpload_date(rs.getTimestamp("UPLOAD_DATE"));
			works.setImg(rs.getBytes("IMG"));
			works.setVdo(rs.getBytes("VDO"));
			works.setWorks_desc(readString(rs.getCharacterStream("WORKS_DESC")));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return works;
	}

	@Override
	public List<WorksVO> findWorksByComNo(String com_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WorksVO works = null;
		List<WorksVO> worksList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_BY_COM_NO);
			pstmt.setString(1, com_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				works = new WorksVO();
				works.setWorks_no(rs.getString("WORKS_NO"));
				works.setCom_no(rs.getString("COM_NO"));
				works.setName(rs.getString("NAME"));
				works.setUpload_date(rs.getTimestamp("UPLOAD_DATE"));
				works.setImg(rs.getBytes("IMG"));
				works.setWorks_desc(readString(rs.getCharacterStream("WORKS_DESC")));
				worksList.add(works);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return worksList;
	}

	@Override
	public List<WorksVO> findAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		WorksVO works = null;
		List<WorksVO> worksList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(FIND_ALL);
			while (rs.next()) {
				works = new WorksVO();
				works.setWorks_no(rs.getString("WORKS_NO"));
				works.setCom_no(rs.getString("COM_NO"));
				works.setName(rs.getString("NAME"));
				works.setUpload_date(rs.getTimestamp("UPLOAD_DATE"));
				works.setImg(rs.getBytes("IMG"));
				works.setVdo(rs.getBytes("VDO"));
				works.setWorks_desc(readString(rs.getCharacterStream("WORKS_DESC")));
				worksList.add(works);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return worksList;
	}

	@Override
	public int countWorksInOneComNo(String com_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfCont = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(COUNT_SQL);
			pstmt.setString(1, com_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfCont = rs.getInt(1);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return numberOfCont;
		
	}
	private static String readString(Reader reader) throws IOException{
		if(reader != null){
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(reader);
			String str;
			while((str = br.readLine()) != null){
				sb.append(str);
				sb.append("\n");
			}
			br.close();
			return sb.toString();
		}else{
			return null;
		}
		
	}
		
}
