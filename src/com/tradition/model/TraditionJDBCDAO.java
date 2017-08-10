package com.tradition.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.regexp.internal.recompile;



public class TraditionJDBCDAO implements Tradition_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "home";
	String passwd = "123";
	
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
		private static final String GET_ONE_ALL = "SELECT * FROM Tradition where tra_type_no = ?";
		
		
		
		
		@Override
		public void insert(TraditionVO traditionVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				
				pstmt.setInt(1, traditionVO.getTra_type_no());
				pstmt.setInt(2, traditionVO.getTra_order());
				pstmt.setString(3, traditionVO.getTitle());
				pstmt.setString(4, traditionVO.getArticle());
				
				
				Blob blob = con.createBlob();
				blob.setBytes(1, traditionVO.getImg());
				
				pstmt.setBlob(5, blob);
				
				
				pstmt.executeUpdate();

				
			} catch (SQLException | ClassNotFoundException se) {
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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

				
			} catch (SQLException | ClassNotFoundException se) {
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, tra_no);

				pstmt.executeUpdate();

				
			} catch (SQLException | ClassNotFoundException se) {
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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

				
			} catch (SQLException | ClassNotFoundException se) {
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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
					
					
					list.add(traditionVO); 
				}

				
			} catch (SQLException | ClassNotFoundException se) {
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
		@Override
		public TraditionVO getOneAll(Integer tra_type_no) {
			
			
			TraditionVO traditionVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_ALL);

				pstmt.setInt(1, tra_type_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					traditionVO = new TraditionVO();
					traditionVO.setTra_no(rs.getInt("tra_no"));
					traditionVO.setTra_type_no(rs.getInt("tra_type_no"));
					traditionVO.setTra_order(rs.getInt("tra_order"));
					traditionVO.setTitle(rs.getString("title"));
					traditionVO.setArticle(rs.getString("article"));
					
					
//					Blob blob = rs.getBlob("img");
//					int blobLength = (int) blob.length();
//					traditionVO.setImg(blob.getBytes(1, blobLength));
					
					
				}

				
			} catch (SQLException | ClassNotFoundException se) {
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
		
	
		
		
		
		public static void main(String[] args) throws IOException {

			TraditionJDBCDAO dao = new TraditionJDBCDAO();
			
			
			
			// 新增
//			TraditionVO traditionVO1 = new TraditionVO();
//			
//			traditionVO1.setTra_type_no(10);
//			traditionVO1.setTra_order(1);
//			traditionVO1.setTitle("?");
//			traditionVO1.setArticle("文章");
			
//			FileInputStream fis = new FileInputStream(new File("items/b03.jpg"));
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			byte[] buffer = new byte[8192];
//			int i;
//			while ((i = fis.read(buffer)) != -1) {
//				baos.write(buffer, 0, i);
//			}
//			baos.close();
//			fis.close();
//			
//			traditionVO1.setImg(baos.toByteArray());
//			dao.insert(traditionVO1);

//			// 修改
//			TraditionVO traditionVO2 = new TraditionVO();
//			
//			traditionVO2.setTra_type_no(10);
//			traditionVO2.setTra_order(2);
//			traditionVO2.setTitle("??");
//			traditionVO2.setArticle("章");
			
//			FileInputStream fis = new FileInputStream(new File("items/b02.jpg"));
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			byte[] buffer = new byte[8192];
//			int i;
//			while ((i = fis.read(buffer)) != -1) {
//				baos.write(buffer, 0, i);
//			}
//			baos.close();
//			fis.close();
//
//			traditionVO2.setImg(baos.toByteArray());	
//			traditionVO2.setTra_no(1);
//			dao.update(traditionVO2);
	//
//			// 刪除
//			dao.delete(2);
	//
//			// 查詢
//			TraditionVO traditionVO3 = dao.findByPrimaryKey(1);
//			System.out.print(traditionVO3.getTra_no() + ",");
//			System.out.print(traditionVO3.getTra_type_no()+ ",");
//			System.out.println(traditionVO3.getTra_order());
//			System.out.println(traditionVO3.getTitle()+",");
//			System.out.print(traditionVO3.getArticle()+ ",");
//			System.out.print(traditionVO3.getImg() + ",");
//			
//			System.out.println("---------------------");
	//
//			// 查詢
//			List<TraditionVO> list = dao.getAll();
//			for (TraditionVO tradition4 : list) {
//				System.out.print(tradition4.getTra_no() + ",");
//				System.out.print(tradition4.getTra_type_no() + ",");
//				System.out.print(tradition4.getTra_order());
//				System.out.println(tradition4.getTitle()+",");
//				System.out.print(tradition4.getArticle()+ ",");
//				System.out.print(tradition4.getImg() + ",");
//				
//				System.out.println();
		
			
			//查詢
			TraditionVO traditionVO3 = dao.getOneAll(10);
			System.out.print(traditionVO3.getTra_no() + ",");
			System.out.print(traditionVO3.getTra_type_no()+ ",");
			System.out.println(traditionVO3.getTra_order());
			System.out.println(traditionVO3.getTitle()+",");
			System.out.print(traditionVO3.getArticle()+ ",");
			System.out.print(traditionVO3.getImg() + ",");
			
			System.out.println("---------------------");
}
		
}
