package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import admin.dao.AdminDAO;
import admin.view.AdCashCalcView;
import admin.view.AdOrderDetailView;
import admin.view.AdOrderManageView;
import admin.vo.SelectListVO;
import admin.vo.SelectOrderDetailDTO;
import admin.vo.SelectOrderListVO;
import admin.vo.UpdateDeliveryChkVO;

/**
 * -주문관리창
 * 
 * @author hyebin
 */
public class AdOrderManageEvt extends MouseAdapter implements ActionListener {

	public static final int DOUBLE_CLICK = 2;
	public static final int ALL_ORDER = 0;
	public static final int ORDER_NUM = 1;
	public static final int CUS_ID = 2;
	public static final int RECEIVE = 3;
	public static final int GOODS_NAME = 4;
	public static final int ORDER_DATE = 5;

	private AdOrderManageView ov;
	private int index;

	public AdOrderManageEvt(AdOrderManageView ov) {
		this.ov = ov;
		selectAllOrder();

	}// OrderListEvt

	/**
	 * 주문조회
	 */

	public void selectAllOrder() {
		DefaultTableModel dtmOrder = ov.getDtmOrder();
		dtmOrder.setRowCount(0);
		AdminDAO aDAO = AdminDAO.getInstance();
		Object[] rowData = null;
		List<SelectOrderListVO> list = null;
		SelectOrderListVO oVO = null;
		SelectListVO slVO = null;
		String jtfData = ov.getJtfSearch().getText().trim();
		try {
			slVO = new SelectListVO(jtfData, index);
			index = ov.getJcbSearch().getSelectedIndex();
			list = aDAO.selectAllOrder(slVO);

			if (list.isEmpty()) {// 조회할 리스트가 없다면
				JOptionPane.showMessageDialog(ov, "조회가능한 주문내역이 없습니다. ");
				ov.getJtfSearch().setText("");
				ov.getJtfSearch().requestFocus();
				selectAllOrder();
			} // end if

			for (int i = 0; i < list.size(); i++) {
				oVO = list.get(i);
				rowData = new Object[8];
				rowData[0] = oVO.getO_code();
				rowData[1] = oVO.getM_id() + "(" + oVO.getO_person() + ")";
				rowData[2] = oVO.getO_phone();
				rowData[3] = oVO.getG_name();
				rowData[4] = oVO.getO_buypay();
				rowData[5] = oVO.getO_quantity();
				rowData[6] = oVO.getO_delivery();
				rowData[7] = oVO.getO_date();
				ov.getDtmOrder().addRow(rowData);

			} // end for
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// selectAllOrder

	/**
	 * 배송상태 변경 Y : 배송완료 N : 배송안함
	 */
	public void modifyDeli() {
		JTable jtOrder = ov.getJtOrder();
		int selectRow = jtOrder.getSelectedRow();
		if (selectRow == -1) {//
			JOptionPane.showMessageDialog(ov, "배송을 변경할 행을 선택해주세요.");
			return;
		} // end if

		String status = (String) jtOrder.getValueAt(selectRow, 6);
		String orderCode = (String) jtOrder.getValueAt(selectRow, 0);
		String orderid = (String) jtOrder.getValueAt(selectRow, 1);
		String orderName = orderid.substring(0, orderid.lastIndexOf("("));

		UpdateDeliveryChkVO udVO = null;
		switch (JOptionPane.showConfirmDialog(ov, "배송상태를 변경하시겠습니까?")) {
		case JOptionPane.OK_OPTION:
			if (status.equals("N")) {
				status = "Y";
			} else {
				status = "N";
			} // end if
			jtOrder.setValueAt(status, selectRow, 6);
			AdminDAO aDAO = AdminDAO.getInstance();
			udVO = new UpdateDeliveryChkVO(orderCode, status);
			String resultMsg = orderName + "님의 배송상태가 변경되지 않았습니다";
			try {
				if (aDAO.updateDelivery(udVO)) {
					resultMsg = orderName + "님의 배송상태가 변경되었습니다";
				} // end if
				JOptionPane.showMessageDialog(ov, resultMsg);
			} catch (SQLException e) {
				e.printStackTrace();
			} // end if

		}// end switch

	}// modifyDeli

	/**
	 * 주문내역 선택시 삭제 order_pay에 없는 주문코드면 삭제가능함
	 * 
	 */
	public void deleteOrder() {
		JTable jtOrder = ov.getJtOrder();
		int selectRow = ov.getJtOrder().getSelectedRow();
		
//		String code=jtOrder.getValueAt(selectRow, 0);
		if (selectRow == -1) {
			JOptionPane.showMessageDialog(ov, "삭제하실 행을 선택해주세요.");
			return;
		} // end if
		String code = (String) jtOrder.getValueAt(selectRow, 0);
		String name = (String) jtOrder.getValueAt(selectRow, 1);
		String deli = (String) jtOrder.getValueAt(selectRow, 6);
		try {
			switch (JOptionPane.showConfirmDialog(ov, name + "님의 주문내역을 삭제하시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				AdminDAO aDAO = AdminDAO.getInstance();
//				doVO= new 수정_DeleteOrderVO(code);
				if(deli.equals("N")||deli=="N") {
					JOptionPane.showMessageDialog(ov, "현재 결제가 진행중인 주문내역이므로 삭제가 불가능합니다.");
					return;
				}//end if
				if (aDAO.deleteOrder(code)) {
					ov.getDtmOrder().removeRow(selectRow);
					JOptionPane.showMessageDialog(ov, name + "번의 주문내역을 삭제되었습니다.");
				selectAllOrder();
				} // end if
			}// end switch
		selectAllOrder();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ov, "현재 결제가 진행중인 주문내역이므로 삭제가 불가능합니다.");
		} // end catch
	}// deleteOrder

	/**
	 * 선택한 주문내역을 상세정보를 설정
	 * 
	 * @param jtOrder
	 */
	public void setChoiceOrder(JTable jtOrder) {
		// 상세창에서 사용할 값 VO를 설정
		SelectOrderDetailDTO odDTO = new SelectOrderDetailDTO();
		String orderNum = (String) ov.getJtOrder().getValueAt(jtOrder.getSelectedRow(), 0);
		String id = (String) ov.getJtOrder().getValueAt(jtOrder.getSelectedRow(), 1);
		String orderid = id.substring(0, id.lastIndexOf("("));
		String orderer = id.substring(id.lastIndexOf("(") + 1, id.lastIndexOf(")"));
		String phone = (String) ov.getJtOrder().getValueAt(jtOrder.getSelectedRow(), 2);
		String goodsName = (String) ov.getJtOrder().getValueAt(jtOrder.getSelectedRow(), 3);
		int buypay = (int) ov.getJtOrder().getValueAt(jtOrder.getSelectedRow(), 4);
		int quantity = (int) ov.getJtOrder().getValueAt(jtOrder.getSelectedRow(), 5);
		String delivery = (String) ov.getJtOrder().getValueAt(jtOrder.getSelectedRow(), 6);
		String orderDate = (String) ov.getJtOrder().getValueAt(jtOrder.getSelectedRow(), 7);

		odDTO.setO_code(orderNum);// 주문번호
		odDTO.setO_person(orderer); // 받는이
		odDTO.setM_id(orderid);// 회원아이디
		odDTO.setO_phone(phone);// 연락처
		odDTO.setG_name(goodsName);// 상품명
		odDTO.setO_buypay(buypay);// 결제금액
		odDTO.setO_quantity(quantity);// 수량
		odDTO.setO_delivery(delivery);// 배송여부
		odDTO.setO_date(orderDate);// 주문일자

		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			aDAO.selectDetailOrder(odDTO);
			new AdOrderDetailView(odDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// setChoiceOrder

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == ov.getJbtnSearch() || ae.getSource() == ov.getJtfSearch()) {// 조회버튼
			// 전체", "주문 번호", "회원아이디", "전화번호"
			String search =ov.getJtfSearch().getText().trim();
			index = ov.getJcbSearch().getSelectedIndex();
			// "전체", "주문 번호", "회원아이디","주문자", "제품명","주문일자"
			switch (index) {
			case ALL_ORDER:// 전체
				JOptionPane.showMessageDialog(ov, "전체 주문관리가 조회되었습니다.");
				ov.getJtfSearch().setText("");
				ov.getJtfSearch().requestFocus();
				selectAllOrder();
				break;
			case ORDER_NUM: // 주문번호
				if (search.isEmpty()) {
					JOptionPane.showMessageDialog(ov, "주문번호를 입력해주세요.");
					ov.getJtfSearch().setText("");
					ov.getJtfSearch().requestFocus();
				} // end if
				selectAllOrder();
				break;
			case CUS_ID:// 회원아이디
				if (search.isEmpty()) {
					JOptionPane.showMessageDialog(ov, "회원아이디를 입력해주세요.");
					ov.getJtfSearch().setText("");
					ov.getJtfSearch().requestFocus();
				} // end if
				selectAllOrder();
				break;
			case RECEIVE: // 받는이
				if (search.isEmpty()) {
					JOptionPane.showMessageDialog(ov, "받는이를 입력해주세요.");
					ov.getJtfSearch().setText("");
					ov.getJtfSearch().requestFocus();
				} // end if
				selectAllOrder();
				break;

			case GOODS_NAME: // 상품명
				if (search.isEmpty()) {
					JOptionPane.showMessageDialog(ov, "상품명을 입력해주세요.");
					ov.getJtfSearch().setText("");
					ov.getJtfSearch().requestFocus();
				} // end if
				selectAllOrder();
				break;
			case ORDER_DATE: // 주문일자
				if (search.isEmpty()) {
					JOptionPane.showMessageDialog(ov, "주문일자를 입력해주세요.");
					ov.getJtfSearch().setText("");
					ov.getJtfSearch().requestFocus();
				} // end if
				selectAllOrder();
				break;

			}// end switch
		} // end if

		if (ae.getSource() == ov.getJmDelivery()) {
			modifyDeli();
		} // end if
		if (ae.getSource() == ov.getJmDelete()) {
			deleteOrder();
		} // end if
		if (ae.getSource() == ov.getJtfSearch()) {
//			selectAllOrder();
		} // end if
		if (ae.getSource() == ov.getJbtnCash()) {
			new AdCashCalcView();
		} // end if

	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getSource() == ov.getJtfSearch()) {
			ov.getJtfSearch().setText("");
		} // end if
		if (me.getClickCount() == DOUBLE_CLICK) {// 더블클릭
			if (me.getSource() == ov.getJtOrder()) {
				JTable jtOrder = ov.getJtOrder();
				setChoiceOrder(jtOrder);
			} // end if
		} // end if

	}// mouseClicked

}// class
