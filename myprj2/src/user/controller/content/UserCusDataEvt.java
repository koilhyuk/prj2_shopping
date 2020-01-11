package user.controller.content;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import user.view.content.UserCusDataView;
import user.view.content.UserCusPwResetView;
import user.view.content.ZipcodeSearchCusView;

public class UserCusDataEvt implements ActionListener {
	private UserCusDataView ucd;
	private static String id;

	public UserCusDataEvt(UserCusDataView ucd, String id) {
		this.ucd = ucd;
		this.id = id;
	}// UserCusDataEvt
	
	// ����
	public void modifyMyPage() {
		String id = ucd.getJtfId().getText().trim();
//		String pass = ucd.getJpfPw().getPassword().toString();
//		String newpass = ucd.getJpfPwConfirm().getPassword().toString();
		String addr = ucd.getJtfAddr().getText().trim();
		String birth = ucd.getJtfBirth().getText().trim();
//		String phone = "-" + ucd.getJtfPhoneFront().getText() + "-" + ucd.getJtfPhoneBehind().getText();
		String email = ucd.getJtfEmail().getText().trim();
		String cipherText = "";// ��ȣȭ
		String cipherText1 = "";// ��ȣȭ
		
//		try {
//			cipherText = DataEncrypt.messageDigest("MD5", pass);
//			cipherText1 = DataEncrypt.messageDigest("MD5", newpass);
//		} catch (NoSuchAlgorithmException e1) {
//			e1.printStackTrace();

//			MyPageUpdateVO mpuVO = new MyPageUpdateVO(id, pass, newpass, addr, birth, phone, email, cipherText);
//			ClientDAO cDAO = ClientDAO.getInstance();

//			try {
//				if (cDAO.updateMyPage(mpuVO)) {
//					JOptionPane.showMessageDialog(ucd, "������ �Ϸ�Ǿ����ϴ�");
//					ucd.dispose();
//				} // end if
//			} catch (SQLException e) {
//				e.printStackTrace();
//				JOptionPane.showMessageDialog(ucd, "������ �����Ͽ����ϴ�");
//			} // end catch
//		} // end switch
	}// modifyMyPage

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ucd.getJbtConfirm()) {// ���� ��ư Ŭ��
			modifyMyPage();
//			if (ucd.getJtfPassword().getText().equals(ucd.getJtfChangePass().getText())) {
//				modifyMyPage();
//			} else {
//				JOptionPane.showMessageDialog(ucd, "�Էµ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�");
//			} // end if
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








