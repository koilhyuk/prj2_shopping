package user.controller.content;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import user.dao.ClientDAO;
import user.view.content.UserCusDataView;
import user.vo.content.SelectCusDataVO;

public class UserCusDataEvt implements ActionListener {
	private UserCusDataView ucd;
	private static String id;

	public UserCusDataEvt(UserCusDataView ucd, String id) {
		this.ucd = ucd;
		this.id = id;
	}// UserCusDataEvt
	
	// ����
//	public void modifyMyPage() {
//		String id = ucd.getJtfId().getText().trim();
//		String pass = ucd.getJtfPassword().getText().trim();
//		String newpass = ucd.getJtfChangePass().getText().trim();
//		String addr = ucd.getJtfAddr().getText().trim();
//		String birth = ucd.getJtfBirth().getText().trim();
//		String phone = "-" + ucd.getJtfPhoneFront().getText() + "-" + ucd.getJtfPhoneBehind().getText();
//		String email = ucd.getJtfEmail().getText().trim();
//		String cipherText = "";// ��ȣȭ
//		
//		try {
//			cipherText = DataEncrypt.messageDigest("MD5", newpass);
//		} catch (NoSuchAlgorithmException e1) {
//			e1.printStackTrace();
//
//			MyPageUpdateVO mpuVO = new MyPageUpdateVO(id, pass, newpass, addr, birth, phone, email, cipherText);
//			ClientDAO cDAO = ClientDAO.getInstance();
//
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
//	}// modifyMyPage

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ucd.getJbtConfirm()) {// ���� ��ư Ŭ��
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
	}// actionPerformed
}// class








