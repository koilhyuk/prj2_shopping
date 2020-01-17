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
 * -�ֹ�����â
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
	 * �ֹ���ȸ
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

			if (list.isEmpty()) {// ��ȸ�� ����Ʈ�� ���ٸ�
				JOptionPane.showMessageDialog(ov, "��ȸ������ �ֹ������� �����ϴ�. ");
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
	 * ��ۻ��� ���� Y : ��ۿϷ� N : ��۾���
	 */
	public void modifyDeli() {
		JTable jtOrder = ov.getJtOrder();
		int selectRow = jtOrder.getSelectedRow();
		if (selectRow == -1) {//
			JOptionPane.showMessageDialog(ov, "����� ������ ���� �������ּ���.");
			return;
		} // end if

		String status = (String) jtOrder.getValueAt(selectRow, 6);
		String orderCode = (String) jtOrder.getValueAt(selectRow, 0);
		String orderid = (String) jtOrder.getValueAt(selectRow, 1);
		String orderName = orderid.substring(0, orderid.lastIndexOf("("));

		UpdateDeliveryChkVO udVO = null;
		switch (JOptionPane.showConfirmDialog(ov, "��ۻ��¸� �����Ͻðڽ��ϱ�?")) {
		case JOptionPane.OK_OPTION:
			if (status.equals("N")) {
				status = "Y";
			} else {
				status = "N";
			} // end if
			jtOrder.setValueAt(status, selectRow, 6);
			AdminDAO aDAO = AdminDAO.getInstance();
			udVO = new UpdateDeliveryChkVO(orderCode, status);
			String resultMsg = orderName + "���� ��ۻ��°� ������� �ʾҽ��ϴ�";
			try {
				if (aDAO.updateDelivery(udVO)) {
					resultMsg = orderName + "���� ��ۻ��°� ����Ǿ����ϴ�";
				} // end if
				JOptionPane.showMessageDialog(ov, resultMsg);
			} catch (SQLException e) {
				e.printStackTrace();
			} // end if

		}// end switch

	}// modifyDeli

	/**
	 * �ֹ����� ���ý� ���� order_pay�� ���� �ֹ��ڵ�� ����������
	 * 
	 */
	public void deleteOrder() {
		JTable jtOrder = ov.getJtOrder();
		int selectRow = ov.getJtOrder().getSelectedRow();
		
//		String code=jtOrder.getValueAt(selectRow, 0);
		if (selectRow == -1) {
			JOptionPane.showMessageDialog(ov, "�����Ͻ� ���� �������ּ���.");
			return;
		} // end if
		String code = (String) jtOrder.getValueAt(selectRow, 0);
		String name = (String) jtOrder.getValueAt(selectRow, 1);
		String deli = (String) jtOrder.getValueAt(selectRow, 6);
		try {
			switch (JOptionPane.showConfirmDialog(ov, name + "���� �ֹ������� �����Ͻðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				AdminDAO aDAO = AdminDAO.getInstance();
//				doVO= new ����_DeleteOrderVO(code);
				if(deli.equals("N")||deli=="N") {
					JOptionPane.showMessageDialog(ov, "���� ������ �������� �ֹ������̹Ƿ� ������ �Ұ����մϴ�.");
					return;
				}//end if
				if (aDAO.deleteOrder(code)) {
					ov.getDtmOrder().removeRow(selectRow);
					JOptionPane.showMessageDialog(ov, name + "���� �ֹ������� �����Ǿ����ϴ�.");
				selectAllOrder();
				} // end if
			}// end switch
		selectAllOrder();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ov, "���� ������ �������� �ֹ������̹Ƿ� ������ �Ұ����մϴ�.");
		} // end catch
	}// deleteOrder

	/**
	 * ������ �ֹ������� �������� ����
	 * 
	 * @param jtOrder
	 */
	public void setChoiceOrder(JTable jtOrder) {
		// ��â���� ����� �� VO�� ����
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

		odDTO.setO_code(orderNum);// �ֹ���ȣ
		odDTO.setO_person(orderer); // �޴���
		odDTO.setM_id(orderid);// ȸ�����̵�
		odDTO.setO_phone(phone);// ����ó
		odDTO.setG_name(goodsName);// ��ǰ��
		odDTO.setO_buypay(buypay);// �����ݾ�
		odDTO.setO_quantity(quantity);// ����
		odDTO.setO_delivery(delivery);// ��ۿ���
		odDTO.setO_date(orderDate);// �ֹ�����

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

		if (ae.getSource() == ov.getJbtnSearch() || ae.getSource() == ov.getJtfSearch()) {// ��ȸ��ư
			// ��ü", "�ֹ� ��ȣ", "ȸ�����̵�", "��ȭ��ȣ"
			String search =ov.getJtfSearch().getText().trim();
			index = ov.getJcbSearch().getSelectedIndex();
			// "��ü", "�ֹ� ��ȣ", "ȸ�����̵�","�ֹ���", "��ǰ��","�ֹ�����"
			switch (index) {
			case ALL_ORDER:// ��ü
				JOptionPane.showMessageDialog(ov, "��ü �ֹ������� ��ȸ�Ǿ����ϴ�.");
				ov.getJtfSearch().setText("");
				ov.getJtfSearch().requestFocus();
				selectAllOrder();
				break;
			case ORDER_NUM: // �ֹ���ȣ
				if (search.isEmpty()) {
					JOptionPane.showMessageDialog(ov, "�ֹ���ȣ�� �Է����ּ���.");
					ov.getJtfSearch().setText("");
					ov.getJtfSearch().requestFocus();
				} // end if
				selectAllOrder();
				break;
			case CUS_ID:// ȸ�����̵�
				if (search.isEmpty()) {
					JOptionPane.showMessageDialog(ov, "ȸ�����̵� �Է����ּ���.");
					ov.getJtfSearch().setText("");
					ov.getJtfSearch().requestFocus();
				} // end if
				selectAllOrder();
				break;
			case RECEIVE: // �޴���
				if (search.isEmpty()) {
					JOptionPane.showMessageDialog(ov, "�޴��̸� �Է����ּ���.");
					ov.getJtfSearch().setText("");
					ov.getJtfSearch().requestFocus();
				} // end if
				selectAllOrder();
				break;

			case GOODS_NAME: // ��ǰ��
				if (search.isEmpty()) {
					JOptionPane.showMessageDialog(ov, "��ǰ���� �Է����ּ���.");
					ov.getJtfSearch().setText("");
					ov.getJtfSearch().requestFocus();
				} // end if
				selectAllOrder();
				break;
			case ORDER_DATE: // �ֹ�����
				if (search.isEmpty()) {
					JOptionPane.showMessageDialog(ov, "�ֹ����ڸ� �Է����ּ���.");
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
		if (me.getClickCount() == DOUBLE_CLICK) {// ����Ŭ��
			if (me.getSource() == ov.getJtOrder()) {
				JTable jtOrder = ov.getJtOrder();
				setChoiceOrder(jtOrder);
			} // end if
		} // end if

	}// mouseClicked

}// class
