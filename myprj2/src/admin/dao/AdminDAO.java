package admin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.vo.InsertBrandVO;
import admin.vo.InsertGoodsAddVO;
import admin.vo.InsertGoodsTypeVO;
import admin.vo.SelectBrandListVO;
import admin.vo.SelectClickGoodsDetailDTO;
import admin.vo.SelectCusDetailDTO;
import admin.vo.SelectGoodsDetailDTO;
import admin.vo.SelectGoodsListVO;
import admin.vo.SelectListVO;
import admin.vo.SelectOrderDetailDTO;
import admin.vo.SelectOrderListVO;
import admin.vo.UpdateBrandVO;
import admin.vo.UpdateCustomerVO;
import admin.vo.UpdateDeliveryChkVO;
import admin.vo.UpdateGoodsVO;
import admin.vo.UpdateTypeVO;
import admin.vo.updateStopVO;

/**
 * ������ DBMS�� �����۾�
 * 
 * @author hyebin
 */
public class AdminDAO {
	// Ŭ���� �ܺο��� ��üȭ�� �� �� ������ private�� ����
	private static AdminDAO aDAO;

	private AdminDAO() {

	}// ServerDAO

	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static AdminDAO getInstance() {
		// ��ü�� �ϳ��� ������ �� �ֵ��� ����
		if (aDAO == null) { // aDAO�� ������
			aDAO = new AdminDAO(); // ��ü����
		} // end if
		return aDAO;
	}// getInstance

