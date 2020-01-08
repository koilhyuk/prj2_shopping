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

import admin.dao.AdDAO;
import admin.dao.AdminDAO;
import admin.view.AdCusDetailView;
import admin.view.AdCusManageView;
import admin.vo.SelectAllCusVO;
import admin.vo.SelectCusCheckVO;
import admin.vo.SelectCusDetailDTO;

public class AdCusManageEvt extends MouseAdapter implements ActionListener {
	public static final int DOUBLE_CLICK = 2;

	private AdCusManageView acmv;
	private AdCusDetailEvt cde;
	
	public AdCusManageEvt(AdCusManageView acmv) {
		this.acmv = acmv;
		selectAllCustomer();
	}// AdCusManageEvt

	/**
	 * 상세창에 회원정보를 설정 
	 * @param jtCus
	 */
	public void setChoiceCus(JTable jtCus) {
		// 상세창에서 사용할 값 VO를 설정
		SelectCusDetailDTO scDTO = new SelectCusDetailDTO();
		String cusNum = (String) acmv.getJtCus().getValueAt(jtCus.getSelectedRow(), 0);
		String cusId = (String) acmv.getJtCus().getValueAt(jtCus.getSelectedRow(), 1);
		String cusName = (String) acmv.getJtCus().getValueAt(jtCus.getSelectedRow(), 2);
		String cusPhone = (String) acmv.getJtCus().getValueAt(jtCus.getSelectedRow(), 3);
		int cusTotalPrice = (int) acmv.getJtCus().getValueAt(jtCus.getSelectedRow(), 4);
		String cusDate = (String) acmv.getJtCus().getValueAt(jtCus.getSelectedRow(), 5);
		String cusStop = (String) acmv.getJtCus().getValueAt(jtCus.getSelectedRow(), 6);

		scDTO.setM_code(cusNum);
		scDTO.setM_id(cusId);
		scDTO.setM_name(cusName);
		scDTO.setM_phone(cusPhone);
		scDTO.setM_totalmoney(cusTotalPrice);
		scDTO.setM_joindate(cusDate);
		scDTO.setM_stopflag(cusStop);

		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			aDAO.selectDetailCus(scDTO);
			new AdCusDetailView(scDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// setChoiceCus

	/**
	 * 검색내역조회
	 */
	public void selectAllCustomer() {
		DefaultTableModel dtmCus = acmv.getDtmCusList();
		dtmCus.setRowCount(0);
		String jtfData = acmv.getJtfSearch().getText().trim();
		int comboIndex = acmv.getJcbSearch().getSelectedIndex();

		SelectCusCheckVO sccVO = null;
		AdDAO uDAO = AdDAO.getInstance();
		List<SelectAllCusVO> cusData = null;
		SelectAllCusVO sacVO = null;
		Object[] tempData = null;
		try {
			sccVO = new SelectCusCheckVO(comboIndex,jtfData);
			cusData = uDAO.selectAllCus(sccVO);
			
			if (cusData.isEmpty()) {// 조회할 리스트가 없다면
				JOptionPane.showMessageDialog(acmv, "조회가능한 회원이 없습니다.");
				acmv.getJtfSearch().setText("");// 초기화
				acmv.getJtfSearch().requestFocus(); // 커서위치
				selectAllCustomer();
			} // end if
			
			for (int i = 0; i < cusData.size(); i++) {
				sacVO = cusData.get(i);
				tempData = new Object[7];
				tempData[0] = sacVO.getCode();
				tempData[1] = sacVO.getId();
				tempData[2] = sacVO.getName();
				tempData[3] = sacVO.getPhone();
				tempData[4] = new Integer(sacVO.getUseCash());
				tempData[5] = sacVO.getJoinData();
				tempData[6] = sacVO.getStopflag();
				dtmCus.addRow(tempData);
				
			} // end for
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// selectAllCustomer

	
	@Override
	public void actionPerformed(ActionEvent ae) {
		//조회버튼 클릭시 
		if (ae.getSource() == acmv.getJbtnSearch() || ae.getSource() == acmv.getJtfSearch()) {
			
			int index= acmv.getJcbSearch().getSelectedIndex(); 
			String search = acmv.getJtfSearch().getText().trim();
			switch (index) {
			case 0: //전체조회
				JOptionPane.showMessageDialog(acmv, "전체 회원이 조회되었습니다.");
					acmv.getJtfSearch().setText("");
					acmv.getJtfSearch().requestFocus();
					selectAllCustomer();
					break;
			case 1: //회원번호
				if(search.isEmpty()) {
					JOptionPane.showMessageDialog(acmv, "회원번호를 입력해주세요.");
					acmv.getJtfSearch().setText("");
					acmv.getJtfSearch().requestFocus();
				}//end if
					selectAllCustomer();
				break;
			case 2: //아이디
				if(search.isEmpty()) {
					JOptionPane.showMessageDialog(acmv, "아이디를 입력해주세요.");
					acmv.getJtfSearch().setText("");
					acmv.getJtfSearch().requestFocus();
				}//end if
				selectAllCustomer();
				break;
			case 3: //이름
				if(search.isEmpty()) {
					JOptionPane.showMessageDialog(acmv, "이름를 입력해주세요.");
					acmv.getJtfSearch().setText("");
					acmv.getJtfSearch().requestFocus();
				}//end if
				selectAllCustomer();
				break;
			case 4: //전화번호
				if(search.isEmpty()) {
					JOptionPane.showMessageDialog(acmv, "전화번호를 입력해주세요.");
					acmv.getJtfSearch().setText("");
					acmv.getJtfSearch().requestFocus();
				}//end if
				selectAllCustomer();
				break;
			}//end switch
		} // end if
	
	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getClickCount() == DOUBLE_CLICK) {
			if (me.getSource() == acmv.getJtCus()) {
				JTable jtCus = acmv.getJtCus();
				setChoiceCus(jtCus);
			} // end if
		} // end if
		if (me.getSource() == acmv.getJtfSearch()) { // 조회 T.F를 누르면 초기화
			acmv.getJtfSearch().setText("");
		} // end if

	}// mouseClicked

}// class
