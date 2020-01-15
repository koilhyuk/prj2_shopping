package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import admin.dao.AdminDAO;
import admin.run.StaticCla;
import admin.view.AdGoodsAddView;
import admin.view.AdGoodsCateManageView;
import admin.view.AdGoodsDetailView;
import admin.view.AdGoodsManageView;
import admin.vo.SelectGoodsDetailDTO;
import admin.vo.SelectGoodsListVO;
import admin.vo.SelectListVO;

/**
 * 상품관리 Event - 선택한 상품검색 - 선택한 상품 삭제
 * 
 * @author hyebin
 *
 */
public class AdGoodsManageEvt extends MouseAdapter implements ActionListener {
	public static final int DOUBLE_CLICK = 2;// 더블클릭
	// 조회를 위한 상수
	public static final int ALL_GOODS = 0;// 전체
	public static final int GOODS_CODE = 1;// 상품코드
	public static final int BRAND = 2;// 브랜드
	public static final int GOODS_NAME = 3;// 상품명
	public static final int SCORE = 4;// 더블클릭

	private int index; // JCOMBOBOX의 리스트의 인덱스
	private String search; // T.F
	private AdGoodsManageView gv;

	public AdGoodsManageEvt(AdGoodsManageView gv) {
		this.gv = gv;
		searchGoods();

	}// AdGoodsManageEvt

