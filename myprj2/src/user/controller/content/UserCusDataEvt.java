package user.controller.content;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import user.dao.ClientDAO;
import user.view.content.UserCusDataView;
import user.view.content.UserCusPwResetView;
import user.view.content.ZipcodeSearchCusView;
import user.vo.content.UpdateCusDataVO;

public class UserCusDataEvt implements ActionListener {
	private UserCusDataView ucd;
	private static String id;

	public UserCusDataEvt(UserCusDataView ucd, String id) {
		this.ucd = ucd;
		this.id = id;
	}// UserCusDataEvt
	
	// ����
	//m_phone,m_detail_addr,m_email , z_seq, m_id,z_zipcode, z_addr
	public void modifyMyPage() {
		String id = ucd.getJtfId().getText().trim();
		String addr = ucd.getJtfAddr().getText().trim();
		String email = ucd.getJtfEmail().getText().trim();
		String detail_addr=ucd.getJtfAddress().getText().trim();
		String phone1=ucd.getJcbPhoneNum().getSelectedItem().toString();
		String phone2= "-"+ucd.getJtfPhoneNum1().getText().trim();
		String phone3="-"+ucd.getJtfPhoneNum2().getText().trim();
		String phone=phone1+phone2+phone3;
		String zipcode= ucd.getJtfZipcode().getText().trim();
		if(addr.isEmpty()) {
			JOptionPane.showConfirmDialog(ucd, "�ּҸ� �Է����ּ���");
			ucd.getJtfAddr().setText("");
			ucd.getJtfAddr().requestFocus();
			return;
		}//end if
		if(zipcode.isEmpty()) {
			JOptionPane.showConfirmDialog(ucd, "�ּҸ� �Է����ּ���");
			ucd.getJtfZipcode().setText("");
			ucd.getJtfZipcode().requestFocus();
			return;
		}//end if
		if(email.isEmpty()) {
			if(!email.contains("@")&&!email.contains(".")) {
				JOptionPane.showConfirmDialog(ucd, "�̸����� �ùٸ��� �Է����ּ���");
				ucd.getJtfEmail().setText("");
				ucd.getJtfEmail().requestFocus();
				return;
			}//end if
		}//end if
		if(ucd.getJtfPhoneNum1().getText().trim().isEmpty()) {
			if(ucd.getJtfPhoneNum1().getText().length()!=4) {
				JOptionPane.showConfirmDialog(ucd, "��ȭ��ȣ4�ڸ��� �Է����ּ���");
				ucd.getJtfPhoneNum1().setText("");
				ucd.getJtfPhoneNum1().requestFocus();
				return;
			}//end if
		}//end if
		if(ucd.getJtfPhoneNum2().getText().trim().isEmpty()) {
			if(ucd.getJtfPhoneNum2().getText().length()!=4) {
				JOptionPane.showConfirmDialog(ucd, "��ȭ��ȣ4�ڸ��� �Է����ּ���");
				ucd.getJtfPhoneNum2().setText("");
				ucd.getJtfPhoneNum2().requestFocus();
				return;
			}//end if
		}//end if
		UpdateCusDataVO ucVO= new UpdateCusDataVO(phone, addr, email,id, zipcode, detail_addr);
			ClientDAO cDAO = ClientDAO.getInstance();
			try {
				if (cDAO.updateCusData(ucVO)) {
					JOptionPane.showMessageDialog(ucd, "������ �Ϸ�Ǿ����ϴ�");
					ucd.dispose();
				} // end if
			} catch (SQLException e) {
				e.printStackTrace();
			} // end catch
	}// modifyMyPage

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ucd.getJbtConfirm()) {// ���� ��ư Ŭ��
			modifyMyPage();
		} // end if

		if (ae.getSource() == ucd.getJbtWithdrawal()) {// Ż�� ��ư Ŭ��
			switch (JOptionPane.showConfirmDialog(ucd, "ȸ�� Ż�� �Ͻðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				
			}// end switch
		} // end if
		if (ae.getSource() == ucd.getJbtnPass()) {// ��й�ȣ ����
				new UserCusPwResetView(ucd,id);
		} // end if
		if (ae.getSource() == ucd.getJbtnSearchAddr()) {// �ּҰ˻�
				new ZipcodeSearchCusView(ucd);
		} // end if
	}// actionPerformed
}// class








