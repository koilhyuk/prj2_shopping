package user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import user.view.content.UserGoodsMainView;
import user.vo.content.BuyGoodsInformVO;
import user.vo.content.InsertNewCardVO;
import user.vo.content.MemberCardInformVO;
import user.vo.content.SelectAllGoodsVO;
import user.vo.content.SelectBestFiveGoodsVO;
import user.vo.content.SelectClickGoodsDetailDTO;
import user.vo.content.SelectDetailChkVO;
import user.vo.content.SelectFiveCheckVO;
import user.vo.content.SelectGoodsCheckVO;
import user.vo.content.SelectOrderChkCard;
import user.vo.content.SelectRecentGoodsVO;
import user.vo.content.SellNextInformDTO;
import user.vo.content.UnCompleteOrderInformVO;

public class UserDAO {
	private static UserDAO uDAO;
	private String originGoodsCode;

	private UserDAO() {
		originGoodsCode = "";
	}

	public static UserDAO getInstance() {
		if (uDAO == null) {
			uDAO = new UserDAO();
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
					.append("	from goods g, detail_clothestype d	, brand b 	where (g.d_code=d.d_code) and (g.b_code=b.b_code)	and  d.d_code !='DEL' and b.b_code !='DEL' and g_inventory!=0  ");

			if ((sgck.getClothesType() != null && !sgck.getClothesType().isEmpty())) {// clothesType이 눌렸을 땐
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

			if (!sgck.getClothesType().equals("BEST")) {
				selectBest.append("	order by g_score desc, g_salenum desc	");
			} else {
				selectBest.append("	order by g_salenum desc, g_score desc	");
			}

			pstmt = con.prepareStatement(selectBest.toString());

			if ((sgck.getClothesType() != null && !sgck.getClothesType().isEmpty())) {// clothesType이 눌렸을 땐
				cnt++;
				if (!sgck.getClothesType().equals("BEST")) {// Best가 아닐 때
					pstmt.setString(cnt, sgck.getClothesType());
				} else {// BEST일 때
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
	}// selectAllGoods

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
					.append("	where (g.d_code=d.d_code) and (g.b_code=b.b_code) and  d.d_code !='DEL' and b.b_code !='DEL' and g_inventory!=0	");

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
					.append("	where  (g.d_code=d.d_code and g.b_code= b.b_code) and  d.d_code !='DEL' and b.b_code !='DEL' and g_inventory!=0  and  c_type=?	");

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
					.append("	where b_name like '%'||?||'%' and b_name!='NO' and b_code !='DEL'  	")
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

	///////////////////// 2019-09-23 최근 본 상품 보기
	public boolean insertRecentGoods(String goodsCode) throws SQLException {
		if (originGoodsCode == goodsCode) {
			return false;
		}
		originGoodsCode = goodsCode;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			StringBuilder insertRecent = new StringBuilder();
			insertRecent.append("		insert into recent_goods(r_code, r_goods,m_id)	")
					.append("		values(r_code,?,?)	");

			pstmt = con.prepareStatement(insertRecent.toString());
			pstmt.setString(1, goodsCode);

			pstmt.setString(2, UserGoodsMainView.id);

			pstmt.execute();

			return true;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
	}// insertRecentGoods

	public boolean insertNmRecentGoods(String goodsCode) throws SQLException {
		if (originGoodsCode == goodsCode) {
			return false;
		}
		originGoodsCode = goodsCode;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			StringBuilder insertNmRecent = new StringBuilder();
			insertNmRecent.append("		insert into NM_RECENT_GOODS(nmr_code, nmr_goods,nm_ip)	")
					.append("		values(nmr_code,?,?)	");

			pstmt = con.prepareStatement(insertNmRecent.toString());
			pstmt.setString(1, goodsCode);
			pstmt.setString(2, UserGoodsMainView.ip);

			pstmt.execute();

			return true;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
	}// insertRecentGoods

	/////////////////// 2019-09-23 최근 본 상품/////////// 스레드에서 사용 중/////////// 회원 비회원
	/////////////////// 최근 본 상품
	public List<SelectRecentGoodsVO> selectRecent() throws SQLException {
		List<SelectRecentGoodsVO> list = new ArrayList<SelectRecentGoodsVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectRecentGd = new StringBuilder();

			if (UserGoodsMainView.id != null && !UserGoodsMainView.id.isEmpty()) {// 회원

				selectRecentGd.append("	select  g_code, g_name, g_img	").append("	from goods	")
						.append("	where g_code in(select r_goods	").append("	from(select rownum rw  , r_goods	")
						.append("	from(select  r_goods	").append("	from   recent_goods	")
						.append("	where m_id=?	").append("	order by  r_inputdate desc))	")
						.append("		where rw between 1 and 3)	");
			} else {// 비회원
				selectRecentGd.append("	select  g_code, g_name, g_img	").append("	from goods	")
						.append("	where g_code in(select nmr_goods	")
						.append("	from(select rownum rw  , nmr_goods	").append("	from(select  nmr_goods	")
						.append("	from   NM_RECENT_GOODS	").append("	where nm_ip=?	")
						.append("	order by  nmr_inputdate desc))	").append("		where rw between 1 and 3)	");
			}

			pstmt = con.prepareStatement(selectRecentGd.toString());

			if (UserGoodsMainView.id != null && !UserGoodsMainView.id.isEmpty()) {// 회원
				pstmt.setString(1, UserGoodsMainView.id);
			} else {
				pstmt.setString(1, UserGoodsMainView.ip);
			}

			rs = pstmt.executeQuery();

			SelectRecentGoodsVO srgVO = null;
			while (rs.next()) {
				srgVO = new SelectRecentGoodsVO(rs.getString("g_code"), rs.getString("g_name"), rs.getString("g_img"));
				list.add(srgVO);
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
	}// selectRecent

	public boolean deleteRecent() throws SQLException {
		boolean deleteChk = false;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = getConnection();

			String deleteRecentData = "	delete from recent_goods where m_id=?	";

			pstmt = con.prepareStatement(deleteRecentData);
			pstmt.setString(1, UserGoodsMainView.id);
			deleteChk = pstmt.executeUpdate() == 1;

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally

		return deleteChk;
	}// deleteRecent

	public boolean deleteNonMem() throws SQLException {
		boolean deleteChk = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = getConnection();

			String deleteNonMemData = "	delete from NON_MEMBER where nm_ip=?	";

			pstmt = con.prepareStatement(deleteNonMemData);
			pstmt.setString(1, UserGoodsMainView.ip);
			deleteChk = pstmt.executeUpdate() == 1;

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
		return deleteChk;
	}// deleteNonMem

	public boolean deleteNmemRe() throws SQLException {
		boolean deleteChk = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			String deleteNonMemData = "	delete from NM_RECENT_GOODS where nm_ip=?	";
			pstmt = con.prepareStatement(deleteNonMemData);
			pstmt.setString(1, UserGoodsMainView.ip);
			deleteChk = pstmt.executeUpdate() == 1;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
		return deleteChk;
	}// deleteNonMem

	public void insertMemNewCard(InsertNewCardVO inc) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();

			StringBuilder insertCard = new StringBuilder();
			insertCard.append("	insert into pay(p_code,p_method,p_cardnum,p_cvc,m_id)	")
					.append("	values(p_code, ?, ?, ?, ?)	");

			pstmt = con.prepareStatement(insertCard.toString());
			pstmt.setString(1, inc.getCardCom());
			pstmt.setString(2, inc.getTransCardNum());
			pstmt.setString(3, inc.getTransCvc());
			pstmt.setString(4, UserGoodsMainView.id);

			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
	}// insertMemNewCard

	/**
	 * 2019-12-30 선택한 상품 상세보기
	 * 
	 * @return
	 */
	public SelectClickGoodsDetailDTO searchClickGoodsDetail(String goodsCode) throws SQLException {
		SelectClickGoodsDetailDTO scgdDTO = new SelectClickGoodsDetailDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			scgdDTO.setgCode(goodsCode);

			con = getConnection();

			StringBuilder selectClickGoods = new StringBuilder();
			selectClickGoods.append(
					"	select  g_price, g_inventory, g_code, g_name, g_score, g_strong, g_img , b_name, d_type, c_type,   g_salenum 	")
					.append("	from GOODS g, Brand b , DETAIL_CLOTHESTYPE dc	")
					.append("	where (g.b_code=b.b_code and g.d_code = dc.d_code) and g_code=?	");

			pstmt = con.prepareStatement(selectClickGoods.toString());
			pstmt.setString(1, goodsCode);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				scgdDTO.setbName(rs.getString("b_name"));
				scgdDTO.setcName(rs.getString("c_type"));
				scgdDTO.setdName(rs.getString("d_type"));
				scgdDTO.setgImg(rs.getString("g_img"));
				scgdDTO.setgInventory(rs.getInt("g_inventory"));
				scgdDTO.setgName(rs.getString("g_name"));
				scgdDTO.setgPrice(rs.getInt("g_price"));
				scgdDTO.setgScore(rs.getInt("g_score"));
				scgdDTO.setgStrong(rs.getString("g_strong"));
				scgdDTO.setgSaleNum(rs.getInt("g_salenum"));
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
		return scgdDTO;
	}// searchClickGoodsDetail

	public SellNextInformDTO searchBuyGoodsInform(SellNextInformDTO snifDTO) throws SQLException {
		SellNextInformDTO successDTO = snifDTO;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = getConnection();

			StringBuilder selectBuyInform = new StringBuilder();

			selectBuyInform.append("	select m_name, m_phone, m_detail_addr, z_seq	").append("	from member	")
					.append("	where m_id=?	");

			pstmt = con.prepareStatement(selectBuyInform.toString());
			pstmt.setString(1, snifDTO.getmId());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				successDTO.setmName(rs.getString("m_name"));
				successDTO.setmPhone(rs.getString("m_phone"));
				successDTO.setmDetailAddr(rs.getString("m_detail_addr"));
				successDTO.setmSeq(rs.getInt("z_seq"));
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
		}
		return successDTO;
	}// searchBuyGoodsInform

	public List<MemberCardInformVO> selectMemberCard(SellNextInformDTO sniDTO) throws SQLException {
		List<MemberCardInformVO> memberCard = new ArrayList<MemberCardInformVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectMemCardInform = new StringBuilder();
			selectMemCardInform.append("	select   p_method, p_cardnum	").append("	 from PAY	")
					.append("	 where  M_ID=?	");

			pstmt = con.prepareStatement(selectMemCardInform.toString());
			pstmt.setString(1, UserGoodsMainView.id);

			rs = pstmt.executeQuery();

			MemberCardInformVO mciVO = null;
			while (rs.next()) {
				mciVO = new MemberCardInformVO(rs.getString("p_method"), rs.getString("p_cardnum"));
				memberCard.add(mciVO);
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
		return memberCard;
	}// selectMemberCard

	public String selectCardNum(String cardCom, String mId) throws SQLException {
		String cardNum = "";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectMemCardInform = new StringBuilder();
			selectMemCardInform.append("	select  p_cardnum	").append("	 from PAY	")
					.append("	 where  M_ID=? and P_METHOD=?	");

			pstmt = con.prepareStatement(selectMemCardInform.toString());
			pstmt.setString(1, mId);
			pstmt.setString(2, cardCom);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				cardNum = rs.getString("p_cardnum");
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
		return cardNum;
	}// selectCardNum

	public String selectChkCard(SelectOrderChkCard socc) throws SQLException {
		String cardCode = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectMemCardChk = new StringBuilder();
			selectMemCardChk.append("	select p_code 	").append("	 from PAY	")
					.append("	 where p_method=? and p_cardnum=? and p_cvc=? and m_id=?	");

			pstmt = con.prepareStatement(selectMemCardChk.toString());
			pstmt.setString(1, socc.getCardMethod());
			pstmt.setString(2, socc.getTransCardNum());
			pstmt.setString(3, socc.getTransCardCVC());
			pstmt.setString(4, UserGoodsMainView.id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				cardCode = rs.getString("p_code");
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

		return cardCode;
	}// selectChkCard

	public void insertNewOrdering(BuyGoodsInformVO bgiVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			StringBuilder insertNewOrdering = new StringBuilder();
			insertNewOrdering.append(
					"		insert into ORDERING(O_CODE, G_CODE, O_PHONE, O_QUANTITY,O_ADDR, O_BUYPAY, O_DELMSG, O_PERSON, M_ID, Z_SEQ)	")
					.append("		values(o_code, ?,?,?,?,?,?,?,?,?)	");

			pstmt = con.prepareStatement(insertNewOrdering.toString());

			pstmt.setString(1, bgiVO.getgCode());
			pstmt.setString(2, bgiVO.getoPhone());
			pstmt.setInt(3, bgiVO.getoQuantity());
			pstmt.setString(4, bgiVO.getoAddr());
			pstmt.setInt(5, bgiVO.getoBuypay());
			pstmt.setString(6, bgiVO.getoDelmsg());
			pstmt.setString(7, bgiVO.getoPerson());
			pstmt.setString(8, bgiVO.getmId());
			pstmt.setInt(9, bgiVO.getzSeq());
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
	}// insertNewOrdering

	public String selectRecentOrdering(String mId) throws SQLException {
		String orderingCode = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectRecentOCode = new StringBuilder();
			selectRecentOCode.append("	select o_code 	").append("	from(select o_code from ordering 	")
					.append("	where m_id=? 	").append("	order by O_DATE     desc) 	").append("	 where rownum=1	");

			pstmt = con.prepareStatement(selectRecentOCode.toString());
			pstmt.setString(1, mId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				orderingCode = rs.getString("o_code");
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
		return orderingCode;
	}// selectRecentOrdering

	public int selectGoodsInventory(String goodsGode) throws SQLException {
		int goodsInventory = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectBuyGoodsInven = new StringBuilder();
			selectBuyGoodsInven.append("	select g_inventory from GOODS 	").append("	where   g_code=? 	");

			pstmt = con.prepareStatement(selectBuyGoodsInven.toString());
			pstmt.setString(1, goodsGode.trim());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				goodsInventory = rs.getInt("g_inventory");
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

		return goodsInventory;
	}// selectGoodsInventory

	public boolean updateInventoryGoods(String gCode, int goodsQuantity) throws SQLException {
		boolean updateChk = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			StringBuilder updateGInventory = new StringBuilder();
			updateGInventory.append("	update  GOODS  	").append(
					"	set G_INVENTORY= (select G_INVENTORY from goods where g_code=?)-?,  G_SALENUM =   (select G_SALENUM from goods where g_code=?)+?	")
					.append("	where g_code=?	");

			pstmt = con.prepareStatement(updateGInventory.toString());
			pstmt.setString(1, gCode.trim());
			pstmt.setInt(2, goodsQuantity);
			pstmt.setString(3, gCode.trim());
			pstmt.setInt(4, goodsQuantity);
			pstmt.setString(5, gCode.trim());

			updateChk = pstmt.executeUpdate() == 1;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
		return updateChk;
	}// updateInventoryGoods

	public void insertOrderPay(String oCode, String pCode) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			StringBuilder insertNewOP = new StringBuilder();
			insertNewOP.append("		insert into ORDER_PAY(O_CODE, P_CODE)	").append("		values(?,?)	");

			pstmt = con.prepareStatement(insertNewOP.toString());
			pstmt.setString(1, oCode);
			pstmt.setString(2, pCode);

			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
	}// insertOrderPay

	public UnCompleteOrderInformVO selectEmptyInform(String oCode) throws SQLException {

		UnCompleteOrderInformVO ucoiVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectEmpInform = new StringBuilder();
			selectEmpInform.append("	select g.g_img, m.m_name, to_char(o.o_date,'yyyy-mm-dd am hh:mi:ss') o_date	")
					.append("	from GOODS  g, MEMBER m ,ORDERING o 	")
					.append("	where (g.g_code=o.g_code and m.m_id=o.m_id)   and    o.o_code=?	");

			pstmt = con.prepareStatement(selectEmpInform.toString());
			pstmt.setString(1, oCode.trim());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				ucoiVO = new UnCompleteOrderInformVO(rs.getString("g_img"), rs.getString("m_name"),
						rs.getString("o_date"));
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

		return ucoiVO;
	}// selectEmptyInform

	public String selectGoodsLike(String goodsCode, String memberId) throws SQLException {
		String likeResult = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectGoodsLike = new StringBuilder();
			selectGoodsLike.append("	select g_code from GOODSLIKE	").append("	where m_id=? and g_code=?	");

			pstmt = con.prepareStatement(selectGoodsLike.toString());
			pstmt.setString(1, memberId);
			pstmt.setString(2, goodsCode);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				likeResult = rs.getString("g_code");
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

		return likeResult;
	}// selectGoodsLike

	public int selectGoodsLikeNum(String goodsCode) throws SQLException {
		int likeNum = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectGoodsLike = new StringBuilder();
			selectGoodsLike.append("	select count(*) cnt from GOODSLIKE	").append(" where	g_code=? 	");

			pstmt = con.prepareStatement(selectGoodsLike.toString());
			pstmt.setString(1, goodsCode);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				likeNum = rs.getInt("cnt");
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

		return likeNum;
	}// selectGoodsLike

	public boolean deleteGoodsLike(String memeId, String gCode) throws SQLException {
		boolean deleteFlag = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = getConnection();

			String deleteLikeGoods = "	delete from goodslike where m_id=? and g_code=?	";

			pstmt = con.prepareStatement(deleteLikeGoods);
			pstmt.setString(1, memeId);
			pstmt.setString(2, gCode.trim());
			deleteFlag = pstmt.executeUpdate() == 1;

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally

		return deleteFlag;
	}// selectGoodsLike

	public void insertGoodsLike(String memeId, String gCode) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			StringBuilder insertLikeGoods = new StringBuilder();
			insertLikeGoods.append("	insert into goodslike(M_ID, G_CODE)	").append("	values(?,?)	");

			pstmt = con.prepareStatement(insertLikeGoods.toString());
			pstmt.setString(1, memeId);
			pstmt.setString(2, gCode.trim());

			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
	}// insertOrderPay

	public List<SelectAllGoodsVO> selectLikeAllGoods(String memId) throws SQLException {
		List<SelectAllGoodsVO> list = new ArrayList<SelectAllGoodsVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			StringBuilder selectGoodsLikeMem = new StringBuilder();
			selectGoodsLikeMem
					.append("	select  gl.g_code, g_name, g_price, g_salenum, g_score, g_inventory, g_img, b_name	")
					.append("	from goods g, detail_clothestype d	, brand b, goodslike gl	")
					.append("	where (g.d_code=d.d_code and g.b_code=b.b_code and gl.g_code=g.g_code)	and  d.d_code !='DEL' and b.b_code !='DEL' and g_inventory !=0 and gl.m_id=?		")
					.append("	order by like_date desc	");

			pstmt = con.prepareStatement(selectGoodsLikeMem.toString());
			pstmt.setString(1, memId);

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
	}// selectAllGoods

	public boolean updateUseMoney(String memId, int useMoney) throws SQLException {
		boolean updateChk = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			StringBuilder updateMemUse = new StringBuilder();
			updateMemUse.append("	update member   	")
					.append("	 set M_TOTALMONEY=(select  M_TOTALMONEY from member where m_id=?)+? 	")
					.append("	where m_id=?  	");

			pstmt = con.prepareStatement(updateMemUse.toString());
			pstmt.setString(1, memId);
			pstmt.setInt(2, useMoney);
			pstmt.setString(3, memId);

			updateChk = pstmt.executeUpdate() == 1;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
		return updateChk;
	}// updateUseMoney
	
}// class
