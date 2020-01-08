package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import admin.dao.SelectAddrDAO;
import admin.view.AdCusDetailView;
import admin.view.ZipcodeSearchView;
import admin.vo.CheckAddrVO;
import admin.vo.SearchAddrVO;

public class ZipcodeSearchEvt extends MouseAdapter implements ActionListener {
	public static final int DOUBLE_CLICK = 2;

	private ZipcodeSearchView zsv;

	private String sidoName;
	private String gugunName;
	private String inputAddrName;
	private  AdCusDetailView cdv;

	public ZipcodeSearchEvt(ZipcodeSearchView zsv, AdCusDetailView cdv) {
		this.zsv = zsv;
		this.cdv=cdv;
	}// ZipcodeSearchEvt

	private void selectSido() {

		zsv.getDcmGugun().removeAllElements();
		SelectAddrDAO saDAO = SelectAddrDAO.getInstance();
		List<String> gugunData = null;

		try {
			if (sidoName.equals("��ü") || sidoName.equals("����")) {
				zsv.getDcmGugun().addElement("��ü");
				return;
			} // end if

			gugunData = saDAO.selectGugun(sidoName.trim());
			zsv.getDcmGugun().addElement("��ü");
			for (int i = 0; i < gugunData.size(); i++) {
				zsv.getDcmGugun().addElement(gugunData.get(i));
			}

		} catch (SQLException e) {
		}

	}// selectSido

	private void selectAllAddress(CheckAddrVO caVO) {
		List<SearchAddrVO> selectAddrData = null;
		DefaultTableModel dtm = zsv.getDtmZipcode();
		dtm.setRowCount(0);

		Object[] zipData = null;
		SelectAddrDAO saDAO = SelectAddrDAO.getInstance();

		try {
			selectAddrData = saDAO.selectAddr(caVO);

			if (selectAddrData.isEmpty()) {
				JOptionPane.showMessageDialog(zsv, "��ȸ �� ����� �����ϴ�.");
				return;
			} // end if
			SearchAddrVO raVO = null;

			for (int i = 0; i < selectAddrData.size(); i++) {
				raVO = selectAddrData.get(i);
				zipData = new Object[2];
				zipData[0] = raVO.getZipcode();
				zipData[1] = raVO.getAddr();

				dtm.addRow(zipData);
			} // end for

		} catch (SQLException e) {
		} // end catch
	}// selectAllAddress

	private void setAddr(JTable addrTable, JTextField jtfZipcode, JTextField jtfAddr) {
		String zipcode = "";
		String addr = "";

		zipcode = (String) addrTable.getValueAt(addrTable.getSelectedRow(), 0);
		addr = (String) addrTable.getValueAt(addrTable.getSelectedRow(), 1);

		jtfZipcode.setText(zipcode);
		jtfAddr.setText(addr);
		zsv.dispose();
	}// setAddr

	@Override
	public void actionPerformed(ActionEvent e) {
		sidoName = zsv.getCbSido().getSelectedItem().toString().trim();
		gugunName = zsv.getCbGugun().getSelectedItem().toString().trim();
		inputAddrName = zsv.getJtfSearch().getText().trim();
		
		if (e.getSource() == zsv.getCbSido()) {
			selectSido();
		} // end if

		if (e.getSource() == zsv.getJbtnSearch() || e.getSource() == zsv.getJtfSearch()) {// ��ȸ ��ư Ŭ�� ��
//			sidoName = zsv.getCbSido().getSelectedItem().toString().trim();
			CheckAddrVO caVO = new CheckAddrVO(sidoName, gugunName, inputAddrName);
			selectAllAddress(caVO);
			zsv.getJspTable().getVerticalScrollBar().setValue(0);
		} // end if
	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == DOUBLE_CLICK) {
			setAddr(zsv.getJtZipcode(), cdv.getJtfCusbunzi(), cdv.getJtfCusAddr());

		} // end if

	}// mouseClicked

}// class
