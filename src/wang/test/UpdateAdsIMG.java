package wang.test;

import java.sql.*;
import java.io.*;

public class UpdateAdsIMG {
	private static final String UPDATE = "update advertising set img=? where com_no = ?";

	public static void main(String[] args) {

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "ProjectDB";
		String passwd = "projectdb";

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			for (int i = 1; i < 21; i++) {
				pstmt = con.prepareStatement(UPDATE);
				InputStream in = new FileInputStream(new File("C:\\Users\\cuser\\Desktop\\works_picture\\2002-" + (i+1) + ".jpg"));
				byte[] pic = new byte[in.available()];
				in.read(pic);
				in.close();
				pstmt.setBytes(1, pic);
				pstmt.setString(2, String.valueOf(2013 + i));
				System.out.println(String.valueOf(2014 + i));
				pstmt.executeUpdate();
			}
			System.out.println("done");

		} catch (IOException se) {
			se.printStackTrace(System.err);
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
}
