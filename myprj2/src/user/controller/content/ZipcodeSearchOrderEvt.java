package user.controller.content;

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

import user.dao.SelectAddrDAO;
import user.view.content.PayView;
import user.view.content.ZipcodeSearchOrderView;
import user.vo.login.CheckAddrVO;
import user.vo.login.SearchAddrVO;

public class ZipcodeSearchOrderEvt extends MouseAdapter implements ActionListener {
	public static final int DOUBLE_CLICK = 2;

	private PayView pv;
	private ZipcodeSearchOrderView zsov;

	private String sidoName;
	private String gugunName;
	private String inputAddrName;

	public ZipcodeSearchOrderEvt(PayView pv, ZipcodeSearchOrderView zsov) {
		this.pv = pv;
		this.zsov = zsov;
	}// ZipcodeSearchEvt

	private void selectSido() {

		zsov.getDcmGugun().removeAllElements();
		SelectAddrDAO saDAO = SelectAddrDAO.getInstance();
		List<String> gugunData = null;

		try {
			if (sidoName.equals("전체") || sidoName.equals("세종")) {
				zsov.getDcmGugun().addElement("전체");
				return;
			} // end if

			gugunData = saDAO.selectGugun(sidoName.trim());
			zsov.getDcmGugun().addElement("전체");
			for (int i = 0; i < gugunData.size(); i++) {
				zsov.getDcmGugun().addElement(gugunData.get(i));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// selectSido

	private void selectAllAddress(CheckAddrVO caVO) {
		List<SearchAddrVO> selectAddrData = null;
		DefaultTableModel dtm = zsov.getDtmZipcode();
		dtm.setRowCount(0);

		Object[] zipData = null;
		SelectAddrDAO saDAO = SelectAddrDAO.getInstance();

		try {
			selectAddrData = saDAO.selectAddr(caVO);

			if (selectAddrData.isEmpty()) {
				JOptionPane.showMessageDialog(zsov, "조회 된 결과가 없습니다.");
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
			e.printStackTrace();
		} // end catch
	}// selectAllAddress

	private void setAddr(JTable addrTable, JTextField jtfZipcode, JTextField jtfAddr) {
		String zipcode = "";
		String addr = "";

		zipcode = (String) addrTable.getValueAt(addrTable.getSelectedRow(), 0);
		addr = (String) addrTable.getValueAt(addrTable.getSelectedRow(), 1);

		jtfZipcode.setText(zipcode);
		jtfAddr.setText(addr);
		zsov.dispose();
	}// setAddr

	@Override
	public void actionPerformed(ActionEvent e) {
		sidoName = zsov.getCbSido().getSelectedItem().toString().trim();
		gugunName = zsov.getCbGugun().getSelectedItem().toString().trim();
		inputAddrName = zsov.getJtfSearch().getText().trim();

		if (e.getSource() == zsov.getCbSido()) {
			selectSido();
		} // end if

		if (e.getSource() == zsov.getJbtnSearch() || e.getSource() == zsov.getJtfSearch()) {// 조회 버튼 클릭 시
			CheckAddrVO caVO = new CheckAddrVO(sidoName, gugunName, inputAddrName);
			selectAllAddress(caVO);
			zsov.getJspTable().getVerticalScrollBar().setValue(0);
		} // end if
	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == DOUBLE_CLICK) {
			setAddr(zsov.getJtZipcode(), pv.getJtfzipcode(), pv.getJtfDelivery());
			pv.getJtfDetailDel().setText("");

		} // end if

	}// mouseClicked

}// class
