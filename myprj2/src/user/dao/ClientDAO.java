package user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import user.vo.CalcVO;
import user.vo.content.MyPageDetailVO;
import user.vo.content.MyPageUpdateVO;
import user.vo.login.JoinDetailVO;
import user.vo.login.LoginFoundIdVO;
import user.vo.login.LoginFoundPwVO;
import user.vo.login.LoginPwResetVO;
import user.vo.login.LoginUpdateIpVO;
import user.vo.login.LoginVO;

public class ClientDAO {
	private static ClientDAO cDAO;

	private ClientDAO() {

	}// ClientDAO

	public static ClientDAO getInstance() {
		if (cDAO == null) {
			cDAO = new ClientDAO();
		} // end if
		return cDAO;
	}// getInstance

	// 반환하는 일
	private Connection getConnection() throws SQLException {
		Connection con = null;

		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // end catch

		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "shopper";
		String pass = "shmall";

		con = DriverManager.getConnection(url, id, pass);

		return con;
	}// getcConnection

	public String selectLogin(LoginVO lv) throws SQLException {
		String ClientId = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectName = new StringBuilder();
			selectName.append(" select m_id ").append(" from member ").append(" where m_stop_flag='Y' and m_id=? and m_pass=? ");

			pstmt = con.prepareStatement(selectName.toString());
			pstmt.setString(1, lv.getId());
			pstmt.setString(2, lv.getPass());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				ClientId = rs.getString("m_id");
			} // end if
		} finally {
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally
		return ClientId;
	}// selectLogin

//	public boolean selectJoin(JoinDetailVO jdVO) throws SQLException {
//		boolean joinFlag = false;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			con = getConnection();
//			StringBuilder joinDetail = new StringBuilder();
//			joinDetail.append("  select m_id, m_pass, m_name, m_phone, m_addr, m_email ").append(" from member ")
//					.append("  where m_id=? and m_pass=? and m_name=? and m_phone=? and m_addr=? and m_email=? ");
//
//			pstmt = con.prepareStatement(joinDetail.toString());
//			pstmt.setString(1, jdVO.getId());
//			pstmt.setString(2, jdVO.getPass());
//			pstmt.setString(3, jdVO.getName());
//			pstmt.setString(4, jdVO.getPhone());
//			pstmt.setString(5, jdVO.getAddr());
//			pstmt.setString(6, jdVO.getEmail());
//
//			joinFlag = pstmt.executeUpdate() == 1;
//		} finally {
//			if (pstmt != null) {
//				pstmt.close();
//			} // end if
//			if (con != null) {
//				con.close();
//			} // end if
//		} // end finally
//		return joinFlag;
//	}// selectJoin

	public String selectIdFound(LoginFoundIdVO lfiv) throws SQLException {
		String IdFound = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectIdFound = new StringBuilder();
			selectIdFound.append("  select m_id ").append("  from member ").append("  where m_stop_flag='Y' and m_name=? and m_phone=? ");

			pstmt = con.prepareStatement(selectIdFound.toString());
			pstmt.setString(1, lfiv.getName());
			pstmt.setString(2, lfiv.getPhone());
			rs = pstmt.executeQuery();

			if (rs.next()) {

				IdFound = rs.getString("m_id");
			} // end if
		} finally {
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally
		return IdFound;
	}// selectIdFound

	public Boolean selectPwFound(LoginFoundPwVO lfpv) throws SQLException {
		boolean flag= false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectPwCheck = new StringBuilder();
			selectPwCheck.append(" select m_id ,m_name,m_phone ").append(" from member ")
					.append(" where m_stop_flag='Y' and  m_name=? and m_id=? and m_phone=? ");

			pstmt = con.prepareStatement(selectPwCheck.toString());
			pstmt.setString(1, lfpv.getName());
			pstmt.setString(2, lfpv.getId());
			pstmt.setString(3, lfpv.getPhone());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				lfpv= new LoginFoundPwVO(rs.getString("m_name"), rs.getString("m_id"), rs.getString("m_phone"));
				flag=true;
			} // end if
		} finally {
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally
		return flag;
	}// selectPwFound

	public void selectMypageInfo(MyPageDetailVO mpdVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 2.
			con = getConnection();
			// 3.
			StringBuilder selectLunch = new StringBuilder();
			selectLunch.append("	 select	m_id, m_pass, m_detail_addr, m_birth, m_phone ,m_email ")
					.append("	 from		member ")
					.append(" where	m_id=? and m_pass=? and m_detail_addr=? and m_birth=? and m_phone=? and m_email=? ");

			pstmt = con.prepareStatement(selectLunch.toString());
			// 4.
			pstmt.setString(1, mpdVO.getId());
			pstmt.setString(2, mpdVO.getPass());
			pstmt.setString(3, mpdVO.getAddr());
			pstmt.setString(4, mpdVO.getBirth());
			pstmt.setString(5, mpdVO.getPhoneBehind() + mpdVO.getPhoneFront());
			pstmt.setString(6, mpdVO.getEmail());

			// 5.
			rs = pstmt.executeQuery();

			if (rs.next()) {
				mpdVO.setId(rs.getString("id"));
				mpdVO.setPass(rs.getString("pass"));
				mpdVO.setAddr(rs.getString("addr"));
				mpdVO.setBirth(rs.getString("birth"));
				mpdVO.setPhoneFront(rs.getString("phone"));
				mpdVO.setPhoneBehind(rs.getString("phone"));
				mpdVO.setEmail(rs.getString("email"));

			} // end if

		} finally {
			// 6
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally
	}// selectMypageInfo

	public boolean updateResetPw(LoginPwResetVO lprv) throws SQLException {
		boolean ResetPwFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			StringBuilder updatePw = new StringBuilder();
			updatePw.append(" update member ").append(" set m_pass=? ").append(" where m_stop_flag='Y' and  m_id=? ");

			pstmt = con.prepareStatement(updatePw.toString());
			pstmt.setString(1, lprv.getPass());
			pstmt.setString(2, lprv.getId());
			ResetPwFlag = (pstmt.executeUpdate() == 1);
		} finally {
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally
		return ResetPwFlag;
	}// updateStatus

	public boolean updateMyPage(MyPageUpdateVO mpuv) throws SQLException {
		boolean updateInfoFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			StringBuilder updateInfo = new StringBuilder();
			updateInfo.append(" update member ")
					.append(" set m_pass=? and m_detail_addr=? and m_email=? and m_phone=? ").append(" where m_id=? ");

			pstmt = con.prepareStatement(updateInfo.toString());
			pstmt.setString(1, mpuv.getId());
			pstmt.setString(2, mpuv.getPass());
			pstmt.setString(3, mpuv.getAddr());
			pstmt.setString(4, mpuv.getEmail());
			pstmt.setString(5, mpuv.getPhone());
			updateInfoFlag = pstmt.executeUpdate() == 1;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally
		return updateInfoFlag;
	}// updateStatus

	public List<CalcVO> selectCalc() throws SQLException {
		List<CalcVO> list = new ArrayList<CalcVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectCalc = new StringBuilder();
			selectCalc.append(
					"	 select m_id,m_ip, m_code, m_name, m_pass, m_birth, m_gender, m_phone, m_addr, m_email, m_joindate, m_totalmoney ")
					.append(" from id m, pass m ").append(" where member ");

			pstmt = con.prepareStatement(selectCalc().toString());
			rs = pstmt.executeQuery();
			CalcVO cv = null;

			while (rs.next()) {
				cv = new CalcVO(rs.getString("id"), rs.getString("ip"), rs.getString("code"), rs.getString("name"),
						rs.getString("pass"), rs.getString("birth"), rs.getString("gender"), rs.getString("phone"),
						rs.getString("addr"), rs.getString("email"), rs.getString("joindate"), rs.getInt("totalmoney"));
				list.add(cv);
			} // end if
		} finally {
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally
		return list;
	}

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

