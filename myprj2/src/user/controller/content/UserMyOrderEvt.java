package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import user.dao.ClientDAO;
import user.view.content.UserMyOrderDetailView;
import user.view.content.UserMyOrderView;
import user.vo.content.SelectMyOrderDetailDTO;
import user.vo.content.SelectMyOrderVO;

public class UserMyOrderEvt extends MouseAdapter implements ActionListener {

	private UserMyOrderView umo;
	private String id;

	public UserMyOrderEvt(UserMyOrderView umo, String id) {
		this.umo = umo;
		this.id = id;
		searchOrderList();
	}// UserMyOrderEvt

	/**
	 * 고객의 주문내역 조회
	 */
	public void searchOrderList() {
		DefaultTableModel dtmOrder = umo.getDtmOrderList();
		dtmOrder.setRowCount(0);// 초기화

		ClientDAO cDAO = ClientDAO.getInstance();
		Object[] rowData = null; // jtable에 넣을 데이터
		List<SelectMyOrderVO> list = null;
		SelectMyOrderVO smVO = null;

		try {
			list = cDAO.selectAllOrderList(id);
			if (list.isEmpty()) {
				rowData = new Object[5];
				rowData[0] = "주문내역이 없습니다.";
				dtmOrder.addRow(rowData);
			} // end if
			for (int i = 0; i < list.size(); i++) {
				smVO = list.get(i);
				rowData = new Object[5];
				rowData[0] = smVO.getG_name() + "(" + smVO.getO_code() + ")";
				rowData[1] = smVO.getB_name();
				rowData[2] = smVO.getO_delivery();
				if (smVO.getO_delivery().equals("N")) {
					rowData[2] = "배송 준비중";
				} else {
					rowData[2] = "배송 완료";
				} // end if
				rowData[3] = smVO.getG_price();
				rowData[4] = smVO.getO_date();
				dtmOrder.addRow(rowData);
			} // end for

		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// searchOrderList

	/**
	 * 상세 주문내역
	 */
	public void selectDetailOrder(JTable jtOrder) {
		SelectMyOrderDetailDTO moDTO = new SelectMyOrderDetailDTO();
		String name = (String) umo.getJtOrder().getValueAt(jtOrder.getSelectedRow(), 0);
		String goodsName = name.substring(0, name.lastIndexOf("("));
		String orderNum = name.substring(name.lastIndexOf("(") + 1, name.lastIndexOf(")"));
		String brandName = (String) umo.getJtOrder().getValueAt(jtOrder.getSelectedRow(), 1);
		String delivery = (String) umo.getJtOrder().getValueAt(jtOrder.getSelectedRow(), 2);
		int price = (int) umo.getJtOrder().getValueAt(jtOrder.getSelectedRow(), 3);
		String orderDate = (String) umo.getJtOrder().getValueAt(jtOrder.getSelectedRow(), 4);

		moDTO.setG_name(goodsName);
		moDTO.setO_code(orderNum);
		moDTO.setB_name(brandName);
		moDTO.setO_delivery(delivery);
		moDTO.setO_buypay(price);
		moDTO.setO_date(orderDate);

		ClientDAO cDAO = ClientDAO.getInstance();
		try {
			cDAO.selectDetailMyOrder(moDTO);
			new UserMyOrderDetailView(moDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// selectDetailOrder

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == umo.getJbtClose()) {
			umo.dispose();
		}

	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getClickCount() == 2) {
			if (me.getSource() == umo.getJtOrder()) {
				JTable jtOrder = umo.getJtOrder();
				selectDetailOrder(jtOrder);
			} // end if
		} // end if
	}// mouseClicked

}// class
