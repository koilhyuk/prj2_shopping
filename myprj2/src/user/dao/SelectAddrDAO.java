package user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import user.vo.selectZipcodeVO;
import user.vo.login.CheckAddrVO;
import user.vo.login.SearchAddrVO;

public class SelectAddrDAO {

	private static SelectAddrDAO saDAO;

	private SelectAddrDAO() {

	}

	public static SelectAddrDAO getInstance() {
		if (saDAO == null) {
			saDAO = new SelectAddrDAO();
		}
		return saDAO;
	}

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

	public List<SearchAddrVO> selectAddr(CheckAddrVO caVO) throws SQLException {
		String sido = "";
		String gugun = "";
		List<SearchAddrVO> selectAddrData = new ArrayList<SearchAddrVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sido = caVO.getSido().trim();
			gugun = caVO.getGugun().trim();

			con = getConnection();

			StringBuilder selectAddrQue = new StringBuilder();
			selectAddrQue.append("	select z_zipcode, z_addr	").append(
					"	from (select concat(concat(concat(z_sido,' '||nvl(z_gugun,' ')),' '||z_dong),' '||z_bunji) z_addr, z_seq,  z_zipcode, z_sido,nvl(z_gugun,' ') z_gugun,z_bunji from address)	")
					.append("	where z_sido like '%'||?||'%' and z_gugun like '%'||?||'%' and z_addr like '%'||?||'%'  order by z_zipcode	");

			pstmt = con.prepareStatement(selectAddrQue.toString());

			if (!sido.equals("전체")) {
				pstmt.setString(1, sido);
			} else {
				pstmt.setString(1, "");
			} // end if

			if (!gugun.equals("전체")) {
				pstmt.setString(2, gugun);
			} else {
				pstmt.setString(2, "");
			} // end if

			pstmt.setString(3, caVO.getInputAddr());

			rs = pstmt.executeQuery();

			SearchAddrVO saVO = null;
			while (rs.next()) {
				saVO = new SearchAddrVO(rs.getString("z_zipcode"), rs.getString("z_addr"));
				selectAddrData.add(saVO);
			} // end while

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally

		return selectAddrData;

	}// selectAddr

	public List<String> selectGugun(String sidoName) throws SQLException {
		List<String> gugunData = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = getConnection();

			StringBuilder selectGugun = new StringBuilder();
			selectGugun.append("		select z_gugun from address 	")
					.append("		where z_sido like '%'||?||'%'		group by z_gugun order by z_gugun ");

			pstmt = con.prepareStatement(selectGugun.toString());

			pstmt.setString(1, sidoName);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				gugunData.add(rs.getString("z_gugun").trim());
			} // end while

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally

		return gugunData;
	}// selectGugun

	public int seqSearch(String zipcode, String addr) throws SQLException {
		int seq = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = getConnection();

			StringBuilder selectSeq = new StringBuilder();
			selectSeq.append("	select z_seq	").append(
					"	from (select concat(concat(concat(z_sido,' '||nvl(z_gugun,' ')),' '||z_dong),' '||z_bunji) z_addr, z_seq, z_zipcode from address)	")
					.append("	where z_zipcode like '%'||?||'%'  and z_addr like '%'||?||'%'	");

			pstmt = con.prepareStatement(selectSeq.toString());

			pstmt.setString(1, zipcode);
			pstmt.setString(2, addr);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				seq = rs.getInt("z_seq");
			} // end while

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally

		return seq;
	}// seqSearch

	public selectZipcodeVO zipCodeSearch(int seq) throws SQLException {
		selectZipcodeVO szVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			StringBuilder selectZipcode = new StringBuilder();
			selectZipcode.append(
					"	select concat(concat(concat(z_sido,' '||nvl(z_gugun,' ')),' '||z_dong),' '||z_bunji) z_addr, z_seq, z_zipcode	")
					.append("	from address	").append("	where  z_seq=?	");

			pstmt = con.prepareStatement(selectZipcode.toString());

			pstmt.setInt(1, seq);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				szVO = new selectZipcodeVO(rs.getString("z_addr"), rs.getString("z_zipcode"));
			} // end while

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
		return szVO;
	}// end zipCodeSearch

}// class
