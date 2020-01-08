package user.controller.login;

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
import user.view.login.JoinDetailView;
import user.view.login.ZipcodeSearchView;
import user.vo.login.CheckAddrVO;
import user.vo.login.SearchAddrVO;

public class ZipcodeSearchEvt extends MouseAdapter implements ActionListener {
	public static final int DOUBLE_CLICK = 2;

	private ZipcodeSearchView zsv;
	private JoinDetailView jdv;

	private String sidoName;
	private String gugunName;
	private String inputAddrName;

	public ZipcodeSearchEvt(ZipcodeSearchView zsv, JoinDetailView jdv) {
		this.zsv = zsv;
		this.jdv = jdv;
	}// ZipcodeSearchEvt

	private void selectSido() {

		zsv.getDcmGugun().removeAllElements();
		SelectAddrDAO saDAO = SelectAddrDAO.getInstance();
		List<String> gugunData = null;

		try {
			if (sidoName.equals("전체") || sidoName.equals("세종")) {
				zsv.getDcmGugun().addElement("전체");
				return;
			} // end if

			gugunData = saDAO.selectGugun(sidoName.trim());
			zsv.getDcmGugun().addElement("전체");
			for (int i = 0; i < gugunData.size(); i++) {
				zsv.getDcmGugun().addElement(gugunData.get(i));
			}

		} catch (SQLException e) {
			e.printStackTrace();
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
				JOptionPane.showMessageDialog(zsv, "조회 된 결과가 없습니다.");
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

		if (e.getSource() == zsv.getJbtnSearch() || e.getSource() == zsv.getJtfSearch()) {// 조회 버튼 클릭 시
//			sidoName = zsv.getCbSido().getSelectedItem().toString().trim();
			CheckAddrVO caVO = new CheckAddrVO(sidoName, gugunName, inputAddrName);
			selectAllAddress(caVO);
			zsv.getJspTable().getVerticalScrollBar().setValue(0);
		} // end if
	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == DOUBLE_CLICK) {
			setAddr(zsv.getJtZipcode(), jdv.getJtfZipcode(), jdv.getJtfAddress());

		} // end if

	}// mouseClicked

}// class