//	public boolean updateMyPage(myPageUpdateVO mpuVO) throws SQLException {
//		boolean updateFlag = false;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			con = getConnection();
//			StringBuilder updateMyPage = new StringBuilder();
//			updateMyPage.append("		update myPage ")
//					.append("		set pass=?, newPass=?, addr=?, phoneFront=?, phoneBehind=?, email=? ")
//					.append("		where myPageDetailView=?	");
//
//			pstmt = con.prepareStatement(updateMyPage.toString());
//
//			pstmt.setString(1, mpuVO.getPass());
//			pstmt.setString(2, mpuVO.getNewpass());
//			pstmt.setString(3, mpuVO.getAddr());
//			pstmt.setString(4, mpuVO.getPhoneNumfront());
//			pstmt.setString(5, mpuVO.getPhoneNumBehind());
//			pstmt.setString(6, mpuVO.getEmail());
//
//			updateFlag = pstmt.executeUpdate() == 1;
//		} finally {
//			if (pstmt != null) {
//				pstmt.close();
//			} // end if
//			if (con != null) {
//				con.close();
//			} // end if
//		} // end finally
//		return updateFlag;
//	}// updateMyPage

	public void insertMemJoin(JoinDetailVO jdVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			StringBuilder insertJoin = new StringBuilder();
			insertJoin.append(
					"		insert into member(m_id, m_code, m_name, m_pass, m_birth, m_gender, m_phone,m_email, m_detail_addr,z_seq)	")
					.append("		values(?,m_code,?,?,?,?,?,?,?,?)	");

			pstmt = con.prepareStatement(insertJoin.toString());

			pstmt.setString(1, jdVO.getId());
			pstmt.setString(2, jdVO.getName());
			pstmt.setString(3, jdVO.getPass());
			pstmt.setString(4, jdVO.getBirth());
			pstmt.setString(5, jdVO.getGender());
			pstmt.setString(6, jdVO.getPhone());
			pstmt.setString(7, jdVO.getEmail());
			pstmt.setString(8, jdVO.getAddr());
			pstmt.setInt(9, jdVO.getZ_seq());

			pstmt.executeQuery();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}

		}
	}// insertMemJoin()

	public boolean idConfrim(String id) throws SQLException {
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectID = new StringBuilder();
			selectID.append(" select m_id from member where m_id= ?	");
			pstmt = con.prepareStatement(selectID.toString());
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
			}

		} finally {
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally
		return flag;
	}// updateStatus

	////////// 비회원 로그인
	public void insertNonMem(String ip) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = getConnection();
			StringBuilder insertIp = new StringBuilder();
			insertIp.append("		insert into non_member(nm_ip) values(?)	");

			pstmt = con.prepareStatement(insertIp.toString());

			pstmt.setString(1, ip);

			pstmt.execute();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
	}// insertNonMem

	public boolean updateMemIp(LoginUpdateIpVO luVO) throws SQLException {
		boolean updateFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = getConnection();
			StringBuilder updateIp = new StringBuilder();
			updateIp.append("		update member set m_ip=? where m_id=?	");

			pstmt = con.prepareStatement(updateIp.toString());
			pstmt.setString(1, luVO.getIp());
			pstmt.setString(2, luVO.getId());

			updateFlag = pstmt.executeUpdate() == 1;

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
		return updateFlag;
	}// updateMemIp

//	public String selectMyInform(selectMemVO smvo) throws SQLException {

}// class
