package admin.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

	private static LoginDAO logDAO;

	private LoginDAO() {

	}

	public static LoginDAO getInstance() {
		if (logDAO == null) {
			logDAO = new LoginDAO();
		}
		return logDAO;
	}// getInstance

	private Connection getConnection() throws SQLException {
		Connection con = null;

		try {
			Class.forName("oracle.jdbc.OracleDriver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // catch

		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "shopper";
		String pw = "shmall";

		con = DriverManager.getConnection(url, id, pw);

		return con;
	}// getConnection

	
	
//	public void insertNonMem(String ip) throws SQLException {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = getConnection();
//			StringBuilder insertIp = new StringBuilder();
//			insertIp.append("		insert into non_member(nm_ip) values(?)	");
//
//			pstmt = con.prepareStatement(insertIp.toString());
//
//			pstmt.setString(1, ip);
//
//			pstmt.execute();
//		} finally {
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (con != null) {
//				con.close();
//			}
//		} // end finally
//	}// insertNonMem
//	
	public String selectAdminId(LoginVO lVO) throws SQLException {
		String name = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			StringBuilder selectIdChk = new StringBuilder();
			
			selectIdChk
			.append("		select a_id 	")
			.append("		from admin 	")
			.append("		where a_id=? and a_pass =?	");
			
			pstmt = con.prepareStatement(selectIdChk.toString());
			pstmt.setString(1, lVO.getId());
			pstmt.setString(2, lVO.getPw());
			
			rs= pstmt.executeQuery();
			if(rs.next()) {
				name= rs.getString("a_id");
			}// end
			
		}finally {
			
		}// end finally
		return name;
	}// selectAdminId

}// class
