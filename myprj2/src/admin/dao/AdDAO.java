package admin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.controller.AdCashCalcEvt;
import admin.run.StaticCla;
import admin.vo.SelectAllCusVO;
import admin.vo.SelectAllGoodsVO;
import admin.vo.SelectBestFiveGoodsVO;
import admin.vo.SelectCashCalcVO;
import admin.vo.SelectCusCheckVO;
import admin.vo.SelectDetailChkVO;
import admin.vo.SelectFiveCheckVO;
import admin.vo.SelectGoodsCheckVO;

public class AdDAO {
	private static AdDAO uDAO;
	private String originGoodsCode;

	private AdDAO() {
		originGoodsCode = "";
	}

	public static AdDAO getInstance() {
		if (uDAO == null) {
			uDAO = new AdDAO();
		}
		return uDAO;
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

	public List<SelectAllGoodsVO> selectAllGoods(SelectGoodsCheckVO sgck) throws SQLException {
		String searchDataUpper = "";
		int cnt = 0;
		List<SelectAllGoodsVO> list = new ArrayList<SelectAllGoodsVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			StringBuilder selectBest = new StringBuilder();
			selectBest.append("	select  g_code, g_name, g_price, g_salenum, g_score, g_inventory, g_img, b_name	")
					.append("	from goods g, detail_clothestype d	, brand b 	where (g.d_code=d.d_code) and (g.b_code=b.b_code) and g_inventory!=0	and  d.d_code !='DEL' and b.b_code !='DEL' ");
			if ((sgck.getClothesType() != null && !sgck.getClothesType().isEmpty())) {// clothesType이 안 눌렸을 땐
				selectBest.append("	and	c_type like '%'||?||'%'	");

				if (!sgck.getDetailType().isEmpty()) {
					selectBest.append("	and	d.d_type=?	");
				} // end if
			} // end if

			if (!sgck.getSearchData().isEmpty()) {// 입력 값이 있을 때
				switch (sgck.getSelectCombo()) {
				case "상품명":// 상품 명 입력
					selectBest.append("	and	g.g_name like '%'||?||'%'	");
					break;

				case "브랜드명":// 브랜드명 입력
					selectBest.append("	and	b_name like '%'||?||'%'	");
					break;
				}// end switch
			} // end if

			switch (sgck.getBrandCheck()) {
			case "BRAND":// 브랜드일 때
				selectBest.append("	and b_name !='NO'	");
				break;
			case "SOHO":// 보세일 때
				selectBest.append("	and b_name ='NO'	");
				break;
			case "ALL":// 보세일 때
				selectBest.append(" ");
				break;
			}// end switch

			selectBest.append("	order by g_score desc,g_salenum desc	");

			pstmt = con.prepareStatement(selectBest.toString());

			if ((sgck.getClothesType() != null && !sgck.getClothesType().isEmpty())) {// clothesType이 안 눌렸을 땐
				cnt++;
				if (!sgck.getClothesType().equals("BEST")) {// Best가 아닐 때
					pstmt.setString(cnt, sgck.getClothesType());
				} else {
					pstmt.setString(cnt, "");
				} // end if

				if (!sgck.getDetailType().isEmpty()) {
					cnt++;
					pstmt.setString(cnt, sgck.getDetailType());
				} // end if
			} // end if

			if (!sgck.getSearchData().isEmpty()) {
				cnt++;
				switch (sgck.getSelectCombo()) {
				case "상품명":
					searchDataUpper = sgck.getSearchData().toUpperCase();
					pstmt.setString(cnt, searchDataUpper);
					break;

				case "브랜드명":
					searchDataUpper = sgck.getSearchData().toUpperCase();
					pstmt.setString(cnt, searchDataUpper);
					break;
				}// end switch
			} // end if

			rs = pstmt.executeQuery();

			SelectAllGoodsVO sagVO = null;
			while (rs.next()) {
				sagVO = new SelectAllGoodsVO(rs.getString("g_code"), rs.getString("g_name"), rs.getString("g_img"),
						rs.getString("b_name"), rs.getInt("g_price"), rs.getInt("g_salenum"), rs.getInt("g_score"),
						rs.getInt("g_inventory"));
				list.add(sagVO);
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
		return list;
	}// selectBestGoods

	/////////////// 2019-09-23
	public List<SelectBestFiveGoodsVO> selectFiveGoods(SelectFiveCheckVO sfchkVO) throws SQLException {
		List<SelectBestFiveGoodsVO> list = new ArrayList<SelectBestFiveGoodsVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			StringBuilder selectBestFive = new StringBuilder();
			selectBestFive.append("	select  g_code, g_name, g_price, g_salenum,g_inventory, g_img, b_name	")
					.append("	from goods g, detail_clothestype d	, brand b	")
					.append("	where (g.d_code=d.d_code) and (g.b_code=b.b_code) and g_inventory!=0 and  d.d_code !='DEL' and b.b_code !='DEL'	");

			if ((sfchkVO.getClothesType() != null && !sfchkVO.getClothesType().isEmpty())) {// clothesType이 안 눌렸을 땐
				selectBestFive.append("	and	c_type like '%'||?||'%'	");
				if (!sfchkVO.getDetailType().isEmpty()) {
					selectBestFive.append("	and	d.d_type=?	");
				} // end if
			} // end if

			if (!sfchkVO.getSearchData().isEmpty()) {// 입력 값이 있을 때
				switch (sfchkVO.getSelectCombo()) {
				case "상품명":// 상품 명 입력
					selectBestFive.append("	and	g.g_name like '%'||?||'%'	");
					break;

				case "브랜드명":// 브랜드명 입력
					selectBestFive.append("	and	b_name like '%'||?||'%'	");
					break;
				}// end switch
			} // end if

			switch (sfchkVO.getBrandCheck()) {
			case "BRAND":// 브랜드일 때
				selectBestFive.append("	and b_name !='NO'	");
				break;
			case "SOHO":// 보세일 때
				selectBestFive.append("	and b_name ='NO'	");
				break;
			case "ALL":// 보세일 때
				selectBestFive.append(" ");
				break;
			}// end switch

			selectBestFive.append("	order by g.g_salenum desc	");

			pstmt = con.prepareStatement(selectBestFive.toString());
			int cnt = 0;

			if ((sfchkVO.getClothesType() != null && !sfchkVO.getClothesType().isEmpty())) {// clothesType이 안 눌렸을 땐
				cnt++;
				if (!sfchkVO.getClothesType().equals("BEST")) {// Best가 아닐 때
					pstmt.setString(cnt, sfchkVO.getClothesType());
				} else {
					pstmt.setString(cnt, "");
				} // end if

				if (!sfchkVO.getDetailType().isEmpty()) {
					cnt++;
					pstmt.setString(cnt, sfchkVO.getDetailType());
				} // end if
			} // end if

			String searchDataUpper = "";
			if (!sfchkVO.getSearchData().isEmpty()) {
				cnt++;
				switch (sfchkVO.getSelectCombo()) {
				case "상품명":
					searchDataUpper = sfchkVO.getSearchData();
					pstmt.setString(cnt, searchDataUpper);
					break;
				case "브랜드명":
					searchDataUpper = sfchkVO.getSearchData().toUpperCase();
					pstmt.setString(cnt, searchDataUpper);
					break;
				}// end switch
			} // end if

			rs = pstmt.executeQuery();

			SelectBestFiveGoodsVO sbfgVO = null;
			if (rs.next()) {
				for (int i = 0; i < sfchkVO.getGoods_num(); i++) {
					sbfgVO = new SelectBestFiveGoodsVO(rs.getString("g_code"), rs.getString("g_name"),
							rs.getString("g_img"), rs.getString("b_name"), rs.getInt("g_price"), rs.getInt("g_salenum"),
							rs.getInt("g_inventory"));
					list.add(sbfgVO);
					rs.next();
				} // end for
			} // end if

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
		return list;
	}// selectBestGoods

	public List<String> selectDeType(SelectDetailChkVO sdcVO) throws SQLException {// 소분류 조회
		List<String> detailType = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectDetailType = new StringBuilder();
			selectDetailType.append("	select d_type||'('||count(*)||')' d_type	")
					.append("	from  detail_clothestype d	, goods g, brand b	")
					.append("	where  (g.d_code=d.d_code and g.b_code= b.b_code) and g_inventory!=0 and  d.d_code !='DEL' and b.b_code !='DEL'  and  c_type=?	");
			switch (sdcVO.getBrandCheck()) {
			case "BRAND":// 브랜드일 때
				selectDetailType.append("	and b.b_name !='NO'	");
				break;
			case "SOHO":// 보세일 때
				selectDetailType.append("	and b.b_name ='NO'	");
				break;
			case "ALL":// 보세일 때
				selectDetailType.append(" ");
				break;
			}// end switch

			selectDetailType.append("	group by d_type 	");

			pstmt = con.prepareStatement(selectDetailType.toString());
			pstmt.setString(1, sdcVO.getClothesType());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				detailType.add(rs.getString("d_type"));
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
		return detailType;
	}// selectDetailType

	public List<String> selectBestDetail(String brandCheck) throws SQLException {
		List<String> bestDetailType = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectBestData = new StringBuilder();
			selectBestData.append(
					"	select d_type||'('||good_num||')' d_type	from(select rownum rw, d_type , good_num	")
					.append("	from(select d_type, good_num	")
					.append("	from(select   d_type ,sum(g_salenum) g_salenum,count(*) good_num ,(sum(g_score)/count(*)) g_score	")
					.append("	from goods g, detail_clothestype d,brand b		where (g.d_code=d.d_code  and g.b_code= b.b_code) and g_inventory!=0 and  d.d_code !='DEL' and b.b_code !='DEL'		");

			switch (brandCheck) {
			case "BRAND":// 브랜드일 때
				selectBestData.append("	and b.b_name !='NO'	");
				break;
			case "SOHO":// 보세일 때
				selectBestData.append("	and b.b_name ='NO'	");
				break;
			case "ALL":// 보세일 때
				selectBestData.append(" ");
				break;
			}// end switch
			selectBestData.append("	group by d_type)		order by g_salenum desc, g_score desc))	")
					.append("	where rw between 1 and 8	");

			pstmt = con.prepareStatement(selectBestData.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bestDetailType.add(rs.getString("d_type"));
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
		return bestDetailType;
	}// selectBestDetail

////////////////////2019-09-22 브랜드 찾기 검색
	public List<String> selectBrandKind(String inputBrand) throws SQLException {
		List<String> outputBrand = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectKind = new StringBuilder();
			selectKind.append("	select b_name	").append("	from brand	")
					.append("	where b_name like '%'||?||'%' and b_name!='NO'and b_name!='N' and b_code !='DEL'	")
					.append("	order by b_name	");

			pstmt = con.prepareStatement(selectKind.toString());
			pstmt.setString(1, inputBrand.toUpperCase());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				outputBrand.add(rs.getString("b_name"));
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
		}
		return outputBrand;
	}// selectBrandKind

//	///////////////////// 2019-09-23 최근 본 상품 보기
//	public boolean insertRecentGoods(String goodsCode) throws SQLException {
//		if (originGoodsCode == goodsCode) {
//			return false;
//		}
//		originGoodsCode = goodsCode;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			con = getConnection();
//			StringBuilder insertRecent = new StringBuilder();
//			insertRecent.append("		insert into recent_goods(r_code, r_goods,m_id)	")
//					.append("		values(r_code,?,?)	");
//
//			pstmt = con.prepareStatement(insertRecent.toString());
//			pstmt.setString(1, goodsCode);
//
//			pstmt.setString(2, StaticCla.id);
//
//			pstmt.execute();
//
//			return true;
//		} finally {
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (con != null) {
//				con.close();
//			}
//		} // end finally
//	}// insertRecentGoods

//	public boolean insertNmRecentGoods(String goodsCode) throws SQLException {
//		if (originGoodsCode == goodsCode) {
//			return false;
//		}
//		originGoodsCode = goodsCode;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			con = getConnection();
//			StringBuilder insertNmRecent = new StringBuilder();
//			insertNmRecent.append("		insert into NM_RECENT_GOODS(nmr_code, nmr_goods,nm_ip)	")
//					.append("		values(nmr_code,?,?)	");
//
//			pstmt = con.prepareStatement(insertNmRecent.toString());
//			pstmt.setString(1, goodsCode);
//			pstmt.setString(2, StaticCla.ip);
//
//			pstmt.execute();
//
//			return true;
//		} finally {
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (con != null) {
//				con.close();
//			}
//		} // end finally
//	}// insertRecentGoods

	/////////////////// 2019-09-23 최근 본 상품/////////// 스레드에서 사용 중/////////// 회원 비회원
	/////////////////// 최근 본 상품
//	public List<SelectRecentGoodsVO> selectRecent() throws SQLException {
//		List<SelectRecentGoodsVO> list = new ArrayList<SelectRecentGoodsVO>();
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs;
//
//		try {
//			con = getConnection();
//			StringBuilder selectRecentGd = new StringBuilder();
//
//			selectRecentGd.append("	select  g_code, g_name, g_img	").append("	from goods	")
//					.append("	where g_code in(select r_goods	").append("	from(select rownum rw  , r_goods	")
//					.append("	from(select  r_goods	").append("	from   recent_goods	").append("	where m_id=?	")
//					.append("	order by  r_inputdate desc))	").append("		where rw between 1 and 3)	");
//
//			pstmt = con.prepareStatement(selectRecentGd.toString());
//
//			pstmt.setString(1, StaticCla.id);
//
//			rs = pstmt.executeQuery();
//
//			SelectRecentGoodsVO srgVO = null;
//			while (rs.next()) {
//				srgVO = new SelectRecentGoodsVO(rs.getString("g_code"), rs.getString("g_name"), rs.getString("g_img"));
//				list.add(srgVO);
//			} // end while
//
//		} finally {
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (con != null) {
//				con.close();
//			}
//		} // end finally
//		return list;
//	}// selectRecent

//	public boolean deleteRecent() throws SQLException {
//		boolean deleteChk = false;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = getConnection();
//
//			String deleteRecentData = "	delete from recent_goods where m_id=?	";
//
//			pstmt = con.prepareStatement(deleteRecentData);
//			pstmt.setString(1, StaticCla.id);
//			deleteChk = pstmt.executeUpdate() == 1;
//
//		} finally {
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (con != null) {
//				con.close();
//			}
//		} // end finally
//
//		return deleteChk;
//	}// deleteRecent

//	public boolean deleteNonMem() throws SQLException {
//		boolean deleteChk = false;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = getConnection();
//
//			String deleteNonMemData = "	delete from NON_MEMBER where nm_ip=?	";
//
//			pstmt = con.prepareStatement(deleteNonMemData);
//			pstmt.setString(1, StaticCla.ip);
//			deleteChk = pstmt.executeUpdate() == 1;
//
//		} finally {
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (con != null) {
//				con.close();
//			}
//		} // end finally
//		return deleteChk;
//	}// deleteNonMem

//	public boolean deleteNmemRe() throws SQLException {
//		boolean deleteChk = false;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = getConnection();
//
//			String deleteNonMemData = "	delete from NM_RECENT_GOODS where nm_ip=?	";
//
//			pstmt = con.prepareStatement(deleteNonMemData);
//			pstmt.setString(1, StaticCla.ip);
//			deleteChk = pstmt.executeUpdate() == 1;
//
//		} finally {
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (con != null) {
//				con.close();
//			}
//		} // end finally
//		return deleteChk;
//	}// deleteNonMem

	public SelectCashCalcVO selectCalTotal(int temp) throws SQLException {
		SelectCashCalcVO cVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			StringBuilder selectCalculate = new StringBuilder();
			selectCalculate.append("	select nvl(sum(o_buypay),0) o_buypay, nvl(sum(o_quantity),0) o_quantity	")
					.append("	from ordering	").append("	where o_delivery='Y'	");

			switch (temp) {
			case AdCashCalcEvt.CALDAY:// 일
				selectCalculate.append("	and to_char(o_date,'yyyymmdd')=to_char(sysdate,'yyyymmdd')	");
				break;
			case AdCashCalcEvt.CALMON:// 월
				selectCalculate.append("	and to_char(o_date,'yyyymm')=to_char(sysdate,'yyyymm')	");
				break;
			case AdCashCalcEvt.CALYEAR:// 년
				selectCalculate.append("	and to_char(o_date,'yyyy')=to_char(sysdate,'yyyy')	");
				break;
			case AdCashCalcEvt.CALTOTAL:// 전체
				selectCalculate.append("		");
				break;
			}// switch
			pstmt = con.prepareStatement(selectCalculate.toString());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cVO = new SelectCashCalcVO(rs.getInt("o_buypay"), rs.getInt("o_quantity"));
			} // end if

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
		return cVO;
	}// selectCalTotal

	//////////////////////// 회원 정보
	public List<SelectAllCusVO> selectAllCus(SelectCusCheckVO sccVO) throws SQLException {
		List<SelectAllCusVO> list = new ArrayList<SelectAllCusVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			StringBuilder selectCustomer = new StringBuilder();
			selectCustomer.append("		select m_code, m_id,m_name, m_phone, m_totalmoney,to_char(m_joindate,'yyyy-mm-dd') m_joindate,m_stop_flag	")
					.append("		from member	");

			switch (sccVO.getComboIndex()) {
			case 0:// 전체
				break;
			case 1:// 회원번호
				selectCustomer.append("	where m_code like '%'||?||'%'		");

				break;
			case 2:// 아이디
				selectCustomer.append("	where m_id like '%'||?||'%'		");

				break;
			case 3:// 이름
				selectCustomer.append("	where m_name like '%'||?||'%'		");

				break;
			case 4:// 전화번호
				selectCustomer.append("	where m_phone like '%'||?||'%'		");

				break;
			}// end switch
			selectCustomer.append("		order by m_totalmoney desc	");

			pstmt = con.prepareStatement(selectCustomer.toString());

			if (sccVO.getComboIndex() != 0) {
				pstmt.setString(1, sccVO.getJftData());
			} // end if

			rs = pstmt.executeQuery();
			SelectAllCusVO sacVO = null;
			while (rs.next()) {
				sacVO = new SelectAllCusVO(rs.getString("m_code"), rs.getString("m_id"), rs.getString("m_name"),
						rs.getString("m_phone"), rs.getString("m_joindate"), rs.getString("m_stop_flag"),
						rs.getInt("m_totalmoney"));
				list.add(sacVO);
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
		return list;
	}// selectAllCus

	public boolean adCheckPw(String pw) throws SQLException {
		boolean checkFlag = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectPw = new StringBuilder();

			selectPw.append("	select a_id from admin	").append("	where a_pass=?	");
			pstmt = con.prepareStatement(selectPw.toString());
			pstmt.setString(1, pw);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				checkFlag = true;
			}

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
		}
		return checkFlag;
	}// adCheckPw

	public void updatePw(String pw) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			String insertNewPw = "	update admin set a_pass=? where a_id=?	 ";

			pstmt = con.prepareStatement(insertNewPw);

			pstmt.setString(1, pw);
			pstmt.setString(2, StaticCla.id);

			pstmt.executeUpdate();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally

	}// insertPw

}// class
