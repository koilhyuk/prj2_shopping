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
	 * ��â�� ȸ�������� ���� 
	 * @param jtCus
	 */
	public void setChoiceCus(JTable jtCus) {
		// ��â���� ����� �� VO�� ����
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
	 * �˻�������ȸ
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
			
			if (cusData.isEmpty()) {// ��ȸ�� ����Ʈ�� ���ٸ�
				JOptionPane.showMessageDialog(acmv, "��ȸ������ ȸ���� �����ϴ�.");
				acmv.getJtfSearch().setText("");// �ʱ�ȭ
				acmv.getJtfSearch().requestFocus(); // Ŀ����ġ
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
		//��ȸ��ư Ŭ���� 
		if (ae.getSource() == acmv.getJbtnSearch() || ae.getSource() == acmv.getJtfSearch()) {
			
			int index= acmv.getJcbSearch().getSelectedIndex(); 
			String search = acmv.getJtfSearch().getText().trim();
			switch (index) {
			case 0: //��ü��ȸ
				JOptionPane.showMessageDialog(acmv, "��ü ȸ���� ��ȸ�Ǿ����ϴ�.");
					acmv.getJtfSearch().setText("");
					acmv.getJtfSearch().requestFocus();
					selectAllCustomer();
					break;
			case 1: //ȸ����ȣ
				if(search.isEmpty()) {
					JOptionPane.showMessageDialog(acmv, "ȸ����ȣ�� �Է����ּ���.");
					acmv.getJtfSearch().setText("");
					acmv.getJtfSearch().requestFocus();
				}//end if
					selectAllCustomer();
				break;
			case 2: //���̵�
				if(search.isEmpty()) {
					JOptionPane.showMessageDialog(acmv, "���̵� �Է����ּ���.");
					acmv.getJtfSearch().setText("");
					acmv.getJtfSearch().requestFocus();
				}//end if
				selectAllCustomer();
				break;
			case 3: //�̸�
				if(search.isEmpty()) {
					JOptionPane.showMessageDialog(acmv, "�̸��� �Է����ּ���.");
					acmv.getJtfSearch().setText("");
					acmv.getJtfSearch().requestFocus();
				}//end if
				selectAllCustomer();
				break;
			case 4: //��ȭ��ȣ
				if(search.isEmpty()) {
					JOptionPane.showMessageDialog(acmv, "��ȭ��ȣ�� �Է����ּ���.");
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
		if (me.getSource() == acmv.getJtfSearch()) { // ��ȸ T.F�� ������ �ʱ�ȭ
			acmv.getJtfSearch().setText("");
		} // end if

	}// mouseClicked

}// class
