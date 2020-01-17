package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.util.cipher.DataEncrypt;
import user.dao.ClientDAO;
import user.view.content.UserCusPwResetView;
import user.vo.login.LoginPwResetVO;

public class UserCusPwResetEvt implements ActionListener {
	private UserCusPwResetView lpr;
	private String id;

	public UserCusPwResetEvt(UserCusPwResetView lpr, String id) {
		this.lpr = lpr;
		this.id = id;
	}// UserCusPwResetEvt

	public void pwReset() {
		String pass = lpr.getJtfNewPw().getText().trim();
		String cipherText = "";
		try {
			cipherText = DataEncrypt.messageDigest("MD5", pass);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} // end catch
			// ��ȣȭ
		if (pass.isEmpty() || !pass.equals(lpr.getJtfNewPwConfirm().getText())) {
			JOptionPane.showMessageDialog(lpr, "��й�ȣ�� ��й�ȣ Ȯ���� ��ġ���� �ʽ��ϴ�");
			lpr.getJtfNewPw().setText("");
			lpr.getJtfNewPwConfirm().setText("");
			lpr.getJtfNewPw().requestFocus();
			return;
		} //
		LoginPwResetVO lprVO = new LoginPwResetVO(id, cipherText);
		ClientDAO cDAO = ClientDAO.getInstance();
		try {
			if (cDAO.updateResetPw(lprVO)) {
				JOptionPane.showMessageDialog(lpr, "��й�ȣ�� �缳�� �Ǿ����ϴ�");
				lpr.dispose();
			} // end if
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(lpr, "��й�ȣ�� ������� �ʾҽ��ϴ�");
		} // end catch
	}// pwReset

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == lpr.getJbtConfirm()) {// Ȯ�� ��ư Ŭ��
			pwReset();
		} // end if

		if (ae.getSource() == lpr.getJbtCancle()) {// ��� ��ư Ŭ��
			switch (JOptionPane.showConfirmDialog(lpr, "��й�ȣ �缳�� â�� �����Ͻðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				lpr.dispose();
			}// end switch
		} // end if
	}// actionPerformed

}// class
