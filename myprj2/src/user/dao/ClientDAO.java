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
import user.vo.content.SelectCusDataVO;
import user.vo.content.SelectMyOrderDetailDTO;
import user.vo.content.SelectMyOrderVO;
import user.vo.content.UpdateCusDataVO;
import user.vo.content.UpdateGiveScoreVO;
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
	public boolean insertMemJoin(JoinDetailVO jdVO) throws SQLException {
		boolean flag=false;
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

			flag=pstmt.executeUpdate()==1;

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
			
		}
		return flag;
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
			}//end if 

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

	/**
	 * 주문내역 확인가능
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<SelectMyOrderVO> selectAllOrderList(String id) throws SQLException{
		List<SelectMyOrderVO> orderList=new ArrayList<SelectMyOrderVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		SelectMyOrderVO soVO=null;
		try {
			con=getConnection();
			StringBuilder selectOrder = new StringBuilder();
			selectOrder.append(" select o_code, o_delivery  , to_char(o_date, 'yyyy-mm-dd')o_date, g_name ,g_price, b_name	")
			.append(" from ORDERING o, goods g, brand b	")
			.append("	where (o.g_code=g.g_code) and (g.b_code=b.b_code) and o_delivery !='D' and m_id=?	")
			.append("	order by o_date desc, o_code desc	");
			
			pstmt=con.prepareStatement(selectOrder.toString());
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				soVO = new SelectMyOrderVO(rs.getString("o_delivery"), rs.getString("o_date"), rs.getString("g_name")
						,rs.getString("b_name"),rs.getString("o_code"),rs.getInt("g_price"));
				orderList.add(soVO);
			}//end while
		
		}finally {
			if(rs !=null) {rs.close();}//end if 
			if(pstmt !=null) {pstmt.close();}//end if 
			if(con !=null) {con.close();}//end if 
		}//finally
		
		return orderList;
	}//selectAllOrderList
	
	/**
	 * 주문내역 상세확인
	 * @param moDTO
	 * @throws SQLException
	 */
	public void selectDetailMyOrder(SelectMyOrderDetailDTO moDTO) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=getConnection();
			StringBuilder selectDetailMyOrder= new StringBuilder();
			selectDetailMyOrder.append(" select o.o_code, g.g_name,o_quantity, o_delivery, o_date, o_delmsg, p_method,o.o_buypay, g_img, g.g_code as g_code, o_score	")
			.append(" from ordering o, goods g ,order_pay op, pay p	")
			.append(" where (g.g_code=o.g_code) and (op.o_code=o.o_code) and (p.p_code=op.p_code) and o_delivery !='D' and o.o_code=?	");
			
			pstmt = con.prepareStatement(selectDetailMyOrder.toString());
			pstmt.setString(1, moDTO.getO_code());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				moDTO.setO_quantity(rs.getInt("o_quantity"));
				moDTO.setO_delmsg(rs.getString("o_delmsg"));
				moDTO.setP_method(rs.getString("p_method"));
				moDTO.setO_buypay(rs.getInt("o_buypay"));
				moDTO.setG_img(rs.getString("g_img"));
				moDTO.setG_code(rs.getString("g_code"));
				moDTO.setO_score(rs.getInt("o_score"));
			}//end if
		}finally {
			if(rs !=null) {rs.close();}//end if
			if(pstmt !=null) {pstmt.close();}//end if
			if(con !=null) {con.close();}//end if
		}//end finally
		
	}//selectDetailMyOrder
	
	/**
	 * 회원정보를 수정하기 위한 조회 
	 * @param cdVO
	 * @throws SQLException
	 */
	public SelectCusDataVO selectCusData(SelectCusDataVO cdVO) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=getConnection();
			StringBuilder selectCusData= new StringBuilder();
			selectCusData.append(" select m_id, m_name, m_pass, m_birth, m_phone, m_detail_addr, m_email , concat(concat(concat(z_sido,' '||nvl(z_gugun,' ')),' '||z_dong),' '||z_bunji) z_addr, z_zipcode ")
			.append("	from member m, address a	")
			.append("	where (m.z_seq=a.z_seq) and m_id=? 	");
			
			pstmt=con.prepareStatement(selectCusData.toString());
			pstmt.setString(1, cdVO.getM_id());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				cdVO.setM_birth( rs.getString("m_birth"));
				cdVO.setM_detail_addr(rs.getString("m_detail_addr"));
				cdVO.setM_email( rs.getString("m_email"));
				cdVO.setM_id(rs.getString("m_id"));
				cdVO.setM_name(rs.getString("m_name"));
				cdVO.setM_pass(rs.getString("m_pass"));
				cdVO.setM_phone(rs.getString("m_phone"));
				cdVO.setZ_addr(rs.getString("z_addr"));
				cdVO.setZ_zipcode(rs.getString("z_zipcode"));
			}//end if
			
		}finally {
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		return cdVO;
	}//selectCusData
	
	/**
	 * 사용자가 평점을 입력하여 점수변경
	 * @param ugVO
	 * @return
	 * @throws SQLException
	 */
	public boolean updateGiveScore(UpdateGiveScoreVO ugVO)throws SQLException{
		boolean updateFlag=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=getConnection();
			StringBuilder updateScore=new StringBuilder();
			updateScore.append(" update ordering  set o_score=? where o_code=? 	");
			pstmt=con.prepareStatement(updateScore.toString());
			pstmt.setInt(1, ugVO.getO_score());
			pstmt.setString(2, ugVO.getO_code());
			
			updateFlag=pstmt.executeUpdate()==1;
		}finally {
			if(pstmt!=null) {pstmt.close();}//end if
			if(con!=null) {con.close();}//end if
		}//end finally
		
		return updateFlag;
	}//updateGiveScore

	public boolean updateGoodeScore(String gCode)throws SQLException{
		boolean updateFlag=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=getConnection();
			StringBuilder updateScore=new StringBuilder();
			updateScore.append(" update goods 	")
			.append("	set g_score=(select trunc((sum(o_score)/count(*)),1) score from ordering o, goods g where (o.g_code=g.g_code) and o_score !=0 and g.g_code=?)	")
			.append("	where g_code=?	");
			pstmt=con.prepareStatement(updateScore.toString());
			pstmt.setString(1, gCode);
			pstmt.setString(2, gCode);
			
			updateFlag=pstmt.executeUpdate()==1;
		}finally {
			if(pstmt!=null) {pstmt.close();}//end if
			if(con!=null) {con.close();}//end if
		}//end finally
		
		return updateFlag;
	}//updateGoodeScore
	
	public boolean updateCusData(UpdateCusDataVO ucVO)throws SQLException{
		boolean updateFlag=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=getConnection();
			StringBuilder updateScore=new StringBuilder();
			updateScore.append(" update member	 	")
			.append("	set m_phone=?,m_detail_addr=?,m_email=? , z_seq=( select z_seq	")
			.append("	from (select concat(concat(concat(z_sido,' '||nvl(z_gugun,' ')),' '||z_dong),' '||z_bunji) z_addr,	")
			.append("	z_seq, z_zipcode from address)	")
			.append("	where z_zipcode like '%'||?||'%'  and z_addr like '%'||?||'%')	")
			.append("	where m_id =?   	");
			pstmt=con.prepareStatement(updateScore.toString());
			pstmt.setString(1, ucVO.getM_phone());
			pstmt.setString(2, ucVO.getM_detail_addr());
			pstmt.setString(3, ucVO.getM_email());
			pstmt.setString(4, ucVO.getZ_zipcode());
			pstmt.setString(5, ucVO.getZ_addr());
			pstmt.setString(6, ucVO.getM_id());
			
			updateFlag=pstmt.executeUpdate()==1;
		}finally {
			if(pstmt!=null) {pstmt.close();}//end if
			if(con!=null) {con.close();}//end if
		}//end finally
		
		return updateFlag;
	}//updateGoodeScore
	
	public boolean deleteCus(String id)throws SQLException{
		boolean deleteFlag=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=getConnection();
			StringBuilder deleteCus= new StringBuilder();
			deleteCus.append("	update member set  m_stop_flag='N' where m_id=?		");
			pstmt=con.prepareStatement(deleteCus.toString());
			pstmt.setString(1,id);
			
			deleteFlag=pstmt.executeUpdate()==1;
			
		}finally {
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}//end finally
		
		return deleteFlag;
	}//deleteCus

	
}// class