	/**
	 * ����̹� �ε�, Ŀ�ؼ� jdbc
	 * 
	 * @return
	 * @throws SQLException
	 */
	private Connection getConn() throws SQLException {
		Connection con = null;
		try {
			// 1. ����̹��ε�
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // end catch
			// 2. Ŀ�ؼ� ���
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "shopper";
		String pass = "shmall";
		con = DriverManager.getConnection(url, id, pass);
		return con;
	}// getConn

	/**
	 * ��ǰ���� ��ü����Ʈ ��ȸ 2019-09-12 !!!!!! VO-> ��ǰ�ڵ�, �귣��, ��ǰ��, ����
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<SelectGoodsListVO> selectAllGoods(SelectListVO slVO) throws SQLException {
		int inputScore = 0;
		List<SelectGoodsListVO> list = new ArrayList<SelectGoodsListVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String searchData = "";
		try {
			con = getConn();
			StringBuilder selectAllGoods = new StringBuilder();

			selectAllGoods.append(
					" select g_code, g_name, g_price, g_salenum, g_score, g_inventory, to_char(g_inputdate,'yyyy-mm-dd') g_inputdate, d.d_type d_type, b_img , b.b_name b_name		")
					.append(" from goods g, brand b ,detail_clothestype d	")
					.append(" where (g.b_code=b.b_code) and (g.d_code= d.d_code) and b.b_code !='DEL' and d.d_code!='DEL' 	");
			switch (slVO.getIndex()) { // 0�� ��ü��ȸ
			case 1:// ��ǰ�ڵ�
				searchData = slVO.getJtfData().toLowerCase();
				selectAllGoods.append(" 	and g_code like '%' ||?||'%' 	");
				break;
			case 2:// �귣��
				searchData = slVO.getJtfData().toUpperCase();
				selectAllGoods.append(" 	and b.b_name like '%' ||? ||'%' 	");
				break;
			case 3: // ��ǰ��
				searchData = slVO.getJtfData().toUpperCase();
				selectAllGoods.append(" 	and g_name like '%'||?||'%' 	");
				break;

			case 4:// ����
				searchData = slVO.getJtfData(); // �Է��� �� �ޱ�
				if (!searchData.isEmpty()) {// ��ĭ�� �ƴ� �� bind ������ ����Ͽ� �Է� ���� �ְ� ��ȸ�� �ؾ��մϴ�.
					selectAllGoods.append("	and g_score= ? 	");
				} // end if
			}// end switch

			pstmt = con.prepareStatement(selectAllGoods.toString());

			if (slVO.getIndex() != 0) {// ������ �ε����� 0�� �ƴ� ��
				if (slVO.getIndex() == 4) { // �׸��� �ε����� 0���� �ƴϰ� 4���� ��(����)
					if (!searchData.isEmpty()) { // �ε����� 0���� �ƴϰ� 4���� ��(����) �Է� ���� ������ ��
						inputScore = Integer.parseInt(searchData);// ���� ������ �Է� ���� ĳ�����Ͽ�
						pstmt.setInt(1, inputScore);// ?�� ���ε� ������ ���� �ֽ��ϴ�.
					} // end if
				} else {// ����� �ε����� 0�� �ƴϰ� 4�� �ƴ� �ʵ��Դϴ�.(��� ������ ���� ������ �̰��� ó������ �ʾұ� ����!!)
						// �ε����� ���ε� ������ ���µ� ��� ���� �ְ� �־ error�߻�!!
					pstmt.setString(1, searchData);
				} // end if
			} // end if

			rs = pstmt.executeQuery();// ������ ���ఴü ����

			SelectGoodsListVO pVO = null;
			while (rs.next()) {
				pVO = new SelectGoodsListVO(rs.getString("g_code"), rs.getString("g_name"), rs.getString("b_img"),
						rs.getString("b_name"), rs.getString("g_inputdate"), rs.getString("d_type"),
						rs.getInt("g_price"), rs.getInt("g_salenum"), rs.getInt("g_score"), rs.getInt("g_inventory"));
				list.add(pVO);
			} // end while

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
	}// selectAllOrder

	/**
	 * ��ǰ����
	 * 
	 * @return
	 * @throws SQLException
	 */
	public boolean updateDetailGoods(UpdateGoodsVO ugVO) throws SQLException {
		boolean updateFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConn();
			StringBuilder updateDatailGoods = new StringBuilder();
			updateDatailGoods.append("  update goods	")
					.append("  set  g_price =? , g_strong=?, g_img =? ,g_inventory= ?	")
					.append("  where g_code =? 	");

			pstmt = con.prepareStatement(updateDatailGoods.toString());
			pstmt.setInt(1, ugVO.getG_price());
			pstmt.setString(2, ugVO.getG_strong());
			pstmt.setString(3, ugVO.getG_img());
			pstmt.setInt(4, ugVO.getG_inventory());
			pstmt.setString(5, ugVO.getG_code());
			updateFlag = pstmt.executeUpdate() == 1;

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
		return updateFlag;
	}// updateDetailGoods

	/**
	 * DTO �̿��Ͽ� ������â�� �̹���, Ư����, Ÿ��, �귣���̸��� ���� �� ������ ����
	 * 
	 * @param dgDTO
	 * @throws SQLException
	 */
	public void selectDetailGoods(SelectGoodsDetailDTO dgDTO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConn(); // ����̹��ε�
			StringBuilder selectDetailGoods = new StringBuilder();
			selectDetailGoods.append(" 	select g_img, g_strong,  c_type, b.b_name b_name	")
					.append(" 	from  goods g, detail_clothestype cd, brand b	")
					.append(" 	where (cd.d_code=g.d_code ) and (g.b_code=b.b_code) and g_code =? 	"); // �귣�� �̹����� �������
																										// join�� �ۼ�

			pstmt = con.prepareStatement(selectDetailGoods.toString());
			pstmt.setString(1, dgDTO.getG_code()); // dgDTO�� �ڵ尪�� �־�
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dgDTO.setG_img(rs.getString("g_img"));
				dgDTO.setG_strong(rs.getString("g_strong"));
				dgDTO.setC_type(rs.getString("c_type"));
				dgDTO.setB_name(rs.getString("b_name"));

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

	}// selectDetailGoods

	/**
	 * ��ǰ��� method
	 * 
	 * @param dpVO
	 * @throws SQLException
	 */
	public void insertGoods(InsertGoodsAddVO upVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConn();
			StringBuilder insertGoods = new StringBuilder();
			insertGoods.append(
					"insert into goods(g_code,g_img, b_code,d_code,g_name,g_price, g_inventory, g_strong, g_inputdate) ")
					.append("		values(g_code,?,(select b_code from brand where b_name=?),(select d_code from detail_clothestype where d_type=?),?,?,?,?, sysdate) ");

			pstmt = con.prepareStatement(insertGoods.toString());
			pstmt.setString(1, upVO.getG_img());
			pstmt.setString(2, upVO.getB_name());
			pstmt.setString(3, upVO.getD_type());
			pstmt.setString(4, upVO.getG_name());
			pstmt.setInt(5, upVO.getG_price());
			pstmt.setInt(6, upVO.getG_inventory());
			pstmt.setString(7, upVO.getG_strong());

			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // finally

	}// insertGoods

	/**
	 * ������ ��ǰ ���� ������� true : ��������, false :��������
	 * 
	 * @param code
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteGoods(String code) throws SQLException {
		boolean flag = false;// ** ��������, ��������?
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConn();
			StringBuilder deleteGoods = new StringBuilder();

			deleteGoods.append(" 	delete from goods 	").append(" where g_code =?");
			pstmt = con.prepareStatement(deleteGoods.toString());

			pstmt.setString(1, code);
			flag = pstmt.executeUpdate() == 1;

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally

		return flag;
	}// deleteGoods

	/**
	 * �귣�� ��ȸ
	 * 
	 * @throws SQLException
	 */
	public List<String> selectBrand() throws SQLException {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConn();
			StringBuilder selectBrand = new StringBuilder();
			selectBrand.append(" select b_name, b_code 	").append(" from brand 	").append(" where  b_code!='DEL'	")
					.append(" order by b_name 	");

			pstmt = con.prepareStatement(selectBrand.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("b_name"));
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
	}// selectBrand

	/**
	 * ��з� ��ȸ
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<String> selectType() throws SQLException {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConn();
			StringBuilder selectType = new StringBuilder();
			selectType.append(" select c_type 	").append(" from clothestype 	").append(" where  c_code!='DEL'	")
					.append(" order by c_type 	");
			pstmt = con.prepareStatement(selectType.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("c_type"));
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
	}// selectType

	/**
	 * �Һз� ��ȸ
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<String> selectDetailType(String type) throws SQLException {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConn();
			StringBuilder selectDetailType = new StringBuilder();
			selectDetailType.append(" select d_type, d_code 	").append(" from detail_clothestype 	")
					.append(" where c_type=? and d_code!='DEL'	");
			pstmt = con.prepareStatement(selectDetailType.toString());
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("d_type"));
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
	}// selectDetailType

	/**
	 * ī�װ� �߰�
	 * 
	 * @param idVO
	 * @throws SQLException
	 */
	public void insertDetailType(InsertGoodsTypeVO idVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConn();
			StringBuilder insertDetailType = new StringBuilder();
			insertDetailType.append(" insert into detail_clothestype(c_type, d_code, d_type) values(?,d_code,?)  ");
			pstmt = con.prepareStatement(insertDetailType.toString());
			pstmt.setString(1, idVO.getC_type());
			pstmt.setString(2, idVO.getD_type());

			pstmt.executeUpdate();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally

	}// insertDetailType

	public void insertBrand(InsertBrandVO ibVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConn();
			StringBuilder insertBrand = new StringBuilder();
			insertBrand.append(" insert into brand(b_name, b_code, b_img) values(?,b_code,?) 	");
			pstmt = con.prepareStatement(insertBrand.toString());
			pstmt.setString(1, ibVO.getB_name());
			pstmt.setString(2, ibVO.getB_img());

			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // finally

	}// insertBrand

//////////////////////////////////////////////////////////////////�ֹ�����//////////////////////////////////////////////////////////
	/**
	 * ��ü �ֹ���ȸ
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<SelectOrderListVO> selectAllOrder(SelectListVO slVO) throws SQLException {
		List<SelectOrderListVO> list = new ArrayList<SelectOrderListVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SelectOrderListVO oVO = null;
		String searchData = "";

		try {
			con = getConn();
			StringBuilder selectAllOrder = new StringBuilder();
			selectAllOrder.append(
					" 	select   o_code,	o_phone,o_delivery,	to_char(o_date,'yyyy-mm-dd') o_date,o_person,m_id,o_quantity, o_buypay, g_name	")

					.append(" 	from ordering o, goods g	").append(" 	where (o.g_code=g.g_code)	 and o_delivery !='D'	");
//					.append(" and to_char(sysdate,'yyyymmdd') =to_char(o_date,'yyyymmdd')		");
			switch (slVO.getIndex()) {
			case 1:// �ֹ���ȣ
				searchData = slVO.getJtfData().toLowerCase();
				selectAllOrder.append("  and o_code like '%'||?||'%' 	");
				break;
			case 2:// ȸ�����̵�
				searchData = slVO.getJtfData().toLowerCase();
				selectAllOrder.append("  and m_id like '%'||?||'%'	");
				break;
			case 3:// �޴���
				searchData = slVO.getJtfData();
				selectAllOrder.append(" and o_person like '%'||?||'%' 	");
				break;
			case 4:// ��ǰ��
				searchData = slVO.getJtfData().toUpperCase();
				selectAllOrder.append(" and g_name like '%'||?||'%' 	");
				break;
			case 5:// �ֹ�����
				searchData = slVO.getJtfData();
				selectAllOrder.append(" and o_date like '%'||?||'%' 	");
				break;

			}// end switch

			pstmt = con.prepareStatement(selectAllOrder.toString());
			if (slVO.getIndex() != 0) {// �ε����� 0�� �ƴҶ�
				pstmt.setString(1, searchData);

			} // end if

			rs = pstmt.executeQuery();
			while (rs.next()) {
				oVO = new SelectOrderListVO(rs.getString("o_code"), rs.getString("m_id"), rs.getString("o_person"),
						rs.getString("o_phone"), rs.getString("g_name"), rs.getInt("o_quantity"), rs.getInt("o_buypay"),
						rs.getString("o_delivery"), rs.getString("o_date"));
				list.add(oVO);
			} // end while

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
	}// selectAllOrder

	/**
	 * �ֹ����� - ��ۻ��� ����
	 * 
	 * @param orderCode
	 * @param deli
	 * @return
	 * @throws SQLException
	 */
	public boolean updateDelivery(UpdateDeliveryChkVO udVO) throws SQLException {
		boolean modifyFlag = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConn();
			StringBuilder updateDelivery = new StringBuilder();
			updateDelivery.append(" update ordering ").append(" set o_delivery=? ").append(" where o_code=? ");
			pstmt = con.prepareStatement(updateDelivery.toString());
			pstmt.setString(1, udVO.getO_delivery());
			pstmt.setString(2, udVO.getO_code());
			modifyFlag = pstmt.executeUpdate() == 1;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally

		return modifyFlag;
	}// updateDelivery

	public boolean deleteOrder(String code) throws SQLException {
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConn();
			StringBuilder deleteOrder = new StringBuilder();
			deleteOrder.append(" update ordering set o_delivery='D' where o_code=? 	and o_delivery='Y' ");

			pstmt = con.prepareStatement(deleteOrder.toString());

			pstmt.setString(1, code);
			flag = pstmt.executeUpdate() == 1;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			}

		} // end finally
		return flag;
	}// deleteOrder

	/**
	 * �޴���, �޴����ּ�, �������, ��ۿ�û����, �̹����� ��ȸ�Ͽ� ����
	 * 
	 * @param odDTO
	 * @throws SQLException
	 */
	public void selectDetailOrder(SelectOrderDetailDTO odDTO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConn();
			StringBuilder selectDetailOrder = new StringBuilder();
			selectDetailOrder.append(" 	select  g_img,concat(concat(concat(z_sido,' '||nvl(z_gugun,'')),' '||z_dong),' '||z_bunji)o_addr, o_delmsg,p_method , o_person, o.o_code, o_score	 ")
					.append("	from ordering o, goods g, pay p, ADDRESS a ")
					.append(" where (o.g_code=g.g_code) and (p.m_id=o.m_id) and  (a.z_seq=o.z_seq) and o.o_code=? ");

			pstmt = con.prepareStatement(selectDetailOrder.toString());
			pstmt.setString(1, odDTO.getO_code());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				odDTO.setG_img(rs.getString("g_img"));
				odDTO.setO_addr(rs.getString("o_addr"));
				odDTO.setO_delmsg(rs.getString("o_delmsg"));
				odDTO.setP_method(rs.getString("p_method"));
				odDTO.setO_person(rs.getString("o_person"));
				odDTO.setO_score(rs.getInt("o_score"));
			} // end if

		} finally {
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally

	}// selectDetailOrder

	public boolean deleteBrand(String name) throws SQLException {
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConn();
			StringBuilder deleteBrand = new StringBuilder();
			deleteBrand.append(" 	delete from brand where b_code =(select b_code from brand where b_name=?) ");
			pstmt = con.prepareStatement(deleteBrand.toString());

			pstmt.setString(1, name);
			flag = pstmt.executeUpdate() == 1;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // finally
		return flag;
	}// deleteBrand

	public boolean updateBrand(UpdateBrandVO ubVO) throws SQLException {
		boolean updateFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConn();
			StringBuilder updateBrand = new StringBuilder();
			updateBrand.append("  update brand	").append("  set  b_img=?, b_name=?	")
					.append("  where b_code =(select b_code from brand where b_name=?) 	");

			pstmt = con.prepareStatement(updateBrand.toString());
			pstmt.setString(1, ubVO.getB_img());
			pstmt.setString(2, ubVO.getB_name());
			pstmt.setString(3, ubVO.getB_code());// ������ �귣���� �̸�

			updateFlag = pstmt.executeUpdate() == 1;

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
		return updateFlag;
	}// updateBrand

	public String selectBrandImg(String name) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String img = "";
		try {
			con = getConn();
			StringBuilder selectBrand = new StringBuilder();
			selectBrand.append(" 	select  b_img ").append("	from brand 	")
					.append(" 	where b_name=? and  b_name!='DEL' 	");

			pstmt = con.prepareStatement(selectBrand.toString());
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				img = rs.getString("b_img");
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
		return img;
	}// selectBrandImg

	public boolean updateTypeDEL(SelectListVO slVO) throws SQLException {
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConn();
			StringBuilder updateDEL = new StringBuilder();
			switch (slVO.getIndex()) {
			case 1:
				updateDEL.append(
						" 	update goods set d_code='DEL' where d_code = (select d_code from detail_clothestype where d_type=?) ");
				break;
			case 2:
				updateDEL.append(
						" 	update goods set b_code='DEL' where b_code = (select b_code from brand where b_name=?) ");

			}// end switch
			pstmt = con.prepareStatement(updateDEL.toString());

			pstmt.setString(1, slVO.getJtfData());
			flag = pstmt.executeUpdate() == 1;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // finally
		return flag;
	}// updateTypeDEL

	public boolean deleteType(String code) throws SQLException {
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConn();
			StringBuilder deleteType = new StringBuilder();
			deleteType.append(
					" 	delete from DETAIL_CLOTHESTYPE  where d_code=(select d_code from detail_clothestype where d_type=?)	");
			pstmt = con.prepareStatement(deleteType.toString());

			pstmt.setString(1, code);
			flag = pstmt.executeUpdate() == 1;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // finally
		return flag;
	}// deleteType

	public List<SelectBrandListVO> selectBrandList() throws SQLException {
		List<SelectBrandListVO> list = new ArrayList<SelectBrandListVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SelectBrandListVO sbVO = null;
		try {
			con = getConn();
			StringBuilder selectBrand = new StringBuilder();
			selectBrand.append(" select b_name,b_code 	").append(" from brand 	").append(" where b_code!='DEL' 	")
					.append(" order by b_code 	");

			pstmt = con.prepareStatement(selectBrand.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sbVO = new SelectBrandListVO(rs.getString("b_name"), rs.getString("b_code"));
				list.add(sbVO);
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
	}// selectBrand

	public boolean updateType(UpdateTypeVO utVO) throws SQLException {
		boolean updateFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConn();
			StringBuilder updateType = new StringBuilder();
			updateType.append("  update  detail_clothestype	").append("  set d_type=?	")
					.append("  where d_code=(select d_code from detail_clothestype where d_type=?)	");

			pstmt = con.prepareStatement(updateType.toString());
			pstmt.setString(1, utVO.getnewType());// ������ �Һз��� newcode
			pstmt.setString(2, utVO.getD_type()); // ������ �Һз���

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
	}// updateType
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////// 9�� 25�� ȸ����
	///////////////////////////////////////////////////// ///////////////////////////////////////////////////////////////

	public void selectDetailCus(SelectCusDetailDTO sdDTO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConn();
			StringBuilder selectDetailCus = new StringBuilder();
			selectDetailCus.append(
					" 	select m_ip,m_birth, m_gender,m_detail_addr,m_email,m_img , concat(concat(concat(z_sido,' '||nvl(z_gugun,' ')),' '||z_dong),' '||z_bunji) z_addr, z_zipcode, m_stop_reason	 ")
					.append("		from   member m, address a 	")
					.append("		where (m.z_seq=a.z_seq) and m_code=?	 ");

			pstmt = con.prepareStatement(selectDetailCus.toString());
			pstmt.setString(1, sdDTO.getM_code());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				sdDTO.setM_ip(rs.getString("m_ip"));
				sdDTO.setM_birth(rs.getString("m_birth"));
				sdDTO.setM_gender(rs.getString("m_gender"));
				sdDTO.setM_detail_addr(rs.getString("m_detail_addr"));
				sdDTO.setM_email(rs.getString("m_email"));
				sdDTO.setM_img(rs.getString("m_img"));
				sdDTO.setZ_addr(rs.getString("z_addr"));
				sdDTO.setZ_zipcode(rs.getString("z_zipcode"));
				sdDTO.setM_stop_reason(rs.getString("m_stop_reason"));
			} // end if

		} finally {
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally

	}// selectDetailOrder

	public boolean updateDetailCus(UpdateCustomerVO ucVO) throws SQLException {
		boolean updateFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConn();
			StringBuilder update = new StringBuilder();
			update.append("	update member	").append(" 	set  m_name=?,m_birth=?,m_gender=?,m_phone=?,	")
					.append("	 m_detail_addr=? ,m_img=? ,m_email=? , z_seq=( select z_seq	")
					.append("	from (select concat(concat(concat(z_sido,' '||nvl(z_gugun,' ')),' '||z_dong),' '||z_bunji) z_addr,	")
					.append("	 z_seq, z_zipcode from address)	")
					.append("	where z_zipcode like '%'||?||'%'  and z_addr like '%'||?||'%')	")
					.append("	where m_id =?            	");
			pstmt = con.prepareStatement(update.toString());
			pstmt.setString(1, ucVO.getM_name());
			pstmt.setString(2, ucVO.getM_birth());
			pstmt.setString(3, ucVO.getM_gender());
			pstmt.setString(4, ucVO.getM_phone());
			pstmt.setString(5, ucVO.getM_detail_addr());
			pstmt.setString(6, ucVO.getM_img());
			pstmt.setString(7, ucVO.getM_email());
			pstmt.setString(8, ucVO.getZ_zipcode());
			pstmt.setString(9, ucVO.getZ_addr());
			pstmt.setString(10, ucVO.getM_code());

			updateFlag = pstmt.executeUpdate() == 1;
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
		return updateFlag;
	}// updateDetailCus

	public boolean updateStopFlag(updateStopVO usVO) throws SQLException {
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConn();
			StringBuilder updateStopFlag = new StringBuilder();
			updateStopFlag.append(" 	update member set m_stop_flag=?  , m_stop_reason=?  where m_code=?  ");
			pstmt = con.prepareStatement(updateStopFlag.toString());
			pstmt.setString(1, usVO.getM_stopflag());
			pstmt.setString(2, usVO.getM_reason());
			pstmt.setString(3, usVO.getM_code());
			flag = pstmt.executeUpdate() == 1;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // finally
		return flag;
	}// updateStopFlag

	public SelectClickGoodsDetailDTO searchClickGoodsDetail(String goodsCode) throws SQLException {
		SelectClickGoodsDetailDTO scgdDTO = new SelectClickGoodsDetailDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			scgdDTO.setgCode(goodsCode);

			con = getConn();

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
	
	public int selectGoodsLikeNum(String goodsCode) throws SQLException {
		int likeNum = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConn();
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

}// class