	/**
	 * 선택한 상품의 상세정보를 설정
	 */
	public void setChoiceGoods(JTable jtGoods) {
		// 상세창에서 사용할 값 DTO에 설정한다!
		SelectGoodsDetailDTO dgDTO = new SelectGoodsDetailDTO();
		// 상품리스트에서 얻을수 있는 값, 선택한 행의 상품의 값을 얻는다
		String goodsName = (String) jtGoods.getValueAt(jtGoods.getSelectedRow(), 0); // 상품명
		String goodsCode = goodsName.substring(goodsName.lastIndexOf("(") + 1, goodsName.lastIndexOf(")")); // 상품코드
		String brand = jtGoods.getValueAt(jtGoods.getSelectedRow(), 1).toString(); // 브랜드
		String detailType = (String) jtGoods.getValueAt(jtGoods.getSelectedRow(), 2);// 소분류
		// 선택한 상품금액의 값을 ,로 나눠서 배열에 넣음
		int price = (int) jtGoods.getValueAt(jtGoods.getSelectedRow(), 3);// 가격

		int saleNum = (int) jtGoods.getValueAt(jtGoods.getSelectedRow(), 4);// 판매수량
		int score = (int) jtGoods.getValueAt(jtGoods.getSelectedRow(), 5);// 평점
		int inventory = (int) jtGoods.getValueAt(jtGoods.getSelectedRow(), 6);// 재고량
		String inputDate = (String) jtGoods.getValueAt(jtGoods.getSelectedRow(), 7);// 입고날짜

		// 값을 넣는다.
		dgDTO.setG_code(goodsCode.trim()); // 코드
		dgDTO.setG_name(goodsName.trim());// 상품명
		dgDTO.setB_name(brand.trim());// 브앤드
		dgDTO.setD_type(detailType.trim());// 상품분류

		dgDTO.setG_price(price);// 가격
		dgDTO.setG_salenum(saleNum);// 판매수량
	
		dgDTO.setG_score(score);// 평점
		dgDTO.setG_inventory(inventory);// 재고량
		dgDTO.setG_inputdate(inputDate.trim());// 입고일

		// DB에서 얻을 수 있는 값
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			aDAO.selectDetailGoods(dgDTO);// 특장점, 이미지, 브랜드, 타입
			// 상세보기 창에 값을 넣어줌
			new AdGoodsDetailView(this, dgDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// setChoiceGoods

	/**
	 * JcomboBox에서 선택한 항목으로 조회 DBMS 테이블에서 조회한 상품리스트를 JTable에 설정
	 */
	public void searchGoods() {
		DefaultTableModel dtmPro = gv.getDtmGoodsList(); // Jtable을 가져옴
//		dtmPro.removeRow(0);
		dtmPro.setRowCount(0); // 초기화

		AdminDAO aDAO = AdminDAO.getInstance(); // 객체 하나만 생성하고 유지

		Object[] rowData = null; // jtable에 넣을 데이터
		search = gv.getJtfSearch().getText().trim(); // T.F에 있는 내용

		List<SelectGoodsListVO> list = null;
		SelectListVO slVO = null;// 조회 인덱스, T.F에 대한 VO
		SelectGoodsListVO gvO = null; // JTable의 값을 조회하기 위한 VO

		try {
			slVO = new SelectListVO(search, index);
			index = gv.getJcbSearch().getSelectedIndex(); // 선택한 항목의 인덱스
			list = aDAO.selectAllGoods(slVO);

			if (list.isEmpty()) {// 조회할 리스트가 없다면 (조회할 상품이 없다면 )
				JOptionPane.showMessageDialog(gv, "조회가능한 상품이 없습니다.");
				gv.getJtfSearch().setText("");// 초기화
				gv.getJtfSearch().requestFocus(); // 커서위치
				// 조회가능한 제품이 없으면 컨펌창나오고 전체 리스트가 나온다.
				searchGoods();
			} // end if
			for (int i = 0; i < list.size(); i++) { // 데이터를 넣음
				gvO = list.get(i);
				rowData = new Object[8];
				rowData[0] = gvO.getG_name() + "(" + gvO.getG_code() + ")";
				if (gvO.getB_img() != null) {// 이미지가 있다면
					rowData[1] = new ImageIcon(StaticCla.FILE_PATH+"/rs_br_"+gvO.getB_img().trim());
				} else {// 이미지가 없다면 이름을 넣어
					rowData[1] = gvO.getB_name();
				} // end else
				rowData[2] = gvO.getD_type();
				rowData[3] = gvO.getG_price();
				rowData[4] = gvO.getG_salenum();
				rowData[5] = gvO.getG_score();
				rowData[6] = gvO.getG_inventory();
				rowData[7] = gvO.getG_inputdate();
				dtmPro.addRow(rowData);
			} // end for

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(gv, "관리자님 서비스가 원활하지 않습니다. 죄송합니다.");
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(gv, "조회가능한 상품이 없습니다.");
		} // end catch
	}// searchGoods

	/**
	 * 주문이 안들어온 상품만 삭제가능
	 */
	public void removeGoods() {
		JTable jtGoods = gv.getJtGoods();

		int selectRow = jtGoods.getSelectedRow(); // 삭제할 상품의 행
		if (selectRow == -1) {// 삭제할 상품의 행이 선택되지 않았다면
			JOptionPane.showMessageDialog(gv, "삭제하실 상품을 선택해주세요.");
			return;
		} // end if

		String name = (String) jtGoods.getValueAt(selectRow, 0);// 상품명
		String goodsCode = name.substring(name.lastIndexOf("(") + 1, name.lastIndexOf(")")); // 상품코드
		switch (JOptionPane.showConfirmDialog(gv, goodsCode + "번의 상품을 삭제하시겠습니까?")) {
		case JOptionPane.OK_OPTION:
			AdminDAO aDAO = AdminDAO.getInstance();
			try {
				if (aDAO.deleteGoods(goodsCode)) {
					gv.getDtmGoodsList().removeRow(selectRow);
					JOptionPane.showMessageDialog(gv, goodsCode + "번의 상품이 삭제되었습니다.");
				} // end if;
				searchGoods();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(gv, "현재 주문이 있는 상품이기때문에 상품삭제가 불가능합니다.");
//				e.printStackTrace();
			} // end if
		}// end switch
	}// removeGoods

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == gv.getJbtnSearch()) { // 제품조회
			// 선택한 리스트가 조회되게 작성
			index = gv.getJcbSearch().getSelectedIndex();// jcombobox 인덱스
			search = gv.getJtfSearch().getText().trim(); // T.F의 내용
			switch (index) {
			case ALL_GOODS: // 전체
//				JOptionPane.showMessageDialog(gv, "전체상품이 조회되었습니다.");
				gv.getJtfSearch().setText("");// 초기화
				gv.getJtfSearch().requestFocus();
				searchGoods();
				break;

			case GOODS_CODE: // 상품코드
				if (search.isEmpty()) {// 공백이라면
					JOptionPane.showMessageDialog(gv, "조회하실 상품 코드를 입력해주세요");
					gv.getJtfSearch().setText("");
					gv.getJtfSearch().requestFocus();
					return;
				} // end if
				searchGoods();
				break;

			case BRAND: // 브랜드
				if (search.isEmpty()) {// 공백이라면
					JOptionPane.showMessageDialog(gv, "조회하실 브랜드를 입력해주세요");
					gv.getJtfSearch().setText("");// 공백이라면
					gv.getJtfSearch().requestFocus();
					return;
				} // end if
				searchGoods();
				break;

			case GOODS_NAME: // 상품명
				if (search.isEmpty()) {// 공백이라면
					JOptionPane.showMessageDialog(gv, "조회하실 상품명을 입력해주세요");
					gv.getJtfSearch().setText("");
					gv.getJtfSearch().requestFocus();
					return;
				} // end if
				searchGoods();
				break;

			case SCORE: // 평점
				if (search.isEmpty()) {// 공백이라면
					JOptionPane.showMessageDialog(gv, "조회하실 평점을 입력해주세요");
					gv.getJtfSearch().setText("");
					gv.getJtfSearch().requestFocus();
					return;
				} // end if
				try {
					if (Integer.parseInt(search) == 0 || Integer.parseInt(search) > 5) { // 검색창이 1~5점 사이가 아니라면
						JOptionPane.showMessageDialog(gv, "평점은 1~5점내로 입력해주세요.");
						gv.getJtfSearch().setText("");
						gv.getJtfSearch().requestFocus();
					} // end if
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(gv, "평점은 숫자만 입력가능합니다.");
					gv.getJtfSearch().setText("");
					gv.getJtfSearch().requestFocus();
				} // end catch
				searchGoods();
				break;

			}// end switch
		} // end if
		if (ae.getSource() == gv.getJtfSearch()) {// 엔터
			searchGoods();
		} // end if
		if (ae.getSource() == gv.getJbtnUpload()) { // 제품등록
			new AdGoodsAddView(this);
		} // end if
		if (ae.getSource() == gv.getJmDelete()) {// 팝업 삭제
			removeGoods();
		} // end if
		if (ae.getSource() == gv.getJbtnCate()) {// 브랜드및 카테고리정보
			new AdGoodsCateManageView(this);
		} // end if
	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getClickCount() == DOUBLE_CLICK) {// 더블클릭
			if (me.getSource() == gv.getJtGoods()) {
				JTable jtGoods = gv.getJtGoods();
				setChoiceGoods(jtGoods); // 선택한 상품 상세보기창에 설정
			} // end if
		} // end if
		if (me.getSource() == gv.getJtfSearch()) { // 조회 T.F를 누르면 초기화
			gv.getJtfSearch().setText("");
		} // end if
	}// mouseClicked

}// class
