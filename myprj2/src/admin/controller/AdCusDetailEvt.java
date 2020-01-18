package admin.controller;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import admin.dao.AdminDAO;
import admin.view.AdCusDetailView;
import admin.view.ZipcodeSearchView;
import admin.vo.UpdateCustomerVO;
import admin.vo.updateStopVO;

/**
 * �ݱ� , ����, ����
 * @author owner
 */
public class AdCusDetailEvt implements ActionListener {
	private AdCusDetailView sdv;
	private AdCusManageEvt cme;

	public AdCusDetailEvt(AdCusDetailView sdv) {
		this.sdv = sdv;
	}// AdCusDetailEvt

	/**
	 * ȸ�������� ����
	 */
	public void modifyCus() {
		String name = sdv.getJtfCusName().getText().trim(); // ȸ���̸�
		switch (JOptionPane.showConfirmDialog(sdv, name + " ���� ȸ�������� �����Ͻðڽ��ϱ�?")) {
		case JOptionPane.OK_OPTION: // Ȯ��
			String code = sdv.getJtfID().getText().trim();
//			String name=sdv.getJtfCusName().getText().trim();
			String birth = sdv.getJtfBirth().getText().trim();
			String gender = sdv.getJtfGender().getText().trim();
			if(gender.equals("����")) {
				gender="M";
			}else {
				gender="F";
			}//end if
			String phone = sdv.getJtfPhone().getText().trim();
			String detail_addr = sdv.getJtfCusAddr2().getText().trim();
			String img = new File(sdv.getJlImg().getIcon().toString()).getName();
			String email = sdv.getJtfEmail().getText().trim();
			String zipcode = sdv.getJtfCusbunzi().getText().trim();
			String addr = sdv.getJtfCusAddr().getText().trim();
			UpdateCustomerVO ucVO = new UpdateCustomerVO(code, name, birth, gender, phone, detail_addr, img, email,
					zipcode, addr);
			AdminDAO aDAO = AdminDAO.getInstance();
			try {
				if (aDAO.updateDetailCus(ucVO)) {
					JOptionPane.showMessageDialog(sdv, "ȸ�������� �����Ϸ��߽��ϴ�");
				} // end if
			} catch (SQLException e) {

				JOptionPane.showMessageDialog(sdv, "�����Ͻ� �׸��� �ùٸ��� �Է����ּ���");
			} // end catch
		}// end switch
	}// modifyCus

	/**
	 * ȸ�� ������ ���� method
	 */
	public void stopCustomer() {

		String status = sdv.getJtfStop().getText().trim();
		String code = sdv.getJtfCusNum().getText().trim();
		String name= sdv.getJtfCusName().getText().trim();
		AdminDAO aDAO = AdminDAO.getInstance();
		if (status.equals("Y")||status.equals("Ȱ��ȭ����")) { // ����X -> ������ų��
			switch (JOptionPane.showConfirmDialog(sdv, name + " ȸ������ ������Ű�ڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				try {
					status = "N"; // ������Ų��.
					String reason = JOptionPane.showInputDialog("���� �Է�\n ��) ��ģ����, ����, �߸��� �ڵ� ");
					updateStopVO usVO = new updateStopVO(status, reason, code);
					if (!aDAO.updateStopFlag(usVO)) {// ������ �����ϸ�
						JOptionPane.showMessageDialog(sdv, name + " ȸ������ �������¿� �����ϼ̽��ϴ�.");
					} else {// �����ϸ�
						sdv.getJtfStop().setText(status);
						sdv.getJlreason().setText("��Ȱ��ȭ����  [�������� : " + reason + "]");
						JOptionPane.showMessageDialog(sdv, name + " ȸ������ �������׽��ϴ�.");
					} // end else
				} catch (SQLException e) {
					e.printStackTrace();
				} // end catch
			}// end switch
		
		} else {// ������ Ǯ��
			switch (JOptionPane.showConfirmDialog(sdv, name + " ȸ������ ������ ��ü�Ͻðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				try {
					String reason = "";
					status = "Y";
					updateStopVO usVO = new updateStopVO(status, reason, code);
					if (!aDAO.updateStopFlag(usVO)) {
						JOptionPane.showMessageDialog(sdv, name + " ȸ������ ������ü�� �����ϼ̽��ϴ�.");
					} else {// �����ϸ�
						JOptionPane.showMessageDialog(sdv, name + " ȸ������ ������ �������׽��ϴ�.");
						sdv.getJtfStop().setText(status);
						sdv.getJlreason().setText("Ȱ��ȭ����");
					} // end else
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} // end if
			} // end else
		} // end switch
	}// stopCustomer

	@Override
	public void actionPerformed(ActionEvent ae) {// �ݱ�
		if (ae.getSource() == sdv.getJbtnClose()) {
			switch (JOptionPane.showConfirmDialog(sdv, "ȸ������â�� �����ðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				sdv.dispose();
			}// end switch
		} // end if

		if (ae.getSource() == sdv.getJbtnModify()) {// ����
			modifyCus();
		} // end if
		if (ae.getSource() == sdv.getJbtnAddr()) {// �ּҹ�ư
			new ZipcodeSearchView(sdv);
		} // end if
		if (ae.getSource() == sdv.getJbtnStop()) {// ����
			stopCustomer();
		} // end if
		
	}// actionPerformed

}
