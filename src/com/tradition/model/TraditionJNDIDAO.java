package com.tradition.model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
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



public class TraditionJNDIDAO implements Tradition_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO Tradition (tra_no,tra_type_no,tra_order,title,Article,Img) VALUES (lpad(tra_no_sq.NEXTVAL,4,'0'), ?, ?,?, ?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM Tradition order by tra_no";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM Tradition where tra_no = ?";
		private static final String DELETE = 
			"DELETE FROM Tradition where tra_no = ?";
		private static final String UPDATE = 
			"UPDATE Tradition set tra_type_no=?,tra_order=?, title=?,Article=?, Img=? where tra_no=?";
			
		
		
		
		
		@Override
		public void insert(TraditionVO traditionVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				
				pstmt.setInt(1, traditionVO.getTra_type_no());
				pstmt.setInt(2, traditionVO.getTra_order());
				pstmt.setString(3, traditionVO.getTitle());
				pstmt.setString(4, traditionVO.getArticle());
				
				
				Blob blob = con.createBlob();
				blob.setBytes(1, traditionVO.getImg());
				
				pstmt.setBlob(5, blob);
				
				pstmt.executeUpdate();

				
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				
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
		public void update(TraditionVO traditionVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, traditionVO.getTra_type_no());
				pstmt.setInt(2, traditionVO.getTra_order());
				pstmt.setString(3, traditionVO.getTitle());
				pstmt.setString(4, traditionVO.getArticle());
				
				Blob blob = con.createBlob();
				blob.setBytes(1, traditionVO.getImg());
				
				pstmt.setBlob(5, blob);
				pstmt.setInt(6, traditionVO.getTra_no());
				
				
				pstmt.executeUpdate();


				
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				
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
		public void delete(Integer tra_no) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, tra_no);

				pstmt.executeUpdate();

				
			} catch (SQLException  se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				
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
		public TraditionVO findByPrimaryKey(Integer tra_no) {

			TraditionVO traditionVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, tra_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					traditionVO = new TraditionVO();
					traditionVO.setTra_no(rs.getInt("tra_no"));
					traditionVO.setTra_type_no(rs.getInt("tra_type_no"));
					traditionVO.setTra_order(rs.getInt("tra_order"));
					traditionVO.setTitle(rs.getString("title"));
					traditionVO.setArticle(rs.getString("article"));
					

					Blob blob = rs.getBlob("img");
					int blobLength = (int) blob.length();
					traditionVO.setImg(blob.getBytes(1, blobLength));
					
					
				}

				
			} catch (SQLException  se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				
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
			return traditionVO;
		}
		@Override
		public List<TraditionVO> getAll() {
			List<TraditionVO> list = new ArrayList<TraditionVO>();
			TraditionVO traditionVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					traditionVO = new TraditionVO();
					traditionVO.setTra_no(rs.getInt("tra_no"));
					traditionVO.setTra_type_no(rs.getInt("tra_type_no"));
					traditionVO.setTra_order(rs.getInt("tra_order"));
					traditionVO.setTitle(rs.getString("title"));
					traditionVO.setArticle(rs.getString("article"));

					Blob blob = rs.getBlob("img");
					int blobLength = (int) blob.length();
					traditionVO.setImg(blob.getBytes(1, blobLength));
					
				
					list.add(traditionVO); // Store the row in the list
				}

				
			} catch (SQLException  se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				
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
